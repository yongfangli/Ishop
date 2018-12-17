<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/webtaglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>首页</title>
<%@include file="/WEB-INF/views/include/webHead.jsp"%>
<link	href="${ctxStatic}/modules/front/css/mobile/postnameger/postCenter.css"	type="text/css" rel="stylesheet" />
<meta charset="utf-8">

</head>
<body>
<div class="cover hid"></div>
    <div id="app">
	<div class="content">
      <%@include file="/WEB-INF/views/include/header.jsp" %>
       <div id="alert" class="hid"></div>
	  <div class="vertical">
	  <ul class="t-cln">
	  <template v:if="typeList" v-for="(t,index) in typeList">
	   <li v-if="index==0" class="selected" @mouseover="selected(t.id,event)">{{t.name}}</li>
	  <li v-else @mouseover="selected(t.id,event)">{{t.name}}</li>
	  </template>
	  </ul>
	  
	    <div class="rigcontent">
	  <ul class="postList">
	  <template v-if="postList" v-for="(p, index) in postList">
	   <li v-if="index==0" class="postItem" v-html="p.content">
	   </li>
	   <li v-else v-html="p.content">
	   </li>
	   </template>
	  </ul>
	  </div>
	  
	  </div> 
	  
	
	           
	 
	    
	</div>

  </div>
</body>
 <script src="${ctxStatic}/modules/front/js/project/postCenter.js"></script>
</html>