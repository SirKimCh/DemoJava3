<%--
  Created by IntelliJ IDEA.
  User: MSI
  Date: 19/09/2024
  Time: 21:13
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Student Manager Demo Java 3</title>
    <script src="https://code.jquery.com/jquery-3.7.1.min.js" integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="https://cdn.datatables.net/2.1.6/css/dataTables.dataTables.css" />
    <script src="https://cdn.datatables.net/2.1.6/js/dataTables.js"></script>
</head>
<body>
<div class="row">
    <div class="container">
        <h3 class="text-center">List of Student</h3>
        <hr>
        <div class="container text-left">

            <a href="<%=request.getContextPath()%>/students/new" class="btn btn-success">Add
                New Student</a>
        </div>
        <br>
        <table id="myTable" class="display">
            <thead>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Phone</th>
                <th>Address</th>
                <th>Major</th>
                <th>Actions</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="student" items="${listStudent}">

                <tr>
                    <td><c:out value="${student.id}" /></td>
                    <td><c:out value="${student.name}" /></td>
                    <td><c:out value="${student.phone}" /></td>
                    <td><c:out value="${student.address}" /></td>
                    <td><c:out value="${student.major}" /></td>
                    <td><a href="/students/edit?id=<c:out value='${student.id}' />">Edit</a>
                        &nbsp;&nbsp;&nbsp;&nbsp;
                        <a href="javascript:void(0);" onclick="confirmDelete(<c:out value='${student.id}' />)">Delete</a></td>
                </tr>
            </c:forEach>
            </tbody>

        </table>
    </div>
</div>
<script type="text/javascript">
    function confirmDelete(studentId) {
        if (confirm("Are you sure you want to delete this student?")) {
            window.location.href = "/students/delete?id=" + studentId;
        }
    }
</script>
<script>
    $(document).ready(function () {
        $('#myTable').DataTable();
    });
</script>
</body>
</html>
