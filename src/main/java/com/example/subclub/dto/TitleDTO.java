package com.example.subclub.dto;

import com.example.subclub.entity.MediaType;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class TitleDTO {
    private String title;
    private String description;
    private String mediaType;
}
