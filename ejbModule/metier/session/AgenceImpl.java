package metier.session;

import java.util.List;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import metier.entities.Compte;

@Stateless(name="AG")
@SuppressWarnings("unchecked")
public class AgenceImpl implements IAgenceRemote, IAgenceLocal{

	@PersistenceContext(unitName="Agence")
	private EntityManager em;
	@Override
	public Compte ajouterCompte(Compte cp) {
		em.persist(cp);
		return cp;
	}

	@Override
	public Compte consulterCompte(Long code) {
		Compte cp=em.find(Compte.class, code);
		if(cp==null)throw new RuntimeException("Compte introuvable");
		return cp;
	}

	@Override
	public List<Compte> afficherToutLesComptes() {
		Query req=em.createQuery("select c from Compte c");
		return req.getResultList();
	}

	@Override
	public void faireUnVersement(Long code, double mt) {
		Compte cp = consulterCompte(code);
		cp.setSolde(cp.getSolde()+mt);
		
	}

	@Override
	public void faireUnRetrait(Long code, double mt) {
		Compte cp = consulterCompte(code);
		if(cp.getSolde()<mt)throw new RuntimeException("Solde insuffisant!");
		cp.setSolde(cp.getSolde()-mt);
		
	}

	@Override
	public void faireUnVirement(Long cp1, Long cp2, double mt) {
		faireUnRetrait(cp1,mt);
		faireUnVersement(cp2,mt);
	}



}
