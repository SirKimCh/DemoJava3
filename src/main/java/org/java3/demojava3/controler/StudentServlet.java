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
        "/students/edit", "/students/update"})
public class StudentServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private final StudentService studentService = new StudentService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null || (!user.getRole().equals("USER") && !user.getRole().equals("ADMIN"))) {
            response.sendRedirect("/login");
            return;
        }

        String action = request.getServletPath();
        try {
            switch (action) {
                case "/students/insert":
                case "/students/update":
                case "/students/delete":
                    if (user.getRole().equals("ADMIN")) {
                        studentService.insertStudent(request, response);
                    } else {
                        response.sendRedirect("/students");
                    }
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
        User user = (User) request.getSession().getAttribute("user");
        if (user == null || (!user.getRole().equals("USER") && !user.getRole().equals("ADMIN"))) {
            response.sendRedirect("/login");
            return;
        }
        String action = request.getServletPath();
        try {
            switch (action) {
                case "/students/new":
                case "/students/edit":
                case "/students/delete":
                    if (user.getRole().equals("ADMIN")) {
                        studentService.showNewForm(request, response);
                    } else {
                        response.sendRedirect("/students");
                    }
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