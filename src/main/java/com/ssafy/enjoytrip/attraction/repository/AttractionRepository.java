package com.ssafy.enjoytrip.attraction.repository;

import com.ssafy.enjoytrip.attraction.entity.Attraction;
import com.ssafy.enjoytrip.attraction.entity.Category;
import com.ssafy.enjoytrip.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface AttractionRepository extends JpaRepository<Attraction, Long> {
    boolean existsById(Long id);

    List<Attraction> findAllByCategory(Category category);

}
