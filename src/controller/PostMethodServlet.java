package controller;

import onetooneentity.Users;
import service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class PostMethodServlet {
    private UserService usdimp = new UserService();

    private void regist(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String user_name = req.getParameter("user_name");
        String rName = req.getParameter("rName");
        String gender = req.getParameter("gender");
        String regist_pwd = req.getParameter("regist_pwd");
        String confirm_pwd = req.getParameter("confirm_pwd");
        String phone = req.getParameter("phone");
        String email = req.getParameter("email");
        if (user_name != null && rName != null && gender != null && regist_pwd != null && confirm_pwd != null && phone != null && email != null) {
            if (user_name.equals("") && rName.equals("") && gender.equals("") && regist_pwd.equals("") && confirm_pwd.equals("") && phone.equals("") && email.equals("")) {
                if (regist_pwd.equals(confirm_pwd)) {
                    Users users = new Users(user_name, phone, gender, email, confirm_pwd);
                    users.setrName(rName);
                    usdimp.Register(users);
                    resp.getWriter().write("1");
                } else {
                    resp.getWriter().write("-1");
                }
            } else {
                resp.getWriter().write("-1");
            }
        } else {
            resp.getWriter().write("-1");
        }

    }

    private void settleAccounts(HttpServletRequest req, HttpServletResponse resp) {
        String uid = req.getParameter("uid");
        //结算功能，统计价格，移除购买货物数量，清空购物清单
        System.out.println(uid);
        System.out.println("*********结算功能启动************");


    }
}
