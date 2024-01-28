package it.uniba.dib.sms2324.num15.PronuntiApp.views.navigationSelector;

import androidx.annotation.IdRes;
import androidx.fragment.app.FragmentManager;

public abstract class AbstractNavigationSelector {

    protected final FragmentManager fragmentManager;
    protected final int fragmentContainerId;

    public AbstractNavigationSelector(FragmentManager fragmentManager, @IdRes int fragmentContainerId) {
        this.fragmentManager = fragmentManager;
        this.fragmentContainerId = fragmentContainerId;
    }
}
