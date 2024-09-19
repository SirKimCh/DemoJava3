package org.java3.demojava3.controler;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.java3.demojava3.dao.StudentDAO;
import org.java3.demojava3.model.Student;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet({"/students","/students/new","/students/insert","/students/delete",
        "/students/edit","/students/update"})
public class StudentServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private StudentDAO studentDAO;

    public void init() {
        studentDAO = new StudentDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        String action = request.getServletPath();
        try {
            switch (action) {
                case "/students/insert":
                    insertStudent(request, response);
                    break;
                case "/students/update":
                    updateStudent(request, response);
                    break;
                default:
                    listStudent(request, response);
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
                    showNewForm(request, response);
                    break;
                case "/students/delete":
                    deleteStudent(request, response);
                    break;
                case "/students/edit":
                    showEditForm(request, response);
                    break;
                default:
                    listStudent(request, response);
                    break;}
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/student/studentform.jsp").forward(request, response);
    }

    private void insertStudent(HttpServletRequest request, HttpServletResponse response)
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

    private void deleteStudent(HttpServletRequest request, HttpServletResponse response)
        throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        try {
            studentDAO.deleteStudent(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        response.sendRedirect("/students");
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException, SQLException{
        int id = Integer.parseInt(request.getParameter("id"));
        Student existingStudent = studentDAO.selectStudent(id);
        request.setAttribute("student", existingStudent);
        request.getRequestDispatcher("/WEB-INF/student/studentform.jsp").forward(request, response);
    }

    private void updateStudent(HttpServletRequest request, HttpServletResponse response)
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

    private void listStudent(HttpServletRequest request, HttpServletResponse response)
        throws SQLException, IOException, ServletException {
        request.setAttribute("listStudent", studentDAO.selectAllStudents());
        request.getRequestDispatcher("/WEB-INF/student/studentlist.jsp").forward(request, response);
    }
}
