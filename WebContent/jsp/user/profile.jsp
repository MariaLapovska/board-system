<%@ include file="../custom/header.jsp" %>

<div class="content">

    <h1 class="content__title"><fmt:message key="welcome" bundle="${bundle}" /> ${user.getName()}!</h1>

	<c:if test="${not empty param.message}">
        <div class="content__section">
            <div class="alert alert--${param.message eq 'changesSuccess' ? 'success' : 'danger'}">
                <p><fmt:message key="${param.message}" bundle="${bundle}" /></p>
            </div>
        </div>
    </c:if>

    <c:if test="${empty userApplication}">
        <div class="content__section">
            <div class="alert alert--warning">
                <p><fmt:message key="noApplication" bundle="${bundle}" /></p>
                <p>
                    <a class="button button--primary" href="${context}/board-system/application/add">
                    	<fmt:message key="createApplication" bundle="${bundle}" />
                    </a>
                </p>
            </div>
        </div>
    </c:if>

    <c:if test="${not empty userApplication && userApplication.getFaculty().isAvailable()}">
        <div class="content__section">
            <div class="alert alert--success">
                <p><fmt:message key="haveApplication" bundle="${bundle}" /></p>
                <p>
                    <a class="button button--primary" href="${context}/board-system/application/edit">
                    	<fmt:message key="editApplication" bundle="${bundle}" />
                    </a>
                    <a class="button button--default" href="${context}/board-system/application/delete">
                    	<fmt:message key="deleteApplication" bundle="${bundle}" />
                    </a>
                </p>
            </div>
        </div>
    </c:if>
    
    <c:if test="${not empty userApplication && not userApplication.getFaculty().isAvailable()}">
        <div class="content__section">
	        <c:choose>
			    <c:when test="${applicationNo le userApplication.getFaculty().getPlan()}">
			        <div class="alert alert--success">
		                <fmt:message key="facultyPassed" bundle="${bundle}" /> ${userApplication.getFaculty().getName()}.
		                 <fmt:message key="number" bundle="${bundle}" /> ${applicationNo}. 
		                 <fmt:message key="totalNumber" bundle="${bundle}" /> ${totalApplicationNo}
	            	</div>
			    </c:when>    
			    <c:otherwise>
			        <div class="alert alert--danger">
		                <fmt:message key="facultyNotPassed" bundle="${bundle}" /> ${userApplication.getFaculty().getName()}.
		                 <fmt:message key="number" bundle="${bundle}" /> ${applicationNo}. 
		                 <fmt:message key="totalNumber" bundle="${bundle}" /> ${totalApplicationNo}
	            	</div>
			    </c:otherwise>
			</c:choose>
        </div>
    </c:if>
    
    <div class="content__section">
        <jsp:include page="../custom/profileInfo.jsp"/>
        
        <c:if test="${empty userApplication || userApplication.getFaculty().isAvailable()}">
	        <div class="row">
	            <div class="row__item">
	                <a class="button button--primary" href="${context}/board-system/profile/edit">
	                	<fmt:message key="editProfile" bundle="${bundle}" />
	                </a>
	                <a class="button button--default" href="${context}/board-system/profile/delete">
	                	<fmt:message key="deleteProfile" bundle="${bundle}" />
	                </a>
	            </div>
	        </div>
        </c:if>
    </div>
    
    <div class="content__section">
    	
    	<h1 class="table__title"><fmt:message key="availableFaculties" bundle="${bundle}" /></h1>
    	
        <table class="table table--stripped">
            <thead>
                <tr>
                    <th><fmt:message key="faculty" bundle="${bundle}" /></th>
                    <th><fmt:message key="exams" bundle="${bundle}" /></th>
                    <th><fmt:message key="plan" bundle="${bundle}" /></th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="f" items="${examsList}">
                    <tr>
                        <td>${f.getName()}</td>
                        <td>${f.getSubjects().toArray()[0].getName()},
                       		${f.getSubjects().toArray()[1].getName()},
                        	${f.getSubjects().toArray()[2].getName()}</td>
                       	<td>${f.getPlan()}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>

    <c:if test="${examsList.isEmpty()}">
        <div class="content__section">
            <div class="alert alert--default"><fmt:message key="noFaculties" bundle="${bundle}" /></div>
        </div>
    </c:if>

</div>

<%@ include file="../custom/footer.jsp" %>