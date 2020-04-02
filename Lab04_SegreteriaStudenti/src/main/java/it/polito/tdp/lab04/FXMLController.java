/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.lab04;

import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Model;
import it.polito.tdp.lab04.model.Studente;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {

	Model model;

	public Model getModel() {
		return model;
	}

	private ObservableList<Corso> list = FXCollections.observableArrayList();

	public void setModel(Model model) {

		list.addAll(model.popolaMenuTendina());
		menuTendina.setItems(list);
		this.model = model;
	}

	@FXML // ResourceBundle that was given to the FXMLLoader
	private ResourceBundle resources;

	@FXML // URL location of the FXML file that was given to the FXMLLoader
	private URL location;

	@FXML // fx:id="menuTendina"
	private ComboBox<Corso> menuTendina; // Value injected by FXMLLoader

	@FXML // fx:id="btbCercaIscrittiCorso"
	private Button btbCercaIscrittiCorso; // Value injected by FXMLLoader

	@FXML // fx:id="txtMatricola"
	private TextField txtMatricola; // Value injected by FXMLLoader

	@FXML // fx:id="checkBox"
	private CheckBox checkBox; // Value injected by FXMLLoader

	@FXML // fx:id="txtNome"
	private TextField txtNome; // Value injected by FXMLLoader

	@FXML // fx:id="txtCognome"
	private TextField txtCognome; // Value injected by FXMLLoader

	@FXML // fx:id="btnCercaCorsi"
	private Button btnCercaCorsi; // Value injected by FXMLLoader

	@FXML // fx:id="btnIscrivi"
	private Button btnIscrivi; // Value injected by FXMLLoader

	@FXML // fx:id="txtArea"
	private TextArea txtArea; // Value injected by FXMLLoader

	@FXML // fx:id="btnReset"
	private Button btnReset; // Value injected by FXMLLoader

	@FXML
	void DoCercaCorsi(ActionEvent event) {

		txtArea.clear();
		int matricola = Integer.parseInt(txtMatricola.getText());
		List<Corso> corsi = model.getCorsiDelloStudente(matricola);

		if (corsi.size() == 0)
			txtArea.appendText("questo studente non è iscritto a nessun corso");
		else {
			for (Corso c : corsi)
				txtArea.appendText(c.Strinapiulunga() + "\n");
		}

	}

	@FXML
	void doCercaIscrittiCorso(ActionEvent event) {
		txtArea.clear();
		Corso corso = menuTendina.getValue();
		List<Studente> studenti;
		if (corso == null) {
			studenti = model.ritornaTuttiGliStudenti();
		} else {

			studenti = model.getIscrittialCorso(corso);
		}

		if (studenti.size() == 0)
			txtArea.appendText("nessuno studente è iscritto a questo corso");
		else {

			for (Studente s : studenti)
				txtArea.appendText(s.toString() + "\n");
		}

	}

	@FXML
	void doCercaNomeCognome(ActionEvent event) {
		int matricola;
		try {
			matricola = Integer.parseInt(txtMatricola.getText());
		} catch (Exception e) {
			txtArea.setText("la matricola deve essere un numero!");
			return;
		}

		Studente ciao = model.completamentoAutomatico(matricola);
		if (ciao == null) {
			txtArea.setText("matricola insistente");
			txtMatricola.clear();
			return;
		}

		txtNome.setText(ciao.getNome());
		txtCognome.setText(ciao.getCognome());

	}

	@FXML
	void doIscrivi(ActionEvent event) {
		txtArea.clear();
		int matricola = Integer.parseInt(txtMatricola.getText());
		Studente studente = model.completamentoAutomatico(matricola);
		Corso corso = menuTendina.getValue();
		boolean iscritto = model.iscrivi(studente, corso);
		if (iscritto)
			txtArea.appendText("Studente iscritto al corso");
		else
			txtArea.appendText("Studente già iscritto a questo corso");

	}

	@FXML
	void doReset(ActionEvent event) {
		txtArea.clear();
		txtNome.clear();
		txtCognome.clear();
		txtMatricola.clear();
		menuTendina.setValue(null);

	}

	@FXML // This method is called by the FXMLLoader when initialization is complete
	void initialize() {
		assert menuTendina != null : "fx:id=\"menuTendina\" was not injected: check your FXML file 'Scene.fxml'.";
		assert btbCercaIscrittiCorso != null : "fx:id=\"btbCercaIscrittiCorso\" was not injected: check your FXML file 'Scene.fxml'.";
		assert txtMatricola != null : "fx:id=\"txtMatricola\" was not injected: check your FXML file 'Scene.fxml'.";
		assert checkBox != null : "fx:id=\"checkBox\" was not injected: check your FXML file 'Scene.fxml'.";
		assert txtNome != null : "fx:id=\"txtNome\" was not injected: check your FXML file 'Scene.fxml'.";
		assert txtCognome != null : "fx:id=\"txtCognome\" was not injected: check your FXML file 'Scene.fxml'.";
		assert btnCercaCorsi != null : "fx:id=\"btnCercaCorsi\" was not injected: check your FXML file 'Scene.fxml'.";
		assert btnIscrivi != null : "fx:id=\"btnIscrivi\" was not injected: check your FXML file 'Scene.fxml'.";
		assert txtArea != null : "fx:id=\"txtArea\" was not injected: check your FXML file 'Scene.fxml'.";
		assert btnReset != null : "fx:id=\"btnReset\" was not injected: check your FXML file 'Scene.fxml'.";

	}
}
