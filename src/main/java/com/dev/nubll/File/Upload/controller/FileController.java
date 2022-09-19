package com.dev.nubll.File.Upload.controller;


import com.dev.nubll.File.Upload.model.FileModel;
import com.dev.nubll.File.Upload.model.FileResponseModel;
import com.dev.nubll.File.Upload.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class FileController {

    @Autowired
    private FileService fileService;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam MultipartFile file) throws IOException {
        try{
            fileService.saveFile(file);
            return new ResponseEntity<String>("File uploaded successfully : " + file.getOriginalFilename(),HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("Could not upload the file",HttpStatus.EXPECTATION_FAILED);
        }
    }

    @GetMapping("/files/{id}")
    public ResponseEntity<byte[]> getFile(@PathVariable String id){
        FileModel fileModel = fileService.findById(id);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename=\""+ fileModel.getName() + "\"")
                .body(fileModel.getData());
    }

    @GetMapping("/files")
    public ResponseEntity<List<FileResponseModel>> getAllfiles() {
        List<FileResponseModel> files = fileService.getAllFiles().map(file -> {
            String fileDownloadUri = ServletUriComponentsBuilder
                    .fromCurrentContextPath()
                    .path("/files/")
                    .path(file.getId())
                    .toUriString();
            return new FileResponseModel(file.getName(), fileDownloadUri, file.getType(), file.getData().length);
        }).collect(Collectors.toList());
        return new ResponseEntity<>(files, HttpStatus.OK);
    }
}

