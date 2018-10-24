<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/webtaglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>首页</title>
<%@include file="/WEB-INF/views/include/webHead.jsp"%>
<link	href="${ctxStatic}/modules/front/css/mobile/postnameger/postCenter.css"	type="text/css" rel="stylesheet" />
<meta charset="utf-8">

</head>
<body>
<div class="cover hid"></div>
    <div id="app">
	<div class="content">
      <%@include file="/WEB-INF/views/include/header.jsp" %>
       <div id="alert" class="hid"></div>
	  <div class="vertical">
	  <ul class="t-cln">
	  <template v:if="typeList" v-for="(t,index) in typeList">
	   <li v-if="index==0" class="selected" @mouseover="selected(t.id,event)">{{t.name}}</li>
	  <li v-else @mouseover="selected(t.id,event)">{{t.name}}</li>
	  </template>
	  </ul>
	  
	    <div class="rigcontent">
	  <ul class="postList">
	  <template v-if="postList" v-for="(p, index) in postList">
	   <li v-if="index==0" class="postItem">
	   <span class="itemContent big">{{p.content}}</span>
	   </li>
	   <li v-else>
	   <span class="itemContent">{{p.content}}</span>
	   </li>
	   </template>
	  </ul>
	  </div>
	  
	  </div> 
	  
	
	           
	  <script>
	  var posturl="${ctx}"+"/post/postListJson";
   var postCenter=new Vue({
	  el:'.vertical',
	  data:{
		  typeList:[],
		  postList:[],
		  last:false,
		  pageNo:1,
	  },
	  created:function(){
			var vm=this;
			var url='${ctx}'+'/postType/listJson';
			vm.$http.get(url, null).then(function(res) {
				if(res.data.status=='success'){
					vm.typeList=res.data.data;
					   vm.$http.post(posturl+"?pageNo="+vm.pageNo+"&typeId="+vm.typeList[0].id, null).then(function(res) {
							if(res.data.status=='success'){
								var data=res.data.data;
								vm.last=res.data.last;
								if(data.length>0){
									for(var i in data){
									/* 	if(data[i].content.length>10){
											data[i].content=data[i].content.substring(0,20)+"...";
										} */
									vm.postList.push(data[i]);
									 }
									}
							}
						}, function(res) {
							Msg.show("服务器异常！");
						});
				}else{
					 Msg.show(res.data.msg);
				}
			}, function(res) {
				alert(res.status)
			});
			//获取第一个分类的列表
			
		}, 
	  methods:{
		  selected:function(id,e){
			  var vm=this;
			  vm.postList=[];
			  var event=$(e.target);
			  event.addClass("selected").siblings().removeClass("selected");
			  this.getDataList(id);
		  },
		  getDataList:function(id){
			  var vm=this;
			  if(!vm.last){
				    vm.$http.post(posturl+"?pageNo="+vm.pageNo+"&typeId="+id, null).then(function(res) {
						if(res.data.status=='success'){
							var data=res.data.data;
							vm.last=res.data.last;
							if(data.length>0){
								for(var i in data){
								/* 	if(data[i].content.length>10){
										data[i].content=data[i].content.substring(0,20)+"...";
									} */
								vm.postList.push(data[i]);
								 }
								}
						}
					}, function(res) {
						Msg.show("服务器异常！");
					});
				    }
		  }
	  }
})
</script>     
	</div>

  </div>
</body>

</html>