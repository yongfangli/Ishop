<%@ page contentType="text/html;charset=UTF-8" %>
  <%@ include file="/WEB-INF/views/include/webtaglib.jsp"%> 
<!DOCTYPE html>
<html>
<head>
<%@include file="/WEB-INF/views/include/webHead.jsp" %>
<meta charset="utf-8">
<title>测试视频</title>
<style>
    


      
</style>
</head>
<body>

<div class="" id="app">
<video  class="video" controls preload>
						<source src="${pageContext.request.contextPath}/test/test">
</video>
</div>

<script>

</script>
</body>
</html>