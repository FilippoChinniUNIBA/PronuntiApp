package it.uniba.dib.sms2324.num15.PronuntiApp.testingTODELETE;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import it.uniba.dib.sms2324.num15.PronuntiApp.R;
import it.uniba.dib.sms2324.num15.PronuntiApp.views.fragment.user_paziente.giochi.esercizi.EsercizioDenominazioneImmagineFragment;
import it.uniba.dib.sms2324.num15.PronuntiApp.views.fragment.autenticazione.LoginFragment;

public class TestMenuTestFragment extends Fragment {
	private FragmentManager mFragmentManager;

	private Button buttonToTestApi;
	private Button buttonToTestLogin;
	private Button buttonToTestDB;
	private Button buttonFragmentFilePicker;
	private Button buttonFragmentInserimentoDatiDB;
	private Button buttonFragmentNuovoTest1;
	private Button buttonFragmentNuovoTest2;

	public TestMenuTestFragment() {}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.test_fragment_menu_test, container, false);

		this.mFragmentManager = getActivity().getSupportFragmentManager();

		this.buttonToTestApi = view.findViewById(R.id.buttonTestApi);
		this.buttonToTestLogin = view.findViewById(R.id.buttonTestLoginRegistrazione);
		this.buttonToTestDB = view.findViewById(R.id.buttonToTestDB);
		this.buttonFragmentFilePicker = view.findViewById(R.id.idfilepickerfragment);
		this.buttonFragmentInserimentoDatiDB = view.findViewById(R.id.buttonFragmentInserimentoDatiDB);
		this.buttonFragmentNuovoTest1 = view.findViewById(R.id.buttonFragmenttestNuovotest1);
		this.buttonFragmentNuovoTest2 = view.findViewById(R.id.buttonFragmenttestNuovotest2);

		return view;

	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		this.buttonToTestApi.setOnClickListener(v -> {
			replaceFragment(mFragmentManager, R.id.TEST_frameLayoutActivityTest, new EsercizioDenominazioneImmagineFragment());
		});

		this.buttonToTestLogin.setOnClickListener(v -> {
			replaceFragment(mFragmentManager, R.id.TEST_frameLayoutActivityTest, new LoginFragment());
		});

		this.buttonToTestDB.setOnClickListener(v -> {
			replaceFragment(mFragmentManager, R.id.TEST_frameLayoutActivityTest, new TestDBFragment());
		});

		this.buttonFragmentFilePicker.setOnClickListener(v -> {
			replaceFragment(mFragmentManager, R.id.TEST_frameLayoutActivityTest, new TestFilePickerFragment());
		});

		this.buttonFragmentInserimentoDatiDB.setOnClickListener(v -> {
			replaceFragment(mFragmentManager, R.id.TEST_frameLayoutActivityTest, new TestInserimentoDatiDBFragment());
		});

		this.buttonFragmentNuovoTest1.setOnClickListener(v -> {
			replaceFragment(mFragmentManager, R.id.TEST_frameLayoutActivityTest, new TestNuovoTest1Fragment());
		});

		this.buttonFragmentNuovoTest2.setOnClickListener(v -> {
			replaceFragment(mFragmentManager, R.id.TEST_frameLayoutActivityTest, new TestNuovoTest2Fragment());
		});

	}

	private static void replaceFragment(FragmentManager fragmentManager, int containerId, Fragment fragment) {
		FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
		fragmentTransaction.replace(containerId, fragment);
		fragmentTransaction.addToBackStack(null);
		fragmentTransaction.commit();
	}

}

