package it.uniba.dib.sms2324.num15.PronuntiApp.views.fragment.user_paziente.giochi.scenari;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.ViewModelProvider;

import com.squareup.picasso.Picasso;

import it.uniba.dib.sms2324.num15.PronuntiApp.R;
import it.uniba.dib.sms2324.num15.PronuntiApp.viewmodels.paziente_viewmodels.PazienteViewModel;
import it.uniba.dib.sms2324.num15.PronuntiApp.views.fragment.AbstractFragmentWithNavigation;

public class ScenarioFragment extends AbstractFragmentWithNavigation {
	private float xDelta, yDelta;
	private float bottomHeight;
	private float topHeight;
	private ImageView personaggioImageView;
	private ImageView posizioneGioco1ImageView, posizioneGioco2ImageView, posizioneGioco3ImageView;
	private CurvedLineView curvedLineView1to2, curvedLineView2to3;
	private float personaggioX, personaggioY, personaggioWidth, personaggioHeight;
	private ConstraintLayout constraintLayout;
	private Vibrator vibrator;
	private boolean isVibrating = false;

	private PazienteViewModel mPazienteViewModel;


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_scenario, container, false);

		this.mPazienteViewModel = new ViewModelProvider(requireActivity()).get(PazienteViewModel.class);

		//TODO con questo logo crasha
		//Log.d("Scenario",mPazienteViewModel.getPazienteLiveData().getValue().toString());

		constraintLayout = view.findViewById(R.id.constraintLayoutScenario);

		personaggioImageView = view.findViewById(R.id.imageViewPersonaggio);

		posizioneGioco1ImageView = view.findViewById(R.id.posizione_primo_esercizio);
		posizioneGioco2ImageView = view.findViewById(R.id.posizione_secondo_esercizio);
		posizioneGioco3ImageView = view.findViewById(R.id.posizione_terzo_esercizio);

		constraintLayout.setBackground(getContext().getDrawable(R.drawable.background_space_scenario));
		posizioneGioco1ImageView.setImageResource(R.drawable.uranio);
		posizioneGioco2ImageView.setImageResource(R.drawable.giove);
		posizioneGioco3ImageView.setImageResource(R.drawable.earth);


		curvedLineView1to2 = view.findViewById(R.id.curvedLineView1to2);
		curvedLineView2to3 = view.findViewById(R.id.curvedLineView2to3);

		posizioneGioco3ImageView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
			@Override
			public void onGlobalLayout() {
				// Rimuovi il listener una volta che la vista è stata completamente inizializzata
				posizioneGioco3ImageView.getViewTreeObserver().removeOnGlobalLayoutListener(this);

				curvedLineView1to2.setStartPoint(posizioneGioco1ImageView.getX() + posizioneGioco1ImageView.getWidth() / 2, posizioneGioco1ImageView.getY() + posizioneGioco1ImageView.getHeight() / 2);
				curvedLineView1to2.setEndPoint(posizioneGioco2ImageView.getX() + posizioneGioco2ImageView.getWidth() / 2, posizioneGioco2ImageView.getY() + posizioneGioco2ImageView.getHeight() / 2);
				curvedLineView2to3.setStartPoint(posizioneGioco2ImageView.getX() + posizioneGioco2ImageView.getWidth() / 2, posizioneGioco2ImageView.getY() + posizioneGioco2ImageView.getHeight() / 2);
				curvedLineView2to3.setEndPoint(posizioneGioco3ImageView.getX() + posizioneGioco3ImageView.getWidth() / 2, posizioneGioco3ImageView.getY() + posizioneGioco3ImageView.getHeight() / 2);
			}
		});
		return view;
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		mPazienteViewModel.getTexturePersonaggioSelezionatoLiveData().observe(getViewLifecycleOwner(), texture -> {
			Picasso.get().load(texture).into(personaggioImageView);
		});

		vibrator = (Vibrator) getContext().getSystemService(Context.VIBRATOR_SERVICE);
		personaggioImageView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
			@Override
			public void onGlobalLayout() {
				// Rimuovi il listener una volta che la vista è stata completamente inizializzata
				personaggioImageView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
				int dp=125;
				topHeight= TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, getResources().getDisplayMetrics());
				bottomHeight = getResources().getDimension(R.dimen.nav_bar_height);
				bottomHeight += bottomHeight*0.2f;
				// Abilita il drag dell'immagine del personaggio
				enableImageDrag(personaggioImageView);
			}
		});
	}

	private void setPersonaggioPosition() {
		personaggioX = personaggioImageView.getX();
		personaggioY = personaggioImageView.getY();
		personaggioWidth = personaggioImageView.getWidth();
		personaggioHeight = personaggioImageView.getHeight();
	}

	private void highlightPosizione(ImageView imageView) {
		ingrandisciPosizione(imageView);
		vibrate();
		imageView.setBackground(getContext().getDrawable(R.drawable.esercizio_highlight_background));
	}

	private void ingrandisciPosizione(ImageView imageView){
		imageView.setScaleX(1.3f);
		imageView.setScaleY(1.3f);
	}

	private void ridimensionaPosizione(ImageView imageView){
		imageView.setScaleX(1f);
		imageView.setScaleY(1f);
	}

	private boolean isPersonaggioInAreaPrimoEsercizio() {
		setPersonaggioPosition();
		float areaPrimoEsercizioX = posizioneGioco1ImageView.getX();
		float areaPrimoEsercizioY = posizioneGioco1ImageView.getY();
		float areaPrimoEsercizioWidth = posizioneGioco1ImageView.getWidth();
		float areaPrimoEsercizioHeight = posizioneGioco1ImageView.getHeight();

		return personaggioX < (areaPrimoEsercizioX + areaPrimoEsercizioWidth) &&
				(personaggioX + personaggioWidth) > areaPrimoEsercizioX &&
				personaggioY < (areaPrimoEsercizioY + areaPrimoEsercizioHeight) &&
				(personaggioY + personaggioHeight) > areaPrimoEsercizioY;
	}

	private boolean isPersonaggioInAreaSecondaEsercizio() {
		setPersonaggioPosition();
		float areaSecondoEsercizioX = posizioneGioco2ImageView.getX();
		float areaSecondoEsercizioY = posizioneGioco2ImageView.getY();
		float areaSecondoEsercizioWidth = posizioneGioco2ImageView.getWidth();
		float areaSecondoEsercizioHeight = posizioneGioco2ImageView.getHeight();

		return personaggioX < (areaSecondoEsercizioX + areaSecondoEsercizioWidth) &&
				(personaggioX + personaggioWidth) > areaSecondoEsercizioX &&
				personaggioY < (areaSecondoEsercizioY + areaSecondoEsercizioHeight) &&
				(personaggioY + personaggioHeight) > areaSecondoEsercizioY;
	}

	private boolean isPersonaggioInAreaTerzoEsercizio() {
		setPersonaggioPosition();
		float areaTerzoEsercizioX = posizioneGioco3ImageView.getX();
		float areaTerzoEsercizioY = posizioneGioco3ImageView.getY();
		float areaTerzoEsercizioWidth = posizioneGioco3ImageView.getWidth();
		float areaTerzoEsercizioHeight = posizioneGioco3ImageView.getHeight();

		return personaggioX < (areaTerzoEsercizioX + areaTerzoEsercizioWidth) &&
				(personaggioX + personaggioWidth) > areaTerzoEsercizioX &&
				personaggioY < (areaTerzoEsercizioY + areaTerzoEsercizioHeight) &&
				(personaggioY + personaggioHeight) > areaTerzoEsercizioY;
	}

	@SuppressLint("ClickableViewAccessibility")
	private void enableImageDrag(ImageView view) {
		view.setOnTouchListener(new View.OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				final float x = event.getRawX();
				final float y = event.getRawY();

				switch (event.getAction() & MotionEvent.ACTION_MASK) {
					case MotionEvent.ACTION_DOWN:
						// Salva le coordinate iniziali del tocco
						xDelta = x - v.getX();
						yDelta = y - v.getY();
						break;

					case MotionEvent.ACTION_MOVE:
						// Calcola la nuova posizione della ImageView
						float newX = x - xDelta;
						float newY = y - yDelta;

						if (isPersonaggioInAreaPrimoEsercizio()) {
							ridimensionaPosizione(posizioneGioco2ImageView);
							ridimensionaPosizione(posizioneGioco3ImageView);
							posizioneGioco2ImageView.setBackground(null);
							posizioneGioco3ImageView.setBackground(null);
							highlightPosizione(posizioneGioco1ImageView);

						} else if (isPersonaggioInAreaSecondaEsercizio()) {
							ridimensionaPosizione(posizioneGioco1ImageView);
							ridimensionaPosizione(posizioneGioco3ImageView);
							posizioneGioco1ImageView.setBackground(null);
							posizioneGioco3ImageView.setBackground(null);
							highlightPosizione(posizioneGioco2ImageView);

						} else if (isPersonaggioInAreaTerzoEsercizio()) {
							ridimensionaPosizione(posizioneGioco1ImageView);
							ridimensionaPosizione(posizioneGioco2ImageView);
							posizioneGioco1ImageView.setBackground(null);
							posizioneGioco2ImageView.setBackground(null);
							highlightPosizione(posizioneGioco3ImageView);

						} else {
							Log.d("Personaggio", "non in area esercizio");
							//isPersonaggioInAreaEsercizio = false;
							isVibrating = false;
							ridimensionaPosizione(posizioneGioco1ImageView);
							ridimensionaPosizione(posizioneGioco2ImageView);
							ridimensionaPosizione(posizioneGioco3ImageView);
							posizioneGioco1ImageView.setBackground(null);
							posizioneGioco2ImageView.setBackground(null);
							posizioneGioco3ImageView.setBackground(null);
						}

						// Verifica che la ImageView non esca dalla schermata
						if (newX > 0 && newX < (getScreenWidth() - v.getWidth())) {
							v.setX(newX);
						}

						if (newY > topHeight && newY < getScreenHeight() - bottomHeight) {
							v.setY(newY);
						}

						break;

					case MotionEvent.ACTION_UP:
						//controlla dove sta il personaggio e naviga al gioco corrispondente
						if (isPersonaggioInAreaPrimoEsercizio()) {
							Log.d("Personaggio", "in area primo esercizio in esecuzione");
							navigateTo(R.id.action_scenarioFragment_to_esercizioDenominazioneImmagineFragment2);
						} else if (isPersonaggioInAreaSecondaEsercizio()) {
							navigateTo(R.id.action_scenarioFragment_to_esercizioCoppiaImmagini2);
						} else if (isPersonaggioInAreaTerzoEsercizio()) {
							navigateTo(R.id.action_scenarioFragment_to_esercizioSequenzaParole);
						}
				}
				return true;
			}
		});
	}

	private void vibrate() {
		if (!isVibrating) {
			if (vibrator.hasVibrator())
				vibrator.vibrate(VibrationEffect.createOneShot(100, VibrationEffect.DEFAULT_AMPLITUDE));
			isVibrating = true;
		}
	}

	private int getScreenWidth() {
		return getResources().getDisplayMetrics().widthPixels;
	}

	private int getScreenHeight() {
		return getResources().getDisplayMetrics().heightPixels;
	}

}