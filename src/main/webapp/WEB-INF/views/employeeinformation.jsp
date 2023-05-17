<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
</head>
<body>

<h1>Informações do funcionário</h1>
  <form action="<%= request.getContextPath() %>/information" method="get">
   <table style="with: 80%">
   <thead>
   		<tr>
   			<th>Nome</th>
   			<th>Sobrenome</th>
   			<th>Username</th>
   			<th>Senha</th>
   			<th>Endereço</th>
   			<th>Contato</th>
   		</tr>
   </thead>
   <tbody>
   		<tr>
   			<td><%=request.getAttribute("firstname")%></td>
   			<td><%=request.getAttribute("lastname")%></td>
   			<td><%=request.getAttribute("username")%></td>
   			<td><%=request.getAttribute("password")%></td>
   			<td><%=request.getAttribute("address")%></td>
   			<td><%=request.getAttribute("contact")%></td>
   		</tr>
   </tbody>
   </table>
  </form>
</body>
</html>