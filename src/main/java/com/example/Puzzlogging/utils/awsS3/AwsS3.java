package com.example.Puzzlogging.utils.awsS3;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.*;
import com.example.Puzzlogging.common.exception.BaseException;
import com.example.Puzzlogging.domain.trashimage.entity.TrashImage;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import static com.example.Puzzlogging.common.exception.type.ErrorCode.BAD_GATEWAY;

@RequiredArgsConstructor
@Component
public class AwsS3 {

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    private final AmazonS3 s3Client;

    public String upload(MultipartFile imageFile, String fileName) {
        try {

            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentLength(imageFile.getInputStream().available());
            objectMetadata.setContentType("image/jpg");

            s3Client.putObject(new PutObjectRequest(this.bucket, fileName, imageFile.getInputStream(), objectMetadata));

            return s3Client.getUrl(bucket, fileName).toString();

        } catch (AmazonServiceException e) {
            e.printStackTrace();
            throw new BaseException(BAD_GATEWAY);
        } catch (SdkClientException e) {
            e.printStackTrace();
            throw new BaseException(BAD_GATEWAY);
        } catch (IOException e) {
            e.printStackTrace();
            throw new BaseException(BAD_GATEWAY);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BaseException(BAD_GATEWAY);
        }
    }

    public String upload(File imageFile, String fileName) {
        try {

            s3Client.putObject(new PutObjectRequest(this.bucket, fileName, imageFile));

            imageFile.delete();

            return s3Client.getUrl(bucket, fileName).toString();

        } catch (AmazonServiceException e) {
            e.printStackTrace();
            throw new BaseException(BAD_GATEWAY);
        } catch (SdkClientException e) {
            e.printStackTrace();
            throw new BaseException(BAD_GATEWAY);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BaseException(BAD_GATEWAY);
        }
    }

    public void download(TrashImage image, String destPath) {
        try {
            S3Object s3Object = s3Client.getObject(bucket, image.getImageKey());
            S3ObjectInputStream inputStream = s3Object.getObjectContent();
            FileUtils.copyInputStreamToFile(inputStream, new File(destPath + File.separator + image.getImageName()));
        } catch (Exception e) {
            e.printStackTrace();
            throw new BaseException(BAD_GATEWAY);
        }

    }

    public void copy(String orgDirName, String copyDirName) {
        try {
            //Copy 객체 생성
            CopyObjectRequest copyObjRequest = new CopyObjectRequest(
                    this.bucket,
                    orgDirName,
                    this.bucket,
                    copyDirName
            );
            //Copy
            this.s3Client.copyObject(copyObjRequest);

            System.out.println(String.format("Finish copying [%s] to [%s]", orgDirName, copyDirName));

        } catch (AmazonServiceException e) {
            e.printStackTrace();
        } catch (SdkClientException e) {
            e.printStackTrace();
        }
    }

    public void delete(String dirName) {
        try {
            //Delete 객체 생성
            DeleteObjectRequest deleteObjectRequest = new DeleteObjectRequest(this.bucket, dirName);
            //Delete
            s3Client.deleteObject(deleteObjectRequest);
            System.out.println(String.format("[%s] deletion complete", dirName));

        } catch (AmazonServiceException e) {
            e.printStackTrace();
        } catch (SdkClientException e) {
            e.printStackTrace();
        }
    }

}
