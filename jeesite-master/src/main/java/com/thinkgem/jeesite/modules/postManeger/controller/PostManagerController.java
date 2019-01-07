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

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsCriteria;
import org.springframework.data.mongodb.gridfs.GridFsOperations;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSFile;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.Encodes;
import com.thinkgem.jeesite.modules.cms.utils.RelativeDateFormat;
import com.thinkgem.jeesite.modules.postManeger.baiduApi.service.TextService;
import com.thinkgem.jeesite.modules.postManeger.cost.AjaxReturn;
import com.thinkgem.jeesite.modules.postManeger.cost.AllConst;
import com.thinkgem.jeesite.modules.postManeger.vo.PostSearchVo;
import com.thinkgem.jeesite.modules.wsp.collection.entity.WPostCollection;
import com.thinkgem.jeesite.modules.wsp.collection.service.WPostCollectionService;
import com.thinkgem.jeesite.modules.wsp.comments.entity.WComments;
import com.thinkgem.jeesite.modules.wsp.comments.service.WCommentsService;
import com.thinkgem.jeesite.modules.wsp.file.service.WFileService;
import com.thinkgem.jeesite.modules.wsp.follow.entity.WFollow;
import com.thinkgem.jeesite.modules.wsp.follow.service.WFollowService;
import com.thinkgem.jeesite.modules.wsp.post.entity.WPost;
import com.thinkgem.jeesite.modules.wsp.post.entity.WPostType;
import com.thinkgem.jeesite.modules.wsp.post.service.WPostService;
import com.thinkgem.jeesite.modules.wsp.post.service.WPostTypeService;
import com.thinkgem.jeesite.modules.wsp.praise.entity.WPostPraise;
import com.thinkgem.jeesite.modules.wsp.praise.service.WPostPraiseService;
import com.thinkgem.jeesite.modules.wsp.session.entity.WSession;
import com.thinkgem.jeesite.modules.wsp.session.service.WSessionService;
import com.thinkgem.jeesite.modules.wsp.user.entity.WUser;
import com.thinkgem.jeesite.modules.wsp.user.service.WUserService;

@Controller
@RequestMapping(value = "${webPath}/post/")
public class PostManagerController extends MyBaseController {

	@Autowired
	private GridFsOperations gridFsTemplate;
	@Autowired
	private WSessionService sessionService;
	@Autowired
	private WPostService postService;
	@Autowired
	private TextService textService;
	@Autowired
	private WUserService userService;
	@Autowired
	private WFileService fileService;
	@Autowired
	private WPostPraiseService praiseService;
	@Autowired
	private WFollowService followService;
	@Autowired
	private WCommentsService commentsService;
	@Autowired
	private WPostTypeService postTypeService;
	@Autowired
	private WPostCollectionService collectionService;
	public static final List STYLE_LIST = new ArrayList();
	static {
		STYLE_LIST.add("basic");
	}

	@RequestMapping(value = "postInput")
	public String goPostInput(Model model, String style) {

		if (StringUtils.isNotEmpty(style) && STYLE_LIST.contains(style)) {
			model.addAttribute("style", style);
			return "modules/postManager/postInput_" + style;
		} else {
			return "modules/postManager/postInput";
		}

	}

	@ResponseBody
	@RequestMapping(value = "uploadFile")
	public Map<String, Object> uploadFile(MultipartFile file) throws IOException {
		Map<String, Object> resultMap = new HashMap<>();
		// 只能上传视频和图片
		if (file.getContentType().contains("image") || file.getContentType().contains("video")) {
			GridFSFile gridfile = gridFsTemplate.store(file.getInputStream(), file.getOriginalFilename(),
					file.getContentType(), null);
			String fileId = String.valueOf(gridfile.getId());
			resultMap.put(AjaxReturn.STATUS, AjaxReturn.SUCCESS);
			resultMap.put(AjaxReturn.DATA, fileId);
		} else {
			resultMap.put(AjaxReturn.STATUS, AjaxReturn.ERROR);
			resultMap.put(AjaxReturn.MSG, "文件格式不对");
		}

		GridFSFile gridfile = gridFsTemplate.store(file.getInputStream(), file.getOriginalFilename(),
				file.getContentType(), null);
		return resultMap;
	}

