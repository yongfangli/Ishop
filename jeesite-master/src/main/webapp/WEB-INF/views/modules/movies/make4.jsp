<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/webtaglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>动画制作</title>
<%@include file="/WEB-INF/views/include/webHead.jsp"%>
<%@include file="/WEB-INF/views/include/moivemake/movie.jsp"%>
<link	href="${ctxStatic}/modules/front/css/mobile/movies/make.css"	type="text/css" rel="stylesheet" />
<meta charset="utf-8">
  
</head>
<style>
#redactor-drawer-box{
  top:300px !important;
  left:50px !important;
}
.native-opt span{
    display: inline-flex;
    background-color: #ffeb00;
    margin-left: 54px;
    padding: 5px;
    border-radius: 5px;
    color: #040404;
    cursor:pointer;
}
.img-cont{
    margin-top: 10px;
    margin-left: 54px;
    max-height: 133px;
    width: 1200px;
    overflow-x: scroll;
    display: flex;
}
.img-cont img{
    margin-left:50px;
}
.selected{
    border: 1px solid #cc5c72;
    box-shadow: 0px -2px 4px 3px;
    border-radius: 2px;
}
.img-cont::-webkit-scrollbar {display:none}
.tog{
    position: absolute;
    width: 20px;
    height: 40px;
    display: flex;
    justify-content: center;
    align-items: center;
    font-size: 2.1rem;
    background: #5a5150;
}
.next{
    border-top-right-radius: 20px;
    border-bottom-right-radius: 20px;
    right: 71px;
}
.pre{
    border-top-left-radius: 20px;
    border-bottom-left-radius: 20px;
}
.hid{
    display:none;
}
.uploadImg{

    padding-top: 5px;
    padding-left: 10px;
    padding-bottom: 5px;
    z-index: 2;
    position: absolute;
    top: 100px;
    left: 40%;
    border: 1px solid black;
    max-width: 500px;
    max-height: 500px;
    margin: 0 auto;
    border: 1px solid #cc5c57;
    background: #ffeb00;
}
.textarea{
    border: 1px solid #cac3c3;
}
.normal{
    outline: none;
    -webkit-appearance: button;
    border: 1px solid #cac3c3;
    border-radius: 2px;
}
.cln{
    width: 200px;
    line-height: 25px;
}
label{
    color: #040404;
    width: 50px;
    text-align: right;
}
.submit{
    border: none;
    margin-left: 30px;
    outline: none;
    -webkit-appearance: button;
    padding: 5px;
    background: #0a0a0a;
    color: white;
}
.opt{
     text-align: center;
     margin-top: 10px;
}
</style>
<body>
<div id="app">
 <div id="alert" class="hid">信息提示</div>
	<h3 class=text-center>画板</h3>
	<div class="native-opt"><span onclick="saveAsPic()">保存图片</span><span id="upload" class="hid" onclick="uploadImg()">上传图片</span></div>
	<div class="img-cont" id="imgs">
	<span class="tog pre hid" @click="pre"></span><span class="tog next hid" @click="next"></span>
	</div>
	<div id="canvas-editor" style="position: absolute; top: 200px;"></div>
	
	
	<div class='uploadImg hid'>
	<div class='cln'><label>主题:</label><input class="normal" name="topic"/></div>
	<div class='cln'><label>关键字:</label><input class="normal" name="keyword"/></div>
	<div class='cln'><label>描述:</label><textarea row="5" column='10' class="textarea" name="desc"></textarea></div>
	<div class='opt'><input id="submit" class='submit' type='button' value='上传'><input class='submit' id='cancel' type='button' value='取消'></div>
	</div>
	
	<div class='movie'></div>
