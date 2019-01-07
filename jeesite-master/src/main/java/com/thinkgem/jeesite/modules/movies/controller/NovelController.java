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
import com.thinkgem.jeesite.modules.wsp.chapter.entity.WNovelChapter;
import com.thinkgem.jeesite.modules.wsp.chapter.service.WNovelChapterService;
import com.thinkgem.jeesite.modules.wsp.menu.entity.WMenu;
import com.thinkgem.jeesite.modules.wsp.mypicture.entity.Mypicture;
import com.thinkgem.jeesite.modules.wsp.mypicture.service.MypictureService;
import com.thinkgem.jeesite.modules.wsp.novel.entity.WNovel;
import com.thinkgem.jeesite.modules.wsp.novel.service.WNovelService;
import com.thinkgem.jeesite.modules.wsp.session.entity.WSession;
import com.thinkgem.jeesite.modules.wsp.session.service.WSessionService;
import com.thinkgem.jeesite.modules.wsp.user.service.WUserService;

/**
 * 小说
 * 
 * @author Rukas.Li
 *
 */
@Controller
@RequestMapping(value = "${webPath}/novel/")
public class NovelController extends MyBaseController {
	@Autowired
	private GridFsOperations gridFsTemplate;
	@Autowired
	private MypictureService pictureService;
	@Autowired
	private WNovelService novelService;
	@Autowired
	private WNovelChapterService novelChapterService;
	@Autowired
	private WSessionService sessionService;
	@RequestMapping("make")
	public String moviesMake() {
		return "modules/novel/make";
	}
	@RequestMapping("edit")
	public String edit() {
		return "modules/novel/edit";
	}
	@ResponseBody
	@RequestMapping(value = "saveNovel")
	public Map<String, Object> saveNovel(MultipartFile file, String title) throws IOException {
		Map<String, Object> resultMap = new HashMap<>();
		if(StringUtils.isEmpty(title)) {
			resultMap.put(AjaxReturn.STATUS, AjaxReturn.ERROR);
			resultMap.put(AjaxReturn.MSG, "请输入标题!");
			return resultMap;
		}
		WNovel novel=new WNovel();
		novel.setTitle(title);
		if (null!=file) {
			GridFSFile gridfile = gridFsTemplate.store(file.getInputStream(), file.getOriginalFilename(),
					file.getContentType(), null);
			String fileId = String.valueOf(gridfile.getId());
			novel.setCover(fileId);
		}
		WSession session=sessionService.getCurrentUser(getCurrentUserId());
		novel.setUser(session.getUser());
		novelService.save(novel);
		resultMap.put(AjaxReturn.STATUS, AjaxReturn.SUCCESS);
		return resultMap;
	}
	@ResponseBody
	@RequestMapping(value = "myNovel")
	public Map<String, Object> myNovel() throws IOException {
		Map<String, Object> resultMap = new HashMap<>();
		WNovel novel=new WNovel();
		WSession session=sessionService.getCurrentUser(getCurrentUserId());
		novel.setUser(session.getUser());
		List<WNovel> novels=novelService.findList(novel);
		for (WNovel n : novels) {
			n.setChapters(novelChapterService.getChapterByNovel(n.getId()));
		}
		resultMap.put(AjaxReturn.DATA, novels);
		resultMap.put(AjaxReturn.STATUS, AjaxReturn.SUCCESS);
		return resultMap;
	}
	
	@ResponseBody
	@RequestMapping(value = "saveChapter")
	public Map<String, Object> saveChapter(String novelId,String ctitle,String pre,String next) throws IOException {
		Map<String, Object> resultMap = new HashMap<>();
		if(StringUtils.isEmpty(ctitle)) {
			resultMap.put(AjaxReturn.STATUS, AjaxReturn.ERROR);
			resultMap.put(AjaxReturn.MSG, "请输入标题!");
			return resultMap;
		}
		if(StringUtils.isEmpty(novelId)) {
			resultMap.put(AjaxReturn.STATUS, AjaxReturn.ERROR);
			resultMap.put(AjaxReturn.MSG, "小说id不能为空!");
			return resultMap;
		}
		WNovelChapter novelChapter=new WNovelChapter();
		novelChapter.setNovel(new WNovel(novelId));
		novelChapter.setTitle(ctitle);
		novelChapter.setPre(pre);
		novelChapter.setNext(next);
		novelChapterService.save(novelChapter);
		resultMap.put(AjaxReturn.STATUS, AjaxReturn.SUCCESS);
		resultMap.put(AjaxReturn.DATA, novelChapter.getId());
		return resultMap;
	}
}
