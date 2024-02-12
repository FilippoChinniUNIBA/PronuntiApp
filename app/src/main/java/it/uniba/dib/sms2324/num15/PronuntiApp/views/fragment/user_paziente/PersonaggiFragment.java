package it.uniba.dib.sms2324.num15.PronuntiApp.views.fragment.user_paziente;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import it.uniba.dib.sms2324.num15.PronuntiApp.viewmodels.paziente_viewmodels.PazienteViewModel;
import it.uniba.dib.sms2324.num15.PronuntiApp.R;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.profilo.personaggio.Personaggio;
import it.uniba.dib.sms2324.num15.PronuntiApp.views.fragment.AbstractFragmentWithNavigation;

public class PersonaggiFragment extends AbstractFragmentWithNavigation {
    private RecyclerView recyclerViewPersonaggiSbloccati;
    private RecyclerView recyclerViewPersonaggiAcquistabili;
    private TextView textViewPersonaggiSbloccati;
    private TextView textViewPersonaggiAcquistabili;
    private TextView textViewMonete;
    private PazienteViewModel mPazienteViewModel;

    List<String> urlsPersonaggiSbloccati;
    List<String> urlsPersonaggiAcquistabili;
    List<String> nomiPersonaggiSbloccati;
    List<String> nomiPersonaggiAcquistabili;

    List<String> idsPersonaggiAcquistabili;
    List<String> idsPersonaggiSbloccati;

    public PersonaggiFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view= inflater.inflate(R.layout.fragment_personaggi, container, false);

        /*
        TypedArray typedArray = getResources().obtainTypedArray(R.array.personaggi_images);

        // Converti l'array in una lista di Integer
        ArrayList<Integer> imageList = new ArrayList<>();
        for (int i = 0; i < typedArray.length(); i++) {
            imageList.add(typedArray.getResourceId(i, 0));
        }

        typedArray.recycle();

        // Ottieni la lista di nomi dei personaggi
        String[] characterNamesArray = getResources().getStringArray(R.array.character_names);
        ArrayList<String> characterNames = new ArrayList<>(Arrays.asList(characterNamesArray));

        // Ottieni la lista dei costi dei personaggi
        String[] characterCostsArray = getResources().getStringArray(R.array.character_costs);
        ArrayList<String> characterCosts = new ArrayList<>(Arrays.asList(characterCostsArray));
        */


        //TODO settare monete in onViewCreated
        textViewMonete = view.findViewById(R.id.coinsTextView);
        textViewPersonaggiSbloccati = view.findViewById(R.id.textViewPersonaggiSbloccati);
        textViewPersonaggiAcquistabili = view.findViewById(R.id.textViewPersonaggiAcquistabili);

        recyclerViewPersonaggiSbloccati = view.findViewById(R.id.recyclerViewPersonaggiSbloccati);
        recyclerViewPersonaggiAcquistabili = view.findViewById(R.id.recyclerViewPersonaggiAcquistabili);

        mPazienteViewModel = new ViewModelProvider(requireActivity()).get(PazienteViewModel.class);
        urlsPersonaggiSbloccati = new ArrayList<>();
        urlsPersonaggiAcquistabili = new ArrayList<>();
        nomiPersonaggiAcquistabili = new ArrayList<>();
        nomiPersonaggiSbloccati = new ArrayList<>();
        idsPersonaggiAcquistabili= new ArrayList<>();
        idsPersonaggiSbloccati = new ArrayList<>();

        GridLayoutManager layoutManagerSbloccati = new GridLayoutManager(getContext(), 3);
        GridLayoutManager layoutManagerAcquistabili = new GridLayoutManager(getContext(), 3);
        recyclerViewPersonaggiSbloccati.setLayoutManager(layoutManagerSbloccati);
        recyclerViewPersonaggiAcquistabili.setLayoutManager(layoutManagerAcquistabili);

