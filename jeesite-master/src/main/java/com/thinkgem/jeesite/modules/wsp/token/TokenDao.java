package com.thinkgem.jeesite.modules.wsp.token;

import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;

@MyBatisDao
public interface TokenDao {
    public String getToken(String code);
	public int insert(WToken entity);
	/**
	 * 更新数据
	 * @param entity
	 * @return
	 */
	public int update(WToken entity);
	public int delete(WToken entity);
}
