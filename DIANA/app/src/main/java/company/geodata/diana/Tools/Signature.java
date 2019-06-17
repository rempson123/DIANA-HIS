package company.geodata.diana.Tools;

/**
 * Created by jcmate on 7/4/2017.
 */

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;

/**
 * Created by MyPc on 9/7/2016.
 */

public class Signature extends View {
    private static final float STROKE_WIDTH = 5f;
    private static final float HALF_STROKE_WIDTH = STROKE_WIDTH / 2;

    Path m_path = new Path();

    private float m_lastTouchX;
    private float m_lastTouchY;
    private final RectF m_dirtyRect = new RectF();
    Paint m_paint = new Paint();
    Bitmap m_bitmap;
    Context context;
    public static String m_tempDir;


    public Signature(Context context, AttributeSet attrs) {
        super(context, attrs);
        m_paint.setAntiAlias(true);
        m_paint.setStyle(Paint.Style.STROKE);
        m_paint.setStrokeJoin(Paint.Join.ROUND);
        m_paint.setStrokeWidth(STROKE_WIDTH);
        this.context = context;
    }

    public Bitmap getBitmap(View v)
    {
        Log.v("log_tag", "Width: " + v.getWidth());
        Log.v("log_tag", "Height: " + v.getHeight());

        if(m_bitmap == null)
        {
            m_bitmap =  Bitmap.createBitmap (v.getWidth(), v.getHeight(), Bitmap.Config.RGB_565);
        }
        Canvas canvas = new Canvas(m_bitmap);
        v.draw(canvas);
        return m_bitmap;
    }

    public void save(View v) {
        Log.v("log_tag", "Width: " + v.getWidth());
        Log.v("log_tag", "Height: " + v.getHeight());

        if (m_bitmap == null) {
            m_bitmap = Bitmap.createBitmap(545, 224, Bitmap.Config.RGB_565);
        }
        Canvas m_canvas = new Canvas(m_bitmap);
        //String m_FtoSave = m_tempDir + System.currentTimeMillis() + ".png";
        String m_FtoSave = m_tempDir + "Signature" + ".png";
        System.err.println("File Name is---->" + m_FtoSave);
        File m_file = new File(m_FtoSave);

        try {
            FileOutputStream m_FileOutStream = new FileOutputStream(m_file);
            v.draw(m_canvas);
            m_bitmap.compress(Bitmap.CompressFormat.PNG, 90,
                    m_FileOutStream);

            m_FileOutStream.flush();
            m_FileOutStream.close();
            String m_url = MediaStore.Images.Media.insertImage(context.getContentResolver(),
                    m_bitmap, "Signature", null);
            Log.v("log_tag", "url" + m_url);

        } catch (Exception e) {
            Log.v("log_tag", e.toString());
        }
    }

    public void clear() {
        m_path.reset();
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawPath(m_path, m_paint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float m_eventX = event.getX();
        float m_eventY = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                m_path.moveTo(m_eventX, m_eventY);
                m_lastTouchX = m_eventX;
                m_lastTouchY = m_eventY;
                return true;

            case MotionEvent.ACTION_MOVE:

            case MotionEvent.ACTION_UP:
                resetDirtyRect(m_eventX, m_eventY);
                int historySize = event.getHistorySize();
                for (int i = 0; i < historySize; i++) {
                    float historicalX = event.getHistoricalX(i);
                    float historicalY = event.getHistoricalY(i);
                    expandDirtyRect(historicalX, historicalY);
                    m_path.lineTo(historicalX, historicalY);
                }
                m_path.lineTo(m_eventX, m_eventY);
                break;

            default:
                debug("Ignored touch event: " + event.toString());
                return false;
        }

        invalidate((int) (m_dirtyRect.left - HALF_STROKE_WIDTH),
                (int) (m_dirtyRect.top - HALF_STROKE_WIDTH),
                (int) (m_dirtyRect.right + HALF_STROKE_WIDTH),
                (int) (m_dirtyRect.bottom + HALF_STROKE_WIDTH));

        m_lastTouchX = m_eventX;
        m_lastTouchY = m_eventY;

        return true;
    }

    private void debug(String string) {
    }

    private void expandDirtyRect(float historicalX, float historicalY) {
        if (historicalX < m_dirtyRect.left) {
            m_dirtyRect.left = historicalX;
        } else if (historicalX > m_dirtyRect.right) {
            m_dirtyRect.right = historicalX;
        }

        if (historicalY < m_dirtyRect.top) {
            m_dirtyRect.top = historicalY;
        } else if (historicalY > m_dirtyRect.bottom) {
            m_dirtyRect.bottom = historicalY;
        }
    }

    private void resetDirtyRect(float eventX, float eventY) {
        m_dirtyRect.left = Math.min(m_lastTouchX, eventX);
        m_dirtyRect.right = Math.max(m_lastTouchX, eventX);
        m_dirtyRect.top = Math.min(m_lastTouchY, eventY);
        m_dirtyRect.bottom = Math.max(m_lastTouchY, eventY);
    }

    public boolean prepareDirectory() {
        m_tempDir = Environment.getExternalStorageDirectory() + "/Signature/";
        try {
            if (makedirs()) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(
                    context,
                    "Could not initiate File System.. Is Sdcard mounted properly?",
                    Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    public static boolean makedirs() {
        File m_tempdir = new File(m_tempDir);
        if (!m_tempdir.exists())
            m_tempdir.mkdirs();

        if (m_tempdir.isDirectory()) {
            File[] files = m_tempdir.listFiles();
            for (File file : files) {
                if (!file.delete()) {
                    System.out.println("Failed to delete " + file);
                }
            }
        }
        return (m_tempdir.isDirectory());
    }
}

