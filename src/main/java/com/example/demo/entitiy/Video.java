package com.example.demo.entitiy;

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
@SequenceGenerator(name = "idgen", sequenceName = "VIDEO_SEQ", allocationSize = 1)
@Entity
@Table(name = "VIDEO")
public class Video {

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "idgen")
	private Long id;
	
	@Column(name="VIDEO_NAME")
	private String videoName;
	
	@Column(name="START_POINT")
	private Long startPoint;
	
	@Column(name="DURATION")
	private Long duration;
	
	@Column(name="COLOR")
	private String color;
	
	@Column(name="iconName")
	private String iconName;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CONTENT_ID", nullable = false)
	private Content content;
	
	
}
