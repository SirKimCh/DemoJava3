package org.java3.demojava3.controler;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.java3.demojava3.model.User;
import org.java3.demojava3.service.StudentService;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet({"/students", "/students/new", "/students/insert", "/students/delete",
        "/students/edit", "/students/update","/"})
public class StudentServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private final StudentService studentService = new StudentService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getServletPath();
        try {
            switch (action) {
                case "/students/insert":
                    studentService.insertStudent(request, response);
                    break;
                case "/students/update":
                    studentService.updateStudent(request, response);
                    break;
                default:
                    studentService.listStudent(request, response);
                    break;
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getServletPath();
        try {
            switch (action) {
                case "/students/new":
                    studentService.showNewForm(request, response);
                    break;
                case "/students/delete":
                    studentService.deleteStudent(request, response);
                    break;
                case "/students/edit":
                    studentService.showEditForm(request, response);
                    break;
                default:
                    studentService.listStudent(request, response);
                    break;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}