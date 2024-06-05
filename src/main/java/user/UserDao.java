package user;

import com.zaxxer.hikari.HikariDataSource;
import config.DataSourceConfig;
import user.request.PostUserEditReq;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserDao {
    HikariDataSource dataSourceConfig;

    UserDao() {
        dataSourceConfig = DataSourceConfig.getInstance();
    }

    public Boolean edit(PostUserEditReq dto) {
        Connection connection = null;
        PreparedStatement pstmt = null;
        Integer result = null;

        try{
            connection = dataSourceConfig.getConnection();
            pstmt = connection.prepareStatement("UPDATE USER\n" +
                            "SET PW = ?, NICKNAME = ?, BIRTHDAY = ?, MODIFIED_DATE = NOW()\n" +
                            "WHERE EMAIL = ?;");
            pstmt.setString(1, dto.getPw());
            pstmt.setString(2, dto.getNickname());
            pstmt.setString(3, dto.getBirthday());
            pstmt.setString(4, dto.getEmail());
            result = pstmt.executeUpdate();

            if (result > 0) { return true; }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException sqlEx) { } // ignore
                pstmt = null;
            }

            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException sqlEx) { } // ignore
                connection = null;
            }
        }
        return false;
    }
}
