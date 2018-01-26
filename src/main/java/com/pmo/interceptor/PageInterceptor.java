package com.pmo.interceptor;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * Page Interceptor.
 *
 * @author Fred Mao
 *
 */
public class PageInterceptor extends HandlerInterceptorAdapter
{
    /*@Override
    public boolean preHandle(HttpServletRequest request,
            HttpServletResponse response, Object handler) throws Exception
    {
        String requestUri = request.getRequestURI();

        final Employee emp = (Employee) request.getSession()
                .getAttribute("employee");
        if (emp != null && requestUri.indexOf("service/manage/login") > 0)
        {
            request.getRequestDispatcher("/service/employee/index")
                    .forward(request, response);
            return false;
        }
        if (noFilter(requestUri))
        {
            return true;
        }

        if (emp == null && requestUri.indexOf("/service/") >= 0)
        {
            request.getRequestDispatcher("/service/manage/logout")
                    .forward(request, response);
            return false;
        }
        else
        {
            return true;
        }
    }*/

    
    public boolean noFilter(String requestUri)
    {
        if (requestUri.indexOf("service/manage/loginPage") > 0
                || requestUri.indexOf("service/manage/logout") > 0
                || requestUri.indexOf("service/manage/login") > 0
                || requestUri.indexOf("WEB-INF/page") > 0
                || requestUri.indexOf("service/employee/index.html") > 0
                || requestUri.indexOf("service/syncEmployInfo") > 0
        		)
        {
        	System.out.println("页面拦截器======================");
        	return true;
        }
        else
        {
            return false;
        }
    }
}