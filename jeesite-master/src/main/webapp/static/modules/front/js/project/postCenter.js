//src:http://localhost:81/jeesite/src/main/webapp/modules/front/js/project/postCenter.js
var posturl=ctx+"/post/postListJson";
var userId=$("#userId").val();
   var postCenter=new Vue({
	  el:'#postCenter',
	  data:{
		  postList:[],
		  last:false,
		  pageNo:1,
		  timeOut:"",
		  pageNo:1,
		  showPage:[],
		  allpage:0,
		  total:0,
		  scontent:'',
		  typeId:'',
	      orderBy:[]
	  },
	  created:function(){
			var vm=this;
			//获取帖子列表
			  var vm=this;
			  vm.$http.post(posturl, 
					  JSON.stringify({'userId':userId})).then(function(res) {
								if(res.data.status=='success'){
									var data=res.data;
									vm.last=data.last;
									vm.allpage=data.total;
									vm.countShowPage(vm.pageNo,vm.allpage);
									vm.postList=[];
									if(data.data.length>0){
										for(var i in data.data){
										vm.postList.push(data.data[i]);
										 }
										}
								}else{
									 Msg.show("服务器异常!");
								}
							});
			  
		}, 
	  methods:{
		  search:function(flag){
			  var vm=this;
			  if(flag==1){
				  vm.pageNo=1; 
			  }
			  vm.$http.post(posturl, 
					  JSON.stringify({'pageNo':vm.pageNo,'scont':vm.scontent,'userId':userId,'typeId':vm.typeId,'orderBy':vm.orderBy})).then(function(res) {
								if(res.data.status=='success'){
									var data=res.data;
									vm.last=data.last;
									vm.allpage=data.total;
									vm.countShowPage(vm.pageNo,vm.allpage);
									vm.postList=[];
									if(data.data.length>0){
										for(var i in data.data){
										vm.postList.push(data.data[i]);
										 }
										}
								}else{
									 Msg.show("服务器异常!");
								}
							});
		  },
		  
		  detail:function(id){
			  window.location.href=ctx+"/post/detail/"+id;
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
			}
	  }
})
	   $(document).keydown(function(event){
		   　　　　if(event.keyCode == 13){
		   　　　　　　if(postCenter.scontent!=''){
			       postCenter.search(1);
		   　　　　　　}else{
			     Msg.show("请输入搜索数据!");
			    }
		   　　　　}
		   　　});
   
   
   $(function(){
	   $(".searchform").mouseover(function(){
		   $(".searchform").addClass("sch");
			$(".search").addClass("schi");
	   });
       $(".searchform").mouseout(function(){
    	   $(".searchform").removeClass("sch");
			$(".search").removeClass("schi");
	   })
	   
	   //搜索
	       
	   $(".ptype span").click(function(){
		 if($(this).attr('value')!='all'){
			 postCenter.typeId=$(this).attr('value');
			 postCenter.search();
			 $(this).addClass('stress');
			 $(this).siblings().removeClass('stress');
		 }else{
			 postCenter.typeId='';
			 $(this).siblings().removeClass('stress');
			 postCenter.search();
		 }
	   });
       $(".oth span").click(function(){
  		 if($(this).attr('value')!='all'){
  			 if($(this).hasClass('stress')){
  				var ostr=$(this).attr('value');
  			    var index=postCenter.orderBy.indexOf(ostr);
  			    postCenter.orderBy.splice(index,1);
  			    $(this).removeClass('stress');
  			 }else{
  				postCenter.orderBy.push($(this).attr('value'));
  				$(this).addClass('stress');
  			 }
  			 postCenter.search();
  		 }else{
  			 postCenter.orderBy=[];
  			$(this).siblings().removeClass('stress');
  			postCenter.search();
  		 }
  	   });
       
   })

