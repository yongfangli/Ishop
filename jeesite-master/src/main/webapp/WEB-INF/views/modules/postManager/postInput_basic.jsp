<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/webtaglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<%@include file="/WEB-INF/views/include/webHead.jsp"%>
<meta charset="utf-8">
<link	href="${ctxStatic}/modules/front/css/mobile/postnameger/postInput.css"	type="text/css" rel="stylesheet" />
<link	href="${ctxStatic}/modules/font-awesome-4.7.0/css/font-awesome.min.css"	rel="stylesheet">
<title>帖子录入</title>
</head>

<body>
    
	<div id="app">
	<div class="content">
	    <div id="alert" class="hid">信息提示</div>
	   <%@include file="/WEB-INF/views/include/header.jsp" %>
	   <div id="editor">
	   
	   <div class="operate"><span  class="toggle" title='更换编辑器版本'>
	   <c:choose>
	   <c:when test="${style!=null}">
	   <i class="toggle1  tog" @click='changeStyle(1)'></i><i class="toggle2" @click='changeStyle(2)'></i></span>
	   </c:when>
	   <c:otherwise>
	   <i class="toggle1" @click='changeStyle(1)'></i><i class="toggle2 tog" @click='changeStyle(2)'></i></span>
	   </c:otherwise>
	   </c:choose>
	   </div>
	   
	   <div class="cln"><label>主题:</label><input type="text" id="topic" ></div>
	   
		<div class="editor"  contenteditable="true"></div> 
		
		<!-- 帖子类型选择 -->
		<div class="container"><template v-if="typeList" v-for="t in typeList"><span v-if="typeId==t.id" class="choose selected" @mouseover="choose(t.id,event)">{{t.name}}</span><span v-else class="choose" @mouseover="choose(t.id,event)">{{t.name}}</span></template></div>
		
		<div class="upload">
			<embed src="${ctxStatic}/images/addfile.svg" type="image/svg+xml" />
			<input class="file" v-model="file" type="file"
				@change='showFileAndUpload(event)'>
		</div> 
		<div class="imgs">
			<ul class="imglist">
				<template v-if="imgfiles" v-for="option in imgfiles">

				<li v-if="option.type=='pic'" class='img-item'><img
					v-bind:src="option.src" /></li>
				<li v-else class='img-item'><video class="video"
						controls="controls">
						<source v-bind:src="option.src">
					</video></li>
				</template>
			</ul>
		</div>
		<div class="bottom" @click='commit'>发布</div>
		</div>
		</div>
	</div>

</body>
<script>


	
	var vm = new Vue({
		el : '#editor',
		data : {
			content : '',
			file : '',
			imgfiles : [],
            files:[],
            typeList:[],
            typeId:''
		},
		created:function(){
			var vm=this;
			var url='${ctx}'+'/postType/listJson';
			vm.$http.get(url, null).then(function(res) {
				if(res.data.status=='success'){
					vm.typeList=res.data.data;
					vm.typeId=vm.typeList[0].id;
				}else{
					 Msg.show(res.data.msg);
				}
			}, function(res) {
				alert(res.status)
			});
		},
		methods : {
			 changeStyle:function(v){
		         	var vm=this;
		         	if(v==1){
		         		window.location.href = "${ctx}"	+ "/post/postInput";
		         	}else if(v==2){
		         		window.location.href = "${ctx}"	+ "/post/postInput?style=basic";
		         	}
		         },
			choose:function(id,e){
				var vm=this;
				var span=$(e.target);
				span.addClass('selected').siblings().removeClass('selected');
				vm.typeId=id;
			},
			showFileAndUpload : function(e) {
				var vm = this;
				var taget = e.target;
				if (taget.files.length > 0) {
					var fileo = new Object();
					var file = taget.files[0];

					var result = vm.testFileTypeAndSize(file);

					if (result != '') {
						alert(result);
						return;
					}
					vm.files.push(file);
					if (reg.exec(file.name)) {
						fileo.type = 'pic';
						fileo.src = vm.getObjectURL(file);
					} else {
						fileo.type = 'vid';
						fileo.src = vm.getObjectURL(file);
					}
					vm.imgfiles.push(fileo);
					
				}
			},
			testFileTypeAndSize : function(file) {
				var file_sub = file.name;
				var size = file.size;
				var max_size = 1024 * 200 * 1024;
				if ((!reg.exec(file_sub) && !patrn.exec(file_sub))) {
					return "文件格式错误";
				} else if (size > max_size) {
					return "文件太大";
				} else {
					return '';
				}
			},
			commit : function(){
				var vm=this;
				var uploadUrl = "${ctx}" + "/post/postSave";
				console.info(vm.files);
				var html=$(".editor").text();
				var encode_html=encodeURIComponent(html);
				console.info(encode_html);
				//上传服务器
				let formData = new FormData();
				if(vm.files.length>0){
					for(var i in vm.files){
						formData.append('files', vm.files[i]);
					}
				}
				formData.append('content',encode_html);
				formData.append('typeId',vm.typeId);
				formData.append('topic',$("#topic").val());
				vm.$http.post(uploadUrl, formData).then(function(res) {
					if(res.data.status=='success'){
                        Msg.show("发布成功!");
						window.location.href = ctx
							+ "/post/center";
					}else{
						 Msg.show(res.data.msg);
					}
				}, function(res) {
					alert(res.status)
				});
			},
			getObjectURL : function(file) {
				var url = null;
				if (window.createObjectURL != undefined) { // basic
					url = window.createObjectURL(file);
				} else if (window.URL != undefined) { // mozilla(firefox)
					url = window.URL.createObjectURL(file);
				} else if (window.webkitURL != undefined) { // webkit or chrome
					url = window.webkitURL.createObjectURL(file);
				}
				return url;
			}

		}

	})
</script>
</html>