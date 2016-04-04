package be.vdab.entities;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "artikels")
public class Artikel implements Serializable {

	private static final long serialVersionUID = 1L;
	public static BigDecimal MINIMUM_AANKOOPPRIJS = BigDecimal.valueOf(0.01);
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String naam;
	private BigDecimal aankoopprijs;
	private BigDecimal verkoopprijs;
	
	// CONSTRUCTORS
	protected Artikel() {};
	
	public Artikel(String naam, BigDecimal aankoopprijs, BigDecimal verkoopprijs) {
		setNaam(naam);
		setAankoopprijs(aankoopprijs);
		setVerkoopprijs(verkoopprijs);
	}

	// GETTERS & SETTERS
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNaam() {
		return naam;
	}
	public void setNaam(String naam) {
		if (!isValidString(naam)) {
			throw new IllegalArgumentException();
		}
		this.naam = naam;
	}
	public BigDecimal getAankoopprijs() {
		return aankoopprijs;
	}
	public void setAankoopprijs(BigDecimal aankoopprijs) {
		if (!isValidPrijs(aankoopprijs) && aankoopprijs.compareTo(MINIMUM_AANKOOPPRIJS) >= 0) {
			throw new IllegalArgumentException();
		}
		this.aankoopprijs = aankoopprijs;
	}
	public BigDecimal getVerkoopprijs() {
		return verkoopprijs;
	}
	public void setVerkoopprijs(BigDecimal verkoopprijs) {
		if (!isValidPrijs(verkoopprijs) && verkoopprijs.compareTo(aankoopprijs) <= 0) {
			throw new IllegalArgumentException();
		}
		this.verkoopprijs = verkoopprijs;
	}
	
	// VALIDATION METHODS
	public static boolean isValidString(String string) {
		return string != null && !string.isEmpty();
	}
	
	public static boolean isValidPrijs(BigDecimal prijs) {
		return prijs != null && prijs.compareTo(BigDecimal.ZERO) >= 0;
	}
	
}
