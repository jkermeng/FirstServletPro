package service;


import dao.imp.*;
import enums.Enums;
import enums.Responese;
import mtomentity.OrderDetails;
import onetomanyentity.Goods;
import onetomanyentity.Orders;
import onetomanyentity.ShopCart;
import onetooneentity.Users;

import java.sql.SQLException;
import java.util.Set;

public class ShopCartService {
    private UsersDaoIMP udimp = new UsersDaoIMP();
    private ShopCartDaoIMP scdimp = new ShopCartDaoIMP();
    private OrderDetailsIMP odsimp = new OrderDetailsIMP();
    private GoodsDaoIMP gdimp = new GoodsDaoIMP();
    private OrdersDaoIMP odimp = new OrdersDaoIMP();

    public Responese deleteByCidAndGid(Integer cid, Integer gid) {
        Responese responese = new Responese(Enums.SUCCESS);
        scdimp.deleteByCidAndGid(cid, gid);
        if (!scdimp.existCid(cid)) {
            //relation_4没有cid了删除shoocart中的cid
            scdimp.delete(cid);
        }
        return responese;
    }

    public Responese updateShopCartDetails(ShopCart shopCart) {
        Responese responese = new Responese(Enums.SUCCESS);
        scdimp.updateShopCartGoods(shopCart);
        return responese;
    }

    public Boolean existGid(Integer Gid) {
        return scdimp.existGid(Gid);
    }

    public Boolean existGidAndCid(Integer Gid, Integer Cid) {
        return scdimp.existGidAndCid(Gid, Cid);
    }


    public Boolean existUid(Integer uid) {
        return scdimp.existUid(uid);
    }

    public Responese selectShopCartbyUid(Integer uid) {
        Responese responese = new Responese(Enums.SUCCESS);
        if (uid != null) {
            ShopCart shopCart = scdimp.selectShopCartbyUid(uid);
            responese.setObj(shopCart);
            return responese;
        } else {
            return new Responese(Enums.FAIL);
        }
    }

    public Responese insertShopCartByUid(Integer uid) {
        Responese responese = new Responese(Enums.SUCCESS);
//        ShopCart shopCart = new ShopCart();
//        shopCart.setUse(new Users(uid));
//        scdimp.insertShoppingCart(shopCart);
        return responese;
    }


    public Responese selectShopCartDetailsByCid(Integer cid) {
        Responese responese = new Responese(Enums.SUCCESS);
        Set<ShopCart> shopCarts = scdimp.selectShopCarstbyCid(cid);
        responese.setObj(shopCarts);
        return responese;
    }

    public Responese insertGoodToShoppingCart(ShopCart shopCart) {
        Responese responese = null;
        responese = new Responese(Enums.SUCCESS);

        shopCart.setCid(shopCart.getUse().getUid());
        scdimp.insertGoodsToShoppingCart(shopCart);

        return responese;
    }

    public Responese insertGoodsToShoppingCart(ShopCart shopCart) {
        Responese responese = null;
        if (shopCart != null) {
            if (!scdimp.existUid(shopCart.getUse().getUid())) {
                //没有存在购物车
                shopCart.setCid(shopCart.getUse().getUid());
                scdimp.insertShoppingCart(shopCart);
                scdimp.insertGoodsToShoppingCart(shopCart);
                responese = new Responese(Enums.SUCCESS);
            } else {
                //已经存在购物车
                ShopCart shopCart1 = scdimp.selectShopCartbyUid(shopCart.getUse().getUid());
                shopCart.setCid(shopCart1.getCid());
                scdimp.updateShopCartGoods(shopCart);
                responese = new Responese(Enums.SUCCESS);
            }
        } else {
            responese = new Responese(Enums.FAIL);
            return responese;
        }
        return responese;
    }
//	public Responese getUserShopCartGoodsData(Users uid) throws SQLException {
//		ShopCart cart = new ShopCart();
//		Responese rs = new Responese(Enums.SUCCESS);
//		Users selectUsersById = udimp.selectUsersById(uid.getUid());
//		Set<ShopCart> selectGoodsByUsersId = scdimp.selectGoodsByUsersId(selectUsersById.getUid());
//		rs.setObj(selectGoodsByUsersId);
//		return rs;
//	}

//    public void addShopCartGoods(int id, int num, Users u) throws SQLException {
//        Goods selectById = gdimp.selectById(id);
//        OrderDetails orderDetails = new OrderDetails(selectById.getGname(), num, selectById.getGprice(),
//                num * selectById.getGprice());
//        // 用户的id
//        odimp.selectByUserId(u.getUid());
//        Orders selectByUserId = odimp.selectByUserId(u.getUid());
//        orderDetails.setOrder_oid(selectByUserId);
//        odsimp.insert(orderDetails);
//
//    }
//
//    public Responese updateShopCartGoods(int id, int num, Users u) throws SQLException {
//        OrderDetails ods = new OrderDetails();
//        Orders selectByUserId = odimp.selectByUserId(u.getUid());
//        OrderDetails selectByGid = odsimp.selectByGid(id);
//        selectByGid.setOrder_oid(selectByUserId);
//        selectByGid.setDetailednumber(num);
//        odsimp.update(selectByGid);
//        return new Responese(Enums.SUCCESS);
//
//    }

//    public Responese deleteShopCartGoods(int id, Users u) throws SQLException {
//        OrderDetails selectByGid = odsimp.selectByGid(id);
//        Orders selectByUserId = odimp.selectByUserId(u.getUid());
//        selectByGid.setOrder_oid(selectByUserId);
//        odsimp.delete(selectByGid);
//        return new Responese(Enums.SUCCESS);
//
//    }
//
//    public Responese countMyShopCart(Users u) throws SQLException {
//        Orders selectByUserId = odimp.selectByUserId(u.getUid());
//        selectByUserId.setOstatus("已结算");
//        odimp.update(selectByUserId);
//        return new Responese(Enums.SUCCESS);
//
//    }

}
