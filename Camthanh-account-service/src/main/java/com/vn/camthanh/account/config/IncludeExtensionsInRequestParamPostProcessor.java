package com.vn.camthanh.account.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

@Configuration
public class IncludeExtensionsInRequestParamPostProcessor implements BeanPostProcessor {
	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		if (bean instanceof RequestMappingHandlerMapping) {
			RequestMappingHandlerMapping mapping = (RequestMappingHandlerMapping) bean;
			mapping.setUseRegisteredSuffixPatternMatch(false);
			mapping.setUseSuffixPatternMatch(false);
		}
		return bean;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) {
		return bean;
	}
}
