package gov.jslt.taxweb;

import java.util.HashMap;
import java.util.Map;

import com.ctp.core.config.IConfigContext;

public class RuleFilterContext implements IConfigContext {
	/**
	 */
	private Map configCTXPool = new HashMap();

	public RuleFilterContext() {
	}

	/**
	 * 属性 configCTXPool 的 setter 方法
	 * 
	 * @param pool
	 */
	public void setCTXPool(Map pool) {
		this.configCTXPool = pool;
	}

	/**
	 * 根据参数表中的键值,取出具体的参数值
	 * 
	 * @param keyid
	 *            参数表中的键值
	 * @return 具体的参数值
	 */
	public Object getValue(String keyid) {
		if (configCTXPool == null)
			return null;
		return configCTXPool.get(keyid);
	}

	public Map getConfigCTXPool() {
		return configCTXPool;
	}

	public void setConfigCTXPool(Map configCTXPool) {
		this.configCTXPool = configCTXPool;
	}
}
