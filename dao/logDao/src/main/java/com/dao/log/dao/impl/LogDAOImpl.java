package com.dao.log.dao.impl;

import com.dao.log.dao.DatabaseUtil;
import com.dao.log.dao.LogDAO;
import com.dao.log.model.Log;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Objects;

public class LogDAOImpl implements LogDAO {

    @Override
    public void insert(Log log) {
        String sql = "INSERT INTO log(logger_level, logger_info) VALUES (?, ?)";
        Connection connection = DatabaseUtil.getConnection();
        if (Objects.nonNull(connection)) {
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, log.getLoggerLevel());
                preparedStatement.setString(2, log.getLoggerInfo());
                preparedStatement.execute();
                connection.close();
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }
    }
}
