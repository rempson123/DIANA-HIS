package company.geodata.diana.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import company.geodata.diana.Model.Patient;
import company.geodata.diana.R;

/**
 * Created by jcmate on 5/29/2017.
 */

public class PatientListAdapter extends ArrayAdapter<Patient> {
    Context context;
    List<Patient> patients;
    LayoutInflater inflater;

    public PatientListAdapter(Context context, List<Patient> patients) {
        super(context, R.layout.patients_list_row, patients);
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.patients = patients;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Patient patient = patients.get(position);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.patients_list_row,parent,false);

        ImageView patientImage = (ImageView) view.findViewById(R.id.imageViewPatient);
        TextView patientName = (TextView) view.findViewById(R.id.textViewName);

        patientImage.setImageBitmap(patient.getImage());
        patientName.setText(patient.getPatientName());
        return view;
    }
}

