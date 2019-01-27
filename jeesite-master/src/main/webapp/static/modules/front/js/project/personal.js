//src:http://localhost:81/jeesite/src/main/webapp/modules/front/js/project/postCenter.js
var posturl=ctx+"/post/postListJson";
var userId=$("#userId").val();
   var postCenter=new Vue({
	  el:'.personalIndex',
	  data:{
		  user:{},
		  readonly:true,
		  className:'showmodel',
		  user:{},
		  postList:[]
	     },
	  created:function(){
			var vm=this;
		}, 
	  methods:{
		  changePortail:function(){
			 $("#portrait").click();
		  },
		  uploadPortrait:function(e){
			  var vm=this;
			  var tar=e.target;
			  if (tar.files.length > 0) {
					var file = tar.files[0];
					if (!reg.exec(file.name)) {
						 Msg.show("请上传图片格式的文件");
						 return;
					}
					var uploadUrl=ctx+'/personal/changePortail';
					var formData=new FormData();
					formData.append('file', file);
					vm.$http.post(uploadUrl, formData).then(function(res) {
						if(res.data.status=='success'){
	                        Msg.show("修改成功!");
	                        var imgurl=ctx+'/post/file/'+res.data.data;
	                        $('.protail img').attr('src',imgurl);
	                        $('.portrait').attr('src',imgurl);
						}else{
						    Msg.show(res.data.msg);
						}
					}, function(res) {
						alert(res.status)
					});
			  }
		  },
		  makeEdit:function(e){
			  var vm=this;
			  var tar=$(e.target);
			  if(tar.html()=='编辑'){
				  tar.html('保存');
				  vm.readonly=false;
	              vm.className="editModel";
	            vm.user.phone=Number($("input[name='phone']").val());
	            vm.user.nickname=$("input[name='nickname']").val();
	            vm.user.email=$("input[name='email']").val();
	            vm.user.birthday=$("input[name='birthday']").val();
	            vm.user.constellation=$("input[name='constellation']").val();
			  }else{
	             var phone=Number($("input[name='phone']").val());
	 			 var nickname=$("input[name='nickname']").val();
	 			 var email=$("input[name='email']").val();
	 			 var birthday=$("input[name='birthday']").val();
	 			 var constellation=$("input[name='constellation']").val();
	 			 if(nickname==''){
	 				Msg.show("昵称不能为空!");
			    	return;
	 		    }
	 			var reg=/^1[34578]\d{9}$/;
			    if(!(reg.test(phone))||phone==''){
			    	Msg.show("手机号格式不对!");
			    	return;
			    }
			    var reg=/^[a-zA-Z0-9_.-]+@[a-zA-Z0-9-]+(\.[a-zA-Z0-9-]+)*\.[a-zA-Z0-9]{2,6}$/;
				if(!(reg.test(email))||email==''){
					Msg.show("邮箱格式不对！");
					return;
				}
				var url=ctx+'/personal/updateUser';
				var data={
						
				};
				
				data.nickname=nickname;
				data.email=email;
				data.phone=phone;
				data.constellation=constellation;
				if(birthday!=null&&birthday!=''){
					data.birthday=birthday;
				}
			    
				vm.$http.post(url, JSON.stringify(data)).then(function(res) {
					if(res.data.status=='success'){
                        Msg.show("修改成功!");
                        tar.html('编辑');
      				  vm.readonly=true;
      	              vm.className="showmodel";
					}else{
						$("input[name='phone']").val(vm.user.phone);
						$("input[name='nickname']").val(vm.user.nickname);
						$("input[name='email']").val(vm.user.email);
						$("input[name='birthday']").val(vm.user.birthday);
						$("input[name='constellation']").val(vm.user.constellation);
					    Msg.show(res.data.msg);
					}
				}, function(res) {
					alert(res.status);
				});
			     
			  }
			 
		  },
		  exit:function(){
			  window.location.href=ctx+"/personal/exit";
		  },
		  setxz:function(e){
			  var target=$(e.target);
			  var birthday=target.val();
				var index1=birthday.indexOf("-");
				var index2=birthday.lastIndexOf("-");
				var month=birthday.substring(index1+1,index2);
				var day=birthday.substring(index2+1);
				 $("input[name='constellation']").val(xingzuo(month,day));
				
		  },
		  showList:function(flag){
			  var vm=this;
			  //发的帖子
			  if(flag==1){
				  vm.$http.post(ctx+'/post/postListJson', JSON.stringify({'userId':userId,'pageNo':1})).then(function(res) {
						if(res.data.status=='success'){
							var data=res.data;
							vm.postList=[];
							if(data.data.length>0){
								for(var i in data.data){
								vm.postList.push(data.data[i]);
								 }
								}
						}else{
							Msg.show(res.data.msg);
						}
					}, function(res) {
						alert(res.status);
					});
			  }
			  //collection
			  else if(flag==2){
				  
			  }
			  //notice
              else if(flag==3){
				  
			  }
			  //follow
              else if(flag==4){
	  
              }
			  //history
              else if(flag==5){
	  
              }
			  //message
              else if(flag==6){
	  
              }
		  },
		  //转到小说编辑页面
		  gonovel:function(){
			  window.location.href=ctx+'/novel/make';
		  },
		  goedit:function(){
			  window.location.href=ctx+'/novel/edit';
		  },
		  goluxiang:function(){
			  window.location.href=ctx+'/movies/make';
		  }
	   }
   })
