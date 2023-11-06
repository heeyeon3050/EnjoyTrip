package com.ssafy.enjoytrip.common;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class BaseEntity {

    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private boolean isDeleted;

    public void delete(){
        this.isDeleted = true;
    }
}
