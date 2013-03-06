/**  
 * Description: <类功能描述-必填>文件上传工具类
 * Copyright:   Copyright (c)2012  
 * Company:     wendellup
 * @author:     wendellup  
 * @version:    1.0  
 * Create at:   2013-1-31 下午4:53:58
 *  
 * Modification History:  
 * Date         Author      Version     Description  
 * ------------------------------------------------------------------  
 * 2013-1-31   wendellup     1.0       如果修改了;必填  
 */
package com.wendellup.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

public class FileUploadUtils {
	public final static String _SUCCESS = "success";
	public final static String _MSG = "msg";
	
	public final static long BYTE_BASE = 1024; //1M = 1024KB
	
	/**
	 * 字节转换为千字节
	 * @param bytes
	 * @return
	 */
	public static long byteToKilobyte(long bytes){
		return bytes/BYTE_BASE;
	}
	
	/**
	 * 千字节转换为兆字节
	 * @param kilobyte
	 * @return
	 */
	public static long kilobyteToMegabyte(long kilobyte){
		return kilobyte/BYTE_BASE;
	}
	
	/**
	 * 获取文件字节数<br>
	 * @param file
	 * @return 返回文件流的字节大小
	 * @throws Exception
	 */
	public static Long getFileSize(File file) throws Exception{
		long s = 0;
		FileInputStream fis = null;
		try {
			if (file.exists()) {
				fis = new FileInputStream(file);
				s = fis.available();
			} else{
				if(!file.createNewFile()){
					
				}
			}
		} catch (Exception ex) {
			throw new Exception(ex);
		} finally{
			if(null != fis){
				fis.close();
			}
		}
		return s;
	}
	
	/**
	 * 验证上传附件是否符合规则<br>
	 * 
	 * @param request
	 * @param ext 上传文件的后缀限制.如{"jpg", "jpeg", "gif", "bmp", "png"}
	 * @param fileSize 上传文件的大小限制
	 * @return 返回map, 如果失败, 则返回的map对象为:{"success":boolean, "msg":String};
	 *     如果成功, 则返回的map对象为:{"success":boolean,"msg":String,"fileSet":MultipartFileSet}
	 *      
	 */
	public static Map<String, Object> validateFile(HttpServletRequest request,
			String[] ext, Long fileSize){
		return validateFile(request, null, ext, fileSize);
	}

	/**
	 * 验证上传附件是否符合规则
	 * @param request
	 * @param fileFormName
	 *         页面file对象名, 可有多个.如页面上有两个file对象
	 *         imageFile和txtFile, 则示例参数为: "imageFile,txtFile".
	 *         若此参数为null, 则直接取得表单所提交的所有file对象
	 * @param ext
	 *         上传文件的后缀限制.如{"jpg", "jpeg", "gif", "bmp", "png"}
	 * @param fileSize
	 *         上传文件的大小限制
	 * @return 返回map, 如果失败, 则返回的map对象为:{"success":boolean, "msg":String};
	 *     如果成功, 则返回的map对象为:{"success":boolean,"msg":String,"fileSet":MultipartFileSet}
	 */
	private static Map<String, Object> validateFile(HttpServletRequest request,
			String fileFormName, String[] ext, Long fileSize) {
		
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest)request;
		
		//取得MultipartFile 的集合
		Set<MultipartFile> fileSet = null;
		
		//可以针对表单file对象数据, 也可以不需要数据
		if(null == fileFormName || "".equals(fileFormName.trim())){
			fileSet = getMultipartFileSet(multipartRequest);
		}else{
			String[] files = fileFormName.split(",");
			fileSet = new LinkedHashSet<MultipartFile>();
			for(String s : files){
				MultipartFile mf = multipartRequest.getFile(s);
				if(0<mf.getOriginalFilename().length()){
					fileSet.add(mf);
				}
			}
		}
		
		//上传文件为空判读
		if(null == fileSet){
			return returnResult(false, "您没有选择任何附件");
		}
		
		//判断文件后缀是否符合约束
		if(null != ext && ext.length > 0){
			for(MultipartFile file : fileSet){
				if(!okExt(file, ext)){
					return returnResult(false, "可以上传的文件类型为:" + extString(ext));
				}
			}
		}
		
