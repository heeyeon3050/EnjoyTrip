package com.ssafy.enjoytrip.attraction.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegistAttractionDto {
    private String title;
    private Double latitude;
    private Double longitude;
    private String imageUrl;
    private String address1;
    private String address2;
    private int category;
    private String description;;
}
