<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/webtaglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>小说编辑</title>
<%@include file="/WEB-INF/views/include/webHead.jsp"%>
<link	href="${ctxStatic}/modules/font-awesome-4.7.0/css/font-awesome.min.css"	rel="stylesheet">
<meta charset="utf-8">
<style>
.left{
    padding-left: 20px;
    width: 210px;
    background: #31A2E2;
    float: left;
    border-radius:10px;
}
.e-parent li{
   line-height: 25px;
   background: #31A2E2;
   color: white;
}
.hid{
   display:none;
}
.e-child{
   margin-left: 20px;
}
.e-child li{
   line-height: 30px;
    margin-top: 10px;
}
.inputBtn{
   width: 120px;
   outline:none;
}
.rig{
    float: right;
    width: 800px;
    height: 600px;
    margin-right: 85px;
}
/* 着重 */
.express{
    color: white;
    width: 143px;
    outline: none;
    border: 0px;
    background: #31A2E2;
    pointer-events: none;
}
.editor{
    background: #fff; 
    width: 800px;
    height: 550px;
    outline: none;
    overflow-y: scroll;
}
.editor::-webkit-scrollbar {
        width: 10px;     
        height: 1px;
    }
.editor::-webkit-scrollbar-thumb {
        border-radius: 10px;
         -webkit-box-shadow: inset 0 0 5px rgba(0,0,0,0.2);
        background: #31A2E2;
    }
.editor::-webkit-scrollbar-track {
        -webkit-box-shadow: inset 0 0 5px rgba(0,0,0,0.2);
        border-radius: 10px;
        background: #EDEDED;
    }
