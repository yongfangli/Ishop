<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/webtaglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>小说详情</title>
<%@include file="/WEB-INF/views/include/webHead.jsp"%>
<link	href="${ctxStatic}/modules/font-awesome-4.7.0/css/font-awesome.min.css"	rel="stylesheet">
<meta charset="utf-8">
<style>
.left{
    width: 200px;
    background: aliceblue;
}
.chapter-list{
   margin-left: 40px;
}
.chapter-list li{
   cursor:pointer;
}
</style>
</head>
<body>
<div class="cover hid"></div>
    <div id="app">
	<div class="content">
      <%@include file="/WEB-INF/views/include/header.jsp" %>
      <div id="alert" class="hid"></div>
	<div class="d-content">
	
	<div class='left'>
	<h3>${novel.title}</h3>
    <ul class="chapter-list">
    <c:forEach items="${novel.chapters}" var='c'>
    <li cid="${c.id}" onclick="showDetail(this)">${c.title}</li>
    </c:forEach>
    </ul>	
	
	</div>
	
	</div>
	<input value="${nid}" type="hidden" id="nid"/>
	</div>
</div>

<script>
var nid=$("#nid").val();//章节id
var detail=new Vue({
	el:'.d-content',
	data:{
		novel:[]
	},
	created:function(){
		var vm=this;
	},
	methods:{
		
	}
})

 
</script>
</body>
</html>