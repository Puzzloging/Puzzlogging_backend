package com.example.Puzzlogging.domain.photomosaic.repository;

import com.example.Puzzlogging.domain.photomosaic.entity.PhotoMosaicImage;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PhotoMosaicImageRepository extends JpaRepository<PhotoMosaicImage, Long> {

    List<PhotoMosaicImage> findByMemberId(Long memberId);
}
