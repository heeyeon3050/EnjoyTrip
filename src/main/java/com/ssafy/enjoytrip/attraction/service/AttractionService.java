package com.ssafy.enjoytrip.attraction.service;

import com.ssafy.enjoytrip.attraction.dto.RegistAttractionDto;
import com.ssafy.enjoytrip.attraction.entity.Attraction;
import com.ssafy.enjoytrip.attraction.repository.AttractionRepository;
import com.ssafy.enjoytrip.common.dto.response.CommonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AttractionService {
    private final AttractionRepository attractionRepository;

    @Transactional
    public CommonResponse regist(RegistAttractionDto registAttractionDto) {
        Attraction attraction = Attraction.toAttraction(registAttractionDto);
        return new CommonResponse(true, "Success to create attraction", attractionRepository.save(attraction));
    }
}
