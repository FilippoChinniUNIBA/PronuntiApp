package it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.esercizio;

import java.io.File;
import java.util.Map;

import it.uniba.dib.sms2324.num15.PronuntiApp.models.database.costantidatabase.CostantiDBTemplateEsercizioDenominazioneImmagine;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.Persistente;

public class TemplateEsercizioDenominazioneImmagine extends AbstractEsercizio implements Esercizio {
   private File immagineEsercizio;

    public TemplateEsercizioDenominazioneImmagine(int ricompensaCorretto, int ricompensaErrato, File immagineEsercizio) {
        super(ricompensaCorretto, ricompensaErrato);
        this.immagineEsercizio = immagineEsercizio;
    }

    public TemplateEsercizioDenominazioneImmagine(String idEsercizio, int ricompensaCorretto, int ricompensaErrato, File immagineEsercizio) {
        super(idEsercizio, ricompensaCorretto, ricompensaErrato);
        this.immagineEsercizio = immagineEsercizio;
    }

    public TemplateEsercizioDenominazioneImmagine(Map<String,Object> fromDatabaseMap){
        super(fromDatabaseMap);
        TemplateEsercizioDenominazioneImmagine T = (TemplateEsercizioDenominazioneImmagine) fromMap(fromDatabaseMap);
        this.immagineEsercizio = T.immagineEsercizio;
    }

    public File getImmagineEsercizio() {
        return immagineEsercizio;
    }

    public void setImmagineEsercizio(File immagineEsercizio) {
        this.immagineEsercizio = immagineEsercizio;
    }

    @Override
    public Map<String, Object> toMap() {
        Map<String, Object> entityMap = super.toMap();
        //entityMap.put(CostantiDBTemplateEsercizioDenominazioneImmagine.ID_TEMPLATE_ESERCIZIO, this.idEsercizio);
        entityMap.put(CostantiDBTemplateEsercizioDenominazioneImmagine.IMMAGINE_ESERCIZIO, this.immagineEsercizio.getPath());
        return entityMap;
    }

    @Override
    public Esercizio fromMap(Map<String, Object> fromDatabaseMap) {
        return null;
    }

    @Override
    public String toString() {
        return "TemplateEsercizioDenominazioneImmagine{" +
                "idEsercizio='" + idEsercizio + '\'' +
                ", ricompensaCorretto=" + ricompensaCorretto +
                ", ricompensaErrato=" + ricompensaErrato +
                ", immagineEsercizio=" + immagineEsercizio +
                '}';
    }

}
