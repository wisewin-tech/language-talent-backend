package com.wisewin.backend.web.support.interceptor;

import com.google.common.collect.Sets;
import com.wisewin.backend.common.constants.SysConstants;
import com.wisewin.backend.entity.bo.AdminBO;
import com.wisewin.backend.entity.dto.ResultDTOBuilder;
import com.wisewin.backend.util.JsonUtils;
import com.wisewin.backend.web.controller.base.BaseCotroller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
* Authentication拦截器
* @author Felix Lee
* @date 2015/1/12
*/
public class AuthInterceptor extends HandlerInterceptorAdapter {

    private static final Logger logger = LoggerFactory.getLogger(AuthInterceptor.class);
      private BaseCotroller baseCotroller=new BaseCotroller() ;

    // 不需要过滤的URL
    public static final Set<String> unCheckSet = Sets.newHashSet("/admin/adminLogin","/chapter/selectChapterById","/leavel/queryCourseChoice",
            "/course/selectChapterById","/Language/queryLanguageChoice","/question/test","/upFile/upFile","/voucher/getAddress","/test/test",
            "/voucher/refreshAddress","/course/queryCourseChoice") ;

 //   public static final Set<String> CheckListForAjax = Sets.newHashSet("/client/login" , "/apiCourse/toDetail" ) ;


//
    /**
     * 在业务处理器处理请求之前被调用
     * 如果返回false
     *     从当前的拦截器往回执行所有拦截器的afterCompletion(),再退出拦截器链
     * 如果返回true
     *    执行下一个拦截器,直到所有的拦截器都执行完毕
     *    再执行被拦截的Controller
     *    然后进入拦截器链,
     *    从最后一个拦截器往回执行所有的postHandle()
     *    接着再从最后一个拦截器往回执行所有的afterCompletion()
     */
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {

        String uri = this.getInvokeMethod(request);
        if(unCheckSet.contains(uri)){
            return true;
        }
//        AdminBO loginAdmin = baseCotroller.getLoginAdmin(request);
//        if(loginAdmin==null){
//            String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000002")) ;
//            baseCotroller.safeJsonPrint(response,result);
//            return false;
//        }
//
//
//        if(loginAdmin.getUrl().contains(uri)){
//            return true;
//        }else{
//            String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000009")) ;
//            baseCotroller.safeJsonPrint(response,result);
//            return false;
//        }
    return  true;

    }

    /**
     * 在业务处理器处理请求执行完成后,生成视图之前执行的动作
     * 可在modelAndView中加入数据，比如当前时间
     */
    @Override
    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
//        System.out.println("==============执行顺序: 2、postHandle================");
//        if(modelAndView != null){  //加入当前时间
//            modelAndView.addObject("var", "测试postHandle");
//        }
    }

    /**
     * 在DispatcherServlet完全处理完请求后被调用,可用于清理资源等
     *
     * 当有拦截器抛出异常时,会从当前拦截器往回执行所有的拦截器的afterCompletion()
     */
    @Override
    public void afterCompletion(HttpServletRequest request,  HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
         if(ex!=null){
            logger.error("Exception:",ex);
         }
    }

    /**
     * 获取请求的URL
     * @param request
     * @return

    public String getUrl (HttpServletRequest request) {
        StringBuffer url = new StringBuffer(request.getRequestURL()) ;
        Iterator parameters = request.getParameterMap().entrySet().iterator() ;

        synchronized (this) {
            boolean firstFlag = true ;
            while (parameters.hasNext()) {
                Map.Entry<String , String[]> e = (Map.Entry<String , String[]>) parameters.next();
                if (firstFlag) {
                    url.append("?" + e.getKey()+"="+e.getValue()[0]) ;
                    firstFlag =  false ;
                }else {
                    url.append("&" + e.getKey()+"="+e.getValue()[0]) ;
                }
            }
        }

        return  url.toString() ;
    }
  */
    /**
     * 获取调用的方法
     * @param request
     * @return
     */
    public String getInvokeMethod (HttpServletRequest request) {


        String uTemp = request.getRequestURI();

        // 统一去掉路径中项目名
      /*  if (uTemp.startsWith("/localhost")) {
            uTemp = uTemp.replaceFirst("/localhost" , "");
        }
*/
        return uTemp ;
    }

    public String getUriAndParams (HttpServletRequest request) {

        String uTemp = request.getRequestURI();

        // 统一去掉路径中项目名
        if (uTemp.startsWith("/urbanfit-back-end-management")) {
            uTemp = uTemp.replaceFirst("/urbanfit-back-end-management" , "");
        }
        StringBuffer url = new StringBuffer(uTemp);
        Iterator parameters = request.getParameterMap().entrySet().iterator();

        synchronized (this) {
            boolean firstFlag = true;
            while (parameters.hasNext()) {
                Map.Entry<String, String[]> e = (Map.Entry<String, String[]>) parameters.next();
                if (firstFlag) {
                    url.append("?" + e.getKey() + "=" + e.getValue()[0]);
                    firstFlag = false;
                } else {
                    url.append("&" + e.getKey() + "=" + e.getValue()[0]);
                }
            }
        }
        return url.toString();
    }


    /**
     * 判断是否是ajax请求
     * @param request
     * @return
     */
    public boolean isAjaxRequest (HttpServletRequest request) {
        String xReq = request.getHeader("x-requested-with");
        if (xReq != null && !xReq.equals("") && "XMLHttpRequest".equalsIgnoreCase(xReq)) {
            // 是ajax异步请求
            return true;
        }
        return false;
    }


    /**
     *  修改上一次的请求列表
     * @param response
     * @return
     */
    public void setLastRequestUrl(HttpServletResponse response , String url) {

        setCookie(response , SysConstants.CURRENT_LOGIN_LAST_URL , url ,365 * 24 * 60 * 60);
    }

    public void setCookie(HttpServletResponse response, String key , String value , int expiry) {
        Cookie cookie = new Cookie(key, value);
        cookie.setMaxAge(expiry); //365 * 24 * 60 * 60
        cookie.setPath("/");
        response.addCookie(cookie);
    }



}
