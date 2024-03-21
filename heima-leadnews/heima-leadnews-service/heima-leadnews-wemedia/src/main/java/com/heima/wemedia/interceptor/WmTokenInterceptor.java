package com.heima.wemedia.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.heima.model.wemedia.pojos.WmUser;
import com.heima.utils.thread.WmThreadLocalUtil;

public class WmTokenInterceptor implements HandlerInterceptor {
	
	
	/**
	 * header の中のユーザー情報を取得し、スレッドに保存する
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		String userId = request.getHeader("userId");
		if(userId != null) {
			//スレッドに保存する
			WmUser wmUser = new WmUser();
			wmUser.setId(Integer.valueOf(userId));
			WmThreadLocalUtil.setUser(wmUser);
			
		}
		return true;
	}
	
	
	/**
	 * スレッド内のデータを削除する
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		WmThreadLocalUtil.clear();
	}

}
