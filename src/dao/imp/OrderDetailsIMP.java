package dao.imp;

import idao.IDao;
import jdbcutil.IPreparedStatement;
import jdbcutil.IResultSet;
import jdbcutil.MySqlConnection;
import jdbcutil.MySqlUtil;
import mtomentity.OrderDetails;
import onetomanyentity.Goods;
import onetomanyentity.Orders;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class OrderDetailsIMP implements IDao<OrderDetails> {
    private Connection conn;
    private Connection cnn = MySqlConnection.getConnect();
    private PreparedStatement ps;

    public OrderDetailsIMP() {
        super();
    }

    public static void main(String[] args) {
        new OrderDetailsIMP().selectGidAndOid();
    }

    public Set<OrderDetails> selectGidAndOid() {

        Set<OrderDetails> setOrderDetails = new HashSet<>();
        try {
            new MySqlUtil(cnn).QueryNoParam("select goods_gid,order_oid from orderdetailed", new IResultSet() {
                @Override
                public void setIResultSet(ResultSet rs) throws SQLException {
                    while (rs.next()) {
                        int goods_gid = rs.getInt("goods_gid");
                        int order_oid = rs.getInt("order_oid");
                        OrderDetails orderDetails = new OrderDetails();
                        orderDetails.setGoods_gid(new Goods(goods_gid));
                        orderDetails.setOrder_oid(new Orders(order_oid));
                        setOrderDetails.add(orderDetails);
                    }
                }
            });
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            MySqlConnection.closePart();
        }
        System.out.println(setOrderDetails);
        return setOrderDetails;
    }

    @Override
    public Set<OrderDetails> select(OrderDetails t) {
        conn = MySqlConnection.getConnect();
        Set<OrderDetails> setOrderDetails = new HashSet<>();
        try {
            if (t == null) {
                String sql = "select * from orderdetailed";
                ResultSet rs = null;
                ps = conn.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    int detailedid = rs.getInt("detailedid");
                    int detailednumber = rs.getInt("detailednumber");
                    int goods_gid = rs.getInt("goods_gid");//
                    int order_oid = rs.getInt("order_oid");//
                    double goodsprice = rs.getDouble("goodsprice");
                    double detailedtotal = rs.getDouble("detailedtotal");
                    String detailedname = rs.getString("detailedname");
                    OrderDetails od = new OrderDetails(detailedid, detailedname, detailednumber, goodsprice, detailedtotal);
                    od.setGoods_gid(new Goods(goods_gid));
                    od.setOrder_oid(new Orders(order_oid));
                    setOrderDetails.add(od);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            MySqlConnection.closePart();
        }

        return setOrderDetails;
    }

    @Override
    public OrderDetails selectById(int id) throws SQLException {
        Set<OrderDetails> select = select(null);
        OrderDetails od = new OrderDetails();
        for (OrderDetails orderDetails : select) {
            if (orderDetails.getDetailedid() == id) {
                od = orderDetails;
            }
        }
        return od;
    }

    public OrderDetails selectByGid(int Gid) throws SQLException {
        Set<OrderDetails> select = select(null);
        OrderDetails od = new OrderDetails();
        for (OrderDetails orderDetails : select) {
            if (orderDetails.getGoods_gid().getGid() == Gid) {
                od = orderDetails;
            }
        }
        return od;

    }

    public OrderDetails selectByOrderid(int Orderid) {
        Set<OrderDetails> select = select(null);
        OrderDetails orderDetails1 = new OrderDetails();
        for (OrderDetails orderDetails : select) {
            if (orderDetails.getOrder_oid().getOid() == Orderid) {
                orderDetails1 = orderDetails;
            }
        }
        return orderDetails1;
    }

    public OrderDetails selectByGidAndOid(Integer gid, Integer oid) {
        OrderDetails orderDetails1 = null;
        Set<OrderDetails> orderDetails = selectGidAndOid();
        for (OrderDetails ods :
                orderDetails) {
            if (gid == ods.getGoods_gid().getGid() && oid == ods.getOrder_oid().getOid()) {
                orderDetails1 = ods;
            }
        }
        return orderDetails1;
    }

    @Override
    public void update(OrderDetails ods) {
        MySqlUtil mySqlUtil = new MySqlUtil(conn);
        try {
            mySqlUtil.UpdateOrInsert(
                    "UPDATE orderdetailed SET detailednumber = ?,detailedtotal = ? WHERE order_oid = ? AND goods_gid = ?",
                    new IPreparedStatement() {

                        @Override
                        public void setPreparedStatement(PreparedStatement ps) throws SQLException {
                            ps.setInt(1, ods.getDetailednumber());
                            ps.setDouble(2, ods.getDetailednumber() * ods.getGoodsprice());
                            ps.setInt(3, ods.getOrder_oid().getOid());
                            ps.setInt(4, ods.getGoods_gid().getGid());
                        }
                    });
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            MySqlConnection.closePart();
        }
    }

    @Override
    public void delete(OrderDetails ods) {
        MySqlUtil mySqlUtil = new MySqlUtil(conn);
        try {
            mySqlUtil.UpdateOrInsert("DELETE FROM orderdetailed WHERE detailedid = ?",
                    new IPreparedStatement() {

                        @Override
                        public void setPreparedStatement(PreparedStatement ps) throws SQLException {
                            ps.setInt(1, ods.getDetailedid());

                        }
                    });
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            MySqlConnection.closePart();
        }

    }

    @Override
    public void isExist() {
        // TODO Auto-generated method stub

    }

    public Boolean existGid(Integer gid) {
        boolean yes = false;
        Set<OrderDetails> orderDetails = selectGidAndOid();
        for (OrderDetails ods : orderDetails
        ) {
            if (ods.getGoods_gid().getGid() == gid) {
                yes = true;
                break;
            }
        }
        return yes;
    }

    public Boolean existOid(Orders odds) {
        Set<OrderDetails> orderDetails = selectGidAndOid();
        boolean yes = false;
        for (OrderDetails ods : orderDetails
        ) {
            if (ods.getOrder_oid().getOid() == odds.getOid()) {
                yes = true;
                break;
            }
        }
        return yes;
    }

    public Boolean existOidAndGid(OrderDetails odds) {
        Set<OrderDetails> orderDetails = selectGidAndOid();
        boolean yes = true;
        for (OrderDetails ods : orderDetails
        ) {
            if (ods.getGoods_gid().getGid() == odds.getGoods_gid().getGid() && ods.getOrder_oid().getOid() == odds.getOrder_oid().getOid()) {
                yes = false;
                break;
            }
        }
        return true;
    }

    @Override
    public void insert(OrderDetails t) {
        MySqlUtil mySqlUtil = new MySqlUtil(cnn);
        try {
            mySqlUtil.UpdateOrInsert(
                    "INSERT INTO orderdetailed (detailedname,detailednumber,goodsprice,detailedtotal,goods_gid,order_oid) VALUES(?,?,?,?,?,?)",
                    new IPreparedStatement() {
                        @Override
                        public void setPreparedStatement(PreparedStatement ps) throws SQLException {
                            ps.setString(1, t.getDetailedname());
                            ps.setInt(2, t.getDetailednumber());
                            ps.setDouble(3, t.getGoodsprice());
                            ps.setDouble(4, t.getDetailedtotal());
                            ps.setInt(5, t.getGoods_gid().getGid());
                            ps.setInt(6, t.getOrder_oid().getOid());
                        }

                    });
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            MySqlConnection.closePart();
        }
    }


}
