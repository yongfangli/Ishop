var Msg={
		
		setMsg:function(msg){
			$("#alert").empty().html(msg);
		},
		show:function(msg){
			this.setMsg(msg);
			$("#alert").removeClass("hid").addClass("show");
		},
		hid:function(){
			$("#alert").removeClass("show").addClass("hid");
		}
		
	}