	@ResponseBody
	@RequestMapping(value = "postSaveNew")
	public Map<String, Object> postSaveNew(String content, String typeId, String title) throws IOException {
		Map<String, Object> resultMap = new HashMap<>();
		WPost post = new WPost();
		if (com.thinkgem.jeesite.common.utils.StringUtils.isNoneBlank(content)) {
			content = URLDecoder.decode(content, "utf-8");
			post.setContent(Encodes.escapeHtml(content));
		} else {
			resultMap.put(AjaxReturn.STATUS, AjaxReturn.ERROR);
			resultMap.put(AjaxReturn.MSG, "内容不能为空!");
			return resultMap;
		}
		if (com.thinkgem.jeesite.common.utils.StringUtils.isBlank(title)) {
			resultMap.put(AjaxReturn.STATUS, AjaxReturn.ERROR);
			resultMap.put(AjaxReturn.MSG, "标题不能为空!");
			return resultMap;
		}
		WSession session = sessionService.getCurrentUser(getRequest().getSession().getId());
		post.setUser(session.getUser());
		post.setPostType(new WPostType(typeId));
		post.setTitle(title);
		post.setCreateDate(new Date(System.currentTimeMillis()));
		post.setUpdateDate(new Date(System.currentTimeMillis()));
		postService.save(post);
		resultMap.put(AjaxReturn.STATUS, AjaxReturn.SUCCESS);
		resultMap.put(AjaxReturn.MSG, "success published!");
		return resultMap;
	}

