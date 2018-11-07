<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/webtaglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>动画制作</title>
<%@include file="/WEB-INF/views/include/webHead.jsp"%>
<link	href="${ctxStatic}/modules/front/css/mobile/movies/make.css"	type="text/css" rel="stylesheet" />
<style>
.qianbi{
cursor:url('${ctxStatic}/modules/canvas/qianbi.png');
}
</style>
<meta charset="utf-8">

</head>
<body>
<div id='app'>
<div><img src="${ctxStatic}/modules/canvas/qianbi.png" style="width: 30px;" @click="linepic"></div>
</div>
<canvas id='canvas' style="background:#fff" >

</canvas>
</body>
<script>
var clickX = new Array();
var clickY = new Array();
var clickDrag = new Array();
var isLine=false;//是否点击了铅笔

var canvas=document.getElementById("canvas");
var ctx = canvas.getContext('2d');
var isMouseDown=false;
canvas.width=400;
canvas.height=300;


var app=new Vue({
	el:"#app",
	data:{
		color:'#fff',
	},
	methods:{
		linepic:function(){//铅笔画图
			isLine=true;
		$("body").css("cursor","url('${ctxStatic}/modules/canvas/qianbi.cur')-60 31,auto");
			changeQianbi();
		}
	}
});

function addClick(x, y, dragging)
{
  clickX.push(x);
  clickY.push(y);
  clickDrag.push(dragging);
}
function redraw(){
	  canvas.width = canvas.width; // Clears the canvas
	  ctx.strokeStyle = "blue";
	 // ctx.lineJoin = "round";
	  ctx.lineWidth = 1;
	 
	  for(var i=0; i < clickX.length; i++)
	  {
		  ctx.beginPath();
	    if(clickDrag[i] && i){//当是拖动而且i!=0时，从上一个点开始画线。
	    	ctx.moveTo(clickX[i-1], clickY[i-1]);
	     }else{
	    	 ctx.moveTo(clickX[i]-1, clickY[i]);
	     }
	    ctx.lineTo(clickX[i], clickY[i]);
	    ctx.closePath();
	    ctx.stroke();
	  }
}
changeQianbi=function(){
if(isLine){
$('#canvas').mousedown(function(e){
	isMouseDown=true;
	var mouseX = e.pageX - this.offsetLeft;
	  var mouseY = e.pageY - this.offsetTop;
	  addClick(e.pageX - this.offsetLeft, e.pageY - this.offsetTop);
	  redraw();
});

$('#canvas').mousemove(function(e){
  if(isMouseDown){//是不是按下了鼠标
    addClick(e.pageX - this.offsetLeft, e.pageY - this.offsetTop, true);
    redraw();
  }
});
$('#canvas').mouseup(function(e){
	isMouseDown = false;
});
$('#canvas').mouseleave(function(e){
	isMouseDown = false;
});
}
}
$(document).keydown(function(e){
	
    var key =  e.which;
    if(key == 27){
         alert('按下了ESC键');
    }
});
</script>
</html>