package dao.imp;

import enums.Responese;
import idao.IDao;
import jdbcutil.IPreparedStatement;
import jdbcutil.IResultSet;
import jdbcutil.MySqlConnection;
import jdbcutil.MySqlUtil;
import onetomanyentity.Orders;
import onetooneentity.Users;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class OrdersDaoIMP implements IDao<Orders> {
    private Connection conn;
    private PreparedStatement ps;
    private Connection cnn = MySqlConnection.getConnect();

    public Set<Orders> selectOidAndUid() {
        Set<Orders> setOrders = new HashSet<>();
        try {
            new MySqlUtil(cnn).QueryNoParam("select oid,user_uid from orders", new IResultSet() {
                @Override
                public void setIResultSet(ResultSet rs) throws SQLException {
                    while (rs.next()) {
                        int oid = rs.getInt("oid");
                        int user_uid = rs.getInt("user_uid");
                        Orders orders = new Orders();
                        orders.setOid(oid);
                        orders.setUse(new Users(user_uid));
                        setOrders.add(orders);
                    }
                }
            });
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            MySqlConnection.closePart();
        }
        return setOrders;
    }

    public Boolean existOidAndUid(Integer oid, Integer uid) {
        Boolean yes = false;
        Set<Orders> orders = selectOidAndUid();
        for (Orders ord :
                orders) {
            if (ord.getOid() == oid && ord.getUse().getUid() == uid) {
                yes = true;
                break;
            }
        }
        return yes;
    }

    public Boolean existUid(Integer uid) {
        Boolean yes = false;
        Set<Orders> orders = selectOidAndUid();
        for (Orders ord :
                orders) {
            if (ord.getUse().getUid() == uid) {
                yes = true;
                break;
            }
        }
        return yes;
    }

    @Override
    public Set<Orders> select(Orders t) {
        conn = MySqlConnection.getConnect();
        Set<Orders> setOrders = new HashSet<>();
        try {
            if (t == null) {
                String sql = "select * from orders";
                ResultSet rs = null;
                ps = conn.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    String ostatus = rs.getString("ostatus");
                    String ostart_time = rs.getString("ostart_time");
                    int oid = rs.getInt("oid");
                    int user_uid = rs.getInt("user_uid");
                    double ototal = rs.getDouble("ototal");
                    Orders os = new Orders(oid, ostart_time, ostatus, ototal);
                    os.setUse(new Users(user_uid));
                    setOrders.add(os);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            MySqlConnection.closePart();
        }

        return setOrders;
    }

    @Override
    public Orders selectById(int id) {
        Set<Orders> select = select(null);
        Orders o = new Orders();
        for (Orders orders : select) {
            if (orders.getOid() == id) {
                o = orders;
            }
        }
        return o;
    }


    public Set<Orders> selectByUserId(int userid) {
        Set<Orders> so = new HashSet<>();

        MySqlUtil mySqlUtil = new MySqlUtil(cnn);
        try {
            mySqlUtil.QueryWithParam("SELECT * FROM orders WHERE user_uid = ?", new IPreparedStatement() {
                @Override
                public void setPreparedStatement(PreparedStatement ps) throws SQLException {
                    ps.setInt(1, userid);
                }
            }, new IResultSet() {
                @Override
                public void setIResultSet(ResultSet rs) throws SQLException {
                    while (rs.next()) {
                        String ostatus = rs.getString("ostatus");
                        String ostart_time = rs.getString("ostart_time");
                        int oid = rs.getInt("oid");
                        int user_uid = rs.getInt("user_uid");
                        double ototal = rs.getDouble("ototal");
                        Orders o = new Orders();
                        o.setOid(oid);
                        o.setOstart_time(ostart_time);
                        o.setOstatus(ostatus);
                        o.setOtotal(ototal);
                        o.setUse(new Users(user_uid));
                        so.add(o);
                    }
                }
            });
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            MySqlConnection.closePart();
        }
        return so;

    }

    public Boolean existOrder(Integer oid) {
        boolean yes = false;
        Set<Orders> orders = selectOidAndUid();
        for (Orders od :
                orders) {
            if (od.getOid() == oid) {
                yes = true;
                break;
            }
        }
        return yes;
    }

    @Override
    public void isExist() {
        // TODO Auto-generated method stub

    }


    @Override
    public void insert(Orders t) {
        MySqlUtil mySqlUtil = new MySqlUtil(cnn);
        try {
            mySqlUtil.UpdateOrInsert("insert into orders (ostart_time,ototal,ostatus,user_uid) values(?,?,?,?)", new IPreparedStatement() {
                @Override
                public void setPreparedStatement(PreparedStatement ps) throws SQLException {
                    ps.setString(1, t.getOstart_time());
                    ps.setDouble(2, t.getOtotal());
                    ps.setString(3, t.getOstatus());
                    ps.setInt(4, t.getUse().getUid());
                }
            });
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            MySqlConnection.closePart();
        }

    }

    public void updateOrder_t_o_s_ByUidAndOid(Orders od) {
        try {
            new MySqlUtil(cnn).UpdateOrInsert("update orders set ostart_time = ?,ototal = ?,ostatus = ? where oid =? and user_uid = ? ", new IPreparedStatement() {
                @Override
                public void setPreparedStatement(PreparedStatement ps) throws SQLException {
                    ps.setString(1, od.getOstart_time());
                    ps.setDouble(2, od.getOtotal());
                    ps.setString(3, od.getOstatus());
                    ps.setInt(4, od.getOid());
                    ps.setInt(5, od.getUse().getUid());
                }
            });
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            MySqlConnection.closePart();
        }
    }

    public void updateByUidAndOid(Orders od) {
        try {
            new MySqlUtil(cnn).UpdateOrInsert("update orders set ostart_time = ?,ototal = ? where oid =? and user_uid = ?", new IPreparedStatement() {
                @Override
                public void setPreparedStatement(PreparedStatement ps) throws SQLException {
                    ps.setString(1, od.getOstart_time());
                    ps.setDouble(2, od.getOtotal());
                    ps.setInt(3, od.getOid());
                    ps.setInt(4, od.getUse().getUid());
                }
            });
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            MySqlConnection.closePart();
        }
    }

    @Override
    public void update(Orders t) throws SQLException {
        MySqlUtil mySqlUtil = new MySqlUtil(cnn);
        mySqlUtil.UpdateOrInsert("UPDATE orders SET ostatus = ? WHERE user_uid = ?", new IPreparedStatement() {
            @Override
            public void setPreparedStatement(PreparedStatement ps) throws SQLException {
                ps.setString(1, t.getOstatus());
                ps.setInt(2, t.getUse().getUid());

            }
        });

    }

    @Override
    public void delete(Orders t) throws SQLException {
        // TODO Auto-generated method stub
//        MySqlUtil mySqlUtil = new MySqlUtil(cnn);


    }

}
