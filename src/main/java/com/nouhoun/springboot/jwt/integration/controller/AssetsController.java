package com.nouhoun.springboot.jwt.integration.controller;

import com.nouhoun.springboot.jwt.integration.domain.User;
import com.nouhoun.springboot.jwt.integration.service.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.activation.FileTypeMap;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.nouhoun.springboot.jwt.integration.controller.ConsultantInformationController.UPLOADED_FOLDER;

@RestController
@RequestMapping("/assets")
public class AssetsController {
    @RequestMapping(value = "/{id:.+}", method = RequestMethod.GET)
    public ResponseEntity<byte[]> showImage(@PathVariable("id") String id) throws IOException {
        File file = new File(UPLOADED_FOLDER + id);
        return ResponseEntity.ok()
                .contentType(MediaType.valueOf(FileTypeMap.getDefaultFileTypeMap().getContentType(file)))
                .body(Files.readAllBytes(file.toPath()));
    }

    @RequestMapping(value = "/download/{id:.+}", method = RequestMethod.GET)
    public ResponseEntity<byte[]> downloadImage(@PathVariable("id") String id) throws IOException {
        File file = new File(UPLOADED_FOLDER + id);
        return ResponseEntity.ok()
                .header("Content-Disposition", "attachment; filename=" +file.getName())
                .contentType(MediaType.valueOf(FileTypeMap.getDefaultFileTypeMap().getContentType(file)))
                .body(Files.readAllBytes(file.toPath()));
    }
}
