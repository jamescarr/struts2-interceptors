package org.jamescarr.struts2.interceptor;

import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ApplicationAware;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class BeanContextAware extends AbstractInterceptor implements
		ApplicationAware {

	private Map contextMap;

	public String intercept(ActionInvocation invocation) throws Exception {
		ApplicationContext context = WebApplicationContextUtils
				.getWebApplicationContext(ServletActionContext
						.getServletContext());
		for (String beanName : context.getBeanDefinitionNames()) {
			contextMap.put(beanName, context.getBean(beanName));
		}
		return invocation.invoke();
	}

	public void setApplication(Map contextMap) {
		this.contextMap = contextMap;

	}

}
