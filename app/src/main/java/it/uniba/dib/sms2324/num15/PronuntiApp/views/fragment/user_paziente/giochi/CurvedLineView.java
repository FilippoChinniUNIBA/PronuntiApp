package it.uniba.dib.sms2324.num15.PronuntiApp.views.fragment.user_paziente.giochi;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.CornerPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class CurvedLineView extends View {

    private Paint paint;
    private float startX, startY, endX, endY;
    private boolean startSet, endSet;

    public CurvedLineView(Context context) {
        super(context);
        init();
    }

    public CurvedLineView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(5f);
        paint.setPathEffect(new CornerPathEffect(20));
        startSet = false;
        endSet = false;
    }

    public void setStartPoint(float x, float y) {
        startX = x;
        startY = y;
        startSet = true;
        checkAndInvalidate();
    }

    public void setEndPoint(float x, float y) {
        endX = x;
        endY = y;
        endSet = true;
        checkAndInvalidate();
    }

    private void checkAndInvalidate() {
        if (startSet && endSet) {
            invalidate(); // Redraw the view only when both start and end points are set
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // Draw the curved line between the points
        Path path = new Path();
        Log.d("startX", String.valueOf(startX));
        Log.d("startY", String.valueOf(startY));
        Log.d("endX", String.valueOf(endX));
        Log.d("endY", String.valueOf(endY));

        path.moveTo(startX, startY);
        path.quadTo((startX + endX) / 2, startY + 200, endX, endY); // Change the quadTo parameters to adjust the curve
        canvas.drawPath(path, paint);
    }
}
