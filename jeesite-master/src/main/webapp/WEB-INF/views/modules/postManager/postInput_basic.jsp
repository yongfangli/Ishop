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
	    <div id="alert" class="hid">信息提示</div>
	   <%@include file="/WEB-INF/views/include/header.jsp" %>
	   <div id="editor">
	     <div class="operate"><span  class="toggle" title='更换编辑器版本'><i class="toggle1 tog" @click='changeStyle(1)'></i><i class="toggle2 " @click='changeStyle(2)'></i></span></div>
		<div class="editor"  contenteditable="true"></div>
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

</body>
<script>
	var reg = /^\S*\.(?:png|jpe?g|bmp|gif)$/;
	var patrn = /\w+(.flv|.rvmb|.mp4|.avi|.wmv)$/;
	
	var vm = new Vue({
		el : '#editor',
		data : {
			content : '',
			file : '',
			imgfiles : [],
            files:[]
		},
		methods : {
			changeStyle : function(val) {
				var vm = this;
				if (val == 1) {
					window.location.href="${ctx}"+"/post/postInput";
				} else if (val == 2) {
					return;
				}
			},
			test:function(){
				Msg.setMsg("msg");
				Msg.show();
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
				vm.$http.post(uploadUrl, formData).then(function(res) {
					if(res.data.status=='success'){
                        Msg.show("发布成功!");
						window.location.href = "${ctx}"
							+ "/post/personalCenter";
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