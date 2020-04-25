package com.itszt.taotao.manager.dao;

import com.itszt.taotao.pojo.TbItemCat;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemCatDao {

    @Select("select * from tb_item_cat where parent_id = 0")
    public List<TbItemCat> queryTopCats();

    @Select("select * from tb_item_cat where parent_id = #{parentID}")
    public List<TbItemCat> queryChildCats(String parentID);
}
