<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>上海子浩传播有限公司</title>
	<%@include file="/WEB-INF/views/include/zihaohead.jsp" %>
	 <link rel="stylesheet"  type="text/css"  href="${staticzihao}/css/product.css"/>
	<link rel="stylesheet"  type="text/css"  href="${staticzihao}/css/paging.css"/>
	<script src="${staticzihao}/js/jquery-1.11.1.min.js"></script>
	<script src="${staticzihao}/js/paging.min.js"></script>
		<script type="text/javascript" src="${staticzihao}/imagenav/js/carousel.js"></script>
<script type="text/javascript">
		$(function(){

            $(".carousel-content").carousel({
                carousel : ".carousel",//轮播图容器
                indexContainer : ".img-index",//下标容器
                prev : ".carousel-prev",//左按钮
                next : ".carousel-next",//右按钮
                timing : 5000,//自动播放间隔
                animateTime : 800,//动画时间
                auto : true,//是否自动播放
            });

            $(".carousel-prev").hover(function(){
                $(this).find("img").attr("src","${staticzihao}"+"/imagenav/images/left_btn2.png");
            },function(){
                $(this).find("img").attr("src","${staticzihao}"+"/imagenav/images/left_btn1.png");
            });
            $(".carousel-next").hover(function(){
                $(this).find("img").attr("src","${staticzihao}"+"/imagenav/images/right_btn2.png");
            },function(){
                $(this).find("img").attr("src","${staticzihao}"+"/imagenav/images/right_btn1.png");
            });


           $(".p-typeList li").click(function () {
               $(this).addClass("cur");
                $(this).siblings().removeClass("cur");
                var index=$(this).index();
               html="";
                if(index==0){

                    for(var i=0;i<8;i++){
                        html+="<li>\n" +
                            "\t\t\t\t<div class=\"item-device\">\n" +
                            "\t\t\t<img class=\"device-img\" src=\"${staticzihao}/image/light.jpg\" />\n" +
                            "\t\t\t<span class=\"device-name\">车展灯</span>\n" +
                            "\t\t\t\t</div>\n" +
                            "\t\t</li>"
					}

				}
               if(index==1){
                   for(var i=0;i<8;i++){
                   html+="<li>\n" +
                       "\t\t\t\t<div class=\"item-device\">\n" +
                       "\t\t\t<img class=\"device-img\" src=\"${staticzihao}/image/product4.jpg\" />\n" +
                       "\t\t\t<span class=\"device-name\">音响</span>\n" +
                       "\t\t\t\t</div>\n" +
                       "\t\t</li>"
               }}
               if(index==2){
                   for(var i=0;i<8;i++){
                       html+="<li>\n" +
                           "\t\t\t\t<div class=\"item-device\">\n" +
                           "\t\t\t<img class=\"device-img\" src=\"${staticzihao}/image/product5.jpg\" />\n" +
                           "\t\t\t<span class=\"device-name\">调音箱</span>\n" +
                           "\t\t\t\t</div>\n" +
                           "\t\t</li>"
                   }
			   }
               $(".list-u").empty().append(html);
            });

		})

	</script>
<body>
<div class="top">
	<div class="right">
		<div class="logo"><img src="${staticzihao}/logo.png" alt="logo"/></div>
		<div class="title"><h3>上海子浩传播有限公司</h3></div>
	</div>
	<div class="left">
		<ul class="nav">
		<li><a href="${ctxzihao}/index">首页</a></li>
		<li><a href="indexother.html">公司介绍</a></li>
		<li><a href="${ctxzihao}/product">产品</a></li>
		<li><a href="indexother.html">联系我们</a></li>
	</ul>
	</div>
</div>
<div class="progress">
	<div class="bar">我们选用的设备</div>
	<div class="p-title"><label>设备类型:</label><ul class="p-typeList"><li class="cur">灯</li><li>音响</li><li>调音台</li></ul></div>
	<div class="p-list">
    <ul class="list-u">
			<li>
				<div class="item-device">
			<img class="device-img" src="${staticzihao}/image/light.jpg" />
			<span class="device-name">车展灯</span>
				</div>
		</li>

		<li>
			<div class="item-device">
				<img class="device-img" src="${staticzihao}/image/light.jpg" />
				<span class="device-name">车展灯</span>
			</div>
		</li>

		<li>
			<div class="item-device">
				<img class="device-img" src="${staticzihao}/image/light.jpg" />
				<span class="device-name">车展灯</span>
			</div>
		</li>

		<li>
			<div class="item-device">
				<img class="device-img" src="${staticzihao}/image/light.jpg" />
				<span class="device-name">车展灯</span>
			</div>
		</li>

		<li>
			<div class="item-device">
				<img class="device-img" src="${staticzihao}/image/light.jpg" />
				<span class="device-name">车展灯</span>
			</div>
		</li>

		<li>
			<div class="item-device">
				<img class="device-img" src="${staticzihao}/image/light.jpg" />
				<span class="device-name">车展灯</span>
			</div>
		</li>

	</ul>
		<div class="box" id="box"></div>
		<script>
            var total=8;
                var curpage=1;
                $('#box').paging({
                    initPageNo:1, // 初始页码
                    totalPages: total, //总页数
                    totalCount: '合计' + 20 + '条数据', // 条目总数
                    slideSpeed: 600, // 缓动速度。单位毫秒
                    jump: true, //是否支持跳转
                    callback: function(page) { // 回调函数
                        console.log(page);
                    }
                })


		</script>
	</div>
	<div class="bar">舞台策划</div>
	<div class="carousel-content">
		<ul class="carousel spride">
			<li><img src="${staticzihao}/image/product.jpg"  /></li>
			<li><img src="${staticzihao}/image/product2.jpg"  /></li>
			<li><img src="${staticzihao}/image/product4.jpg"  /></li>
			<li><img src="${staticzihao}/image/product5.jpg"  /></li>
			<li><img src="${staticzihao}/image/product6.jpg"  /></li>
		</ul>
		<ul class="img-index"></ul>
		<div class="carousel-prev"><img src="${staticzihao}/imagenav/images/left_btn1.png"></div>
		<div class="carousel-next"><img src="${staticzihao}/imagenav/images/right_btn1.png"></div>
	</div>
	<div class="bar">成品</div>
</div>


<div class="bottom">
	<div class="contact">
	     <div class="person">
			 <p>联系人：李小军</p>
			 <p>联系电话：	15221156989</p>
			 <p>
				 Copyright 2018 上海子浩传播有限公司</p>
		 </div>

	</div>

</div>
</body>
</html>