package org.java3.demojava3.service;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.java3.demojava3.dao.StudentDAO;
import org.java3.demojava3.model.Student;

import java.io.IOException;
import java.sql.SQLException;

public class StudentService {

    private StudentDAO studentDAO = new StudentDAO();

    public void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/student/studentform.jsp").forward(request, response);
    }

    public void insertStudent(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        String name = request.getParameter("name");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");
        String major = request.getParameter("major");
        Student newStudent = new Student(name, phone, address, major);
        try {
            studentDAO.insertStudent(newStudent);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        response.sendRedirect("/students");
    }

    public void deleteStudent(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        try {
            studentDAO.deleteStudent(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        response.sendRedirect("/students");
    }

    public void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        int id = Integer.parseInt(request.getParameter("id"));
        Student existingStudent = studentDAO.selectStudent(id);
        request.setAttribute("student", existingStudent);
        request.getRequestDispatcher("/WEB-INF/student/studentform.jsp").forward(request, response);
    }

    public void updateStudent(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");
        String major = request.getParameter("major");

        Student student = new Student(id, name, phone, address, major);
        try {
            studentDAO.updateStudent(student);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        response.sendRedirect("/students");
    }

    public void listStudent(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        request.setAttribute("listStudent", studentDAO.selectAllStudents());
        request.getRequestDispatcher("/WEB-INF/student/studentlist.jsp").forward(request, response);
    }
}
