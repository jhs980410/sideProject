package com.joblessfriend.tour.controller.tour;

import com.joblessfriend.tour.dto.TourFestivalDto;
import com.joblessfriend.tour.service.tour.TourServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/api/tour")
public class TourApiController {

    private final TourServiceImpl tourService;
    public TourApiController(TourServiceImpl tourService) {
        this.tourService = tourService;
    }

    @ModelAttribute("areaJson")
    public String areaJson() {
        return tourService.getAreaCodes();
    }
    @GetMapping("/main")
    public String getAreas(Model model) {
        String festival = tourService.getFestivalList("20250425","20250524","D","Y",1,10);

        model.addAttribute("festivalJson", festival);

        System.out.println(festival);
        return "index"; // resources/templates/index.html

    }
    //관광지 단일 상세페이지
    @GetMapping("/detailView")
    public String getFestivalDetail(@RequestParam String contentId, Model model) {
        TourFestivalDto dto = tourService.selectFestivalDetail(contentId);
        model.addAttribute("festival", dto); // 객체 그대로 넘김
        System.out.println(dto.toString());
        return "tour/tourDetailView";
    }
    //지역별 리스트
    @GetMapping("/areaList")
    public String getAreaList(@RequestParam String areaCode, Model model) {
        String areafestivalList = tourService.getAreaBasedList(areaCode,10,"1","A",1);

        model.addAttribute("areafestivalList", areafestivalList);
        return "tour/tourAreaList";
    }


}
