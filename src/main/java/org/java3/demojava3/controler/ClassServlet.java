package org.java3.demojava3.controler;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.java3.demojava3.model.User;
import org.java3.demojava3.service.ClassService;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet({"/classes", "/classes/new", "/classes/insert",
        "/classes/delete", "/classes/edit", "/classes/update",
        "/classes/getClassName"})
public class ClassServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private ClassService classService = new ClassService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null || !user.getRole().equals("ADMIN")) {
            response.sendRedirect("/login");
            return;
        }

        String action = request.getServletPath();
        try {
            switch (action) {
                case "/classes/insert":
                case "/classes/update":
                case "/classes/delete":
                    classService.insertClass(request, response);
                    break;
                default:
                    classService.listClass(request, response);
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
                case "/classes/new":
                case "/classes/edit":
                case "/classes/delete":
                    if (user.getRole().equals("ADMIN")) {
                        classService.showNewForm(request, response);
                    } else {
                        response.sendRedirect("/classes");
                    }
                    break;
                default:
                    classService.listClass(request, response);
                    break;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}