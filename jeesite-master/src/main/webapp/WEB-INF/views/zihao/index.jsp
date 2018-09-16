<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>首页</title>
	<%@include file="/WEB-INF/views/include/zihaohead.jsp" %>
	<link rel="stylesheet"  type="text/css"  href="${staticzihao}/css/index.css"/>
	<link rel="stylesheet"  type="text/css"  href="${staticzihao}/imagenav/css/style.css"/>
	<script type="text/javascript" src="${staticzihao}/imagenav/js/carousel.js"></script>
	<script>
	$(function(){
		$(".triangle").click(function(){
			if($(".lang-select").css("display")!="none"){
				$(".lang-select").hide();
			}else{
				$(".lang-select").show();
				}
		})
		$(".lang-select li").click(function(){
			$(".lang label").text($(this).text());
			$(".lang-select").hide();
		})
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
		
	})
	</script>
</head>
<body>
<div class="header">
<div class="clearfix">
    <div class="logo"><img src="${staticzihao}/logo.png" alt="logo"/><span>某某有限公司</span></div>
    <div class="right">
    <div class="searchform">
    <form action="${ctxzihao}/search">
    <input class="search" placeholder="请输入搜索内容" name="content">
    <input class="submit" type="button" id="submit">
    </form>
    </div>
    <div class="langwrap"><span class="lang"><label>简体中文</label>
    <ul class="lang-select">
    <li>EN</li>
    <li>简体中文</li>
    </ul></span>
    <span class="triangle"></span>
    </div>
    </div>
    </div>
</div> 
<div class="nav">
<ul class="toggle">
 <li><a href="${ctxzihao}/index">首页</a></li>
 <li><a href="${ctxzihao}/companyIntro">业务范畴</a></li>
 <li><a href="${ctxzihao}/contactus">联系我们</a></li>
</ul>
</div> 
<div class="content">
    <div class="carousel-content">
		<ul class="carousel">
		<c:forEach items="${activityList}" var="activity">
			<li><a href="${ctxzihao}/activity/detail/${activity.id}"><img src="${activity.img}" alt="${activity.name}"/></a></li>
        </c:forEach>
		</ul>
		<ul class="img-index"></ul>
		<div class="carousel-prev"><img src="${staticzihao}/imagenav/images/left_btn1.png"></div>
		<div class="carousel-next"><img src="${staticzihao}/imagenav/images/right_btn1.png"></div>
	</div>
</div> 
<div class="bottom">
	<div class="contact">
	     <div class="person">
			 <p>联系人：李小军</p>
			 <p>联系电话：	15221156989</p>
			 <p>
				 Copyright 2018 有限公司</p>
		 </div>

	</div>

</div>
</body>
</html>