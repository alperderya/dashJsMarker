package com.example.demo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.entitiy.Content;
import com.example.demo.entitiy.Video;
import com.example.demo.entitiy.ViewStats;
import com.example.demo.repo.ContentRepository;
import com.example.demo.repo.VideoRepository;
import com.example.demo.repo.ViewStatsRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class IndexController {

	@Autowired
	ViewStatsRepository repo;
	
	@Autowired
	VideoRepository videoRepo;
	
	@Autowired
	ContentRepository contentRepo;
	

    
    @PostMapping("/uploadFile")
    public  ModelAndView uploadFile(@RequestParam("file")MultipartFile file) throws IOException
    {
    	VideoInfo info;
        ObjectMapper objectMapper = new ObjectMapper();
        info = objectMapper.readValue(file.getInputStream(),VideoInfo.class);
        return new ModelAndView("redirect:/");
    }
    @GetMapping("/")
    public ModelAndView index() {

    	
    	VideoInfo info = null;

    	//info = createInfo();
    	info = getStats();

       return new ModelAndView("index")
        .addObject("videoId", info.getVideoId())
        .addObject("videoUrl", info.getVideoUrl())
        .addObject("videoType",info.getVideoType())
        .addObject("markers",info.getMarkers())
        ;       
    }

    private VideoInfo getStats() {
    	
    	
    	Map<Long, String> colorMap = new HashMap<>();
    	colorMap.put(1L, "green");
    	colorMap.put(2L, "yellow");
    	colorMap.put(3L, "orange");
    	colorMap.put(4L, "red");
    	
    	VideoInfo info = null;
        info = new VideoInfo();
        info.setVideoType("video/mp4");
        info.setVideoUrl("RealJuve13.mp4"); //374 seconds
        info.setVideoId(1); 
        
        Content content = contentRepo.findById(1L).get();
        
    	List<ViewStats> statsList = repo.findAllByContent(content);
    	Map<Long, Long> clickMap = new HashMap<>();
    	for(ViewStats stats:statsList) {
    		if(clickMap.get(stats.getVideo().getId()) == null){
    			clickMap.put(stats.getVideo().getId(), 1L);
    		}else {
    			clickMap.put(stats.getVideo().getId(), clickMap.get(stats.getVideo().getId())+1L);
    		}
    	}    	
        
    	System.out.println("Unsorted Map : " + clickMap);
    	 
    	//LinkedHashMap preserve the ordering of elements in which they are inserted
    	LinkedHashMap<Long, Long> reverseSortedMap = new LinkedHashMap<>();
    	 
    	//Use Comparator.reverseOrder() for reverse ordering
    	clickMap.entrySet()
    	    .stream()
    	    .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())) 
    	    .forEachOrdered(x -> reverseSortedMap.put(x.getKey(), x.getValue()));
    	 
    	System.out.println("Reverse Sorted Map   : " + reverseSortedMap);
    	
        ArrayList<Marker> markers = new ArrayList<>();
        
        Long index = 0L;
        for(Long key : reverseSortedMap.keySet()) {
        	index++;
        	System.out.println("Keys:" + key );
        	Video video = videoRepo.findById(key).get();
        	markers.add(new Marker(video.getContent().getId(), video.getId(), video.getStartPoint(), video.getDuration(), video.getVideoName(), colorMap.get(index), null));
        }
        
        
        Collections.sort(markers, new Comparator<Marker>(){

        	  public int compare(Marker o1, Marker o2)
        	  {
        	     return new Float(o1.getTime()).compareTo(new Float(o2.getTime()));
        	  }
        	});

        info.setMarkers(markers);
    	
    	return info;
		
	}
	//markers?id=1928832
    @GetMapping("/markers")
    public List<Marker> markers(@RequestParam(required = false) Long id) {
    	VideoInfo info = null;
        info = getStats();
        return info.getMarkers();
    }
}
