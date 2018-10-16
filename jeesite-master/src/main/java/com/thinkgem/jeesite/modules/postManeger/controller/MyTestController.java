package com.thinkgem.jeesite.modules.postManeger.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletResponse;

import org.activiti.engine.impl.util.IoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.thinkgem.jeesite.common.security.Digests;
import com.thinkgem.jeesite.common.utils.Encodes;
import com.thinkgem.jeesite.modules.postManeger.baiduApi.service.TextService;
import com.thinkgem.jeesite.modules.postManeger.message.EmailMessageService;
import com.thinkgem.jeesite.modules.wsp.token.TokenService;

@Controller
@RequestMapping("/test")
public class MyTestController extends MyBaseController{
	@Autowired
	private TokenService tokenService;
	@Autowired
	private TextService textService;
	@Autowired
	private EmailMessageService messageServer;
	@RequestMapping("/test")
	public void test(HttpServletResponse response) {
		File file=new File("C:\\Users\\rukas.li\\Desktop\\zxlvideo.mp4");
		InputStream inputStream=null;
		OutputStream outputStream=null;
		try {
			inputStream=new FileInputStream(file);
			outputStream=response.getOutputStream();
		/*	byte[] read=new byte[100];
			int n=0;
			while((n=inputStream.read(read))!=-1) {
				outputStream.write(read,0,n);
			}*/
			byte[] read=new byte[1000000];
			int n=0;
			if((n=inputStream.read(read))!=-1) {
				outputStream.write(read,0,n);
			}
			outputStream.flush();
		} catch (Exception e) {
			e.printStackTrace();
			
		}finally {
				IoUtil.closeSilently(inputStream);
		}
		
	}
	@RequestMapping("/goTest")
	public String goTest() {
		//测试发送邮件
		messageServer.sendTest(true);
		return "modules/postManager/test";
	}
	@RequestMapping("/goTestEmail")
	public String goEmail() {
		return "modules/postManager/email";
	}
	
	public static void main(String[] arg) throws UnsupportedEncodingException {
		String pass=Encodes.encodeBase64(Digests.md5("kobe2008".getBytes()));
	}
}
