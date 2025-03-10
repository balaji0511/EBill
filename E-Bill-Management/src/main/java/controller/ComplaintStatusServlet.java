package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.ComplaintDAO;

/**
 * Servlet implementation class ComplaintStatusServlet
 */
@WebServlet("/ComplaintStatusServlet")
public class ComplaintStatusServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session = req.getSession();
		String str = req.getParameter("compId");

		String status = ComplaintDAO.displayComplaint(str);
		session.setAttribute("status", status);
		
		req.getRequestDispatcher("complaint_status.jsp").include(req,  resp);
	}
}

