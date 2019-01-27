package com.thinkgem.jeesite.modules.postManeger.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsOperations;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.mongodb.gridfs.GridFSFile;
import com.thinkgem.jeesite.modules.postManeger.cost.AjaxReturn;
import com.thinkgem.jeesite.modules.wsp.session.entity.WSession;
import com.thinkgem.jeesite.modules.wsp.session.service.WSessionService;
import com.thinkgem.jeesite.modules.wsp.user.entity.WUser;
import com.thinkgem.jeesite.modules.wsp.user.service.WUserService;

@Controller
@RequestMapping(value = "${webPath}/personal")
public class PersonController extends MyBaseController {
	@Autowired
	private WUserService userService;
	@Autowired
	private WSessionService wssioonService;
	@Autowired
	private GridFsOperations gridFsTemplate;

	@RequestMapping("")
	public String index(Model model) {
		WSession user = wssioonService.getCurrentUser(getCurrentUserId());
		if(null!=user){
			model.addAttribute("user", userService.get(user.getUser()));
		}
		return "modules/personal/personalindex";
	}

	@ResponseBody
	@RequestMapping(value = "changePortail")
	public Map<String, Object> uploadFile(MultipartFile file) throws IOException {
		Map<String, Object> resultMap = new HashMap<>();
		if (file.getContentType().contains("image")) {
			WSession wSession = wssioonService.getCurrentUser(getCurrentUserId());
			GridFSFile gridfile = gridFsTemplate.store(file.getInputStream(), file.getOriginalFilename(),
					file.getContentType(), null);
			String fileId = String.valueOf(gridfile.getId());
			WUser user=userService.get(wSession.getUser().getId());
			if(StringUtils.isNotBlank(user.getPortrait())) {
				//刪除mongodb
				Query query = new Query(Criteria.where("_id").is(new ObjectId(user.getPortrait())));
				gridFsTemplate.delete(query);
			}
			user.setPortrait(fileId);
			userService.save(user);
			resultMap.put(AjaxReturn.STATUS, AjaxReturn.SUCCESS);
			resultMap.put(AjaxReturn.DATA, fileId);
		} else {
			resultMap.put(AjaxReturn.STATUS, AjaxReturn.ERROR);
			resultMap.put(AjaxReturn.MSG, "文件格式不对");
		}
		return resultMap;
	}
	
	@ResponseBody
	@RequestMapping(value = "updateUser")
	public Map<String, Object> updateUser(@RequestBody WUser user) {
		Map<String, Object> resultMap = new HashMap<>();
		WSession session=wssioonService.getCurrentUser(getCurrentUserId());
		WUser userd=userService.get(session.getUser().getId());
		userd.setEmail(user.getEmail());
		//判断重复性
		List<WUser> users=userService.findList(userd);
		if(users.size()>0) {
			for (WUser wUser : users) {
				//当前用户
				if(!wUser.getId().equals(session.getUser().getId())) {
					resultMap.put(AjaxReturn.STATUS, AjaxReturn.ERROR);
					resultMap.put(AjaxReturn.MSG, "用户已存在!");
					return resultMap;
				}
			}
		}
		userd.setNickname(user.getNickname());
		userd.setPhone(user.getPhone());
		userd.setBirthday(user.getBirthday());
		userd.setConstellation(user.getConstellation());
		
		userService.save(userd);
		resultMap.put(AjaxReturn.STATUS, AjaxReturn.SUCCESS);
		return resultMap;
	}
	
	@RequestMapping(value = "exit")
	public String exit() {
		WSession session=wssioonService.getCurrentUser(getCurrentUserId());
		wssioonService.delete(session);
		return "redirect:" + "/";
	}
}
