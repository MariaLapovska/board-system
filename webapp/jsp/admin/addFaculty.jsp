<%@ include file="../custom/header.jsp" %>

<div class="content content--form">

    <h1 class="content__title"><fmt:message key="addFaculty" bundle="${bundle}" /></h1>

    <c:if test="${not empty param.message}">
        <div class="content__section">
            <div class="alert alert--danger">
            	<fmt:message key="${param.message}" bundle="${bundle}" />
            </div>
        </div>
    </c:if>

    <div class="content__section">
        <form action="${context}/controller/faculty/add" method="post">
            <div class="row">
                <div class="row__item row__item--12">
                    <input class="input" type="text" name="name" placeholder="<fmt:message key="name" bundle="${bundle}" />" autocomplete="off" required />
                </div>
            </div>
            <div class="row">
                <div class="row__item row__item--12">
                    <input class="input" type="text" name="plan" placeholder="<fmt:message key="plan" bundle="${bundle}" />" autocomplete="off" required />
                </div>
            </div>
            <div class="row">
            	<c:forEach begin="1" end="3">
	                <div class="row__item row__item--4">
	                    <select class="input input--select" name="subjects" required>
	                        <option value=""><fmt:message key="chooseSubject" bundle="${bundle}" /></option>
	                        <c:forEach var="s" items="${subjectsList}">
	                            <option value="${s.getId()}">${s.getName()}</option>
	                        </c:forEach>
	                    </select>
	                </div>
                </c:forEach>
            </div>
            <div class="row">
                <div class="row__item row__item--12">
                    <input class="button button--primary" type="submit" value="<fmt:message key="addFaculty" bundle="${bundle}" />" />
                </div>
            </div>
        </form>
    </div>

</div>

<%@ include file="../custom/footer.jsp" %>