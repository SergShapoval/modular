package com.dao.log.model;

public class Log {

    public Log(String loggerLevel, String loggerInfo) {
        this.loggerLevel = loggerLevel;
        this.loggerInfo = loggerInfo;
    }

    private int id;
    private String loggerLevel;
    private String loggerInfo;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLoggerLevel() {
        return loggerLevel;
    }

    public void setLoggerLevel(String loggerLevel) {
        this.loggerLevel = loggerLevel;
    }

    public String getLoggerInfo() {
        return loggerInfo;
    }

    public void setLoggerInfo(String loggerInfo) {
        this.loggerInfo = loggerInfo;
    }
}
