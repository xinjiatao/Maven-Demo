package com.xjt.core.base;

import com.baomidou.mybatisplus.plugins.Page;
import com.xjt.core.utils.DataUtil;

import java.util.Map;

/**
 * Created by Administrator on 2017/4/10.
 * Controller对应的service或者其他service
 */
public abstract class BaseService {

    /** 分页查询  */
    protected <T> Page<T> getPage(Class<T> tClass,Map<String, Object> params) {
        Integer current = 1;
        Integer size = 10;
        String orderBy = "";
        if (DataUtil.isNotEmpty(params.get("pageNum"))) {
            current = Integer.valueOf(params.get("pageNum").toString());
        }
        if (DataUtil.isNotEmpty(params.get("pageSize"))) {
            size = Integer.valueOf(params.get("pageSize").toString());
        }
        if (DataUtil.isNotEmpty(params.get("orderBy"))) {
            orderBy = (String)params.get("orderBy");
        }
        Page<T> page = new Page<T>(current, size, orderBy);
        page.setAsc(false);
        return page;
    }

}
