
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%@ include file="/WEB-INF/views/header.jsp" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>



<h1>Catalogue de produits</h1>

<c:if test="${not empty flash}">
    <p style="color:green;">${flash}</p>
</c:if>

<a href="${ctx}/produits?action=new">Ajouter un produit</a>

<c:if test="${empty produits}">
    <p>Aucun produit</p>
</c:if>

<table>
    <tr>
        <th>Nom</th>
        <th>Description</th>
        <th>Prix</th>
        <th>Stock</th>
        <th>Actions</th>
    </tr>

    <c:forEach var="p" items="${produits}">

        <tr>
			<td><c:out value="${p.nom}"/></td>
			<td><c:out value="${p.description}"/></td>

            <td>
                <fmt:formatNumber value="${p.prix}"
                                  minFractionDigits="2"
                                  maxFractionDigits="2"/>
            </td>

            <td>
                <c:choose>
                    <c:when test="${p.stock == 0}">
                        <span style="color:red;">${p.stock}</span>
                    </c:when>

                    <c:otherwise>
                        ${p.stock}
                    </c:otherwise>
                </c:choose>
            </td>

            <td>
                <a href="${ctx}/produits?action=edit&id=${p.id}">
                    Éditer
                </a>

                <form method="post" action="${ctx}/produits"
                      onsubmit="return confirm('Voulez-vous vraiment supprimer ce produit ?');">

                    <input type="hidden" name="action" value="delete">

                    <input type="hidden" name="id" value="${p.id}">

                    <button type="submit">Supprimer</button>

                </form>
            </td>
        </tr>

    </c:forEach>

</table>

<a href="${ctx}/dashboard">Retour au tableau de bord</a>

</body>
</html>		

