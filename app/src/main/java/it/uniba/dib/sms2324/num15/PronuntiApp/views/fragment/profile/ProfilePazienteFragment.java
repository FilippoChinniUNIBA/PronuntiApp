package it.uniba.dib.sms2324.num15.PronuntiApp.views.fragment.profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.google.android.material.textfield.TextInputEditText;

import it.uniba.dib.sms2324.num15.PronuntiApp.R;

public class ProfilePazienteFragment extends AsbtractProfileFragment{
    private TextInputEditText textInputEditTextDataNascita;
    private Spinner spinnerSesso;

    private TextView textViewDatiBambino;
    private ImageView textViewArrowDown;
    LinearLayout linearLayoutDatiBambinoClick;
    LinearLayout linearLayoutContainerBambino;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_profile_paziente, container, false);
        textViewUsernameProfilo = view.findViewById(R.id.textInputEditTextUsernameProfiloPaziente);
        textInputEditTextNome = view.findViewById(R.id.textInputEditTextNomeProfiloPaziente);
        textInputEditTextCognome = view.findViewById(R.id.textInputEditTextCognomeProfiloPaziente);
        textInputEditTextEmail = view.findViewById(R.id.textInputEditTextEmailProfiloPaziente);
        //imageViewProfile = view.findViewById(R.id.imageViewProfile);
        //imageViewEditProfile = view.findViewById(R.id.imageViewEditProfile);
        //uttonModificaProfilo= view.findViewById(R.id.buttonModificaProfiloPaziente);
        //setPickMedia();
        textInputEditTextDataNascita = view.findViewById(R.id.textInputEditTextDataNascitaProfiloPaziente);
        spinnerSesso = view.findViewById(R.id.spinnerSessoProfiloPaziente);

        textViewDatiBambino = view.findViewById(R.id.textViewDatiBambino);
        textViewArrowDown = view.findViewById(R.id.arrowImageView);
        linearLayoutDatiBambinoClick = view.findViewById(R.id.llDatiPazientiClick);
        linearLayoutDatiBambinoClick.setOnClickListener(v->onDatiBambiniClick(view));
        linearLayoutContainerBambino = view.findViewById(R.id.linearLayoutProfiloPaziente);

        setData();
        return view;
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

    public void setData(){
        textInputEditTextNome.setText("Nome");
        textInputEditTextNome.setEnabled(false);
        textInputEditTextCognome.setText("Cognome");
        textInputEditTextCognome.setEnabled(false);
        textViewUsernameProfilo.setText("@username");
        textInputEditTextDataNascita.setText("12/03/1999");
        textInputEditTextDataNascita.setEnabled(false);
        textInputEditTextEmail.setText("ginoPasticcino@gmail.com");
        textInputEditTextEmail.setEnabled(false);
        spinnerSesso.setSelection(0);
        spinnerSesso.setEnabled(false);
    }

    @Override
    public void modificaProfilo(){
        textInputEditTextNome.setEnabled(true);
        textInputEditTextCognome.setEnabled(true);
        textInputEditTextDataNascita.setEnabled(true);
        spinnerSesso.setEnabled(true);
        textInputEditTextEmail.setEnabled(true);
    }

    @Override
    public void confermaModificaProfilo(){
        //salvare in db
        //save();

        setData();

   }
    @Override
    public void onResume() {
        super.onResume();
        if (getActivity() != null) {
            getActivity().setTitle("Profilo");
        }
    }

    //richiamre dao per salvare in db
    //@Ovvveride
    //public void save(){}

}
