package com.thinkgem.jeesite.modules.postManeger.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.internet.ContentType;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.h2.util.New;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsCriteria;
import org.springframework.data.mongodb.gridfs.GridFsOperations;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.google.common.net.MediaType;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSFile;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.UserAgentUtils;
import com.thinkgem.jeesite.modules.cms.utils.RelativeDateFormat;
import com.thinkgem.jeesite.modules.postManeger.baiduApi.service.TextService;
import com.thinkgem.jeesite.modules.postManeger.cost.AjaxReturn;
import com.thinkgem.jeesite.modules.postManeger.cost.UserType;
import com.thinkgem.jeesite.modules.wsp.file.service.WFileService;
import com.thinkgem.jeesite.modules.wsp.post.entity.WPost;
import com.thinkgem.jeesite.modules.wsp.post.entity.WPostType;
import com.thinkgem.jeesite.modules.wsp.post.service.WPostService;
import com.thinkgem.jeesite.modules.wsp.user.entity.WUser;
import com.thinkgem.jeesite.modules.wsp.user.service.WUserService;

import groovy.json.StreamingJsonBuilder;

@Controller
@RequestMapping(value = "${webPath}/post/")
public class PostManagerController extends MyBaseController {

	@Autowired
	private GridFsOperations gridFsTemplate;

	@Autowired
	private WPostService postService;
	@Autowired
	private TextService textService;
	@Autowired
	private WUserService userService;
	@Autowired
	private WFileService fileService;

	public static final List STYLE_LIST = new ArrayList();
	static {
		STYLE_LIST.add("basic");
	}

	@RequestMapping(value = "postInput")
	public String goPostInput(Model model, String style) {
		
		if (StringUtils.isNotEmpty(style) && STYLE_LIST.contains(style)) {
			return "modules/postManager/postInput_" + style;
		} else {
			return "modules/postManager/postInput";
		}

	}

	@ResponseBody
	@RequestMapping(value = "uploadFile")
	public Map<String, Object> uploadFile(MultipartFile file) throws IOException {
		Map<String, Object> resultMap = new HashMap<>();
		//只能上传视频和图片
		if(file.getContentType().contains("image")||file.getContentType().contains("video")) {
			GridFSFile gridfile = gridFsTemplate.store(file.getInputStream(), file.getOriginalFilename(),
					file.getContentType(), null);
			String fileId = String.valueOf(gridfile.getId());
			resultMap.put(AjaxReturn.STATUS, AjaxReturn.SUCCESS);
			resultMap.put(AjaxReturn.DATA, fileId);
		}else {
			resultMap.put(AjaxReturn.STATUS, AjaxReturn.ERROR);
			resultMap.put(AjaxReturn.MSG, "文件格式不对");
		}
		
		GridFSFile gridfile = gridFsTemplate.store(file.getInputStream(), file.getOriginalFilename(),
				file.getContentType(), null);
		return resultMap;
	}

	@ResponseBody
	@RequestMapping(value = "postSave")
	public Map<String, Object> postInput(@RequestParam("files") MultipartFile[] files, String content,String typeId)
			throws IOException {
		Map<String, Object> resultMap = new HashMap<>();
		
		if (com.thinkgem.jeesite.common.utils.StringUtils.isNoneBlank(content)) {
			content = URLDecoder.decode(content, "utf-8");
		}
		//测试文本是否包含一些不法言论
		if(StringUtils.isNotEmpty(content)&&!textService.validateText(content)) {
			resultMap.put(AjaxReturn.STATUS, AjaxReturn.ERROR);
			resultMap.put(AjaxReturn.MSG,"文本包含不法言论");
			return resultMap;
		}
		if(StringUtils.isEmpty(typeId)) {
			resultMap.put(AjaxReturn.STATUS, AjaxReturn.ERROR);
			resultMap.put(AjaxReturn.MSG,"请选择类型！");
			return resultMap;
		}
		String ip = getRequest().getRemoteHost();
		// save post
		WPost post = new WPost();
		post.setPostType(new WPostType(typeId));
		post.setContent(content);
		post.setUser(getCurrentUser());
		post.setCreateDate(new Date(System.currentTimeMillis()));
		post.setUpdateDate(new Date(System.currentTimeMillis()));
		if(StringUtils.isNotBlank(post.getContent())||(null != files && files.length > 0)) {
			postService.save(post);
		}
		if (null != files && files.length > 0&&StringUtils.isNoneBlank(post.getId())) {
			for (MultipartFile multipartFile : files) {
				GridFSFile gridfile = gridFsTemplate.store(multipartFile.getInputStream(),
						multipartFile.getOriginalFilename(), multipartFile.getContentType(), null);
				String fileId = String.valueOf(gridfile.getId());
				fileService.save(fileId, post.getId(), multipartFile.getContentType(), multipartFile.getSize());
				
			}
		}
		resultMap.put(AjaxReturn.STATUS, AjaxReturn.SUCCESS);
		return resultMap;
	}

