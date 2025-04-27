package com.joblessfriend.tour.service.tour;

import com.joblessfriend.tour.dao.TourMapper;
import com.joblessfriend.tour.dto.TourFestivalDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


public interface TourService {

    void insertFestival(TourFestivalDto dto);


    void saveFestivalList(List<TourFestivalDto> list);

    public String getAreaCodes();

    public String getCategoryCodes(int pageNo, int numOfRows);

    public String getAreaBasedList(
            String areaCode,
            String sigunguCode,
            String contentTypeId,
            String arrange,
            int pageNo,
            int numOfRows);

    String getAreaBasedList(
            String areaCode,
            int numOfRows,
            String contentTypeId,
            String arrange,
            int pageNo
    );

    public String getLocationBasedList1Code(
            double mapX,
            double mapY,
            int radius,
            int contentTypeId,
            int pageNo,
            int numOfRows,
            String arrange);

    public String getFestivalList(
            String eventStartDate,
            String eventEndDate,
            String arrange,
            String listYN,
            int pageNo,
            int numOfRows
    );

    public String getSearchStay1Codes(
            int numOfRows,
            int pageNo,
            String arrange,
            String listYN
    );
    public String getSearchKeywordList(String keyword, String contentTypeId, int pageNo, int numOfRows);
    public String getSubCategory(String cat1, String cat2, int pageNo, int numOfRows);
    public String getDetailCommon1Codes(String contentId, String defaultYN);

    public String getDetailIntro1Codes(String contentId,String contentTypeId);

    public String getDetailInfo1Codes(String contentId, String contentTypeId, int pageNo, int numOfRows);

    public String getDetailImageList(String contentId, int pageNo, int numOfRows);

    public String getAreaBasedSyncList1Codes(
            int pageNo,
            int numOfRows,
            String arrange,
            String showFlag
    );

    boolean existsFestival(String contentId);

    TourFestivalDto selectFestivalDetail(String contentId);


}
