<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/webtaglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>首页</title>
<%@include file="/WEB-INF/views/include/webHead.jsp"%>
<link href="${ctxStatic}/modules/front/css/home/index.css"
	type="text/css" rel="stylesheet" />
<meta charset="utf-8">

</head>
<script>
	
</script>
<body>
<div class="cover hid"></div>
    <div id="app">
	<div class="content">
	<div class="header">
	 <div class="logo"><img src="${ctxStatic}/modules/front/image/logo.png" alt="logo"/><span>唯森</span></div>
	 <div class="rig"><span class="span-btn"><a href="${ctx}/post/postInput?style=basic">发帖</a></span><span class="span-btn"><a href="#">登录</a></span><span @click="goRegister" class="span-btn">注册</span></div>
	</div>
	<div class="toggle-bar">
	           <ul class="f-nav">
	           <li @mouseover="showchild($event)" @mouseout="fadway($event)">游戏<ul  class="c-nav hid"><li>英雄联盟</li><li>极限求生</li><li>王者荣耀</li></ul></li>
	           <li @mouseover="showchild($event)" @mouseout="fadway($event)">动漫<ul  class="c-nav hid"><li>日本动漫</li></ul></li>
	           </ul>
	</div>
	
	
	</div>

  <div class="innerHtml hid">
   <span id="close" @click="closeframe">×</span>
   <iframe class="framk"  id="frame"   src=""> 
   </iframe> 
  </div>
  </div>
<script>
$(function(){
	var wwid=window.screen.width;
	$("#close").css("left",(wwid-330)/2+300+"px");
	$(".c-nav").mouseover(function(){
		$(this).removeClass("hid");
	});
	$(".c-nav").mouseout(function(){
		$(this).addClass("hid");
	});
	$(".c-nav li").mouseover(function(e){
		e.stopPropagation();
		e.preventDefault(); 
	});
	window.onresize=function(){
		var wwid=window.screen.width;
		$("#close").css("left",(wwid-334)/2+300+"px");
	}
})
var vm = new Vue({
	el : '#app',
	data : {
	},
	methods : {
		goRegister:function(){
			$(".cover").removeClass("hid");
			$(".innerHtml").removeClass("hid");
			$("#frame").attr("src",'${ctx}/system/goRegister');
		},
		closeframe:function(){
			$(".cover").addClass("hid");
			$(".innerHtml").addClass("hid");
		},
		showchild:function(e){
			var tar=$(e.target);
			tar.find("ul").removeClass("hid");
		},
		fadway:function(e){
			var tar=$(e.target);
			tar.find("ul").addClass("hid");
		},
		show:function(e){
			var tar=$(e.target);
			tar.parent("c-nav").removeClass("hid");
		},
		fadwaythis:function(e){
			var tar=$(e.target);
			tar.parent("c-nav").addClass("hid");
		}
	  }
   })


</script>
</body>
</html>