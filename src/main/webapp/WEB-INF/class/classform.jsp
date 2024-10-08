<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Class Manager Demo Java 3</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<header>
    <nav class="navbar navbar-expand-lg navbar-light" style="background-color: orange;">
        <div class="container-fluid">
            <a class="navbar-brand" href="/students">Student Manager</a>
            <div class="collapse navbar-collapse">
                <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                    <li class="nav-item">
                        <a class="nav-link" href="<%=request.getContextPath()%>/students">Student List</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="<%=request.getContextPath()%>/classes">Class List</a>
                    </li>
                </ul>
                <ul class="navbar-nav ml-auto">
                    <li class="nav-item">
                        <a class="nav-link" href="<%=request.getContextPath()%>/logout">Logout</a>
                    </li>
                </ul>
            </div>
        </div>
    </nav>
</header>
<br>
<div class="container col-md-5">
    <div class="card">
        <div class="card-body">
            <c:choose>
            <c:when test="${existingClass != null}">
            <form action="/classes/update" method="post">
                </c:when>
                <c:otherwise>
                <form action="/classes/insert" method="post">
                    </c:otherwise>
                    </c:choose>

                    <h2 class="mb-4">
                        <c:choose>
                            <c:when test="${existingClass != null}">
                                Edit Class
                            </c:when>
                            <c:otherwise>
                                Add New Class
                            </c:otherwise>
                        </c:choose>
                    </h2>

                    <c:if test="${existingClass != null}">
                        <input type="hidden" name="id" value="<c:out value='${existingClass.id}' />"/>
                    </c:if>

                    <div class="mb-3">
                        <label for="name" class="form-label">Class Name</label>
                        <input type="text" id="name" value="<c:out value='${existingClass.name}' />" class="form-control" name="name" required>
                    </div>

                    <div class="mb-3 form-check">
                        <input type="checkbox" class="form-check-input" id="status" name="status" <c:if test="${existingClass != null && existingClass.status}">checked</c:if>>
                        <label class="form-check-label" for="status">Class Status</label>
                    </div>

                    <button type="submit" class="btn btn-success">Save</button>
                </form>
        </div>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>