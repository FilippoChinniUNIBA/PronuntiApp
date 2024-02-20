package it.uniba.dib.sms2324.num15.PronuntiApp.views.fragment.user_logopedista.lista_pazienti.terapie;

import static android.app.Activity.RESULT_OK;


import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.google.android.material.textfield.TextInputEditText;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import it.uniba.dib.sms2324.num15.PronuntiApp.R;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.database.database_utils.ComandiFirebaseStorage;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.database.scenariogioco.TemplateScenarioGiocoDAO;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.esercizio.Esercizio;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.esercizio.EsercizioEseguibile;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.profilo.Paziente;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.scenariogioco.ScenarioGioco;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.scenariogioco.TemplateScenarioGioco;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.terapia.Terapia;
import it.uniba.dib.sms2324.num15.PronuntiApp.viewmodels.logopedista_viewmodel.LogopedistaViewModel;
import it.uniba.dib.sms2324.num15.PronuntiApp.views.dialog.InfoDialog;
import it.uniba.dib.sms2324.num15.PronuntiApp.views.fragment.AbstractFragmentWithNavigation;
import it.uniba.dib.sms2324.num15.PronuntiApp.views.fragment.utils_fragments.DatePickerCustom;

public class CreazioneScenarioFragment extends AbstractFragmentWithNavigation {

    private TextInputEditText dataScenario;
    private TextInputEditText ricompensaFinale;
    private Button buttonUseTemplate;
    private Button buttonCreateScenarioFromStart;
    private Button buttonChooseBackground;
    private Button buttonChooseImgPos1;
    private Button buttonChooseImgPos2;
    private Button buttonChooseImgPos3;
    private ImageView imgPos1;
    private ImageView imgPos2;
    private ImageView imgPos3;
    private Button buttonSalvataggioScenario;
    private LinearLayout linearLayoutSceltaTemplateOCreaScenario;
    private LinearLayout linearLayoutCreazioneScenario;
    private ConstraintLayout constraintLayoutCostruzioneImmagineScenario;
    private ImageButton buttonNextScenario;
    private ImageButton buttonPreviousScenario;

    private static final int PICK_FILE_REQUEST_1 = 1;
    private static final int PICK_FILE_REQUEST_2 = 2;
    private static final int PICK_FILE_REQUEST_3 = 3;
    private static final int PICK_FILE_REQUEST_4 = 4;

    private String imgPos1Uri;
    private String imgPos2Uri;
    private String imgPos3Uri;
    private String imgBackgroundUri;
    private String dataInizio;
    private int ricompensa;

    private List<EsercizioEseguibile> esercizi;
    private ScenarioGioco scenario;
    private List<TemplateScenarioGioco> templateScenari;
    private int currentIndexTemplateScenari = 0;
    private int sizeTemplateScenari;
    private boolean sceltaScenari = false;

    //interfaccia di callBack per salvare lo scenario
    private LogopedistaViewModel mLogopedistaViewModel;
    private SaveScenario mCallback;
    private Bundle bundle;


    //solo quando crei uno scenario per terapia esistente
    private String idPaziente;
    private int indiceTerapia;

    public CreazioneScenarioFragment(SaveScenario mCallback) {
        this.mCallback = mCallback;
    }

    public CreazioneScenarioFragment() {
        super();
        mCallback = null;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        //mLogopedistaViewModel.getLogopedistaLiveData().observe();
        View view = inflater.inflate(R.layout.fragment_creazione_scenario, container, false);
        mLogopedistaViewModel = new ViewModelProvider(requireActivity()).get(LogopedistaViewModel.class);
        view.findViewById(R.id.toolBar).setVisibility(View.GONE);


        bundle = getArguments();
        if(bundle!=null) {
            setToolBar(view,getString(R.string.nuovoScenario));
            idPaziente = bundle.getString("idPaziente");
            indiceTerapia = bundle.getInt("indiceTerapia");
            view.findViewById(R.id.toolBar).setVisibility(View.VISIBLE);
        }


        esercizi=new ArrayList<>();

        dataScenario = view.findViewById(R.id.textInputEditTextDataInizioScenario);
        ricompensaFinale = view.findViewById(R.id.textInputEditTextRicompensaFinaleScenario);

        linearLayoutSceltaTemplateOCreaScenario = view.findViewById(R.id.linearLayoutSceltaTemplateOCreaScenario);
        buttonUseTemplate = view.findViewById(R.id.buttonTemplateScenario);
        buttonCreateScenarioFromStart = view.findViewById(R.id.buttonCreaScenario);

        linearLayoutCreazioneScenario = view.findViewById(R.id.linearLayoutCreazioneScenario);
        buttonChooseBackground = view.findViewById(R.id.buttonOpenFilePickerImmagineSfondo);
        buttonChooseImgPos1 = view.findViewById(R.id.buttonOpenFilePickerImmagineTappa1);
        buttonChooseImgPos2 = view.findViewById(R.id.buttonOpenFilePickerImmagineTappa2);
        buttonChooseImgPos3 = view.findViewById(R.id.buttonOpenFilePickerImmagineTappa3);


        constraintLayoutCostruzioneImmagineScenario = view.findViewById(R.id.constraintLayoutCostruzioneImmagineScenario);
        buttonNextScenario = view.findViewById(R.id.buttonTemplateScenarioNext);
        buttonPreviousScenario = view.findViewById(R.id.buttonTemplateScenarioBack);
        imgPos1 = view.findViewById(R.id.primaTappaCreazioneScenario);
        imgPos2 = view.findViewById(R.id.secondaTappaCreazioneScenario);
        imgPos3 = view.findViewById(R.id.terzaTappaCreazioneScenario);



        buttonSalvataggioScenario = view.findViewById(R.id.confermaScenarioButton);

        return view;
    }



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        linearLayoutSceltaTemplateOCreaScenario.setVisibility(View.VISIBLE);
        linearLayoutCreazioneScenario.setVisibility(View.GONE);
        buttonNextScenario.setVisibility(View.GONE);
        buttonPreviousScenario.setVisibility(View.GONE);
        buttonChooseImgPos1.setVisibility(View.GONE);
        buttonChooseImgPos2.setVisibility(View.GONE);
        buttonChooseImgPos3.setVisibility(View.GONE);

