package com.ingsw.conectamente.api;

import com.ingsw.conectamente.dto.UploadMediaDTO;
import  com.ingsw.conectamente.service.StorageService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.swing.*;
import java.io.IOException;
import java.nio.file.Files;

@RequiredArgsConstructor
@RequestMapping("/media")
@RestController
public class MediaController {
    private final StorageService storageService;

    @PostMapping("/upload")
    public UploadMediaDTO upload(@RequestParam("file") MultipartFile multipartFile){
        String path = storageService.store(multipartFile);
        return new UploadMediaDTO(path);
    }

    @GetMapping("/{filename}")
    public ResponseEntity<Resource> getResource(@PathVariable String filename)throws IOException{
        Resource resource = storageService.loadAsResource(filename);
        String contentType = Files.probeContentType(resource.getFile().toPath());

        return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, contentType).body(resource);
    }


}
