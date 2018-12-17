package com.thinkgem.jeesite.modules.movies.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.postManeger.controller.MyBaseController;
import com.thinkgem.jeesite.modules.postManeger.cost.AjaxReturn;
import com.thinkgem.jeesite.modules.wsp.mypicture.entity.Mypicture;
import com.thinkgem.jeesite.modules.wsp.mypicture.service.MypictureService;

@Controller
@RequestMapping(value = "${webPath}/cartoon/")
public class CartoonController extends MyBaseController {
	@Autowired
	private MypictureService mypictureService;
	@RequestMapping("index")
	public String index() {
		return "modules/cartoon/index";
	}
	@ResponseBody
	@RequestMapping("cartoonList")
	public Map<String, Object> cartoonList(String searchContent,Integer pageNo){
		Integer pageSize=10;
		Map<String, Object> resultMap = new HashMap<>();
		Page<Mypicture> mPage=new Page<>(pageNo,pageSize);
		mPage=mypictureService.findPage(mPage, new Mypicture());
		if(mPage.getList().size()>0) {
			for (Mypicture pic : mPage.getList()) {
				String file=pic.getImgs();
				if(StringUtils.isNoneEmpty(file)) {
					pic.setImgs(file.split(",")[0]);
				}
			}
		}
		resultMap.put(AjaxReturn.STATUS, AjaxReturn.SUCCESS);
		resultMap.put(AjaxReturn.DATA, mPage);
		return resultMap;
	}
}
