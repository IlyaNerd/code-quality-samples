package com.itechart.error.handling;

import lombok.RequiredArgsConstructor;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@RequiredArgsConstructor
public class EmailDao {

    private static final String DELETE_QUERY = "DELETE FROM EMAIL WHERE ID = ?";
    private final DataSource dataSource;

    private Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    public boolean deleteEmailAddress(int idEmailAddress) throws DaoException {
        try(Connection connection = getConnection();
            PreparedStatement deleteStatement = connection.prepareStatement(DELETE_QUERY);) {
            deleteStatement.setInt(1, idEmailAddress);
            return deleteStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DaoException("Deleting email address failed", e);
        }
    }


}
