package it.uniba.dib.sms2324.num15.PronuntiApp.views.fragment;

import android.content.res.TypedArray;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import it.uniba.dib.sms2324.num15.PronuntiApp.R;
import it.uniba.dib.sms2324.num15.PronuntiApp.views.PersonaggioImageAdapter;

public class PersonaggiFragment extends Fragment {
    public PersonaggiFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view= inflater.inflate(R.layout.fragment_personaggi, container, false);

        TypedArray typedArray = getResources().obtainTypedArray(R.array.personaggi_images);

        // Converti l'array in una lista di Integer
        List<Integer> imageList = new ArrayList<>();
        for (int i = 0; i < typedArray.length(); i++) {
            imageList.add(typedArray.getResourceId(i, 0));
        }

        typedArray.recycle();

        // Ottieni la lista di nomi dei personaggi
        String[] characterNamesArray = getResources().getStringArray(R.array.character_names);
        List<String> characterNames = Arrays.asList(characterNamesArray);

        // Ottieni la lista dei costi dei personaggi
        String[] characterCostsArray = getResources().getStringArray(R.array.character_costs);
        List<String> characterCosts = Arrays.asList(characterCostsArray);

        // Trova la RecyclerView nel tuo layout del fragment
        RecyclerView recyclerView = view.findViewById(R.id.recyclerViewPersonaggi);

        // Crea un'istanza del tuo adattatore personalizzato
        PersonaggioImageAdapter adapter = new PersonaggioImageAdapter(requireContext(), imageList, characterNames, characterCosts );

        // Imposta il layout manager e l'adattatore per la RecyclerView
        recyclerView.setLayoutManager(new GridLayoutManager(requireContext(), 3)); // 3 colonne, modifica in base alle tue esigenze
        recyclerView.setAdapter(adapter);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (getActivity() != null) {
            getActivity().setTitle("Personaggi");
        }
    }
}