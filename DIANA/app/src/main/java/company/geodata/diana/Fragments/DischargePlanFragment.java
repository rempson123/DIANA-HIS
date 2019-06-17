package company.geodata.diana.Fragments;

import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.UUID;

import company.geodata.diana.Dialogs.SignatureDialog;
import company.geodata.diana.Model.DischargePlan;
import company.geodata.diana.Model.Orders;
import company.geodata.diana.Model.Patient;
import company.geodata.diana.PatientCareActivity;
import company.geodata.diana.PatientsListActivity;
import company.geodata.diana.R;

/**
 * Created by jcmate on 6/8/2017.
 */

public class DischargePlanFragment extends Fragment {
    ImageView ivSignature;

    EditText editClinicalAbstract, editFinalDiagnosis, editAfterCare;
    Button btnSave;

    DischargePlan dischargePlan;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Let me be the portable heater that you'll get cold without

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_discharge_plan, container, false);
        ivSignature = (ImageView) v.findViewById(R.id.imageViewSignature);

        editClinicalAbstract = (EditText) v.findViewById(R.id.editClinicalAbstract);
        editFinalDiagnosis = (EditText) v.findViewById(R.id.editFinalDiagnosis);
        editAfterCare = (EditText) v.findViewById(R.id.editAfterCare);
        btnSave = (Button) v.findViewById(R.id.btnSave);

        dischargePlan = DischargePlan.findDischargePlan(getActivity(), PatientCareActivity.patient);
        if (dischargePlan != null) displayDatas();

        ivSignature.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SignatureDialog dialog = new SignatureDialog();
                dialog.newInstance(view);
                dialog.show(getFragmentManager(), "PatientInformation");
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (DischargePlan.findDischargePlan(getActivity(), PatientCareActivity.patient) != null) {
                    DischargePlan dischargeplans = new DischargePlan(
                            PatientCareActivity.patient.getDischargePlan().getDischargePlanID(),
                            PatientCareActivity.patient.getPatientID(),
                            editClinicalAbstract.getText().toString(),
                            editFinalDiagnosis.getText().toString(),
                            PatientCareActivity.patient.getDischargePlan().getPrescriptionList(),
                            editAfterCare.getText().toString(),
                            (ivSignature.getDrawable() != null) ? ((BitmapDrawable) ivSignature.getDrawable()).getBitmap() : null
                    );

                    PatientsListActivity.patients.get(PatientCareActivity.position).setDischargePlan(dischargeplans);
                    DischargePlan.update(getActivity(), dischargeplans);

                } else { //add new
                    DischargePlan dischargeplans = new DischargePlan(
                            UUID.randomUUID().toString(),
                            PatientCareActivity.patient.getPatientID(),
                            editClinicalAbstract.getText().toString(),
                            editFinalDiagnosis.getText().toString(),
                            PatientCareActivity.patient.getDischargePlan().getPrescriptionList(),
                            editAfterCare.getText().toString(),
                            (ivSignature.getDrawable() != null) ? ((BitmapDrawable) ivSignature.getDrawable()).getBitmap() : null
                    );

                    PatientsListActivity.patients.get(PatientCareActivity.position).setDischargePlan(dischargeplans);
                    DischargePlan.create(getActivity(), dischargeplans);
                }

                Toast.makeText(getActivity(), "Saved successfully!", Toast.LENGTH_SHORT).show();
            }
        });

        return v;

    }

    private void displayDatas() {
        editClinicalAbstract.setText(dischargePlan.getClinicalAbstractSummary());
        editFinalDiagnosis.setText(dischargePlan.getFinalDiagnosis());
        editAfterCare.setText(dischargePlan.getAfterCareInstructions());
        ivSignature.setImageBitmap(dischargePlan.getSignature());
    }
}
