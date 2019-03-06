<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/webtaglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>问题反馈</title>
<%@include file="/WEB-INF/views/include/webHead.jsp"%>
<link	href="${ctxStatic}/modules/front/css/help/help.css"	type="text/css" rel="stylesheet" />
<meta charset="utf-8">
<style>
.fcontent{
     width: 600px;
     margin: 0 auto;
}
</style>
</head>
<body>
<div class="cover hid"></div>
    <div id="app">
	<div class="content">
      <%@include file="/WEB-INF/views/include/header.jsp" %>
       <div id="alert" class="hid"></div>
	  <div class="fcontent">
	       <div class='cln'><label>问题描述:</label><textarea rows="" cols=""></textarea></div>
	       <div class='cln'><label>改善建议:</label><textarea rows="" cols=""></textarea></div>
	       <div class="cln"><input class='sub' value='提交'></div>
	  </div>
	</div>

  </div>
</body>
<script src="${ctxStatic}/modules/front/js/project/feedback/feedback.js"></script>
</html>