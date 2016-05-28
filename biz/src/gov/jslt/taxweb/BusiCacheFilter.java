package gov.jslt.taxweb;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BusiCacheFilter implements Filter {

	public void init(FilterConfig filterConfig) {

	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		String uri = req.getRequestURI();
		if (uri.indexOf("jquery") != -1 || uri.indexOf(".css") != -1
				|| uri.indexOf(".gif") != -1 || uri.indexOf(".jpg") != -1
				|| uri.indexOf("ace") != -1 || uri.indexOf("angular") != -1
				|| uri.indexOf("bootstrap") != -1
				|| uri.indexOf("footable") != -1 || uri.indexOf("ionic") != -1
				|| uri.indexOf(".png") != -1) {
			res.setHeader("Expires", "Sat, 01 Jan 2022 02:38:56 GMT");
			res.setHeader("Cache-Control", "max-age=315360000");
		} else {
			res.setHeader("Cache-Control", "no-cache");
			res.setHeader("Pragma", "no-cache");
			res.setDateHeader("Expires", 0);
		}
		chain.doFilter(request, response);
	}

	public void destroy() {
	}

}
