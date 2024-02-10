package it.uniba.dib.sms2324.num15.PronuntiApp.views.fragment.profilo;

import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputEditText;

public abstract class AsbtractProfileFragment extends Fragment    {
    protected TextView textViewUsernameProfilo;
    protected TextInputEditText textInputEditTextNome;
    protected TextInputEditText textInputEditTextCognome;
    protected TextInputEditText textInputEditTextEmail;


    abstract void modificaProfilo();
    abstract void confermaModificaProfilo();
    abstract void setData();

}

