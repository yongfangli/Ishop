<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>联系我们</title>
	<%@include file="/WEB-INF/views/include/zihaohead.jsp" %>
	<link rel="stylesheet"  type="text/css"  href="${staticzihao}/css/index.css"/>
	<link rel="stylesheet"  type="text/css"  href="${staticzihao}/css/contactus.css"/>
	<script>
	$(function(){
		$(".triangle").click(function(){
			if($(".lang-select").css("display")!="none"){
				$(".lang-select").hide();
			}else{
				$(".lang-select").show();
				}
		})
		$(".lang-select li").click(function(){
			$(".lang label").text($(this).text());
			$(".lang-select").hide();
		})
		
		$("#contactus").submit(function(){
        	
        	if($("#name").val()==""||$("#name").val()==null){
        		$("#namemsg").show();
        		return false;
        	}
        	 if($("#phone").val()==""||$("#phone").val()==null){
        		$("#phonemsg").show();
        		return false;
        	}
        	if(!checkPhone($("#phone").val())){
        		$("#phonemsg").empty().html("手机号码格式有误，请重新输入");
        		$("#phonemsg").show();
        		return false;
        	}
        	$.ajax({
				type:"post",
				async:false,
				url:"${ctxzihao}/contactus/save",
				dataType:"json",
				cache:false,
				data:$('#contactus').serializeArray(),
				success : function(data) {
					if(data.code==200){
						alert(data.msg);
						$("#phone").val("");
						$("#name").val("");
						$("#detail").val("");
					}
				}
			}); 
        	return false;
        })
		
	})
	function hidmsg(){
        	$("#namemsg").hide();
        	$("#phonemsg").hide();
        }  
        function checkPhone(phone){ 
            if(!(/^1[34578]\d{9}$/.test(phone))){ 
                return false; 
            } else
            	return true;
        }
	</script>
</head>
<body>
<div class="header">
<div class="clearfix">
    <div class="logo"><img src="${staticzihao}/logo.png" alt="logo"/><span>某某有限公司</span></div>
    <div class="right">
    <div class="searchform">
    <form action="${ctxzihao}/search">
    <input class="search" placeholder="请输入搜索内容" name="content">
    <input class="submit" type="button" id="submit">
    </form>
    </div>
    <div class="langwrap"><span class="lang"><label>简体中文</label>
    <ul class="lang-select">
    <li>EN</li>
    <li>简体中文</li>
    </ul></span>
    <span class="triangle"></span>
    </div>
    </div>
    </div>
</div> 
<div class="nav">
<ul class="toggle">
<li><a href="${ctxzihao}/index">首页</a></li>
 <li><a href="${ctxzihao}/companyIntro">业务范畴</a></li>
 <li><a href="${ctxzihao}/contactus">联系我们</a></li>
</ul>
</div> 
<div class="content">
    <div class="contactUs">
	<div class="contactBody">
    <div class="notice"><label>如果你有什么需求，请输入你的信息，我们会尽快回复</label></div>
    <div class="contactForm">
    <form action="${ctxzihao}/contactus/save" method="post" id="contactus">
       <div> <input placeholder="你的名字" oninput="hidmsg()" name="name" id="name" type="text"><div><span id="namemsg" style="display:none;color:red;margin-left:5px;font-size:0.5rem">名字不能为空</span></div></div>
		<div> <input placeholder="你的联系电话" oninput="hidmsg()" name="phone" id="phone" type="text"><div><span id="phonemsg" style="display:none;color:red;margin-left:5px;font-size:0.5rem">电话不能为空</span></div></div>
		<div> <textarea id="detail" rows="4" cols="22" placeholder="详情" name="detail" ></textarea></div>
		<div> <input class="submit1"  value="提交"  type="submit"></div>
    </form>
	</div>
    </div>
</div>
</div> 
<div class="bottom">
	<div class="contact">
	     <div class="person">
			 <p>联系人：李小军</p>
			 <p>联系电话：	15221156989</p>
			 <p>
				 Copyright 2018 有限公司</p>
		 </div>

	</div>

</div>
</body>
</html>