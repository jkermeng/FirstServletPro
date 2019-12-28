package controller;

import com.alibaba.fastjson.JSONObject;
import enums.Responese;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "CheckUserServlet", urlPatterns = "/CheckUser")
public class CheckUserServlet extends HttpServlet {
    private UserService us = new UserService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userName = request.getParameter("userName");//用户名称  查询真实姓名是否符合
        if (userName != null) {
            Responese responese = us.selectUserByUname(userName);
            Object o = JSONObject.toJSON(responese);
            response.getWriter().write(o.toString());
        } else {

        }
        String email = request.getParameter("email");//用户邮箱
        if (email != null) {
            Responese responese1 = us.existUemail(email);
            Object o1 = JSONObject.toJSON(responese1);
            response.getWriter().write(o1.toString());
        } else {

        }
        String phone = request.getParameter("phone");
        if (phone != null) {
            Responese responese = us.existUphone(phone);
            Object o1 = JSONObject.toJSON(responese);
            response.getWriter().write(o1.toString());
        } else {

        }
//        request.getParameter("phone");//真实姓名

//        request.getParameter("");//用户联系电话

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
