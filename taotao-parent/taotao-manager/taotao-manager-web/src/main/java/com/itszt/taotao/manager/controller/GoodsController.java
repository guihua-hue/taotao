package com.itszt.taotao.manager.controller;

import com.itszt.taotao.easyui.bean.EasyUIPageDatasBean;
import com.itszt.taotao.easyui.bean.ItemPicUtil;
import com.itszt.taotao.manager.service.inter.GoodsService;
import com.itszt.taotao.manager.vo.EasyUIPicUploadBean;
import com.itszt.taotao.manager.vo.EasyUISavItemBean;
import com.itszt.taotao.pojo.TbItem;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

@Controller
public class GoodsController {

    @Resource
    private GoodsService goodsService;

    @Resource
    private FastDFSUtil fastDFSUtil;


//    @Value("http://192.168.61.146:8888/")
//    private String nginxServer;

    private Logger logger = Logger.getLogger(GoodsController.class);

    @RequestMapping("/item/list")
    @ResponseBody
    public EasyUIPageDatasBean<TbItem> getAllGoods(@RequestParam(required = false,defaultValue = "1") String page,
                                    @RequestParam(required = false,defaultValue ="20")String rows){

        EasyUIPageDatasBean<TbItem> pageGoods = goodsService.getPageGoods(NumberUtils.toInt(page), NumberUtils.toInt(rows));

        return pageGoods;
    }

//    @RequestMapping("item-list")
//    public String goToItemList(){
//
//        return "item-list";
//    }

    @RequestMapping("{path}")
    public String goToItemList(@PathVariable(name = "path") String path){

        return path;

    }

    @RequestMapping("/pic/upload")
    @ResponseBody
    public EasyUIPicUploadBean uploadPic(MultipartFile uploadFile){

        String[] split = uploadFile.getOriginalFilename().split("\\.");
        String extName =  split[1];
        EasyUIPicUploadBean easyUIPicUploadBean = new EasyUIPicUploadBean();

        try {
            String path = fastDFSUtil.uploadFile(uploadFile.getBytes(), extName, null);
            String url = ItemPicUtil.genFullPath(path);



            easyUIPicUploadBean.setError(0);
            easyUIPicUploadBean.setMessage("上传成功!");
            easyUIPicUploadBean.setUrl(url);

            logger.debug("url---------->"+url);

            return easyUIPicUploadBean;
        } catch (Exception e) {
            e.printStackTrace();
        }

        easyUIPicUploadBean.setError(1);
        easyUIPicUploadBean.setMessage("网络异常！");

        return easyUIPicUploadBean;
    }

    @RequestMapping("/item/save")
    @ResponseBody
    public EasyUISavItemBean savItemBean(TbItem tbItem,String desc){

        EasyUISavItemBean easyUISavItemBean = new EasyUISavItemBean();

        tbItem.setImage(ItemPicUtil.genFdfsPath(tbItem.getImage()));

        try {
            goodsService.addGoods(tbItem,desc);
            easyUISavItemBean.setStatus(EasyUISavItemBean.STATUS_OK);
            easyUISavItemBean.setMessage("新增商品完成!"+ tbItem.getTitle());
        } catch (Exception e) {
            e.printStackTrace();
            easyUISavItemBean.setStatus(EasyUISavItemBean.STATUS_ERROR);
            easyUISavItemBean.setMessage("新增商品失败!"+ tbItem.getTitle());
        }

        return easyUISavItemBean;

    }

}
