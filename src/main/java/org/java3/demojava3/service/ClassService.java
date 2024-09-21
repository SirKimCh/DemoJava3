package org.java3.demojava3.service;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.java3.demojava3.dao.ClassDAO;
import org.java3.demojava3.model.Class;

import java.io.IOException;
import java.sql.SQLException;

public class ClassService {
    private ClassDAO classDAO = new ClassDAO();

    public void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/class/classform.jsp").forward(request, response);
    }

    public void insertClass(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        String name = request.getParameter("name");
        boolean status = Boolean.parseBoolean(request.getParameter("status"));
        Class newClass = new Class(name, status);
        classDAO.insertClass(newClass);
        response.sendRedirect("/classes");
    }

    public void deleteClass(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        classDAO.deleteClass(id);
        response.sendRedirect("/classes");
    }

    public void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        int id = Integer.parseInt(request.getParameter("id"));
        Class existingClass = classDAO.selectClass(id);
        request.setAttribute("existingClass", existingClass);
        request.getRequestDispatcher("/WEB-INF/class/classform.jsp").forward(request, response);
    }

    public void updateClass(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        boolean status = Boolean.parseBoolean(request.getParameter("status"));
        Class classes = new Class(id, name, status);
        classDAO.updateClass(classes);
        response.sendRedirect("/classes");
    }

    public void listClass(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        request.setAttribute("listClass", classDAO.selectAllClasses());
        request.getRequestDispatcher("/WEB-INF/class/classlist.jsp").forward(request, response);
    }

    public void getClassName(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int classId = Integer.parseInt(request.getParameter("classId"));
        Class classObj = classDAO.selectClass(classId);
        response.setContentType("application/json");
        response.getWriter().write("{\"className\": \"" + classObj.getName() + "\"}");
    }
}
