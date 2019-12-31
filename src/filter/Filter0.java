package filter;

import enums.Responese;
import onetooneentity.Users;
import service.UserService;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Filter0 implements Filter {
    int i = 0;
    private UserService userService = new UserService();

    public void destroy() {
        System.out.println("goods Destroy");
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        System.out.println("* doFilter.....");
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        Map<String, String> user = (Map<String, String>) request.getSession().getAttribute("user");
        if (user == null || user.size() == 0) {
            Cookie[] cookies = request.getCookies();
            if (cookies != null && cookies.length != 0) {
//                request.getSession().setAttribute("add", add);
                for (Cookie ck :
                        cookies) {
                    if (ck.getName().equals("login")) {
                        String name = ck.getValue();
                        if (name == null) {
                            response.sendRedirect("/shopping_test/login.jsp");
                        } else {
                            String uname = name.substring((name.indexOf("=") + 1), name.indexOf("_"));
                            String pwd = name.substring(name.lastIndexOf("=") + 1);
                            Responese login = userService.Login(uname, pwd);
                            if (login.getCode() == 1) {
                                Users obj = (Users) login.getObj();
                                Map<String, String> usermap = new HashMap();
                                usermap.put("user_name", obj.getUname());
                                usermap.put("uid", String.valueOf(obj.getUid()));
                                request.getSession().setAttribute("user", usermap);

                                break;
//                                chain.doFilter(req, resp);
//                                destroy();
                            }
                        }
                    }
                }
            }
        }
        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
