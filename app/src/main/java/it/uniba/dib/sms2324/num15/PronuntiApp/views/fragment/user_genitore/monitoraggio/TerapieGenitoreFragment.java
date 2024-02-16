package it.uniba.dib.sms2324.num15.PronuntiApp.views.fragment.user_genitore.monitoraggio;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentContainerView;

import it.uniba.dib.sms2324.num15.PronuntiApp.R;
import it.uniba.dib.sms2324.num15.PronuntiApp.views.fragment.AbstractFragmentWithNavigation;

public class TerapieGenitoreFragment extends AbstractFragmentWithNavigation {

    private FragmentContainerView fragmentContainerViewMonitoraggio;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         super.onCreateView(inflater, container, savedInstanceState);
         View view = inflater.inflate(R.layout.fragment_terapie, container, false);

        fragmentContainerViewMonitoraggio = view.findViewById(R.id.fragmentContainerViewMonitoraggio);

         FragmentContainerView.MarginLayoutParams params = (FragmentContainerView.MarginLayoutParams) fragmentContainerViewMonitoraggio.getLayoutParams();
         params.setMargins(0, 0, 0, getContext().getResources().getDimensionPixelSize(R.dimen.nav_bar_height));

         getChildFragmentManager().beginTransaction().replace(R.id.fragmentContainerViewMonitoraggio, new MonitoraggioGenitoreFragment()).commit();

         return view;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }
}
