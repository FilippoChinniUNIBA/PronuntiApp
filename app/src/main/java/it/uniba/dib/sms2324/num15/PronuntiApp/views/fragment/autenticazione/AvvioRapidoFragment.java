package it.uniba.dib.sms2324.num15.PronuntiApp.views.fragment.autenticazione;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.lifecycle.ViewModelProvider;

import java.util.concurrent.CompletableFuture;

import it.uniba.dib.sms2324.num15.PronuntiApp.R;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.autenticazione.AuthSharedPreferences;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.profilo.Profilo;
import it.uniba.dib.sms2324.num15.PronuntiApp.testingTODELETE.TEST_Activity;
import it.uniba.dib.sms2324.num15.PronuntiApp.viewmodels.autenticazione_viewmodel.LoginViewModel;
import it.uniba.dib.sms2324.num15.PronuntiApp.views.activity.AbstractAppActivity;
import it.uniba.dib.sms2324.num15.PronuntiApp.views.activity.EntryActivity;
import it.uniba.dib.sms2324.num15.PronuntiApp.views.activity.FragmentCaricamento;
import it.uniba.dib.sms2324.num15.PronuntiApp.views.dialog.InfoDialog;
import it.uniba.dib.sms2324.num15.PronuntiApp.views.fragment.AbstractFragmentWithNavigation;

public class AvvioRapidoFragment extends AbstractFragmentWithNavigation {
    private static final String EMAIL_LOGOPEDISTA_PREDEFINITO = "predefinito@logopedista.it";
    private static final String PASSWORD_LOGOPEDISTA_PREDEFINITO = "123456";
    private static final String EMAIL_GENITORE_PREDEFINITO = "predefinito@genitore.it";
    private static final String PASSWORD_GENITORE_PREDEFINITO = "123456";
    private static final String EMAIL_PAZIENTE_PREDEFINITO = "predefinito@paziente.it";
    private static final String PASSWORD_PAZIENTE_PREDEFINITO = "123456";


    private FrameLayout layoutAvvioRapido;

    private LoginViewModel mLoginViewModel;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_avvio_rapido, container, false);

        this.mLoginViewModel = new ViewModelProvider(requireActivity()).get(LoginViewModel.class);

        this.layoutAvvioRapido = view.findViewById(R.id.layoutAvvioRapido);

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.findViewById(R.id.button_logopedista).setOnClickListener(v -> {
            loginActivityProfilo(EMAIL_LOGOPEDISTA_PREDEFINITO, PASSWORD_LOGOPEDISTA_PREDEFINITO);
        });

        view.findViewById(R.id.button_genitore).setOnClickListener(v -> {
            loginActivityProfilo(EMAIL_GENITORE_PREDEFINITO, PASSWORD_GENITORE_PREDEFINITO);
        });

        view.findViewById(R.id.button_paziente).setOnClickListener(v -> {
            loginActivityProfilo(EMAIL_PAZIENTE_PREDEFINITO, PASSWORD_PAZIENTE_PREDEFINITO);
        });

        view.findViewById(R.id.testPortaleTestButton).setOnClickListener(v -> startActivity(new Intent(getActivity(), TEST_Activity.class)));

    }

    private void loginActivityProfilo(String email, String password) {
        avviaSchermataCaricamento();

        CompletableFuture<Boolean> futureIsLoginCorrect = mLoginViewModel.verificaLogin(email, password);
        futureIsLoginCorrect.thenAccept(isLoginCorrect -> {
            if (!isLoginCorrect) {
                InfoDialog infoDialog = new InfoDialog(getContext(), getString(R.string.erroreLoginCredenziali), getString(R.string.tastoRiprova));
                infoDialog.show();
                infoDialog.setOnConfermaButtonClickListener(null);
            }
            else {
                AuthSharedPreferences authSharedPreferences = new AuthSharedPreferences(requireActivity());
                authSharedPreferences.salvaCredenziali(email, password);

                CompletableFuture<Profilo> futureProfilo = mLoginViewModel.login();
                futureProfilo.thenAccept(profilo -> {
                    Log.d("AvvioRapidoFragment.loginActivityProfilo()", "Profilo: " + profilo.toString());

                    getActivity().runOnUiThread(() -> ((AbstractAppActivity) getActivity()).navigaConProfilo(profilo, getActivity()));
                });
            }
        });
    }

    private void avviaSchermataCaricamento() {
        layoutAvvioRapido.removeAllViews();
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.layoutAvvioRapido, new FragmentCaricamento())
                .commit();
    }

}
