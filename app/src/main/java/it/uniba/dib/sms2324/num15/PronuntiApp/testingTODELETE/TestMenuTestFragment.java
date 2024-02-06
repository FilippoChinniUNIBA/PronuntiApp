package it.uniba.dib.sms2324.num15.PronuntiApp.testingTODELETE;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import it.uniba.dib.sms2324.num15.PronuntiApp.R;
import it.uniba.dib.sms2324.num15.PronuntiApp.views.fragment.esercizi.EsercizioDenominazioneImmagineFragment;
import it.uniba.dib.sms2324.num15.PronuntiApp.views.fragment.signInUp.LoginFragment;

public class TestMenuTestFragment extends Fragment {

	private Button buttonToTestApi;
	private Button buttonToTestLogin;

	private Button buttonToTestDB;

	private Button buttonFragmentFilePicker;
	private Button buttonFragmentInserimentoDatiDB;

	public TestMenuTestFragment() {}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.test_fragment_menu_test, container, false);

		buttonToTestApi = view.findViewById(R.id.buttonTestApi);
		buttonToTestLogin = view.findViewById(R.id.buttonTestLoginRegistrazione);
		buttonToTestDB = view.findViewById(R.id.buttonToTestDB);
		buttonFragmentFilePicker = view.findViewById(R.id.idfilepickerfragment);
		buttonFragmentInserimentoDatiDB = view.findViewById(R.id.buttonFragmentInserimentoDatiDB);

		this.buttonToTestApi.setOnClickListener(v -> {
			EsercizioDenominazioneImmagineFragment testApiFragment = new EsercizioDenominazioneImmagineFragment();
			FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
			fragmentManager.beginTransaction()
					.replace(R.id.frameLayoutLogopedista, testApiFragment)
					.addToBackStack(null)
					.commit();
			}
		);

		this.buttonToTestLogin.setOnClickListener(v -> {
			LoginFragment testLoginFragment = new LoginFragment();
			FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
			fragmentManager.beginTransaction()
					.replace(R.id.frameLayoutLogopedista, testLoginFragment)
					.addToBackStack(null)
					.commit();
			}
		);


		this.buttonToTestDB.setOnClickListener(v -> {
			TestDBFragment testDBFragment = new TestDBFragment();
			FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
			fragmentManager.beginTransaction()
					.replace(R.id.frameLayoutLogopedista, testDBFragment)
					.addToBackStack(null)
					.commit();
			}
		);

		this.buttonFragmentFilePicker.setOnClickListener(v -> {
					TestFilePickerFragment testFilePickerFragment = new TestFilePickerFragment();
					FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
					fragmentManager.beginTransaction()
							.replace(R.id.frameLayoutLogopedista, testFilePickerFragment)
							.addToBackStack(null)
							.commit();
					}
		);

		this.buttonFragmentInserimentoDatiDB.setOnClickListener(v -> {
					TestInserimentoDatiDBFragment testInserimentoDatiDBFragment = new TestInserimentoDatiDBFragment();
					FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
					fragmentManager.beginTransaction()
							.replace(R.id.frameLayoutLogopedista, testInserimentoDatiDBFragment)
							.addToBackStack(null)
							.commit();
					}
		);

		return view;

	}


}

