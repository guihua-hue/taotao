package com.itszt.taotao.manager.service.inter;

import com.itszt.taotao.easyui.bean.EasyUIPageDatasBean;
import com.itszt.taotao.pojo.TbItem;
import com.itszt.taotao.pojo.TbItemDesc;

import java.util.List;

//商品业务
public interface GoodsService {

    public List<TbItem> getAllGoods();
//    pageCapacity 哪一页 有多少条数据
    public EasyUIPageDatasBean<TbItem> getPageGoods(Integer pageNow, Integer pageCapacity);

    public boolean addGoods(TbItem tbItem , String desc) throws Exception;
}
