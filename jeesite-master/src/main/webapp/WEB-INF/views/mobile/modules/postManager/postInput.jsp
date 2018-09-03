<%@ page contentType="text/html;charset=UTF-8" %>
  <%@ include file="/WEB-INF/views/include/webtaglib.jsp"%> 
<!DOCTYPE html>
<html>
<head>
<%@include file="/WEB-INF/views/include/webHead.jsp" %>
<link href="${ctxStatic}/modules/front/mobile/postnameger/postInput.css" type="text/css" rel="stylesheet" />
<meta charset="utf-8">
<!-- 强制让文档与设备的宽度保持1：1 -->
  <meta name="viewport" content="width=device-width, initial-scale=1, minimum-scale=1, maximum-scale=1, user-scalable=no">
    <!-- 删除默认的苹果工具栏和菜单栏 -->
   <meta name="apple-mobile-web-app-capable" content="yes">
   <!-- 在web app应用下状态条（屏幕顶部条）的颜色 -->
    <meta name="apple-mobile-web-app-status-bar-style" content="black" />
<title>帖子录入</title>
<style>

</style>
</head>
<body>
<div class="content" id="content">
   <div class="form">
   <div class="big-clnm"><label>帖子内容:</label><textarea rows="3" cols="20"></textarea></div>
   <div class="upload-cont"></div>
   </div>
</div>
<script src="serverpath/vue.js"></script>
<script src="serverpath/vue-html5-editor.js"></script>
</body>
</html>