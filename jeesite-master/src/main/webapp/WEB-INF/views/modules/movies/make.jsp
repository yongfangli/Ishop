<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/webtaglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>动画制作</title>
<%@include file="/WEB-INF/views/include/webHead.jsp"%>
<meta charset="utf-8">
  
</head>
<style>
video{
      background: rgba(255,255,255,0.5);
    }
</style>
<body>
   <div id="app">
	<div class="content">
      <%@include file="/WEB-INF/views/include/header.jsp" %>
       <div id="alert" class="hid"></div>
       <div class="video">
      <video src="" controls="controls">
您的浏览器不支持 video 标签。
</video>
       </div>
       <div class='operate'>
       <span class="shoot" onclick="shoot()">截图</span>
       <span class="down" onclick="">下载</span>
       </div>
    </div>
</div>
</body>
<script>
var video = document.querySelector('video');
var option = {audio:false,video:{width:640,height:480}};
var media = navigator.mediaDevices.getUserMedia(option);
  media.then(show).catch((error)=>{console.log(error)});
  function show(mediaStream){
	  video.srcObject=mediaStream;
    //将meida流转为url
     video.onloadedmetadata = function(e) {
        video.play();
  };
  }
  
  function shoot(){
      var canvas = document.createElement('canvas');
      canvas.width = 640;
      canvas.height = 480;
      var ctx = canvas.getContext('2d');
      ctx.drawImage(video,0,0);
      canvas.toBlob((myblob)=>{
        download(myblob);
      });
  }
  function download(blob){
     var a = document.createElement('a');
        a.href = URL.createObjectURL(blob);
        a.download = Math.random().toString(36).substr(2,14);
        a.click();
  }
  
</script>
</html>