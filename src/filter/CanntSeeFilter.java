package filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class CanntSeeFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        String remoteHost = req.getRemoteHost();

        HttpServletRequest request = (HttpServletRequest) req;
        String error = req.getParameter("pwd");
        if (error == null) {
            request.setAttribute("Error", "瞎看?已经记录您的ip，您将受到法律的制裁！！！！Ip:" + remoteHost);
            request.getRequestDispatcher("/Error.jsp").forward(req, resp);
        } else {
            chain.doFilter(req, resp);

        }
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
