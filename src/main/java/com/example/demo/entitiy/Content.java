package com.example.demo.entitiy;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
@SequenceGenerator(name = "idgen", sequenceName = "CONTENT_SEQ", allocationSize = 1)
@Entity
@Table(name = "CONTENT")
public class Content {

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "idgen")
	private Long id;
	
	@Column(name="CONTENT_NAME")
	private String contentName;
	
	@Column(name="PART_COUNT")
	private Long partCount;
	
}
