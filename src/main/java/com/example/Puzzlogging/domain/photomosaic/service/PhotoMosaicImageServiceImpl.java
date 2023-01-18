package com.example.Puzzlogging.domain.photomosaic.service;

import com.example.Puzzlogging.common.exception.BaseException;
import com.example.Puzzlogging.common.exception.type.ErrorCode;
import com.example.Puzzlogging.domain.photomosaic.entity.PhotoMosaicImage;
import com.example.Puzzlogging.domain.photomosaic.repository.PhotoMosaicImageRepository;
import com.example.Puzzlogging.domain.photomosaic.service.dto.CreatePhotoMosaicImageRequest;
import com.example.Puzzlogging.domain.trashimage.entity.TrashImage;
import com.example.Puzzlogging.domain.trashimage.repository.TrashImageRepository;
import com.example.Puzzlogging.utils.awsS3.AwsS3;
import com.example.Puzzlogging.domain.photomosaic.service.dto.PhotoMosaicImageResponse;
import com.example.Puzzlogging.utils.photomosaic_generator.PhotoMosaicGenerator;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PhotoMosaicImageServiceImpl implements PhotoMosaicImageService {

    private final AwsS3 awsS3;
    private final PhotoMosaicGenerator generator;
    private final PhotoMosaicImageRepository photoMosaicImageRepository;
    private final TrashImageRepository trashImageRepository;

    @Override
    public PhotoMosaicImage uploadPhotoMosaicImage(CreatePhotoMosaicImageRequest request, MultipartFile image) throws IOException {
        //멤버 작업 폴더 생성
        File memberDir = generator.mkMemberDir(request.getMemberId());
        String memberDirPath = memberDir.getAbsolutePath();

        Path lockPath = Paths.get(memberDirPath + File.separator + ".lock").toAbsolutePath();
        FileChannel fileChannel = FileChannel.open(lockPath, StandardOpenOption.WRITE);

        //락 학인
        FileLock fileLock = fileChannel.tryLock();
        if (fileLock == null) {
            throw new BaseException(ErrorCode.WAIT_TO_GENERATE);
        }

        //메인 사진 저장
        Path mainImagePath = Paths.get(memberDirPath + File.separator + "main_image" + File.separator + "img.jpg").toAbsolutePath();
        image.transferTo(mainImagePath.toFile());

        //타일 파일 가져와서 저장
        List<TrashImage> trashImages = request.getColors().stream()
                .map(color -> trashImageRepository.findByMemberIdAndColor(request.getMemberId(), color).stream().findAny().get()).toList();
        trashImages.forEach(trashImage -> awsS3.download(trashImage, memberDirPath + File.separator + "filler_images"));

        //모자이크 사진 생성후 업로드
        File file = generator.generatePhotoMosaic(request.getMemberId());
        String imagePath;

        if (file.exists()) {
            imagePath = awsS3.upload(file, request.getMemberId() + "/photoMosaicImage/" + UUID.randomUUID() + ".jpg");
        } else {
            throw new BaseException(ErrorCode.FAIL_TO_GENERATE);
        }
        // 사용된 타일 사진 삭제
        trashImageRepository.deleteAll(trashImages);

        //lock 해제
        fileLock.release();
        fileChannel.close();

        //멤버 작업 폴더 삭제
        FileUtils.deleteDirectory(memberDir);

        return PhotoMosaicImage.newInstance(imagePath, request.getMemberId());
    }

    @Override
    public List<PhotoMosaicImageResponse> getPhotoMosaicImageList(Long memberId) {
        return photoMosaicImageRepository.findByMemberId(memberId).stream()
                .map(photoMosaicImage -> PhotoMosaicImageResponse.of(photoMosaicImage.getImagePath()))
                .collect(Collectors.toList());
    }

    @Override
    public PhotoMosaicImageResponse addPhotoMosaicImage(PhotoMosaicImage photoMosaicImage) {
        photoMosaicImageRepository.save(photoMosaicImage);
        return PhotoMosaicImageResponse.of(photoMosaicImage.getImagePath());
    }
}
