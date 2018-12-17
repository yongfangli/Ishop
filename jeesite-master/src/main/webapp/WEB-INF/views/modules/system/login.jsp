<%@ page contentType="text/html;charset=UTF-8" %>
  <%@ include file="/WEB-INF/views/include/webtaglib.jsp"%> 
<!DOCTYPE html>
<html>
<head>
 <title>登录</title>
<%@include file="/WEB-INF/views/include/webHead.jsp" %>
<link href="${ctxStatic}/modules/front/css/system/login.css" type="text/css" rel="stylesheet" />
<meta charset="utf-8">

</head>
<script>
</script>
<body>

<div class="content" id="app">
    <%@include file="/WEB-INF/views/include/header.jsp" %>
     <div id="alert" class="hid"></div>
    <div class="container">
      <input id="username" autocomplete="off" v-model="loginName" type="text" @change="checkusername(event)" placeholder="请输入昵称或者邮箱"/>
     <input autocomplete="new-password" v-model="password" type="password" @change="checkpassword(event)" placeholder="请输入密码">
     <input  class="submitbtn" type="button" @click="submit" value="登录">
    </div>
    <script>
var login=new Vue({
	el:".container",
	data:{
		loginName:'',
		password:'',
		isloginname:false,
		isPas:false
	},
	created: function(){
		$(".submitbtn").css("width",$("#username").width());
	},
	methods:{
		checkusername : function(e){
			var vm=this;
		    if(vm.loginName==''){
				Msg.show("请输入您的邮箱!");
				vm.loginName='';
				$(e.target).focus();
			}else{
				vm.isloginname=true;
			}
		},
        checkpassword : function(e){
        	var vm=this;
		    if(vm.password==''){
				Msg.show("请输入您的密码!");
				vm.password='';
				$(e.target).focus();
			}else{
				vm.isPas=true;
			}
		},
		submit : function(){
			var vm=this;
			if(vm.isloginname&&vm.isPas){
			var loginurl='${ctx}'+'/system/tryLogin';
			vm.$http.post(loginurl,{
				'username':vm.loginName,
				'password':vm.password
		},{
			emulateJSON: true
		}).then(function(res) {
				if(res.data.status=='success'){
					 Msg.show(res.data.msg);
				}else{
					 Msg.show(res.data.msg);
				}
			}, function(res) {
				     Msg.show(res.data.msg);
			});
		}else{
			Msg.show("请输入您的账号或者密码!");
		}
	 }
	}
})


</script>
</div>

</body>

</html>