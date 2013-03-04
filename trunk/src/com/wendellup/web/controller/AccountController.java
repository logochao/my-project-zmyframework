/**  
 * Description: <类功能描述-必填>  需要在每个方法前添加业务描述，方便业务业务行为的BI工作
 * Copyright:   Copyright (c)2012  
 * Company:     wendellup
 * @author:     wendellup  
 * @version:    1.0  
 * Create at:   2013-1-28 下午2:01:18
 *  
 * Modification History:  
 * Date         Author      Version     Description  
 * ------------------------------------------------------------------  
 * 2013-1-28   wendellup     1.0       如果修改了;必填  
 */
package com.wendellup.web.controller;

import java.io.File;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.configuration.ConfigurationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import org.wendellup.core.configuration.ConfigurationUtils;
import org.wendellup.core.support.Page;
import org.wendellup.core.support.QueryParams;
import org.wendellup.core.util.FastJsonUtils;

import com.wendellup.app.service.contract.account.IAccountService;
import com.wendellup.app.valueobject.entity.Account;
import com.wendellup.app.valueobject.entity.AccountCondition;
import com.wendellup.util.FileUploadUtils;
import com.wendellup.web.base.annotation.bind.BusinessDesc;
import com.wendellup.web.base.exceptions.BusinessException;
import com.wendellup.web.base.service.BaseController;

@Controller
@RequestMapping("/account")
public class AccountController extends BaseController {
	static {
		try {
			ConfigurationUtils.init("redirectUrl.properties");
		} catch (ConfigurationException e) {
			e.printStackTrace();
		}
	}

	@Autowired
	@Qualifier("iAccountService")
	private IAccountService iAccountService;

	private final static String MODULE_DES = "账户信息模块";

	// 显示所有账户信息
	private final static String SHOW_ACCOUNTS = "showAccounts";

	// 分页显示所有账户信息
	private final static String SHOW_ACCOUNTS_BY_PAGE = "showAccountsByPage";

	// 账户注册
	private final static String REGIST = "regist";

	// 删除一个账户
	private final static String DELETE = "delete";

	// 修改一个账户信息
	private final static String UPDATE = "update";

	// 文件上传
	private final static String IMAGE_UPLOAD = "imageUpload";
	// ajax方式实现文件上传
	private final static String IMAGE_UPLOAD_AJAX = "imageUploadAjax";

	// 跳转到更新账号信息表单页面
	private final static String REDIRECT_TO_ACCOUNT_UPDATE = ConfigurationUtils
			.getString("redirectToAccountUpdate");

	// 跳转到的页面从配置文件中读取,避免硬编码
	private final static String REDIRECT_TO_SHOW_ACCOUNTS = ConfigurationUtils
			.getString("redirectToShowAccounts");

	/**
	 * 查看所有账户信息
	 */
	@BusinessDesc(ModuleDesc = MODULE_DES, MethodDesc = "查看所有的账户信息")
	@RequestMapping(value = SHOW_ACCOUNTS, method = { RequestMethod.GET })
	public String showAccounts(HttpServletRequest request) throws Exception {
		try {
			List<Account> accountList = iAccountService.queryAll();
			request.setAttribute("accountList", accountList);
		} catch (Exception ex) {
			throw new BusinessException("异常:" + ex.getMessage());
		}
		return this.AutoGetURL();
	}

	/**
	 * 账户注册
	 */
	@BusinessDesc(ModuleDesc = MODULE_DES, MethodDesc = "账户注册")
	@RequestMapping(value = REGIST, method = { RequestMethod.GET })
	public String regist() throws Exception {
		return AutoGetURL();
	}

	/**
	 * 账户注册
	 */
	@BusinessDesc(ModuleDesc = MODULE_DES, MethodDesc = "账户注册")
	@RequestMapping(value = REGIST, method = { RequestMethod.POST })
	public ModelAndView regist(HttpServletRequest request, Account account)
			throws Exception {
		try {
			iAccountService.save(account);
		} catch (Exception ex) {
			throw new BusinessException("异常:" + ex.getMessage());
		}
		return new ModelAndView(new RedirectView(REDIRECT_TO_SHOW_ACCOUNTS));
	}

