package controller;

import jdbcutil.MySqlConnection;

import javax.servlet.*;
import java.io.IOException;

public class InitServlet implements Servlet {
    @Override
    public void init(ServletConfig servletConfig) throws ServletException {

    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {

    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy()
    {
        System.out.println("摧毀所有数据库连接");
        MySqlConnection.closeAll();
    }
}
