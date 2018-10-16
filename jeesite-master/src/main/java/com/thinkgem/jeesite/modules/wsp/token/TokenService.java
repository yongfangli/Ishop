package com.thinkgem.jeesite.modules.wsp.token;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class TokenService {
	@Autowired
	protected TokenDao dao;

	public String getToken(String code) {
		return dao.getToken(code);
	}

	public String getValidateCode(String sessionId) {
        return dao.getValidateCode(sessionId);
	}

	@Transactional(readOnly = false)
	public void update(WToken token) {
		dao.update(token);
	}

	@Transactional(readOnly = false)
	public void save(WToken token) {
		dao.delete(token);
		dao.insert(token);
	}

}
