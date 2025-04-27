package com.joblessfriend.tour.service.tour;


import com.joblessfriend.tour.dao.TourMapper;
import com.joblessfriend.tour.dto.TourFestivalDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;


@Service
public class TourServiceImpl implements TourService {

    @Value("${tourapi.service-key}")
    private String serviceKey;

    @Autowired
    private TourMapper tourMapper;

    @Override
    public void saveFestivalList(List<TourFestivalDto> list) {
        for (TourFestivalDto dto : list) {
            tourMapper.insertFestival(dto);
        }
    }

    @Override
    public void insertFestival(TourFestivalDto dto) {
        tourMapper.insertFestival(dto);
    }

    //지역//
    @Override
    public String getAreaCodes() {


        URI uri = UriComponentsBuilder.fromHttpUrl("http://apis.data.go.kr/B551011/KorService1/areaCode1")
                .queryParam("serviceKey", serviceKey)
                .queryParam("MobileOS", "ETC")
                .queryParam("MobileApp", "MyTourProject")
                .queryParam("_type", "json")
                .build(true)  //  이게 중요, true: 인코딩 방지
                .toUri();

        RestTemplate restTemplate = new RestTemplate();
        System.out.println("카테고리 : " + uri);
        ResponseEntity<String> response = restTemplate.getForEntity(uri, String.class);
        System.out.println("응답결과: " + response.getBody());
        return response.getBody();
    }

    //대분류 카테고리
    @Override
    public String getCategoryCodes(int pageNo, int numOfRows) {
        URI uri = UriComponentsBuilder.fromHttpUrl("http://apis.data.go.kr/B551011/KorService1/categoryCode1")
                .queryParam("serviceKey", serviceKey)
                .queryParam("MobileOS", "ETC")
                .queryParam("MobileApp", "MyTourProject")
                .queryParam("_type", "json")
                .queryParam("pageNo", pageNo)
                .queryParam("numOfRows", numOfRows)
                .build(true)
                .toUri();

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity(uri, String.class);
        return response.getBody();
    }


    // 소분류 조회 (cat1, cat2, contentTypeId까지)
    @Override
    public String getSubCategory(String cat1, String cat2, int pageNo, int numOfRows) {
        URI uri = UriComponentsBuilder.fromHttpUrl("http://apis.data.go.kr/B551011/KorService1/categoryCode1")
                .queryParam("serviceKey", serviceKey)
                .queryParam("MobileOS", "ETC")
                .queryParam("MobileApp", "AppTest")
                .queryParam("_type", "json")
                .queryParam("contentTypeId", "12")
                .queryParam("cat1", cat1)
                .queryParam("cat2", cat2)
                .queryParam("pageNo", pageNo)
                .queryParam("numOfRows", numOfRows)
                .build(true)
                .toUri();

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity(uri, String.class);
        return response.getBody();
    }


    //지역 기반 관광정보 조회//
    @Override
    public String getAreaBasedList(
            String areaCode,
            String sigunguCode,
            String contentTypeId,
            String arrange,
            int pageNo,
            int numOfRows
    ) {
        URI uri = UriComponentsBuilder
                .fromHttpUrl("http://apis.data.go.kr/B551011/KorService1/areaBasedList1")
                .queryParam("serviceKey", serviceKey)
                .queryParam("MobileOS", "ETC")
                .queryParam("MobileApp", "MyTourProject")
                .queryParam("_type", "json")
                .queryParam("areaCode", areaCode)
                .queryParam("sigunguCode", sigunguCode)
                .queryParam("contentTypeId", contentTypeId)
                .queryParam("arrange", arrange)
                .queryParam("pageNo", pageNo)
                .queryParam("numOfRows", numOfRows)
                .build(true)
                .toUri();

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity(uri, String.class);

        System.out.println("응답결과: " + response.getBody());

        return response.getBody();
    }

