package company.geodata.diana.Fragments;

import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;

import java.util.UUID;

import company.geodata.diana.Dialogs.SignatureDialog;
import company.geodata.diana.Model.Assessment;
import company.geodata.diana.Model.DischargePlan;
import company.geodata.diana.PatientCareActivity;
import company.geodata.diana.PatientsListActivity;
import company.geodata.diana.R;

/**
 * Created by jcmate on 6/5/2017.
 */

public class AssessmentFragment extends Fragment implements View.OnClickListener {
    ImageView ivSignature;
    EditText editSubjective, editObjective, editAssessment, editPlan, editInitial, editAdmitting;
    RadioButton rbInitial;
    RadioButton rbAdmitting;

    Button btnSave;

    Assessment assessment;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_assessment, container, false);

        ivSignature = (ImageView) v.findViewById(R.id.imageViewSignature);
        editSubjective = (EditText) v.findViewById(R.id.editSubjective);
        editObjective = (EditText) v.findViewById(R.id.editObjective);
        editAssessment = (EditText) v.findViewById(R.id.editAssessment);
        editInitial = (EditText) v.findViewById(R.id.editInitial);
        editAdmitting = (EditText) v.findViewById(R.id.editAdmitting);
        editPlan = (EditText) v.findViewById(R.id.editPlan);
        rbInitial = (RadioButton) v.findViewById(R.id.rbInitial);
        rbAdmitting = (RadioButton) v.findViewById(R.id.rbAdmitting);
        btnSave = (Button) v.findViewById(R.id.btnSave);

        rbInitial.setOnClickListener(this);
        rbAdmitting.setOnClickListener(this);

        rbInitial.performClick();

        assessment = Assessment.findAssessment(getActivity(), PatientCareActivity.patient);
        if (assessment != null) displayDatas();

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
                if (Assessment.findAssessment(getActivity(), PatientCareActivity.patient) != null) {
                    Assessment assessments = new Assessment(
                            PatientCareActivity.patient.getDischargePlan().getDischargePlanID(),
                            PatientCareActivity.patient.getPatientID(),
                            editSubjective.getText().toString(),
                            editObjective.getText().toString(),
                            editAssessment.getText().toString(),
                            editPlan.getText().toString(),
                            editInitial.getText().toString(),
                            editAdmitting.getText().toString(),
                            (ivSignature.getDrawable() != null) ? ((BitmapDrawable) ivSignature.getDrawable()).getBitmap() : null
                    );

                    PatientsListActivity.patients.get(PatientCareActivity.position).setAssessment(assessments);
                    Assessment.update(getActivity(), assessments);

                } else { //add new
                    Assessment assessments = new Assessment(
                            UUID.randomUUID().toString(),
                            PatientCareActivity.patient.getPatientID(),
                            editSubjective.getText().toString(),
                            editObjective.getText().toString(),
                            editAssessment.getText().toString(),
                            editPlan.getText().toString(),
                            editInitial.getText().toString(),
                            editAdmitting.getText().toString(),
                            (ivSignature.getDrawable() != null) ? ((BitmapDrawable) ivSignature.getDrawable()).getBitmap() : null
                    );

                    PatientsListActivity.patients.get(PatientCareActivity.position).setAssessment(assessments);
                    Assessment.create(getActivity(), assessments);
                }

                Toast.makeText(getActivity(), "Saved successfully!", Toast.LENGTH_SHORT).show();
            }
        });

        return v;
    }

    private void displayDatas() {
        editSubjective.setText(assessment.getSubjective());
        editObjective.setText(assessment.getObjective());
        editAssessment.setText(assessment.getAssessment());
        editInitial.setText(assessment.getDiagnosisInitial());
        editAdmitting.setText(assessment.getDiagnosisAdmitting());
        editPlan.setText(assessment.getPlan());
        ivSignature.setImageBitmap(assessment.getSignature());
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.rbInitial) {
            rbAdmitting.setChecked(false);
            editAdmitting.setEnabled(false);
            editInitial.setEnabled(true);
            editAdmitting.setText("");
        }

        if (view.getId() == R.id.rbAdmitting) {
            rbInitial.setChecked(false);
            editAdmitting.setEnabled(true);
            editInitial.setEnabled(false);
            editInitial.setText("");
        }

    }
}