        return view;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Map<String, Integer> personaggiPaziente = mPazienteViewModel.getPazienteLiveData().getValue().getPersonaggiSbloccati();
        //List<Personaggio> personaggi = mPazienteViewModel.getListaPersonaggiLiveData().getValue();

        List<Personaggio> personaggi = new ArrayList<>();
        Personaggio personaggio0 = new Personaggio("-NqIFkOcaDJKFU_BhuH2","Batman",500,"https://firebasestorage.googleapis.com/v0/b/pronuntiapp-32bf6.appspot.com/o/texture_personaggi%2FBatman.png?alt=media&token=b892a5f7-8cd5-4081-976e-34925b20740f");
        Personaggio personaggio1 = new Personaggio("-NqIFkOqJ7eznb2BIubm","Cane",500,"https://firebasestorage.googleapis.com/v0/b/pronuntiapp-32bf6.appspot.com/o/texture_personaggi%2FCane.png?alt=media&token=ca263bc9-634d-4b34-a498-4c87f5a791e2");
        Personaggio personaggio2 = new Personaggio("-NqIFkOuix-wiNRaxtlo","Capitan America",500,"https://firebasestorage.googleapis.com/v0/b/pronuntiapp-32bf6.appspot.com/o/texture_personaggi%2FCapitan%20America.png?alt=media&token=0c9d2613-9d93-4fb0-a8a7-40d5e770c143");
        Personaggio personaggio3 = new Personaggio("-NqIFkP0yNyXQFp5VveX","Cavaliere",500,"https://firebasestorage.googleapis.com/v0/b/pronuntiapp-32bf6.appspot.com/o/texture_personaggi%2FCavaliera.png?alt=media&token=83bdcc54-4730-4c83-ac13-6551faf3b836");
        Personaggio personaggio4 = new Personaggio("-NqIFkP7u9RENb6CfQlG","Cavaliera",500,"https://firebasestorage.googleapis.com/v0/b/pronuntiapp-32bf6.appspot.com/o/texture_personaggi%2FCavaliere.png?alt=media&token=0f5d97fb-1a3c-41ed-ba63-93f52b41f202");
        Personaggio personaggio5 = new Personaggio("-NqIFkPCmPM-2sOY6O0e","Coniglio",500,"https://firebasestorage.googleapis.com/v0/b/pronuntiapp-32bf6.appspot.com/o/texture_personaggi%2FConiglio.png?alt=media&token=de33b79e-fe74-4191-bfdf-bb5d15550ff1");
        Personaggio personaggio6 = new Personaggio("-NqIFkPIJoJlQZ1iwgfj","Droghetta",500,"https://firebasestorage.googleapis.com/v0/b/pronuntiapp-32bf6.appspot.com/o/texture_personaggi%2FDraghetta.png?alt=media&token=500b4653-fb12-4a59-9a50-a5baee091f9d");
        Personaggio personaggio7 = new Personaggio("-NqIFkPTTupPgYkYc74E","Drago",500,"https://firebasestorage.googleapis.com/v0/b/pronuntiapp-32bf6.appspot.com/o/texture_personaggi%2FDrago.png?alt=media&token=cd56a567-567f-4945-b8de-7a182168a7a0");
        Personaggio personaggio8 = new Personaggio("-NqIFkPYD_JMBxlV-dIx","Elefente",500,"https://firebasestorage.googleapis.com/v0/b/pronuntiapp-32bf6.appspot.com/o/texture_personaggi%2FElefante.png?alt=media&token=8fc69ac1-97f2-460c-9d57-ef8ad9f4c571");
        Personaggio personaggio9 = new Personaggio("-NqIFkPchAFVCOm0Uffi","Gattina",500,"https://firebasestorage.googleapis.com/v0/b/pronuntiapp-32bf6.appspot.com/o/texture_personaggi%2FGattina.png?alt=media&token=c61ff666-8ef6-4f8f-91ec-cba90fac9da8");
        Personaggio personaggio10 = new Personaggio("-NqIFkPgCrXO8lqN56jo","Gatto",500,"https://firebasestorage.googleapis.com/v0/b/pronuntiapp-32bf6.appspot.com/o/texture_personaggi%2FGatto.png?alt=media&token=f43b4cc9-7885-43e3-bd98-8378a6cffd6c");
        Personaggio personaggio11 = new Personaggio("-NqIFkPlPjvZDY2TskvO","Mucca",500,"https://firebasestorage.googleapis.com/v0/b/pronuntiapp-32bf6.appspot.com/o/texture_personaggi%2FMucca.png?alt=media&token=7d6f6304-fa94-472f-bda4-c295fc50fdcb");
        Personaggio personaggio12 = new Personaggio("-NqIFkPpkXllw9SXtFY2","Pecora",500,"https://firebasestorage.googleapis.com/v0/b/pronuntiapp-32bf6.appspot.com/o/texture_personaggi%2FPecora.png?alt=media&token=a479a636-f152-4406-a605-f221bfb5decc");
        Personaggio personaggio13 = new Personaggio("-NqIFkPtcD7NHKpKOL0X","Spiderman",500,"https://firebasestorage.googleapis.com/v0/b/pronuntiapp-32bf6.appspot.com/o/texture_personaggi%2FSpiderman.png?alt=media&token=1e93debf-a5d0-49ad-a918-05b8b3aeca96");
        Personaggio personaggio14 = new Personaggio("-NqIFkPxWAuHV9o40Idc","WonderWoman",500,"https://firebasestorage.googleapis.com/v0/b/pronuntiapp-32bf6.appspot.com/o/texture_personaggi%2FWonderwoman.png?alt=media&token=46260ed5-0447-445f-bf95-9a9342ed87ab");
        personaggi.add(personaggio0);
        personaggi.add(personaggio1);
        personaggi.add(personaggio2);
        personaggi.add(personaggio3);
        personaggi.add(personaggio4);
        personaggi.add(personaggio5);
        personaggi.add(personaggio6);
        personaggi.add(personaggio7);
        personaggi.add(personaggio8);
        personaggi.add(personaggio9);
        personaggi.add(personaggio10);
        personaggi.add(personaggio11);
        personaggi.add(personaggio12);
        personaggi.add(personaggio13);
        personaggi.add(personaggio14);