	/**
	 * 删除一个账户
	 */
	@BusinessDesc(ModuleDesc = MODULE_DES, MethodDesc = "删除一个账户")
	@RequestMapping(value = DELETE)
	public ModelAndView delete(HttpServletRequest request, Integer id)
			throws Exception {
		HttpSession session = request.getSession();
		String queryString = null;
		try {
//			QueryParams<AccountCondition> queryParams = (QueryParams<AccountCondition>) session
//					.getAttribute("queryParams");
			
			//如果保存在session中的查询条件过期
			queryString = (String) session.getAttribute("queryString");
			if(null == queryString){
				return new ModelAndView(new RedirectView(REDIRECT_TO_SHOW_ACCOUNTS));
			}
			
			// Page sessionPage = queryParams.getPaging();
			// session.setAttribute("sessionPage", sessionPage);
			iAccountService.delete(id);
		} catch (Exception ex) {
			throw new BusinessException("异常:" + ex.getMessage());
		}
		return new ModelAndView(new RedirectView(REDIRECT_TO_SHOW_ACCOUNTS
				+ "?" + queryString));
	}

	/**
	 * 修改一个账户前,根据id获取该账户信息
	 */
	@BusinessDesc(ModuleDesc = MODULE_DES, MethodDesc = "修改一个账户前,根据id获取该账户信息")
	@RequestMapping(value = UPDATE, method = { RequestMethod.GET })
	public String update(HttpServletRequest request, Integer id)
			throws Exception {
		try {
			Account updateAccount = iAccountService.getById(id);
			HttpSession session = request.getSession();
			session.setAttribute("updateAccount", updateAccount);

		} catch (Exception ex) {
			throw new BusinessException("异常:" + ex.getMessage());
		}
		return AutoGetURL();
	}

	/**
	 * 修改一个账户信息
	 */
	@BusinessDesc(ModuleDesc = MODULE_DES, MethodDesc = "修改账户信息")
	@RequestMapping(value = UPDATE, method = { RequestMethod.POST })
	public ModelAndView update(HttpSession session, Account account)
			throws Exception {
		String queryString = (String) session.getAttribute("queryString");
		try {
			QueryParams<AccountCondition> queryParams = (QueryParams<AccountCondition>) session
					.getAttribute("queryParams");
			//如果保存在session中的查询条件过期
			if(null == queryParams){
				return new ModelAndView(new RedirectView(REDIRECT_TO_SHOW_ACCOUNTS));
			}
			
			Page sessionPage = queryParams.getPaging();
			session.setAttribute("sessionPage", sessionPage);
			iAccountService.update(account);
		} catch (Exception ex) {
			throw new BusinessException("异常:" + ex.getMessage());
		}
		return new ModelAndView(new RedirectView(REDIRECT_TO_SHOW_ACCOUNTS
				+ "?" + queryString));
	}

	/**
	 * 分页查看所有账户信息
	 */
	@SuppressWarnings("unchecked")
	@BusinessDesc(ModuleDesc = MODULE_DES, MethodDesc = "分页查看所有的账户信息")
	@RequestMapping(value = SHOW_ACCOUNTS_BY_PAGE, method = {
			RequestMethod.GET, RequestMethod.POST })
	public String showAccountsByPage(HttpServletRequest request, Page page,
			AccountCondition accountCondition) throws Exception {
		HttpSession session = request.getSession();
		String queryString = request.getQueryString();
		session.setAttribute("queryString", queryString);
		try {
			QueryParams<AccountCondition> queryParams = (QueryParams<AccountCondition>) session
					.getAttribute("queryParams");
			if (queryParams == null) {
				queryParams = new QueryParams<AccountCondition>();
			}

			// 动态查询条件是否重置
			// if(accountCondition.getName() != null
			// || accountCondition.getStartTime() != null
			// || accountCondition.getEndTime() != null){
			// queryParams.setEntity(accountCondition);
			// }
			// 动态查询条件是否重置(等同于上面)
			// if(BeanUtils.hasAtLeastOneFieldWithValue(accountCondition)){
			// queryParams.setEntity(accountCondition);
			// }

			// 删除一条记录的时候依然能在当前页显示
			// Page sessionPage = (Page) session.getAttribute("sessionPage");
			// if(sessionPage!=null){
			// queryParams.setPaging(sessionPage);
			// session.removeAttribute("sessionPage");
			// }else{
			// queryParams.setPaging(page);
			// }
			queryParams.setEntity(accountCondition);
			queryParams.setPaging(page);

			List<Account> accountList = iAccountService
					.queryByPage(queryParams);
			session.setAttribute("queryParams", queryParams);
			request.setAttribute("accountList", accountList);
		} catch (Exception ex) {
			throw new BusinessException("异常:" + ex.getMessage());
		}
		return this.AutoGetURL();
	}

