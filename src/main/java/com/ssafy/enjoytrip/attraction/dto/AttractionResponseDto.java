package com.ssafy.enjoytrip.attraction.dto;

import com.ssafy.enjoytrip.attraction.entity.Attraction;
import com.ssafy.enjoytrip.attraction.entity.AttractionCategory;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AttractionResponseDto {
	private Long id;
	private String title;
	private Double latitude;
	private Double longitude;
	private String image_url;
	private String address1;
	private String address2;
	private AttractionCategory category;
	private String description;
	private Boolean isLiked;
	private Long likeCount;

	public static AttractionResponseDto toAttractionDto(Attraction attraction, Boolean isLiked, Long likeCount) {
		if (attraction == null) {
			return null;
		}

		return AttractionResponseDto.builder()
			.id(attraction.getId())
			.title(attraction.getTitle())
			.latitude(attraction.getLatitude())
			.longitude(attraction.getLongitude())
			.image_url(attraction.getImage_url())
			.address1(attraction.getAddress1())
			.address2(attraction.getAddress2())
			.category(attraction.getCategory())
			.description(attraction.getDescription())
			.isLiked(isLiked)
			.likeCount(likeCount)
			.build();
	}

}
