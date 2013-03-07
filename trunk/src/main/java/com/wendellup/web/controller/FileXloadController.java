/*
 * FileName: FileXloadController.java
 * Author:   lukejia
 * Date:     2013-3-5 下午4:13:19
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.wendellup.web.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.wendellup.core.configuration.ConfigurationUtils;
import org.wendellup.core.util.FastJsonUtils;

import com.wendellup.util.FileUploadUtils;
import com.wendellup.web.base.annotation.bind.BusinessDesc;
import com.wendellup.web.base.exceptions.BusinessException;
import com.wendellup.web.base.service.BaseController;

/**
 * 〈一句话功能简述〉<br> 
 * 〈功能详细描述〉
 *
 * @author lukejia
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
@Controller
@RequestMapping("/filexload")
public class FileXloadController extends BaseController{
    /**
     * 模块描述
     */
    private static final String MODULE_DESC = "文件上传与下载";
    /**
     * 常量
     */
    private static final String UPLOAD = "upload";
    private static final String DOWNLOAD = "download";
    
    @BusinessDesc(MethodDesc="文件上传",ModuleDesc=MODULE_DESC)
    @RequestMapping(value = UPLOAD)
    @ResponseBody
    public void upload(HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setContentType("text/html;charset=UTF-8");
        Map<String, Object> result = new HashMap<String, Object>();
        // 检验附件是否合法
        ConfigurationUtils.init("filexload.properties");
        System.out.println(ConfigurationUtils.getString("fileType"));
        String[] types = ConfigurationUtils.getString("fileType").split(",");
        Long length = Long.parseLong(ConfigurationUtils.getString("fileLength"));
        result = FileUploadUtils.validateFile(request, types, length);
        if (result.get(FileUploadUtils._SUCCESS).equals(new Boolean("false"))) {
            // 文件验证失败, 返回错误信息
            response.getWriter().print(FastJsonUtils.Fast_toJSONString(result, true));
            return;
        }
        try {
            Set<MultipartFile> multipartFiles = FileUploadUtils.getMultipartFileSet((MultipartHttpServletRequest) request);
            for (MultipartFile multipartFile : multipartFiles) {
                Long fileSize = (long) multipartFile.getBytes().length;

                // 获得文件所要上传所存放的路径
                String[] strs = FileUploadUtils.calcPathAndFile(fileSize);
                String relativeURI = "/"+ strs[0]+ "/"+ strs[1]+ "/"+ multipartFile.getOriginalFilename();
                String realURI = request.getSession().getServletContext()
                        .getRealPath(ConfigurationUtils.getString("fileUri"))
                        + relativeURI;

                File fileto = new File(realURI);

                boolean ok = FileUploadUtils
                        .copyFile(multipartFile.getBytes(), fileto, true);
                // 文件上传出错
                if (!ok) {
                    result.put(FileUploadUtils._SUCCESS, "false");
                    result.put(FileUploadUtils._MSG, "文件上传失败");
                }
                
                // 记录到数据库
                System.out.println("**************数据存储**************");
                
                // 返回
                System.out.println(relativeURI.replaceAll("/", "@"));
                result.put("filePath", relativeURI.replaceAll("/", "@"));
                response.getWriter().print(FastJsonUtils.Fast_toJSONString(result, true));
            }
        } catch (Exception e) {
            throw new BusinessException("异常:" + e.getMessage());
        }
    }
    
    @BusinessDesc(MethodDesc="文件下载",ModuleDesc=MODULE_DESC)
    @RequestMapping(value = DOWNLOAD)
    public void download(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("multipart/form-data");
        String fileName = (String) request.getParameter("myfiles").replace("@", "\\");
        String fileRealName = fileName.substring(fileName.lastIndexOf("\\") + 1);
        response.setHeader("Content-Disposition", "attachment;fileName=" + fileRealName);
        ConfigurationUtils.init("filexload.properties");
        OutputStream out = null;
        try {
            File file = new File(request.getSession().getServletContext()
                    .getRealPath(ConfigurationUtils.getString("fileUri")) + fileName);
            InputStream in = new FileInputStream(file);
            out = response.getOutputStream();
            byte[] bytes = new byte[2048];
            int length;
            while ((length = in.read(bytes)) > 0) {
                out.write(bytes, 0, length);
            }
            in.close();
        } catch (Exception e) {
            throw new BusinessException("异常:" + e.getMessage());
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }
}
