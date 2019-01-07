package com.tcps.origin.appcore.utils;

public class FileNameUtil {

    private FileNameUtil() {

    }

    /**
     * @describe 获取短文件名, 不带扩展名
     * @author gaowy @date 2018/9/1
     */
    public static String getShortName(String fileName) {
        if (fileName != null && fileName.length() > 0 && fileName.lastIndexOf(".") > -1) {
            return fileName.substring(0, fileName.lastIndexOf("."));
        }
        return fileName;
    }

    /**
     * @describe 获取扩展名, 带点
     * @author gaowy @date 2018/9/1
     */
    public static String getExtention(String fileName) {
        if (fileName != null && fileName.length() > 0 && fileName.lastIndexOf(".") > -1) {
            return fileName.substring(fileName.lastIndexOf("."));
        }
        return "";
    }
}
