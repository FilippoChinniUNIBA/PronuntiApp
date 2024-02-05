package it.uniba.dib.sms2324.num15.PronuntiApp.testingTODELETE;

import android.os.Bundle;

import it.uniba.dib.sms2324.num15.PronuntiApp.R;
import it.uniba.dib.sms2324.num15.PronuntiApp.views.activity.AbstractAppActivity;

public class TEST_Activity extends AbstractAppActivity {

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_logopedista);
		setFirstFragment(R.id.frameLayoutLogopedista, new TestMenuTestFragment());

	}
}
