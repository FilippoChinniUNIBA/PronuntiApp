package it.uniba.dib.sms2324.num15.PronuntiApp.views.fragment.profilo;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import it.uniba.dib.sms2324.num15.PronuntiApp.R;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.autenticazione.Autenticazione;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.autenticazione.AuthSharedPreferences;
import it.uniba.dib.sms2324.num15.PronuntiApp.views.activity.AutenticazioneActivity;
import it.uniba.dib.sms2324.num15.PronuntiApp.views.dialog.RichiestaConfermaDialog;

public class LogoutFragment extends Fragment {
	private View bottoneLogout;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_logout, container, false);

		bottoneLogout = view.findViewById(R.id.buttonLogout);

		return view;
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		bottoneLogout.setOnClickListener(v -> richiestaLogout());

	}

	private void richiestaLogout() {
		RichiestaConfermaDialog dialog = new RichiestaConfermaDialog(getContext(),getString(R.string.logoutTitle), getString(R.string.logoutRichiesta));
		dialog.setOnConfermaButtonClickListener(this::eseguiLogout);
		dialog.setOnAnnullaButtonClickListener(() -> {});
		dialog.show();
	}

	private void eseguiLogout() {
		Autenticazione auth = new Autenticazione();
		AuthSharedPreferences authSharedPreferences = new AuthSharedPreferences(getContext());

		auth.logout();
		authSharedPreferences.clearCredenziali();

		Intent intent = new Intent(getActivity(), AutenticazioneActivity.class);
		startActivity(intent);
	}

}
