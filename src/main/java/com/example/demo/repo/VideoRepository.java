package com.example.demo.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entitiy.Video;

@Repository
public interface VideoRepository  extends JpaRepository<Video, Long>{

	List<Video> findAllById(Long id);
	
	@Query(value = "SELECT video FROM Video video ORDER BY startPoint ASC")
	List<Video> findAllOrderByDuration();
	
}
