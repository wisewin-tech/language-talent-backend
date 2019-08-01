package com.wisewin.backend.service;

import com.wisewin.backend.dao.GiftDAO;
import com.wisewin.backend.dao.KeyValDAO;
import com.wisewin.backend.entity.bo.GiftBO;
import com.wisewin.backend.entity.bo.KeyValuesBO;
import com.wisewin.backend.entity.param.GiftParam;
import com.wisewin.backend.util.IDBuilder;
import com.wisewin.backend.util.RandomUtils;
import com.wisewin.backend.util.SnowflakeIdWorker;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
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
        GiftParam giftParam = (GiftParam)map.get("giftParam");
        System.err.println(giftParam.getValue());
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

    public void addGift(GiftParam giftParam,Integer num){
        List<GiftParam> list = new ArrayList<GiftParam>();
        SimpleDateFormat  simpleDateFormat=new SimpleDateFormat("yyyyMMddHHmmss");
        String format = simpleDateFormat.format(new Date());
        Long wkId=Long.parseLong(RandomUtils.getRandomNumber(6));
        Long wk=Long.parseLong(RandomUtils.getRandomNumber(6));
        SnowflakeIdWorker  snowflakeIdWorker=new SnowflakeIdWorker(wkId%31,wk%31);

        for (int i = 0; i < num; i++) {
            Long number = snowflakeIdWorker.nextId();
            GiftParam gif = new GiftParam();
            //设置卡号
            gif.setCardnumber(number.toString());
            //设置兑换码
            String random = RandomStringUtils.random(10, "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890abcdefghijklmnopqrstuvwxyz");
            gif.setExchangeyard(random);
            //设置时间戳为批次号
            gif.setBatchNumber(format);
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

    public void deriveGift(HttpServletResponse response,Long batch) throws Exception {
        response.setContentType("application/binary;charset=UTF-8");
        response.setHeader("Content-Disposition", "attachment;fileName=" + URLEncoder.encode("礼品卡-"+batch+".xlsx", "UTF-8"));

        String[] titles = { "卡号", "兑换码", "面值", "开始时间" ,"结束时间"};

        // 第一步，创建一个workbook，对应一个Excel文件
        Workbook wb = new XSSFWorkbook();

        // 第二步，在webbook中添加一个sheet
        Sheet  sheet = wb.createSheet("礼品卡");
        // 第三步，在sheet中添加表头第0行
        Row row = sheet.createRow(0);

        // 第四步，创建单元格，并设置值表头
        for(int i=0;i<titles.length;i++){
            Cell cell1 = row.createCell(i);
            cell1.setCellValue(titles[i]);
        }


        List<GiftBO>  list=giftDAO.queryGifByBatch(batch);

        for(int i=0;i<list.size();i++){
            Row ro = sheet.createRow(i + 1);
            ro.createCell(0).setCellValue(list.get(i).getCardnumber());//卡号
            ro.createCell(1).setCellValue(list.get(i).getExchangeyard());//兑换码
            ro.createCell(2).setCellValue(list.get(i).getValue());//面值
            ro.createCell(3).setCellValue(list.get(i).getStarttime());//开始时间
            ro.createCell(4).setCellValue(list.get(i).getFinishtime());//开始时间
        }

        // 第七步，将文件输出到客户端浏览器
        OutputStream ouputStream = response.getOutputStream();//new FileOutputStream(new File(path+"a.xls"));
        wb.write(ouputStream);
        ouputStream.flush();
        ouputStream.close();

    }


    /**
     * 未兑换的礼品卡数量
     */
    public int countBatch(Long batch){
        return giftDAO.countBatch(batch);
    }

    /**
     * 查询一已用数量
     */
    public int userCount(String ids){
        String[] split = ids.split(",");
        List<Integer>  list=new ArrayList<Integer>();
        for(int i=0;i<split.length;i++){
            list.add(Integer.parseInt(split[i]));
        }
        return  giftDAO.useCount(list);
    }


    /**
     * 批量修改时间
     * @param ids
     * @param startTime
     * @param endTime
     * @param userId
     */
    public void updateDate(String ids, Date startTime, Date endTime,Integer userId) {
        String[] split = ids.split(",");
        List<Integer>  list=new ArrayList<Integer>();
        for(int i=0;i<split.length;i++){
            list.add(Integer.parseInt(split[i]));
        }
        Map<String,Object>  paramMap=new HashMap<String,Object>();
        paramMap.put("ids",list);
        paramMap.put("startTime",startTime);
        paramMap.put("endTime",endTime);
        paramMap.put("userId",userId);
        giftDAO.updateDate(paramMap);

    }
}
