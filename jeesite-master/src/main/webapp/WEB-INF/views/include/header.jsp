<%@ page contentType="text/html;charset=UTF-8" %>
	
<div id="header">	
<div class="header">
	 <div class="logo"><img @click="goIndex" src="${ctxStatic}/modules/front/image/logo.png" alt="logo"/></div>
	 <div class="desc">小说,动漫&漫画交流，制作网站</div>
	 <div class="i-rig">
	 <span class="span-btn"><a href="${pageContext.request.contextPath}">首页</a></span>
	 <span class="span-btn"><a href="${ctx}/post/postInput?style=basic">发帖</a></span>
	 <span @click="goRegister" class="span-btn">注册</span>
	 <c:if test="${fns:getWUser(pageContext.session.id)==null}"><span class="span-btn"><a href="#" @click="goLogin">登录</a></span></c:if>
	 <c:if test="${fns:getWUser(pageContext.session.id)!=null}">
	 <c:if test="${fns:getWUser(pageContext.session.id).portrait!=''&&fns:getWUser(pageContext.session.id).portrait!=null}">
	 <a href="${ctx}/personal"><img class="portrait" src="${ctx}/post/file/${fns:getWUser(pageContext.session.id).portrait}"/></a>
	 </c:if>
	  <c:if test="${fns:getWUser(pageContext.session.id).portrait==''||fns:getWUser(pageContext.session.id).portrait==null}">
	 <a href="${ctx}/personal"><img class="portrait" src="${ctxStatic}/images/default_protail.png"/></a>
	 </c:if>
	 </span>
	 </c:if>
	 </div>
	</div>
	<div class="toggle-bar">
	           <ul class="f-nav">
	           
	          
	           </ul>
	</div>
	<div style="line-height: 40px;"><a class='back' href="javascript:history.back(-1)"></a></div>
	</div>
	<script>
 var ctx='${ctx}';
 var header= new Vue({
	el : '#header',
	data : {
		typeList:[]
	},
	beforeCreate:function(){
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
		
		goLogin:function(){
			window.location.href='${ctx}/system/login';
		}
	  
	}
	}
   )

   

</script>
<script>
	          window.onload=function(){
	        	  //加载菜单
	        	 var url='${ctx}'+'/menu/menuJson';
	        	 $.ajax({
	        		url:url,
	        		method:'post',
	        		success:function(data){
	        			if(data.status=='success'){
	        				var tag=$(".f-nav");
	        				var html="";
	        				for(var i=0;i< data.data.length;i++){
	        					var item=data.data[i];
	        					var ctx='${ctx}';
	        					html+="<li class='f-li'>";
	        					if(item.href==''){
	        						html=html+"<a class='link' href='javascript:void(0)'>"+item.name+"</a>";
	        					}else{
	        						html=html+ "<a  class='link' href='"+ctx+item.href+"'>"+item.name+"</a>";
	        					}
	        					if(item.child){
	        						var ul="<ul   class='c-nav hid'>";
	        						for(var j=0;j< item.child.length;j++){
	        							var c=item.child[j];
	        							ul=ul+"<li value='"+c.id+"'><a class='link' href='"+ctx+c.href+"'>"+c.name+"</a></li>";
	        						}
	        						ul=ul+"</ul>";
	        						html+=ul;
	        					}
	        					html+="</li>";
	        				}
	        				tag.empty().html(html);
	        				//
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
	        			}else{
	        				 Msg.show(res.data.msg);
	        			}
	        		} 
	        	 })
	        	  
	  		 
	  		    } 
	          
	          </script>
	
		