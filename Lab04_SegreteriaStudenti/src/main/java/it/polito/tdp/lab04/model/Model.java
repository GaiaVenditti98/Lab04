package it.polito.tdp.lab04.model;

import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.lab04.DAO.CorsoDAO;
import it.polito.tdp.lab04.DAO.StudenteDAO;

public class Model {

	private CorsoDAO cdao;
	private StudenteDAO sdao;

	public Model() {
		cdao = new CorsoDAO();
		sdao = new StudenteDAO();
	}

	public List<Corso> popolaMenuTendina() {
		
		return cdao.getTuttiICorsi();
	}

	public Studente completamentoAutomatico(Integer matricola) {
		List<Studente> studenti = sdao.getTuttiStudenti();
		int posizione = studenti.indexOf(new Studente(matricola, null, null, null));
		Studente s=null;
		if (posizione != -1) {
			s = studenti.get(posizione);
		}
		return s;
	}
	
	
	public List<Studente>getIscrittialCorso(Corso corso){
		
		return cdao.getStudentiIscrittiAlCorso(corso);
		
		
	}
	
	public List<Corso> getCorsiDelloStudente(Integer matricola){
		return sdao.getCorsidelloStudente(matricola);
	}
	
	public List<Studente> ritornaTuttiGliStudenti(){
		return sdao.getTuttiStudenti();
	}
	
	public boolean iscrivi(Studente s,Corso c) {
		return cdao.inscriviStudenteACorso(s, c);
	}
	

}
