<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/webtaglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>首页</title>
<%@include file="/WEB-INF/views/include/webHead.jsp"%>
<link	href="${ctxStatic}/modules/front/css/mobile/postnameger/postCenter.css"	type="text/css" rel="stylesheet" />
<meta charset="utf-8">
<style>
.type{
    font-style: italic;
    position: absolute;
    right: 500px;
    color: #64d5ca;
}
.date{
    position: absolute;
    right: 400px;
    color: #79796c;
}
.item{
    line-height: 30px;
    border-bottom: 1px dashed #ffd0a6;
}
.person{
    font-style: italic;
    position: absolute;
    right: 600px;
    color: #64d5ca;
    background: #ffe1df;
    border-radius: 10px;
    margin-top: 5px;
}
.title:hover{
  cursor:pointer;
  color:red;
}

.submit{
    width: 30px;
    background: url('${ctxStatic}/modules/front/image/search_btn.png') top no-repeat;
    text-indent: -9999px;
    border: none;
    vertical-align: top;
    margin-top: 5px;
}
.search{
	width: 100px;
    font-size: 13px;
    line-height: 26px;
    height: 26px;
    border: none;
    background: none;
   
    
}
input{ outline: medium;}
.searchform{
    width:150px;
	float: right;
    margin-top: -45px;
    padding: 4px 10px;
    border: #cccccc 1px solid;
    height: 28px;
    line-height: 24px;
    border-radius: 3px;
    margin-right: 26px;
}

.sch{
  animation: myfirst 1s;
  animation-iteration-count:1;
  animation-fill-mode:forwards;
}
.schi{
  animation: myfirst1 1s;
  animation-iteration-count:1;
  animation-fill-mode:forwards;
}
.meta{
  height: 48px;
}


@keyframes myfirst
{
from  {width:150px;}
to  { border: 1px solid;
      width:250px ;
   }
}

@keyframes myfirst1
{
from  {width:100px;}
to  { 
    width: 206px;
   }
}
.searchcont{
    border: 1px solid #decbcb;
    padding: 5px;
    background: white;
    margin-top: 25px;
}
.stitle span{
 margin-left:10px;
}
.all{
    background: #0cd4c2;
    color: white;
    padding: 2px;
}
.metainfo{
    margin-left:10px;
}
em{
    color: #249470;
    font-style: normal;
}
.stitle{
    line-height: 40px;
}
.stress{
    background: #b5180d;
    color: white;
}
</style>
</head>
<body>
<div class="cover hid"></div>
    <div id="app">
	<div class="content">
      <%@include file="/WEB-INF/views/include/header.jsp" %>
      <div id="postCenter">
       <div class="searchform">
    <input class="search" v-model="scontent" placeholder="请输入搜索内容" name="content">
    <input class="submit" type="button" id="submit" @click="search(1)">
    </div>
       <div id="alert" class="hid"></div>
	  <div class="vertical">
	  
	  <div class="searchcont">
	  <div class="stitle ptype"><label>类型:</label><span class="all" value="all">全部</span><c:forEach items="${types}" var="t"><span value="${t.id}">${t.name}</span></c:forEach></div>
	  <div class="stitle oth"><label>其它:</label><span class="all" value="all">全部</span><span  value="collectionNum">收藏量</span><span value="commentsNum">评论量</span><span value="praiseNum">赞量</span></div>
	  </div>
	  
	  <template v-for="o in postList">
	  <div class="item" >
	  <p class="title"  @click="detail(o.id)">{{o.title}}</p>
	  <div class="meta"><span class="metainfo">赞<em>{{o.praiseNum}}</em></span><span class="metainfo">收藏量<em>{{o.collectionNum}}</em></span><span class="metainfo">评论量<em>{{o.commentsNum}}</em></span><span class="type">{{o.postType.name}}</span><span class="person">{{o.user.nickname}}</span><span class="date">{{o.createDateStr}}</span></div>
	  </div>
	  </template>
	  
	       <div  class="page">
      
    <span class="btn first" @click="changePage(1,$event)">第一页</span ><span class="btn pre" v-show="pageNo!=1" @click="changePage(2,$event)">上一页</span>
    <ul class="pageUl">
    <template v-if="showPage" v-for="o in showPage">
    
    <li v-if="o==pageNo" class="pageItem cur"><a class="pageNum" @click="changePage(3,$event)">{{o}}</a></li>
       <li v-else class="pageItem" ><a class="pageNum" @click="changePage(3,$event)">{{o}}</a></li>
    </template>
    </ul><span class="btn next" v-show="pageNo!=allpage" @click="changePage(4,$event)">下一页</span ><span class="btn end" @click="changePage(5,$event)">尾页</span>
    <label class="all">共：</label>{{allpage}}页
    </div>
	  
	  </div> 
	   
	
	    
	</div>
</div>
  </div>
  <input type="hidden" value="${userId}" id="userId"/> 
</body>
 <script src="${ctxStatic}/modules/front/js/project/postCenter.js"></script>
 
</html>