//src:http://localhost:81/jeesite/src/main/webapp/modules/front/js/project/postCenter.js
let  postId = $("#value").val();
   var postDetail=new Vue({
	  el:'.detail',
	  data:{
		  comment:'',
	      pageNo:1,
		  pageNo:1,
		  showPage:[],
		  allpage:0,
	      total:0,  
	      commentsList:[],
	      count:0
	     },
	  created:function(){
			var vm=this;
			  vm.$http.post(ctx+'/comments/page', 
					  {'postId':postId}).then(function(res) {
								if(res.data.status=='success'){
									var data=res.data;
									vm.last=data.last;
									vm.allpage=data.total;
									vm.count=data.count;
									vm.countShowPage(vm.pageNo,vm.allpage);
									vm.commentsList=[];
									if(data.data.length>0){
										for(var i in data.data){
										vm.commentsList.push(data.data[i]);
										 }
										}
								}else{
									 Msg.show("服务器异常!");
								}
							});
		}, 
	  methods:{
		  search:function(){
			  var vm=this;
			  vm.$http.post(ctx+"/comments/page", 
					  {'pageNo':vm.pageNo,'postId':postId}).then(function(res) {
								if(res.data.status=='success'){
									var data=res.data;
									vm.last=data.last;
									vm.allpage=data.total;
									vm.count=data.count;
									vm.countShowPage(vm.pageNo,vm.allpage);
									vm.commentsList=[];
									if(data.data.length>0){
										for(var i in data.data){
										vm.commentsList.push(data.data[i]);
										 }
										}
								}else{
									 Msg.show("服务器异常!");
								}
							});
		  },
		  comments:function(){
			  var vm=this;
			  if(vm.comment==''){
				  Msg.show("请输入评论内容!");
			  }
			  else if(vm.comment.length>500){
				  Msg.show("请输入评论内容长度不能超过500!");
			  }
			  vm.$http.post(ctx+"/post/comments", {'comments':vm.comment,'postId':postId}).then(function(res) {
					if(res.data.status=='success'){
                      Msg.show("评论成功!");
                      vm.comment='';
                      vm.search();
					}else{
					    Msg.show(res.data.msg);
					}
				}, function(res) {
					alert(res.status)
				});
		  },
		 
		  countShowPage : function(pageNo,allpage){
				var vm=this;
				vm.showPage=[];
				var defaultsize=10;
				var qujian=Math.ceil(defaultsize/2);
				var page1=pageNo;
				var page2=pageNo;
				  vm.showPage.push(pageNo);
				  defaultsize--;
				while(defaultsize>0){
					page1--;
					page2++;
					if(page1>0&&(pageNo-page1)<=qujian){
					  vm.showPage.push(page1);
					  
					}
					if(page2<=allpage&&(page2-pageNo)<=qujian){
						 vm.showPage.push(page2);
						
					}
					defaultsize--;
				 }
				vm.showPage.sort(function (a,b) {
				    return a-b;
				});
				},
			changePage : function(test,e){
				
				var vm=this;
				
				if(test==1){
					vm.pageNo=1;
					vm.search();
				}else if(test==2){
					vm.pageNo=vm.pageNo-1;
					vm.search();
				}else if(test==3){
					var target=e.target;
					var val=Number(target.text);
					vm.pageNo=val;
					vm.search();
				}else if(test==4){
					vm.pageNo=vm.pageNo+1;
					vm.search();
				}else if(test==5){
					vm.pageNo=vm.allpage;
					vm.search();
				}
			},
			praise:function(){
				var vm=this;
				vm.$http.post(ctx+"/post/praise", {'postId':postId}).then(function(res) {
					if(res.data.status=='success'){
                      Msg.show("赞+1");
                      var num=Number($('.praiseNum').html());
                      num++;
                      $('.praiseNum').html(num);
					}else{
					    Msg.show(res.data.msg);
					}
				}, function(res) {
					alert(res.status)
				});
			},
			step:function(){
				var vm=this;
				vm.$http.post(ctx+"/post/step", {'postId':postId}).then(function(res) {
					if(res.data.status=='success'){
                      Msg.show("踩+1");
                      var num=Number($('.stepNum').html());
                      num++;
                      $('.stepNum').html(num);
					}else{
					    Msg.show(res.data.msg);
					}
				}, function(res) {
					alert(res.status)
				});
			},
			follow:function(usrId){
				var vm=this;
				vm.$http.post(ctx+"/post/follow", {'userId':usrId}).then(function(res) {
					if(res.data.status=='success'){
                      Msg.show("成功关注!");
					}else{
					    Msg.show(res.data.msg);
					}
				}, function(res) {
					alert(res.status)
				});
			},
			collect:function(userId){
				var vm=this;
				vm.$http.post(ctx+"/post/collect", {'postId':postId,'userId':userId}).then(function(res) {
					if(res.data.status=='success'){
                      Msg.show("收藏成功!");
					}else{
					  Msg.show(res.data.msg);
					}
				}, function(res) {
					alert(res.status)
				});
			},
	   }
   })
