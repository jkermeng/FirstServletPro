package controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import dao.imp.GoodsPictureDaoIMP;
import enums.Enums;
import enums.Responese;
import onetomanyentity.Goods;
import onetomanyentity.GoodsPicture;
import onetomanyentity._Classification;
import service.GoodService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CategoryServlet extends HttpServlet {
    private GoodService gs = new GoodService();
    private GoodsPictureDaoIMP gp = new GoodsPictureDaoIMP();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String cate = req.getParameter("cate");
        String hpcId = req.getParameter("hpcId");
        List<Goods> lg = new ArrayList<>();
        if (cate.equals("parent")) {
            Responese goodsByClassifyId = gs.getGoodsByClassifyId(Integer.valueOf(hpcId));
            if (goodsByClassifyId.getCode() == 1) {
                List<Goods> obj = (List<Goods>) goodsByClassifyId.getObj();
                for (Goods gs :
                        obj) {
                    GoodsPicture goodsPicture = gp.selectByGId(gs.getGid());
                    gs.setGp(goodsPicture);
                    lg.add(gs);
                }
                req.getSession().setAttribute("goods", lg);
                resp.sendRedirect("/shopping_test/product-list.jsp");
            } else {
                Object o = JSONObject.toJSON(new Responese(Enums.FAIL));
                resp.getWriter().write(o.toString());
            }
        } else {
            Object o = JSONObject.toJSON(new Responese(Enums.FAIL));
            resp.getWriter().write(o.toString());
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
