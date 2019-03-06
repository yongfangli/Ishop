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
       <div class="audio">
       <audio src="" controls="controls">
您的浏览器不支持 audio 标签。
</audio>
       <p id="volume"></p>
<div class="box"></div>
   <br>
        高通滤波:<input type="range" min='20' max='15000' step='40' value='20' id="high">
   <p id='level'>0</p>
   <p>只有高于此频率的才能通过，滤掉低频率内容</p>

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
  
  
  //音频
  var audio = document.querySelector('audio'); 
   var audioctx = new (window.AudioContext || window.webkitAudioContext);
   var filter_high = audioctx.createBiquadFilter();
   var analyser = audioctx.createAnalyser();
   var dataArray = new Uint8Array(analyser.frequencyBinCount);
   var media1 = navigator.mediaDevices.getUserMedia({audio:true});
   media1.then(getdata).catch((error)=>{alert(error)});
   function getdata(mediaStream){
    var mic = audioctx.createMediaStreamSource(mediaStream);
    // audio.srcObject = window.URL.createObjectURL(mediaStream);
 audio.srcObject = mediaStream;
    mic.connect(analyser);
    filter_high.type = 'highpass';
    filter_high.frequency.value = 20;
    analyser.connect(filter_high);
    //filter_high.connect(audioctx.destination);
    //analyser.connect(audioctx.destination);
   }
   elVolume = document.getElementById('volume');
   var box = document.querySelector('.box');
  function draw(){
    var x = Math.floor(getByteFrequencyDataAverage());
    elVolume.innerHTML = x;
    box.style.width = 2+x*3+'px';
  };
  setInterval(draw,100);

   function getByteFrequencyDataAverage(){
    analyser.getByteFrequencyData(dataArray);
    return dataArray.reduce(function(previous, current) {
        return previous + current;
    }) / analyser.frequencyBinCount;
    };
    //以下是简单的高频滤波部分
    var high = document.querySelector('#high');
    high.onchange = function(){
      filter_high.frequency.value = high.value;
      document.querySelector('#level').innerText = high.value;
    }

  
  </script>
</html>