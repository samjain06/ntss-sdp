package com.ntss.registration.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ntss.registration.dao.UserDao;
import com.ntss.registration.model.User;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/register")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDao userDao = new UserDao();

    /**
     * Default constructor. 
     */
    public UserServlet() {
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		/*Opens the Registration Page*/
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/register.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*Extracting parameters from request*/
		String email = request.getParameter("email");
		String pwd = request.getParameter("pwd");
		
		/*Creating the User*/
		User user = new User();
		user.setEmail(email);
		user.setPwd(pwd);
		
		/*Registering User in the system*/		
		userDao.registerUser(user);
		
		/*Showing success message to the User*/
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/success.jsp");
		dispatcher.forward(request, response);
	}

}
