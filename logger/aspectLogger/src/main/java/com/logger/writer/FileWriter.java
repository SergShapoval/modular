package com.logger.writer;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

@Component("fileWriter")
public class FileWriter implements Writer {

    @Value("${writer.filePath}")
    private String filePath;


    @Override
    public void write(String text) {

        if (!StringUtils.isEmpty(filePath)) {
            try {
                File file = new File(filePath);
                if (!file.exists()) {
                    file.createNewFile();
                }
                Files.write(Paths.get(filePath), text.getBytes(), StandardOpenOption.APPEND);
                System.out.println("written to logs");
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        }
    }
}