        buttonChooseImgPos1.setOnClickListener(v -> startFilePicker(PICK_FILE_REQUEST_1));
        buttonChooseImgPos2.setOnClickListener(v -> startFilePicker(PICK_FILE_REQUEST_2));
        buttonChooseImgPos3.setOnClickListener(v -> startFilePicker(PICK_FILE_REQUEST_3));

        constraintLayoutCostruzioneImmagineScenario.setVisibility(View.GONE);

        buttonChooseBackground.setOnClickListener(v -> {
                    startFilePicker(PICK_FILE_REQUEST_4);
                    constraintLayoutCostruzioneImmagineScenario.setVisibility(View.VISIBLE);
                    buttonChooseImgPos1.setVisibility(View.VISIBLE);
                    buttonChooseImgPos2.setVisibility(View.VISIBLE);
                    buttonChooseImgPos3.setVisibility(View.VISIBLE);
                }
        );

        dataScenario.setOnClickListener(v -> DatePickerCustom.showDatePickerDialog(getContext(), dataScenario));

        buttonSalvataggioScenario.setOnClickListener(v -> saveScenario());

        buttonUseTemplate.setOnClickListener(v -> useTemplate());
        buttonCreateScenarioFromStart.setOnClickListener(v -> createScenarioFromStart());

        buttonNextScenario.setOnClickListener(v -> prossimoTemplateScenario());
        buttonPreviousScenario.setOnClickListener(v -> precedenteTemplateScenario());

