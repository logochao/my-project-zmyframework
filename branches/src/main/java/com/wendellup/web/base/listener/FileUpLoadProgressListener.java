/*
 * FileName: FileUpLoadProgressListener.java
 * Author:   lukejia
 * Date:     2013-3-7 下午1:57:07
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.wendellup.web.base.listener;

import java.text.NumberFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.ProgressListener;

public class FileUpLoadProgressListener implements ProgressListener {
    private HttpSession session;
    public FileUpLoadProgressListener(HttpServletRequest request) {
        session = request.getSession();
    }
    
    @Override
    public void update(long pBytesRead, long pContentLength, int pItems) {
        if (pContentLength == -1) {
            return;
        } else {
            double read = (pBytesRead / pContentLength);
            NumberFormat nf = NumberFormat.getCurrencyInstance();
            session.setAttribute("read", nf.format(read));
        }
    }

}
