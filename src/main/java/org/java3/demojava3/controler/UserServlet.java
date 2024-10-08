package org.java3.demojava3.controler;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.java3.demojava3.model.User;
import org.java3.demojava3.service.UserService;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet({"/register", "/login"})
public class UserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserService userService = new UserService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getServletPath();
        try {
            switch (action) {
                case "/register":
                    userService.registerUser(request, response);
                    break;
                case "/login":
                    userService.loginUser(request, response);
                    break;
                default:
                    response.sendRedirect("/login");
                    break;
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getServletPath();
        switch (action) {
            case "/login":
                userService.getLogin(request, response);
                break;
            case "/register":
                userService.getRegister(request, response);
                break;
            case "/logout":
                userService.logoutUser(request, response);
                break;
            default:
                response.sendRedirect("/login");
                break;
        }
    }
}