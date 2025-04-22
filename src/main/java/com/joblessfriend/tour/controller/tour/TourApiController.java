package com.joblessfriend.tour.controller.tour;

import com.joblessfriend.tour.service.tour.TourServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/tour")
public class TourApiController {

    private final TourServiceImpl tourService;
    public TourApiController(TourServiceImpl tourService) {
        this.tourService = tourService;
    }
    @GetMapping("/main")
    public String getAreas(Model model) {
        String result = tourService.getAreaCodes();
        String festival = tourService.getFestivalList("20250401","20250430","D","Y",1,10);
        model.addAttribute("areaJson", result);
        model.addAttribute("festivalJson", festival);

        System.out.println(festival);
        return "index"; // resources/templates/index.html

    }



}
