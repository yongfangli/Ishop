<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>活动详情</title>
	<%@include file="/WEB-INF/views/include/zihaohead.jsp" %>
	<link rel="stylesheet"  type="text/css"  href="${staticzihao}/css/activity.css"/>
<link rel="stylesheet"  type="text/css"  href="${staticzihao}/css/index.css"/>

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
<div class="contents">
    <h3 class="newstitle">${activity.name}</h3>
    <hr style=" height:2px;border:none;border-top:4px solid #185598;"/>
    <div class="attr">
        <div><label>发布时间：</label><span  id="publishDate"><fmt:formatDate value="${activity.startTime}" pattern="yyyy/MM/dd HH:mm"/>--<fmt:formatDate value="${activity.endTime}" pattern="yyyy/MM/dd HH:mm"/></span></div>

        <div><label>发布地址：</label><span id="address">${activity.address}</span></div>
    </div>
    <div class="detail">
            　　${activity.content }
    </div>

</div>
</div>
<div class="bottom">
    <div class="contact">
        <div class="person">
            <p>联系人：李小军</p>
            <p>联系电话：	15221156989</p>
            <p>
                Copyright 2018 上海子浩传播有限公司</p>
        </div>

    </div>

</div>
</body>
</html>