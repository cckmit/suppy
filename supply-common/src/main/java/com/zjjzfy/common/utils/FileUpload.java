package com.zjjzfy.common.utils;

import com.zjjzfy.common.config.ReturnMsg;
import com.zjjzfy.common.config.ReturnStatus;
import com.zjjzfy.common.entity.SupplyResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.util.TextUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.UUID;

@Slf4j
@RestController
public class FileUpload {

    /**
     * 文件上传
     * @param dirName 要保存的文件夹
     * @param file 文件
     */
    @RequestMapping(value = "/rest/fileUpload", method = RequestMethod.POST)
    public SupplyResult fileUpload(HttpServletRequest request,
                                   @RequestParam(name = "dir", required = false) String dirName,
                                   @RequestParam(name = "file", required = false) MultipartFile file) {

        try {
            ServletContext servletContext = request.getServletContext();//获取ServletContext的对象 代表当前WEB应用
            //String realPath = servletContext.getRealPath("/uploads");//得到文件上传目的位置的真实路径

            String path;
            if (TextUtils.isEmpty(dirName)) {
                path = servletContext.getRealPath(File.separator + "upload" + File.separator + "file");
            } else {
                path = servletContext.getRealPath(File.separator + "upload" + File.separator + dirName);
            }

            File dir = new File(path);

            log.info(dir.getAbsolutePath());
            if (!dir.exists()) {
                //noinspection ResultOfMethodCallIgnored
                dir.mkdirs();   //如果该目录不存在，就创建此抽象路径名指定的目录。
            }

            String prefix = UUID.randomUUID().toString();
            prefix = prefix.replace("-", "");
            String fileName = prefix + "_" + file.getOriginalFilename();//使用UUID加前缀命名文件，防止名字重复被覆盖

            InputStream in = file.getInputStream();//声明输入输出流

            String filePath = path + File.separator + fileName;

            OutputStream out = new FileOutputStream(new File(filePath));//指定输出流的位置;

            byte[] buffer = new byte[1024];
            int len;
            while ((len = in.read(buffer)) != -1) {
                out.write(buffer, 0, len);
                out.flush();                //类似于文件复制，将文件存储到输入流，再通过输出流写入到上传位置
            }                               //这段代码也可以用IOUtils.copy(in, out)工具类的copy方法完成

            out.close();
            in.close();
            return SupplyResult.build(ReturnStatus.SUCCESS.getStatus(), ReturnMsg.SUCCESS.getMsg(), filePath);
        } catch (IOException e) {
            e.printStackTrace();
            return SupplyResult.build(ReturnStatus.FAILURE.getStatus(), ReturnMsg.FAILURE.getMsg());
        }


    }
}
