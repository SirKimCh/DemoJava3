<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Student Manager Demo Java 3</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://code.jquery.com/jquery-3.7.1.min.js"
            integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="https://cdn.datatables.net/1.13.4/css/dataTables.bootstrap5.min.css"/>
    <script src="https://cdn.datatables.net/1.13.4/js/jquery.dataTables.min.js"></script>
    <script src="https://cdn.datatables.net/1.13.4/js/dataTables.bootstrap5.min.js"></script>
</head>
<body>
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
<div class="container mt-4">
    <h3 class="text-center">List of Students</h3>
    <hr>
    <div class="d-flex justify-content-end mb-3">
        <a href="<%=request.getContextPath()%>/students/new" class="btn btn-success">Add New Student</a>
    </div>
    <table id="myTable" class="table table-striped table-bordered">
        <thead>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Phone</th>
            <th>Address</th>
            <th>Major</th>
            <th>Status</th>
            <th>Class Name</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="student" items="${listStudent}">
            <tr>
                <td><c:out value="${student.id}"/></td>
                <td><c:out value="${student.name}"/></td>
                <td><c:out value="${student.phone}"/></td>
                <td><c:out value="${student.address}"/></td>
                <td><c:out value="${student.major}"/></td>
                <td><c:out value="${student.status ? 'Active' : 'Inactive'}"/></td>
                <td class="class-name" data-class-id="${student.classId}"></td>
                <td>
                    <a href="/students/edit?id=<c:out value='${student.id}' />" class="btn btn-primary btn-sm">Edit</a>
                    &nbsp;
                    <button class="btn btn-danger btn-sm" onclick="confirmDelete(<c:out value='${student.id}'/>)">Delete</button>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
<script type="text/javascript">
    function confirmDelete(studentId) {
        if (confirm("Are you sure you want to delete this student?")) {
            window.location.href = "/students/delete?id=" + studentId;
        }
    }

    $(document).ready(function () {
        $('#myTable').DataTable();

        $('.class-name').each(function () {
            var classId = $(this).data('class-id');
            var $this = $(this);
            $.ajax({
                url: '/classes/getClassName',
                type: 'GET',
                data: { classId: classId },
                success: function (response) {
                    $this.text(response.className);
                },
                error: function () {
                    console.error('Error fetching class name');
                }
            });
        });
    });
</script>
</body>
</html>