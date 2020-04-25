package com.itszt.taotao.manager.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itszt.taotao.easyui.bean.EasyUIPageDatasBean;
import com.itszt.taotao.manager.dao.GoodsDao;
import com.itszt.taotao.manager.service.inter.GoodsService;
import com.itszt.taotao.pojo.TbItem;
import com.itszt.taotao.pojo.TbItemDesc;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
@Service
@Transactional
public class GoodsServiceImpl implements GoodsService {
    @Resource
    private GoodsDao goodsDao;

    @Override
    public List<TbItem> getAllGoods() {

        List<TbItem> tbItems = goodsDao.queryAllItems();

        return tbItems;
    }
    //    pageCapacity 哪一页 有多少条数据
    @Override
    public EasyUIPageDatasBean<TbItem> getPageGoods(Integer pageNow, Integer pageCapacity) {

        PageHelper.startPage(pageNow,pageCapacity);

        List<TbItem> tbItems = goodsDao.queryAllItems();

        PageInfo<TbItem> pageInfo = new PageInfo(tbItems);
        //商品总数目
        long total = pageInfo.getTotal();
        //当前页数据
        List<TbItem> list = pageInfo.getList();

        EasyUIPageDatasBean<TbItem> tbItemEasyUIPageDatasBean = new EasyUIPageDatasBean<>();
        tbItemEasyUIPageDatasBean.setRows(list);
        tbItemEasyUIPageDatasBean.setTotal(total);

        return tbItemEasyUIPageDatasBean;
    }

    @Override
    public boolean addGoods(TbItem tbItem, String desc) throws Exception{

        tbItem.setCreated(new Date());
        tbItem.setUpdated(new Date());
        tbItem.setStatus((byte) 0);

        goodsDao.insertTbitem(tbItem);

        TbItemDesc tbItemDesc = new TbItemDesc();

        tbItemDesc.setItemDesc(desc);
        tbItemDesc.setItemId(tbItem.getId());
        tbItemDesc.setUpdated(new Date());
        tbItemDesc.setCreated(new Date());

        return true;
    }
}
