
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%@ include file="/WEB-INF/views/header.jsp" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>



    <h1>
        <c:choose>
            <c:when test="${empty produit.id}">
                Nouveau produit
            </c:when>
            <c:otherwise>
                Modifier le produit
            </c:otherwise>
        </c:choose>
    </h1>

    <form method="post" action="${ctx}/produits">

        <c:if test="${not empty produit.id}">
            <input type="hidden" name="id"
                   value="<c:out value='${produit.id}'/>">
        </c:if>

        <input type="hidden" name="action" value="save">

        <div>
            <label for="nom">Nom :</label>

            <input type="text"
                   id="nom"
                   name="nom"
                   value="<c:out value='${produit.nom}'/>"
                   required>

            <c:if test="${not empty erreurs.nom}">
                <p style="color:red;">
                    <c:out value="${erreurs.nom}"/>
                </p>
            </c:if>
        </div>

        <div>
            <label for="description">Description :</label>

            <textarea id="description"
                      name="description"><c:out value="${produit.description}"/></textarea>

            <c:if test="${not empty erreurs.description}">
                <p style="color:red;">
                    <c:out value="${erreurs.description}"/>
                </p>
            </c:if>
        </div>

        <div>
            <label for="prix">Prix :</label>

            <input type="number"
                   id="prix"
                   name="prix"
                   step="0.01"
                   value="<c:out value='${produit.prix}'/>"
                   required>

            <c:if test="${not empty erreurs.prix}">
                <p style="color:red;">
                    <c:out value="${erreurs.prix}"/>
                </p>
            </c:if>
        </div>

        <div>
            <label for="stock">Stock :</label>

            <input type="number"
                   id="stock"
                   name="stock"
                   value="<c:out value='${produit.stock}'/>"
                   required>

            <c:if test="${not empty erreurs.stock}">
                <p style="color:red;">
                    <c:out value="${erreurs.stock}"/>
                </p>
            </c:if>
        </div>

        <br>

        <button type="submit">Enregistrer</button>

        <a href="${ctx}/produits">Annuler</a>

    </form>
	</body>
	</html>		

