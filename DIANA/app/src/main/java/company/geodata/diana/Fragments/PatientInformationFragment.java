package company.geodata.diana.Fragments;

import android.app.DialogFragment;
import android.content.DialogInterface;
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
import android.widget.TextView;
import android.widget.Toast;

import company.geodata.diana.Dialogs.DropdownDialog;
import company.geodata.diana.Dialogs.SignatureDialog;
import company.geodata.diana.Dialogs.TimePickerDialog;
import company.geodata.diana.Model.Assessment;
import company.geodata.diana.Model.DischargePlan;
import company.geodata.diana.Model.Orders;
import company.geodata.diana.Model.Patient;
import company.geodata.diana.PatientCareActivity;
import company.geodata.diana.PatientsListActivity;
import company.geodata.diana.R;

/**
 * Created by jcmate on 6/1/2017.
 */

public class PatientInformationFragment extends Fragment {
    TextView tvDateOfVisit, tvTime, tvDateOfAdmission, tvPatientName, tvAddress, tvGender, tvAge, tvBloodType,
            tvEMRNo, tvPatientType, tvRoomBed, tvFood, tvLatex, tvMedicine, tvOthers, tvChiefComplaint,
            tvPreviousAssessment, tvDoctorsName;

    EditText editIndications;

    Button btnSave, btnViewReport, btnPost;

    ImageView ivPatientImage, ivSignature;
    EditText editTime1, editTime2, editTime3;
    EditText editTime1PulseRate, editTime2PulseRate, editTime3PulseRate;
    EditText editTime1RespiratoryRate, editTime2RespiratoryRate, editTime3RespiratoryRate;
    EditText editTime1RespiratoryQuality, editTime2RespiratoryQuality, editTime3RespiratoryQuality;
    EditText editTime1Sa02, editTime2Sa02, editTime3Sa02;
    EditText editTime1CapRefill, editTime2CapRefill, editTime3CapRefill;
    EditText editTime1BloodPressure1st, editTime1BloodPressure2nd, editTime2BloodPressure1st,editTime2BloodPressure2nd, editTime3BloodPressure1st,editTime3BloodPressure2nd;
    EditText editTime1Temperature, editTime2Temperature, editTime3Temperature;
    EditText editTime1LeftPupilSize, editTime2LeftPupilSize, editTime3LeftPupilSize;
    EditText editTime1RightPupilSize, editTime2RightPupilSize, editTime3RightPupilSize;
    EditText editTime1LeftPupilReaction, editTime2LeftPupilReaction, editTime3LeftPupilReaction;
    EditText editTime1RightPupilReaction, editTime2RightPupilReaction, editTime3RightPupilReaction;
    EditText editTime1PainScore, editTime2PainScore, editTime3PainScore;
    EditText editTime1BloodGlucoseLevel, editTime2BloodGlucoseLevel, editTime3BloodGlucoseLevel;
    EditText editTime1Eye, editTime2Eye, editTime3Eye;
    EditText editTime1Verbal, editTime2Verbal, editTime3Verbal;
    EditText editTime1Motor, editTime2Motor, editTime3Motor;
    EditText editTime1Total, editTime2Total, editTime3Total;

