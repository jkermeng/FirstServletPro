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

@WebServlet(name = "retrievePassWordServlet", urlPatterns = "/retrievePassWordServlet")
public class retrievePassWordServlet extends HttpServlet {
    private UserService us = new UserService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String parameter = request.getParameter("remail");
        String newpwd = request.getParameter("newpwd");
        String userName = request.getParameter("userName");
        Responese responese = us.modifyUserByUnameAndUemail(userName, parameter, newpwd);
        if (responese.getCode() == 1) {
            response.sendRedirect("/shopping_test/back.jsp");
        } else {
            //¥ÌŒÛÃ· æ
            response.sendRedirect("/shopping_test/back.jsp");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
