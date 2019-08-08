package com.itechart.error.handling;

import org.h2.util.IOUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileService {

    public void copyResource(MultipartFile file, String dest) {
        try {
            File destFile = new File(dest);
            FileOutputStream out = new FileOutputStream(destFile);
            IOUtils.copy(file.getInputStream(), out);
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
