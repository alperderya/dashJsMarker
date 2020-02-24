package com.example.demo.repo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entitiy.Content;
import com.example.demo.entitiy.Video;
import com.example.demo.entitiy.ViewStats;

@Repository
public interface ViewStatsRepository  extends CrudRepository<ViewStats, Long>{

	List<ViewStats> findAllById(Long id);
	
	List<ViewStats> findAllByContent(Content content);
	
	ViewStats findByUserIdAndContentAndVideo(String userId, Content content, Video video);

}
