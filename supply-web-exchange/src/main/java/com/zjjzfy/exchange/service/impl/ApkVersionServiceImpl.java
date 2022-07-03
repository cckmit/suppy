package com.zjjzfy.exchange.service.impl;

import com.zjjzfy.common.entity.SupplyResult;
import com.zjjzfy.exchange.pojo.VersionInfoBean;
import com.zjjzfy.exchange.service.ApkVersionService;
import com.zjjzfy.exchange.service.BaseService;
import com.zjjzfy.exchange.service.SignatureService;
import com.zjjzfy.exchange.utils.AXMLPrinter;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.Namespace;
import org.jdom.input.SAXBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.system.ApplicationHome;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import sun.misc.BASE64Decoder;

import java.io.*;
import java.util.Calendar;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;


/**
 *
 * @author      jackshenonly
 * @description 类说明
 * @date        2020-03-30 09:20
 */
@Service
public class ApkVersionServiceImpl extends BaseService implements ApkVersionService {

    public static String [] PRODUCT_FLAVOR_LIST = {"xprint","xprint80"};

    private static final Namespace NS = Namespace.getNamespace("http://schemas.android.com/apk/res/android");
    private final String APK_PATH = File.separator +"apk"+File.separator+"apk";

    @Override
    public SupplyResult checkVersion(String productFlavor) throws Exception {
        int tmperCode = 0;
        String _path = "";
        String ver_path = APK_PATH;

        if(!checkProductFlavor(productFlavor)){
            throw new Exception("未知的设备类型");
        }

        if(null!=productFlavor && !productFlavor.trim().equals("")){
            ver_path = ver_path + File.separator + productFlavor;
            _path = productFlavor+File.separator;
        }

        String realPath = getUploadPath(ver_path);
        File dir = new File(realPath);
        if(!dir.exists()){
            dir.mkdirs();
        }
        File[] files = dir.listFiles();
        if (files.length == 0) {
            return SupplyResult.build(404,ver_path+"目录下没有可供选择的安装包！");
        } else {
            VersionInfoBean versionInfoBean = new VersionInfoBean();
            int result = 0;
            for (File file : files) {
                SAXBuilder builder = new SAXBuilder();
                Document document = null;
                try {
                    document = builder.build(getXmlInputStream(file.getPath()));
                    Element root = document.getRootElement();//跟节点-->manifest
                    String verName = root.getAttributeValue("versionName", NS).trim();
                    int verCode = Integer.valueOf(root.getAttributeValue("versionCode", NS).trim());
                    if (tmperCode < verCode) {
                        tmperCode = verCode;

                        versionInfoBean.setFileName(_path+file.getName());
                        versionInfoBean.setLatestVersionCode(String.valueOf(verCode));
                        versionInfoBean.setLatestVersionName(verName);
                        versionInfoBean.setProductFlavor(productFlavor);
                        result = 200;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            if(result != 200){
                return SupplyResult.build(300,ver_path+"目录下没有正确的安装包!",versionInfoBean);

            }else {
                return SupplyResult.build(200,"获取版本信息成功",versionInfoBean);
            }

        }

    }

    private static InputStream getXmlInputStream(String apkPath) {
        InputStream inputStream = null;
        InputStream xmlInputStream = null;
        ZipFile zipFile = null;
        try {
            zipFile = new ZipFile(apkPath);
            ZipEntry zipEntry = new ZipEntry("AndroidManifest.xml");
            inputStream = zipFile.getInputStream(zipEntry);
            AXMLPrinter xmlPrinter = new AXMLPrinter();
            xmlPrinter.startPrinf(inputStream);
            xmlInputStream = new ByteArrayInputStream(xmlPrinter.getBuf().toString().getBytes("UTF-8"));
        } catch (IOException e) {
            e.printStackTrace();
            try {
                inputStream.close();
                zipFile.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
        return xmlInputStream;
    }

    /**
     * 获取安装包路径
     * @return
     */
    private String getUploadPath(String relativePath) throws FileNotFoundException {
        String path;
        ApplicationHome h = new ApplicationHome(getClass());
        File jarF = h.getSource();
        path = jarF.getParentFile().getAbsolutePath() + File.separator + relativePath;
        log.info("apk path=" + path);
        return path;
    }

    @Override
    public Boolean checkProductFlavor(String productFlavor) {
        for (String i:PRODUCT_FLAVOR_LIST) {
            if(i.equals(productFlavor)){
                return true;
            }
        }
        return false;
    }
}