</div>
<script>
//base64转file
/* function convertBase64UrlToBlob(urlData){  

    var bytes=window.atob(urlData.split(',')[1]);        //去掉url的头，并转换为byte  

    //处理异常,将ascii码小于0的转换为大于0  
    var ab = new ArrayBuffer(bytes.length);  
    var ia = new Uint8Array(ab);  
    for (var i = 0; i < bytes.length; i++) {  
        ia[i] = bytes.charCodeAt(i);  
    }  

    return new Blob( [ab] , {type : 'image/png'});  
} */
function dataURLtoFile(dataurl, filename) {//将base64转换为文件
    var arr = dataurl.split(','), mime = arr[0].match(/:(.*?);/)[1],
    bstr = atob(arr[1]), n = bstr.length, u8arr = new Uint8Array(n);
    while(n--){
        u8arr[n] = bstr.charCodeAt(n);
    }
    return new File([u8arr], filename, {type:mime});
}
     function uploadImg(){
	   $(".uploadImg").removeClass('hid');
    }
    $(function(){
    	$("#submit").click(function(){
    		var topic=$("input[name='topic']").val();
    		var keyword=$("input[name='keyword']").val();
            var desc=$("textarea[name='desc']").val();
            var imgs=$('#imgs img.selected');
            //var imgsdata=[];
            var formData = new FormData();
            for(var i=0;i<imgs.length;i++){
            	var file=dataURLtoFile(imgs[i].src,'');
            	 formData.append('files',dataURLtoFile(imgs[i].src,''));
            }
            formData.append('keyword',keyword);
            formData.append('desc',desc);
            formData.append('topic',topic);
            //show progress
            $.ajax({
            	method: "post",
            	url:'${ctx}'+'/movies/pictureUpload',
                data: formData,
                dataType: "json",
                //contentType: 'multipart/form-data;charset=utf-8',
                contentType: false,
                processData:false,
                success:function(data){
                	if(data.status=='success'){
                		 Msg.show("上传成功!");
                	}else{
                		 Msg.show(data.msg);
                	}
                },
                error:function(e){
                	Msg.show("服务器异常!");
                } 
              
            })
    	});
        $("#cancel").click(function(){
        	$(".uploadImg").addClass('hid');
        	$("input[name='topic']").val("");
        	$("input[name='keyword']").val("");
        	 $("textarea[name='desc']").val("");
    	});
    })
    
     
     
    function saveAsPic(){
    	var imageData =window.drawer.api.getCanvasAsImage();
    	var img=$("<img />");
    	img.attr("src",imageData);
    	img.attr("width",200);
    	img.attr("height",100);
    	$(".img-cont").append(img);
    	//动画
    	var imgs=$("<img />");
    	imgs.attr("src",imageData);
    	$(".movie").append(imgs);
    	
    	var imgs=$(".img-cont img");
    	var wids=imgs.length*200+200;
    	if(wids>1200){
    		$(".next").removeClass("hid");
    	}
    	//img add click event handler
    	var selectedNum=0;
    	$('#imgs img').unbind('click').bind('click',function(){
  	    	if($(this).hasClass('selected')){
  	    		$(this).removeClass('selected');
  	    		selectedNum--;
  	    	}else{
  	    		$(this).addClass('selected');
  	    		selectedNum++;
  	    	}
  	    	if(selectedNum>0){
  	    		$("#upload").removeClass('hid');
  	    	}else{
  	    		$("#upload").addClass('hid');
  	    	}
  	    });
    }
    var vue=new Vue({
    	el:'#app',
    	data:{
    		imggroup:''
    	},
    	methods:{
    		next:function(){
    			var run;
    			var i=50;
    			function moverig(){
    			while(i>0){
    				document.getElementById("imgs").scrollLeft++;i--
    				}
    			}
    			if(run){
    				clearTimeout(run);
    			}
    			 run = setTimeout(moverig,100);
    			 if(document.getElementById("imgs").scrollLeft>0){
    				 $(".pre").removeClass("hid");
    			 }
    		},
    		pre:function(){
    			var run;
    			var i=50;
    			function moverig(){
    			while(i>0){
    				document.getElementById("imgs").scrollLeft--;i--
    				}
    			}
    			if(run){
    				clearTimeout(run);
    			}
    			 run = setTimeout(moverig,100);
    		}
    	}
    })
</script>
</body>
</html>