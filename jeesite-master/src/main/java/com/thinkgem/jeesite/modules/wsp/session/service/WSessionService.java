/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.wsp.session.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mysql.cj.protocol.ServerSession;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.wsp.session.entity.WSession;
import com.thinkgem.jeesite.modules.wsp.session.dao.WSessionDao;

/**
 * session管理Service
 * @author lyf
 * @version 2018-10-04
 */
@Service
@Transactional(readOnly = true)
public class WSessionService extends CrudService<WSessionDao, WSession> {

	public WSession get(String id) {
		return super.get(id);
	}
	
	public List<WSession> findList(WSession wSession) {
		return super.findList(wSession);
	}
	
	public Page<WSession> findPage(Page<WSession> page, WSession wSession) {
		return super.findPage(page, wSession);
	}
	
	@Transactional(readOnly = false)
	public void save(WSession wSession) {
		super.save(wSession);
	}
	
	@Transactional(readOnly = false)
	public void delete(WSession wSession) {
		super.delete(wSession);
	}
	
	@Transactional(readOnly = false)
	public void deleteExpiredSessionInfo() {
		
	}
	
	public WSession getCurrentUser(String sessionId) {
		return dao.getBySessionId(sessionId);
	}
}