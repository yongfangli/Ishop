<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/webtaglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>首页</title>
<%@include file="/WEB-INF/views/include/webHead.jsp"%>
<meta charset="utf-8">

</head>
<script>
	
</script>
<body>
<div class="cover hid"></div>
    <div id="app">
	<div class="content">
      <%@include file="/WEB-INF/views/include/header.jsp" %>
	
	
	</div>

  <!-- <div class="innerHtml hid">
   <span id="close" @click="closeframe">×</span>
   <iframe class="framk"  id="frame"   src=""> 
   </iframe> 
  </div> -->
  </div>
<script>
$(function(){
	var wwid=window.screen.width;
	$("#close").css("left",(wwid-330)/2+300+"px");
	window.onresize=function(){
		var wwid=window.screen.width;
		$("#close").css("left",(wwid-334)/2+300+"px");
	}
})
var index= new Vue({
	el : '#innerHtml',
	methods : {
		closeframe:function(){
			$(".cover").addClass("hid");
			$(".innerHtml").addClass("hid");
		}
	  }
   })


</script>
</body>
</html>