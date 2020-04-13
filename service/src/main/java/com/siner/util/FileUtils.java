package com.siner.util;

import java.io.File;

public class FileUtils {
    /**
     * 迭代删除文件夹
     *
     * @param dirPath 文件夹路径
     */
    public static boolean deleteDir(String dirPath) {
        System.out.println(dirPath);
        String FullPath =System.getProperty("user.dir")+"/src/main/resources/static"+dirPath;
        System.out.println("删除图片路径："+FullPath);
        File file = new File(FullPath);// 读取
        if (file.exists()){
            boolean flag = file.delete();// 删除
            return flag;
        }else {
            return false;
        }

    }
}
