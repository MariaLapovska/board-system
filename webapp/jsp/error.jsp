<%@ taglib prefix="er" uri="../WEB-INF/custom.tld"%>
<%@ include file="custom/header.jsp" %>

<div class="content content--form">
	<h1 class="content__title">
		<er:error code="404">
			<fmt:message key="error" bundle="${bundle}" />
		</er:error>
	</h1>
	
	<div class="content__section">
		<a class="button button--primary" href="${context}/index.html">
			<fmt:message key="backHome" bundle="${bundle}" />
		</a>
	</div>
</div>

<%@ include file="custom/footer.jsp" %>