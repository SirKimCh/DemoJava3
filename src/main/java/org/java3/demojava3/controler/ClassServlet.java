package org.java3.demojava3.controler;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
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
        String action = request.getServletPath();
        try {
            switch (action) {
                case "/classes/insert":
                    classService.insertClass(request, response);
                    break;
                case "/classes/update":
                    classService.updateClass(request, response);
                    break;
                case "/classes/delete":
                    classService.deleteClassAndStudents(request, response);
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
        String action = request.getServletPath();
        try {
            switch (action) {
                case "/classes/new":
                    classService.showNewForm(request, response);
                    break;
                case "/classes/delete":
                    classService.deleteClass(request, response);
                    break;
                case "/classes/edit":
                    classService.showEditForm(request, response);
                    break;
                case "/classes/getClassName":
                    classService.getClassName(request, response);
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