package metier.session;

import java.util.List;

import jakarta.ejb.Remote;
import metier.entities.Compte;

@Remote
public interface IAgenceRemote {

	public Compte ajouterCompte(Compte cp);
	
	public Compte consulterCompte(Long code);
	
	public List<Compte>afficherToutLesComptes();
	
	public void faireUnVersement(Long code, double mt);
	
	public void faireUnRetrait(Long code, double mt);
	
	public void faireUnVirement(Long cp1, Long cp2,double mt);
	
	
	
}
