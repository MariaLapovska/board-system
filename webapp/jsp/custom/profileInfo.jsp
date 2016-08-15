<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<fmt:setLocale value="${lang}" />
<fmt:setBundle basename="localization/messages" var="bundle" />

<div class="row">
	<div class="row__item">
		<span class="label label--bold"> <fmt:message key="login"
				bundle="${bundle}" />:
		</span> <span class="label">${user.getLogin()}</span>
	</div>
</div>

<div class="row">
	<div class="row__item">
		<span class="label label--bold"> <fmt:message key="name"
				bundle="${bundle}" />:
		</span> <span class="label">${user.getName()}</span>
	</div>
</div>

<div class="row">
	<div class="row__item">
		<span class="label label--bold"> <fmt:message key="surname"
				bundle="${bundle}" />:
		</span> <span class="label">${user.getSurname()}</span>
	</div>
</div>

<div class="row">
	<div class="row__item">
		<span class="label label--bold"> <fmt:message key="password"
				bundle="${bundle}" />:
		</span> <span class="label"> <c:forEach var="p"
				items="${user.getPassword().toCharArray()}">
           		*
           	</c:forEach>
		</span>
	</div>
</div>