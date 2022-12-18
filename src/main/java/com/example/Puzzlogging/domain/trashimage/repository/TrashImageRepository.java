package com.example.Puzzlogging.domain.trashimage.repository;

import com.example.Puzzlogging.domain.trashimage.entity.TrashImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrashImageRepository extends JpaRepository<TrashImage, Long> {
}
