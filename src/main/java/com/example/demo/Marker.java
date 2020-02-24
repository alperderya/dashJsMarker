package com.example.demo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Marker {

	private Long contentId;
	private Long videoPartId;
	private float    time;
	private float duration;
    private String text;
    private String backgroundColor;
    private String iconName;
}
