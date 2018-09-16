<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>公司新闻管理</title>
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
		<li class="active"><a href="${ctx}/companynews/companyNews/">公司新闻列表</a></li>
		<shiro:hasPermission name="companynews:companyNews:edit"><li><a href="${ctx}/companynews/companyNews/form">公司新闻添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="companyNews" action="${ctx}/companynews/companyNews/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>新闻标题：</label>
				<form:input path="title" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>新闻标题</th>
				<th>浏览次数</th>
				<th>创建人</th>
				<th>更新时间</th>
				<shiro:hasPermission name="companynews:companyNews:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="companyNews">
			<tr>
				<td><a href="${ctx}/companynews/companyNews/form?id=${companyNews.id}">
					${companyNews.title}
				</a></td>
				<td>
					${companyNews.viewCount}
				</td>
				<td>
					${companyNews.createBy.id}
				</td>
				<td>
					<fmt:formatDate value="${companyNews.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="companynews:companyNews:edit"><td>
    				<a href="${ctx}/companynews/companyNews/form?id=${companyNews.id}">修改</a>
					<a href="${ctx}/companynews/companyNews/delete?id=${companyNews.id}" onclick="return confirmx('确认要删除该公司新闻吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>