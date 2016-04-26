package be.vdab.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.hibernate.annotations.ManyToAny;

import be.vdab.valueobjects.Korting;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "artikels")
@DiscriminatorColumn(name = "soort")
public abstract class Artikel implements Serializable {

	private static final long serialVersionUID = 1L;
	public static BigDecimal MINIMUM_AANKOOPPRIJS = BigDecimal.valueOf(0.01);
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String naam;
	private BigDecimal aankoopprijs;
	private BigDecimal verkoopprijs;
	
	@ElementCollection
	@CollectionTable(name="kortingen", joinColumns = @JoinColumn(name="artikelid"))
	@OrderBy("vanafAantal")
	private Set<Korting> kortingen;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "artikelgroepid")
	private Artikelgroep artikelgroep;
	
	// CONSTRUCTORS
	protected Artikel() {};
	
	public Artikel(String naam, BigDecimal aankoopprijs, BigDecimal verkoopprijs, Artikelgroep artikelgroep) {
		setNaam(naam);
		setAankoopprijs(aankoopprijs);
		setVerkoopprijs(verkoopprijs);
		setArtikelgroep(artikelgroep);
		kortingen = new HashSet<>();
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
	public Set<Korting> getKortingen() {
		return Collections.unmodifiableSet(kortingen);
	}
	public void setArtikelgroep(Artikelgroep artikelgroep) {
		this.artikelgroep = artikelgroep;
	}
	public Artikelgroep getArtikelgroep() {
		return artikelgroep;
	}
	
	// VALIDATION METHODS
	public static boolean isValidString(String string) {
		return string != null && !string.isEmpty();
	}
	
	public static boolean isValidPrijs(BigDecimal prijs) {
		return prijs != null && prijs.compareTo(BigDecimal.ZERO) >= 0;
	}

	@Override
	public String toString() {
		return "Artikel [naam=" + naam + ", aankoopprijs=" + aankoopprijs + ", verkoopprijs=" + verkoopprijs + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((naam == null) ? 0 : naam.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Artikel))
			return false;
		Artikel other = (Artikel) obj;
		if (id != other.id)
			return false;
		if (naam == null) {
			if (other.naam != null)
				return false;
		} else if (!naam.equals(other.naam))
			return false;
		return true;
	}
	
	// OTHERS
	public void addKorting(Korting korting) {
		this.kortingen.add(korting);
	}
	
	public void removeKorting(Korting korting) {
		this.kortingen.remove(korting);
	}
	
}