		//判断文件大小是否符合
		if(null != fileSize && 0 != fileSize){
			for(MultipartFile file : fileSet){
				if(!okSize(file, fileSize)){
					return returnResult(false, "最大上传文件大小为"+sizeString(fileSize));
				}
			}
		}
		
		return returnResult(true, "success");
	}
	
	/**
	 * <pre>
	 * 当总数小于10000000的时候, 分为3级目录 100(0~99)*100*1000
	 * 当总数介于10000000 和 1000000000 分为4级目录 101/100(0~99) * 100 * 100 * 1000
	 * 当总数介于1000000000 和 100000000000 分为4级目录 102/100(0~99) * 100 * 100 * 100 * 1000
	 * Example:
	 * ============= OUT ========================
	 * /23/49/983
	 * /01/00/00/000
	 * /01/00/00/00/000
	 * 	/33/03/23/00/00/34/32/32/322
	 * </pre>
	 * @param l
	 * @return
	 */
	public static String calcPath(long l){
		if (100000000000L <= l){
			return calcPath(new StringBuffer(), String.valueOf(l));
		}
		if (1000000000 <= l) {
			return calcPath(11, String.valueOf(l));
		}
		if (10000000 <= l) {
			return calcPath(9,String.valueOf(l));
		}else{
			return calcPath(7, String.valueOf(l));
		}
	}
	
	/**
	 * <pre>
	 * 生成目录和文件名称，最外层目录为季度。
	 * 当总数小于10000000的时候，分为3级目录 100(0~99) * 100 * 1000
	 * 当总数介于10000000 和 1000000000 分为4级目录 101/100(0~99) * 100 * 100 * 1000
	 * 当总数介于1000000000 和 100000000000 分为4级目录 102/100(0~99) * 100 * 100 * 100 * 1000
	 * Example:
	 * ============= OUT ========================
	 * /20093/23/49	 983 + random(1000)
	 * /20093/01/00/00 	 000 + random(1000)
	 * /20093/01/00/00/00	 000 + random(1000)
	 * 	/20093/33/03/23/00/00/34/32/32	 322 + random(1000)
	 * </pre>
	 * 
	 * @param l
	 * @return
     * @see 
     * @since [1.0]
	 */
	public static String[] calcPathAndFile(long l){
		String str = calcPath(l);
		int lastIndex = str.lastIndexOf("/");
		return new String[]{
				quarter() + str.substring(0, lastIndex),
				new StringBuffer(str.substring(lastIndex + 1)).append(new Random().nextInt(1000)).toString()
		};
	}
	
	/**
	 * 获取季度，格式YYYYQ
	 * @return
	 */
	static String quarter() {
		Calendar c = Calendar.getInstance();
		return String.valueOf(c.get(Calendar.YEAR))
				+ (c.get(Calendar.MONTH) / 3 + 1);
	}
	
	private static String calcPath(int length, String s){
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length - s.length(); i++) {
			sb.append("0");
		}
		return calcPath(new StringBuffer(), sb.append(s).toString());
	}
	
	private static String calcPath(StringBuffer sb, String s){
		if(s.length() <= 3){
			return sb.append("/").append(s).toString();
		}else{
			sb.append("/").append(s.substring(0,2));
			return calcPath(sb, s.substring(2));
		}
		
	}
	
	/**
	 * 根据文件名(无后缀)生成唯一的UUID
	 * @return
	 */
	public static String getUUIDFileNameWithoutExt(){
		return java.util.UUID.randomUUID().toString();
	}
	
	/**
	 * live size
	 * @param size
	 * @return
	 */
	private static String sizeString(Long size) {
		int g = 1024 * 1024 * 1024;
		int m = 1024 * 1024;
		int k = 1024;
		
		if(size > g){
			return size/g + "G";
		}
		if(size > m){
			return size/m + "M";
		}
		if(size > k){
			return size/k + "K";
		}
		return null;
	}

	/**
	 * 判断文件大小是否符合规则
	 * @param file
	 * @param length
	 * @return
	 */
	private static boolean okSize(MultipartFile file, Long length) {
		return 0 < file.getSize() && length > file.getSize();
	}

	/**
	 * String[] to String with ','
	 * @param ext
	 * @return
	 */
	private static String extString(String[] ext) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < ext.length; i++) {
			sb.append(ext[i]).append(", ");
		}
		// 私有方法，因为传入的String[] 参数不为空，则可以直接去除最后一个逗号
		return sb.substring(0, sb.length()-2).toString();
	}

	/**
	 * 判断文件后缀是否符合规则
	 * @param file
	 * @param ext
	 * @return
	 */
	private static boolean okExt(MultipartFile file, String[] ext) {
		String fileName = file.getOriginalFilename();
		String extName = fileName.substring(fileName.lastIndexOf(".")+1)
				.toLowerCase();
		if(null == ext || ext.length <= 0){
			return false;
		}
		for(int i=0; i<ext.length; i++){
			if((ext[i].toLowerCase()).equals(extName)){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 判断文件格式是否符合规则
	 * @param fileType 要验证的文件类型
	 * @param fileTypes 允许的文件类型
	 * @return
	 */
	public static boolean okType(String fileType, String[] fileTypes){
		for(String allowType : fileTypes){
			if(allowType.equalsIgnoreCase(fileType)){
				return true;
			}
		}
		return false;
	}

	/**
	 * 返回结果map
	 * @param success
	 * @param msg
	 * @return
	 */
	private static Map<String, Object> returnResult(boolean success, String msg) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(_SUCCESS, success);
		map.put(_MSG, msg);
		return map;
	}

	/**
	 * 得到MultipartFile集合
	 * @param multipartRequest
	 * @return
	 */
	public static Set<MultipartFile> getMultipartFileSet(
			MultipartHttpServletRequest multipartRequest) {
		Set<MultipartFile> fileSet = new LinkedHashSet<MultipartFile>();
		for(Iterator<?> it = multipartRequest.getFileNames(); it.hasNext();){
			String key = (String) it.next();
			MultipartFile file = multipartRequest.getFile(key);
			if(0 < file.getOriginalFilename().length()){
				fileSet.add(file);
			}
		}
		return fileSet;
	}
	
	/**
	 * 复制文件
	 * @param filefrom
	 * @param fileto
	 * @param rewrite
	 * @return
	 */
	public static boolean copyFile(File filefrom, File fileto, boolean rewrite){
		if(!filefrom.exists()){
			return false;
		}
		if(!filefrom.isFile()){
			return false;
		}
		if(!filefrom.canRead()){
			return false;
		}
		if(fileto.exists() && rewrite){
			fileto.delete();
		}
		if(!fileto.getParentFile().exists()){
			fileto.getParentFile().mkdirs();
		}
		if(!fileto.exists()){
			try {
				filefrom.createNewFile();
			} catch (Exception e) {
				return false;
			}
		}
		if(!fileto.canWrite()){
			return false;
		}
		try {
			FileInputStream in  = new FileInputStream(filefrom);
			FileOutputStream out = new FileOutputStream(fileto);
			byte bt[] = new byte[1024];
			int c;
			while((c=in.read(bt)) > 0){
				out.write(bt, 0, c);
			}
			in.close();
			out.close();
			return true;
		} catch (Exception e) {
			return false;
		}
		
	}
	
	/**
	 * 复制文件
	 * @param filefrom
	 * @param fileto
	 * @param rewrite
	 * @return
	 */
	public static boolean copyFile(byte[] fileFromBytes, File fileto, boolean rewrite){
		if(fileFromBytes == null){
			return false;
		}
		if(fileto.exists() && rewrite){
			fileto.delete();
		}
		if(!fileto.getParentFile().exists()){
			fileto.getParentFile().mkdirs();
		}
//		if(!fileto.canWrite()){
//			return false;
//		}
		try {
			FileOutputStream out = new FileOutputStream(fileto);
			for(int i=0; i<fileFromBytes.length; i++){
				out.write(fileFromBytes[i]);
			}
			out.close();
			return true;
		} catch (Exception e) {
			return false;
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		 FileUploadUtils x = new FileUploadUtils();
		 System.out.println(extString(new String[] {"jpg", "jpeg", "gif",
		 "bmp", "png"}));
		 System.out.println(calcPath(3303230000343232322L));
		 System.out.println(Arrays.toString(calcPathAndFile(3303230000343232322L)));
		 String[] s1 = calcPathAndFile(123456);
		 String[] s2 = calcPathAndFile(12);
		 String[] s3 = calcPathAndFile(0);
		 String[] s4 = calcPathAndFile(12345678);
		 String[] s5 = calcPathAndFile(12345678911L);
		 String[] s6 = calcPathAndFile(123456789101112131L);
		 
	}
}
