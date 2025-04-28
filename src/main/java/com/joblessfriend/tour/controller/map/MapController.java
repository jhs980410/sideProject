package com.joblessfriend.tour.controller.map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.joblessfriend.tour.dto.TourFestivalDto;
import com.joblessfriend.tour.service.tour.TourService;
import com.joblessfriend.tour.service.tour.TourServiceImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/map")
public class MapController {
    private final TourService tourService;

    public MapController(TourService tourService) {
        this.tourService = tourService;
    }

    @GetMapping("/api/naverMapClientId")
    @ResponseBody
    public String getNaverMapClientId() {
        return naverMapClientId;
    }

    @Value("${naver.map.client-id}")
    private String naverMapClientId;

    @GetMapping("/naver")
    public String mapPage(Model model) throws JsonProcessingException {
        String festValList = tourService.getFestivalList("20250422","20250722","D","Y",1,600);
        ObjectMapper objectMapper = new ObjectMapper();
        //Jackson 라이브러리의 ObjectMapper란, Java 객체를 JSON으로 또는 반대로 JSON을 Java 객체로 역직렬화하는 도구
        JsonNode root = objectMapper.readTree(festValList) ;
        JsonNode items = root.path("response").path("body").path("items").path("item");

        List<TourFestivalDto> festivalList = new ArrayList<>();
        for (JsonNode item : items) {
            TourFestivalDto festival = new TourFestivalDto();
            festival.setAddr1(item.path("addr1").asText());
            festival.setMapX(item.path("mapx").asDouble());
            festival.setMapY(item.path("mapy").asDouble());
            festival.setContentId(item.path("contentid").asText());
            festival.setTitle(item.path("title").asText());
            festival.setEventEndDate(item.path("eventenddate").asText());
            festival.setEventStartDate(item.path("eventstartdate").asText());
            festival.setTel(item.path("tel").asText());
            festival.setFirstImage(item.path("firstimage").asText());
            festival.setAreaCode(item.path("areacode").asText());
            festivalList.add(festival);
        } // Jackson으로 JSON 파싱

        for (TourFestivalDto festival : festivalList) {
            if (!tourService.existsFestival(festival.getContentId())) {
                tourService.insertFestival(festival);
            }
        }

        model.addAttribute("clientId", naverMapClientId);
        model.addAttribute("festivalList", festivalList);

        return "map/mapMain";
    }
}
