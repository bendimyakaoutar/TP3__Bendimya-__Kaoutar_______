
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/header.jsp" %>
<!DOCTYPE html>


    <h1>Bienvenue ${user}</h1>

    <p>Vous êtes connecté avec succès !</p>

    <h2>Menu</h2>

    <ul>
        <li>
            <a href="${pageContext.request.contextPath}/produits">
                Gestion des produits
            </a>
        </li>
        <li>
            <a href="${pageContext.request.contextPath}/logout">
                Déconnexion
            </a>
        </li>
    </ul>

	</body>
	</html>		

