<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="content__section">
    <div class="pagination">

        <c:if test="${currentPage ne 1}">
            <a class="pagination__link  pagination__link--arrow" href="?page=${currentPage - 1}">&lsaquo;</a>
        </c:if>

        <c:forEach begin="1" end="${noOfPages}" var="i">
            <c:choose>
                <c:when test="${currentPage eq i}">
                    <a class="pagination__link pagination__link--current" href="#">${i}</a>
                </c:when>
                <c:otherwise>
                    <a class="pagination__link" href="?page=${i}">${i}</a>
                </c:otherwise>
            </c:choose>
        </c:forEach>

        <c:if test="${currentPage lt noOfPages}">
            <a class="pagination__link pagination__link--arrow" href="?page=${currentPage + 1}">&rsaquo;</a>
        </c:if>

    </div>
</div>