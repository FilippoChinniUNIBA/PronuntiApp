package it.uniba.dib.sms2324.num15.PronuntiApp.views.fragment.profilo;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.PickVisualMediaRequest;
import androidx.activity.result.contract.ActivityResultContracts;

import com.bumptech.glide.Glide;

import it.uniba.dib.sms2324.num15.PronuntiApp.R;

public abstract class AbstractProfileWithImageFragment extends AsbtractProfileFragment{
    protected ActivityResultLauncher<PickVisualMediaRequest> pickMedia;
    protected Button buttonModificaProfilo;
    protected ImageView imageViewProfile;
    protected ImageView imageViewEditProfile;
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

    @Override
    void confermaModificaProfilo(){
        //salvare in db
        //save();

        setData();

        imageViewEditProfile.setVisibility(View.GONE);

        buttonModificaProfilo.setText(getString(R.string.modify_profile));
        buttonModificaProfilo.setOnClickListener(v->modificaProfilo());

        //imageViewProfile.setOnClickListener(v->{});
    }

}
