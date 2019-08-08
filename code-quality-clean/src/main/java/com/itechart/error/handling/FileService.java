package com.itechart.error.handling;

import org.h2.util.IOUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

public class FileService {

    public void copyResource(MultipartFile file, String dest) {
            File destFile = new File(dest);
        try (FileOutputStream out = new FileOutputStream(destFile);
             InputStream inputStream = file.getInputStream();){
            IOUtils.copy(inputStream, out);
            out.flush();
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

}