.options{
    height: 32px;
}
.curItem{
    background: #92cdea;
    border-top-right-radius: 12px;
    border-bottom-right-radius: 12px;
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
	<i class="fa fa-minus-square hid" onclick='showChild(this)'></i><input :value="n.title" class='inputBtn express'/><i class="fa fa-plus-square hid" @click="addChapter(event)"></i>
	<i class="fa fa-pencil fa-fw" onclick='editNovel(this)'></i>
	<ul class="e-child">
	<template v-for="c in n.chapters">
	<li :cid="c.id" onclick="showEdit(this)"><input type='text' :value="c.title" class='inputBtn express' style='width: 120px;'>
	<i class='fa fa-minus-square hid' onclick='removeChapter(this)'></i>
	<i class="fa fa-pencil fa-fw hid" onclick='editChapter(this)'></i>
	</li>
	</template>
	</ul>
	</li>
	</template>
	</ul>
	</div>
	<!-- 编辑器 -->
	<div class="rig">
	<div class="options"><i class="fa fa-save fa-2x" onClick="saveContent()"></i><i class="fa fa-cog fa-2x" onclick="showSetting()"></i></div>
	<div class="editor" contenteditable="plaintext-only">
	
	</div>
	</div>
	
	</div>
	
	</div>
</div>

<script>
var chapterId='';//章节id
var selectInput='';//显示的章节
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
				//显示第一个章节的内容
				if(vm.novel.length>0){
				   var first=vm.novel[0];
			    if(null!=first){
				   var firstc=first.chapters[0].content;
				   chapterId=first.chapters[0].id;
				   $('.editor').html(firstc);
				  }
				}
			}else{
			    Msg.show(res.data.msg);
			}
		}, function(res) {
			Msg.show(res.status);
		});
	},
	watch:{
		novel:function(){
		    this.$nextTick(function(){
		    	$('.e-parent li:first ul li:first input').addClass('curItem');
		    	selectInput=$('.e-parent li:first ul li:first input');
		    });
		}
	},
	methods:{
		showAdd:function(e){
			$(e.target).find('i').removeClass('hid');
		},
		hidAdd:function(e){
			$(e.target).find('i').addClass('hid');
		},
		addChapter:function(e){
			$(e.target).next().next().append("<li><input type='text' class='inputBtn' style='width: 120px;'><i class='fa fa-minus-square' onclick='removeChapter(this)'></i><i class='fa fa-plus-square' onclick='addChapter(this)' ></i></li>");
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
					$(e).after("<i class='fa fa-pencil fa-fw' onclick='editChapter(this)'></i>");
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
	//if(inp.hasClass('express')){
		//判断是不是点击状态
		if(inp.hasClass('curItem')){
			chapterId='';
			selectInput='';
			$('.editor').empty();
		}
		var curId=$(e).parent().attr("cId");
		var pre=$(e).parent().prev();
		var prevId='';
		var nextId='';
		if(undefined==pre){
			prevId='';
		}else{
			if(undefined!=pre.attr("cId")){
			 prevId=pre.attr("cId");
			}
		}
		var next=$(e).parent().next();
		if(undefined==next){
			 nextId='';
		}else{
			if(undefined!=next.attr("cId")){
			 nextId=next.attr("cId");
			}
		}
		$.ajax({
			url:ctx+'/novel/deleteChapter',
			type:'post',
			dataType:'json',
			data:{'chapterId':curId,'preId':prevId,'afterId':nextId},
			success:function(data){
				if(data.status=='success'){
					$(e).parent().find('input').addClass('express');
					$(e).parent().remove();
				}else{
				    Msg.show(data.msg);
				}
			}
		})
	
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
function showEdit(e){
	chapterId=$(e).attr("cid");
	if(!$(e).find('input').hasClass('curItem')&&''==selectInput){
		selectInput=$(e).find('input');
		$(e).find('input').addClass('curItem');
	}else if(!$(e).find('input').hasClass('curItem')&&''!=selectInput){
		selectInput.removeClass('curItem');
		selectInput=$(e).find('input');
		$(e).find('input').addClass('curItem');
	}
	$.ajax({
		url:ctx+'/novel/getChapterContent',
		type:'post',
		dataType:'json',
		data:{'chapterId':chapterId},
		success:function(data){
			if(data.status=='success'){
				$('.editor').empty().html(data.data.content);
			}else{
			    Msg.show(data.msg);
			}
		}
	})
}
//保存章节文本
function saveContent(){
	var cont=$('.editor').html();
	$.ajax({
		url:ctx+'/novel/updateChapterContent',
		type:'post',
		dataType:'json',
		data:{'chapterId':chapterId,'content':cont},
		success:function(data){
			if(data.status=='success'){
				Msg.show('保存成功！');
			}else{
			    Msg.show(data.msg);
			}
		}
	})
}
 
 //编辑章节
 function editChapter(e){
	 $(e).prev().prev().removeClass('express');
	 if(!$(e).prev().hasClass('fa-plus-square')){
		 $(e).prev().after("<i class='fa fa-plus-square' onclick='updateChapter(this)' ></i>");
	 }
	
 }
 //编辑小说
 function editNovel(e){
	 $(e).removeClass('fa-pencil').addClass('fa-save');
	 $(e).prev().prev().removeClass('express');
	 $(e).attr('onclick','saveNovel(this)');
 }
 
 //保存小说
 function saveNovel(e){
	 var tex=$(e).prev().prev().val();
	 var nid=$(e).parent().attr('parentId');
	 $.ajax({
			url:ctx+'/novel/updateNovel',
			type:'post',
			dataType:'json',
			data:{'title':tex,'nId':nid},
			success:function(data){
				if(data.status=='success'){
					Msg.show('修改成功！');
					 $(e).removeClass('fa-save').addClass('fa-pencil');
					 $(e).attr('onclick','editNovel(this)');
					 $(e).prev().prev().addClass('express');
				}else{
				    Msg.show(data.msg);
				}
			}
		})
 }
 
 //更新章节
 function updateChapter(e){
	 var txt=$(e).siblings('input').val();
	 var chapterId=$(e).parent().attr("cId");
	 $.ajax({
			url:ctx+'/novel/updateChapter',
			type:'post',
			dataType:'json',
			data:{'chapterId':chapterId,'title':txt},
			success:function(data){
				if(data.status=='success'){
					Msg.show('保存成功！');
					$(e).parent().find('input').addClass('express');
					$(e).remove();
				}else{
				    Msg.show(data.msg);
				}
			}
		})
 }
 
 function showSetting(e){
	 
 }
 
</script>
</body>
</html>