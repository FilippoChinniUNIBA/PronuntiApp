package it.uniba.dib.sms2324.num15.PronuntiApp.testingTODELETE;

import android.os.Bundle;

import it.uniba.dib.sms2324.num15.PronuntiApp.R;
import it.uniba.dib.sms2324.num15.PronuntiApp.views.activity.AbstractAppActivity;
import it.uniba.dib.sms2324.num15.PronuntiApp.views.fragment.ScenariGenitoriFragment;
import it.uniba.dib.sms2324.num15.PronuntiApp.views.fragment.signInUp.AvvioRapidoFragment;
import it.uniba.dib.sms2324.num15.PronuntiApp.views.navigationSelector.navBarApp.NavigationNavBarSelectorGenitore;

public class TEST_Activity extends AbstractAppActivity {

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setBottomNavBar(R.menu.bottom_navbar_genitore, new NavigationNavBarSelectorGenitore(getSupportFragmentManager(), R.id.appFrameLayout, bottomNavigationView));
		setFirstFragment(R.id.appFrameLayout, new TestMenuTestFragment());

	}
}
