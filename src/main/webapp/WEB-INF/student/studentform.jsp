<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Student Manager Demo Java 3</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<header>
    <nav class="navbar navbar-expand-lg navbar-light" style="background-color: orange;">
        <div class="container-fluid">
            <a class="navbar-brand" href="#">Student Manager</a>
            <div class="collapse navbar-collapse">
                <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                    <li class="nav-item">
                        <a class="nav-link" href="/students">Student List</a>
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
            <c:when test="${student != null}">
            <form action="/students/update" method="post">
                </c:when>
                <c:otherwise>
                <form action="/students/insert" method="post">
                    </c:otherwise>
                    </c:choose>

                    <h2 class="mb-4">
                        <c:choose>
                            <c:when test="${student != null}">
                                Edit Student
                            </c:when>
                            <c:otherwise>
                                Add New Student
                            </c:otherwise>
                        </c:choose>
                    </h2>

                    <c:if test="${student != null}">
                        <input type="hidden" name="id" value="<c:out value='${student.id}' />"/>
                    </c:if>

                    <div class="mb-3">
                        <label for="name" class="form-label">Student Name</label>
                        <input type="text" id="name" value="<c:out value='${student.name}' />" class="form-control" name="name" required>
                    </div>

                    <div class="mb-3">
                        <label for="phone" class="form-label">Student Phone</label>
                        <input type="text" id="phone" value="<c:out value='${student.phone}' />" class="form-control" name="phone">
                    </div>

                    <div class="mb-3">
                        <label for="address" class="form-label">Student Address</label>
                        <input type="text" id="address" value="<c:out value='${student.address}' />" class="form-control" name="address">
                    </div>

                    <div class="mb-3">
                        <label for="major" class="form-label">Student Major</label>
                        <input type="text" id="major" value="<c:out value='${student.major}' />" class="form-control" name="major">
                    </div>

                    <div class="mb-3 form-check">
                        <input type="checkbox" class="form-check-input" id="status" name="status" <c:if test="${student != null && student.status}">checked</c:if>>
                        <label class="form-check-label" for="status">Student Status</label>
                    </div>

                    <div class="mb-3">
                        <label for="class_id" class="form-label">Class</label>
                        <select id="class_id" name="class_id" class="form-select">
                            <c:forEach var="item" items="${listClass}">
                                <option value="${item.id}" <c:if test="${student != null && student.classId == item.id}">selected</c:if>>
                                    <c:out value="${item.name}"/>
                                </option>
                            </c:forEach>
                        </select>
                    </div>

                    <button type="submit" class="btn btn-success">Save</button>
                </form>
        </div>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>