<%@ page contentType="text/html;charset=UTF-8" %>
	
<div id="header">	
<div class="header">
	 <div class="logo"><img src="${ctxStatic}/modules/front/image/logo.png" alt="logo"/><span>唯森</span></div>
	 <div class="i-rig"><span class="span-btn"><a href="${pageContext.request.contextPath}">首页</a></span><span class="span-btn"><a href="${ctx}/post/postInput?style=basic">发帖</a></span><span class="span-btn"><a href="#" @click="goLogin">登录</a></span><span @click="goRegister" class="span-btn">注册</span></div>
	</div>
	<div class="toggle-bar">
	           <ul class="f-nav">
	           <template v-if="typeList" v-for="t in typeList">
	           <li @mouseover="showchild($event)" @mouseout="fadway($event)">{{t.name}}
	        
	           <ul @mouseover="show($event)" @mouseout="fadwaythis($event)" v-if="t.child" class="c-nav hid">
	             <template v-if="t.child" v-for="c in t.child">
	             <li :value="c.id">{{c.name}}</li>
	             </template>
	           </ul></li>
	          
	          </template>
	           </ul>
	</div>
	</div>
	<script>
var header= new Vue({
	el : '#header',
	data : {
		typeList:[]
	},
	created:function(){
		var vm=this;
		var url='${ctx}'+'/menu/menuJson';
		vm.$http.get(url, null).then(function(res) {
			if(res.data.status=='success'){
				vm.typeList=res.data.data;
			}else{
				 Msg.show(res.data.msg);
			}
		}, function(res) {
			alert(res.status)
		});
	},
	methods : {
		goRegister:function(){
			window.location.href='${ctx}/system/goRegister';
		},
		showchild:function(e){
			var tar=$(e.target);
			tar.find("ul").removeClass("hid");
		},
		fadway:function(e){
			var tar=$(e.target);
			tar.find("ul").addClass("hid");
		},
		show:function(e){
			var tar=$(e.target);
			var parent=tar.parent("ul");
			if($(parent).hasClass("hid")){
				$(parent).removeClass("hid");
			}else{
				$(parent).addClass("hid");	
			}
		},
		fadwaythis:function(e){
			var tar=$(e.target);
			var parent=tar.parent("ul");
			if($(parent).hasClass("hid")){
				$(parent).removeClass("hid");
			}else{
				$(parent).addClass("hid");	
			}
		},
		goLogin:function(){
			window.location.href='${ctx}/system/login';
		}
	  
	}
	}
   )


</script>
	
		