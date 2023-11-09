package com.ssafy.enjoytrip.attraction.entity;

import com.ssafy.enjoytrip.attraction.dto.AttractionDto;
import com.ssafy.enjoytrip.common.BaseEntity;
import lombok.*;

import javax.persistence.*;

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
    @Enumerated(EnumType.STRING)
    private AttractionCategory category;
    private String description;

    public static Attraction toAttraction(AttractionDto attractionDto) {
        return Attraction.builder()
                .title(attractionDto.getTitle())
                .latitude(attractionDto.getLatitude())
                .longitude(attractionDto.getLongitude())
                .image_url(attractionDto.getImageUrl())
                .address1(attractionDto.getAddress1())
                .address2(attractionDto.getAddress2())
                .category(attractionDto.getCategory())
                .description(attractionDto.getDescription())
                .build();
    }

    public void update(AttractionDto attractionDto) {
        if (attractionDto.getTitle() != null) { this.title = attractionDto.getTitle(); }
        if (attractionDto.getLatitude() != null) { this.latitude = attractionDto.getLatitude(); }
        if (attractionDto.getLongitude() != null) { this.longitude = attractionDto.getLongitude(); }
        if (attractionDto.getImageUrl() != null) { this.image_url = attractionDto.getImageUrl(); }
        if (attractionDto.getAddress1() != null) { this.address1 = attractionDto.getAddress1(); }
        if (attractionDto.getAddress2() != null) { this.address2 = attractionDto.getAddress2(); }
        if (attractionDto.getCategory() != null) { this.category = attractionDto.getCategory(); }
        if (attractionDto.getDescription() != null) { this.description = attractionDto.getDescription(); }
    }
}