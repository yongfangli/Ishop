<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>新闻详情</title>
	<%@include file="/WEB-INF/views/include/zihaohead.jsp" %>
	 <link rel="stylesheet"  type="text/css"  href="${ctxStatic}/modules/zihao/pc/css/activity.css"/>
<link rel="stylesheet"  type="text/css"  href="${ctxStatic}/modules/zihao/pc/css/index.css"/>

<body>
<div class="top">
    <div class="right">
        <div class="logo"><img src="${staticzihao}/logo.png" alt="logo"/></div>
        <div class="title"><h3>上海子浩传播有限公司</h3></div>
    </div>
    <div class="left">
        <ul class="nav">
            <li><a href="${ctxzihao}/index">首页</a></li>
            <li><a href="${ctxzihao}/activity/list">公司介绍</a></li>
            <li><a href="indexother.html">产品</a></li>
            <li><a href="indexother.html">联系我们</a></li>
        </ul>
    </div>
</div>
<div class="contents">
    <h3 class="newstitle">${news.title}</h3>
    <hr style=" height:2px;border:none;border-top:4px solid #185598;"/>
    <div class="attr">
        <div><label>发布时间：</label><span  id="publishDate"><fmt:formatDate value="${news.createDate}" pattern="yyyy/MM/dd HH:mm"/></span></div>
    </div>
    <div class="detail">
            　　${news.content}
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