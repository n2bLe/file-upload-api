package com.dev.nubll.File.Upload.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FileResponseModel {

    private String name;
    private String url;
    private String type;
    private long size;
}
