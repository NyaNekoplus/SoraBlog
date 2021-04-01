package com.vincent.admin.util;


import lombok.extern.slf4j.Slf4j;

/**
 * @author Vincent Tsai
 * @date 2021/3/26 11:11
 */
@Slf4j
public class FileUtil {
    public static String getFileExtension(String fileName){
        String extension = "png";
        if (fileName==null){
            return extension;
        }
        if (!fileName.isBlank()&&fileName.contains(".")){
            extension = fileName.substring(fileName.lastIndexOf(".") + 1);
        }
        extension = extension.toLowerCase();
        if (extension.length()<1){
            extension = "png";
        }
        return extension;
    }
}
