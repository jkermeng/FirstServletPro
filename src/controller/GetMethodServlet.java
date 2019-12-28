package controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import dao.imp.GoodsPictureDaoIMP;
import entity.HotNews;
import entity.HotProduce;
import entity.PageModel;
import enums.Enums;
import enums.Responese;
import mtomentity.OrderDetails;
import onetomanyentity.*;

import onetooneentity.Users;
import service.*;
import sun.plugin.javascript.navig.Link;
import util.DateUtil;


import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

import java.util.*;

public class GetMethodServlet {
    private UserService userService = new UserService();
    private GoodService gs = new GoodService();
    private _ClassificationService cfs = new _ClassificationService();
    private GoodsPictureDaoIMP gp = new GoodsPictureDaoIMP();
    private OrderdetailedService ordt = new OrderdetailedService();
    private OrderService ods = new OrderService();
    private ShopCartService scs = new ShopCartService();

    private void login(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json;charset=UTF-8");
//        resp.setContentType("html/text;chartset=UTF-8");
        req.setCharacterEncoding("UTF-8");
        String userName = req.getParameter("userName");
        String passWord = req.getParameter("passWord");
        String remenber = req.getParameter("remenber");
        Cookie[] cookies = req.getCookies();
        if (cookies != null && cookies.length != 1) {
            for (Cookie ck : cookies
            ) {
                if (ck.getName().equals("login")) {
                    String name = ck.getValue();
                    String uname = name.substring((name.indexOf("=") + 1), name.indexOf("_"));
                    String pwd = name.substring(name.lastIndexOf("=") + 1);
                    if (uname.equals(userName) && pwd.equals(passWord)) {
                        Responese login = userService.Login(userName, passWord);
                        if (login.getCode() == 1) {
//                            ck.setPath("/shopping_test");
                            ck.setMaxAge(60 * 60 * 24 * 1);
                            resp.addCookie(ck);
                            Users obj = (Users) login.getObj();
                            Map<String, String> usermap = new HashMap();
                            usermap.put("user_name", obj.getUname());
                            usermap.put("uid", String.valueOf(obj.getUid()));
                            req.getSession().setAttribute("user", usermap);
                            resp.sendRedirect("/shopping_test/index.jsp");
                        } else {
                            Object o = JSONObject.toJSON(login);
                            resp.getWriter().write(o.toString());
                            resp.sendRedirect("/shopping_test/retrieve_password.jsp");
                        }
                    } else {
                        Object o = JSONObject.toJSON(new Responese(Enums.FAIL));
                        resp.getWriter().write(o.toString());
                        resp.sendRedirect("/shopping_test/retrieve_password.jsp");
                    }
                    break;
                }
            }
        } else {
            Responese login = userService.Login(userName, passWord);
            if (login.getCode() == 1) {
                if (remenber != null && remenber.equals("1")) {
                    Cookie cookie = new Cookie("login", "uname=" + userName + "_upwd=" + passWord);
                    cookie.setMaxAge(60 * 60 * 24 * 1);
                    cookie.setPath("/shopping_test");
                    resp.addCookie(cookie);
                    Map<String, String> usermap = new HashMap();
                    Users obj = (Users) login.getObj();
                    usermap.put("user_name", obj.getUname());
                    usermap.put("uid", String.valueOf(obj.getUid()));
                    req.getSession().setAttribute("user", usermap);
                    resp.sendRedirect("/shopping_test/index.jsp");
                } else {
                    Map<String, String> usermap = new HashMap();
                    Users obj = (Users) login.getObj();
                    usermap.put("user_name", obj.getUname());
                    usermap.put("uid", String.valueOf(obj.getUid()));
                    req.getSession().setAttribute("user", usermap);
                    resp.sendRedirect("/shopping_test/index.jsp");
                }
            } else {
                Object o = JSONObject.toJSON(login);
                resp.getWriter().write(o.toString());
                resp.sendRedirect("/shopping_test/retrieve_password.jsp");
            }
        }
    }

    private void veryCode(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String veryCode = req.getParameter("veryCode");
        String validateCode = (String) req.getSession().getAttribute("validateCode");
        if (veryCode != null && validateCode != null) {
            Responese responese = null;
            if (validateCode.equals(veryCode)) {
                responese = new Responese(Enums.SUCCESS);
                Object o = JSONObject.toJSON(responese);
                resp.getWriter().write(o.toString());
            } else {
                responese = new Responese(Enums.FAIL);
                Object o = JSONObject.toJSON(responese);
                resp.getWriter().write(o.toString());
            }
        }
    }

