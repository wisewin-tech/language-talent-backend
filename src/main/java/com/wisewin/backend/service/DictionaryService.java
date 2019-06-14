package com.wisewin.backend.service;

import com.wisewin.backend.dao.DictionaryDAO;
import com.wisewin.backend.entity.bo.DictionaryBO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Transactional
@Service("DictionaryService")
public class DictionaryService {

    @Resource
    DictionaryDAO dictionaryDAO;

    //查询字典
    public List<DictionaryBO> getDictionaryBOs(){
        return dictionaryDAO.getDictionaryBOs();
    }

}
