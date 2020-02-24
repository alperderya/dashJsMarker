package com.example.demo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.entitiy.Video;
import com.example.demo.repo.VideoRepository;

@RestController
public class DashJsController {

	@Autowired
	VideoRepository videoRepo;
	
    @GetMapping("/dashJs")
    public ModelAndView dashJs() {

       return new ModelAndView("dashJs");    
    }
    
    @GetMapping("/markersForDashJs")
    public List<Marker> markers() {
    	List<Marker> markerList = new ArrayList<>();
        List<Video> videoList = videoRepo.findAllOrderByDuration();
        for(Video v:videoList) {
        	Marker marker = new Marker(1L, v.getId(), v.getStartPoint(), v.getDuration(), v.getVideoName(), v.getColor(), v.getIconName());
        	markerList.add(marker);
        }
        return markerList;
    }

}
