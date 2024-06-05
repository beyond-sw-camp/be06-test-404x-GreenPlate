package board;

import board.response.GetBoardRes;
import com.zaxxer.hikari.HikariDataSource;
import config.DataSourceConfig;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BoardDao {
    HikariDataSource dataSourceConfig;

    BoardDao() {
        dataSourceConfig = DataSourceConfig.getInstance();
    }

    public List<GetBoardRes> findAll() {
        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            connection = dataSourceConfig.getConnection();
            pstmt = connection.prepareStatement("SELECT * FROM board");

            rs = pstmt.executeQuery();
            List<GetBoardRes> result = new ArrayList<>();

            while (rs.next()) {
                GetBoardRes getBoardRes = new GetBoardRes(rs.getInt("idx"), rs.getString("title"), rs.getString("contents"));
                result.add(getBoardRes);
            }


            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException sqlEx) {
                } // ignore
                pstmt = null;
            }

            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException sqlEx) {
                } // ignore
                connection = null;
            }

            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException sqlEx) {
                } // ignore
                rs = null;
            }
        }
    }


    public GetBoardRes find(Integer idx) {
        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            connection = dataSourceConfig.getConnection();
            pstmt = connection.prepareStatement("SELECT * FROM board WHERE idx=?");
            pstmt.setInt(1, idx);
            rs = pstmt.executeQuery();
            GetBoardRes getBoardRes = null;
            if (rs.next()) {
                getBoardRes = new GetBoardRes(rs.getInt("idx"), rs.getString("title"), rs.getString("contents"));
            }

            return getBoardRes;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException sqlEx) {
                } // ignore
                pstmt = null;
            }

            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException sqlEx) {
                } // ignore
                connection = null;
            }

            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException sqlEx) {
                } // ignore
                rs = null;
            }
        }
    }
}
