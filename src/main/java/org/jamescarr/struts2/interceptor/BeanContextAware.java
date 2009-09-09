package org.jamescarr.struts2.interceptor;

import java.util.Map;

import org.apache.struts2.interceptor.ApplicationAware;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class BeanContextAware extends AbstractInterceptor implements ApplicationContextAware, ApplicationAware{

	private ApplicationContext context;
	private Map contextMap;

	public String intercept(ActionInvocation invocation) throws Exception {
		for(String beanName : context.getBeanDefinitionNames()){
			contextMap.put(beanName, context.getBean(beanName));
		}
		return invocation.invoke();
	}

	public void setApplicationContext(ApplicationContext context)
			throws BeansException {
				this.context = context;
		
	}

	public void setApplication(Map contextMap) {
		this.contextMap = contextMap;
		
	}

}
