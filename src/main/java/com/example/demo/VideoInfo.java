package com.example.demo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VideoInfo
{

    private int videoId;
    private String videoUrl;
    private String videoType;

    private List<Marker> markers;
}
