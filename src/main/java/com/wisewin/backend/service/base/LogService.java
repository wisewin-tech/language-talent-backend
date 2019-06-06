package com.wisewin.backend.service.base;


import com.wisewin.backend.util.redisUtils.RedissonHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * 开关预设Redis key=log value=true
 */
@Service("LogService")
public class LogService {

    private static final Logger logger = LoggerFactory.getLogger(LogService.class);
    private static final String SEPARATOR="\t";

    /**
     * 开始执行Controller
     */
    public <T,V> void startController(T user, HttpServletRequest request,Object... args){
        if(onOff()){
            StringBuffer  buffer=new StringBuffer("startExecuteController").append(SEPARATOR)
                    .append(request.getRemoteAddr()).append(SEPARATOR).append(user).append(SEPARATOR)
                    .append(request.getRequestURL()).append(SEPARATOR).append(joint(args))
                    .append(Thread.currentThread().getName());
            logger.info(buffer.toString());
        }
    }



    /**
     * 开始执行service
     * @param funName 方法名称
     * @param args
     */
    public void serviceStart(String funName,Object... args){
        if(onOff()){
            StringBuffer  buffer=new StringBuffer("startExecuteService").append(SEPARATOR)
                    .append(funName).append(SEPARATOR).append(joint(args)).append(SEPARATOR)
                    .append(Thread.currentThread().getName());
            logger.info(buffer.toString());
        }
    }

    /**
     * Service 调用
     * @param callName
     * @param args
     */
    public void call(String callName,Object... args){
        if(onOff()){
            StringBuffer  buffer=new StringBuffer("call").append(SEPARATOR)
                    .append(callName).append(SEPARATOR).append(joint(args)).append(SEPARATOR)
                    .append(Thread.currentThread().getName());
            logger.info(buffer.toString());
        }
    }

    /**
     * 返回结果
     * @param args
     */
    public void result(Object... args){
        if(onOff()){
            StringBuffer  buffer=new StringBuffer("result").append(SEPARATOR).append(joint(args))
                    .append(SEPARATOR).append(Thread.currentThread().getName());
            logger.info(buffer.toString());
        }
    }


    /**
     * 方法结束
     * @param funName 方法名称
     * @param args
     */
    public void end(String funName ,Object... args){
        if(onOff()){
            StringBuffer  buffer=new StringBuffer("end").append(SEPARATOR).append(funName).
                    append(SEPARATOR).append(joint(args)).append(SEPARATOR).append(Thread.currentThread().getName());
            logger.info(buffer.toString());
        }
    }


    /**
     * 自定义
     */
    public void custom(Object... args){
        if(onOff()){
            StringBuffer  buffer=new StringBuffer().append(joint(args)).append(Thread.currentThread().getName());
            logger.info(buffer.toString());
        }
    }

    /**
     * 判断开关
     * @return
     */
    private boolean  onOff(){
        String flag =(String) RedissonHandler.getInstance().get("log");
        if(flag!=null && flag.equals("true"))
            return true;
        return false;
    }

    /**
     * 拼接参数
     * @param args
     * @return
     */
    public String joint(Object... args){
        if(args!=null){
            StringBuffer buffer=new StringBuffer("");
            for(int i=0;i<args.length;i++){
                buffer.append(args[i]).append(SEPARATOR);
            }
            return buffer.toString().trim();
        }
        return "";
    }


}
