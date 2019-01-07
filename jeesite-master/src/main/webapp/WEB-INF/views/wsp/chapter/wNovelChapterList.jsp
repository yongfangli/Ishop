<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>章节管理</title>
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
		<li class="active"><a href="${ctx}/chapter/wNovelChapter/">章节列表</a></li>
		<shiro:hasPermission name="chapter:wNovelChapter:edit"><li><a href="${ctx}/chapter/wNovelChapter/form">章节添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="wNovelChapter" action="${ctx}/chapter/wNovelChapter/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>title：</label>
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
				<th>title</th>
				<shiro:hasPermission name="chapter:wNovelChapter:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="wNovelChapter">
			<tr>
				<td><a href="${ctx}/chapter/wNovelChapter/form?id=${wNovelChapter.id}">
					${wNovelChapter.title}
				</a></td>
				<shiro:hasPermission name="chapter:wNovelChapter:edit"><td>
    				<a href="${ctx}/chapter/wNovelChapter/form?id=${wNovelChapter.id}">修改</a>
					<a href="${ctx}/chapter/wNovelChapter/delete?id=${wNovelChapter.id}" onclick="return confirmx('确认要删除该章节吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>