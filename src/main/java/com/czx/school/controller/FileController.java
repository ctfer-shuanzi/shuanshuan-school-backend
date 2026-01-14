package com.czx.school.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/file")
public class FileController {
    @PostMapping(value = "/upload")
    public String upload(@RequestParam("file") MultipartFile file) throws IOException {
        File path = new File("D://upload/");
        if(!path.exists()){
            path.mkdir();
        }
        String fileName = file.getOriginalFilename();
        File f = new File("D://upload/"+fileName);
        System.out.println("path's path: "+fileName);
        System.out.println("f's path: "+f.getAbsolutePath());

        file.transferTo(f);
        return "success";
    }
}
