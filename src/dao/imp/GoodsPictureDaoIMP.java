package dao.imp;

import jdbcutil.MySqlConnection;
import onetomanyentity.Goods;
import onetomanyentity.GoodsPicture;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class GoodsPictureDaoIMP {
    private Connection conn;
    private PreparedStatement ps;


    public Set<GoodsPicture> selectall() {
        Set<GoodsPicture> gpall = new HashSet<>();
        conn = MySqlConnection.getConnect();
        try {
            ps = conn.prepareStatement("select * from goodspicture");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int pid = rs.getInt("pid");
                String pname = rs.getString("pname");
                String purl = rs.getString("purl");
                int goods_gid = rs.getInt("goods_gid");
                GoodsPicture gp = new GoodsPicture(pid, pname, purl);
                gp.setGoods_gid(new Goods(goods_gid));
                gpall.add(gp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            MySqlConnection.closePart();
        }
        return gpall;
    }

    public GoodsPicture selectById(int gpid) {
        Set<GoodsPicture> selectall = selectall();
        GoodsPicture gp = new GoodsPicture();
        for (GoodsPicture gps : selectall) {
            if (gps.getPid() == gpid) {
                gp = gps;
            }
        }
        return gp;
    }

    public GoodsPicture selectByGId(int gip) {
        Set<GoodsPicture> selectall = selectall();
        GoodsPicture gp = new GoodsPicture();
        for (GoodsPicture gps : selectall) {
            if (gps.getGoods_gid().getGid() == gip) {
                gp = gps;
            }
        }
        return gp;

    }
}
