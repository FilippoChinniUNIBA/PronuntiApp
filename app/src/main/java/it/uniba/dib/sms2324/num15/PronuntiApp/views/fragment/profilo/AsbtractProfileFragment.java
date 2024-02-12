package it.uniba.dib.sms2324.num15.PronuntiApp.views.fragment.profilo;

import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputEditText;

import it.uniba.dib.sms2324.num15.PronuntiApp.views.fragment.AbstractFragmentWithNavigation;

public abstract class AsbtractProfileFragment extends AbstractFragmentWithNavigation {
    protected TextView textViewUsernameProfilo;
    protected TextInputEditText textInputEditTextNome;
    protected TextInputEditText textInputEditTextCognome;
    protected TextInputEditText textInputEditTextEmail;


    abstract void modificaProfilo();
    abstract void confermaModificaProfilo();
    abstract void setData();

}

