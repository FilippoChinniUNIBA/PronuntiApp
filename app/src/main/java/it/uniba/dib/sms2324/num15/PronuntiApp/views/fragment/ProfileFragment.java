package it.uniba.dib.sms2324.num15.PronuntiApp.views.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.PickVisualMediaRequest;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.google.android.material.textfield.TextInputEditText;

import it.uniba.dib.sms2324.num15.PronuntiApp.R;


public class ProfileFragment extends Fragment {
    private TextView textViewUsernameProfilo;
    private ImageView imageViewProfile;
    private ImageView imageViewEditProfile;
    private TextInputEditText textInputEditTextNome;
    private TextInputEditText textInputEditTextCognome;
    private TextInputEditText textInputEditTextEmail;
    private TextInputEditText textInputEditTextTelefono;
    private TextInputEditText textInputEditTextIndirizzo;
    private ActivityResultLauncher<PickVisualMediaRequest> pickMedia;
    private Button buttonModificaProfilo;
    public ProfileFragment(){
        // require a empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_profile, container, false);
        textViewUsernameProfilo = view.findViewById(R.id.textViewUsernameProfilo);
        textInputEditTextNome = view.findViewById(R.id.textInputEditTextNomeProfilo);
        textInputEditTextCognome = view.findViewById(R.id.textInputEditTextCognomeProfilo);
        textInputEditTextEmail = view.findViewById(R.id.textInputEditTextEmailProfilo);
        textInputEditTextTelefono = view.findViewById(R.id.textInputEditTextTelefonoProfilo);
        textInputEditTextIndirizzo = view.findViewById(R.id.textInputEditTextIndirizzoProfilo);
        buttonModificaProfilo= view.findViewById(R.id.buttonModificaProfilo);
        imageViewEditProfile = view.findViewById(R.id.imageViewEditProfile);
        setData();

        imageViewProfile = view.findViewById(R.id.imageViewProfile);

        pickMedia =
                registerForActivityResult(new ActivityResultContracts.PickVisualMedia(), uri -> {
                    // Callback is invoked after the user selects a media item or closes the
                    // photo picker.
                    if (uri != null) {
                        Log.d("PhotoPicker", "Selected URI: " + uri);
                        Glide.with(this)
                                .load(uri)
                                .circleCrop() // Applica la trasformazione circolare
                                .into(imageViewProfile); // Carica l'immagine nell'ImageView
                    } else {
                        Log.d("PhotoPicker", "No media selected");
                    }
                });

        buttonModificaProfilo.setOnClickListener(v->modifyProfile());

        return view;
    }

    public void setData(){
        textInputEditTextNome.setText("Nome");
        textInputEditTextNome.setEnabled(false);
        textInputEditTextCognome.setText("Cognome");
        textInputEditTextCognome.setEnabled(false);
        textInputEditTextEmail.setText("Email");
        textInputEditTextEmail.setEnabled(false);
        textViewUsernameProfilo.setText("@username");
        textInputEditTextTelefono.setText("Telefono");
        textInputEditTextTelefono.setEnabled(false);
        textInputEditTextIndirizzo.setText("Indirizzo");
        textInputEditTextIndirizzo.setEnabled(false);
    }

    public void modifyProfile(){
        textInputEditTextNome.setEnabled(true);
        textInputEditTextCognome.setEnabled(true);
        textInputEditTextEmail.setEnabled(true);
        textInputEditTextTelefono.setEnabled(true);
        textInputEditTextIndirizzo.setEnabled(true);

        buttonModificaProfilo.setText(getString(R.string.confirm_modify_profile));
        buttonModificaProfilo.setOnClickListener(v->confirmModifyProfile());

        imageViewProfile.setOnClickListener(v->pickImage());

        imageViewEditProfile.setOnClickListener(v->pickImage());
        imageViewEditProfile.setVisibility(View.VISIBLE);

        //focus automatico per far capire che si può modificare
        textInputEditTextNome.requestFocus();
    }
    @Override
    public void onResume() {
        super.onResume();
        if (getActivity() != null) {
            getActivity().setTitle("Profilo");
        }
    }

    private void confirmModifyProfile() {
        //salvare in db
        //save();

        setData();

        imageViewEditProfile.setVisibility(View.GONE);

        buttonModificaProfilo.setText(getString(R.string.modify_profile));
        buttonModificaProfilo.setOnClickListener(v->modifyProfile());

        imageViewProfile.setOnClickListener(v->{});
    }

    private void pickImage(){

        // Launch the photo picker and let the user choose only images.
        pickMedia.launch(new PickVisualMediaRequest.Builder()
                .setMediaType(ActivityResultContracts.PickVisualMedia.ImageOnly.INSTANCE)
                .build());

    }



}