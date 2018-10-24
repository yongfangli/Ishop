<%@ page contentType="text/html;charset=UTF-8" %>
	
<div id="header">	
<div class="header">
	 <div class="logo"><img @click="goIndex" src="${ctxStatic}/modules/front/image/logo.png" alt="logo"/></div>
	 <div class="desc">动漫&漫画交流，制作网站</div>
	 <div class="i-rig"><span class="span-btn"><a href="${pageContext.request.contextPath}">首页</a></span><span class="span-btn"><a href="${ctx}/post/postInput?style=basic">发帖</a></span><span class="span-btn"><a href="#" @click="goLogin">登录</a></span><span @click="goRegister" class="span-btn">注册</span></div>
	</div>
	<div class="toggle-bar">
	           <ul class="f-nav">
	           <template v-if="typeList" v-for="t in typeList">
	           <li class="f-li">
	           <a v-if="t.href" class="link" :href="'${ctx}'+t.href">{{t.name}}</a>
	           <a v-else class="link" href="javascript:void(0)">{{t.name}}</a>
	           <ul  v-if="t.child" class="c-nav hid">
	             <template v-if="t.child" v-for="c in t.child">
	             <li :value="c.id"><a class="link" :href="'${ctx}'+c.href">{{c.name}}</a></li>
	             </template>
	           </ul></li>
	          
	          </template>
	           </ul>
	           <script>
	           window.onload=function(){
	           $(".f-li").mouseover(function(){
	        	  var tag=$(this);
	        		  tag.find("ul").removeClass("hid");
	           })
	            $(".f-li").mouseout(function(){
	        	  var tag=$(this);
	        		  tag.find("ul").addClass("hid");
	           })
	           $(".c-nav").mouseover(function(){
	        	  var tag=$(this);
	        		  tag.removeClass("hid");
	           })
	           $(".c-nav").mouseout(function(){
		        	  var tag=$(this);
		        		  tag.addClass("hid");
		           })
	           } 
	          
	           
	           </script>
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
		goIndex:function(){
			window.location.href='${pageContext.request.contextPath}';
		},
		showchild:function(e){
			var tar=$(e.target);
			if(tar[0].tagName=='li'){
				if($(tar.find("ul")).hasClass("hid")){
					tar.find("ul").removeClass("hid");
					}else{
						tar.find("ul").addClass("hid");
					}
			}else{
				if($(tar.siblings("ul")).hasClass("hid")){
					tar.siblings("ul").removeClass("hid");
					}else{
						tar.siblings("ul").addClass("hid");
					}
			}
		},
		fadway:function(e){
			var tar=$(e.target);
			if(tar[0].tagName=='li'){
				if($(tar.find("ul")).hasClass("hid")){
				tar.find("ul").removeClass("hid");
				}else{
					tar.find("ul").addClass("hid");
				}
			}else{
				if($(tar.siblings("ul")).hasClass("hid")){
					tar.siblings("ul").removeClass("hid");
					}else{
						tar.siblings("ul").addClass("hid");
					}
			}
		},
		show:function(e){
			var tar=$(e.target);
			var parent=tar.parent("ul");
			if($(parent).hasClass("hid")){
				$(parent).removeClass("hid");	
			}else{
				$(parent).addClass("hid");	
			}
			parent.removeClass("hid");
			
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
	
		