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
     <div class="rig">
     <span><label>昵称:</label><i>一个不能少</i></p>
     </div>
    </c:if>
     </div>
    
     </div>
     <div class="operate"><span  class="toggle" title='改变列表样式'><i class="toggle1 tog" @click='toggle(event)'></i><i class="toggle2" @click='toggle(event)'></i></span></div>
     <div class="content" @scroll="scroll(event)">
     <ul class="data-list">
     <template v-if="postList" v-for="option in postList">
     <li v-bind:class="liclass">
     <p class="text">{{option.content}}</p>
     <span class="emdata"><i class='date'>{{option.createDateStr}}</i></span>
     <ul class='imglist'>
     <li>
     <img v-if="option.fileIds!=''" :src="'${ctx}/post/file/'+option.fileIds" /> 
     </li>
      </ul> 
     </li>
     </template>
     </ul>
     </div>
</div>

</body>
<script>
var url="${ctx}"+"/post/postListJson";
var personl=new Vue({
	el:"#app",
	data:{
		portrait:'',
		postList:[],
		pageNo:1,
		last:false,
		liclass:'item',//grid
	},
	created:function(){
		this.getData();
		var cnt=$(".content");
		$(".content").scrollTop(10);//控制滚动条下移10px
		  if( $(".content").scrollTop()<=0 ){
			  this.pageNo++;
			    this.getData();
		  }
	},
	methods:{
		scroll :function(e){
		     var vm=this;
			var scrollTop = e.target.scrollTop;
			　　var scrollHeight =e.target.clientHeight;
			　　var windowHeight = $(e.target).height();
			　　if(scrollTop + windowHeight >= scrollHeight){
			    vm.pageNo++;
			    this.getData();
		}
	 },
	 getData:function(){
	     	 var vm=this;
		  if(!vm.last){
			    vm.$http.post(url+"?pageNo="+vm.pageNo, null).then(function(res) {
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
	 },
	 toggle : function(e){
		 var vm=this;
		 var i=$(e.target);
		 if(i.hasClass('tog')){
			 i.removeClass('tog').siblings().addClass('tog');
				 if(vm.liclass=='item') {
					 vm.liclass='grid';
				 }else{
					 vm.liclass='item';
				 }
			 
		 }
	 }
	}
	
});


</script>
</html>