var Msg={
		
		setMsg:function(msg){
			$("#alert").empty().html(msg);
		},
		show:function(msg){
			  var t;
			this.setMsg(msg);
			$("#alert").removeClass("hid").addClass("show");
		    window.clearTimeout(t);
		  
		   t= window.setTimeout(  function(){
		    	 $("#alert").removeClass("show").addClass("hid");
		     },1500);
		 
		}
		
	}