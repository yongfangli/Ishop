package com.thinkgem.jeesite.modules.movies.controller;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.gridfs.GridFsOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.drew.lang.StringUtil;
import com.mongodb.gridfs.GridFSFile;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.postManeger.controller.MyBaseController;
import com.thinkgem.jeesite.modules.postManeger.cost.AjaxReturn;
import com.thinkgem.jeesite.modules.wsp.menu.entity.WMenu;
import com.thinkgem.jeesite.modules.wsp.mypicture.entity.Mypicture;
import com.thinkgem.jeesite.modules.wsp.mypicture.service.MypictureService;
import com.thinkgem.jeesite.modules.wsp.session.service.WSessionService;
import com.thinkgem.jeesite.modules.wsp.user.service.WUserService;

@Controller
@RequestMapping(value = "${webPath}/movies/")
public class MovieController extends MyBaseController {
	@Autowired
	private GridFsOperations gridFsTemplate;
	@Autowired
	private MypictureService pictureService;
	@Autowired
	private WSessionService sessionService;
	@RequestMapping("make")
	public String moviesMake() {
         return "modules/movies/make";
	}
	@ResponseBody
	@RequestMapping(value = "pictureUpload")
	public Map<String, Object> pictureUpload(@RequestParam("files") MultipartFile[] files,String topic,String keyword,String desc) throws IOException {
		Map<String, Object> resultMap = new HashMap<>();
		if(StringUtils.isEmpty(topic)) {
			resultMap.put(AjaxReturn.STATUS, AjaxReturn.ERROR);
			resultMap.put(AjaxReturn.MSG, "主题不能为空!");
			return resultMap;
		}
		Mypicture pMypicture=new Mypicture();
		pMypicture.setTopic(topic);
		pMypicture.setKeyword(keyword);
		pMypicture.setDesc(desc);
		pMypicture.setUser(sessionService.getCurrentUser(getSession().getId()).getUser());
		pMypicture.setUpdateDate(new Date());
		if(files.length>0) {
			String ids="";
			for (MultipartFile multipartFile : files) {
				GridFSFile gridfile = gridFsTemplate.store(multipartFile.getInputStream(),
						multipartFile.getOriginalFilename(), multipartFile.getContentType(), null);
				String fileId = String.valueOf(gridfile.getId());
				ids+=fileId+",";
			}
			pMypicture.setImgs(ids);
		}
		pictureService.save(pMypicture);
		resultMap.put(AjaxReturn.STATUS,AjaxReturn.SUCCESS);
		return resultMap;
	}
	
	@RequestMapping("audioTest")
	public String audioTest() {
		return "modules/movies/audio";
	}
}
