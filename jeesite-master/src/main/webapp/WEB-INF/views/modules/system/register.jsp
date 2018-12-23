<%@ page contentType="text/html;charset=UTF-8" %>
  <%@ include file="/WEB-INF/views/include/webtaglib.jsp"%> 
<!DOCTYPE html>
<html>
<head>
 <title>注册</title>
<%@include file="/WEB-INF/views/include/webHead.jsp" %>
<link href="${ctxStatic}/modules/front/css/system/register.css" type="text/css" rel="stylesheet" />
<meta charset="utf-8">

</head>
<script>
</script>
<body>
<div class="content" id="app">
 <%@include file="/WEB-INF/views/include/header.jsp" %>
    <div id="alert" class="hid"></div>
    <form id="register" autocomplete="off">
    <div class="cln"><div class="lab">手机号：</div><div class="rig"><input v-model="phone" type="text" class="ipt" @change="checkPhone(event)"/><span class="notice">&nbsp*</span></div></div>
    <div class="cln"><div class="lab">邮箱：</div><div class="rig"><input  v-model="email" type="text"  class="ipt"  @change="checkEmail(event)"/><span class="notice">&nbsp*</span></div></div>
    <div class="cln"><div class="lab">昵称：</div><div class="rig"><input autocomplete="off" v-model="nickname" type="text"  class="ipt" @change="checkNickname(event)"/><span class="notice">&nbsp*</span></div></div>
    <div class="cln"><div class="lab">密码：</div><div class="rig"><input  autocomplete="new-password" v-model="password" type="password"  class="ipt" @change="checkPassword(event)"/><span style="text-align: center;" class="notice">&nbsp*(至少6位字母或者数字)</span></div></div>
    <div class="cln"><div class="lab">重复密码：</div><div class="rig"><input  v-model="repassword" type="password" class="ipt" @change="checkRepassword(event)"/><span class="notice"></span></div></div>
    <div class="cln"><div class="lab">检验码：</div><div class="rig"><input  v-model="code" type="text" class="ipt code" @change="checkCode(event)"/><span v-show="showTime" class="time">{{time}}</span><img v-show="showFlush" class="flush" @click="flush" src="${ctxStatic}/images/flush.png"/><span class="notice">&nbsp*</span></div></div>
    <div class="cln txtcet"><input class="submt" type="button" @click="submit" value="提交"/></div>
    </form>
</div>
</body>
<script>
var register=new Vue({
	el:"#register",
	data:{
		showTime:false,
		showFlush:false,
		time:120,
		phone:'',
		email:'',
		nickname:'',
		password:'',
		repassword:'',
		code:'',
		isPhone:false,
		isEmail:false,
		isnickName:false,
		isPassword:false,
		isCode:false,
		 date:null,
	},
	methods:{
		submit:function(){
			var vm=this;
			if(!vm.isPhone){
				Msg.show("请输入手机号！");
				return;
			}else if(!vm.isEmail){
				Msg.show("请输入邮箱！");
				return;
			}
			else if(!vm.isPassword){
				Msg.show("请输入密码！");
				return;
			}else if(!vm.isnickName){
				Msg.show("请输入昵称！");
				return;
			}else if(!vm.isCode){
				Msg.show("请输入验证码！");
				return;
			}else{
				var loginurl='${ctx}'+'/system/register';
				vm.$http.post(loginurl,{
					'phone':vm.phone,
					'email':vm.email,
					'nickName':vm.nickname,
					'validateCode':vm.code,
					'password':vm.password,
					'repassword':vm.repassword
			},{
				emulateJSON: true
			}).then(function(res) {
					if(res.data.status=='success'){
						 
					}else{
						 Msg.show(res.data.msg);
					}
				}, function(res) {
					 Msg.show(res.data.msg);
				});
			}
		},
		checkPhone : function(e){
			var vm=this;
			var reg=/^1[34578]\d{9}$/;
		    if(!(reg.test(vm.phone))){
				Msg.show("手机号格式不对！");
				vm.phone='';
				$(e.target).focus();
			}else{
				vm.isPhone=true;
			}
		},
		checkEmail: function(e){
			var vm=this;
			var reg=/^[a-zA-Z0-9_.-]+@[a-zA-Z0-9-]+(\.[a-zA-Z0-9-]+)*\.[a-zA-Z0-9]{2,6}$/;
			if(!(reg.test(vm.email))){
				Msg.show("邮箱格式不对！");
				vm.email='';
				$(e.target).focus();
			}else{
				//发送验证码
				  
				vm.sendMsg();
				Msg.show("已将验证码发到你的邮箱!");
				vm.isEmail=true;
				vm.showTime=true;
				vm.showFlush=false;
				vm.time=120;
				clearInterval(vm.date);
				vm.date=setInterval(function(){
				  if(vm.time>0){
					   vm.time--;
				   }else{
					   vm.showFlush=true;
				   }
			   },1000);
			}
		},
		checkCode:function(e){
			var vm=this;
			if(vm.code!=''){
				vm.isCode=true;
			}else{
				Msg.show("请输入6位数的验证码！");
				vm.codee='';
				vm.isCode=false;
				$(e.target).focus();
			}
		},
		checkPassword:function(e){
			var vm=this;
			var reg=/^(?![0-9]+$)(?![a-zA-Z]+$)(?!([^(0-9a-zA-Z)]|[\\(\\)])+$)([^(0-9a-zA-Z)]|[\\(\\)]|[a-zA-Z]|[0-9]){6,20}$/;
			if(!(reg.test(vm.password))){
				Msg.show("密码格式不对！");
				vm.password='';
				$(e.target).focus();
			}

		},
		checkRepassword:function(e){
			var vm=this;
			if(vm.repassword!=vm.password){
				Msg.show("两次输入的密码不相同！");
				vm.repassword='';
				$(e.target).focus();
			}else{
				vm.isPassword=true;
			}
		},
		checkNickname:function(){
			var vm=this;
			if(vm.nickname!=''){
				vm.isnickName=true;
			}else{
				Msg.show("请输入昵称！");
				vm.nickname='';
				$(e.target).focus();
			}
		},
		sendMsg:function(){
			var vm=this;
			var codeurl="${ctx}/system/code";
			vm.$http.get(codeurl+"?email="+vm.email,null).then(function(res) {
				if(res.data.status=='success'){
					Msg.show(res.data.msg);
				}else{
					 Msg.show(res.data.msg);
				}
			}, function(res) {
				alert(res.status)
			});
		},
		flush:function(){
			var vm=this;
			if(!vm.isEmail){
				Msg.show("邮箱格式不对！");
				vm.email='';
			}else{
				vm.sendMsg();
				Msg.show("已将验证码发到你的邮箱!");
				vm.isEmail=true;
				vm.showTime=true;
				vm.showFlush=false;
				vm.time=120;
				clearInterval(vm.date);
				vm.date=setInterval(function(){
				  if(vm.time>0){
					   vm.time--;
				   }else{
					   vm.showFlush=true;
				   }
			   },1000);
			}
		}
	}
})


</script>
</html>