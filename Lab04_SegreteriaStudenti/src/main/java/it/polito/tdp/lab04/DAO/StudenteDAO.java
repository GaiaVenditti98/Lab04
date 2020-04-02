package it.polito.tdp.lab04.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Studente;

public class StudenteDAO {
	List<Studente> studenti = new LinkedList<>();

	public List<Studente> getTuttiStudenti() {

		final String sql = "SELECT * FROM studente";

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);

			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				Integer matricola = rs.getInt("matricola");
				String cognome = rs.getString("cognome");
				String nome = rs.getString("nome");
				String CDS = rs.getString("CDS");

				System.out.println(matricola + " " + cognome + " " + nome + " " + CDS);

				Studente s = new Studente(matricola, cognome, nome, CDS);
				studenti.add(s);
			}
			conn.close();

			return studenti;

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db", e);
		}
	}

	public List<Corso> getCorsidelloStudente(Integer matricola) {
		List<Corso> ciao = new LinkedList<>();
		final String sql = "select c.codins, c.nome, c.crediti, c.pd " + "from corso as c, iscrizione i "
				+ "where c.codins = i.codins and i.matricola = ? ";

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, matricola);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				Corso c = new Corso(rs.getString("codins"), rs.getInt("crediti"), rs.getString("nome"),
						rs.getInt("pd"));
				System.out.println(rs.getString("codins")+" "+rs.getInt("crediti")+" " +rs.getString("nome")+" "+
						rs.getInt("pd"));

				ciao.add(c);
			}

			conn.close();

			return ciao;

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db", e);
		}
	}

}
