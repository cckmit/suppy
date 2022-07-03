package com.zjjzfy.exchange.service.impl;

import com.zjjzfy.common.entity.SupplyResult;
import com.zjjzfy.exchange.service.BaseService;
import com.zjjzfy.exchange.service.SignatureService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.system.ApplicationHome;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import sun.misc.BASE64Decoder;

import java.io.*;
import java.util.Calendar;


/**
 *
 * @author      jackshenonly
 * @description 类说明
 * @date        2020-03-30 09:20
 */
@Service
public class SignatureServiceImpl extends BaseService implements SignatureService {

    @Value("${myconfig.signature-path}")
    private String configPath;
    public static final String DEFAULT_PATH  = "signature";
    public static final String EXTENSION_FORM = ".png";


    @Override
    public SupplyResult uploadSignature(String imgStr, String billno) throws Exception {
        String uploadPath = getUploadPath();
        String relativePath = saveToImgByStr(imgStr, billno, uploadPath);

        return SupplyResult.build(200,"上传成功！",relativePath);
    }

    /**
     * 获取签名存放位置
     * @return
     */
    private String getUploadPath() throws FileNotFoundException {
        String path;
        log.info("configPath="+configPath);
        if(configPath != null && configPath.trim().length() > 0){
            path = ResourceUtils.getURL(configPath).getPath();
        }else {
            ApplicationHome h = new ApplicationHome(getClass());
            File jarF = h.getSource();
            path = jarF.getParentFile().getAbsolutePath() + File.separator + DEFAULT_PATH;
        }

        return path;

    }

    /**
     * 将接收的二进制流转换成图片保存
     *
     * @param imgStr  base64编码过后的图片
     * @param imgName 图片的名称（不包含扩展名）
     * @return !=null：保存正常 ; null ：保存失败
     */
    public String saveToImgByStr(String imgStr, String imgName, String realPath) throws IOException {

        if (imgStr == null || imgName == null) {
            return null;
        }

        imgName = imgName + EXTENSION_FORM;

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH) + 1;
        String contentPath = String.valueOf(year) + File.separator + String.valueOf(month) + File.separator;
        String relativePath = contentPath + imgName;
        realPath += File.separator + contentPath;

        log.info("file path="+realPath+imgName);

        try {
            //验证目录是否存在，不存则创建。
            File validateCodeFolder = new File(realPath);
            if (!validateCodeFolder.exists()) {
                validateCodeFolder.mkdirs();
            }

            BASE64Decoder decoder = new BASE64Decoder();
            FileOutputStream fos = new FileOutputStream(new File(realPath, imgName));
            byte[] imgByte = decoder.decodeBuffer(imgStr);

            for (int i = 0; i < imgByte.length; ++i) {
                if (imgByte[i] < 0) {
                    // 调整异常数据
                    imgByte[i] += 256;
                }
            }

            InputStream in = new ByteArrayInputStream(imgByte);
            byte[] b = new byte[1024];
            int nRead = 0;
            while ((nRead = in.read(b)) != -1) {
                fos.write(b, 0, nRead);
            }
            fos.flush();
            fos.close();
            in.close();


        } catch (Exception e) {
            log.error("前面上传，保存签名失败",e);
            throw e;

        } finally {
        }
        return relativePath;
    }
}
