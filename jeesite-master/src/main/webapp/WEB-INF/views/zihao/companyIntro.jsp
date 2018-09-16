<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>首页</title>
	<%@include file="/WEB-INF/views/include/zihaohead.jsp" %>
	<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0">
	<link rel="stylesheet"  type="text/css"  href="${staticzihao}/css/index.css"/>
	<link rel="stylesheet"  type="text/css"  href="${staticzihao}/css/company.css"/>

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
		
		
		
		
	})
	var myScroll;

		function loaded () {
			myScroll = new IScroll('#wrapper', { scrollX: true, freeScroll: true });
		}

		document.addEventListener('touchmove', function (e) { e.preventDefault(); }, isPassive() ? {
			capture: false,
			passive: false
		} : false);
	</script>
</head>
<body onload="loaded()">
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
<div  class="content">
   <div class="innercontent" >
   <div class="column top">
   <h3>公司简介</h3>
   <p>${office.remarks}</p>
   </div>
   <div class="column center">
    <h3>公司历程</h3>
    <c:forEach items="${progressList}" var="progress"> 
   <dl>
   <dt>
   ${progress.title}
   <p><fmt:formatDate value="${progress.happenDate}" pattern="yyyy-MM-dd"/></p>
   </dt>
   <dd> ${progress.content}</dd>
   </dl>
   </c:forEach>
   </div>
    <div class="column bottom1">
      <h3>公司业务</h3>
      <p>
      ${office.businessArea}
      </p>
    </div>
   </div>
</div> 
<div class="bottom">
	<div class="contact">
	     <div class="person">
			 <p>联系人：李小军</p>
			 <p>联系电话：	15221156989</p>
			 <p>Copyright 2018 有限公司</p>
		 </div>
	</div>
</div>
</body>
</html>