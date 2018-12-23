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
	     <!-- <div class="operate"><span  class="toggle" title='更换编辑器版本'><i class="toggle1 tog" @click='changeStyle(1)'></i><i class="toggle2 " @click='changeStyle(2)'></i></span></div> -->
		<!-- <div class="editor"  contenteditable="true"></div> -->
		<vue-html5-editor :content="content" v-model="content" :height="300"></vue-html5-editor>
		<!-- 帖子类型选择 -->
		<div class="container"><template v-if="typeList" v-for="t in typeList"><span v-if="typeId==t.id" class="choose selected" @mouseover="choose(t.id,event)">{{t.name}}</span><span v-else class="choose" @mouseover="choose(t.id,event)">{{t.name}}</span></template></div>
		
		<%-- <div class="upload">
			<embed src="${ctxStatic}/images/addfile.svg" type="image/svg+xml" />
			<input class="file" v-model="file" type="file"
				@change='showFileAndUpload(event)'>
		</div> --%>
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
Vue.use(VueHtml5Editor,{ // 全局组件名称，使用new VueHtml5Editor(options)时该选项无效 
    // global component name
    name: "vue-html5-editor",
    // 是否显示模块名称，开启的话会在工具栏的图标后台直接显示名称
    // if set true,will append module name to toolbar after icon
    showModuleName: false,
    // 自定义各个图标的class，默认使用的是font-awesome提供的图标
    // custom icon class of built-in modules,default using font-awesome
    icons: {
        text: "fa fa-pencil",
        color: "fa fa-paint-brush",
        font: "fa fa-font",
        align: "fa fa-align-justify",
        list: "fa fa-list",
        link: "fa fa-chain",
        unlink: "fa fa-chain-broken",
        tabulation: "fa fa-table",
        image: "fa fa-file-image-o",
        hr: "fa fa-minus",
        eraser: "fa fa-eraser",
        undo: "fa-undo fa",
        "full-screen": "fa fa-arrows-alt",
        info: "fa fa-info",
    },
    // 配置图片模块
    // config image module
    image: {
        // 文件最大体积，单位字节  max file size
        sizeLimit: 512 * 1024,
        // 上传参数,默认把图片转为base64而不上传
        // upload config,default null and convert image to base64
        upload: {
            url: null,
            headers: {},
            params: {},
            fieldName: {}
        },
        // 压缩参数,默认使用localResizeIMG进行压缩,设置为null禁止压缩
        // compression config,default resize image by localResizeIMG (https://github.com/think2011/localResizeIMG)
        // set null to disable compression
        compress: {
            width: 1600,
            height: 1600,
            quality: 80
        },
        // 响应数据处理,最终返回图片链接
        // handle response data，return image url
        uploadHandler(responseText){
            //default accept json data like  {ok:false,msg:"unexpected"} or {ok:true,data:"image url"}
            var json = JSON.parse(responseText)
            if (!json.ok) {
                alert(json.msg)
            } else {
                return json.data
            }
        }
    },
    // 语言，内建的有英文（en-us）和中文（zh-cn）
    //default en-us, en-us and zh-cn are built-in
    language: "zh-cn",
    // 自定义语言
    i18n: {
        //specify your language here
        "zh-cn": {
            "align": "对齐方式",
            "image": "图片",
            "list": "列表",
            "link": "链接",
            "unlink": "去除链接",
            "table": "表格",
            "font": "文字",
            "full screen": "全屏",
            "text": "排版",
            "eraser": "格式清除",
            "info": "关于",
            "color": "颜色",
            "please enter a url": "请输入地址",
            "create link": "创建链接",
            "bold": "加粗",
            "italic": "倾斜",
            "underline": "下划线",
            "strike through": "删除线",
            "subscript": "上标",
            "superscript": "下标",
            "heading": "标题",
            "font name": "字体",
            "font size": "文字大小",
            "left justify": "左对齐",
            "center justify": "居中",
            "right justify": "右对齐",
            "ordered list": "有序列表",
            "unordered list": "无序列表",
            "fore color": "前景色",
            "background color": "背景色",
            "row count": "行数",
            "column count": "列数",
            "save": "确定",
            "upload": "上传",
            "progress": "进度",
            "unknown": "未知",
            "please wait": "请稍等",
            "error": "错误",
            "abort": "中断",
            "reset": "重置"
        }
    },
    // 隐藏不想要显示出来的模块
    // the modules you don't want
    hiddenModules: [],
    // 自定义要显示的模块，并控制顺序
    // keep only the modules you want and customize the order.
    // can be used with hiddenModules together
    visibleModules: [
        "text",
        "color",
        "font",
        "align",
        "list",
        "link",
        "unlink",
        "tabulation",
        "image",
        "hr",
        "eraser",
        "undo",
        "full-screen",
        "video"
        //"info",
    ],
    // 扩展模块，具体可以参考examples或查看源码
    // extended modules
    modules: {
        //omit,reference to source code of build-in modules
    }});


	var reg = /^\S*\.(?:png|jpe?g|bmp|gif)$/;
	var patrn = /\w+(.flv|.rvmb|.mp4|.avi|.wmv)$/;
	
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
			changeStyle : function(val) {
				var vm = this;
				if (val == 1) {
					window.location.href="${ctx}"+"/post/postInput";
				} else if (val == 2) {
					return;
				}
			},
			choose:function(id,e){
				var vm=this;
				var span=$(e.target);
				span.addClass('selected').siblings().removeClass('selected');
				vm.typeId=id;
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
				formData.append('typeId',vm.typeId);
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