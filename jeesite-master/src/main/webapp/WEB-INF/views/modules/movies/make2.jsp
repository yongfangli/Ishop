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
<div class="cln"><span>背景颜色:</span><input id="bcolor" type="color" v-model="backgroundColor" @change="changeBackgroundColor()"/></div>
</div>
<div class="left">
<p class="title">形状选择</p>
<tabel>
<tr><td>直线</td><td>正方形</td></tr>
</tabel>
</div>
<div class="left">
<p class="title">线条大小</p>

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
		backgroundColor:'#fff',
		height:300
	},
	created:function(){
		var vm=this;
		canvas.css("border","1px solid"+vm.borderColor);
		canvas.css("background",vm.backgroundColor);
		canvas.width(vm.width);
		canvas.height(vm.height);
	},
	methods:{
		changeBorderColor:function(){
			var vm=this;
			$("#canvas").css("border","1px solid"+vm.borderColor);
		},
		changeBackgroundColor:function(){
			var vm=this;
			$("#canvas").css("background",vm.backgroundColor);
		},
		resize:function(){
			  var vm=this;
			  var canvas1 = document.getElementById('canvas');
			  canvas1.style.width = parseInt(vm.width)+"px";
			  canvas1.style.height = parseInt(vm.height)+"px";
		}
	}
	
});
$(function(){
	var canv=document.getElementById("canvas");
	var ctx=canv.getContext("2d");
	 ctx.strokeStyle = "blue";
	 ctx.lineWidth = 10;
	 ctx.lineTo(20,100);
	  //执行画线
     ctx.stroke();
	canv.onmousedown=function(e){
        //划线到当前客户端的x与y座标
        ctx.lineTo(e.clientX, e.clientY);
        //执行画线
        ctx.stroke();
    }
})
</script>
</body>
</html>