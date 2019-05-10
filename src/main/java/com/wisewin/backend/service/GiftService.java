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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


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
     * 添加
     * @param giftParam
     */
    public void addGift(GiftParam giftParam,Integer num){
        if (num==0){
            num=1;
        }
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
            //设置title
            gif.setTitle(giftParam.getTitle());
            gif.setValue(giftParam.getValue());
            gif.setScope(giftParam.getScope());
            gif.setStarttime(giftParam.getStarttime());
            gif.setFinishtime(giftParam.getFinishtime());
            gif.setCause(giftParam.getCause());
            gif.setCause(giftParam.getCause());
            gif.setStatus("not use");
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
     * 删除
     * @param  idArr
     */
    public Integer deleteGift(Integer[] idArr) {
       return giftDAO.deleteGift(idArr);
    }

}
