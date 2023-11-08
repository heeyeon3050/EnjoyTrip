package com.ssafy.enjoytrip.attraction.entity;

import lombok.Getter;

public enum Category {
    CATEGORY_NORMAL("일반"),
    CATEGORY_MEDIA("촬영지"),
    CATEGORY_HERITAGE("문화재");

    @Getter
    private final String value;

    Category(String value) {
        this.value = value;
    }
}