    @Override
    public String getAreaBasedList(
            String areaCode,
            int numOfRows,
            String contentTypeId,
            String arrange,
            int pageNo
    ) {
        URI uri = UriComponentsBuilder
                .fromHttpUrl("http://apis.data.go.kr/B551011/KorService1/areaBasedList1")
                .queryParam("serviceKey", serviceKey)
                .queryParam("MobileOS", "ETC")
                .queryParam("MobileApp", "MyTourProject")
                .queryParam("_type", "json")
                .queryParam("areaCode", areaCode)
                .queryParam("contentTypeId", contentTypeId)
                .queryParam("arrange", arrange)
                .queryParam("pageNo", pageNo)
                .queryParam("numOfRows", numOfRows)
                .build(true)
                .toUri();

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity(uri, String.class);

        System.out.println("응답결과: " + response.getBody());

        return response.getBody();
    }



    //위치기반 관광정보조회
    @Override
    public String getLocationBasedList1Code(
            double mapX,
            double mapY,
            int radius,
            int contentTypeId,
            int pageNo,
            int numOfRows,
            String arrange
    ) {
        URI uri = UriComponentsBuilder.fromHttpUrl("http://apis.data.go.kr/B551011/KorService1/locationBasedList1")
                .queryParam("serviceKey", serviceKey)
                .queryParam("MobileOS", "ETC")
                .queryParam("MobileApp", "MyTourProject")
                .queryParam("_type", "json")
                .queryParam("mapX", mapX)
                .queryParam("mapY", mapY)
                .queryParam("radius", radius)
                .queryParam("contentTypeId", contentTypeId)
                .queryParam("pageNo", pageNo)
                .queryParam("numOfRows", numOfRows)
                .queryParam("arrange", arrange)
                .build(true)
                .toUri();

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity(uri, String.class);
        System.out.println("응답결과: " + response.getBody());

        return response.getBody();
    }

    //키워드기반 관광정보조회
    @Override
    public String getSearchKeywordList(String keyword, String contentTypeId, int pageNo, int numOfRows) {
        URI uri = UriComponentsBuilder.fromHttpUrl("http://apis.data.go.kr/B551011/KorService1/searchKeyword1")
                .queryParam("serviceKey", serviceKey)
                .queryParam("MobileOS", "ETC")
                .queryParam("MobileApp", "MyTourProject")
                .queryParam("_type", "json")
                .queryParam("keyword", keyword)
                .queryParam("contentTypeId", contentTypeId)
                .queryParam("pageNo", pageNo)
                .queryParam("numOfRows", numOfRows)
                .queryParam("listYN", "Y")
                .queryParam("arrange", "C")
                .build(true)
                .toUri();

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity(uri, String.class);
        return response.getBody();
    }


    // 행사 정보 조회 시작날짜 , 끝나는날짜 , 정렬구분 , 갯수 ,
    @Override
    public String getFestivalList(
            String eventStartDate,
            String eventEndDate,
            String arrange,
            String listYN,
            int pageNo,
            int numOfRows
    ) {
        URI uri = UriComponentsBuilder.fromHttpUrl("http://apis.data.go.kr/B551011/KorService1/searchFestival1")
                .queryParam("serviceKey", serviceKey)
                .queryParam("MobileOS", "ETC")
                .queryParam("MobileApp", "MyTourProject")
                .queryParam("_type", "json")
                .queryParam("eventStartDate", eventStartDate)
                .queryParam("eventEndDate", eventEndDate)
                .queryParam("arrange", arrange)
                .queryParam("listYN", listYN)
                .queryParam("pageNo", pageNo)
                .queryParam("numOfRows", numOfRows)
                .build(true)
                .toUri();

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity(uri, String.class);

        System.out.println("응답결과: " + response.getBody());
        return response.getBody();
    }

    //숙박정보 조회 메서드//
    @Override
    public String getSearchStay1Codes(
            int numOfRows,
            int pageNo,
            String arrange,
            String listYN
    ) {
        URI uri = UriComponentsBuilder
                .fromHttpUrl("http://apis.data.go.kr/B551011/KorService1/searchStay1")
                .queryParam("serviceKey", serviceKey)
                .queryParam("MobileOS", "ETC")
                .queryParam("MobileApp", "MyTourProject")
                .queryParam("_type", "json")
                .queryParam("numOfRows", numOfRows)
                .queryParam("pageNo", pageNo)
                .queryParam("arrange", arrange)
                .queryParam("listYN", listYN)
                .build(true)
                .toUri();

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity(uri, String.class);

        System.out.println("📦 응답결과: " + response.getBody());
        return response.getBody();
    }

