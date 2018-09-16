<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>联系我们表管理</title>
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
		<li class="active"><a href="${ctx}/contactus/contactus/">联系人列表</a></li>
		<shiro:hasPermission name="contactus:contactus:edit"><li><a href="${ctx}/contactus/contactus/form">联系人添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="contactus" action="${ctx}/contactus/contactus/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>联系人：</label>
				<form:input path="name" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>联系人</th>
				<th>联系电话</th>
				<th>信息</th>
				<shiro:hasPermission name="contactus:contactus:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="contactus">
			<tr>
				<td><a href="${ctx}/contactus/contactus/form?id=${contactus.id}">
					${contactus.name}
				</a></td>
				<td>
					${contactus.phone}
				</td>
				<td>
					${contactus.detail}
				</td>
				<shiro:hasPermission name="contactus:contactus:edit"><td>
    				<a href="${ctx}/contactus/contactus/form?id=${contactus.id}">修改</a>
					<a href="${ctx}/contactus/contactus/delete?id=${contactus.id}" onclick="return confirmx('确认要删除该联系我们表吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>