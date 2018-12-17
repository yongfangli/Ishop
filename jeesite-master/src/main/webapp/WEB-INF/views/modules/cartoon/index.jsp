<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/webtaglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>漫画首页</title>
<%@include file="/WEB-INF/views/include/webHead.jsp"%>
<link	href="${ctxStatic}/modules/front/css/mobile/postnameger/postCenter.css"	type="text/css" rel="stylesheet" />
<meta charset="utf-8">

</head>
<style>
.piccontent{
    display: flex;
    flex-wrap: wrap;
}
.item{
    max-width: 600px;
    max-height: 400px;
}
.piccover{
   max-width: 600px;
    max-height: 200px;
}
</style>
<body>
<div class="cover hid"></div>
    <div id="app">
	<div class="content">
      <%@include file="/WEB-INF/views/include/header.jsp" %>
       <div id="alert" class="hid"></div>
       
	 <div class="piccontent">
	 <template  v-for="c in cartonList.list">
	 <div class="item">
	 <img class="piccover" :src="'${ctx}/post/file/'+c.imgs" />
	 <em>主题:</em><span>&nbsp;&nbsp;{{c.topic}}</span>
	 </div>
	 </template>
	 </div>
	 
	</div>

  </div>
</body>
<script>
var cartoon=new Vue({
	el:'.piccontent',
	data:{
		cartonList:[],
	},
	created:function(){
		var vm=this;
		var cartoonList=ctx+"/cartoon/cartoonList";
		vm.$http.post(cartoonList,{'pageNo':1
	},{emulateJSON: true}).then(function(res) {
		var data=res.data;
		if(data.status=='success'){
			vm.cartonList=data.data;
		}else{
			Msg.show(data.msg);
		}
		
		})
	},
	methods:{
		
	}
})
</script>
</html>