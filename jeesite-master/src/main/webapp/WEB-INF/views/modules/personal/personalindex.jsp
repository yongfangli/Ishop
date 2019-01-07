<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/webtaglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>个人中心</title>
<%@include file="/WEB-INF/views/include/webHead.jsp"%>
<link	href="${ctxStatic}/modules/front/css/mobile/personal/personal.css"	type="text/css" rel="stylesheet" />
<meta charset="utf-8">
<style>

.pdesc{
    background: linear-gradient(to right,#b0cedc, #ffe1df);
    color: white;
    text-indent: 40px;
    line-height: 37px;
    margin-top: 10px;
}
.personalIndex{
    display: flex;
}
.protail{
    height: 100px;
    text-align: center;
    padding-top: 10px;
    padding-bottom: 20px;
}
.protail img{
    width: 80px;
    height: 80px;
    border-radius: 40px;
}
.left{
    width: 320px;
    border: 1px solid #6cbbb7;
}
.pdesc span{
    
}
.showmodel{
      float: right;
    margin-right: 30px;
    margin-top: 5px;
    line-height: 25px;
    outline: none;
    color: white;
    border: none;
    background: #948d8d;
    border-radius: 2px;
}
.editModel{
    float: right;
    margin-right: 30px;
    margin-top: 5px;
    line-height: 25px;
    outline: none;
    background: #948d8d;
    color: white;
    border-radius: 2px;
}
.edit{
    position: relative;
    left: 45%;
    border-bottom: 1px solid;
    font-size: 1.1rem;
}
.video{
	width: 100px;
    height: 150px;
}
.img{
}
.pmenu-ul{
  line-height:40px;
}
.pmenu-ul li:hover{
  cursor:pointer;
}
.count{
    background: #ce0404;
    display: inline-flex;
    justify-content: center;
    align-items: center;
    color: white;
    text-align: center;
    padding: 5px;
    width: 13px;
    height: 13px;
    font-size: 0.5rem;
    border-radius: 15px;
    position: relative;
    top: -13px;
}
.p-item{
    padding: 10px;
    width: 50px;
}
.rig-cont{
    display: flex;
    justify-content: space-between;
    flex-wrap: wrap;
    margin-left: 100px;
    max-width: 600px;
    align-items: flex-start;
}
.pmenu{
   width: 150px;
}
a{
  text-decoration:none;
}
.more{
    position: relative;
    bottom: 0px;
    left: 50%;
}
.opt{
    padding: 2px 10px;
}
.opt span{
    background: #ffe1df;
    border-radius: 5px;
    padding: 4px;
    color: black;
    margin-left:10px;
}
</style>
</head>
<body>
    <div id="app">
	<div class="content">
      <%@include file="/WEB-INF/views/include/header.jsp" %>
       <div id="alert" class="hid"></div>
       <div class="personalIndex">
       <div class="left">
       <div class="protail">
       
       <c:if test="${user.portrait!=''&&user.portrait!=null}">
       <img  @click="changePortail" title="点击修改头像" src="${ctx}/post/file/${user.portrait}"/>
       </c:if>
       <c:if test="${user.portrait==''||user.portrait==null}">
       <img @click="changePortail" title="点击修改头像" src="${ctxStatic}/images/default_protail.png"/>
       </c:if>
       
       <input id="portrait" type="file" style="display:none" @change="uploadPortrait($event)">
       </div>
       <div class="opt"><span @click="gonovel">小说创作</span><span @click="goedit">我的小说</span></div>
        <div class="pdesc"> <span class='edit' @click="makeEdit($event)">编辑</span><span class='exist' @click="exit()">退出登录</span></div>
        <div class="pdesc"><span>昵称:</span><input :class="className" :readonly="readonly"  name="nickname" value="${user.nickname}" /></div>
        <div class="pdesc"><span>联系电话:</span><input :class="className" :readonly="readonly" name="phone" value="${user.phone}" /></div>
        <div class="pdesc"><span>邮箱:</span><input :class="className" :readonly="readonly" name="email" value="${user.email}" /></div>
        <div class="pdesc"><span>生日:</span><input  :class="className" :readonly="readonly" type="date" @change="setxz($event)" name="birthday" value="<fmt:formatDate value="${user.birthday}" pattern="yyyy-MM-dd" />" /></div>
        <div class="pdesc"><span>星座:</span><input :class="className" :readonly="readonly" name="constellation" value="${user.constellation}" /></div>
       </div>
       
       <div class="pmenu">
       <ul class="pmenu-ul">
       <li @mouseOver="showList(1)">我的帖子<span class="count">${user.postNum}</span></li>
       <li @mouseOver="showList(2)">我的收藏<span class="count">${user.collectionNum}</span></li>
       <li @mouseOver="showList(3)">我的关注<span class="count">${user.noticeNum}</span></li>
       <li @mouseOver="showList(4)">我的粉丝<span class="count">${user.followerNum}</span></li>
       <li @mouseOver="showList(5)">浏览记录</li>
       <li @mouseOver="showList(6)">我的消息</li>
       </ul>
       </div>
       
       <div class="rig-cont">
       <template v-if="postList.length>0">
       <template v-for="p in postList">
         <div class="p-item">
          <span class="title"><a :href="'${ctx}/post/detail/'+p.id">{{p.title}}</a></span> 
         </div>
       
         </template>
         <div class="more"><a href="${ctx}/post/center?userId=${user.id}">更多...</a></div>
         </template>
         
          <template v-else>
               暂无数据
         </template>
         
       </div>
       
       </div>
	</div>
    
  </div>
   <input type="hidden" value="${user.id}" id="userId"/>
</body>
<script src="${ctxStatic}/modules/front/js/project/xingzuo.js"></script>
<script src="${ctxStatic}/modules/front/js/project/personal.js"></script>
</html>