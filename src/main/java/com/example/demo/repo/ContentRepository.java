package com.example.demo.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entitiy.Content;

@Repository
public interface ContentRepository  extends CrudRepository<Content, Long>{

}
