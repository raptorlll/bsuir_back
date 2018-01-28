package com.nouhoun.springboot.jwt.integration.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nouhoun.springboot.jwt.integration.domain.ConsultantGroupUser;
import com.nouhoun.springboot.jwt.integration.domain.ConsultantInformation;
import com.nouhoun.springboot.jwt.integration.service.ConsultantGroupUserService;
import com.nouhoun.springboot.jwt.integration.service.ConsultantInformationService;
import com.nouhoun.springboot.jwt.integration.service.CrudServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationHome;
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
    private static String UPLOADED_FOLDER = "assets//uploads//";
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
//        @RequestParam("data") ConsultantInformation information,
            @RequestParam("data") String informationString,
            @RequestParam("file") MultipartFile file
    ) {
        ConsultantInformation consultantInformation = null;

        ApplicationHome home = new ApplicationHome(this.getClass());


        ObjectMapper objectMapper = new ObjectMapper();

        try {
            consultantInformation = jacksonObjectMapper.readValue(informationString, ConsultantInformation.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("!!");
        if (file.isEmpty()) {
            System.out.println("Empty file");
        }

        try {

            // Get the file and save it somewhere
            byte[] bytes = file.getBytes();
            Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
            Files.write(path, bytes);

//            redirectAttributes.addFlashAttribute("message",
//                    "You successfully uploaded '" + file.getOriginalFilename() + "'");
            if (consultantInformation != null) {
                System.out.println("Logg");
                consultantInformation.setLicenseFile(file.getOriginalFilename());
                service.save(consultantInformation);
            }

            System.out.println("Log");
        } catch (IOException e) {
            e.printStackTrace();
        }
//        try {
//            getService().save(information);
//        }catch (Exception e){
//            System.out.println("e");
//        }

        return consultantInformation;
//        return  information;
    }
}
