package controller;

import dao.imp.GoodsPictureDaoIMP;
import onetomanyentity.Goods;
import onetomanyentity.GoodsPicture;
import service.GoodService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "PviewServlet", urlPatterns = "/pview")
public class PviewServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pid = request.getParameter("pId");
        GoodService gs = new GoodService();
        GoodsPictureDaoIMP gp = new GoodsPictureDaoIMP();
        Goods goodsDataById = gs.getGoodsDataById(Integer.valueOf(pid));
        GoodsPicture goodsPicture = gp.selectByGId(Integer.valueOf(pid));
        goodsDataById.setGp(goodsPicture);
        request.getSession().setAttribute("good", goodsDataById);
        response.sendRedirect("/shopping_test/product_view.jsp");

    }
}
