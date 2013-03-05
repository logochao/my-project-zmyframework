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

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.wendellup.web.base.annotation.bind.BusinessDesc;
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
    
    @BusinessDesc(MethodDesc="主页面",ModuleDesc=MODULE_DESC)
    @RequestMapping(value = UPLOAD)
    public void upload(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        List<MultipartFile> files = multipartRequest.getFiles("myfiles");
        System.out.println(files);
        System.out.println("**********************************************");
    }
}