    private void goodsCategory(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        HttpSession session = req.getSession();
        List<Object> mclassList = new ArrayList<>();
        List<HotProduce> hplist = new LinkedList<>();
        Responese responese = cfs.selectAll();
        if (responese.getCode() == 1) {
            List<_Classification> cf = (List<_Classification>) responese.getObj();
            for (_Classification cfs :
                    cf) {
                Responese goodsByClassifyId = gs.getGoodsByClassifyId(cfs.getCid());
                if (goodsByClassifyId.getCode() == 1) {
                    List<Goods> obj = (List<Goods>) goodsByClassifyId.getObj();
                    List<Goods> glist = new LinkedList<>();
                    int i = 0;
                    for (Goods gd :
                            obj) {
                        GoodsPicture goodsPicture = gp.selectByGId(gd.getGid());
                        HotProduce hotProduce = new HotProduce();
                        hotProduce.setHpId(gd.getGid());
                        hotProduce.setHpName(gd.getGname());
                        hotProduce.setHpPrice(gd.getGprice());
                        hotProduce.setHpFileName(goodsPicture.getPurl());
                        hplist.add(hotProduce);
                        glist.add(gd);
                        i++;
                        if (i > 3) {
                            i = 0;
                            break;
                        }
                    }
                    cfs.setSetgoods(glist);
                    mclassList.add(cfs);
                } else {
                    //错误提示
                }
            }
        } else {
            //错误提示
        }
        session.setAttribute("mclassList", mclassList);
        PageModel pageModel = gs.pageGoods(4, 1);
        pageModel.setSource("/shopping_test/index.jsp");//什么的资源
        pageModel.setHpcId("1");//热门网页类id
        pageModel.setQname("Qname");//请求的名称
        req.setAttribute("pageModel", pageModel);
        //购物车

        Map<String, String> user = (Map<String, String>) req.getSession().getAttribute("user");
        if (user != null) {
            //有用户时
            Integer uid = Integer.valueOf(user.get("uid"));//用户ID
            Responese responese1 = scs.selectShopCartbyUid(uid);
            ShopCart obj = (ShopCart) responese1.getObj();
            if (obj != null && obj.getCid() != null) {
                Integer cid = obj.getCid();
                Responese responese2 = scs.selectShopCartDetailsByCid(cid);
                Set<ShopCart> obj1 = (Set<ShopCart>) responese2.getObj();
                if (obj1.size() > 0) {
                    for (ShopCart sc :
                            obj1) {
                        Goods goodsDataById = gs.getGoodsDataById(sc.getG().getGid());
                        sc.setG(goodsDataById);
                    }
                    req.setAttribute("carts", obj1);
                }
            }
            Responese responese2 = userService.selectbyUid(uid);
            Users obj1 = (Users) responese2.getObj();
            String uhotword = obj1.getUhotword();
            if (uhotword != null) {
                String[] split = uhotword.split("&");
                List viewgoods = new LinkedList();
                for (String st :
                        split) {
                    List<Goods> goods = gs.selectByGoodsName(st);
                    if (goods.size() > 1) {
                        continue;
                    } else {
                        Goods goods1 = goods.get(0);
                        GoodsPicture goodsPicture = gp.selectByGId(goods1.getGid());
                        goods1.setGp(goodsPicture);
                        viewgoods.add(goods1);
                    }
                }

                session.setAttribute("viewedProduct", viewgoods);
            }


        }
        //热门商品
        List<HotNews> nlist = new LinkedList<>();
        for (int i = 1; i < 10; i++) {
            HotNews hotNews = new HotNews(i, "Iphone" + i + ":" + i + "100￥");
            nlist.add(hotNews);
        }
        req.setAttribute("news", nlist);
        req.setAttribute("hotProducts", hplist);
        //货物数量
        req.getRequestDispatcher("/index.jsp").forward(req, resp);//Cannot forward after response has been committed
    }


    private void uniqueGoodsView(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String goodid = req.getParameter("goodid");
        Goods goodsDataById = gs.getGoodsDataById(Integer.valueOf(goodid));
        GoodsPicture goodsPicture = gp.selectByGId(Integer.valueOf(goodid));
        goodsDataById.setGp(goodsPicture);
        req.getSession().setAttribute("good", goodsDataById);
        resp.sendRedirect("/shopping_test/product_view.jsp");
    }

    private void deleteCart(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Integer cid = Integer.valueOf(req.getParameter("cid"));
        Integer gid = Integer.valueOf(req.getParameter("gid"));
        scs.deleteByCidAndGid(cid, gid);
        showCart(req, resp);
    }

