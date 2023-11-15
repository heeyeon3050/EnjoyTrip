package com.ssafy.enjoytrip.attraction.entity;

import lombok.Getter;

public enum AttractionCategory {
    NORMAL("일반"),
    MEDIA("촬영지"),
    HERITAGE("문화재");

    @Getter
    private final String value;

    AttractionCategory(String value) {
        this.value = value;
    }
}
