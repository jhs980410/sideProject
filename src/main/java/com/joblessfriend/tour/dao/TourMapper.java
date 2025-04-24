package com.joblessfriend.tour.dao;

import com.joblessfriend.tour.dto.TourFestivalDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface TourMapper {
    void insertFestival(TourFestivalDto tourFestivalDto);

    boolean existsFestival(String contentId);

    TourFestivalDto selectFestivalDetail(@Param("contentId")String contentId);

}