    private void goodsView(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String searchName = req.getParameter("searchName");
        String searchId = req.getParameter("searchId");
        if (searchId != null && searchName != null) {
            List<Goods> goods = gs.selectByGoodsName(searchName);
            if (goods.size() > 1) {
                for (Goods gs :
                        goods) {
                    GoodsPicture goodsPicture = gp.selectByGId(gs.getGid());
                    gs.setGp(goodsPicture);
                }
                req.getSession().setAttribute("goods", goods);
                resp.sendRedirect("/shopping_test/product-list.jsp");
            } else {
                //只有一个商品时
                Goods goods1 = goods.get(0);
                GoodsPicture goodsPicture = gp.selectByGId(goods1.getGid());
                goods1.setGp(goodsPicture);
                req.getSession().setAttribute("good", goods1);
                resp.sendRedirect("/shopping_test/product_view.jsp");
            }
            //如果有用户登陆
            Map<String, String> user = (Map<String, String>) req.getSession().getAttribute("user");
            if (user != null) {
                if (user.get("uid") != null) {
                    String uid = user.get("uid");
                    userService.InsertUserKeyWord(searchName, uid);
                }
            }

        } else {

        }


    }

    private void addToCart(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Integer goodid = Integer.valueOf(req.getParameter("goodid"));
        Integer count = Integer.valueOf(req.getParameter("count"));
        Map<String, String> user = (Map<String, String>) req.getSession().getAttribute("user");
        if (user != null) {
            Integer uid = Integer.valueOf(user.get("uid"));
            ShopCart shopCart = new ShopCart();
            shopCart.setGtime(DateUtil.dateToStr(new Date(), "yyyy-MM-dd HH:mm:ss"));
            shopCart.setUse(new Users(uid));
            shopCart.setG(new Goods(goodid));
            shopCart.setGnumber(count);
            if (scs.existUid(uid)) {
                //存在购物车
                Responese responese = scs.selectShopCartbyUid(uid);
                ShopCart obj = (ShopCart) responese.getObj();
                if (!scs.existGidAndCid(goodid, obj.getCid())) {
                    //不存在货物id和购物车id
                    scs.insertGoodToShoppingCart(shopCart);
                    Object o = JSONObject.toJSON(new Responese(Enums.SUCCESS));
                    resp.getWriter().write(o.toString());
                } else {
                    //存在货物id和购物车id
                    //覆盖相同购物车id和商品id的时间和数量到relation_4中
                    Responese responese1 = scs.selectShopCartDetailsByCid(obj.getCid());
                    Set<ShopCart> obj1 = (Set<ShopCart>) responese1.getObj();//获得用户已存在的购物车，之后修改gtime，gnumber
                    for (ShopCart sc :
                            obj1) {
                        if (sc.getG().getGid() == goodid) {
                            sc.setGnumber(sc.getGnumber() + count);
                            sc.setGtime(DateUtil.dateToStr(new Date(), "yyyy-MM-dd HH:mm:ss"));
                            scs.updateShopCartDetails(sc);
                            Object o = JSONObject.toJSON(new Responese(Enums.SUCCESS));
                            resp.getWriter().write(o.toString());
                            break;
                        }

                    }

                }

            } else {
                Responese responese = scs.insertGoodsToShoppingCart(shopCart);
                Responese responese1 = scs.selectShopCartbyUid(uid);
                ShopCart obj = (ShopCart) responese1.getObj();
                shopCart.setCid(obj.getCid());
                scs.insertGoodToShoppingCart(shopCart);
                Object o = JSONObject.toJSON(new Responese(Enums.SUCCESS));
                resp.getWriter().write(o.toString());
            }
        } else {
            //错误
            Object o = JSONObject.toJSON(new Responese(Enums.FAIL));
            resp.getWriter().write(o.toString());
        }
        //添加如购物车
    }

