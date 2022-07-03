package com.zjjzfy.exchange.pojo;


/**
 * @author qazhu   来自@胡慧浩。
 * @date 2017/11/16
 */
public class VersionInfoBean {
    /**
     * fileName : app-debug-unaligned(2).apk
     * latestVersionCode : 2
     * latestVersionName : 1.1
     * msg : 版本信息获取成功！
     * result : 200
     */

    private String fileName;
    private String productFlavor;
    private String latestVersionCode;
    private String latestVersionName;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getProductFlavor() {
        return productFlavor == null ? "" : productFlavor;
    }

    public void setProductFlavor(String productFlavor) {
        this.productFlavor = productFlavor;
    }

    public String getLatestVersionCode() {
        return latestVersionCode;
    }

    public void setLatestVersionCode(String latestVersionCode) {
        this.latestVersionCode = latestVersionCode;
    }

    public String getLatestVersionName() {
        return latestVersionName;
    }

    public void setLatestVersionName(String latestVersionName) {
        this.latestVersionName = latestVersionName;
    }
}