        mLogopedistaViewModel.getLogopedistaLiveData().observe(getViewLifecycleOwner(),logopedista -> {
            TemplateScenarioGiocoDAO templateScenarioGiocoDAO = new TemplateScenarioGiocoDAO();
            templateScenarioGiocoDAO.getAll().thenAccept(result -> {
                templateScenari = result;
                sizeTemplateScenari = result.size();
            });
        });
    }

    private void showErrorDialog(){
        InfoDialog infoDialog = new InfoDialog(getContext(), getString(R.string.compilaPrimaTutto), getString(R.string.tastoRiprova));
        infoDialog.setOnConfermaButtonClickListener(null);
        infoDialog.show();
    }

    private void saveScenario(){
        //prendo esercizi dai fragmentFigli
        EsercizioEseguibile es1 = ((CreazioneEsercizioFragment) getChildFragmentManager().findFragmentById(R.id.fragmentContainerViewEsercizio1)).getEsercizio();
        EsercizioEseguibile es2 = ((CreazioneEsercizioFragment) getChildFragmentManager().findFragmentById(R.id.fragmentContainerViewEsercizio2)).getEsercizio();
        EsercizioEseguibile es3 = ((CreazioneEsercizioFragment) getChildFragmentManager().findFragmentById(R.id.fragmentContainerViewEsercizio3)).getEsercizio();
        if(sceltaScenari) {
            imgPos1Uri = templateScenari.get(currentIndexTemplateScenari).getImmagineTappa1();
            imgPos2Uri = templateScenari.get(currentIndexTemplateScenari).getImmagineTappa2();
            imgPos3Uri = templateScenari.get(currentIndexTemplateScenari).getImmagineTappa3();
            imgBackgroundUri = templateScenari.get(currentIndexTemplateScenari).getImmagineSfondo();
        }

        if(dataScenario.getText().toString().isEmpty() || ricompensaFinale.getText().toString().isEmpty() || imgPos1Uri==null
                || imgPos2Uri==null || imgPos3Uri==null || imgBackgroundUri==null || es1==null || es2==null || es3==null){
            showErrorDialog();
        }
        else {
            dataInizio = dataScenario.getText().toString();
            ricompensa = Integer.parseInt(ricompensaFinale.getText().toString());

            esercizi.add(es1);
            esercizi.add(es2);
            esercizi.add(es3);
            Log.d("CreazioneScenarioFragment", "saveScenario: "+esercizi.toString());

            String refTemplate = "0";
            scenario = new ScenarioGioco(imgBackgroundUri, imgPos1Uri, imgPos2Uri, imgPos3Uri, LocalDate.parse(dataInizio), ricompensa, esercizi, refTemplate);

            if(mCallback == null){
                Paziente paziente = mLogopedistaViewModel.getPazienteById(idPaziente);
                paziente.getTerapie().get(indiceTerapia).addScenario(scenario);
                mLogopedistaViewModel.aggiornaLogopedistaRemoto();

                navigateTo(R.id.action_creazioneScenarioFragment_to_schedaPazienteFragment,bundle);
            }
            else {
                mCallback.saveScenario(scenario);
                getParentFragmentManager().beginTransaction().remove(this).commit();
            }
        }
    }

    private void useTemplate(){
        sceltaScenari =true;
        linearLayoutSceltaTemplateOCreaScenario.setVisibility(View.GONE);
        linearLayoutCreazioneScenario.setVisibility(View.GONE);
        buttonNextScenario.setVisibility(View.VISIBLE);
        buttonPreviousScenario.setVisibility(View.VISIBLE);
        constraintLayoutCostruzioneImmagineScenario.setVisibility(View.VISIBLE);
        modificaCostruzioneScenarioConTemplate();
    }

    private void createScenarioFromStart(){
        linearLayoutSceltaTemplateOCreaScenario.setVisibility(View.GONE);
        linearLayoutCreazioneScenario.setVisibility(View.VISIBLE);
        buttonNextScenario.setVisibility(View.GONE);
        buttonPreviousScenario.setVisibility(View.GONE);
    }

    private void prossimoTemplateScenario(){
        if(currentIndexTemplateScenari+1<sizeTemplateScenari){
            currentIndexTemplateScenari +=1;
        }else{
            currentIndexTemplateScenari =0;
        }
        modificaCostruzioneScenarioConTemplate();
    }

    private void precedenteTemplateScenario(){
        if(currentIndexTemplateScenari-1 >= 0){
            currentIndexTemplateScenari -=1;
        }else{
            currentIndexTemplateScenari = sizeTemplateScenari-1;
        }
        modificaCostruzioneScenarioConTemplate();

    }

    private void modificaCostruzioneScenarioConTemplate(){
        String img1 = templateScenari.get(currentIndexTemplateScenari).getImmagineTappa1();
        String img2 = templateScenari.get(currentIndexTemplateScenari).getImmagineTappa2();
        String img3 = templateScenari.get(currentIndexTemplateScenari).getImmagineTappa3();
        String imgSfondo = templateScenari.get(currentIndexTemplateScenari).getImmagineSfondo();

        Glide.with(getContext()).load(img1).into(imgPos1);
        Glide.with(getContext()).load(img2).into(imgPos2);
        Glide.with(getContext()).load(img3).into(imgPos3);
        Glide.with(getContext())
                .asBitmap()
                .load(imgSfondo)
                .into(new CustomTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                        constraintLayoutCostruzioneImmagineScenario.setBackground(new BitmapDrawable(getResources(), resource));
                    }

                    @Override
                    public void onLoadCleared(@Nullable Drawable placeholder) {
                        // Questo metodo viene chiamato quando l'immagine non è più necessaria.
                    }
                });

    }


    private void startFilePicker(int requestCode) {
        Intent intent = new Intent();
        intent.setType("*/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.putExtra(Intent.EXTRA_MIME_TYPES, new String[] {"image/*"});

        startActivityForResult(Intent.createChooser(intent, "Select Picture"), requestCode);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri uri = data.getData();

            ComandiFirebaseStorage comandiFirebaseStorage = new ComandiFirebaseStorage();
            comandiFirebaseStorage.uploadFileAndGetLink(uri, "TEST/TERPIA_TEST").thenAccept(link -> {

                switch (requestCode) {
                    case PICK_FILE_REQUEST_1:
                        imgPos1Uri = link;
                        Glide.with(getContext()).load(uri).into(imgPos1);
                        break;
                    case PICK_FILE_REQUEST_2:
                        imgPos2Uri = link;
                        Glide.with(getContext()).load(uri).into(imgPos2);
                        break;
                    case PICK_FILE_REQUEST_3:
                        imgPos3Uri = link;
                        Glide.with(getContext()).load(uri).into(imgPos3);
                        break;
                    case PICK_FILE_REQUEST_4:
                        imgBackgroundUri = link;
                        Glide.with(getContext())
                                .asBitmap()
                                .load(uri)
                                .into(new CustomTarget<Bitmap>() {
                                    @Override
                                    public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                                        constraintLayoutCostruzioneImmagineScenario.setBackground(new BitmapDrawable(getResources(), resource));
                                    }

                                    @Override
                                    public void onLoadCleared(@Nullable Drawable placeholder) {
                                        // Questo metodo viene chiamato quando l'immagine non è più necessaria.
                                    }
                                });
                        break;
                    default:
                        break;
                }
            });
        }
    }
}