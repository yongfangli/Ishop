var Msg={
		
		setMsg:function(msg){
			$("#alert").empty().html(msg);
		},
		show:function(msg){
			  var t;
			this.setMsg(msg);
			this.setWid();
			$("#alert").removeClass("hid").addClass("show");
		    window.clearTimeout(t);
		  
		   t= window.setTimeout(  function(){
		    	 $("#alert").removeClass("show").addClass("hid");
		     },1500);
		 
		},
		setWid:function(){
			var wwid=window.screen.width;
			var left=(wwid-$("#alert").width())/2+'px';
			$("#alert").css('left',left);
		}
		
	}