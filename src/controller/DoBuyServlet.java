package controller;

import com.alibaba.fastjson.JSONObject;
import enums.Enums;
import enums.Responese;
import mtomentity.OrderDetails;
import onetomanyentity.Goods;
import onetomanyentity.Orders;
import onetomanyentity.ShopCart;
import onetooneentity.Users;
import service.GoodService;
import service.OrderService;
import service.OrderdetailedService;
import service.ShopCartService;
import util.DateUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.Map;
import java.util.Set;

@WebServlet(name = "DoBuyServlet", urlPatterns = "/doBuy")
public class DoBuyServlet extends HttpServlet {
    private GoodService gs = new GoodService();
    private ShopCartService scs = new ShopCartService();
    private OrderService ods = new OrderService();
    private OrderdetailedService odds = new OrderdetailedService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String[] gids = request.getParameterValues("gid");
        Map<String, String> user = (Map<String, String>) request.getSession().getAttribute("user");
        Integer uid = Integer.valueOf(user.get("uid"));
        Boolean go = true;
        for (String str :
                gids) {
            Integer number = Integer.valueOf(request.getParameter("number" + str));
            System.out.println("数量" + number);
            Integer gid = Integer.valueOf(str);
            Goods goods = gs.getGoodsDataById(gid);
            double price = (goods.getGprice() * number);
            System.out.println("价格" + price);
            Orders orders = new Orders();
            orders.setOtotal(price);
            orders.setOstart_time(DateUtil.dateToStr(new Date(), "yyyy-MM-dd HH-mm-ss"));
            orders.setOstatus("已结算");
            orders.setUse(new Users(uid));
            Responese responese = ods.insertOrder(orders);
            if (responese.getCode() == 1) {

                Responese responese3 = ods.selectOrdersOIdByUserId(new Users(uid));
                //有相同的order，覆盖原始order并且覆盖orderdetals
                Set<Orders> obj1 = (Set<Orders>) responese3.getObj();
                for (Orders odss :
                        obj1) {
                    OrderDetails orderDetail = new OrderDetails();
                    orderDetail.setDetailedname(goods.getGname());
                    orderDetail.setDetailednumber(number);
                    orderDetail.setGoodsprice(goods.getGprice());
                    orderDetail.setDetailedtotal(price);
                    orderDetail.setGoods_gid(goods);
                    orders.setOid(odss.getOid());
                    orderDetail.setOrder_oid(orders);

                    if (!odds.existOidAndGid(orderDetail)) {
                        odds.insertGoodsIntoOderDetails(orderDetail);
                        break;
                    } else {
                        Responese responese2 = odds.selectOberDetailsByoId(odss.getOid());
                        OrderDetails obj2 = (OrderDetails) responese2.getObj();
                        if (obj2.getGoods_gid() != null && gid == obj2.getGoods_gid().getGid()) {
                            orderDetail.setDetailednumber(obj2.getDetailednumber() + number);
                            orderDetail.setDetailedtotal(obj2.getDetailedtotal() + price);
                            odds.updateOrderDetails(orderDetail);
                            break;
                        }


                    }
                }
                Responese responese1 = scs.selectShopCartbyUid(uid);
                ShopCart obj = (ShopCart) responese1.getObj();
                Responese responese2 = scs.deleteByCidAndGid(obj.getCid(), gid);
                go = responese2.getCode() == 1 ? true : false;
            } else {
                go = false;
                //错误代码
            }
        }
        if (go) {
            response.sendRedirect("/shopping_test/shopping-result.jsp");

        } else {
            //錯誤
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
