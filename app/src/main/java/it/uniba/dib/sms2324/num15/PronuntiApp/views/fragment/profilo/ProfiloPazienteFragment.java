package it.uniba.dib.sms2324.num15.PronuntiApp.views.fragment.profilo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.textfield.TextInputEditText;

import it.uniba.dib.sms2324.num15.PronuntiApp.R;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.profilo.Paziente;
import it.uniba.dib.sms2324.num15.PronuntiApp.viewmodels.genitore_viewmodel.GenitoreViewModel;
import it.uniba.dib.sms2324.num15.PronuntiApp.viewmodels.paziente_viewmodels.PazienteViewModel;

public class ProfiloPazienteFragment extends AsbtractProfileFragment{
    private TextInputEditText textInputEditTextDataNascita;
    private EditText spinnerSesso;
    private TextView textViewDatiBambino;
    private ImageView textViewArrowDown;
    LinearLayout linearLayoutDatiBambinoClick;
    LinearLayout linearLayoutContainerBambino;
    private PazienteViewModel mPazienteViewModel;
    private GenitoreViewModel mGenitoreViewModel;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_profile_paziente, container, false);
        textViewUsernameProfilo = view.findViewById(R.id.textInputEditTextUsernameProfiloPaziente);
        textInputEditTextNome = view.findViewById(R.id.textInputEditTextNomeProfiloPaziente);
        textInputEditTextCognome = view.findViewById(R.id.textInputEditTextCognomeProfiloPaziente);
        textInputEditTextEmail = view.findViewById(R.id.textInputEditTextEmailProfiloPaziente);
        //imageViewProfile = view.findViewById(R.id.imageViewProfile);
        //imageViewEditProfile = view.findViewById(R.id.imageViewEditProfile);
        //buttonModificaProfilo= view.findViewById(R.id.buttonModificaProfiloPaziente);
        //setPickMedia();
        textInputEditTextDataNascita = view.findViewById(R.id.textInputEditTextDataNascitaProfiloPaziente);
        spinnerSesso = view.findViewById(R.id.spinnerSessoProfiloPaziente);

        this.mGenitoreViewModel = new ViewModelProvider(requireActivity()).get(GenitoreViewModel.class);

        //TODO prendere il paziente dal genitore dal viewmodelGenitore

        textViewDatiBambino = view.findViewById(R.id.textViewDatiBambino);
        textViewArrowDown = view.findViewById(R.id.arrowImageView);
        linearLayoutDatiBambinoClick = view.findViewById(R.id.llDatiPazientiClick);
        linearLayoutDatiBambinoClick.setOnClickListener(v->onDatiBambiniClick(view));
        linearLayoutContainerBambino = view.findViewById(R.id.linearLayoutProfiloPaziente);

        setData();



        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setData();
    }

    private void onDatiBambiniClick(View view) {
        if (linearLayoutContainerBambino.getVisibility() == View.VISIBLE) {
            linearLayoutContainerBambino.setVisibility(View.GONE);
            textViewArrowDown.setRotation(0);
        } else {
            linearLayoutContainerBambino.setVisibility(View.VISIBLE);
            textViewArrowDown.setRotation(180);
        }
    }

    @Override
    public void setData(){
        String idGenitore = mGenitoreViewModel.getGenitoreLiveData().getValue().getIdProfilo();
        mGenitoreViewModel.getPazienteGenitore(idGenitore).thenAccept(paziente -> {
            textInputEditTextNome.setText(paziente.getNome());
            textInputEditTextNome.setEnabled(false);
            textInputEditTextCognome.setText(paziente.getCognome());
            textInputEditTextCognome.setEnabled(false);
            textViewUsernameProfilo.setText(paziente.getUsername());
            textInputEditTextDataNascita.setText(paziente.getDataNascita().toString());
            textInputEditTextDataNascita.setEnabled(false);
            textInputEditTextEmail.setText(paziente.getEmail());
            textInputEditTextEmail.setEnabled(false);
            spinnerSesso.setSelection(paziente.getSesso());
            spinnerSesso.setEnabled(false);
        });

    }

    @Override
    public void modificaProfilo(){

    }

    @Override
    public void confermaModificaProfilo(){
        //salvare in db
        //save();
        setData();
   }

}
