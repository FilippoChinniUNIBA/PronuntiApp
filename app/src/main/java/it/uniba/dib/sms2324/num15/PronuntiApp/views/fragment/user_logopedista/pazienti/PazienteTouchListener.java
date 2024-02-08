package it.uniba.dib.sms2324.num15.PronuntiApp.views.fragment.user_logopedista.pazienti;

import android.content.Context;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import it.uniba.dib.sms2324.num15.PronuntiApp.R;
import it.uniba.dib.sms2324.num15.PronuntiApp.models.domain.profilo.Paziente;

public class PazienteTouchListener implements RecyclerView.OnItemTouchListener {

    private Context context;
    private RecyclerView recyclerView;
    private int lastClickedPosition = -1;

    public PazienteTouchListener(Context context, RecyclerView recyclerView) {
        this.context = context;
        this.recyclerView = recyclerView;
        RecyclerView.Adapter adapter = recyclerView.getAdapter();
    }

    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
        View childView = rv.findChildViewUnder(e.getX(), e.getY());
        return childView != null && isClick(e);
    }

    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {
        if (isClick(e)) {
            if(lastClickedPosition != -1) {
                View lastClickedChildView = rv.getChildAt(lastClickedPosition);
                lastClickedChildView.setBackgroundResource(R.drawable.rectangle_rounded_border_bkg);
            }
            View childView = rv.findChildViewUnder(e.getX(), e.getY());
            int position = rv.getChildLayoutPosition(childView);
            lastClickedPosition = position;
            childView.setBackgroundResource(R.drawable.rectangle_rounded_border_selector_bkg);

            //TODO: naviga a vista dove AggiungereTerapia e MonitoraggioTerapia
            RecyclerView.Adapter adapter = recyclerView.getAdapter();
            Paziente paziente = ((PazienteAdapter) adapter).getItem(position);
            Log.d("PazienteTouchListener", "onTouchEvent: " + paziente);
        }
    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {
    }

    private boolean isClick(MotionEvent e) {
        return e.getAction() == MotionEvent.ACTION_DOWN;
    }
}

