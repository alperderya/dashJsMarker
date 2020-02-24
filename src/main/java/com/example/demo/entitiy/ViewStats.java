package com.example.demo.entitiy;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
@SequenceGenerator(name = "idgen", sequenceName = "VIEW_SEQ", allocationSize = 1)
@Entity
@Table(name = "VIEW_STATS")
public class ViewStats {

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "idgen")
	private Long id;
	
	@Column(name = "CREATED_AT")
	private Date createdAt;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "VIDEO_ID", nullable = false)
	private Video video;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CONTENT_ID", nullable = false)
	private Content content;	
	
	@Column(name = "USER_ID")
	private String userId; 
}