<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/webtaglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>首页</title>
<%@include file="/WEB-INF/views/include/webHead.jsp"%>
<link	href="${ctxStatic}/modules/front/css/mobile/postnameger/postCenter.css"	type="text/css" rel="stylesheet" />
<meta charset="utf-8">
<style>
.type{
    font-style: italic;
    position: absolute;
    right: 100px;
        color: #64d5ca;
}
.item{
    line-height: 40px;
    border-bottom: 1px dashed #ffd0a6;
}
.person{
    font-style: italic;
    position: absolute;
    right: 200px;
    color: #64d5ca;
    background: #ffe1df;
    border-radius: 10px;
    margin-top: 5px;
}
.title:hover{
  cursor:pointer;
  color:red;
}
.detail{
  padding: 0 20px;
}
.detail img{
  max-width:1000px;
}
</style>
</head>
<body>
<div class="cover hid"></div>
    <div id="app">
	<div class="content">
      <%@include file="/WEB-INF/views/include/header.jsp" %>
       <div id="alert" class="hid"></div>
	  <div class="detail">
	  ${post.content}
	  </div>
	    
	</div>

  </div>
</body>
</html>