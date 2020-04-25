package com.itszt.taotao.manager.service.inter;

import com.itszt.taotao.easyui.bean.EasyUITreeBean;

import java.util.List;

//高冠华
//2020年4月24日20:08:05
public interface ItemCatService {

    public List<EasyUITreeBean> showTopNodes();

    public List<EasyUITreeBean> showLeafNodes(String parentID);
}
