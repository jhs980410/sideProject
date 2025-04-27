package com.joblessfriend.tour.dto;

import lombok.Data;

@Data
public class TourFestivalDto {
    private String contentId;
    private String title;
    private String addr1;
    private String eventStartDate;
    private String eventEndDate;
    private String tel;
    private String areaCode;
    private String firstImage;
    private double mapX;
    private double mapY;
}
