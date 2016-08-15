<%@ include file="custom/header.jsp" %>

<div class="content">

    <h1 class="content__title">
    	<fmt:message key="applicationsList" bundle="${bundle}" />
   	</h1>

    <div class="content__section">

        <form class="pull-left" method="get">
            <div class="row">
                <div class="row__item">
                    <select class="input input--select" name="faculty">
                        <option value="" ${empty selectedFaculty ? 'selected' : ''}>
                        	<fmt:message key="allFaculties" bundle="${bundle}" />
                       	</option>
                        <c:forEach var="f" items="${facultiesList}">
                            <option value="${f.getId()}" ${selectedFaculty.getId() eq f.getId() ? 'selected' : ''}>
                            	${f.getName()}
                           	</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="row__item">
                    <input class="button button--primary" type="submit" value="<fmt:message key="showApplications" bundle="${bundle}" />" />
                </div>
            </div>
        </form>

        <form class="pull-right" method="get">
            <div class="row">
                <div class="row__item">
                    <input class="input" type="text" name="search" value="${param.search}" placeholder="<fmt:message key="searchCertificate" bundle="${bundle}" />" autocomplete="off" />
                </div>
                <div class="row__item">
                    <input class="button button--primary" type="submit" value="<fmt:message key="search" bundle="${bundle}" />" />
                </div>
            </div>
        </form>

    </div>

	<c:if test="${not empty searchMessage}">
        <div class="content__section">
            <div class="alert alert--danger">
            	<fmt:message key="${searchMessage}" bundle="${bundle}" />
           	</div>
        </div>
    </c:if>

    <c:if test="${not empty selectedFaculty}">
    	<div class="content__section">
	    	<c:choose>
	    		<c:when test="${selectedFaculty.isAvailable()}">
            		<div class="alert alert--success">
            			${selectedFaculty.getName()} <fmt:message key="facultyAvailable" bundle="${bundle}" />
           			</div>
	    		</c:when>    
	    		<c:otherwise>
	            	<div class="alert alert--warning">
	            		${selectedFaculty.getName()} <fmt:message key="facultyNotAvailable" bundle="${bundle}" />
            		</div>
	    		</c:otherwise>
			</c:choose>
		</div>
	</c:if>

    <div class="content__section">
        <table class="table table--stripped">
            <thead>
                <tr>
                    <th><fmt:message key="Id" bundle="${bundle}" /></th>
                    <th><fmt:message key="faculty" bundle="${bundle}" /></th>
                    <th><fmt:message key="name" bundle="${bundle}" /></th>
                    <th><fmt:message key="surname" bundle="${bundle}" /></th>
                    <th><fmt:message key="certificateNumber" bundle="${bundle}" /></th>
                    <th><fmt:message key="certificateGrade" bundle="${bundle}" /></th>
                    <th><fmt:message key="examGrades" bundle="${bundle}" /></th>
                    <th><fmt:message key="total" bundle="${bundle}" /></th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="a" items="${applicationsList}">
                    <tr>
                        <td>${a.getId()}</td>
                        <td>${a.getFaculty().getName()}</td>
                        <td>${a.getUser().getName()}</td>
                        <td>${a.getUser().getSurname()}</td>
                        <td>${a.getCertificateNumber()}</td>
                        <td>${a.getCertificateGrade()}</td>
                        <td>${a.getExams().values().toArray()[0]},
                       		${a.getExams().values().toArray()[1]},
                        	${a.getExams().values().toArray()[2]}</td>
                        <td>${a.getSumGrade()}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>

    <c:if test="${applicationsList.isEmpty()}">
        <div class="content__section">
            <div class="alert alert--default"><fmt:message key="noApplications" bundle="${bundle}" /></div>
        </div>
    </c:if>
    
	<jsp:include page="custom/pagination.jsp" />

</div>

<%@ include file="custom/footer.jsp" %>
