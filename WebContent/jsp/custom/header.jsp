<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:choose>
	<c:when test="${empty lang}">
		<c:set var="lang" value="${empty param.lang ? 'en_US' : param.lang}" scope="session"/>
	</c:when>
	<c:otherwise>
		<c:set var="lang" value="${empty param.lang ? lang : param.lang}" scope="session"/>
	</c:otherwise>
</c:choose>

<fmt:setLocale value="${lang}" />
<fmt:setBundle basename="localization/messages" var="bundle" />

<c:set var="context" value="${pageContext.request.contextPath}" />

<!doctype html>
<html>
<head>
    <title>
    	<fmt:message key="pageTitle" bundle="${bundle}" />
    </title>

    <meta charset="utf-8" />
    <meta name="x-ua-compatible" content="ie=edge" />
    <meta name="robots" content="index, follow" />
    
    <link rel="icon" href="${context}/assets/images/favicon.ico" type="image/x-icon" />
    <link rel="shortcut icon" href="${context}/assets/images/favicon.ico" type="image/x-icon" />

    <link rel="stylesheet" href="${context}/assets/styles/custom.css" />

</head>
<body>

    <div class="header-wrapper">
        <div class="header">

            <div class="header__logo">
                <a class="header__logo__link" href="${context}/">
                    <img class="header__logo__image" src="${context}/assets/images/logo.svg" alt="BoardSystem" />
                </a>
            </div>

            <ul class="header__menu">
                <li class="header__menu__item">
                    <a class="header__menu__link" href="${context}/">
                    	<fmt:message key="home" bundle="${bundle}" />
                   	</a>
                </li>
                <c:choose>
                    <c:when test="${empty user}">
                        <li class="header__menu__item">
                            <a class="header__menu__link" href="${context}/board-system/auth/login">
                            	<fmt:message key="logIn" bundle="${bundle}" />
                           	</a>
                        </li>
                        <li class="header__menu__item">
                            <a class="header__menu__link" href="${context}/board-system/auth/signup">
                            	<fmt:message key="signUp" bundle="${bundle}" />
                           	</a>
                        </li>
                    </c:when>
                    <c:otherwise>
                        <li class="header__menu__item">
                            <a class="header__menu__link" href="${context}/board-system/profile">
                            	<fmt:message key="profile" bundle="${bundle}" />
                           	</a>
                        </li>
                        <li class="header__menu__item">
                            <a class="header__menu__link" href="${context}/controller/logout">
                            	<fmt:message key="logOut" bundle="${bundle}" />
                           	</a>
                        </li>
                    </c:otherwise>
                </c:choose>
            </ul>

            <ul class="header__languages">
                <li class="header__languages__item">
                    <a class="header__languages__link ${lang ne 'ru_RU' ? 'header__languages__link--current' : ''}" 
                   	href="${lang ne 'ru_RU' 
                   	? '#' 
                   	: '?lang=en_US'}">
                    	ENG
                   	</a>
                </li>
                <li class="header__languages__item">
                    <a class="header__languages__link ${lang eq 'ru_RU' ? 'header__languages__link--current' : ''}" 
                    href="${lang eq 'ru_RU' 
                    ? '#' 
                    : '?lang=ru_RU'}">
                    	РУС
                   	</a>
                </li>
            </ul>

        </div>
    </div>