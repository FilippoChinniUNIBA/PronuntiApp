package it.uniba.dib.sms2324.num15.PronuntiApp.views.fragment.user_logopedista;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.textfield.TextInputEditText;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.concurrent.CompletableFuture;

import it.uniba.dib.sms2324.num15.PronuntiApp.R;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.profilo.Appuntamento;
import it.uniba.dib.sms2324.num15.PronuntiApp.viewmodels.logopedista_viewmodel.CreazioneAppuntamentoController;
import it.uniba.dib.sms2324.num15.PronuntiApp.viewmodels.logopedista_viewmodel.LogopedistaViewModel;

public class AppuntamentiLogopedistaFragment extends Fragment {
    private TextInputEditText editTextGenitore;
    private TextInputEditText editTextLuogo;
    private TextInputEditText editTextDataAppuntemento;
    private LogopedistaViewModel logopedistaViewModel;
    private CreazioneAppuntamentoController mController;
    private Button addAppuntamentoButton;
    private TextView orarioTextView;


    public AppuntamentiLogopedistaFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_appuntamenti_logopedista, container, false);

        editTextGenitore = view.findViewById(R.id.textInputEditTextPazienteAppuntamentoLogopedista);
        editTextDataAppuntemento = view.findViewById(R.id.textInputEditTextDataAppuntamentoLogopedista);
        editTextLuogo = view.findViewById(R.id.textInputEditTextLuogoAppuntamentoLogopedista);

        //addAppuntamentoButton=view.findViewById();

        logopedistaViewModel = new ViewModelProvider(requireActivity()).get(LogopedistaViewModel.class);
        mController = logopedistaViewModel.getCreazioneAppuntamentoController();

        return view;
    }

   /* @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        addAppuntamentoButton.setOnClickListener(v -> {
            Logopedista mLogopedista = logopedistaViewModel.getLogopedista();
            String idLogopedista = mLogopedista.getIdProfilo();
            eseguiAggiuntaPrenotazione(idLogopedista);

        });
    }*/

    private CompletableFuture<Appuntamento> eseguiAggiuntaPrenotazione(String idLogopedista) {

        String idGenitore = editTextGenitore.getText().toString();
        String luogoAppuntamento = editTextLuogo.getText().toString();
        LocalDate dataAppuntamento = LocalDate.parse(editTextDataAppuntemento.getText().toString());
        LocalTime orarioAppuntamento = null;

        CompletableFuture<Appuntamento> futureAppuntamento = new CompletableFuture<>();
        Appuntamento appuntamento = mController.creazioneAppuntamento(idLogopedista,idGenitore,dataAppuntamento,orarioAppuntamento,luogoAppuntamento);
        Log.d("AppuntamentiLogopedistaFragment.eseguiAggiuntaPrenotazione()", appuntamento.toString());
        futureAppuntamento.complete(appuntamento);
        return futureAppuntamento;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (getActivity() != null) {
            getActivity().setTitle("Apppuntamenti");
        }
    }

}
