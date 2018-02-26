package com.leonov.springboot.jwt.integration.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.activation.FileTypeMap;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@RestController
@RequestMapping("/assets")
public class AssetsController {
    @RequestMapping(value = "/{id:.+}", method = RequestMethod.GET)
    public ResponseEntity<byte[]> showImage(@PathVariable("id") String id) throws IOException {
        File file = new File(ConsultantInformationController.UPLOADED_FOLDER + id);
        return ResponseEntity.ok()
                .contentType(MediaType.valueOf(FileTypeMap.getDefaultFileTypeMap().getContentType(file)))
                .body(Files.readAllBytes(file.toPath()));
    }

    @RequestMapping(value = "/download/{id:.+}", method = RequestMethod.GET)
    public ResponseEntity<byte[]> downloadImage(@PathVariable("id") String id) throws IOException {
        File file = new File(ConsultantInformationController.UPLOADED_FOLDER + id);
        return ResponseEntity.ok()
                .header("Content-Disposition", "attachment; filename=" +file.getName())
                .contentType(MediaType.valueOf(FileTypeMap.getDefaultFileTypeMap().getContentType(file)))
                .body(Files.readAllBytes(file.toPath()));
    }
}
