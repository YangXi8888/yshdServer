package gov.jslt.taxweb;

import gov.jslt.taxevent.comm.GeneralCons;
import gov.jslt.taxevent.comm.JsonResData;
import gov.jslt.taxevent.comm.LoginVO;

import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONSerializer;

import com.ctp.core.config.ApplicationContext;
import com.ctp.core.log.LogWritter;

public class RuleFilter implements Filter {

	public void destroy() {

	}

	public void doFilter(ServletRequest servletRequest,
			ServletResponse servletResponse, FilterChain filterChain) {
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		try {
			HttpServletResponse response = (HttpServletResponse) servletResponse;
			HttpSession session = request.getSession();
			String strServletPath = request.getRequestURI();
			if (strServletPath.equals("")) {
				System.out.println("空。。。。。。。。。==============================="
						+ strServletPath);
				filterChain.doFilter(servletRequest, servletResponse);
				return;
			}
			RuleFilterContext ruleContext = (RuleFilterContext) ApplicationContext
					.singleton().getValue("biz.wsbs.rulefilter");
			List<String> listServletPath = (List<String>) (ruleContext
					.getConfigCTXPool().get("rulefilter"));
			for (int i = 0; i < listServletPath.size(); i++) {
				if (strServletPath.equals((String) listServletPath.get(i))) {
					System.out.println("符合允许直接访问的地址:" + listServletPath.get(i));
					LogWritter
							.sysError("符合允许直接访问的地址:" + listServletPath.get(i));
					filterChain.doFilter(servletRequest, servletResponse);
					return;
				}
			}
			System.out.println("用户请求的地址;" + strServletPath);
			LogWritter.sysError("用户请求的地址;" + strServletPath);
			try {
				LoginVO loginVO = (LoginVO) session.getAttribute(request
						.getParameter("sessionId"));
				System.out.println("当前访问用户名称为:" + loginVO.getXm() + ";身份证件号码:"
						+ loginVO.getZjHm());
				LogWritter.sysError("当前访问用户名称为:" + loginVO.getXm() + ";身份证件号码:"
						+ loginVO.getZjHm());
				filterChain.doFilter(servletRequest, servletResponse);
			} catch (Exception e) {
				JsonResData resData = new JsonResData();
				resData.setCode(GeneralCons.ERROR_CODE_ZB0036);
				resData.setMsg("会话已经失效，请重新登录");
				response.setContentType("text/plain;charset=UTF-8");
				response.getWriter().print(
						JSONSerializer.toJSON(resData).toString());
				System.out.println("会话已经失效，请重新登录.");
				LogWritter.sysError("会话已经失效，请重新登录.");
				response.getWriter().flush();
			}

		} catch (Exception e) {
		}
	}

	public void init(FilterConfig filterConfig) throws ServletException {

	}

}