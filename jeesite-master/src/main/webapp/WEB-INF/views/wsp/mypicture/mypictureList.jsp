<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>我的图片管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/mypicture/mypicture/">我的图片列表</a></li>
		<shiro:hasPermission name="mypicture:mypicture:edit"><li><a href="${ctx}/mypicture/mypicture/form">我的图片添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="mypicture" action="${ctx}/mypicture/mypicture/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>topic</th>
				<th>desc</th>
				<th>keyword</th>
				<th>create_date</th>
				<th>user_id</th>
				<shiro:hasPermission name="mypicture:mypicture:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="mypicture">
			<tr>
				<td><a href="${ctx}/mypicture/mypicture/form?id=${mypicture.id}">
					${mypicture.topic}
				</a></td>
				<td>
					${mypicture.desc}
				</td>
				<td>
					${mypicture.keyword}
				</td>
				<td>
					<fmt:formatDate value="${mypicture.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${mypicture.user.name}
				</td>
				<shiro:hasPermission name="mypicture:mypicture:edit"><td>
    				<a href="${ctx}/mypicture/mypicture/form?id=${mypicture.id}">修改</a>
					<a href="${ctx}/mypicture/mypicture/delete?id=${mypicture.id}" onclick="return confirmx('确认要删除该我的图片吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>