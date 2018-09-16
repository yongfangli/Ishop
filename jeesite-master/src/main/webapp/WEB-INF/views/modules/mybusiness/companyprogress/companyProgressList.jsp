<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>公司进展管理</title>
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
		<li class="active"><a href="${ctx}/companyprogress/companyProgress/">公司进展列表</a></li>
		<shiro:hasPermission name="companyprogress:companyProgress:edit"><li><a href="${ctx}/companyprogress/companyProgress/form">公司进展添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="companyProgress" action="${ctx}/companyprogress/companyProgress/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>事件标题：</label>
				<form:input path="title" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li><label>发生时间：</label>
				<input name="happenDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${companyProgress.happenDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>事件标题</th>
				<th>事件内容</th>
				<th>公司名称</th>
				<th>发生时间</th>
				<shiro:hasPermission name="companyprogress:companyProgress:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="companyProgress">
			<tr>
				<td><a href="${ctx}/companyprogress/companyProgress/form?id=${companyProgress.id}">
					${companyProgress.title}
				</a></td>
				<td>
					${companyProgress.content}
				</td>
				<td>
					${companyProgress.company.name}
				</td>
				<td>
					<fmt:formatDate value="${companyProgress.happenDate}" pattern="yyyy-MM-dd"/>
				</td>
				<shiro:hasPermission name="companyprogress:companyProgress:edit"><td>
    				<a href="${ctx}/companyprogress/companyProgress/form?id=${companyProgress.id}">修改</a>
					<a href="${ctx}/companyprogress/companyProgress/delete?id=${companyProgress.id}" onclick="return confirmx('确认要删除该公司进展吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>