package be.vdab.servlets;

import java.io.IOException;
import java.util.Collections;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import be.vdab.entities.Artikel;
import be.vdab.services.ArtikelService;

/**
 * Servlet implementation class KortingenServlet
 */
@WebServlet("/artikels/kortingen.htm")
public class KortingenServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private static final String VIEW = "/WEB-INF/JSP/artikels/kortingen.jsp";
	private final transient ArtikelService artikelService = new ArtikelService();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	
		if (request.getQueryString() != null) {
			
			try {
				Artikel artikel = artikelService.read(Long.parseLong(request.getParameter("id")));
				if (artikel != null) {
					request.setAttribute("gevondenArtikel", artikel);
				}
				else {
					request.setAttribute("fouten", Collections.singletonMap("id", "Artikel niet gevonden"));
				}
			}
			catch (NumberFormatException ex) {
				request.setAttribute("fouten", Collections.singletonMap("id", "Artikel niet gevonden"));
			}
			
		}

		request.setAttribute("alleArtikels", artikelService.findAll());
		request.getRequestDispatcher(VIEW).forward(request, response);
		
	}

}
