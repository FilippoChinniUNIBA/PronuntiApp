package it.uniba.dib.sms2324.num15.PronuntiApp.views.fragment.classifica;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import it.uniba.dib.sms2324.num15.PronuntiApp.R;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.database.profilo.personaggio.PersonaggioDAO;
import it.uniba.dib.sms2324.num15.PronuntiApp.viewmodels.paziente_viewmodels.ClassificaPazienteController;
import it.uniba.dib.sms2324.num15.PronuntiApp.viewmodels.paziente_viewmodels.PazienteViewModel;
import it.uniba.dib.sms2324.num15.PronuntiApp.views.fragment.AbstractFragmentWithNavigation;

public class ClassificaFragment extends AbstractFragmentWithNavigation {
    private RecyclerView recyclerViewClassifica;
    private PazienteClassificaAdapter pazienteClassificaAdapter;
    private List<PazienteClassifica> pazienti;
    private PazienteViewModel mPazienteViewModel;
    private ClassificaPazienteController mClassificaPazienteController;

    private final String url_img="https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.ilgiornale.it%2Fnews%2Fspettacoli%2Fjoker-grande-film-e-i-pericoli-dellempatia-1763658.html&psig=AOvVaw20KYZ33AVj0UJ1zQVFhpbS&ust=1707670729411000&source=images&cd=vfe&opi=89978449&ved=0CBIQjRxqFwoTCOCg1aufoYQDFQAAAAAdAAAAABAJ";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_classifica, container, false);
        recyclerViewClassifica = view.findViewById(R.id.recyclerViewClassifica);
        recyclerViewClassifica.setLayoutManager(new LinearLayoutManager(getContext()));

        this.mPazienteViewModel = new ViewModelProvider(requireActivity()).get(PazienteViewModel.class);
        //this.mClassificaPazienteController = mPazienteViewModel.getClassificaPazienteController();

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //TODO: costruire la classifica creando una lista di tipo PazienteClassifica (username, punteggio, url_img)
        // url_img si ottiene da personaggioDao ottenendo prima l'id del personaggio selezionato da Paziente paziente.getPersonaggiSbloccati() e
        // poi cercare la chiave con il valore 2 (che significa selezionato)
        PersonaggioDAO personaggioDAO = new PersonaggioDAO();

/*
        String idPaziente = mPazienteViewModel.getPazienteLiveData().getValue().getIdProfilo();
        List<PazienteClassifica> pazientiClassifica = null;
        mClassificaPazienteController.retrieveClassificaPazienti(idPaziente).thenAccept(logopedista -> {
            for (Paziente paziente : logopedista.getPazienti()) {
                    String idPersonaggio = paziente.getPersonaggiSbloccati().entrySet().stream()
                            .filter(entry -> entry.getValue().equals(2)).findFirst()
                            .map(Map.Entry::getKey);
                });
                personaggioDAO.get();
                pazientiClassifica.add(paziente.getUsername(),paziente.getPunteggioTot(),);
            }

        });*/





        pazienti= new ArrayList<>();
        for(int i=100; i>0; i--){
            pazienti.add(new PazienteClassifica("username_paziente"+i, i, url_img));
        }
        pazienti.sort((p1, p2) -> p2.getPunteggio() - p1.getPunteggio());

        //TODO: creare l'adapter passandor come secondo parametro l'username del paziente attuale
        pazienteClassificaAdapter = new PazienteClassificaAdapter(pazienti, pazienti.get(5).getUsername());
        recyclerViewClassifica.setAdapter(pazienteClassificaAdapter);
    }
}