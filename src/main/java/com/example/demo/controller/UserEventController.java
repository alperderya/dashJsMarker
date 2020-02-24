package com.example.demo.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.ClickObject;
import com.example.demo.Marker;
import com.example.demo.entitiy.Content;
import com.example.demo.entitiy.Video;
import com.example.demo.entitiy.ViewStats;
import com.example.demo.repo.ContentRepository;
import com.example.demo.repo.VideoRepository;
import com.example.demo.repo.ViewStatsRepository;

@RestController
public class UserEventController {
	
	@Value("${project.videoplayer.user.click.unique}")
	private boolean isUnique;

	@Autowired
	ViewStatsRepository viewStatsRepo;
	
	@Autowired
	VideoRepository videoRepo;
	
	@Autowired
	ContentRepository contentRepo;
	
    @GetMapping("/test")
    public String test(@RequestParam(required = false) Long id) {

    	System.out.println("SUCCESSSS");
    	return id.toString();
    }
    
    @PostMapping("/sendClick")
    public ResponseEntity<HttpStatus> sendMarkerClick(@RequestBody ClickObject myObj ) {
    	System.out.println("User Click Is Unique: " + isUnique);
    	System.out.println(myObj);
    	Content content = contentRepo.findById(myObj.getContentId()).get();
    	Video video = videoRepo.findById(myObj.getVideoPartId()).get();
    	if(isUnique) {    		
    		
    		ViewStats view = viewStatsRepo.findByUserIdAndContentAndVideo(myObj.getUserId(), content, video);
    		if(view != null) {
    			System.err.println("USER ALREADY CLICK THIS PART OF CONTENT");
    			return new ResponseEntity<>(HttpStatus.OK);
    		}
    	}
    	ViewStats view = new ViewStats();
    	view.setCreatedAt(new Date());
    	view.setContent(content);
    	view.setVideo(video);
    	view.setUserId(myObj.getUserId());
    	viewStatsRepo.save(view);
    	return new ResponseEntity<>(HttpStatus.OK);
    }
}
