package gov.jslt.taxevent.comm;

public class GeneralCons {

	/**
	 * ACTION跳转当前页面
	 */
	public static final String CURRENT_FORWARD = "current";

	/**
	 * ACTION跳转成功页面
	 */
	public static final String SUCCESS_FORWARD = "default_success";

	/**
	 * ACTION跳转错误页面
	 */
	public static final String ERROR_FORWARD = "default_error";

	/**
	 * 失败
	 */
	public static final String ERROR_STR = "error";

	public static final String SUCCESS_CODE = "0";

	/**
	 * 验证码已失效
	 */
	public static final String ERROR_CODE_ZB0001 = "ZB0001";

	/**
	 * 用户名或密码错误
	 */
	public static final String ERROR_CODE_ZB0002 = "ZB0002";
	public static final String ERROR_CODE_ZB0003 = "ZB0003";
	public static final String ERROR_CODE_ZB0004 = "ZB0004";
	public static final String ERROR_CODE_ZB0006 = "ZB0006";
	public static final String ERROR_CODE_ZB0014 = "ZB0014";
	public static final String ERROR_CODE_ZB9999 = "ZB9999";
	public static final String ERROR_CODE_ZB0021 = "ZB0021";
	public static final String ERROR_CODE_ZB0022 = "ZB0022";
	public static final String ERROR_CODE_ZB0023 = "ZB0023";
	// 此申报表不满足再次扣款条件。报表状态扣款失败，金额大于0才允许再次扣款！
	public static final String ERROR_CODE_ZB0024 = "ZB0024";
	/**
	 * 此证件号码已做过自然人登记，不可重复登记
	 */
	public static final String ERROR_CODE_ZB0026 = "ZB0026";
	/**
	 * 未查询到注册信息，请输入正确的注册手机号码！
	 */
	public static final String ERROR_CODE_ZB0027 = "ZB0027";
	/**
	 * 未查询到扣款失败的申报记录
	 */
	public static final String ERROR_CODE_ZB0028 = "ZB0028";
	/**
	 * 用户信息异常，未查询到注册信息！
	 */
	public static final String ERROR_CODE_ZB0029 = "ZB0029";
	/**
	 * 验证码错误，请重新获取验证码
	 */
	public static final String ERROR_CODE_ZB0016 = "ZB0016";
	/**
	 * 证件号码错误，请输入正确的18位身份证号码
	 */
	public static final String ERROR_CODE_ZB0030 = "ZB0030";
	/**
	 * 已登记15位身份证号，请变更自然人登记信息为18位身份证号
	 */
	public static final String ERROR_CODE_ZB0031 = "ZB0031";

	/**
	 * 会话已经失效，请重新登录
	 */
	public static final String ERROR_CODE_ZB0036 = "ZB0036";

	public static final String SUCCESS_MSG_ZB7777 = "´错误不明";

	public static final String SUCCESS_MSG = "处理成功";

	public static final String CODE = "code";

	public static final String MSG = "msg";

}