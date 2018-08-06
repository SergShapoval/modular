package com.logger.dto;

import java.util.Date;

public class LogDTO {
    private Date date;
    private Object arguments;
    private String layer;
    private String io;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Object getArguments() {
        return arguments;
    }

    public void setArguments(Object arguments) {
        this.arguments = arguments;
    }

    public String getLayer() {
        return layer;
    }

    public void setLayer(String layer) {
        this.layer = layer;
    }

    public String getIo() {
        return io;
    }

    public void setIo(String io) {
        this.io = io;
    }
}
