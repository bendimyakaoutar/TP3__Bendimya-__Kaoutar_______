<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/header.jsp" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>


		<h1> Page de connexion </h1>
		<c:if test="${not empty erreur}">
			<p style="color:red">
			<c:out value="${erreur}"/>
			</p>
		</c:if>
		<form action="${pageContext.request.contextPath}/login" method="post">
			<label> NOm d'utilisateur </label>
			<input type="text" name="username" value="<c:out value='${username}'/>" required>
			<br><br>
			<label> Mot de passe </label>
			<input type="password" name="password" required>
			<br><br>
			
			<button type="submit" > Se connecter </button>
			
			</form>
	</body>
	</html>		

		
		 
		
			
			