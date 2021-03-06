package be.vdab.entities;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "artikelgroepen")
public class Artikelgroep {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String naam;
	
	@OneToMany(mappedBy = "artikelgroep")
	private Set<Artikel> artikels;
	
	protected Artikelgroep() {}
	
	public Artikelgroep(String naam) {
		this.naam = naam;
	}

	public long getId() {
		return id;
	}

	public String getNaam() {
		return naam;
	}

	public void setNaam(String naam) {
		this.naam = naam;
	}
	
	public Set<Artikel> getArtikels() {
		return artikels;
	}
	
	public void addArtikel(Artikel artikel) {
		artikels.add(artikel);
		if (artikel.getArtikelgroep() != this) {
			artikel.setArtikelgroep(this);
		}
	}
	
	public void removeArtikel(Artikel artikel) {
		artikels.remove(artikel);
		if (artikel.getArtikelgroep() == this) {
			artikel.setArtikelgroep(null);
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((naam == null) ? 0 : naam.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Artikelgroep))
			return false;
		Artikelgroep other = (Artikelgroep) obj;
		if (naam == null) {
			if (other.naam != null)
				return false;
		} else if (!naam.equals(other.naam))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return naam;
	}
	

}
