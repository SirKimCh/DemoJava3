<%--
  Created by IntelliJ IDEA.
  User: MSI
  Date: 19/09/2024
  Time: 21:14
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Student Manager Demo Java 3</title>
</head>
<body>
<header>
    <nav class="navbar"
         style="background-color: orange">
        <div>
            <a href="/students">Student List</a>
        </div>
    </nav>
</header>
<br>
<div class="container col-md-5">
    <div class="card">
        <div class="card-body">
            <c:if test="${student != null}">
            <form action="/students/update" method="post">
                </c:if>
                <c:if test="${student == null}">
                <form action="/students/insert" method="post">
                    </c:if>

                    <caption>
                        <h2>
                            <c:if test="${student != null}">
                                Edit Student
                            </c:if>
                            <c:if test="${student == null}">
                                Add New Student
                            </c:if>
                        </h2>
                    </caption>

                    <c:if test="${student != null}">
                        <input type="hidden" name="id" value="<c:out value='${student.id}' />"/>
                    </c:if>

                    <fieldset class="form-group">
                        <label>Student Name</label> <input type="text"
                                                           value="<c:out value='${student.name}' />"
                                                           class="form-control"
                                                           name="name" required="required">
                    </fieldset>

                    <fieldset class="form-group">
                        <label>Student Phone</label> <input type="text"
                                                            value="<c:out value='${student.phone}' />"
                                                            class="form-control"
                                                            name="phone">
                    </fieldset>

                    <fieldset class="form-group">
                        <label>Student Address</label> <input type="text"
                                                              value="<c:out value='${student.address}' />"
                                                              class="form-control"
                                                              name="address">
                    </fieldset>

                    <fieldset class="form-group">
                        <label>Student Major</label> <input type="text"
                                                            value="<c:out value='${student.major}' />"
                                                            class="form-control"
                                                            name="major">
                    </fieldset>

                    <button type="submit" class="btn btn-success">Save</button>
                </form>
        </div>
    </div>
</div>
</body>
</html>
