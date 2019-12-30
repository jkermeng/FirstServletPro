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
        if (parameter != null && newpwd != null && userName != null) {
            if (newpwd.equals("") && newpwd != null) {
                Responese responese = us.selectUserByUnameAndUemail(userName, parameter);
                if (responese.getCode() == 1) {
                    String obj = (String) responese.getObj();
                    request.setAttribute("pwd", obj);
                    request.getRequestDispatcher("/back.jsp").forward(request, response);
                    response.sendRedirect("/shopping_test/back.jsp");
                } else {
                    request.setAttribute("Error", "您有一项输入没有正确！！！");
                    request.getRequestDispatcher("/Error.jsp").forward(request, response);
                }

            } else {
                Responese responese = us.modifyUserByUnameAndUemail(userName, parameter, newpwd);
                if (responese.getCode() == 1) {
                    request.setAttribute("pwd", "新密码:" + newpwd);
                    request.getRequestDispatcher("/back.jsp").forward(request, response);
                    response.sendRedirect("/shopping_test/back.jsp");
                } else {
                    //错误提示
                    request.setAttribute("Error", "您什么都没有输入！！！");
                    request.getRequestDispatcher("/Error.jsp").forward(request, response);
                }
            }

        } else {
            request.setAttribute("Error", "您什么都没有输入！！！");
            request.getRequestDispatcher("/Error.jsp").forward(request, response);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
