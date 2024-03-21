/**
 * 
 */
package com.heima.wemedia.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.heima.wemedia.interceptor.WmTokenInterceptor;

/**
 * 
 */
@Configuration
public class WebMvcConfig  implements WebMvcConfigurer{
    
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new WmTokenInterceptor()).addPathPatterns("/**");
	}
	
}
