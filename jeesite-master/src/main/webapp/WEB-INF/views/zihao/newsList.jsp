<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>活动列表</title>
	<%@include file="/WEB-INF/views/include/zihaohead.jsp" %>
	 <link rel="stylesheet"  type="text/css"  href="${staticzihao}/css/list.css"/>
<link rel="stylesheet"  type="text/css"  href="${staticzihao}/css/indexother.css"/>
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
<div class="wrap">
  <ul class="list">
  <c:forEach items="${page.list}" var="news">
    
  <li>
   <a href="${ctxzihao}/companyNews/detail/${news.id}">
      <img class="img" src="${news.img}"/>
      <span class="title"> ${news.title}</span><span class="date">${news.dateFormat}</span><span><img class="icon" src="${staticzihao}/image/view.png"/>${news.viewCount }</span>
   </a>
  </li>
    
  </c:forEach>
  </ul>
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