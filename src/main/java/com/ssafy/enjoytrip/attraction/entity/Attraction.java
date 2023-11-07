package com.ssafy.enjoytrip.attraction.entity;

import com.ssafy.enjoytrip.attraction.dto.RegistAttractionDto;
import com.ssafy.enjoytrip.common.BaseEntity;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Attraction extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private Double latitude;
    private Double longitude;
    private String image_url;
    private String address1;
    private String address2;
    private int category;
    private String description;

    public static Attraction toAttraction(RegistAttractionDto registAttractionDto) {
        return Attraction.builder()
                .title(registAttractionDto.getTitle())
                .latitude(registAttractionDto.getLatitude())
                .longitude(registAttractionDto.getLongitude())
                .image_url(registAttractionDto.getImageUrl())
                .address1(registAttractionDto.getAddress1())
                .address2(registAttractionDto.getAddress2())
                .category(registAttractionDto.getCategory())
                .description(registAttractionDto.getDescription())
                .build();
    }
}