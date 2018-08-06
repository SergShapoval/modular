package com.logger.writer;

import org.springframework.stereotype.Component;

@Component("consoleWriter")
public class ConsoleWriter implements Writer {

    @Override
    public void write(String text) {
        System.out.println(text);
    }
}