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
    height: 700px;
    overflow-y: scroll;
    background: white;
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
.operate{
       height: 30px;
}
.rig{
   height: 800px;
}
.color{
   width: 40px;
    height: 20px;
    display: inline-block;
    margin-top: 3px;
}
.setting{
    width: 250px;
    position: absolute;
    right: 25%;
    background: white;
}
.hid{
  display:none;
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
<div class="setting hid"><label>字体颜色:</label>
<span class='color' style='background: #2cd8d8;'></span>
<span class='color' style='background: #ff9900;'></span>
<span class='color' style='background: red;'></span>
<span class='color' style='background: black;'></span>
</div>
<div class="rig">
<div class='operate'> <i class="fa fa-cog fa-2x" onclick="showSetting()"></i></div>
	<div class="panel"></div>
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
function showDetail(e){
	chapterId=$(e).attr("cid");
	if(!$(e).hasClass('express')){
		$(e).addClass('express').siblings().removeClass('express');
	};
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
function showSetting(){
	if($('.setting').hasClass('hid')){
		$('.setting').removeClass('hid');
	}else{
		$('.setting').addClass('hid');
	}
}
 
 $(function(){
	$('.color').click(function(){
		var col=$(this).css('background-color');
		$('.panel').css('color','');
		$('.panel').css('color',col);
		$('.setting').addClass('hid');
	}); 
 })
</script>
</body>
</html>