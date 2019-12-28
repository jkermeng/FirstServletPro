package filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Set;

public class Filter1 implements Filter {

    public void destroy() {
        System.out.println("Filter destroy.....");
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        System.out.println("/user/goods doFilter.....");
//        resp.setContentType("html/text;chartset=uft-8");

        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {
        System.out.println("Filter init....");
    }

}
