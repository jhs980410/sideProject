package com.joblessfriend.tour.dao;

import com.joblessfriend.tour.dto.TourFestivalDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TourMapper {
    void insertFestival(TourFestivalDto tourFestivalDto);
}
