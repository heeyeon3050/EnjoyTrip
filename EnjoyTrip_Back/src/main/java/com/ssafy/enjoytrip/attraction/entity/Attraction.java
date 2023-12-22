package com.ssafy.enjoytrip.attraction.entity;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.ssafy.enjoytrip.attraction.dto.AttractionDto;
import com.ssafy.enjoytrip.common.BaseEntity;
import com.ssafy.enjoytrip.user.entity.User;

import lombok.*;

import javax.persistence.*;

import org.hibernate.annotations.Where;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Attraction{
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
    @ManyToMany
    @JsonManagedReference
    @JoinTable(
        name = "like_attraction",
        joinColumns = @JoinColumn(name = "attraction_id"),
        inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private Set<User> attraction_users = new HashSet<>();

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