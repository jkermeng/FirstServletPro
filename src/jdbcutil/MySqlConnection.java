package jdbcutil;

import com.mysql.cj.MysqlConnection;
import util.DESUtile;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class MySqlConnection {
    private static String Url;
    private static String Username;
    private static String Pwd;
    private static String DriverName;

    private static Connection conn;
    private static PreparedStatement ps;
    private static ResultSet rs;

    public static void main(String[] args) {
        MySqlConnection.getConnect();
    }

    public static Connection getConnect() {
        return conn;
    }

    static {
        InputStream is;
        is = MySqlConnection.class.getResourceAsStream("jdbc.properties");
        Properties pps = new Properties();
        try {
            pps.load(is);
            DriverName = DESUtile.getDecryptString(pps.getProperty("jdbc.driver"));
            Url = DESUtile.getDecryptString(pps.getProperty("jdbc.url"));
            Username = DESUtile.getDecryptString(pps.getProperty("jdbc.name"));
            Pwd = DESUtile.getDecryptString(pps.getProperty("jdbc.password"));
            Class.forName(DriverName);
            conn = DriverManager.getConnection(Url, Username, Pwd);
            System.out.println("≥…π¶º”‘ÿ");
        } catch (IOException | ClassNotFoundException | SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void closeAll() {
        try {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void closePart() {
        try {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
