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
//        ��ҳ���ȡ��Ż�ҳ��
        String src = request.getParameter("src");//��ת��Դ
        Integer page = Integer.valueOf(request.getParameter("page"));//��ǰѡ��ҳ��
        Integer hpcId = Integer.valueOf(request.getParameter("hpcId"));//����������
        String qname = request.getParameter("qname");//��������
        PageModel pageModel = gs.pageGoods(4, page);
        pageModel.setSource(src);//ʲô����Դ
        pageModel.setHpcId("" + hpcId);//������ҳ��id
        pageModel.setQname(qname);//���������
        request.setAttribute("pageModel", pageModel);
        request.getRequestDispatcher("/index.jsp").forward(request, response);
//        response.sendRedirect(src);


    }
}