	/**
	 * 图片上传
	 */
	@BusinessDesc(ModuleDesc = MODULE_DES, MethodDesc = "照片上传")
	@RequestMapping(value = IMAGE_UPLOAD)
	public ModelAndView processImageUpload(HttpServletRequest request,
//			@RequestParam("imageFile") MultipartFile image, int id) {
			MultipartFile imageFile, int id) {
		try {
			ConfigurationUtils.init("redirectUrl.properties");

			String fileURI = request.getSession().getServletContext()
					.getRealPath(ConfigurationUtils.getString("fileStore"))
					+ "/" + imageFile.getOriginalFilename();
			File file = new File(fileURI);
			FileCopyUtils.copy(imageFile.getBytes(), file);

			// 更改用户图片的路径
			Account account = iAccountService.getById(id);
			account.setAccountPicture(imageFile.getOriginalFilename());
			iAccountService.update(account);

		} catch (ConfigurationException e) {
			e.printStackTrace();
		} catch (Exception ex) {
			throw new BusinessException("异常:" + ex.getMessage());
		}
		return new ModelAndView(new RedirectView(REDIRECT_TO_ACCOUNT_UPDATE
				+ "?id=" + id));
	}

	/**
	 * ajax方式实现图片上传
	 * 
	 * @throws UnsupportedEncodingException
	 */
	@BusinessDesc(ModuleDesc = MODULE_DES, MethodDesc = "照片上传")
	@RequestMapping(value = IMAGE_UPLOAD_AJAX)
	@ResponseBody
	public void processImageUploadAjax(HttpServletRequest request,
			HttpServletResponse response, int id) {
		response.setContentType("text/html;charset=UTF-8");
		Map<String, Object> map = new HashMap<String, Object>();
		PrintWriter out = null;
		try {
			out = response.getWriter();
			// 设置可以上传的文件的格式
			map = FileUploadUtils.validateFile(request, new String[] { "jpg",
					"gif" }, new Long(100 * 1024));

			if (map.get(FileUploadUtils._SUCCESS).equals(new Boolean("false"))) {
				// 文件格式上传验证失败, 返回错误信息
				out.print(FastJsonUtils.Fast_toJSONString(map, true));
				out.close();
				return;
			}

			// 文件可以上传,计算文件大小
			ConfigurationUtils.init("redirectUrl.properties");

			Set<MultipartFile> multipartFiles = FileUploadUtils
					.getMultipartFileSet((MultipartHttpServletRequest) request);
			MultipartFile file = multipartFiles.iterator().next();
			Long fileSize = (long) file.getBytes().length;

			// 获得文件所要上传所存放的路径
			String[] strs = FileUploadUtils.calcPathAndFile(fileSize);
			String relativeURI = "/"+ strs[0]+ "/"+ strs[1]+ "/"+ file.getOriginalFilename();
			String realURI = request.getSession().getServletContext()
					.getRealPath(ConfigurationUtils.getString("fileStore"))
					+ relativeURI;

			File fileto = new File(realURI);

			boolean ok = FileUploadUtils
					.copyFile(file.getBytes(), fileto, true);
			// 文件上传出错
			if (!ok) {
				map.put(FileUploadUtils._SUCCESS, "false");
				map.put(FileUploadUtils._MSG, "文件上传失败");
			}

			// 更改用户图片的路径
			Account account = iAccountService.getById(id);
			account.setAccountPicture(relativeURI);
			iAccountService.update(account);
			
			map.put("imgSrc", relativeURI);
			out.print(FastJsonUtils.Fast_toJSONString(map, true));

		} catch (ConfigurationException e) {
			e.printStackTrace();
		} catch (Exception ex) {
			throw new BusinessException("异常:" + ex.getMessage());
		} finally {
			out.close();
		}
	}

}
