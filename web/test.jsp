<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<section class="browse-section">
    <h2>Browse Suggestions</h2>
    <ul class="suggestions-list">
        <c:forEach var="suggestion" items="${suggestions}">
            <li>
                <b>Website:</b> <c:out value="${suggestion.websiteId.url}"/> <br>
                <b>Title:</b> <c:out value="${suggestion.title}"/> <br>
                <c:if test="${suggestion.description != null}">
                    Description: <c:out value="${suggestion.description}"/> <br>
                </c:if>
                <c:if test="${suggestion.attachment != null}">
                    Attachment: <c:out value="${suggestion.attachment}"/> <br>
                </c:if>
            </li>
        </c:forEach>
    </ul>
</section>
