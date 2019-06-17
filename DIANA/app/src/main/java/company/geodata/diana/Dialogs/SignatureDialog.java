package company.geodata.diana.Dialogs;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.DialogFragment;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import company.geodata.diana.R;
import company.geodata.diana.Tools.Signature;

/**
 * Created by jcmate on 7/4/2017.
 */

public class SignatureDialog extends DialogFragment {

    private View mView;
    public AlertDialog dialog;
    Signature m_signature;
    RelativeLayout m_rlContent;
    View m_view;
    ImageView SignatureField;

    public SignatureDialog newInstance(View v) {
        SignatureField = (ImageView) v;
        return this;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        mView = inflater.inflate(R.layout.custom_signature_dialog, null);

        builder.setView(mView).setTitle("Signature");
        dialog = builder.create();
        dialog.setButton(DialogInterface.BUTTON_POSITIVE, "Save", new DialogInterface.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                SignatureField.setImageBitmap(m_signature.getBitmap(m_rlContent));
                SignatureField.setBackground(getResources().getDrawable(R.drawable.signature_background));
            }
        });

        dialog.setButton(DialogInterface.BUTTON_NEUTRAL, "Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        dialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Upload", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        DisplayMetrics metrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int density = metrics.densityDpi;

        if (density == DisplayMetrics.DENSITY_HIGH) {
        } else if (density == DisplayMetrics.DENSITY_MEDIUM) {
            m_rlContent = (RelativeLayout) mView.findViewById(R.id.mnllMain);
            m_signature = new Signature(getActivity(), null);
            m_signature.setBackgroundColor(Color.WHITE);
            m_rlContent.addView(m_signature, ViewGroup.LayoutParams.FILL_PARENT,
                    ViewGroup.LayoutParams.FILL_PARENT);
            m_view = m_rlContent;

        } else if (density == DisplayMetrics.DENSITY_LOW) {
        } else {
            RelativeLayout li = (RelativeLayout) mView.findViewById(R.id.shampoo);
            li.getLayoutParams().height = 230;
            li.getLayoutParams().width = 600;
            li.requestLayout();

            m_rlContent = (RelativeLayout) mView.findViewById(R.id.mnllMain);
            m_signature = new Signature(getActivity(), null);
            m_signature.setBackgroundColor(Color.WHITE);
            m_rlContent.addView(m_signature, ViewGroup.LayoutParams.FILL_PARENT,
                    ViewGroup.LayoutParams.FILL_PARENT);
            m_view = m_rlContent;

        }

        ImageView img_clear = (ImageView) mView.findViewById(R.id.img_clear);
        img_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                m_signature.clear();

            }
        });

        m_view = m_rlContent;

        dialog.setView(mView);

        return dialog;
    }
}
