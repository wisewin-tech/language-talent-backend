package com.wisewin.backend.service;

import com.wisewin.backend.dao.GiftDAO;
import com.wisewin.backend.dao.KeyValDAO;
import com.wisewin.backend.entity.bo.GiftBO;
import com.wisewin.backend.entity.bo.KeyValuesBO;
import com.wisewin.backend.entity.param.GiftParam;
import com.wisewin.backend.util.IDBuilder;
import com.wisewin.backend.util.RandomUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;


@Service
public class GiftService {
        @Resource
        private GiftDAO giftDAO;

    /**
     * 查询所有礼品卡信息
     * @return
     */
    public List<GiftBO> selectAll(Map<String,Object> map) {
        return giftDAO.selectAll(map);
    }
    /**
     * 查询礼品卡数量
     * @return
     * String batchNumber; //批次号
     * String title; //标题名字
     * String cardnumber; //卡号
     * Integer value; //兑换值
     * String status; //状态(已用和未使用)英文来表示
     */
    public int selectCount(String batchNumber,String title,String cardnumber,Integer value,String status) {

        return  giftDAO.selectCount(batchNumber,title,cardnumber,value,status);
    }

    /**
     * 添加
     * @param giftParam
     */
    public void addGift(GiftParam giftParam,Integer num,String phoneNumber){
        if (num==0){
            num=1;
        }
        //获得当前时间的时间戳
        long time = new Date().getTime();
        List<GiftParam> list = new ArrayList<GiftParam>();
        for (int i = 0; i < num; i++) {
            GiftParam gif = new GiftParam();
            //获得8位的随机数
            String cardnumber=RandomUtils.getRandomNumber(8);
            //获得10位的随机数
            String exchangeyard=RandomUtils.getRandomChar(10);
            //设置卡号为随机数.
            gif.setCardnumber(cardnumber);
            //设置兑换码为随机数
            gif.setExchangeyard(exchangeyard);
            //设置时间戳为批次号
            gif.setBatchNumber(phoneNumber+String.valueOf(time));
            //设置title
            gif.setTitle(giftParam.getTitle());
            gif.setValue(giftParam.getValue());
            gif.setRemark(giftParam.getRemark());
            gif.setStarttime(giftParam.getStarttime());
            gif.setFinishtime(giftParam.getFinishtime());
            gif.setCause(giftParam.getCause());
            list.add(gif);
        }

        giftDAO.addGift(list);
    }
    /**
     * 修改
     * @param giftParam
     */
    public void updateGift(GiftParam giftParam){
        giftDAO.updateGift(giftParam);
    }
    /**
     * 冻结/解冻
     * @param  idArr
     */
    public Integer frostGift(Integer[] idArr,String status) {
        //如果是使用状态.直接返回
        if (status.equals("use")){
            return -1;
        }
        if(status.equals("frost")){ //如果是冻结状态.改为解冻状态
            return giftDAO.frostGift(idArr);
        }else{//如果是未使用状态,改为冻结状态
            return giftDAO.unfreezeGift(idArr);
        }

    }

}
