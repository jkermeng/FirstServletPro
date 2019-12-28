package dao.imp;

import jdbcutil.IPreparedStatement;
import jdbcutil.IResultSet;
import jdbcutil.MySqlConnection;
import jdbcutil.MySqlUtil;
import onetomanyentity.Goods;
import onetomanyentity.ShopCart;
import onetooneentity.Users;
import util.DateUtil;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class ShopCartDaoIMP {
    private Connection conn;
    private PreparedStatement ps;
    private Connection cnn = MySqlConnection.getConnect();

    public Set<ShopCart> selectGoodsByUsersId(int usersid) throws SQLException {
        conn = MySqlConnection.getConnect();
        Set<ShopCart> setgoods = new HashSet<>();
        Set<Goods> setg = new HashSet<>();
        String sql = "SELECT g.gid, g.gname,g.gprice,g.classification_cid ,r4.gtime , r4.gnumber FROM goods g LEFT JOIN relation_4 r4 ON g.gid = r4.gid LEFT JOIN shopcart st ON st.cid = r4.cid WHERE st.user_uid = ?";
        ps = conn.prepareStatement(sql);
        ps.setInt(1, usersid);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int gnumber = rs.getInt("gnumber");
            Date date = rs.getDate("gtime");
            String gtime = DateUtil.dateToStr(date, "YYYY-MM-dd");
            String gname = rs.getString("gname");
            double gprice = rs.getDouble("gprice");
            int gid = rs.getInt("gid");
            Goods g = new Goods(gid, gname, gprice);
            ShopCart sc = new ShopCart(gtime, gnumber);
            sc.setG(g);
            setgoods.add(sc);
        }
        return setgoods;
    }

    public Set<ShopCart> selectShopCarst() {
        Set<ShopCart> setgoods = new HashSet<>();
        try {
            new MySqlUtil(cnn).QueryNoParam("select cid,gid from relation_4", new IResultSet() {
                @Override
                public void setIResultSet(ResultSet rs) throws SQLException {
                    while (rs.next()) {
                        ShopCart shopCart = new ShopCart();
                        shopCart.setCid(rs.getInt("cid"));
                        shopCart.setG(new Goods(rs.getInt("gid")));
                        setgoods.add(shopCart);
                    }
                }
            });
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            MySqlConnection.closePart();
        }
        return setgoods;
    }

    public static void main(String[] args) {
        new ShopCartDaoIMP().selectShopCarstbyCid(1);
    }

    public Set<ShopCart> selectShopCarstbyCid(Integer cid) {
        Set<ShopCart> setgoods = new HashSet<>();
        try {
            new MySqlUtil(cnn).QueryWithParam("select * from relation_4 where cid = ?", new IPreparedStatement() {
                @Override
                public void setPreparedStatement(PreparedStatement ps) throws SQLException {
                    ps.setInt(1, cid);
                }
            }, new IResultSet() {
                @Override
                public void setIResultSet(ResultSet rs) throws SQLException {
                    while (rs.next()) {
                        int gnumber = rs.getInt("gnumber");
                        Date date = rs.getDate("gtime");
                        String gtime = DateUtil.dateToStr(date, "YYYY-MM-dd");
                        int gid = rs.getInt("gid");
                        ShopCart shopCart = new ShopCart();
                        shopCart.setCid(cid);
                        shopCart.setG(new Goods(gid));
                        shopCart.setGtime(gtime);
                        shopCart.setGnumber(gnumber);
                        setgoods.add(shopCart);
                    }
                }
            });
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            MySqlConnection.closePart();
        }

        return setgoods;
    }

    public Set<ShopCart> selectGoodsByCartId(int cartid) throws SQLException {
        conn = MySqlConnection.getConnect();
        Set<ShopCart> setgoods = new HashSet<>();
        Set<Goods> setg = new HashSet<>();
        String sql = "SELECT g.gid, g.gname,g.gprice,r4.gtime , r4.gnumber FROM goods g LEFT JOIN relation_4 r4 ON g.gid = r4.gid LEFT JOIN shopcart st ON st.cid = r4.cid WHERE r4.cid = ?";
        ps = conn.prepareStatement(sql);
        ps.setInt(1, cartid);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int gnumber = rs.getInt("gnumber");
            Date date = rs.getDate("gtime");
            String gtime = DateUtil.dateToStr(date, "YYYY-MM-dd");
            String gname = rs.getString("gname");
            double gprice = rs.getDouble("gprice");
            int gid = rs.getInt("gid");
            Goods g = new Goods(gid, gname, gprice);
            setg.add(g);
            ShopCart sc = new ShopCart(gtime, gnumber);
            sc.setSetgoods(setg);
            setgoods.add(sc);
        }

        return setgoods;
    }

    public Set<ShopCart> selectAllShopCart() {
        Set<ShopCart> setshopsart = new HashSet<>();
        conn = MySqlConnection.getConnect();
        try {
            ps = conn.prepareStatement("select * from shopcart");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int cid = rs.getInt("cid");
                int user_uid = rs.getInt("user_uid");
                ShopCart sc = new ShopCart(cid);
                sc.setUse(new Users(user_uid));
                setshopsart.add(sc);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            MySqlConnection.closePart();
        }
        return setshopsart;
    }

    public ShopCart selectShopCartbyCid(Integer cid) {
        ShopCart shopCart = new ShopCart();
        MySqlUtil mySqlUtil = new MySqlUtil(cnn);
        try {
            mySqlUtil.QueryWithParam("select user_uid from shopcart where cid = ?", new IPreparedStatement() {
                @Override
                public void setPreparedStatement(PreparedStatement ps) throws SQLException {
                    ps.setInt(1, cid);
                }
            }, new IResultSet() {
                @Override
                public void setIResultSet(ResultSet rs) throws SQLException {
                    while (rs.next()) {
                        int user_uid = rs.getInt("user_uid");
                        shopCart.setCid(cid);
                        shopCart.setUse(new Users(user_uid));
                    }
                }
            });
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            MySqlConnection.closePart();
        }
        System.out.println(shopCart);
        return shopCart;
    }


    public void updateShopCartGoods(ShopCart sc) {
        try {
            new MySqlUtil(cnn).UpdateOrInsert("UPDATE relation_4 SET gnumber = ?,gtime = ? WHERE cid =? AND gid =?", new IPreparedStatement() {
                @Override
                public void setPreparedStatement(PreparedStatement ps) throws SQLException {
                    ps.setInt(1, sc.getGnumber());
                    ps.setString(2, sc.getGtime());
                    ps.setInt(3, sc.getCid());
                    ps.setInt(4, sc.getG().getGid());
                }
            });
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            MySqlConnection.closePart();
        }
    }

    public ShopCart selectShopCartbyUid(Integer uid) {
        ShopCart shopCart = new ShopCart();
        MySqlUtil mySqlUtil = new MySqlUtil(cnn);
        try {
            mySqlUtil.QueryWithParam("select cid from shopcart where user_uid = ?", new IPreparedStatement() {
                @Override
                public void setPreparedStatement(PreparedStatement ps) throws SQLException {
                    ps.setInt(1, uid);
                }
            }, new IResultSet() {
                @Override
                public void setIResultSet(ResultSet rs) throws SQLException {
                    while (rs.next()) {
                        int cid = rs.getInt("cid");
                        shopCart.setCid(cid);
                    }
                }
            });
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            MySqlConnection.closePart();
        }
        return shopCart;
    }

    public void insertShoppingCart(ShopCart sc) {
        MySqlUtil mySqlUtil = new MySqlUtil(cnn);
        try {
            mySqlUtil.UpdateOrInsert("insert into shopcart values(?,?)", new IPreparedStatement() {
                @Override
                public void setPreparedStatement(PreparedStatement ps) throws SQLException {
                    ps.setInt(1, sc.getCid());
                    ps.setInt(2, sc.getUse().getUid());
                }
            });
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            MySqlConnection.closePart();
        }
    }

    public Boolean existGidAndCid(Integer Gid, Integer Cid) {
        boolean yes = false;
        Set<ShopCart> shopCarts = selectShopCarst();
        for (ShopCart sc :
                shopCarts) {
            if (sc.getG().getGid() == Gid && sc.getCid() == Cid) {
                yes = true;
                break;
            }
        }
        return yes;
    }

    public Boolean existGid(Integer gid) {
        boolean yes = false;
        Set<ShopCart> shopCarts = selectShopCarst();
        for (ShopCart s : shopCarts
        ) {
            if (s.getG().getGid() == gid) {
                yes = true;
                break;
            }
        }
        return yes;
    }

    public Boolean existCid(Integer cid) {
        boolean yes = false;
        Set<ShopCart> shopCarts = selectShopCarst();
        for (ShopCart sc :
                shopCarts) {
            if (sc.getCid() == cid) {
                yes = true;
                break;
            }
        }
        return yes;
    }

    public Boolean existUid(Integer uid) {
        boolean yes = false;
        Set<ShopCart> shopCarts = selectAllShopCart();
        for (ShopCart s : shopCarts
        ) {
            if (s.getUse().getUid() == uid) {
                yes = true;
                break;
            }
        }
        return yes;
    }

    public void deleteByCidAndGid(Integer cid, Integer gid) {
        try {
            new MySqlUtil(cnn).UpdateOrInsert("delete from relation_4 where cid =? and gid = ? ", new IPreparedStatement() {
                @Override
                public void setPreparedStatement(PreparedStatement ps) throws SQLException {
                    ps.setInt(1, cid);
                    ps.setInt(2, gid);
                }
            });
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            MySqlConnection.closePart();
        }
    }

    public void delete(Integer cid) {
        try {
            new MySqlUtil(cnn).UpdateOrInsert("delete from shopcart where cid =?", new IPreparedStatement() {
                @Override
                public void setPreparedStatement(PreparedStatement ps) throws SQLException {
                    ps.setInt(1, cid);
                }
            });
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            MySqlConnection.closePart();
        }
    }

    public Set<ShopCart> selectShoppingcartGoods() {
        Set<ShopCart> ssc = new HashSet<>();

        try {
            new MySqlUtil(cnn).QueryNoParam("select cid,gid from relation_4", new IResultSet() {
                @Override
                public void setIResultSet(ResultSet rs) throws SQLException {
                    while (rs.next()) {
                        ShopCart shopCart = new ShopCart();
                        shopCart.setCid(rs.getInt("cid"));
                        shopCart.setG(new Goods(rs.getInt("gid")));
                        ssc.add(shopCart);
                    }
                }
            });
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            MySqlConnection.closePart();
        }
        return ssc;
    }

    public Boolean exsitGidAndCid(Integer gid, Integer cid) {
        Set<ShopCart> shopCarts = selectShoppingcartGoods();
        boolean yes = true;
        for (ShopCart sc :
                shopCarts) {
            if (gid != sc.getG().getGid() && cid != sc.getCid()) {
                yes = true;
            } else {
                yes = false;
                break;
            }
        }
        return yes;
    }

    public void insertGoodsToShoppingCart(ShopCart sc) {
        try {
            new MySqlUtil(cnn).UpdateOrInsert("insert into relation_4 values(?,?,?,?)", new IPreparedStatement() {
                @Override
                public void setPreparedStatement(PreparedStatement ps) throws SQLException {
                    ps.setInt(1, sc.getCid());
                    ps.setInt(2, sc.getG().getGid());
                    ps.setString(3, sc.getGtime());
                    ps.setInt(4, sc.getGnumber());
                }
            });
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            MySqlConnection.closePart();
        }
    }


}
