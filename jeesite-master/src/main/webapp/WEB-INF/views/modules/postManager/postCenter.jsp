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
    right: 200px;
        color: #64d5ca;
}
.date{
    position: absolute;
    right: 100px;
    color: #79796c;
}
.item{
    line-height: 40px;
    border-bottom: 1px dashed #ffd0a6;
}
.person{
    font-style: italic;
    position: absolute;
    right: 300px;
    color: #64d5ca;
    background: #ffe1df;
    border-radius: 10px;
    margin-top: 5px;
}
.title:hover{
  cursor:pointer;
  color:red;
}
</style>
</head>
<body>
<div class="cover hid"></div>
    <div id="app">
	<div class="content">
      <%@include file="/WEB-INF/views/include/header.jsp" %>
       <div id="alert" class="hid"></div>
	  <div class="vertical">
	  <template v-for="o in postList">
	  <div class="item" >
	  <p class="title"  @click="detail(o.id)">{{o.title}}</p>
	  <div class="meta"><span class="type">{{o.postType.name}}</span><span class="person">{{o.user.nickname}}</span><span class="date">{{o.createDateStr}}</span></div>
	  </div>
	  </template>
	  </div> 
	  
	
	           
	 
	    
	</div>

  </div>
</body>
 <script src="${ctxStatic}/modules/front/js/project/postCenter.js"></script>
</html>