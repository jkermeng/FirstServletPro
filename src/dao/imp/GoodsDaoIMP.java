package dao.imp;

import idao.IDao;
import jdbcutil.IPreparedStatement;
import jdbcutil.IResultSet;
import jdbcutil.MySqlConnection;
import jdbcutil.MySqlUtil;
import onetomanyentity.Goods;
import onetomanyentity.GoodsPicture;
import onetomanyentity._Classification;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class GoodsDaoIMP implements IDao<Goods> {
    private Connection conn;
    private PreparedStatement ps;
    private Connection cnn = MySqlConnection.getConnect();

    public Set<Integer> selectGoodsGid() {
        Set<Integer> sg = new LinkedHashSet<>();
        try {
            new MySqlUtil(cnn).QueryNoParam("select gid from goods", new IResultSet() {
                @Override
                public void setIResultSet(ResultSet rs) throws SQLException {
                    while (rs.next()) {
                        int gid = rs.getInt("gid");
                        sg.add(gid);
                    }
                }
            });
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            MySqlConnection.closePart();
        }
        return sg;
    }

    @Override
    public Set<Goods> select(Goods t) {
        conn = MySqlConnection.getConnect();
        Set<Goods> setgoods = new HashSet<>();
        if (t == null) {
            String sql = "select * from goods";
            ResultSet rs = null;
            try {
                ps = conn.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    int gid = rs.getInt("gid");
                    int cid = rs.getInt("classification_cid");
                    Goods g = new Goods(gid);
                    g.setSetClassfy(new _Classification(cid));
                    setgoods.add(g);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                MySqlConnection.closePart();
            }

            MySqlConnection.closePart();
        }

        return setgoods;
    }

    public List<Goods> selectAllGid() {
        List<Goods> lg = new ArrayList<>();
        try {
            new MySqlUtil(cnn).QueryNoParam("select gid from goods", new IResultSet() {
                @Override
                public void setIResultSet(ResultSet rs) throws SQLException {
                    while (rs.next()) {
                        Goods goods = new Goods(rs.getInt("gid"));
                        lg.add(goods);
                    }
                }
            });
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            MySqlConnection.closePart();
        }
        return lg;
    }

    public static void main(String[] args) {
        Set<Goods> gs = new GoodsDaoIMP().selectByGname("Ив");
        System.out.println(gs);
    }

    public Set<Goods> selectByGname(String gname) {
        Set<Goods> sg = new LinkedHashSet<>();
        try {
            new MySqlUtil(cnn).QueryWithParam("SELECT * FROM goods WHERE gname LIKE \"%\"?\"%\"", new IPreparedStatement() {
                @Override
                public void setPreparedStatement(PreparedStatement ps) throws SQLException {
                    ps.setString(1, gname);
                }
            }, new IResultSet() {
                @Override
                public void setIResultSet(ResultSet rs) throws SQLException {
                    while (rs.next()) {
                        int gid = rs.getInt("gid");
                        String gname1 = rs.getString("gname");
                        double gprice = rs.getDouble("gprice");
                        int gstock = rs.getInt("gstock");
                        int classification_cid = rs.getInt("classification_cid");
                        Goods goods = new Goods(gid, gname1, gprice, gstock, new _Classification(classification_cid));
                        sg.add(goods);
                    }
                }
            });
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            MySqlConnection.closePart();
        }
        return sg;
    }

    @Override
    public Goods selectById(int id) {
        Goods g1 = new Goods();
        MySqlUtil mySqlUtil = new MySqlUtil(cnn);
        try {
            mySqlUtil.QueryWithParam("select * from goods where gid = ?", new IPreparedStatement() {
                @Override
                public void setPreparedStatement(PreparedStatement ps) throws SQLException {
                    ps.setInt(1, id);
                }
            }, new IResultSet() {
                @Override
                public void setIResultSet(ResultSet rs) throws SQLException {
                    while (rs.next()) {
                        String gname = rs.getString("gname");
                        double gprice = rs.getDouble("gprice");
                        int gid = rs.getInt("gid");
                        int gstock = rs.getInt("gstock");
                        int cid = rs.getInt("classification_cid");
                        g1.setGid(gid);
                        g1.setGname(gname);
                        g1.setGprice(gprice);
                        g1.setGstock(gstock);
                        g1.setSetClassfy(new _Classification(cid));

                    }
                }
            });
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            MySqlConnection.closePart();
        }
        return g1;
    }

    public List<Goods> selectByClassficationId(int classid) {
        List<Goods> setg = new ArrayList<>();
        MySqlUtil mySqlUtil = new MySqlUtil(cnn);
        try {
            mySqlUtil.QueryWithParam("select * from goods where classification_cid = ?", new IPreparedStatement() {
                @Override
                public void setPreparedStatement(PreparedStatement ps) throws SQLException {
                    ps.setInt(1, classid);
                }
            }, new IResultSet() {
                @Override
                public void setIResultSet(ResultSet rs) throws SQLException {
                    while (rs.next()) {
                        String gname = rs.getString("gname");
                        double gprice = rs.getDouble("gprice");
                        int gid = rs.getInt("gid");
                        int gstock = rs.getInt("gstock");
                        int cid = rs.getInt("classification_cid");
                        Goods goods = new Goods(gid, gname, gprice, gstock, new _Classification(cid));
                        setg.add(goods);
                    }
                }
            });
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            MySqlConnection.closePart();
        }
        return setg;
    }

    @Override
    public void isExist() {

    }

    @Override
    public void insert(Goods t) throws SQLException {
        MySqlUtil mySqlUtil = new MySqlUtil(conn);
        mySqlUtil.UpdateOrInsert("INSERT INTO goods (gname,gprice,gstock,classification_cid)VALUE(?,?,?,?)",
                new IPreparedStatement() {
                    @Override
                    public void setPreparedStatement(PreparedStatement ps) throws SQLException {
                        ps.setString(1, t.getGname());
                        ps.setDouble(2, t.getGprice());
                        ps.setInt(3, t.getGstock());
                        ps.setInt(4, t.getSetClassfy().getCid());
                    }
                });
    }

    @Override
    public void update(Goods t) throws SQLException {
        MySqlUtil mySqlUtil = new MySqlUtil(conn);
        mySqlUtil.UpdateOrInsert(
                "UPDATE goods SET gname = ?,gprice = ?,gstock = ?,classification_cid = ? WHERE gid = ?",
                new IPreparedStatement() {
                    @Override
                    public void setPreparedStatement(PreparedStatement ps) throws SQLException {
                        ps.setString(1, t.getGname());
                        ps.setDouble(2, t.getGprice());
                        ps.setInt(3, t.getGstock());
                        ps.setInt(4, t.getSetClassfy().getCid());
                        ps.setInt(5, t.getGid());
                    }
                });
    }

    @Override
    public void delete(Goods t) throws SQLException {
        MySqlUtil mySqlUtil = new MySqlUtil(conn);
        mySqlUtil.UpdateOrInsert("DELETE FROM goods WHERE gid = ?", new IPreparedStatement() {

            @Override
            public void setPreparedStatement(PreparedStatement ps) throws SQLException {
                ps.setInt(1, t.getGid());

            }
        });

    }

}
