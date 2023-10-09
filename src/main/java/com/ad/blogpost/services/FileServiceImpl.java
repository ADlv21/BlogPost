package com.ad.blogpost.services;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService{

    @Override
    public String uploadImage(String path, MultipartFile multipartFile) throws IOException {

        System.out.println("=================================================================");
        // File Name
        String name = multipartFile.getOriginalFilename();
        // random name generate
        String randomID = UUID.randomUUID().toString();
        String filename = randomID.concat(name.substring(name.lastIndexOf(".")));
        // Full Path
        String filePath = path + File.separator + filename;

        System.out.println(name);
        System.out.println(filename);
        System.out.println(filePath);
        System.out.println("=================================================================");

        // Create folder if not created
        File f = new File(path);
        if (!f.exists()){
            f.mkdir();
        }

        Files.copy(multipartFile.getInputStream(), Paths.get(filePath));

        return filename;
    }

    @Override
    public InputStream getImage(String path, String fileName) throws FileNotFoundException {
        String fullPath = path + File.separator + fileName;
        return new FileInputStream(fullPath);
    }
}
