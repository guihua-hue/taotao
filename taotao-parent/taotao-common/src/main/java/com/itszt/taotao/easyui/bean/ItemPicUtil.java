package com.itszt.taotao.easyui.bean;

import org.apache.commons.lang3.StringUtils;

public class ItemPicUtil {

    public static final String FDFS_BASE_URL = "http://192.168.61.146:8888";

    public static String genFullPath(String fdfaPath){

        boolean b = StringUtils.startsWith(fdfaPath, "/");

        if (!b) {
            return FDFS_BASE_URL + "/" + fdfaPath;
        }
        return FDFS_BASE_URL + fdfaPath;
    }

    public static String genFdfsPath(String fullPath){

        return fullPath.replace(FDFS_BASE_URL,"");
    }

}
