package service;

import dao.imp.GoodsDaoIMP;
import dao.imp.OrderDetailsIMP;
import dao.imp.OrdersDaoIMP;
import enums.Enums;
import enums.Responese;
import jdbcutil.MySqlConnection;
import mtomentity.OrderDetails;
import onetomanyentity.Goods;
import onetomanyentity.Orders;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class OrderdetailedService {
    private GoodsDaoIMP gdimp = new GoodsDaoIMP();
    private OrdersDaoIMP odimp = new OrdersDaoIMP();
    private OrderDetailsIMP pdsimp = new OrderDetailsIMP();

    public Boolean existOid(Orders odds) {
        return pdsimp.existOid(odds);

    }

    public Responese selectByGidAndOid(Integer gid, Integer oid) {
        Responese responese = new Responese(Enums.SUCCESS);
        OrderDetails orderDetails = pdsimp.selectByGidAndOid(gid, oid);
        responese.setObj(orderDetails);
        return responese;
    }

    public Responese updateOrderDetails(OrderDetails odds) {
        Responese responese = new Responese(Enums.SUCCESS);
        if (odds != null) {
            pdsimp.update(odds);
            return responese;
        } else {
            return new Responese(Enums.FAIL);
        }


    }

    public List<OrderDetails> getOrderDetailsAllData() {
        List<OrderDetails> ods = new ArrayList<>();
        Set<OrderDetails> select = pdsimp.select(null);
        for (OrderDetails orderDetails : select) {
            Goods selectById = gdimp.selectById(orderDetails.getGoods_gid().getGid());
            Orders order_oid = odimp.selectById(orderDetails.getOrder_oid().getOid());
            orderDetails.setGoods_gid(selectById);
            orderDetails.setOrder_oid(order_oid);
            ods.add(orderDetails);
        }
        return ods;
    }

    public Boolean existGid(Integer Gid) {
        return pdsimp.existGid(Gid);
    }

    public Boolean existOidAndGid(OrderDetails odds) {

        return pdsimp.existOidAndGid(odds);

    }

    public List<OrderDetails> getOrderDetailsGoodsData() throws SQLException {
        List<OrderDetails> ods = new ArrayList<>();
        Set<OrderDetails> select = pdsimp.select(null);
        for (OrderDetails orderDetails : select) {
            Goods selectById = gdimp.selectById(orderDetails.getGoods_gid().getGid());
            orderDetails.setGoods_gid(selectById);
            ods.add(orderDetails);
        }
        return ods;
    }

    public List<OrderDetails> getOrderDetailsOrdersData() throws SQLException {
        List<OrderDetails> ods = new ArrayList<>();
        Set<OrderDetails> select = pdsimp.select(null);
        for (OrderDetails orderDetails : select) {
            Orders order_oid = odimp.selectById(orderDetails.getOrder_oid().getOid());
            orderDetails.setOrder_oid(order_oid);
            ods.add(orderDetails);
        }
        return ods;
    }



    public Responese selectOberDetailsByoId(Integer oid) {
        Responese responese = new Responese(Enums.SUCCESS);
        OrderDetails orderDetails = pdsimp.selectByOrderid(oid);
        responese.setObj(orderDetails);
        return responese;
    }

    public Responese selectOberDetailsByUserId(Integer uid) {
        Responese responese = new Responese(Enums.SUCCESS);
        if (uid != null && uid != 0) {
            if (odimp.existUid(uid)) {
                Set<Orders> order = odimp.selectByUserId(uid);
                Set<OrderDetails> setDes = new HashSet<>();
                if (order.size() != 0) {
                    for (Orders orders :
                            order) {
                        OrderDetails orderDetails1 = pdsimp.selectByOrderid(orders.getOid());
                        orderDetails1.setOrder_oid(orders);
                        setDes.add(orderDetails1);
                    }
                    System.out.println(setDes);
                    responese.setObj(setDes);
                    return responese;
                } else {
                    return new Responese(Enums.FAIL);
                }
            } else {
                return new Responese(Enums.FAIL);
            }
        } else {
            return new Responese(Enums.FAIL);
        }
    }

    public Responese deleteOderDetailsByOid(Integer oid) {
        if (oid != null) {
            OrderDetails orderDetails = new OrderDetails();
            orderDetails.setDetailedid(oid);
            pdsimp.delete(orderDetails);
            return new Responese(Enums.SUCCESS);
        } else {
            return new Responese(Enums.FAIL);

        }
    }

    public Responese insertGoodsIntoOderDetails(OrderDetails ods) {
        if (ods != null) {
            pdsimp.insert(ods);
            return new Responese(Enums.SUCCESS);
        } else {
            return new Responese(Enums.FAIL);
        }
    }
}
