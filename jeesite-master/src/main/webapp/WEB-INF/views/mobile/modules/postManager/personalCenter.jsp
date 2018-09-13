<%@ page contentType="text/html;charset=UTF-8" %>
  <%@ include file="/WEB-INF/views/include/webtaglib.jsp"%> 
<!DOCTYPE html>
<html>
<head>
<%@include file="/WEB-INF/views/include/webHead.jsp" %>
<link href="${ctxStatic}/modules/front/css/mobile/postnameger/personalCenter.css" type="text/css" rel="stylesheet" />
<meta charset="utf-8">
<title>个人中心</title>
</head>
<body>
<div id="app">
     <div class="header">
     <div class="left">
     <c:if test="${user.portrait eq ''||user.portrait eq null}">
    <img class="portrait" src="${ctxStatic}/images/people.png" /> 
    </c:if>
    <c:if test="${user.portrait !='' && user.portrait != null}">
    <img class="portrait" src="${ctx}/post/file/${user.portrait}" />
    </c:if>
     </div>
    <div class="rig">
    
    </div>
     </div>
     <div class="content">
     <ul class="data-list">
     <template v-if="postList" v-for="option in postList">
     <li class="item">
     <img  :src="'${ctx}/post/file/'+option.fileIds" /> 
       <p class="text">{{option.content}}</p>
     </li>
     </template>
     </ul>
     </div>
</div>

</body>
<script>
var vm=new Vue({
	el:"#app",
	data:{
		portrait:'',
		postList:[],
		pageNo:1
	},
	created:function(){
		var vm=this;
		var url="${ctx}"+"/post/postListJson";
		vm.$http.post(url, {pageNo:vm.pageNo}).then(function(res) {
			if(res.data.status=='success'){
				var data=res.data.data;
				if(data.length>0){
					for(var i in data){
						if(data[i].content.length>10){
							data[i].content=data[i].content.substring(0,20)+"...";
						}
					vm.postList.push(data[i]);
					 }
					}
				
			}
		}, function(res) {
			alert(res.status)
		});
	}
	
})

</script>
</html>