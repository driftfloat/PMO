package com.pmo.interceptor;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.pmo.dashboard.entity.User;
import com.pmo.dashboard.entity.UserAuthority;
import com.pom.dashboard.service.UserAuthorityService;

public class UserAuthorityInterceptor implements HandlerInterceptor {

	
	@Resource
	private UserAuthorityService userAuthorityService;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		HttpSession session = request.getSession();  
		User user = (User) session.getAttribute("loginUser"); 	
		if(user==null) {
 			return true;
		}
		List<UserAuthority> menus=null;
		List<UserAuthority> userMenus=null;
		if(menus==null) {
			menus=userAuthorityService.queryMenus();
			session.setAttribute("menus",menus);
			userMenus=userAuthorityService.queryUserAuthorityMenus(user.getUserId());
			session.setAttribute("userMenus",userMenus);
		}		
		
		String src=request.getRequestURL().toString();
		String url="";
		if(src.indexOf(".")==-1) {
			url=src.substring(src.indexOf("Pmo")+3, src.length());
		}else {
			url=src.substring(src.indexOf("Pmo")+3, src.indexOf("."));
		}
		
           if(ifExistMenu(menus,url)) {				
		       if(ifExistMenu(userMenus,url)) {
					return true;
				}else {
					request.getRequestDispatcher("/WEB-INF/page/welcome.jsp").forward(request, response); 			          
			        return false; 
				}				
			}else {
				return true;
			}

	
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub

	}
	
	static boolean ifExistMenu(List<UserAuthority> l,String url) {
		if(l==null||l.size()<1) {
			return false;
		}else {
			for (UserAuthority userAuthority : l) {
				if(userAuthority.getMenuUrl()!=null&&userAuthority.getMenuUrl().contains(url)) {
					return true;
				}
			}
			return false;
		}
		
	}

}
