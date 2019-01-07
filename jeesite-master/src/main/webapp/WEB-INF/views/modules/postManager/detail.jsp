<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/webtaglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>帖子详情</title>
<%@include file="/WEB-INF/views/include/webHead.jsp"%>
<link	href="${ctxStatic}/modules/front/css/mobile/postnameger/postCenter.css"	type="text/css" rel="stylesheet" />
<meta charset="utf-8">
<style>
.type{
    font-style: italic;
    position: absolute;
    right: 100px;
        color: #64d5ca;
}
.item{
    line-height: 40px;
    border-bottom: 1px dashed #ffd0a6;
}
.person{
    font-style: italic;
    position: absolute;
    right: 200px;
    color: #64d5ca;
    background: #ffe1df;
    border-radius: 10px;
    margin-top: 5px;
}
.title:hover{
  cursor:pointer;
  color:red;
}
.detail{
  padding: 0 20px;
}
.detail img{
  max-width:1000px;
}
.title{
  font-size: 20px;
  text-align: center;
}
.meta{
   display:flex;
   justify-content:space-around;
   border-bottom: 2px dashed #ece6e6;
   
}
.innerContent{
    margin-top: 20px;
    background: #fbfbfb;
    overflow: hidden;
    border-radius: 5px;
}
.target img{
	width: 200px;
    height: 150px;
}
.target{
    margin-top: 20px;
}
#comments{
     width: 1160px;
    outline: none;
    border: 0;
    color: black;
    resize: none;
    border-radius: 10px;
}
#comments::-webkit-scrollbar
{
	width: 16px;
	height: 16px;
	background-color:rgb(255,174,91);
}
.submitbtn{
    float: right;
    outline: none;
    border: none;
    padding: 5px;
    background: #c52c22;
    color: white;
}
.citem{
    height: 150px;
}
.citem img{
    width: 80px;
    height: 70px;
}
.ileft{
    width: 100px;
    float: left;
}
.irig{
   width:90%;
   float: right;
}
.commentslist{
    margin-top: 50px;
    padding-top: 20px;
    border-top: 1px solid;
}
#alert{
   top:50% !important;
}
.postoper{
   text-align:right;
}
.follow{
    width: 37px;
    margin-right: 10px;
}
.collect{
    width: 37px;
    margin-right: 10px;
}

</style>
</head>
<body>
<div class="cover hid"></div>
    <div id="app">
	<div class="content">
      <%@include file="/WEB-INF/views/include/header.jsp" %>
       <div id="alert" class="hid"></div>
	  <div class="detail">
	  <h2 class="title"> ${post.title}</h2>
	  <div class="meta"><em>${post.user.nickname}</em><i>${post.createDateStr}</i><span>${post.postType.name}</div>
	  <div class="innerContent">${post.content}</div>
	  <div class="target">
	  <c:forEach items="${post.files}" var="file">
	   <c:choose>
	   <c:when test="${fn:contains(file.contenttype,'image')==true}">
	   <img src="${ctx}/post/file/${file.fileid}"/>
	   </c:when>
	     <c:otherwise>
	     <video class="video" controls="controls">
		  <source src="${ctx}/post/file/${file.fileid}">
		</video>
	     </c:otherwise>
	     </c:choose>
	     </c:forEach>
	  </div>
	  
	  <div class="postoper">
	  <img class="collect" src="${ctxStatic}/images/collection.png" @click="collect('${post.user.id}')" />
	  <img class="follow"  src="${ctxStatic}/images/follow.png" @click="follow('${post.user.id}')" />
	  <img src="${ctxStatic}/images/praise.png" @click="praise"/><span class="praiseNum">${post.praiseNum==null?0:post.praiseNum}</span><img style="margin-left:10px;" src="${ctxStatic}/images/step.png" @click="step"/><span class="stepNum">${post.stepNum==null?0:post.stepNum}</span></div>
	  
	  <div class="comment">
	  <h3>发表评论</h3>
	  <textarea rows="4" v-model="comment" id="comments">
	  </textarea>
	    <input type="button" @click="comments()" value="提交" class="submitbtn"/>
	  </div>
	  
	  <h3 style="margin-top: 40px;">用户评论({{count}})</h3>
	  <div class="commentslist">
	  <template v-for="c in commentsList">
	   <div class="citem">
	  <div class="ileft"> <img :src="'${ctx}/post/file/'+c.user.portrait"/>
	  <span class="cuser">{{c.user.nickname}}</span>
	  <span class="cdate">{{c.createStr}}</span>
	  </div>
	  <div class="irig"> <span>{{c.comments}}</span></div>
	  </div>
      </template>
	  
	  </div>
	  
	  
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
  <input type='hidden' id='value' name='value' value='${post.id}'></input>
  <script src="${ctxStatic}/modules/front/js/project/detail.js"></script>
</body>

</html>