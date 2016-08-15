<%@ include file="../custom/header.jsp" %>

<div class="content content--form">

    <h1 class="content__title"><fmt:message key="signUp" bundle="${bundle}" /></h1>

    <c:if test="${not empty param.message}">
        <div class="content__section">
            <div class="alert alert--danger"><fmt:message key="${param.message}" bundle="${bundle}" /></div>
        </div>
    </c:if>

    <div class="content__section">
        <form action="${context}/controller/signup" method="post">
            <div class="row">
                <div class="row__item row__item--12">
                    <input class="input" type="text" name="login" placeholder="<fmt:message key="login" bundle="${bundle}" />" autocomplete="off" required />
                </div>
            </div>
            <div class="row">
                <div class="row__item row__item--6">
                    <input class="input" type="text" name="name" placeholder="<fmt:message key="name" bundle="${bundle}" />" autocomplete="off" required />
                </div>
            </div>
            <div class="row">
                <div class="row__item row__item--6">
                    <input class="input" type="text" name="surname" placeholder="<fmt:message key="surname" bundle="${bundle}" />" autocomplete="off" required />
                </div>
            </div>
            <div class="row">
                <div class="row__item row__item--6">
                    <input class="input" type="password" name="password" placeholder="<fmt:message key="password" bundle="${bundle}" />" autocomplete="off" required />
                </div>
            </div>
            <div class="row">
                <div class="row__item row__item--6">
                    <input class="input" type="password" name="repeatPassword" placeholder="<fmt:message key="repPassword" bundle="${bundle}" />" autocomplete="off" required />
                </div>
            </div>
            <div class="row">
                <div class="row__item row__item--12">
                    <input class="button button--primary" type="submit" value="<fmt:message key="signUp" bundle="${bundle}" />" />
                </div>
            </div>
        </form>
    </div>

</div>

<%@ include file="../custom/footer.jsp" %>