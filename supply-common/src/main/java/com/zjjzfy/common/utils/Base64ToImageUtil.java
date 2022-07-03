package com.zjjzfy.common.utils;

import sun.misc.BASE64Decoder;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Base64;

/**
 * @author hsmz
 */
public class Base64ToImageUtil {
    /**
     * base64 字符串转化成图片
     *
     * @param imgStr    base64字符串
     * @param imageName 图片名称
     * @return
     * @throws FileNotFoundException
     */
    public static boolean GenerateImage(String imgStr, String imageName, String uploadPath) throws Exception {
        File upload = new File(uploadPath);
        if (upload.exists()) {
            upload.mkdirs();
        }
        //对字节数组字符串进行Base64解码并生成图片
        //图像数据为空
        if (imgStr == null) {
            return false;
        }
        try {
            //Base64解码
            byte[] b = Base64.getDecoder().decode(imgStr.replace("\r\n", ""));
            for (int i = 0; i < b.length; ++i) {
                //调整异常数据
                if (b[i] < 0) {
                    b[i] += 256;
                }
            }
            //生成jpeg图片
            OutputStream out = new FileOutputStream(upload + File.separator + imageName);
            out.write(b);
            out.flush();
            out.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 将base64编码字符串转换为图片
     *
     * @param imgStr
     * @param path
     * @return
     */
    public static boolean yzxGenerateImage(String imgStr, String path) {
        imgStr = imgStr.replace("data:image/jpeg;base64,", "");
        imgStr = imgStr.replace(" ", "+");
        if (imgStr == null) {
            return false;
        }
        BASE64Decoder decoder = new BASE64Decoder();
        try {
            // 解密
            byte[] b = decoder.decodeBuffer(imgStr);
            // 处理数据
            for (int i = 0; i < b.length; ++i) {
                if (b[i] < 0) {
                    b[i] += 256;
                }
            }
            OutputStream out = new FileOutputStream(path);
            out.write(b);
            out.flush();
            out.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}