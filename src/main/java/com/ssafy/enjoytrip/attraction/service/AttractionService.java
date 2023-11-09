package com.ssafy.enjoytrip.attraction.service;

import com.ssafy.enjoytrip.attraction.dto.AttractionDto;
import com.ssafy.enjoytrip.attraction.entity.Attraction;
import com.ssafy.enjoytrip.attraction.entity.AttractionCategory;
import com.ssafy.enjoytrip.attraction.exception.AttractionNotFoundException;
import com.ssafy.enjoytrip.attraction.repository.AttractionRepository;
import com.ssafy.enjoytrip.common.dto.response.CommonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AttractionService {
    private final AttractionRepository attractionRepository;

    @Transactional
    public CommonResponse create(AttractionDto attractionDto) {
        Attraction attraction = Attraction.toAttraction(attractionDto);
        return new CommonResponse(true, "Success to create attraction", attractionRepository.save(attraction));
    }

    public CommonResponse getByCategory(AttractionCategory attractionCategory) {
        return new CommonResponse(true, "Success to get Attraction.", attractionRepository.findAllByCategory(
            attractionCategory));
    }

    @Transactional
    public CommonResponse update(Long id, AttractionDto attractionDto) {
        Optional<Attraction> optionalAttraction = attractionRepository.findById(id);

        if(optionalAttraction.isPresent()) {
            Attraction existAttraction = optionalAttraction.get();
            existAttraction.update(attractionDto);
            return new CommonResponse(true, "Success to update Attraction.", attractionRepository.save(existAttraction));
        }
        throw new AttractionNotFoundException(String.format("관광지(%s)를 찾을 수 없습니다.", id));
    }

    @Transactional
    public CommonResponse delete(Long id) {
        Optional<Attraction> optionalAttraction = attractionRepository.findById(id);

        if(optionalAttraction.isPresent()) {
            Attraction existAttraction = optionalAttraction.get();
            existAttraction.delete();
            return new CommonResponse(true, "Success to delete Attraction.", attractionRepository.save(existAttraction));
        }
        throw new AttractionNotFoundException(String.format("관광지(%s)를 찾을 수 없습니다.", id));
    }
}
