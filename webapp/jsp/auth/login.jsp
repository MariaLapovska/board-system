<%@ include file="../custom/header.jsp" %>

<div class="content content--form">

    <h1 class="content__title"><fmt:message key="logIn" bundle="${bundle}" /></h1>

    <c:if test="${not empty param.message}">
        <div class="content__section">
            <div class="alert alert--danger">
            	<fmt:message key="${param.message}" bundle="${bundle}" />
           	</div>
        </div>
    </c:if>

    <div class="content__section">
        <form action="${context}/controller/login" method="post">
            <div class="row">
                <div class="row__item row__item--12">
                    <input class="input" type="text" name="login" placeholder="<fmt:message key="login" bundle="${bundle}" />" autocomplete="off" required />
                </div>
            </div>
            <div class="row">
                <div class="row__item row__item--12">
                    <input class="input" type="password" name="password" placeholder="<fmt:message key="password" bundle="${bundle}" />" autocomplete="off" required />
                </div>
            </div>
            <div class="row">
                <div class="row__item row__item--12">
                    <input class="button button--primary" type="submit" value="<fmt:message key="logIn" bundle="${bundle}" />" />
                </div>
            </div>
        </form>
    </div>

</div>

<%@ include file="../custom/footer.jsp" %>