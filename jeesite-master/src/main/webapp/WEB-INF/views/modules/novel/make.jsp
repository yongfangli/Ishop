<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/webtaglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>小说制作</title>
<%@include file="/WEB-INF/views/include/webHead.jsp"%>
<meta charset="utf-8">
<style>
.n-content{
    margin: 0 auto;
    text-align: center;
}
.n-form{
    width: 600px;
    margin: 0 auto;
}
.n-cln{
    line-height:60px;
    width: 250px;
    margin: 0 auto;
    text-align: left;
}
.n-cln label{
    outline: none;
}
.submit{
    position: relative;
    left: 37%;
    padding: 5px 8px;
    background: #0c55a0;
    color: white;
    border: 0px;
}
.upwrap{
    display: inline-block;
}
</style>
</head>
<body>
<div class="cover hid"></div>
    <div id="app">
	<div class="content">
      <%@include file="/WEB-INF/views/include/header.jsp" %>
      <div id="alert" class="hid"></div>
	<div class="n-content">
	<div class="n-form">
	<div class="n-cln"><label>标题:</label><input v-model="title" type="text" name="title"/></div>
	<div class="n-cln">
	<label>上传封面:</label>
			<img src="${ctxStatic}/images/addfile.svg" type="image/svg+xml" @click="upload" />
			<input class="file" type="file" style="display:none"/>
	</div>
	<div class="n-cln"><input @click="sumb" class="submit" type="button" name="title" value="提交" /></div>
	</div>
	
	</div>
	
	</div>
</div>

<script>
var novel=new Vue({
	el:'.n-content',
	data:{
		title:'',
	},
	methods:{
		upload:function(){
			$('.file').click();
		},
		sumb:function(){
			var vm=this;
			var file=$('.file')[0].files[0];
			if(vm.title==''){
				Msg.show("请输入标题");
			}else{
				if(undefined!=file&&!reg.exec(file.name)){
					 Msg.show("文件格式错误!");
					 return;
				}
				if(undefined==file){
					 Msg.show("请上传封面!");
					 return;
				}
				var  form=new FormData();
				form.append('file',file);
				form.append('title',vm.title);
				vm.$http.post(ctx+'/novel/saveNovel', form).then(function(res) {
					if(res.data.status=='success'){
                        Msg.show("上传成功!");
                        window.location.href=ctx+"/novel/edit";
					}else{
					    Msg.show(res.data.msg);
					}
				}, function(res) {
					Msg.show(res.status);
				});
			}
		}
	}
})
</script>
</body>
</html>