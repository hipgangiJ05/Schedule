package com.schedule.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class UpdateScheduleRequest {
    private String title;
    private String author;
    private String password;

    public UpdateScheduleRequest(String title, String author, String password) {
        this.title = title;
        this.author = author;
        this.password = password;
    }
}
