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
.d-content{
    display: flex;
    justify-content: flex-start;
}
.panel{
    width: 900px;
    margin-left: 50px;
    max-height: 800px;
    overflow-y: scroll;
    background: white;
    color: black !important;
}
.panel::-webkit-scrollbar {
        width: 10px;     
        height: 1px;
    }
.panel::-webkit-scrollbar-thumb {
        border-radius: 10px;
         -webkit-box-shadow: inset 0 0 5px rgba(0,0,0,0.2);
        background: #31A2E2;
    }
.panel::-webkit-scrollbar-track {
        -webkit-box-shadow: inset 0 0 5px rgba(0,0,0,0.2);
        border-radius: 10px;
        background: #EDEDED;
    }
.panel span{
    color: black;
}
.express{
   background: #fad1e3;
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
	<div class="panel"></div>
	
	
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
window.onload=function(){
	
}
function showDetail(e){
	chapterId=$(e).attr("cid");
	if(!$(e).hasClass('express'){
		$(e).addClass('express').siblings().
	}
	$.ajax({
		url:ctx+'/novel/getChapterContent',
		type:'post',
		dataType:'json',
		data:{'chapterId':chapterId},
		success:function(data){
			if(data.status=='success'){
				$('.panel').empty().html(data.data.content);
			}else{
			    Msg.show(data.msg);
			}
		}
	})
}
 
</script>
</body>
</html>