        setIdLists(personaggiPaziente);
        setAttributes(personaggi);

        PersonaggiAcquistabiliAdapter personaggiAcquistabiliAdapter = new PersonaggiAcquistabiliAdapter(getContext(),urlsPersonaggiAcquistabili,nomiPersonaggiAcquistabili);
        PersonaggiSbloccatiAdapter personaggiSbloccatiAdapter = new PersonaggiSbloccatiAdapter(getContext(),urlsPersonaggiSbloccati,nomiPersonaggiSbloccati);
        recyclerViewPersonaggiAcquistabili.setAdapter(personaggiAcquistabiliAdapter);
        recyclerViewPersonaggiSbloccati.setAdapter(personaggiSbloccatiAdapter);
    }
    private void setAttributes(List<Personaggio> personaggi){
        for (Personaggio personaggio : personaggi) {
            if (idsPersonaggiSbloccati.contains(personaggio.getIdPersonaggio())) {
                urlsPersonaggiSbloccati.add(personaggio.getTexturePersonaggio());
                nomiPersonaggiSbloccati.add(personaggio.getNomePersonaggio());
            } else {
                urlsPersonaggiAcquistabili.add(personaggio.getTexturePersonaggio());
                nomiPersonaggiAcquistabili.add(personaggio.getNomePersonaggio());
            }
        }
    }
    public void setIdLists(Map<String, Integer> map) {
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            String key = entry.getKey();
            int value = Integer.parseInt(String.valueOf(entry.getValue()));
            if (value == 1 || value == 2) {
                idsPersonaggiSbloccati.add(key);
            } else{
                idsPersonaggiAcquistabili.add(key);
            }
        }
    }
}