package com.thinkgem.jeesite.modules.mybusiness.front;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.thinkgem.jeesite.common.front.JsonResult;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.cms.utils.RelativeDateFormat;
import com.thinkgem.jeesite.modules.mybusiness.activity.service.ActivityService;
import com.thinkgem.jeesite.modules.mybusiness.companynews.entity.CompanyNews;
import com.thinkgem.jeesite.modules.mybusiness.companynews.service.CompanyNewsService;
import com.thinkgem.jeesite.modules.mybusiness.companyprogress.entity.CompanyProgress;
import com.thinkgem.jeesite.modules.mybusiness.companyprogress.service.CompanyProgressService;
import com.thinkgem.jeesite.modules.mybusiness.contactus.entity.Contactus;
import com.thinkgem.jeesite.modules.mybusiness.contactus.service.ContactusService;
import com.thinkgem.jeesite.modules.sys.entity.Office;
import com.thinkgem.jeesite.modules.sys.service.OfficeService;
@RequestMapping(value = "${zihao}")
@Controller
public class ZihaoIndexController extends BaseController {
	@Autowired
	private ActivityService activityService;
	@Autowired
	private CompanyNewsService cnewsService;
	@Autowired
	private ContactusService contactusService;
	@Autowired
    private OfficeService officeService;
	@Autowired
    private CompanyProgressService companyProgressService;
	/**
	 * 首页
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/index")
	public String index(Model model) {
		model.addAttribute("activityList", activityService.findRecentlyActivity(8));
		model.addAttribute("newsList", cnewsService.findRecentlyNews(12));
		return "zihao/index";

	}

	/**
	 * 活动详情
	 * 
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/activity/detail/{id}")
	public String activityDetail(Model model, @PathVariable String id) {
		model.addAttribute("activity", activityService.get(id));
		return "zihao/activityDetail";

	}

	/**
	 * 活动列表
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/activity/list")
	public String activityList(Model model) {
		return "zihao/activityList";

	}

	/**
	 * 产品界面
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/product")
	public String product(Model model) {
		return "zihao/product";

	}

	/**
	 * 新闻列表
	 */
	@RequestMapping(value = "/companyNews/list")
	public String newsList(Model model, @RequestParam(required = false, defaultValue = "1") Integer pageNo,
			@RequestParam(required = false, defaultValue = "15") Integer pageSize) {
		Page<CompanyNews> page = new Page<CompanyNews>(pageNo, pageSize);
		cnewsService.findPage(page, new CompanyNews());
		model.addAttribute("page", page);
		for (CompanyNews c : page.getList()) {
			c.setDateFormat(RelativeDateFormat.format(c.getCreateDate()));
		}
		return "zihao/newsList";

	}

	/**
	 * 新闻列表
	 */
	@RequestMapping(value = "/companyNews/detail/{id}")
	public String newsDetail(Model model, @PathVariable String id) {
		model.addAttribute("news", cnewsService.get(id));
		return "zihao/newsDetail";

	}


	/**
	 * 公司详情
	 */
	@RequestMapping(value = "/companyIntro")
	public String companyIntro(Model model) {
		//在数据库里面把公司的id设为1
		Office office=officeService.get("1");
	    model.addAttribute("office",office);
	    CompanyProgress companyProgress=new CompanyProgress();
	    companyProgress.setCompany(office);
	    List<CompanyProgress> companyProgresses=companyProgressService.findList(companyProgress);
	    model.addAttribute("progressList",companyProgresses);
	    return "zihao/companyIntro";
	}
	/**
	 * 联系我们添加
	 */
	@RequestMapping(value = "/contactus")
	public String saveContactus() {
		return "zihao/contactus";
	}
	
	/**
	 * 联系我们添加
	 */
	@RequestMapping(value = "/contactus/save")
	@ResponseBody
	public JsonResult<String> saveContactus(Contactus contactus) {
		JsonResult<String> result = JsonResult.getDefault();
		contactusService.save(contactus);
		result.setBody(null);
		result.setMsg("提交成功");
		return result;
	}
}
