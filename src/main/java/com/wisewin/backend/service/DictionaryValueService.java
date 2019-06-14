package com.wisewin.backend.service;

import com.wisewin.backend.dao.DictionaryValueDAO;
import com.wisewin.backend.entity.bo.DictionaryBO;
import com.wisewin.backend.entity.bo.DictionaryValueBO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

//字典 对应的 字典值
@Transactional
@Service("DictionaryValueService")
public class DictionaryValueService {

    @Resource
    DictionaryValueDAO dictionaryValueDAO;
    //增
    public Integer addDic(DictionaryValueBO dictionaryBO){
        return dictionaryValueDAO.addDic(dictionaryBO);
    }
    //删
    public Integer delDic(Integer[] idArr){
        return dictionaryValueDAO.delDic(idArr);
    }

    //改
    public Integer updDic(DictionaryValueBO dictionaryBO){
        return dictionaryValueDAO.updDic(dictionaryBO);
    }
    //查
    public List<DictionaryValueBO> getDIcs(Integer kid){
        return dictionaryValueDAO.getDIcs(kid);
    }
}
