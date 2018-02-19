package com.leonov.springboot.jwt.integration.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.leonov.springboot.jwt.integration.domain.ConsultantGroupUser;
import com.leonov.springboot.jwt.integration.domain.ConsultantInformation;
import com.leonov.springboot.jwt.integration.domain.User;
import com.leonov.springboot.jwt.integration.service.ConsultantInformationService;
import com.leonov.springboot.jwt.integration.service.CrudServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/consultant_information")
public class ConsultantInformationController extends CrudAbstractAuthUser<ConsultantInformation, Long> {
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

    @Override
    @PreAuthorize("isAuthenticated()")
    public Collection<ConsultantInformation> getItems() {
        User userCurrent = getCurrentUser();
        boolean isConsultant = isConsultant();

        return super.getItems()
                .stream()
                .filter(consultantInformation -> {
                    if (!isConsultant) {
                        return true;
                    }

                    if(consultantInformation.getConsultantGroupUser().getUser().getId().equals(userCurrent.getId())){
                        return true;
                    }

                    return false;
                })
                .collect(Collectors.toSet());
    }
}