	/**
	 * 获取文件默认取第一张,如果是视频的话就往下找
	 * 
	 * @param id
	 */
	@RequestMapping(value = "file/{id}", method = RequestMethod.GET)
	public void getFile(@PathVariable String id, HttpServletResponse response) {
		String ids[] = id.split(",");
		if (ids.length > 0) {
			boolean isFind = false;
			for (int i = 0; i < ids.length; i++) {
				GridFSDBFile file = gridFsTemplate.findOne(new Query(GridFsCriteria.where("_id").is(ids[i])));
				if (null != file && !isFind) {
					if (file.getContentType().contains("image")) {
						isFind = true;
						OutputStream os = null;
						try {
							os = response.getOutputStream();
							response.setContentType(file.getContentType());
							// 浏览器访问的时候提示保存文件还是直接打开，chrome打开直接下载了
							response.setHeader("content-disposition",
									"attachment;filename=" + URLEncoder.encode(file.getFilename(), "UTF-8"));
							IOUtils.copy(file.getInputStream(), os);
							os.flush();
							os.close();
						} catch (Exception e) {
							e.printStackTrace();
						}
					} else if (file.getContentType().contains("video")) {// 假如是视频的话选取第一张图片
						isFind = true;
						OutputStream os = null;
						try {
							os = response.getOutputStream();
							response.setContentType(file.getContentType());
							// 浏览器访问的时候提示保存文件还是直接打开，chrome打开直接下载了
							response.setHeader("content-disposition",
									"attachment;filename=" + URLEncoder.encode(file.getFilename(), "UTF-8"));
							IOUtils.copy(file.getInputStream(), os);
							os.flush();
							os.close();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
			}
		}
	}

	@ResponseBody
	@RequestMapping(value = "delFile/{id}", method = RequestMethod.GET)
	public Map<String, Object> delFile(@PathVariable String id, HttpServletResponse response) {
		Map<String, Object> resultMap = new HashMap<>();
		gridFsTemplate.delete(new Query(GridFsCriteria.where("_id").is(id)));
		resultMap.put(AjaxReturn.STATUS, AjaxReturn.SUCCESS);
		return resultMap;
	}

	/**
	 * 个人中心
	 * 
	 * @return
	 */
	@RequestMapping(value = "personalCenter")
	public String personalCenter(Model model) {
		WUser user = getCurrentUser();
		model.addAttribute("user", user);
		return "modules/postManager/personalCenter";
	}

	@ResponseBody
	@RequestMapping(value = "postListJson")
	public Map<String, Object> postListJson(@RequestParam(defaultValue = "1") Integer pageNo,String typeId) {
		WUser user = getCurrentUser();
		Map<String, Object> resultMap = new HashMap<>();
		WPost wPost = new WPost();
		wPost.setUser(user);
		if(StringUtils.isNotEmpty(typeId)) {
			wPost.setPostType(new WPostType(typeId));
		}
		
		//添加排序
	    //page.orderBy
		
		Page<WPost> page = new Page(pageNo, 8);
		page = postService.findPage(page, wPost);

		page.getTotalPage();
		if(pageNo>page.getLast()) {
			resultMap.put(AjaxReturn.DATA, new ArrayList<>());
			resultMap.put("last",true);
		}else {
			for (WPost o : page.getList()) {
				o.setCreateDateStr(RelativeDateFormat.format(o.getCreateDate()));
			}
			resultMap.put(AjaxReturn.DATA, page.getList());
			resultMap.put("last",false);
		}
		
		resultMap.put(AjaxReturn.STATUS, AjaxReturn.SUCCESS);
	    
		return resultMap;
	}
	@RequestMapping(value = "center")
	public String postCenter() {
		return "modules/postManager/postCenter";
	}
	
}
