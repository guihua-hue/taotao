package com.itszt.taotao.manager.service;

import com.itszt.taotao.easyui.bean.EasyUITreeBean;
import com.itszt.taotao.manager.dao.ItemCatDao;
import com.itszt.taotao.manager.service.inter.ItemCatService;
import com.itszt.taotao.pojo.TbItemCat;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class ItemCatServiceImpl implements ItemCatService {

    @Resource
    private ItemCatDao itemCatDao;

    @Override
    public List<EasyUITreeBean> showTopNodes() {

        List<TbItemCat> tbItemCats = itemCatDao.queryTopCats();
        List<EasyUITreeBean> easyUITreeBeans = convertItem(tbItemCats);

        return easyUITreeBeans;
    }

    @Override
    public List<EasyUITreeBean> showLeafNodes(String parentID) {

        List<TbItemCat> tbItemCats = itemCatDao.queryChildCats(parentID);
        List<EasyUITreeBean> easyUITreeBeans = convertItem(tbItemCats);

        return easyUITreeBeans;
    }

    private List<EasyUITreeBean> convertItem(List<TbItemCat> tbItemCats){

        List<EasyUITreeBean> easyUITreeBeans = new ArrayList<>();

        for (TbItemCat tbItemCat : tbItemCats) {
            EasyUITreeBean easyUITreeBean = new EasyUITreeBean();
            easyUITreeBeans.add(easyUITreeBean);

            easyUITreeBean.setId(tbItemCat.getId());
            easyUITreeBean.setText(tbItemCat.getName());

            if (!tbItemCat.getIsParent()) {
                easyUITreeBean.setState(EasyUITreeBean.STATE_OPEN);
            }
        }

        return easyUITreeBeans;
    }
}
