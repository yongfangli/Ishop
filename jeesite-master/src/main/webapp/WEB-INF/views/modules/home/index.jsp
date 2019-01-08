<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/webtaglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>首页</title>
<%@include file="/WEB-INF/views/include/webHead.jsp"%>
<meta charset="utf-8">
<style>
.n-item img{
    float: left;
    width: 60px;
    height: 100px;
}
.novel-list{
    border: 1px solid #cceef9;
    width: 600px;
    display: flex;
    justify-content: space-around;
}
.n-item{
   width: 140px;
   cursor:pointer;
}

</style>
</head>
<body>
<div class="cover hid"></div>
    <div id="app">
	<div class="content">
      <%@include file="/WEB-INF/views/include/header.jsp" %>
      <div class="i-content">
	<div class="crumb">
	<h3 class="title">最新小说</h3>
	<div class="novel-list">
	<template v-for="n in novels">
	<div class="n-item" :nid="n.id" onclick='showNovelDetail(this)'>
	<img :src="'${ctx}/post/file/'+n.cover" />
	<div class="n-desc">
	<div><span>{{n.title}}</span></div>
	<div><label>作者:</label><span>{{n.user.nickname}}</span></div>
	<div><span>{{n.updateStr}}</span></div>
	<div><span>{{n.fontnum}}字</span></div>
	</div>
	</div>
	</template>
	</div>
	</div>
	</div>
    </div>
  </div>
<script>
$(function(){
	var wwid=window.screen.width;
	$("#close").css("left",(wwid-330)/2+300+"px");
	window.onresize=function(){
		var wwid=window.screen.width;
		$("#close").css("left",(wwid-334)/2+300+"px");
	}
})
var index= new Vue({
	el : '#innerHtml',
	methods : {
		closeframe:function(){
			$(".cover").addClass("hid");
			$(".innerHtml").addClass("hid");
		}
	  }
   })
var novel=new Vue({
	el:'.i-content',
	data:{
		novels:[]
	},
	created:function(){
		var vm=this;
		$.ajax({
			url:ctx+'/novel/novelList',
			type:'post',
			dataType:'json',
			data:{},
			success:function(data){
				if(data.status=='success'){
					vm.novels=data.data;
				}else{
				    Msg.show(data.msg);
				}
			}
		})
	},
	methods:{
	}
})

function showNovelDetail(e){
	var nid=$(e).attr('nid');
	window.location.href=ctx+'/novel/detail?nid='+nid;
}

</script>
</body>
</html>