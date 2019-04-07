package com.wisewin.backend.util.message;

import com.github.qcloudsms.SmsSingleSender;
import com.github.qcloudsms.SmsSingleSenderResult;
import org.apache.log4j.Logger;

import java.util.ArrayList;

/**
 * Created by Administrator on 2018/3/27.
 */
public class SendMessageUtil {
    private final static Logger log = Logger.getLogger(SendMessageUtil.class);

    private static final int APPID = 1400077931;
    private static final String APPKEY = "f0f83079a2c7fd091a039a04bc260741";

    public static void sendSignInCodeMessage(String mobile, String content){
        try {
            SmsSingleSender sender = new SmsSingleSender(APPID, APPKEY);
            ArrayList<String> params = new ArrayList<String>();
            params.add(content);
            SmsSingleSenderResult result = sender.sendWithParam("86", mobile, 98018, params, "", "", "");
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String args[]) throws Exception {
        SmsSingleSender sender = new SmsSingleSender(APPID, APPKEY);
        ArrayList<String> params = new ArrayList<String>();
        params.add("123456");
        SmsSingleSenderResult result = sender.sendWithParam("86", "17610895083", 98018, params, "", "", "");
        System.out.println(result);
    }
}
