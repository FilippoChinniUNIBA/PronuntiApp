package it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.esercizio;

import java.io.File;
import java.util.Map;

import it.uniba.dib.sms2324.num15.PronuntiApp.models.database.costantidatabase.CostantiDBTemplateEsercizioDenominazioneImmagine;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.database.costantidatabase.CostantiDBTemplateEsercizioSequenzaParole;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.Persistente;

public class TemplateEsercizioSequenzaParole extends AbstractEsercizio implements Esercizio {
    private File audioEsercizio;
    private String parola1;
    private String parola2;
    private String parola3;

    public TemplateEsercizioSequenzaParole(int ricompensaCorretto, int ricompensaErrato, File audioEsercizio, String parola1, String parola2, String parola3) {
        super(ricompensaCorretto, ricompensaErrato);
        this.audioEsercizio = audioEsercizio;
        this.parola1 = parola1;
        this.parola2 = parola2;
        this.parola3 = parola3;
    }

    public TemplateEsercizioSequenzaParole(String idEsercizio, int ricompensaCorretto, int ricompensaErrato, File audioEsercizio, String parola1, String parola2, String parola3) {
        super(idEsercizio, ricompensaCorretto, ricompensaErrato);
        this.audioEsercizio = audioEsercizio;
        this.parola1 = parola1;
        this.parola2 = parola2;
        this.parola3 = parola3;
    }

    public TemplateEsercizioSequenzaParole(Map<String,Object> fromDatabaseMap){
        super(fromDatabaseMap);
        TemplateEsercizioSequenzaParole T = (TemplateEsercizioSequenzaParole) fromMap(fromDatabaseMap);
        this.audioEsercizio = T.audioEsercizio;
        this.parola1 = T.parola1;
        this.parola2 = T.parola2;
        this.parola3 = T.parola3;
    }

    public File getAudioEsercizio() {
        return audioEsercizio;
    }

    public String getParola1() {
        return parola1;
    }

    public String getParola2() {
        return parola2;
    }

    public String getParola3() {
        return parola3;
    }

    public void setAudioEsercizio(File audioEsercizio) {
        this.audioEsercizio = audioEsercizio;
    }

    public void setParola1(String parola1) {
        this.parola1 = parola1;
    }

    public void setParola2(String parola2) {
        this.parola2 = parola2;
    }

    public void setParola3(String parola3) {
        this.parola3 = parola3;
    }

    @Override
    public Map<String, Object> toMap() {
        Map<String, Object> entityMap = super.toMap();
        //entityMap.put(CostantiDBTemplateEsercizioSequenzaParole.ID_TEMPLATE_ESERCIZIO, this.idEsercizio);
        entityMap.put(CostantiDBTemplateEsercizioSequenzaParole.AUDIO_ESERCIZIO, this.audioEsercizio.getPath());
        entityMap.put(CostantiDBTemplateEsercizioSequenzaParole.PAROLA_1, this.parola1);
        entityMap.put(CostantiDBTemplateEsercizioSequenzaParole.PAROLA_2, this.parola2);
        entityMap.put(CostantiDBTemplateEsercizioSequenzaParole.PAROLA_3, this.parola3);
        return entityMap;
    }

    @Override
    public Esercizio fromMap(Map<String, Object> fromDatabaseMap) {
        return null;
    }

    @Override
    public String toString() {
        return "TemplateEsercizioSequenzaParole{" +
                "idEsercizio='" + idEsercizio + '\'' +
                ", ricompensaCorretto=" + ricompensaCorretto +
                ", ricompensaErrato=" + ricompensaErrato +
                ", audioEsercizio=" + audioEsercizio +
                ", parola1='" + parola1 + '\'' +
                ", parola2='" + parola2 + '\'' +
                ", parola3='" + parola3 + '\'' +
                '}';
    }

}
