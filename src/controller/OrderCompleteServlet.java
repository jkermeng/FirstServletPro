package controller;

import enums.Responese;
import onetomanyentity.Orders;
import onetooneentity.Users;
import service.OrderService;
import service.OrderdetailedService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@WebServlet(name = "OrderCompleteServlet", urlPatterns = "/orders_view")
public class OrderCompleteServlet extends HttpServlet {
    private OrderService ods = new OrderService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, String> user = (Map<String, String>) request.getSession().getAttribute("user");
        Integer uid = Integer.valueOf(user.get("uid"));
        Responese responese = ods.selectOrdersDataByUserId(new Users(uid));
        if (responese.getCode() == 1) {
            List<Orders> obj = (List<Orders>) responese.getObj();
            request.getSession().setAttribute("orderView", obj);
        } else {
            //´íÎó´úÂë
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, String> user = (Map<String, String>) request.getSession().getAttribute("user");
        Integer uid = Integer.valueOf(user.get("uid"));
        Responese responese = ods.selectOrdersDataByUserId(new Users(uid));
        if (responese.getCode() == 1) {
            List<Orders> obj = (List<Orders>) responese.getObj();
            System.out.println(obj);
            request.getSession().setAttribute("orderView", obj);
            response.sendRedirect("/shopping_test/orders_view.jsp");
        } else {
            //´íÎó´úÂë
        }

    }
}
