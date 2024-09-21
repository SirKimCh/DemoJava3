package org.java3.demojava3.service;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.java3.demojava3.dao.ClassDAO;
import org.java3.demojava3.dao.StudentDAO;
import org.java3.demojava3.model.Class;
import org.java3.demojava3.model.Student;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class StudentService {
    private StudentDAO studentDAO = new StudentDAO();
    private ClassDAO classDAO = new ClassDAO();

    public void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        List<Class> listClass = classDAO.selectAllClasses();
        request.setAttribute("listClass", listClass);
        request.getRequestDispatcher("/WEB-INF/student/studentform.jsp").forward(request, response);
    }

    public void insertStudent(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        String name = request.getParameter("name");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");
        String major = request.getParameter("major");
        boolean status = Boolean.parseBoolean(request.getParameter("status"));
        int classId = Integer.parseInt(request.getParameter("class_id"));
        Student newStudent = new Student(name, phone, address, major, status, classId);
        studentDAO.insertStudent(newStudent);
        response.sendRedirect("/students");
    }

    public void deleteStudent(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        studentDAO.deleteStudent(id);
        response.sendRedirect("/students");
    }

    public void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        int id = Integer.parseInt(request.getParameter("id"));
        Student existingStudent = studentDAO.selectStudent(id);
        List<Class> listClass = classDAO.selectAllClasses();
        request.setAttribute("student", existingStudent);
        request.setAttribute("listClass", listClass);
        request.getRequestDispatcher("/WEB-INF/student/studentform.jsp").forward(request, response);
    }

    public void updateStudent(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");
        String major = request.getParameter("major");
        boolean status = Boolean.parseBoolean(request.getParameter("status"));
        int classId = Integer.parseInt(request.getParameter("class_id"));
        Student student = new Student(id, name, phone, address, major, status, classId);
        studentDAO.updateStudent(student);
        response.sendRedirect("/students");
    }

    public void listStudent(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        request.setAttribute("listStudent", studentDAO.selectAllStudents());
        request.getRequestDispatcher("/WEB-INF/student/studentlist.jsp").forward(request, response);
    }
}