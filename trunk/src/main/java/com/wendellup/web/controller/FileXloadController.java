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
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.ProgressListener;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;
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
import com.wendellup.web.base.listener.FileUpLoadProgressListener;
import com.wendellup.web.base.service.BaseController;

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
    private static final String UPLOADSTAUTS = "upload_stauts";
    
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
    
    @BusinessDesc(MethodDesc="文件上传状态",ModuleDesc=MODULE_DESC)
    @RequestMapping(value = UPLOADSTAUTS)
    public void uploadStauts(HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setContentType("text/html;charset=utf-8");
        //PrintWriter out = response.getWriter();
        HttpSession session=request.getSession();
        System.out.println((String) session.getAttribute("read"));
        //out.write((String) session.getAttribute("read"));
        
        
        String saveDirectory = request.getSession().getServletContext().getRealPath("/upload");
        
        // Check that we have a file upload request
        boolean isMultipart = ServletFileUpload.isMultipartContent(request);
        System.out.println("isMultipart="+isMultipart+"<br>");
        // Create a factory for disk-based file items
        FileItemFactory factory = new DiskFileItemFactory();
        // Create a new file upload handler
        ServletFileUpload upload = new ServletFileUpload(factory);
        //Create a progress listener
        ProgressListener progressListener = new ProgressListener(){
           private long megaBytes = -1;
           public void update(long pBytesRead, long pContentLength, int pItems) {
               long mBytes = pBytesRead / 1000000;
               if (megaBytes == mBytes) {
                   return;
               }
               megaBytes = mBytes;
               System.out.println("We are currently reading item " + pItems);
               if (pContentLength == -1) {
                   System.out.println("So far, " + pBytesRead + " bytes have been read.");
               } else {
                   System.out.println("So far, " + pBytesRead + " of " + pContentLength
                                      + " bytes have been read.");
               }
           }
        };
        upload.setProgressListener(progressListener);
        
        // Parse the request
        List /* FileItem */ items = upload.parseRequest(request);
        
        // Process the uploaded items
        Iterator iter = items.iterator(); 
        while (iter.hasNext()) {
            FileItem item = (FileItem) iter.next();

            if (item.isFormField()) {
                // Process a regular form field
                //processFormField(item);
                String name = item.getFieldName();
                String value = item.getString();
                value = new String(value.getBytes("UTF-8"), "ISO-8859-1");
                System.out.println(name + "=" + value+"<br>");
            } else {
                // Process a file upload
                //processUploadedFile(item);
                String fieldName = item.getFieldName();
                String fileName = item.getName();
                String contentType = item.getContentType();
                boolean isInMemory = item.isInMemory();
                long sizeInBytes = item.getSize();
                System.out.println("fieldName="+fieldName+"<br>");
                System.out.println("fileName="+fileName+"<br>");
                System.out.println("contentType="+contentType+"<br>");
                System.out.println("isInMemory="+isInMemory+"<br>");
                System.out.println("sizeInBytes="+sizeInBytes+"<br>");
                if (fileName != null && !"".equals(fileName)) {
                    fileName= FilenameUtils.getName(fileName);
                    System.out.println("fileName saved="+fileName+"<br>");
                    File uploadedFile = new File(saveDirectory, fileName);
                    item.write(uploadedFile);
                }            
            }
        }
    }
}
