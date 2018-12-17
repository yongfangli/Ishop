//src:http://localhost:81/jeesite/src/main/webapp/modules/front/js/project/postCenter.js
var posturl=ctx+"/post/postListJson";
   var postCenter=new Vue({
	  el:'.vertical',
	  data:{
		  typeList:[],
		  postList:[],
		  last:false,
		  pageNo:1,
		  timeOut:""
	  },
	  beforeCreate:function(){
			var vm=this;
		  vm.$http.post(posturl+"?pageNo=1", null).then(function(res) {
							if(res.data.status=='success'){
								var data=res.data.data;
								vm.last=res.data.last;
								if(data.length>0){
									for(var i in data){
									vm.postList.push(data[i]);
									 }
									}
							}else{
								 Msg.show("服务器异常!");
							}
						});
			//获取第一个分类的列表
			
		}, 
	  methods:{
		  detail:function(id){
			  window.location.href=ctx+"/post/detail/"+id;
		  }
	  }
})
