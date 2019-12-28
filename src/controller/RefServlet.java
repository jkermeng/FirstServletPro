package controller;

import entity.PageModel;
import service.GoodService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "RefServlet", urlPatterns = "/ref")
public class RefServlet extends HttpServlet {
    private GoodService gs = new GoodService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        把页码获取后放回页面
        String src = request.getParameter("src");//跳转资源
        Integer page = Integer.valueOf(request.getParameter("page"));//当前选中页数
        Integer hpcId = Integer.valueOf(request.getParameter("hpcId"));//不懂？？？
        String qname = request.getParameter("qname");//请求名称
        PageModel pageModel = gs.pageGoods(4, page);
        pageModel.setSource(src);//什么的资源
        pageModel.setHpcId("" + hpcId);//热门网页类id
        pageModel.setQname(qname);//请求的名称
        request.setAttribute("pageModel", pageModel);
        request.getRequestDispatcher("/index.jsp").forward(request, response);
//        response.sendRedirect(src);


    }
}
