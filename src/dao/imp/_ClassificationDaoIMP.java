package dao.imp;

import jdbcutil.MySqlConnection;
import onetomanyentity._Classification;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class _ClassificationDaoIMP {
    private Connection conn;
    private PreparedStatement ps;

    public Set<_Classification> selectall() {
        Set<_Classification> setclassfies = new HashSet<>();
        conn = MySqlConnection.getConnect();
        try {
            ps = conn.prepareStatement("select * from classification");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int cid = rs.getInt("cid");
                String cname = rs.getString("cname");
                _Classification c = new _Classification(cid, cname);
                setclassfies.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            MySqlConnection.closePart();
        }
        return setclassfies;
    }

    public _Classification selectById(int cfid) {
        Set<_Classification> selectall = selectall();
        _Classification cf = new _Classification();
        for (_Classification cfs : selectall) {
            if (cfs.getCid() == cfid) {
                cf = cfs;
            }
        }
        return cf;
    }
}
