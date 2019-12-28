package service;

import dao.imp.OrderDetailsIMP;
import dao.imp.OrdersDaoIMP;
import dao.imp.UsersDaoIMP;
import enums.Enums;
import enums.Responese;
import mtomentity.OrderDetails;
import onetomanyentity.Orders;
import onetooneentity.Users;
import util.DateUtil;

import java.sql.SQLException;
import java.util.*;

public class OrderService {
    private UsersDaoIMP udimp = new UsersDaoIMP();
    private OrderDetailsIMP odsimp = new OrderDetailsIMP();
    private OrdersDaoIMP odimp = new OrdersDaoIMP();

    //	public List<Orders> selectAllData() throws SQLException {
//		List<Orders> olist = new ArrayList<>();
//		Set<Orders> select = odimp.select(null);
//		for (Orders orders : select) {
//			Users selectUsersById = udimp.selectUsersById(orders.getUse().getUid());
//			orders.setUse(selectUsersById);
//			Set<OrderDetails> selectByOrderid = odsimp.selectByOrderid(orders.getOid());
//			orders.setSetOrder(selectByOrderid);
//			olist.add(orders);
//		}
//
//		return olist;
//	}

    public Boolean existGoodsByUid(Integer uid, Integer oid) {
        boolean yes = false;
        Set<Orders> orders = odimp.selectByUserId(uid);
        for (Orders ord : orders
        ) {
            if (ord.getOid() == oid) {
                yes = true;
                break;
            }
        }
        return yes;
    }

    public Boolean existOrderOidAndUid(Integer oid, Integer uid) {
        return odimp.existOidAndUid(oid, uid);
    }

    public Boolean existOrderByOid(Integer oid) {
        return odimp.existOrder(oid);
    }

    public Responese updateOrder_t_o_s_ByUidAndOid(Orders od) {
        odimp.updateOrder_t_o_s_ByUidAndOid(od);
        return new Responese(Enums.SUCCESS);
    }

    public Responese updateByUidAndOid(Orders od) {
        odimp.updateByUidAndOid(od);
        return new Responese(Enums.SUCCESS);
    }

    public Boolean existOrderByUid(Integer uid) {
        return odimp.existUid(uid);
    }

    public Responese selectOrdersOIdByUserId(Users u) {
        Responese responese = new Responese(Enums.SUCCESS);
        if (u != null) {
            Set<Orders> orders = odimp.selectByUserId(u.getUid());

            responese.setObj(orders);
            return responese;
        } else {
            return new Responese(Enums.FAIL);
        }
    }

    public Responese selectOrdersDataByUserId(Users u) {
        Responese responese = new Responese(Enums.SUCCESS);
        List<Orders> lod = new ArrayList<>();
        Set<Orders> orders = odimp.selectByUserId(u.getUid());
        int i = 1;
        if (orders != null && orders.size() != 0) {
            for (Orders o :
                    orders) {
                o.setOid(i);
                i++;
                lod.add(o);
            }
            lod.sort(new Comparator<Orders>() {
                @Override
                public int compare(Orders o1, Orders o2) {
                    return o1.getOid() - o2.getOid();
                }
            });
            System.out.println(lod);
            responese.setObj(lod);
            return responese;
        } else {
            return new Responese(Enums.FAIL);
        }


    }


//    public Set<Orders> selectOrdersDataByOrderId(Users u) throws SQLException {
//        Set<Orders> sod = new HashSet<>();
//        Set<Orders> select = odimp.select(null);
//        for (Orders orders : select) {
//            if (orders.getUse().getUid() == u.getUid()) {
//                Set<OrderDetails> selectByOrderid = odsimp.selectByOrderid(orders.getOid());
//                orders.setSetOrder(selectByOrderid);
//                sod.add(orders);
//            }
//        }
//        return sod;
//
//    }

    public Responese insertOrder(Orders o) {
        Responese responese = new Responese(Enums.SUCCESS);
        if (o != null) {
            odimp.insert(o);
            return responese;
        } else {
            return new Responese(Enums.FAIL);
        }
    }

}
