package it.uniba.dib.sms2324.num15.PronuntiApp.viewmodels.signinup_viewmodel;

import androidx.lifecycle.ViewModel;

import it.uniba.dib.sms2324.num15.PronuntiApp.viewmodels.profile_viewmodel.ProfileGenitoreViewModel;
import it.uniba.dib.sms2324.num15.PronuntiApp.viewmodels.profile_viewmodel.ProfileLogopedistaViewModel;
import it.uniba.dib.sms2324.num15.PronuntiApp.viewmodels.profile_viewmodel.ProfilePazienteViewModel;

public class LoginViewModel extends ViewModel {

    private ProfileLogopedistaViewModel profileLogopedistaViewModel;
    private ProfileGenitoreViewModel profileGenitoreViewModel;
    private ProfilePazienteViewModel profilePazienteViewModel;

    public void login(String password, String username) {
        //DAO

       /* if(currentUser.istanceOf(Logopedista.class)){

        profileLogopedistaViewModel = new ViewModelProvider.get(ProfileLogopedistaViewModel.class);
        profileLogopedistaViewModel.setLogopedista();

        }else if (currentUser.istanceOf(Paziente.class)){

        profileGenitoreViewModel = new ViewModelProvider((ViewModelStoreOwner) this).get(ProfileGenitoreViewModel.class);
        profileGenitoreViewModel.setGenitore();

        }else{

        profilePazienteViewModel = new ViewModelProvider((ViewModelStoreOwner) this).get(ProfilePazienteViewModel.class);
        profilePazienteViewModel.setPaziente();

        }*/

    }
}
