package member;

import com.zaxxer.hikari.HikariDataSource;
import config.DataSourceConfig;
import member.request.PostMemberReq;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class MemberDao {
    HikariDataSource dataSourceConfig;

    MemberDao() {
        dataSourceConfig = DataSourceConfig.getInstance();
    }

    public Boolean create(PostMemberReq dto) {
        Connection connection = null;
        PreparedStatement pstmt = null;
        Integer result = null;
        try {
            connection = dataSourceConfig.getConnection();
            pstmt = connection.prepareStatement("INSERT INTO web.member (id, pw, name) VALUES (?, ?, ?)");
            pstmt.setString(1, dto.getId());
            pstmt.setString(2, dto.getPw());
            pstmt.setString(3, dto.getName());
            result = pstmt.executeUpdate();

            if (result > 0) {
                return true;
            }
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
