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
    <div id="alert" class="hid"></div>
    <form id="register">
    <div class="cln"><div class="lab">手机号：</div><div class="rig"><input v-model="phone" type="text" class="ipt" @change="checkPhone(event)"/><span class="notice">&nbsp*</span></div></div>
    <div class="cln"><div class="lab">邮箱：</div><div class="rig"><input  v-model="email" type="text"  class="ipt"  @change="checkEmail(event)"/><span class="notice">&nbsp*</span></div></div>
    <div class="cln"><div class="lab">昵称：</div><div class="rig"><input  v-model="nickname" type="text" class="ipt"/><span class="notice">&nbsp*</span></div></div>
    <div class="cln"><div class="lab">密码：</div><div class="rig"><input  v-model="password" type="password" class="ipt" @change="checkPassword(event)"/><span style="display: block; text-align: center;" class="notice">&nbsp*(至少6位字母或者数字)</span></div></div>
    <div class="cln"><div class="lab">重复密码：</div><div class="rig"><input  v-model="repassword" type="password" class="ipt" @change="checkRepassword(event)"/><span class="notice"></span></div></div>
    <div class="cln"><div class="lab">检验码：</div><div class="rig"><input  v-model="code" type="text" class="ipt code" @change="checkCode(event)"/><span v-show="showTime" class="time">{{time}}</span><span class="notice">&nbsp*</span></div></div>
    <div class="cln txtcet"><input class="submt" type="button" @click="submit" value="提交"/></div>
    </form>
</div>
</body>
<script>
var register=new Vue({
	el:"#app",
	data:{
		showTime:false,
		time:120,
		phone:'',
		email:'',
		nickname:'',
		password:'',
		password:'',
		repassword:'',
		code:'',
		isPhone:false,
		isEmail:false,
		isPassword:false,
	},
	methods:{
		submit:function(){
			var vm=this;
			if(vm.phone==''){
				Msg.show("请输入手机号！");
				$(e.target).focus();
				return;
			}
			return ;
		},
		checkPhone : function(e){
			var vm=this;
			var reg=/^1[34578]\d{9}$/;
		    if(!(reg.test(vm.phone))){
				Msg.show("手机号格式不对！");
				vm.phone='';
				$(e.target).focus();
			}else{
				isPhone=true;
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
				Msg.show("已将验证码发到你的邮箱!");
				vm.isEmail=true;
				vm.showTime=true;
			    var date=setInterval(function(){
				  if(vm.time>0){
					   vm.time--;
				   }
			   },1000);
			}
		},
		checkCode:function(e){
			
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
				isPassword=true;
			}
		},
		sendMsg:function(){
			var vm=this;
			var codeurl="${ctx}/system/code";
			vm.$http.get(codeurl, {'email':"123"}).then(function(res) {
				if(res.data.status=='success'){
					
				}else{
					 Msg.show(res.data.msg);
				}
			}, function(res) {
				alert(res.status)
			});
		}
	}
})


</script>
</html>