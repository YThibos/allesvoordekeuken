package be.vdab.entities;

import java.math.BigDecimal;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("NF")
public class NonFoodArtikel extends Artikel {
	
	private static final long serialVersionUID = 1L;
	
	private int garantie;
	
	// CONSTRUCTORS
	protected NonFoodArtikel() {}
	
	public NonFoodArtikel(String naam, BigDecimal aankoopprijs, BigDecimal verkoopprijs, int garantie) {
		super(naam, aankoopprijs, verkoopprijs);
		setGarantie(garantie);
	}


	// GETTERS & SETTERS
	public int getGarantie() {
		return garantie;
	}

	public void setGarantie(int garantie) {
		if (!isValidGarantie(garantie)) {
			throw new IllegalArgumentException("Garantie moet minstens één jaar zijn");
		}
		this.garantie = garantie;
	}
	
	// VALIDATION METHODS
	public static boolean isValidGarantie(int garantie) {
		return garantie > 0;
	}

}
