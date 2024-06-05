package company;

import com.zaxxer.hikari.HikariDataSource;
import company.response.PostCompanyReq;
import config.DataSourceConfig;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CompanyDao {
    HikariDataSource dataSourceConfig;

    CompanyDao() {
        dataSourceConfig = DataSourceConfig.getInstance();
    }


    public PostCompanyReq select(PostCompanyReq dto) {
        Connection connection = null;
        PreparedStatement pstmt = null;
        String result = "-1";
        ResultSet rs = null;
        try {
            connection = dataSourceConfig.getConnection();
            //pstmt = connection.prepareStatement("INSERT INTO web.member (id, pw, name) VALUES (?, ?, ?)");
            pstmt = connection.prepareStatement("SELECT * FROM gp.company WHERE email = (?)");
            pstmt.setString(1, dto.getEmail());

            rs = pstmt.executeQuery();

            if(rs != null) {
                while(rs.next()) {
                    if(rs.getString("ID") == "-1") {
                        return dto;
                    }else{
                        dto.setId(rs.getString("ID"));
                        if (dto.getPw() == null && rs.getString("PW") != null)
                            dto.setAddress(rs.getString("PW"));
                        if (dto.getAddress() == null && rs.getString("ADDRESS") != null)
                            dto.setAddress(rs.getString("ADDRESS"));
                        if (dto.getBizName() == null && rs.getString("BIZ_NAME") != null)
                            dto.setBizName(rs.getString("BIZ_NAME"));
                        if (dto.getTel() == null && rs.getString("TEL") != null)
                            dto.setTel(rs.getString("TEL"));
                    }
                }
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
        return dto;
    }

    public Boolean update(PostCompanyReq dto) {
        Connection connection = null;
        PreparedStatement pstmt = null;
        Integer result = null;
        try {
            if(dto.getId() == "-1")
                return false;

            connection = dataSourceConfig.getConnection();
            //pstmt = connection.prepareStatement("INSERT INTO web.member (id, pw, name) VALUES (?, ?, ?)");

            pstmt = connection.prepareStatement("UPDATE gp.company SET PW = (?), ADDRESS = (?), BIZ_NAME = (?), TEL = (?)  WHERE ID = (?)");

            pstmt.setString(1, dto.getPw());
            pstmt.setString(2, dto.getAddress());
            pstmt.setString(3, dto.getBizName());
            pstmt.setString(4, dto.getTel());
            pstmt.setString(5, dto.getId());
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
