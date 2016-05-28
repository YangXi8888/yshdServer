package gov.jslt.taxweb;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ctp.core.log.LogWritter;

public class ValidationFilter implements Filter {
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		resp.setHeader("X-Frame-Options", "SAMEORIGIN");
		Enumeration<?> params = req.getParameterNames();
		if (null != params) {
			String[] value;
			while (params.hasMoreElements()) {
				value = req.getParameterValues(params.nextElement().toString());
				for (int i = 0; i < value.length; i++) {
					if (!"".equals(value[i]) && !"null".equals(value[i])
							&& null != value[i]) {
						if (!validation(value[i])) {
							LogWritter.sysError(req.getRequestURI()
									+ "此链接含有非法字符(" + value[i] + ")，跳转至首页");
							resp.sendRedirect("/LoginAction.do");
							return;
						}
					}
				}
			}
			chain.doFilter(req, resp);
		} else {
			chain.doFilter(req, resp);
		}
	}

	public void destroy() {
	}

	protected static boolean validation(String str) {
		str = str.toLowerCase();
		String badStr = "script|exec|execute|insert|create|drop|from|grant|group_concat|column_name|"
				+ "information_schema|columns|table_schema|union|where|select|delete|update|order|count|"
				+ "chr|mid|master|truncate|char|declare|alert|all_source|alltable|like|exists|";
		String[] badStrs = badStr.split("\\|");
		for (int i = 0; i < badStrs.length; i++) {
			if (str.indexOf(badStrs[i]) != -1) {
				return false;
			}
		}
		return true;
	}

	public void init(FilterConfig arg0) throws ServletException {
	}

}
