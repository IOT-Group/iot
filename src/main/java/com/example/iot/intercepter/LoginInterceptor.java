package com.example.iot.intercepter;

import com.example.iot.vo.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * an interceptor for login
 * @author Debonex
 * @date 2020年6月17日
 */
public class LoginInterceptor implements HandlerInterceptor {

    /**
     * page paths to check
     */
    public static final String[] checkPages = {"index"};

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        HttpSession session = request.getSession();
        String contextPath = session.getServletContext().getContextPath();
        String uri = request.getRequestURI();


        //检查是否是静态资源
        if(StringUtils.startsWith(uri,"/static"))return true;

        String page = StringUtils.remove(uri,contextPath+"/");

        boolean toIntercept = false;
        for(String checkPage:checkPages){
            if(StringUtils.startsWith(page,checkPage)){
                toIntercept = true;
                break;
            }
        }

        if(toIntercept){
            User requestUser = (User)request.getAttribute("user");
            if(requestUser==null){
                response.sendRedirect("#/login");
                System.out.println("redirect");
                return false;
            }
        }
        return true;
    }

}
