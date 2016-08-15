<%@ include file="../custom/header.jsp" %>

<div class="content content--form">

    <h1 class="content__title"><fmt:message key="editProfile" bundle="${bundle}" /></h1>

    <c:if test="${not empty param.message}">
        <div class="content__section">
            <div class="alert alert--danger"><fmt:message key="${param.message}" bundle="${bundle}" /></div>
        </div>
    </c:if>

    <div class="content__section">
        <form action="${context}/controller/profile/edit" method="post">
            <div class="row">
                <div class="row__item row__item--12">
                    <input class="input" type="text" name="login" value="${user.getLogin()}" placeholder="<fmt:message key="login" bundle="${bundle}" />" readonly />
                </div>
            </div>
            <div class="row">
                <div class="row__item row__item--6">
                    <input class="input" type="text" name="name" value="${user.getName()}" placeholder="<fmt:message key="name" bundle="${bundle}" />" autocomplete="off" required />
                </div>
                <div class="row__item row__item--6">
                    <input class="input" type="text" name="surname" value="${user.getSurname()}" placeholder="<fmt:message key="surname" bundle="${bundle}" />" autocomplete="off" required />
                </div>
            </div>
            <div class="row">
                <div class="row__item row__item--12">
                    <input class="input" type="password" name="oldPassword" placeholder="<fmt:message key="oldPassword" bundle="${bundle}" />" autocomplete="off" required />
                </div>
            </div>
            <div class="row">
                <div class="row__item row__item--6">
                    <input class="input" type="password" name="newPassword" placeholder="<fmt:message key="newPassword" bundle="${bundle}" />" autocomplete="off" required />
                </div>
                <div class="row__item row__item--6">
                    <input class="input" type="password" name="repeatNewPassword" placeholder="<fmt:message key="repNewPassword" bundle="${bundle}" />" autocomplete="off" required />
                </div>
            </div>
            <div class="row">
                <div class="row__item row__item--12">
                    <input class="button button--primary" type="submit" value="<fmt:message key="saveChanges" bundle="${bundle}" />" />
                </div>
            </div>
        </form>
    </div>

</div>

<%@ include file="../custom/footer.jsp" %>