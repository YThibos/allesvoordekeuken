package be.vdab.valueobjects;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Embeddable;

@Embeddable
public class Korting implements Serializable {

	private static final long serialVersionUID = 1L;

	private int vanafAantal;
	private BigDecimal kortingspercentage;
	
	
	// CONSTRUCTORS
	protected Korting() {}
	
	public Korting(int vanafAantal, BigDecimal kortingspercentage) {
		this.vanafAantal = vanafAantal;
		this.kortingspercentage = kortingspercentage;
	}

	// GETTERS
	public int getVanafAantal() {
		return vanafAantal;
	}

	public BigDecimal getKortingspercentage() {
		return kortingspercentage;
	}

	// HASHCODE, EQUALS & TOSTRING
	@Override
	public int hashCode() {
		return vanafAantal;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Korting)) {
			return false;
		}
		Korting andereKorting = (Korting) obj;
		return this.vanafAantal == andereKorting.vanafAantal;
	}
	
	@Override
	public String toString() {
		return kortingspercentage + "% korting vanaf " + vanafAantal + " stuks";  
	}
	
}
