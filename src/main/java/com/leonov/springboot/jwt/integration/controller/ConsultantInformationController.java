package com.leonov.springboot.jwt.integration.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.leonov.springboot.jwt.integration.domain.ConsultantInformation;
import com.leonov.springboot.jwt.integration.service.ConsultantInformationService;
import com.leonov.springboot.jwt.integration.service.CrudServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/consultant_information")
public class ConsultantInformationController extends CrudAbstract<ConsultantInformation, Long> {
    //Save the uploaded file to this folder
    public static String UPLOADED_FOLDER = "assets//uploads//";
    @Autowired
    private ObjectMapper jacksonObjectMapper;

    @Autowired
    private ConsultantInformationService service;

    @Override
    public CrudServiceInterface<ConsultantInformation, Long> getService() {
        return service;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ConsultantInformation saveClientInformationWithFile(
            @RequestParam("data") String informationString,
            @RequestParam(value = "file",  required = false) MultipartFile file
    ) {
        ConsultantInformation consultantInformation = null;

        try {
            consultantInformation = jacksonObjectMapper.readValue(informationString, ConsultantInformation.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (file==null || file.isEmpty()) {
            service.save(consultantInformation);
            return consultantInformation;
        }

        try {
            // Get the file and save it somewhere
            byte[] bytes = file.getBytes();
            Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
            Files.write(path, bytes);

            if (consultantInformation != null) {
                System.out.println("Logg");
                consultantInformation.setLicenseFile(file.getOriginalFilename());
                service.save(consultantInformation);
            }

            System.out.println("Log");
        } catch (IOException e) {
            e.printStackTrace();
        }

        return consultantInformation;
    }
}
