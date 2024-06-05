package User;

import User.request.PostUserLoginReq;
import com.zaxxer.hikari.HikariDataSource;
import config.DataSourceConfig;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {
    HikariDataSource dataSourceConfig;

    UserDao() {
        dataSourceConfig = DataSourceConfig.getInstance();
    }


    public Boolean login(PostUserLoginReq dto) {
        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet result = null;
        try {
            connection = dataSourceConfig.getConnection();
            pstmt = connection.prepareStatement("SELECT * FROM USER WHERE EMAIL = ? AND PW = ?;");
            pstmt.setString(1, dto.getEmail());
            pstmt.setString(2, dto.getPw());
            //  pstmt.setString(3, dto.getName());
            result = pstmt.executeQuery();
            if(result != null) {
                while (result.next()) {
                    return true;
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException sqlex) {
                }
                pstmt = null;
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException sqlex) {
                }
            }
        }
        return false;
    }
}


