<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>企业信息列表</title>
</head>
<body>
	<table border="1">
		<tbody>
		<tr><th>eeeeeeee</th></tr>
			<tr><td>
				<c:forEach items="${list_corp }" var="c">
				企业地址:<c:out value="${c.c_addr }" />
					<br>
				公司电话:<c:out value="${c.c_tel }" />
					<br>
				企业邮箱:<c:out value="${c.c_email }" />
					<br>
				成立日期:<c:out value="${c.c_create_date }" />
					<br>
					<a
						href="GetCorpInfoServlet.com?company_name=<c:out value="${c.c_name }" />">
						企业名称:<c:out value="${c.c_name }" /><br> 企业图标:<img
						src="<c:out value="${c.c_logo }" />" /><br>
					</a>
				</c:forEach>
				</td>
			</tr>
			<tr>
			<tr> 
				<td></td>
			</tr>
		</tbody>
	</table>
</body>
</html>