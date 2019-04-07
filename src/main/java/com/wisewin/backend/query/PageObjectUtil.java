package com.wisewin.backend.query;

import java.util.List;

/**
 * Created by Administrator on 2018/3/14.
 */
public class PageObjectUtil<T> {

    @SuppressWarnings("unchecked")
    public PageObject savePageObject(int count, List<T> list, QueryInfo queryInfo) {
        PageObject page = new PageObject();
        int totalPage = 0;
        int pageSize = queryInfo.getPageSize();
        if (count % pageSize == 0) {
            totalPage = count / pageSize;
        } else {
            totalPage = count / pageSize + 1;
        }
        page.setDatas(list);
        page.setOffset(queryInfo.getPageOffset());
        page.setTotalPage(totalPage);

        page.setTotalRecord(count);
        page.setPageSize(pageSize);

        return page;
    }
}