    //타입별공통정보(제목, 연락처, 주소, 좌표, 개요정보등)를조회 //
    @Override
    public String getDetailCommon1Codes(String contentId, String defaultYN) {

        URI uri = UriComponentsBuilder.fromHttpUrl("http://apis.data.go.kr/B551011/KorService1/detailCommon1")
                .queryParam("serviceKey", serviceKey)
                .queryParam("MobileOS", "ETC")
                .queryParam("MobileApp", "MyTourProject")
                .queryParam("_type", "json")
                .queryParam("contentId", contentId)
                .queryParam("defaultYN", defaultYN)
                .build(true)
                .toUri();

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity(uri, String.class);

        System.out.println("응답결과: " + response.getBody());

        return response.getBody();
    }


    // 소개정보 조회
    @Override
    public String getDetailIntro1Codes(String contentId, String contentTypeId) {
        URI uri = UriComponentsBuilder
                .fromHttpUrl("http://apis.data.go.kr/B551011/KorService1/detailIntro1")
                .queryParam("serviceKey", serviceKey)
                .queryParam("MobileOS", "ETC")
                .queryParam("MobileApp", "MyTourProject")
                .queryParam("_type", "json")
                .queryParam("contentId", contentId)
                .queryParam("contentTypeId", contentTypeId) // ✅ 이거 빠지면 오류!
                .build(true)
                .toUri();

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity(uri, String.class);

        System.out.println("응답결과: " + response.getBody());
        return response.getBody();
    }


    //상세보기
    @Override
    public String getDetailInfo1Codes(String contentId, String contentTypeId, int pageNo, int numOfRows) {
        URI uri = UriComponentsBuilder
                .fromHttpUrl("http://apis.data.go.kr/B551011/KorService1/detailInfo1")
                .queryParam("serviceKey", serviceKey)
                .queryParam("MobileOS", "ETC")
                .queryParam("MobileApp", "MyTourProject")
                .queryParam("_type", "json")
                .queryParam("pageNo", pageNo)
                .queryParam("numOfRows", numOfRows)
                .queryParam("contentId", contentId)
                .queryParam("contentTypeId", contentTypeId)
                .build(true)
                .toUri();

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity(uri, String.class);

        System.out.println("응답결과: " + response.getBody());
        return response.getBody();
    }

    //이미지리스트
    @Override
    public String getDetailImageList(String contentId, int pageNo, int numOfRows) {
        URI uri = UriComponentsBuilder
                .fromHttpUrl("http://apis.data.go.kr/B551011/KorService1/detailImage1")
                .queryParam("serviceKey", serviceKey)
                .queryParam("MobileOS", "ETC")
                .queryParam("MobileApp", "MyTourProject")
                .queryParam("_type", "json")
                .queryParam("contentId", contentId)
                .queryParam("imageYN", "Y")
                .queryParam("subImageYN", "Y")
                .queryParam("pageNo", pageNo)
                .queryParam("numOfRows", numOfRows)
                .build(true)
                .toUri();

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity(uri, String.class);
        System.out.println("응답결과: " + response.getBody());
        return response.getBody();
    }


    // 국문관광정보 동기화 목록 조회
    @Override
    public String getAreaBasedSyncList1Codes(
            int pageNo,
            int numOfRows,
            String arrange,        // 정렬 기준: A=제목순, B=수정일순, C=생성일순
            String showFlag        // 표시 여부: 1=표출, 0=비표출
    ) {
        URI uri = UriComponentsBuilder
                .fromHttpUrl("http://apis.data.go.kr/B551011/KorService1/areaBasedSyncList1")
                .queryParam("serviceKey", serviceKey)
                .queryParam("MobileOS", "ETC")
                .queryParam("MobileApp", "MyTourProject")
                .queryParam("_type", "json")
                .queryParam("pageNo", pageNo)
                .queryParam("numOfRows", numOfRows)
                .queryParam("arrange", arrange)
                .queryParam("showflag", showFlag)
                .build(true)
                .toUri();

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity(uri, String.class);
        System.out.println("응답결과: " + response.getBody());

        return response.getBody();
    }

    @Override
    public boolean existsFestival(String contentId) {
        return tourMapper.existsFestival(contentId);
    }

    @Override
    public TourFestivalDto selectFestivalDetail(String contentid) {
        return tourMapper.selectFestivalDetail(contentid);
    }


}
