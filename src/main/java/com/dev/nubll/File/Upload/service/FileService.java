package com.dev.nubll.File.Upload.service;

import com.dev.nubll.File.Upload.model.FileModel;
import com.dev.nubll.File.Upload.repository.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.util.stream.Stream;

@Service
public class FileService {
    private static Path filePath;

    @Autowired
    private FileRepository fileRepository;

    public FileModel saveFile(MultipartFile multipartFile) throws IOException {
        String filename = multipartFile.getOriginalFilename();
        FileModel fileModel = new FileModel(filename,multipartFile.getContentType(),multipartFile.getBytes());
        return fileRepository.save(fileModel);
    }

    public FileModel findById(String id){
        return fileRepository.findById(id).get();
    }

    public Stream<FileModel> getAllFiles(){
        return fileRepository.findAll().stream();
    }
}
