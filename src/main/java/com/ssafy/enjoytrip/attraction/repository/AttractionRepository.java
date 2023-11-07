package com.ssafy.enjoytrip.attraction.repository;

import com.ssafy.enjoytrip.attraction.entity.Attraction;
import com.ssafy.enjoytrip.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AttractionRepository extends JpaRepository<Attraction, Long> {
    boolean existsById(Long id);
    boolean getAttractionsByCategory(int category);

}