	@ResponseBody
	@RequestMapping(value = "postSave")
	public Map<String, Object> postInput(@RequestParam("files") MultipartFile[] files, String content, String typeId,
			String topic) throws IOException {
		Map<String, Object> resultMap = new HashMap<>();
		WPost post = new WPost();
		if (com.thinkgem.jeesite.common.utils.StringUtils.isNoneBlank(content)) {
			content = URLDecoder.decode(content, "utf-8");
			post.setContent(Encodes.escapeHtml(content));
		}

		if (com.thinkgem.jeesite.common.utils.StringUtils.isBlank(topic)) {
			resultMap.put(AjaxReturn.STATUS, AjaxReturn.ERROR);
			resultMap.put(AjaxReturn.MSG, "标题不能为空!");
			return resultMap;
		}

		// 测试文本是否包含一些不法言论
		if (StringUtils.isNotEmpty(content) && !textService.validateText(content)) {
			resultMap.put(AjaxReturn.STATUS, AjaxReturn.ERROR);
			resultMap.put(AjaxReturn.MSG, "文本包含不法言论");
			return resultMap;
		}
		if (StringUtils.isEmpty(typeId)) {
			resultMap.put(AjaxReturn.STATUS, AjaxReturn.ERROR);
			resultMap.put(AjaxReturn.MSG, "请选择类型！");
			return resultMap;
		}
		String ip = getRequest().getRemoteHost();
		// save post

		post.setPostType(new WPostType(typeId));
		WSession session = sessionService.getCurrentUser(getCurrentUserId());
		post.setUser(session.getUser());
		post.setTitle(topic);
		post.setCreateDate(new Date(System.currentTimeMillis()));
		post.setUpdateDate(new Date(System.currentTimeMillis()));
		if (StringUtils.isNotBlank(post.getContent()) || (null != files && files.length > 0)) {
			postService.save(post);
		}
		if (null != files && files.length > 0 && StringUtils.isNoneBlank(post.getId())) {
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
		user = userService.get(user.getId());
		model.addAttribute("user", user);
		return "modules/postManager/personalCenter";
	}

	/**
	 * 
	 * @param pageNo
	 * @param typeId
	 * @param scont  searchContent
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "postListJson")
	public Map<String, Object> postListJson(@RequestBody PostSearchVo vo) {
		Map<String, Object> resultMap = new HashMap<>();
		WPost wPost = new WPost();
		if (StringUtils.isNotEmpty(vo.getTypeId())) {
			wPost.setPostType(new WPostType(vo.getTypeId()));
		}
		if (StringUtils.isNotEmpty(vo.getUserId())) {
			wPost.setUser(new WUser(vo.getUserId()));
		}
		if (StringUtils.isNotEmpty(vo.getScont())) {
			wPost.setSearchContent(vo.getScont());
		}
		// 添加排序
		// page.orderBy
		Page<WPost> page = new Page(vo.getPageNo(), 8);
		if (null != vo.getOrderBy()&&vo.getOrderBy().length>0) {
			StringBuffer buffer=new StringBuffer();
			for (int i = 0; i < vo.getOrderBy().length; i++) {
				buffer.append(vo.getOrderBy()[i] + " desc,");
			}
			page.setOrderBy(buffer.toString().substring(0,buffer.length()-1));
		}
		page = postService.findPage(page, wPost);
		resultMap.put("total", page.getTotalPage());
		if (vo.getPageNo() >= page.getLast()) {
			if (page.getList().size() > 0) {
				for (WPost o : page.getList()) {
					o.setCreateDateStr(RelativeDateFormat.format(o.getCreateDate()));
					o.setContent(Encodes.unescapeHtml(o.getContent()));
				}
				resultMap.put(AjaxReturn.DATA, page.getList());
			} else {
				resultMap.put(AjaxReturn.DATA, new ArrayList<>());
			}
			resultMap.put("last", true);
		} else {
			for (WPost o : page.getList()) {
				o.setCreateDateStr(RelativeDateFormat.format(o.getCreateDate()));
				o.setContent(Encodes.unescapeHtml(o.getContent()));
			}
			resultMap.put(AjaxReturn.DATA, page.getList());
			resultMap.put("last", false);
		}
		resultMap.put(AjaxReturn.STATUS, AjaxReturn.SUCCESS);
		return resultMap;
	}

	@RequestMapping(value = "center")
	public String postCenter(String userId, Model model) {
		model.addAttribute("userId", userId);
		WPostType type=new WPostType();
		type.setParent(new WPostType("0"));
		List<WPostType> types=postTypeService.findList(type);
		model.addAttribute("types", types);
		return "modules/postManager/postCenter";
	}

	@RequestMapping(value = "detail/{id}")
	public String detail(@PathVariable String id, Model model) {
		WPost post = postService.get(id);
		post.setContent(Encodes.unescapeHtml(post.getContent()));
		post.setCreateDateStr(RelativeDateFormat.format(post.getCreateDate()));
		model.addAttribute("post", post);
		return "modules/postManager/detail";
	}

	@ResponseBody
	@RequestMapping(value = "comments")
	public Map<String, Object> comments(String comments, String postId) {
		Map<String, Object> resultMap = new HashMap<>();
		if (StringUtils.isBlank(comments) || StringUtils.isBlank(postId)) {
			resultMap.put(AjaxReturn.MSG, "服务器异常啦！");
			return resultMap;
		}
		WComments comments2 = new WComments();
		comments2.setUser(sessionService.getCurrentUser(getCurrentUserId()).getUser());
		comments2.setComments(comments);
		comments2.setPost(new WPost(postId));
		commentsService.save(comments2);
		resultMap.put(AjaxReturn.STATUS, AjaxReturn.SUCCESS);
		return resultMap;
	}

	/**
	 * 赞
	 * 
	 * @param postId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "praise")
	public Map<String, Object> praise(String postId) {
		Map<String, Object> resultMap = new HashMap<>();
		if (StringUtils.isEmpty(postId)) {
			resultMap.put(AjaxReturn.STATUS, AjaxReturn.ERROR);
			resultMap.put(AjaxReturn.MSG, "服务器异常啦!");
			return resultMap;
		}
		WSession session = sessionService.getCurrentUser(getCurrentUserId());
		WPostPraise postPraise = new WPostPraise();
		postPraise.setUser(session.getUser());
		postPraise.setType(AllConst.PRAISE);
		postPraise.setPost(new WPost(postId));
		List<WPostPraise> postPraises = praiseService.findList(postPraise);
		if (postPraises.size() > 0) {
			resultMap.put(AjaxReturn.STATUS, AjaxReturn.ERROR);
			resultMap.put(AjaxReturn.MSG, "你已经赞过了啦!");
			return resultMap;
		}
		praiseService.save(postPraise);
		resultMap.put(AjaxReturn.STATUS, AjaxReturn.SUCCESS);
		return resultMap;
	}

	/**
	 * 踩
	 * 
	 * @param postId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "step")
	public Map<String, Object> step(String postId) {
		Map<String, Object> resultMap = new HashMap<>();
		if (StringUtils.isEmpty(postId)) {
			resultMap.put(AjaxReturn.STATUS, AjaxReturn.ERROR);
			resultMap.put(AjaxReturn.MSG, "服务器异常啦!");
			return resultMap;
		}
		WSession session = sessionService.getCurrentUser(getCurrentUserId());
		WPostPraise postPraise = new WPostPraise();
		postPraise.setUser(session.getUser());
		postPraise.setType(AllConst.STEP);
		postPraise.setPost(new WPost(postId));
		List<WPostPraise> postPraises = praiseService.findList(postPraise);
		if (postPraises.size() > 0) {
			resultMap.put(AjaxReturn.STATUS, AjaxReturn.ERROR);
			resultMap.put(AjaxReturn.MSG, "你已经踩过了啦!");
			return resultMap;
		}
		praiseService.save(postPraise);
		resultMap.put(AjaxReturn.STATUS, AjaxReturn.SUCCESS);
		return resultMap;
	}

	/**
	 * 关注
	 * 
	 * @param userId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "follow")
	public Map<String, Object> follow(String userId) {
		Map<String, Object> resultMap = new HashMap<>();
		if (StringUtils.isEmpty(userId)) {
			resultMap.put(AjaxReturn.STATUS, AjaxReturn.ERROR);
			resultMap.put(AjaxReturn.MSG, "服务器异常啦!");
			return resultMap;
		}
		WSession session = sessionService.getCurrentUser(getCurrentUserId());
		if (session.getUser().getId().equals(userId)) {
			resultMap.put(AjaxReturn.STATUS, AjaxReturn.ERROR);
			resultMap.put(AjaxReturn.MSG, "本人不需要关注啦!");
			return resultMap;
		}
		WFollow follow = new WFollow();
		follow.setFollowUser(new WUser(userId));
		follow.setUser(session.getUser());
		List<WFollow> follows = followService.findList(follow);
		if (follows.size() > 0) {
			resultMap.put(AjaxReturn.STATUS, AjaxReturn.ERROR);
			resultMap.put(AjaxReturn.MSG, "你已经关注了啦!");
			return resultMap;
		}
		followService.save(follow);
		resultMap.put(AjaxReturn.STATUS, AjaxReturn.SUCCESS);
		return resultMap;
	}

	/**
	 * 收藏
	 * 
	 * @param userId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "collect")
	public Map<String, Object> collect(String postId, String userId) {
		Map<String, Object> resultMap = new HashMap<>();
		if (StringUtils.isEmpty(postId) || StringUtils.isEmpty(userId)) {
			resultMap.put(AjaxReturn.STATUS, AjaxReturn.ERROR);
			resultMap.put(AjaxReturn.MSG, "服务器异常啦!");
			return resultMap;
		}
		WSession session = sessionService.getCurrentUser(getCurrentUserId());
		if (session.getUser().getId().equals(userId)) {
			resultMap.put(AjaxReturn.STATUS, AjaxReturn.ERROR);
			resultMap.put(AjaxReturn.MSG, "本人不需要收藏啦!");
			return resultMap;
		}
		WPostCollection collection = new WPostCollection();
		collection.setPost(new WPost(postId));
		collection.setUser(session.getUser());
		List<WPostCollection> collections = collectionService.findList(collection);
		if (collections.size() > 0) {
			resultMap.put(AjaxReturn.STATUS, AjaxReturn.ERROR);
			resultMap.put(AjaxReturn.MSG, "你已经收藏了该帖子!");
			return resultMap;
		}
		collectionService.save(collection);
		resultMap.put(AjaxReturn.STATUS, AjaxReturn.SUCCESS);
		return resultMap;
	}
}
