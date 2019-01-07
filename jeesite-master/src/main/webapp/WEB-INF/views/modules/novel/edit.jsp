<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/webtaglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>首页</title>
<%@include file="/WEB-INF/views/include/webHead.jsp"%>
<link	href="${ctxStatic}/modules/font-awesome-4.7.0/css/font-awesome.min.css"	rel="stylesheet">
<meta charset="utf-8">
<style>
.left{
    padding-left: 20px;
    width: 180px;
    background: #e8eef1;
    float: left;
}
.e-parent li{
   line-height: 25px;
}
.hid{
   display:none;
}
.e-child{
   margin-left: 20px;
}
.e-child li{
   line-height: 30px;
}
.inputBtn{
   width: 120px;
   outline:none;
}
.rig{
    float: right;
    width: 800px;
    height: 600px;
    background: #fff;
    outline: none;
    margin-right: 85px;
}
.express{
    width: 120px;
    outline: none;
    border: 0px;
    background: #E8EEF1;
    pointer-events: none;
}
</style>
</head>
<body>
<div class="cover hid"></div>
    <div id="app">
	<div class="content">
      <%@include file="/WEB-INF/views/include/header.jsp" %>
      <div id="alert" class="hid"></div>
	<div class="e-content">
	<div class="left">
	<ul class="e-parent">
	<template v-for="n in novel">
	<li @mouseenter="showAdd(event)"  @mouseleave="hidAdd(event)"  :parentId="n.id">
	<i class="fa fa-minus-square hid" onclick='showChild(this)'></i>{{n.title}}<i class="fa fa-plus-square hid" @click="addChapter(event)"></i>
	<ul class="e-child">
	<template v-for="c in n.chapters">
	<li :cid="c.id" onclick="showEdit(this)"><input type='text' :value="c.title" class='inputBtn express' style='width: 120px;'>
	<i class='fa fa-minus-square' onclick='removeChapter(this)'></i>
	</li>
	</template>
	</ul>
	</li>
	</template>
	</ul>
	</div>
	<div class="rig" contenteditable='true'>
	
	</div>
	</div>
	
	</div>
</div>

<script>
var novel=new Vue({
	el:'.e-content',
	data:{
		novel:[]
	},
	created:function(){
		var vm=this;
		vm.$http.post(ctx+'/novel/myNovel', {}).then(function(res) {
			if(res.data.status=='success'){
				vm.novel=res.data.data;
			}else{
			    Msg.show(res.data.msg);
			}
		}, function(res) {
			Msg.show(res.status);
		});
	},
	methods:{
		showAdd:function(e){
			$(e.target).find('i').removeClass('hid');
		},
		hidAdd:function(e){
			$(e.target).find('i').addClass('hid');
		},
		addChapter:function(e){
			$(e.target).next().append("<li><input type='text' class='inputBtn' style='width: 120px;'><i class='fa fa-minus-square' onclick='removeChapter(this)'></i><i class='fa fa-plus-square' onclick='addChapter(this)' ></i></li>");
		}
	}
})

function addChapter(e){
	var txt=$(e).siblings('input').val();
	var novelid=$(e).parent().parent().parent().attr('parentid');
	var pre=$(e).parent().prev();
	var preId='';
	var nextId='';
	if(undefined!=pre[0]){
		preId=$(pre).attr('cid');
	}
	var next=$(e).parent().next();
	if(undefined!=next[0]){
		nextId=$(next).attr('cid');
	}
	if(txt!=''){
		//上传服务器
		$.ajax({
			url:ctx+'/novel/saveChapter',
			type:'post',
			dataType:'json',
			data:{'novelId':novelid,'pre':preId,'next':nextId,'ctitle':txt},
			success:function(data){
				if(data.status=='success'){
					$(e).parent().find('input').addClass('express');
					$(e).parent().attr('cid',data.data);
					$(e).parent().attr('onclick','showEdit(this)');
					$(e).remove();
					Msg.show('添加成功!');
				}else{
				    Msg.show(data.msg);
				}
			}
		})
	}else{
		Msg.show('章节标题不能为空!');
	}
}
//删除章节
function removeChapter(e){
	event.preventDefault();
	event.stopPropagation();
	var inp=$(e).parent().find('input');
	if(inp.hasClass('express')){
		alert('不能删除!');
	}else{
		$(e).parent().remove();
	}
	
}
//点击小说显示下级或者隐藏下级
function showChild(e){
	if($(e).hasClass('fa-minus-square')){
		$(e).removeClass('fa-minus-square').addClass('fa-plus-square');
		$(e).parent().children('ul').addClass('hid');
	}else{
		$(e).removeClass('fa-plus-square').addClass('fa-minus-square');
		$(e).parent().children('ul').removeClass('hid');
	}
}
//点击显示右边的编辑器
function showEdit(){
	alert('顯示');
}
$(function(){
	
})
</script>
</body>
</html>