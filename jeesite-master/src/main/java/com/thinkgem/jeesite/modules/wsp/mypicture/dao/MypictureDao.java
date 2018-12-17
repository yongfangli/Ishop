/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.wsp.mypicture.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.wsp.mypicture.entity.Mypicture;

/**
 * 我的图片DAO接口
 * @author lyf
 * @version 2018-12-02
 */
@MyBatisDao
public interface MypictureDao extends CrudDao<Mypicture> {
	
}