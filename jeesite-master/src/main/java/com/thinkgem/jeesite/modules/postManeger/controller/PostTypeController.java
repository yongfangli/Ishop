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
import com.thinkgem.jeesite.modules.wsp.post.service.WPostTypeService;
import com.thinkgem.jeesite.modules.wsp.user.entity.WUser;
import com.thinkgem.jeesite.modules.wsp.user.service.WUserService;

@Controller
@RequestMapping(value = "${webPath}/postType/")
public class PostTypeController extends MyBaseController {
	@Autowired
	private WPostTypeService postTypeService;

	@ResponseBody
	@RequestMapping(value = "listJson")
	public Map<String, Object> typeListJson() {
		Map<String, Object> resultMap = new HashMap<>();
		WPostType type=new WPostType();
		type.setParent(new WPostType("0"));
		List<WPostType> types=postTypeService.findList(type);
		resultMap.put(AjaxReturn.STATUS,AjaxReturn.SUCCESS);
		resultMap.put(AjaxReturn.DATA, types);
		return resultMap;
	}
}
