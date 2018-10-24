<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/webtaglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>动画制作</title>
<%@include file="/WEB-INF/views/include/webHead.jsp"%>
<link	href="${ctxStatic}/modules/front/css/mobile/movies/make.css"	type="text/css" rel="stylesheet" />
<meta charset="utf-8">

</head>
<body>
<div class="content">
<div class="operator">
<div class="left">
<p class="title">画板设置</p>
<div class="cln"><span>宽度:</span><input v-model="width" @change="resize" class="setting-input" id="wid" type="text" oninput="value=value.replace(/[^\d]/g,'')"></div>
<div class="cln"><span>高度:</span><input v-model="height" @change="resize" class="setting-input" id="height" type="text" oninput="value=value.replace(/[^\d]/g,'')"></div>
<div class="cln"><span>边框颜色:</span><input id="color" type="color" v-model="borderColor" @change="changeBorderColor()"/></div>
</div>
<div class="left">
<p class="title">形状选择</p>
<tabel>
<tr><td>直线</td><td>正方形</td></tr>
</tabel>
</div>
</div>
    <canvas id="canvas">
      您的浏览器不支持Canvas
    </canvas>
</div>
<script>
var canvas=$("#canvas");
var make=new Vue({
	el:'.content',
	data:{
		borderColor:'#000000',
		width:1200,
		height:300
	},
	created:function(){
		var vm=this;
		canvas.css("border","1px solid"+vm.borderColor);
		canvas.width(vm.width);
		canvas.height(vm.height);
	},
	methods:{
		changeBorderColor:function(){
			var vm=this;
			$("#canvas").css("border","1px solid"+vm.borderColor);
		},
		resize:function(){
			var vm=this;
			 var canvas1 = document.getElementById('canvas');
			  canvas1.style.width = parseInt(vm.width)+"px";
			  canvas1.style.height = parseInt(vm.height)+"px";
		}
	}
	
})
</script>
</body>
</html>