    private void toBuyNow(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //重新思考立即购买思路
        Integer goodid = Integer.valueOf(req.getParameter("goodid"));
        Integer buynum = Integer.valueOf(req.getParameter("buynum"));
        Double price = Double.valueOf(req.getParameter("price"));
        System.out.println(goodid + "---" + buynum + "----" + price);
        Map<String, String> user = (Map<String, String>) req.getSession().getAttribute("user");
        if (user != null && user.size() != 0) {
            Integer uid = Integer.valueOf(user.get("uid"));
            Goods goods = gs.getGoodsDataById(goodid);//货物信息
            Orders orders = new Orders(DateUtil.dateToStr(new Date(), "yyyy-MM-dd HH:mm:ss"), "已付款", goods.getGprice() * buynum, new Users(uid));
            //基础定订单組件
            Object o = new Object();
            if (ods.existOrderByUid(uid)) {
                //存在订单
                Responese responese2 = ods.selectOrdersOIdByUserId(new Users(uid));//Orders
                Set<Orders> obj = (Set<Orders>) responese2.getObj();
                if (obj != null && obj.size() != 0) {
                    OrderDetails orderDetails = new OrderDetails();
                    orderDetails.setGoods_gid(new Goods(goodid));
                    for (Orders ord : obj
                    ) {
                        orderDetails.setOrder_oid(ord);
                        if (!ordt.existOidAndGid(orderDetails)) {
                            //存在订单详情信息
                            Responese responese = ordt.selectOberDetailsByoId(ord.getOid());
                            OrderDetails obj1 = (OrderDetails) responese.getObj();
                            obj1.setDetailednumber(obj1.getDetailednumber() + buynum);
                            obj1.setDetailedtotal(obj1.getDetailednumber() + obj1.getGoodsprice());
                            //修改order订单
                            ord.setOstatus("已付款");
                            ord.setOstart_time(DateUtil.dateToStr(new Date(), "yyyy-MM-dd HH:mm:ss"));
                            ord.setOtotal(obj1.getDetailedtotal());
                            ods.updateOrder_t_o_s_ByUidAndOid(ord);
                            ordt.updateOrderDetails(obj1);
                        } else {
                            ods.insertOrder(orders);
                            Responese responese3 = ods.selectOrdersOIdByUserId(new Users(uid));
                            //有相同的order，覆盖原始order并且覆盖orderdetals
                            Set<Orders> obj1 = (Set<Orders>) responese3.getObj();
                            for (Orders odss :
                                    obj1) {
                                OrderDetails orderDetail = new OrderDetails();
                                orderDetail.setDetailedname(goods.getGname());
                                orderDetail.setDetailednumber(buynum);
                                orderDetail.setGoodsprice(goods.getGprice());
                                orderDetail.setDetailedtotal(buynum * goods.getGprice());
                                orderDetail.setGoods_gid(goods);
                                orders.setOid(odss.getOid());
                                orderDetail.setOrder_oid(orders);
                                if (!ordt.existOidAndGid(orderDetail)) {
                                    ordt.insertGoodsIntoOderDetails(orderDetail);
                                    o = JSONObject.toJSON(new Responese(Enums.SUCCESS));
                                    break;
                                } else {
                                    Responese responese4 = ordt.selectOberDetailsByoId(odss.getOid());
                                    OrderDetails obj2 = (OrderDetails) responese4.getObj();
                                    if (obj2.getGoods_gid() != null && obj2.getGoods_gid().getGid() == goodid) {
                                        orderDetail.setDetailednumber(obj2.getDetailednumber() + buynum);
                                        orderDetail.setDetailedtotal(obj2.getDetailedtotal() + price);
                                        ordt.updateOrderDetails(orderDetail);
                                        break;
                                    }
                                }


                            }
                            break;
                        }
                    }
                }
            } else {
                //不存在订单
                ods.insertOrder(orders);
                Responese responese3 = ods.selectOrdersOIdByUserId(new Users(uid));
                Set<Orders> obj1 = (Set<Orders>) responese3.getObj();
                for (Orders odss :
                        obj1) {
                    OrderDetails orderDetails = new OrderDetails();
                    orderDetails.setDetailedname(goods.getGname());
                    orderDetails.setDetailednumber(buynum);
                    orderDetails.setGoodsprice(goods.getGprice());
                    orderDetails.setDetailedtotal(buynum * goods.getGprice());
                    orderDetails.setGoods_gid(goods);
                    orders.setOid(odss.getOid());
                    orderDetails.setOrder_oid(orders);
                    ordt.insertGoodsIntoOderDetails(orderDetails);
                    o = JSONObject.toJSON(new Responese(Enums.SUCCESS));
                }
            }
        } else {
            Object o = JSONObject.toJSON(new Responese(Enums.FAIL));
            resp.getWriter().write(o.toString());
        }
        //跳过购物车直接下达订单

    }

    private void showCart(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        List list = new ArrayList<>();
        HttpSession session = req.getSession();
        Map<String, String> user = (Map<String, String>) session.getAttribute("user");
        if (user.size() != 0) {
            Integer uid = Integer.valueOf(user.get("uid"));

            if (scs.existUid(uid)) {
                //存在購物車
                Responese responese = scs.selectShopCartbyUid(uid);
                ShopCart obj = (ShopCart) responese.getObj();
                Responese responese1 = scs.selectShopCartDetailsByCid(obj.getCid());
                Set<ShopCart> obj1 = (Set<ShopCart>) responese1.getObj();//获得用户已存在的购物车，之后修改gtime，gnumber
                for (ShopCart sc :
                        obj1) {
                    Goods goods = gs.getGoodsDataById(sc.getG().getGid());
                    GoodsPicture goodsPicture = gp.selectByGId(sc.getG().getGid());
                    goods.setGp(goodsPicture);
                    sc.setG(goods);
                    list.add(sc);
                }
                req.getSession().setAttribute("carts", list);
                resp.sendRedirect("/shopping_test/shopping.jsp");
            } else {
                //沒有購物車
                req.getSession().removeAttribute("carts");
                resp.sendRedirect("/shopping_test/shopping.jsp");
            }
        } else {
            //沒有購物車
            resp.sendRedirect("/shopping_test/shopping.jsp");
        }
    }
}