    Patient patient;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_patient_information, container, false);

        patient = PatientCareActivity.patient;

        initializeViews(v);
        displayDatas();
        setNonFocusableEditText();
        setListeners();

        return v;
    }

    private void displayDatas() {
        tvDateOfVisit.setText(PatientCareActivity.patient.getDateOfVisit());
        tvTime.setText(PatientCareActivity.patient.getTime());
        tvDateOfAdmission.setText(PatientCareActivity.patient.getDateOfAdmission());
        tvPatientName.setText(PatientCareActivity.patient.getPatientName());
        tvAddress.setText(PatientCareActivity.patient.getAddress());
        tvGender.setText(PatientCareActivity.patient.getGender());
        tvAge.setText(String.valueOf(PatientCareActivity.patient.getAge()));
        tvBloodType.setText(PatientCareActivity.patient.getBloodType());
        tvEMRNo.setText(PatientCareActivity.patient.getEMRNo());
        tvPatientType.setText(PatientCareActivity.patient.getPatientType());
        tvRoomBed.setText(PatientCareActivity.patient.getRoomBed());
        tvFood.setText(PatientCareActivity.patient.getAllergyFood());
        tvLatex.setText(PatientCareActivity.patient.getAllergyLatex());
        tvMedicine.setText(PatientCareActivity.patient.getAllergyMedicine());
        tvOthers.setText(PatientCareActivity.patient.getAllergyOthers());
        tvChiefComplaint.setText(PatientCareActivity.patient.getChiefCompliant());
        tvPreviousAssessment.setText(PatientCareActivity.patient.getPreviousAssessment());

        tvDoctorsName.setText(PatientCareActivity.patient.getDoctorsName());

        ivPatientImage.setImageBitmap(PatientCareActivity.patient.getImage());
        editIndications.setText(PatientCareActivity.patient.getIndications());

        ivSignature.setImageBitmap(PatientCareActivity.patient.getSignature());

        String[] arr_time = PatientCareActivity.patient.getVS_time().split(";");
        if (arr_time.length == 3) {
            editTime1.setText(arr_time[0]);
            editTime2.setText(arr_time[1]);
            editTime3.setText(arr_time[2]);
        }

        String[] arr_pulse_rate = PatientCareActivity.patient.getVS_pulseRate().split(";");
        if (arr_pulse_rate.length == 3) {
            editTime1PulseRate.setText(arr_pulse_rate[0]);
            editTime2PulseRate.setText(arr_pulse_rate[1]);
            editTime3PulseRate.setText(arr_pulse_rate[2]);
        }

        String[] arr_respiratory_rate = PatientCareActivity.patient.getVS_respiratoryRate().split(";");
        if (arr_respiratory_rate.length == 3) {
            editTime1RespiratoryRate.setText(arr_respiratory_rate[0]);
            editTime2RespiratoryRate.setText(arr_respiratory_rate[1]);
            editTime3RespiratoryRate.setText(arr_respiratory_rate[2]);
        }

        String[] arr_resp_quality = PatientCareActivity.patient.getVS_respiratoryQuality().split(";");
        if (arr_resp_quality.length == 3) {
            editTime1RespiratoryQuality.setText(arr_resp_quality[0]);
            editTime2RespiratoryQuality.setText(arr_resp_quality[1]);
            editTime3RespiratoryQuality.setText(arr_resp_quality[2]);
        }

        String[] arr_sa02 = PatientCareActivity.patient.getVS_sa02().split(";");
        if (arr_sa02.length == 3) {
            editTime1Sa02.setText(arr_sa02[0]);
            editTime2Sa02.setText(arr_sa02[1]);
            editTime3Sa02.setText(arr_sa02[2]);
        }

        String[] arr_cap_refill = PatientCareActivity.patient.getVS_capRefill().split(";");
        if (arr_cap_refill.length == 3) {
            editTime1CapRefill.setText(arr_cap_refill[0]);
            editTime2CapRefill.setText(arr_cap_refill[1]);
            editTime3CapRefill.setText(arr_cap_refill[2]);
        }

        String[] arr_blood_pressure = PatientCareActivity.patient.getVS_bloodPressure().split(";");
        if (arr_blood_pressure.length == 3) {

            editTime1BloodPressure1st.setText((arr_blood_pressure[0].split("/").length > 0)
                    ? arr_blood_pressure[0].split("/")[0]
                    : "");
            editTime1BloodPressure2nd.setText((arr_blood_pressure[0].split("/").length > 0)
                    ? arr_blood_pressure[0].split("/")[1]
                    : "");

            editTime2BloodPressure1st.setText((arr_blood_pressure[0].split("/").length > 0)
                    ?arr_blood_pressure[1].split("/")[0]
                    : "");

            editTime2BloodPressure2nd.setText((arr_blood_pressure[0].split("/").length > 0)
                    ?arr_blood_pressure[1].split("/")[1]
                    : "");

            editTime3BloodPressure1st.setText((arr_blood_pressure[0].split("/").length > 0)
                    ? arr_blood_pressure[2].split("/")[0]
                    : "");

            editTime3BloodPressure2nd.setText((arr_blood_pressure[0].split("/").length > 0)
                    ?arr_blood_pressure[2].split("/")[1]
                    : "");
        }

        String[] arr_temperature = PatientCareActivity.patient.getVS_temperature().split(";");
        if (arr_temperature.length == 3) {
            editTime1Temperature.setText(arr_temperature[0]);
            editTime2Temperature.setText(arr_temperature[1]);
            editTime3Temperature.setText(arr_temperature[2]);
        }

        String[] arr_left_pupil_size = PatientCareActivity.patient.getVS_leftPupilSize().split(";");
        if (arr_left_pupil_size.length == 3) {
            editTime1LeftPupilSize.setText(arr_left_pupil_size[0]);
            editTime2LeftPupilSize.setText(arr_left_pupil_size[1]);
            editTime3LeftPupilSize.setText(arr_left_pupil_size[2]);
        }

        String[] arr_right_pupil_size = PatientCareActivity.patient.getVS_rightPupilSize().split(";");
        if (arr_right_pupil_size.length == 3) {
            editTime1RightPupilSize.setText(arr_right_pupil_size[0]);
            editTime2RightPupilSize.setText(arr_right_pupil_size[1]);
            editTime3RightPupilSize.setText(arr_right_pupil_size[2]);
        }

        String[] arr_left_pupil_reaction = PatientCareActivity.patient.getVS_leftPupilReaction().split(";");
        if (arr_left_pupil_reaction.length == 3) {
            editTime1LeftPupilReaction.setText(arr_left_pupil_reaction[0]);
            editTime2LeftPupilReaction.setText(arr_left_pupil_reaction[1]);
            editTime3LeftPupilReaction.setText(arr_left_pupil_reaction[2]);
        }

        String[] arr_right_pupil_reaction = PatientCareActivity.patient.getVS_rightPupilReaction().split(";");
        if (arr_right_pupil_reaction.length == 3) {
            editTime1RightPupilReaction.setText(arr_right_pupil_reaction[0]);
            editTime2RightPupilReaction.setText(arr_right_pupil_reaction[1]);
            editTime3RightPupilReaction.setText(arr_right_pupil_reaction[2]);
        }

        String[] arr_pain_score = PatientCareActivity.patient.getVS_painScore().split(";");
        if (arr_pain_score.length == 3) {
            editTime1PainScore.setText(arr_pain_score[0]);
            editTime2PainScore.setText(arr_pain_score[1]);
            editTime3PainScore.setText(arr_pain_score[2]);
        }

        String[] arr_glucose = PatientCareActivity.patient.getVS_bloodGlucoseLevel().split(";");
        if (arr_glucose.length == 3) {
            editTime1BloodGlucoseLevel.setText(arr_glucose[0]);
            editTime2BloodGlucoseLevel.setText(arr_glucose[1]);
            editTime3BloodGlucoseLevel.setText(arr_glucose[2]);
        }

        String[] arr_eye = PatientCareActivity.patient.getVS_eye().split(";");
        if (arr_eye.length == 3) {
            editTime1Eye.setText(arr_eye[0]);
            editTime2Eye.setText(arr_eye[1]);
            editTime3Eye.setText(arr_eye[2]);
        }


        String[] arr_verbal = PatientCareActivity.patient.getVS_verbal().split(";");
        if (arr_verbal.length == 3) {
            editTime1Verbal.setText(arr_verbal[0]);
            editTime2Verbal.setText(arr_verbal[1]);
            editTime3Verbal.setText(arr_verbal[2]);
        }


        String[] arr_motor = PatientCareActivity.patient.getVS_motor().split(";");
        if (arr_motor.length == 3) {
            editTime1Motor.setText(arr_motor[0]);
            editTime2Motor.setText(arr_motor[1]);
            editTime3Motor.setText(arr_motor[2]);
        }


        String[] arr_total = PatientCareActivity.patient.getVS_total().split(";");
        if (arr_total.length == 3) {
            editTime1Total.setText(arr_total[0]);
            editTime2Total.setText(arr_total[1]);
            editTime3Total.setText(arr_total[2]);
        }

    }

    void initializeViews(View v) {

        tvDateOfVisit = (TextView) v.findViewById(R.id.tvDateOfVisit);
        tvTime = (TextView) v.findViewById(R.id.tvTime);
        tvDateOfAdmission = (TextView) v.findViewById(R.id.tvDateOfAdmission);
        tvPatientName = (TextView) v.findViewById(R.id.tvPatientName);
        tvAddress = (TextView) v.findViewById(R.id.tvAddress);
        tvGender = (TextView) v.findViewById(R.id.tvGender);
        tvAge = (TextView) v.findViewById(R.id.tvAge);
        tvBloodType = (TextView) v.findViewById(R.id.tvBloodType);
        tvEMRNo = (TextView) v.findViewById(R.id.tvEmrno);
        tvPatientType = (TextView) v.findViewById(R.id.tvPatientType);
        tvRoomBed = (TextView) v.findViewById(R.id.tvRoomBed);
        tvFood = (TextView) v.findViewById(R.id.tvAllergyFood);
        tvLatex = (TextView) v.findViewById(R.id.tvallergyLatext);
        tvMedicine = (TextView) v.findViewById(R.id.tvAllergyMedicine);
        tvOthers = (TextView) v.findViewById(R.id.tvAllergyOthers);
        tvChiefComplaint = (TextView) v.findViewById(R.id.tvChiefCompliant);
        tvPreviousAssessment = (TextView) v.findViewById(R.id.tvPreviousAssessment);

        tvDoctorsName = (TextView) v.findViewById(R.id.tvDoctor);

        ivPatientImage = (ImageView) v.findViewById(R.id.imageViewPatient);
        editIndications = (EditText) v.findViewById(R.id.editIndications);

        ivSignature = (ImageView) v.findViewById(R.id.imageViewSignature);
        editTime1 = (EditText) v.findViewById(R.id.editTextTime1);
        editTime2 = (EditText) v.findViewById(R.id.editTextTime2);
        editTime3 = (EditText) v.findViewById(R.id.editTextTime3);

        editTime1PulseRate = (EditText) v.findViewById(R.id.editTextTime1PulseRate);
        editTime2PulseRate = (EditText) v.findViewById(R.id.editTextTime2PulseRate);
        editTime3PulseRate = (EditText) v.findViewById(R.id.editTextTime3PulseRate);

        editTime1RespiratoryRate = (EditText) v.findViewById(R.id.editTextTime1RespiratoryRate);
        editTime2RespiratoryRate = (EditText) v.findViewById(R.id.editTextTime2RespiratoryRate);
        editTime3RespiratoryRate = (EditText) v.findViewById(R.id.editTextTime3RespiratoryRate);

        editTime1RespiratoryQuality = (EditText) v.findViewById(R.id.editTextTime1RespiratoryQuality);
        editTime2RespiratoryQuality = (EditText) v.findViewById(R.id.editTextTime2RespiratoryQuality);
        editTime3RespiratoryQuality = (EditText) v.findViewById(R.id.editTextTime3RespiratoryQuality);

        editTime1Sa02 = (EditText) v.findViewById(R.id.editTextTime1Sa02);
        editTime2Sa02 = (EditText) v.findViewById(R.id.editTextTime2Sa02);
        editTime3Sa02 = (EditText) v.findViewById(R.id.editTextTime3Sa02);

        editTime1CapRefill = (EditText) v.findViewById(R.id.editTextTime1CapRefill);
        editTime2CapRefill = (EditText) v.findViewById(R.id.editTextTime2CapRefill);
        editTime3CapRefill = (EditText) v.findViewById(R.id.editTextTime3CapRefill);


        editTime1BloodPressure1st = (EditText) v.findViewById(R.id.editTextTime1BloodPressure1st);
        editTime1BloodPressure2nd = (EditText) v.findViewById(R.id.editTextTime1BloodPressure2nd);
        editTime2BloodPressure1st = (EditText) v.findViewById(R.id.editTextTime2BloodPressure1st);
        editTime2BloodPressure2nd = (EditText) v.findViewById(R.id.editTextTime2BloodPressure2nd);
        editTime3BloodPressure1st = (EditText) v.findViewById(R.id.editTextTime3BloodPressure1st);
        editTime3BloodPressure2nd = (EditText) v.findViewById(R.id.editTextTime3BloodPressure2nd);

        editTime1Temperature = (EditText) v.findViewById(R.id.editTextTime1Temperature);
        editTime2Temperature = (EditText) v.findViewById(R.id.editTextTime2Temperature);
        editTime3Temperature = (EditText) v.findViewById(R.id.editTextTime3Temperature);

        editTime1LeftPupilSize = (EditText) v.findViewById(R.id.editTextTime1LeftPupilSize);
        editTime2LeftPupilSize = (EditText) v.findViewById(R.id.editTextTime2LeftPupilSize);
        editTime3LeftPupilSize = (EditText) v.findViewById(R.id.editTextTime3LeftPupilSize);

        editTime1RightPupilSize = (EditText) v.findViewById(R.id.editTextTime1RightPupilSize);
        editTime2RightPupilSize = (EditText) v.findViewById(R.id.editTextTime2RightPupilSize);
        editTime3RightPupilSize = (EditText) v.findViewById(R.id.editTextTime3RightPupilSize);

        editTime1LeftPupilReaction = (EditText) v.findViewById(R.id.editTextTime1LeftPupilReaction);
        editTime2LeftPupilReaction = (EditText) v.findViewById(R.id.editTextTime2LeftPupilReaction);
        editTime3LeftPupilReaction = (EditText) v.findViewById(R.id.editTextTime3LeftPupilReaction);

        editTime1RightPupilReaction = (EditText) v.findViewById(R.id.editTextTime1RightPupilReaction);
        editTime2RightPupilReaction = (EditText) v.findViewById(R.id.editTextTime2RightPupilReaction);
        editTime3RightPupilReaction = (EditText) v.findViewById(R.id.editTextTime3RightPupilReaction);

        editTime1PainScore = (EditText) v.findViewById(R.id.editTextTime1PainScore);
        editTime2PainScore = (EditText) v.findViewById(R.id.editTextTime2PainScore);
        editTime3PainScore = (EditText) v.findViewById(R.id.editTextTime3PainScore);

        editTime1BloodGlucoseLevel = (EditText) v.findViewById(R.id.editTextTime1BloodGlucose);
        editTime2BloodGlucoseLevel = (EditText) v.findViewById(R.id.editTextTime2BloodGlucose);
        editTime3BloodGlucoseLevel = (EditText) v.findViewById(R.id.editTextTime3BloodGlucose);

        editTime1Eye = (EditText) v.findViewById(R.id.editTextTime1Eye);
        editTime2Eye = (EditText) v.findViewById(R.id.editTextTime2Eye);
        editTime3Eye = (EditText) v.findViewById(R.id.editTextTime3Eye);

        editTime1Verbal = (EditText) v.findViewById(R.id.editTextTime1Verbal);
        editTime2Verbal = (EditText) v.findViewById(R.id.editTextTime2Verbal);
        editTime3Verbal = (EditText) v.findViewById(R.id.editTextTime3Verbal);

        editTime1Motor = (EditText) v.findViewById(R.id.editTextTime1Motor);
        editTime2Motor = (EditText) v.findViewById(R.id.editTextTime2Motor);
        editTime3Motor = (EditText) v.findViewById(R.id.editTextTime3Motor);

        editTime1Total = (EditText) v.findViewById(R.id.editTextTime1Total);
        editTime2Total = (EditText) v.findViewById(R.id.editTextTime2Total);
        editTime3Total = (EditText) v.findViewById(R.id.editTextTime3Total);

        btnSave = (Button) v.findViewById(R.id.btnSave);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Patient updatePatient = new Patient(patient.getPatientID(),
                        patient.getPatientName(),
                        patient.getAddress(),
                        patient.getGender(),
                        patient.getAge(),
                        patient.getBloodType(),
                        patient.getEMRNo(),
                        patient.getPatientType(),
                        patient.getRoomBed(),
                        patient.getAllergyFood(),
                        patient.getAllergyMedicine(),
                        patient.getAllergyLatex(),
                        patient.getAllergyOthers(),
                        patient.getChiefCompliant(),
                        patient.getPreviousAssessment(),
                        editIndications.getText().toString(),
                        patient.getDateOfVisit(),
                        patient.getDateOfAdmission(),
                        patient.getTime(),
                        ((BitmapDrawable) ivPatientImage.getDrawable()).getBitmap(),
                        tvDoctorsName.getText().toString(),
                        ((BitmapDrawable) ivSignature.getDrawable()).getBitmap(),
                        editTime1.getText().toString() + ";" + editTime2.getText().toString() + ";" + editTime3.getText().toString(),
                        editTime1PulseRate.getText().toString() + ";" + editTime2PulseRate.getText().toString() + ";"+ editTime3PulseRate.getText().toString(),
                        editTime1RespiratoryRate.getText().toString() + ";" + editTime2RespiratoryRate.getText().toString() + ";" + editTime3RespiratoryRate.getText().toString(),
                        editTime1RespiratoryQuality.getText().toString() + ";" +editTime2RespiratoryQuality.getText().toString() + ";" + editTime3RespiratoryQuality.getText().toString(),
                        editTime1Sa02.getText().toString() + ";" + editTime2Sa02.getText().toString() + ";" + editTime3Sa02.getText().toString(),
                        editTime1CapRefill.getText().toString() + ";" + editTime2CapRefill.getText().toString() + ";" + editTime3CapRefill.getText().toString(),
                        editTime1BloodPressure1st.getText().toString() + "/" + editTime1BloodPressure2nd.getText().toString() + ";" + editTime2BloodPressure1st.getText().toString() + "/" +editTime2BloodPressure2nd.getText().toString() + ";" + editTime3BloodPressure1st.getText().toString() + "/" +editTime3BloodPressure2nd.getText().toString(),
                        editTime1Temperature.getText().toString() + ";" + editTime2Temperature.getText().toString() + ";" + editTime3Temperature.getText().toString(),
                        editTime1LeftPupilSize.getText().toString() + ";" + editTime2LeftPupilSize.getText().toString() + ";" + editTime3LeftPupilSize.getText().toString(),
                        editTime1RightPupilSize.getText().toString() + ";" + editTime2RightPupilSize.getText().toString() + ";" + editTime3RightPupilSize.getText().toString(),
                        editTime1LeftPupilReaction.getText().toString() + ";" + editTime2LeftPupilReaction.getText().toString() + ";" +editTime3LeftPupilReaction.getText().toString(),
                        editTime1RightPupilReaction.getText().toString() + ";" + editTime2RightPupilReaction.getText().toString() + ";" + editTime3RightPupilReaction.getText().toString(),
                        editTime1PainScore.getText().toString() + ";" + editTime2PainScore.getText().toString() + ";" + editTime3PainScore.getText().toString() ,
                        editTime1BloodGlucoseLevel.getText().toString() + ";" +editTime2BloodGlucoseLevel.getText().toString() + ";" + editTime3BloodGlucoseLevel.getText().toString(),
                        editTime1Eye.getText().toString() + ";" + editTime2Eye.getText().toString() + ";" + editTime3Eye.getText().toString(),
                        editTime1Verbal.getText().toString() + ";" + editTime2Verbal.getText().toString() + ";" + editTime3Verbal.getText().toString(),
                        editTime1Motor.getText().toString() + ";" + editTime2Motor.getText().toString() + ";" + editTime3Motor.getText().toString(),
                        editTime1Total.getText().toString() + ";" + editTime2Total.getText().toString() + ";" + editTime3Total.getText().toString()
                );

                PatientsListActivity.patients.set(PatientCareActivity.position, updatePatient);
                Patient.update(getActivity(), updatePatient);
                Toast.makeText(getActivity(), "Successfully saved!", Toast.LENGTH_SHORT).show();


            }
        });
    }

    void setListeners() {
        ivSignature.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SignatureDialog dialog = new SignatureDialog();
                dialog.newInstance(view);
                dialog.show(getFragmentManager(), "PatientInformation");
            }
        });

        editTime1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimeDialog(v);
            }
        });
        editTime2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimeDialog(v);
            }
        });
        editTime3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimeDialog(v);
            }
        });

        editTime1RespiratoryQuality.setOnClickListener(showDropdownByclick);
        editTime2RespiratoryQuality.setOnClickListener(showDropdownByclick);
        editTime3RespiratoryQuality.setOnClickListener(showDropdownByclick);

        editTime1CapRefill.setOnClickListener(showDropdownByclick);
        editTime2CapRefill.setOnClickListener(showDropdownByclick);
        editTime3CapRefill.setOnClickListener(showDropdownByclick);

        editTime1LeftPupilSize.setOnClickListener(showDropdownByclick);
        editTime2LeftPupilSize.setOnClickListener(showDropdownByclick);
        editTime3LeftPupilSize.setOnClickListener(showDropdownByclick);

        editTime1RightPupilSize.setOnClickListener(showDropdownByclick);
        editTime2RightPupilSize.setOnClickListener(showDropdownByclick);
        editTime3RightPupilSize.setOnClickListener(showDropdownByclick);

        editTime1LeftPupilReaction.setOnClickListener(showDropdownByclick);
        editTime2LeftPupilReaction.setOnClickListener(showDropdownByclick);
        editTime3LeftPupilReaction.setOnClickListener(showDropdownByclick);

        editTime1RightPupilReaction.setOnClickListener(showDropdownByclick);
        editTime2RightPupilReaction.setOnClickListener(showDropdownByclick);
        editTime3RightPupilReaction.setOnClickListener(showDropdownByclick);

        editTime1PainScore.setOnClickListener(showDropdownByclick);
        editTime2PainScore.setOnClickListener(showDropdownByclick);
        editTime3PainScore.setOnClickListener(showDropdownByclick);

        editTime1Eye.setOnClickListener(showDropdownByclick);
        editTime2Eye.setOnClickListener(showDropdownByclick);
        editTime3Eye.setOnClickListener(showDropdownByclick);

        editTime1Verbal.setOnClickListener(showDropdownByclick);
        editTime2Verbal.setOnClickListener(showDropdownByclick);
        editTime3Verbal.setOnClickListener(showDropdownByclick);

        editTime1Motor.setOnClickListener(showDropdownByclick);
        editTime2Motor.setOnClickListener(showDropdownByclick);
        editTime3Motor.setOnClickListener(showDropdownByclick);
    }

    void setNonFocusableEditText() {
        editTime1.setFocusable(false);
        editTime2.setFocusable(false);
        editTime3.setFocusable(false);
        editTime1RespiratoryQuality.setFocusable(false);
        editTime2RespiratoryQuality.setFocusable(false);
        editTime3RespiratoryQuality.setFocusable(false);
        editTime1CapRefill.setFocusable(false);
        editTime2CapRefill.setFocusable(false);
        editTime3CapRefill.setFocusable(false);
        editTime1LeftPupilSize.setFocusable(false);
        editTime2LeftPupilSize.setFocusable(false);
        editTime3LeftPupilSize.setFocusable(false);
        editTime1RightPupilSize.setFocusable(false);
        editTime2RightPupilSize.setFocusable(false);
        editTime3RightPupilSize.setFocusable(false);
        editTime1LeftPupilReaction.setFocusable(false);
        editTime2LeftPupilReaction.setFocusable(false);
        editTime3LeftPupilReaction.setFocusable(false);
        editTime1RightPupilReaction.setFocusable(false);
        editTime2RightPupilReaction.setFocusable(false);
        editTime3RightPupilReaction.setFocusable(false);
        editTime1PainScore.setFocusable(false);
        editTime2PainScore.setFocusable(false);
        editTime3PainScore.setFocusable(false);
        editTime1Eye.setFocusable(false);
        editTime2Eye.setFocusable(false);
        editTime3Eye.setFocusable(false);
        editTime1Verbal.setFocusable(false);
        editTime2Verbal.setFocusable(false);
        editTime3Verbal.setFocusable(false);
        editTime1Motor.setFocusable(false);
        editTime2Motor.setFocusable(false);
        editTime3Motor.setFocusable(false);
    }

    void dropdown(final View v, final String[] array, final String title) {
        DropdownDialog dialog = new DropdownDialog((EditText) v, array);
        dialog.show(getFragmentManager(), title);
    }

    View.OnClickListener showDropdownByclick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            setDropdown(view);
        }
    };

    void setDropdown(View view){
        if (view.getId() == R.id.editTextTime1RespiratoryQuality ||
                view.getId() == R.id.editTextTime2RespiratoryQuality  ||
                view.getId() == R.id.editTextTime3RespiratoryQuality ) {
            dropdown(view, getResources().getStringArray(R.array.array_respiratory_quality), "Respiratory Quality");
        } else if (view.getId() == R.id.editTextTime1CapRefill ||
                view.getId() == R.id.editTextTime2CapRefill  ||
                view.getId() == R.id.editTextTime3CapRefill ) {
            dropdown(view, getResources().getStringArray(R.array.array_cap_refill), "Cap Refill");
        } else if (view.getId() == R.id.editTextTime1LeftPupilSize ||
                view.getId() == R.id.editTextTime2LeftPupilSize  ||
                view.getId() == R.id.editTextTime3LeftPupilSize ){
            dropdown(view, getResources().getStringArray(R.array.array_pupils_size), "Left Pupil Size");
        } else if (view.getId() == R.id.editTextTime1RightPupilSize ||
                view.getId() == R.id.editTextTime2RightPupilSize  ||
                view.getId() == R.id.editTextTime3RightPupilSize ){
            dropdown(view, getResources().getStringArray(R.array.array_pupils_size), "Right Pupil Size");
        } else if (view.getId() == R.id.editTextTime1LeftPupilReaction ||
                view.getId() == R.id.editTextTime2LeftPupilReaction  ||
                view.getId() == R.id.editTextTime3LeftPupilReaction ){
            dropdown(view, getResources().getStringArray(R.array.array_pupils_size), "Left Pupil Reaction");
        } else if (view.getId() == R.id.editTextTime1RightPupilReaction ||
                view.getId() == R.id.editTextTime2RightPupilReaction  ||
                view.getId() == R.id.editTextTime3RightPupilReaction ){
            dropdown(view, getResources().getStringArray(R.array.array_pupils_size), "Right Pupil Reaction");
        } else if (view.getId() == R.id.editTextTime1PainScore ||
                view.getId() == R.id.editTextTime2PainScore  ||
                view.getId() == R.id.editTextTime3PainScore ){
            dropdown(view, getResources().getStringArray(R.array.array_pain_score), "Pain Score");
        } else if (view.getId() == R.id.editTextTime1Eye ||
                view.getId() == R.id.editTextTime2Eye  ||
                view.getId() == R.id.editTextTime3Eye ){
            dropdown(view, getResources().getStringArray(R.array.array_eye_glascow_coma_scale), "Glascow Coma Scale");
        } else if (view.getId() == R.id.editTextTime1Verbal ||
                view.getId() == R.id.editTextTime2Verbal  ||
                view.getId() == R.id.editTextTime3Verbal ){
            dropdown(view, getResources().getStringArray(R.array.array_verbal_glascow_coma_scale), "Glascow Coma Scale");
        } else if (view.getId() == R.id.editTextTime1Motor ||
                view.getId() == R.id.editTextTime2Motor  ||
                view.getId() == R.id.editTextTime3Motor ){
            dropdown(view, getResources().getStringArray(R.array.array_motor_glascow_coma_scale), "Glascow Coma Scale");
        }
    }


    private void showTimeDialog(View v) {
        TimePickerDialog timePickerDialog = new TimePickerDialog();
        timePickerDialog.newInstance(v, ((EditText) v).getText().toString());
        timePickerDialog.show(getFragmentManager(), "Time Picker");
    }
}
