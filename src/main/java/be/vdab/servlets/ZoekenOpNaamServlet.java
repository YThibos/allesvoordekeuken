package be.vdab.servlets;

import java.io.IOException;
import java.util.Collections;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import be.vdab.services.ArtikelService;

/**
 * Servlet implementation class ZoekenOpNaamServlet
 */
@WebServlet("/artikels/zoekenopnaam.htm")
public class ZoekenOpNaamServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private static final String VIEW = "/WEB-INF/JSP/artikels/zoekenopnaam.jsp";
	private final transient ArtikelService artikelService = new ArtikelService(); 

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if (request.getQueryString() != null) {
			
			String naam = request.getParameter("woord");
			
			if (naam.isEmpty()) {
				request.setAttribute("fouten", Collections.singletonMap("woord", "zoekstring mag niet leeg zijn"));
			}
			else {
				request.setAttribute("artikels", artikelService.findLikeNaam("%" + naam + "%"));
			}
			
		}
		
		request.getRequestDispatcher(VIEW).forward(request, response);
	}

}
