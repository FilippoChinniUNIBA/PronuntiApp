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
    protected TextInputEditText textInputEditTextNome;
    protected TextInputEditText textInputEditTextCognome;
    protected TextInputEditText textInputEditTextEmail;

    abstract void modificaProfilo();
    abstract void confermaModificaProfilo();
    abstract void setData();

}

