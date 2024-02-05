package it.uniba.dib.sms2324.num15.PronuntiApp.viewmodels.signinupviewmodel;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.profilo.Logopedista;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.profilo.Paziente;
import it.uniba.dib.sms2324.num15.PronuntiApp.viewmodels.profileviewmodel.ProfileGenitoreViewModel;
import it.uniba.dib.sms2324.num15.PronuntiApp.viewmodels.profileviewmodel.ProfileLogopedistaViewModel;
import it.uniba.dib.sms2324.num15.PronuntiApp.viewmodels.profileviewmodel.ProfilePazienteViewModel;

public class LoginViewModel extends ViewModel {

    private ProfileLogopedistaViewModel profileLogopedistaViewModel;
    private ProfileGenitoreViewModel profileGenitoreViewModel;
    private ProfilePazienteViewModel profilePazienteViewModel;

    public void login(String password, String username) {
        //DAO

        if(currentUser.istanceOf(Logopedista.class)){

        profileLogopedistaViewModel = new ViewModelProvider.get(ProfileLogopedistaViewModel.class);
        profileLogopedistaViewModel.setLogopedista();

        }else if (currentUser.istanceOf(Paziente.class)){

        profileGenitoreViewModel = new ViewModelProvider((ViewModelStoreOwner) this).get(ProfileGenitoreViewModel.class);
        profileGenitoreViewModel.setGenitore();

        }else{

        profilePazienteViewModel = new ViewModelProvider((ViewModelStoreOwner) this).get(ProfilePazienteViewModel.class);
        profilePazienteViewModel.setPaziente();

        }

    }
}
