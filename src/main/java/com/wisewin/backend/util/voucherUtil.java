package com.wisewin.backend.util;

import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.vod.model.v20170321.CreateUploadVideoRequest;
import com.aliyuncs.vod.model.v20170321.CreateUploadVideoResponse;
import com.aliyuncs.vod.model.v20170321.RefreshUploadVideoRequest;
import com.aliyuncs.vod.model.v20170321.RefreshUploadVideoResponse;

import java.util.HashMap;
import java.util.Map;

public class voucherUtil {

    private static final String accessKeyId = "LTAI0MJbyuPxtWRM";
    private static final String accessKeySecret = "HiErjIdNi2EXcZD2Qoz8ylUJTkAygV";

    //使用 账号AccessKey 初始化
    private static DefaultAcsClient initVodClient(String accessKeyId, String accessKeySecret) throws ClientException {
        String regionId = "cn-shanghai";  // 点播服务接入区域
        DefaultProfile profile = DefaultProfile.getProfile(regionId, accessKeyId, accessKeySecret);
        DefaultAcsClient client = new DefaultAcsClient(profile);
        return client;
    }



    private static CreateUploadVideoResponse createUploadVideo(DefaultAcsClient client,String title,String fileName) throws Exception {
        CreateUploadVideoRequest request = new CreateUploadVideoRequest();
        request.setTitle(title);
        request.setFileName(fileName);
        JSONObject extend = new JSONObject();
        extend.put("MyId", "user-defined-id");
        return client.getAcsResponse(request);
    }

    /**
     * 视频上传凭证
     * @return
     */
    public static Map<String,String> getUploadAddress(String title,String fileName )  {
        Map<String,String>  resultMap=new HashMap<String, String>();
        try {
            DefaultAcsClient client =  initVodClient(accessKeyId, accessKeySecret);
            CreateUploadVideoResponse response = new CreateUploadVideoResponse();
            response = createUploadVideo(client,title,fileName);
            resultMap.put("videoId",response.getVideoId());
            resultMap.put("uploadAddress",response.getUploadAddress());
            resultMap.put("uploadAuth",response.getUploadAuth());
            resultMap.put("requestId", response.getRequestId());

        } catch (Exception e) {
            System.out.print("ErrorMessage = " + e.getLocalizedMessage());
        }
        return resultMap;
    }


    private static RefreshUploadVideoResponse refreshUploadVideo(DefaultAcsClient  client,String videoId) throws Exception{
        RefreshUploadVideoRequest  request=new RefreshUploadVideoRequest();
        request.setVideoId(videoId);
        return  client.getAcsResponse(request);
    }


    /**
     * 刷新上传凭证
     * @param videoId
     * @return
     */
    public static Map<String,String>  refreshAddress(String videoId){
        Map<String,String>  resultMap=new HashMap<String, String>();
        try {
            DefaultAcsClient client =  initVodClient(accessKeyId, accessKeySecret);
            RefreshUploadVideoResponse  response = new RefreshUploadVideoResponse();
            response=refreshUploadVideo(client,videoId);
            resultMap.put("uploadAddress",response.getUploadAddress());
            resultMap.put("uploadAuth",response.getUploadAuth());
            resultMap.put("requestId",response.getRequestId());
        } catch (ClientException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return  resultMap;
    }


}