package it.uniba.dib.sms2324.num15.PronuntiApp.viewmodels.logopedista_viewmodel;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import java.time.LocalDate;

import it.uniba.dib.sms2324.num15.PronuntiApp.models.database.profilo.PazienteDAO;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.profilo.Genitore;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.profilo.Paziente;

public class RegistrazionePazienteEGenitoreViewModel extends ViewModel {
    Paziente paziente;
    Genitore genitore;
    public Paziente getPaziente() {
        return paziente;
    }
    public Genitore getGenitore(){
        return genitore;
    }
    public void setGenitore(Genitore genitore) {
        this.genitore = genitore;
    }
    public void setPaziente(Paziente paziente) {
        this.paziente = paziente;
    }

    public void registrazionePaziente(String nomepaziente, String cognomepaziente, String usernamepaziente, String emailpaziente, String passwordpaziente, int etapaziente, LocalDate datanascitapaziente, char sessopaziente){
        Paziente paziente = new Paziente(nomepaziente,cognomepaziente,usernamepaziente,emailpaziente,passwordpaziente,etapaziente,datanascitapaziente,sessopaziente,0,0,null);
        /*PazienteDAO pazienteDAO = new PazienteDAO();
        pazienteDAO.save(paziente);*/
        Log.d("RegistrazionePazienteEGenitoreViewModel.registrazionePaziente()",paziente.toString());
    }

    public void registrazioneGenitore(String nomegenitore, String cognomegenitore, String usernamegenitore, String emailgenitore, String passwordgenitore, String telefonogenitore, String indirizzogenitore){
        Genitore genitore = new Genitore(nomegenitore,cognomegenitore,usernamegenitore,emailgenitore,passwordgenitore,telefonogenitore,indirizzogenitore);
        Log.d("RegistrazionePazienteEGenitoreViewModel.registrazioneGenitore()",genitore.toString());
    }

}
