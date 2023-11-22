package com.ssafy.enjoytrip.image.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ssafy.enjoytrip.image.entity.Image;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {
}
