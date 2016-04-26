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
import be.vdab.entities.Artikelgroep;
import be.vdab.entities.FoodArtikel;
import be.vdab.entities.NonFoodArtikel;
import be.vdab.services.ArtikelService;
import be.vdab.services.ArtikelgroepService;

/**
 * Servlet implementation class ToevoegenServlet
 */
@WebServlet("/artikels/toevoegen.htm")
public class ToevoegenServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private static final String VIEW = "/WEB-INF/JSP/artikels/toevoegen.jsp";
	private static final String REDIRECT_URL = "%s/artikels/zoekenopnummer.htm?id=%d";
	
	private final transient ArtikelService artikelService = new ArtikelService();
	private final transient ArtikelgroepService artikelgroepService = new ArtikelgroepService();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("artikelgroepen", artikelgroepService.findAll());
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
		
		String soort = request.getParameter("soort");
		
		int houdbaarheid = 0;
		int garantie = 0;
		
		if (soort != null) {
			
			if (soort.equals("F")) {
				try {
					houdbaarheid = Integer.parseInt(request.getParameter("houdbaarheid"));
				}
				catch (NumberFormatException ex) {
					fouten.put("houdbaarheid", "Geef een (strikt) positief getal in");
				}
				
			}
			else if (soort.equals("NF")) {
				try {
					garantie = Integer.parseInt(request.getParameter("garantie"));
				}
				catch (NumberFormatException ex) {
					fouten.put("garantie", "Geef een (strikt) positief getal in");
				}
			}
			else {
				fouten.put("soort", "Ongeldige soort parameter");
			}
			
		}
		else {
			fouten.put("soort", "Duid een soort aan");
		}
		
		String artikelgroepId = request.getParameter("artikelgroepid");
		if (artikelgroepId == null) {
			fouten.put("artikelgroep", "Verplicht een artikelgroep aan te duiden");
		}
		
		if (fouten.isEmpty()) {
			Artikel nieuwArtikel;
			Artikelgroep artikelgroep = artikelgroepService.read(Long.parseLong(artikelgroepId));
			if (soort.equals("F")) {
				nieuwArtikel = new FoodArtikel(naam, aankoopprijs, verkoopprijs, houdbaarheid, artikelgroep);
			}
			else {
				nieuwArtikel = new NonFoodArtikel(naam, aankoopprijs, verkoopprijs, garantie, artikelgroep);
			}
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
