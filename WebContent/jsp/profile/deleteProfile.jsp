<%@ include file="../custom/header.jsp" %>

<div class="content content--form">

    <h1 class="content__title"><fmt:message key="deleteProfile" bundle="${bundle}" /></h1>

    <div class="content__section">
        <form action="${context}/controller/profile/delete" method="post">
            <div class="row">
                <div class="row__item row__item--12">
                    <fmt:message key="deleteProfileConfirmation" bundle="${bundle}" />
	                    <c:if test="${not empty userApplication}">
	                    	<p>
	                    		<fmt:message key="deleteProfileApplication" bundle="${bundle}" />
	                    	</p>
	                    </c:if>
                </div>
            </div>
            <div class="row">
                <div class="row__item row__item--12">
                    <input class="button button--danger" type="submit" value="<fmt:message key="delete" bundle="${bundle}" />" />
                    <a class="button button--default" href="${context}/board-system/profile"><fmt:message key="cancel" bundle="${bundle}" /></a>
                </div>
            </div>
        </form>
    </div>

</div>

<%@ include file="../custom/footer.jsp" %>