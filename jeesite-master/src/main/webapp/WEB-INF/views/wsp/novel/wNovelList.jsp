<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>小说管理</title>
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
		<li class="active"><a href="${ctx}/novel/wNovel/">小说列表</a></li>
		<shiro:hasPermission name="novel:wNovel:edit"><li><a href="${ctx}/novel/wNovel/form">小说添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="wNovel" action="${ctx}/novel/wNovel/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>标题：</label>
				<form:input path="title" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>标题</th>
				<th>update_date</th>
				<shiro:hasPermission name="novel:wNovel:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="wNovel">
			<tr>
				<td><a href="${ctx}/novel/wNovel/form?id=${wNovel.id}">
					${wNovel.title}
				</a></td>
				<td>
					<fmt:formatDate value="${wNovel.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="novel:wNovel:edit"><td>
    				<a href="${ctx}/novel/wNovel/form?id=${wNovel.id}">修改</a>
					<a href="${ctx}/novel/wNovel/delete?id=${wNovel.id}" onclick="return confirmx('确认要删除该小说吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>