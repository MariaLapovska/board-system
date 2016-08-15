<%@ include file="../custom/header.jsp" %>

<div class="content content--form">

    <h1 class="content__title">
        <fmt:message
        	key="${param.action eq 'add' ? 'createApplication' : 'editApplication'}" 
        	bundle="${bundle}"/>
    </h1>

    <c:if test="${not empty param.message}">
        <div class="content__section">
            <div class="alert alert--danger">
            	<fmt:message key="${param.message}" bundle="${bundle}" />
           	</div>
        </div>
    </c:if>

    <div class="content__section">
        <form action="${context}/controller/application/edit" method="post">
            <div class="row">
                <div class="row__item row__item--12">
                    <select class="input input--select" name="faculty">
                        <option value="" selected>
                            <fmt:message key="chooseFaculty" bundle="${bundle}" />
                        </option>
                        <c:forEach var="f" items="${facultiesList}">
                            <option value="${f.getId()}" >
                            	${f.getName()}
                           	</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
            <div class="row">
                <div class="row__item row__item--6">
                    <input class="input" type="text" name="certificateNumber" 
                    placeholder="<fmt:message key="certificateNumber" bundle="${bundle}" />" autocomplete="off" required />
                </div>
                <div class="row__item row__item--6">
                    <input class="input" type="text" name="certificateGrade" 
                    placeholder="<fmt:message key="certificateGrade" bundle="${bundle}" />" autocomplete="off" required />
                </div>
            </div>
            
			<c:forEach begin="1" end="3">
	            <div class="row">
	                <div class="row__item row__item--6">
	                    <select class="input input--select" name="subjects">
	                        <option value="" selected>
	                        	<fmt:message key="chooseSubject" bundle="${bundle}" />
	                        </option>
	                        <c:forEach var="s" items="${subjectsList}">
	                        	<option value="${s.getId()}">${s.getName()}</option>
	                        </c:forEach>
	                    </select>
	                </div>
	                <div class="row__item row__item--6">
	                    <input class="input" type="text" name="grades" 
	                    placeholder="<fmt:message key="grade" bundle="${bundle}" />" autocomplete="off" required />
	                </div>
	            </div>
           	</c:forEach>
           	
            <div class="row">
                <div class="row__item row__item--12">
                    <input type="hidden" name="action" value="${param.action}" />
                    <input class="button button--primary" type="submit" 
                    value="<fmt:message key="${param.action eq 'add' ? 'createApplication' : 'editApplication'}" 
                   					 bundle="${bundle}"/>" 
    				/>
                </div>
            </div>
        </form>
    </div>

</div>

<%@ include file="../custom/footer.jsp" %>