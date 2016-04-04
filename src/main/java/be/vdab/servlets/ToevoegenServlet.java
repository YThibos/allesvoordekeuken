package be.vdab.servlets;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import be.vdab.entities.Artikel;
import be.vdab.services.ArtikelService;

/**
 * Servlet implementation class ToevoegenServlet
 */
@WebServlet("/artikels/toevoegen.htm")
public class ToevoegenServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private static final String VIEW = "/WEB-INF/JSP/artikels/toevoegen.jsp";
	private static final String REDIRECT_URL = "%s/artikels/zoekenopnummer.htm?id=%d";
	
	private final transient ArtikelService artikelService = new ArtikelService();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher(VIEW).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Map<String, String> fouten = new HashMap<>();
		
		String naam = request.getParameter("naam");
		if (!Artikel.isValidString(naam)) {
			fouten.put("naam", "Geef een geldige naam in");
		}
		
		BigDecimal aankoopprijs = null;
		
		try {
			aankoopprijs = new BigDecimal(request.getParameter("aankoopprijs"));
			if (!Artikel.isValidPrijs(aankoopprijs) || aankoopprijs.compareTo(Artikel.MINIMUM_AANKOOPPRIJS) < 0) {
				fouten.put("aankoopprijs", "Geef een getal groter dan 0.01 in");
			}
		}
		catch (NumberFormatException ex) {
			fouten.put("aankoopprijs", "Geef een getal groter dan 0.01 in");
		}
		
		BigDecimal verkoopprijs = null;
		
		try {
			verkoopprijs = new BigDecimal(request.getParameter("verkoopprijs"));
			if (!Artikel.isValidPrijs(verkoopprijs) || verkoopprijs.compareTo(aankoopprijs) <= 0) {
				fouten.put("verkoopprijs", "Geef een niet-negatief getal in, hoger dan de aankoopprijs");
			}
		} catch (NumberFormatException ex) {
			fouten.put("verkoopprijs", "Geef een niet-negatief getal in, hoger dan de aankoopprijs");
		}
		
		if (fouten.isEmpty()) {
			Artikel nieuwArtikel = new Artikel(naam, aankoopprijs, verkoopprijs);
			artikelService.create(nieuwArtikel);
			response.sendRedirect(response.encodeRedirectURL(
					String.format(REDIRECT_URL, request.getContextPath(), nieuwArtikel.getId()))
					);
		}
		else {
			request.setAttribute("fouten", fouten);
			request.getRequestDispatcher(VIEW).forward(request, response);
		}
		
	}

}
