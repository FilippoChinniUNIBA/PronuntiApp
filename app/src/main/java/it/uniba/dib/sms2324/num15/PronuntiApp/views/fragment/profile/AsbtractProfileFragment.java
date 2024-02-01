package it.uniba.dib.sms2324.num15.PronuntiApp.views.fragment.profile;

import android.util.Log;
import android.view.View;
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

public abstract class AsbtractProfileFragment extends Fragment    {
    protected TextView textViewUsernameProfilo;
    protected ImageView imageViewProfile;
    protected ImageView imageViewEditProfile;
    protected TextInputEditText textInputEditTextNome;
    protected TextInputEditText textInputEditTextCognome;
    protected ActivityResultLauncher<PickVisualMediaRequest> pickMedia;
    protected Button buttonModificaProfilo;


    abstract void setData();
    abstract void modificaProfilo();
    //abstract void save();

    protected void setPickMedia(){
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

        buttonModificaProfilo.setOnClickListener(v->modificaProfilo());
    }

    protected void pickImage(){

        // Launch the photo picker and let the user choose only images.
        pickMedia.launch(new PickVisualMediaRequest.Builder()
                .setMediaType(ActivityResultContracts.PickVisualMedia.ImageOnly.INSTANCE)
                .build());

    }

    protected void confermaModificaProfilo(){
            //salvare in db
            //save();

            setData();

            imageViewEditProfile.setVisibility(View.GONE);

            buttonModificaProfilo.setText(getString(R.string.modify_profile));
            buttonModificaProfilo.setOnClickListener(v->modificaProfilo());

            imageViewProfile.setOnClickListener(v->{});
    }

}

