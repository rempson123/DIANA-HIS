package company.geodata.diana.Repository;

import android.content.ContentValues;
import android.content.Context;
import android.content.pm.PackageItemInfo;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

import company.geodata.diana.Model.Assessment;
import company.geodata.diana.Model.DischargePlan;
import company.geodata.diana.Model.Orders;
import company.geodata.diana.Model.Patient;
import company.geodata.diana.PatientCareActivity;

/**
 * Created by jcmate on 9/4/2017.
 */

public class PatientRepository extends Repository {
    Context context;
    private String[] AllColumns = {
            Patient.COL_PATIENT_ID,
                    Patient.COL_NAME,
                    Patient.COL_ADDRESS,
                    Patient.COL_GENDER,
                    Patient.COL_AGE,
                    Patient.COL_BLOOD_TYPE,
                    Patient.COL_EMRNO,
                    Patient.COL_PATIENT_TYPE,
                    Patient.COL_ROOM_BED,
                    Patient.COL_ALLERGY_FOOD,
                    Patient.COL_ALLERGY_MEDICINE,
                    Patient.COL_ALLERGY_LATEX,
                    Patient.COL_ALLERGY_OTHERS,
                    Patient.COL_CHIEF_COMPLIANT,
                    Patient.COL_PREVIOUS_ASSESSMENT,
                    Patient.COL_INDICATIONS,
                    Patient.COL_DATE_OF_VISIT,
                    Patient.COL_DATE_OF_ADMISSION,
                    Patient.COL_TIME,
                    Patient.COL_IMAGE,
                    Patient.COL_DOCTORS_NAME,
                    Patient.COL_SIGNATURE,
                    Patient.COL_VS_TIME,
                    Patient.COL_VS_PULSE_RATE,
                    Patient.COL_VS_RESPIRATORY_RATE,
                    Patient.COL_VS_RESPIRATORY_QUALITY,
                    Patient.COL_VS_SA02,
                    Patient.COL_VS_CAP_REFILL,
                    Patient.COL_VS_BLOOD_PRESSURE,
                    Patient.COL_VS_TEMPERATURE,
                    Patient.COL_VS_LEFT_PUPIL_SIZE,
                    Patient.COL_VS_RIGHT_PUPIL_SIZE,
                    Patient.COL_VS_LEFT_PUPIL_REACTION,
                    Patient.COL_VS_RIGHT_PUPIL_REACTION,
                    Patient.COL_VS_PAIN_SCORE,
                    Patient.COL_VS_BLOOD_GLUCOSE_LEVEL,
                    Patient.COL_VS_EYE,
                    Patient.COL_VS_VERBAL,
                    Patient.COL_VS_MOTOR,
                    Patient.COL_VS_TOTAL
    };

    public PatientRepository(Context context) {
        super(context);
        this.context = context;
    }

    public Patient getPatient(String patientID) {
        Patient patient = null;
        Cursor cursor = database.query(SQLiteDBContext.TABLE_PATIENT_INFO, AllColumns, AllColumns[0] + " = ?" , new String[]{patientID}, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            patient = parseResultSetToPatient(cursor);
            cursor.moveToNext();
        }
        cursor.close();
        return patient;
    }
    public List<Patient> getAllPatients(){
        List<Patient> patients = new ArrayList<>();
        Cursor cursor = database.query(SQLiteDBContext.TABLE_PATIENT_INFO, AllColumns, null, null, null, null, null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            patients.add(parseResultSetToPatient(cursor));
            cursor.moveToNext();
        }
        cursor.close();
        return patients;
    }

    public Patient createPatient(Patient patient) {
        ContentValues patientContentValues = setPatientContentValues(patient);
        long insertId = database.insert(SQLiteDBContext.TABLE_PATIENT_INFO, null, patientContentValues);
        Cursor cursor = database.query(SQLiteDBContext.TABLE_PATIENT_INFO, AllColumns, AllColumns[0] + " = ?", new String[] {patient.getPatientID()}, null, null, null);
        cursor.moveToFirst();
        Patient patient1 = null;
        if (insertId > 0) {
            patient1 = parseResultSetToPatient(cursor);
        }
        cursor.close();
        return patient1;
    }

    public Patient updatePatient(Patient patient) {
        ContentValues patientContentValues = setPatientContentValues(patient);
        int editedId = Integer.parseInt(String.valueOf(database.update(SQLiteDBContext.TABLE_PATIENT_INFO, patientContentValues,
                AllColumns[0] + " = ?", new String[] {patient.getPatientID()})));

        Patient patient1 = null;
        if (editedId > 0) {
            Cursor cursor = database.query(SQLiteDBContext.TABLE_PATIENT_INFO, AllColumns,
                    AllColumns[0] + " = ?", new String[] {patient.getPatientID()}, null, null, null);
            cursor.moveToFirst();
            patient1 = parseResultSetToPatient(cursor);
            cursor.close();
        }

        return  patient1;
    }

    private Patient parseResultSetToPatient(Cursor cursor) {
        Patient patient = new Patient(
                cursor.getString(cursor.getColumnIndex(AllColumns[0])),
                cursor.getString(cursor.getColumnIndex(AllColumns[1])),
                cursor.getString(cursor.getColumnIndex(AllColumns[2])),
                cursor.getString(cursor.getColumnIndex(AllColumns[3])),
                cursor.getInt(cursor.getColumnIndex(AllColumns[4])),
                cursor.getString(cursor.getColumnIndex(AllColumns[5])),
                cursor.getString(cursor.getColumnIndex(AllColumns[6])),
                cursor.getString(cursor.getColumnIndex(AllColumns[7])),
                cursor.getString(cursor.getColumnIndex(AllColumns[8])),
                cursor.getString(cursor.getColumnIndex(AllColumns[9])),
                cursor.getString(cursor.getColumnIndex(AllColumns[10])),
                cursor.getString(cursor.getColumnIndex(AllColumns[11])),
                cursor.getString(cursor.getColumnIndex(AllColumns[12])),
                cursor.getString(cursor.getColumnIndex(AllColumns[13])),
                cursor.getString(cursor.getColumnIndex(AllColumns[14])),
                cursor.getString(cursor.getColumnIndex(AllColumns[15])),
                cursor.getString(cursor.getColumnIndex(AllColumns[16])),
                cursor.getString(cursor.getColumnIndex(AllColumns[17])),
                cursor.getString(cursor.getColumnIndex(AllColumns[18])),
                (cursor.getBlob(cursor.getColumnIndex(AllColumns[19])) != null) ? getBitmap(cursor.getBlob(cursor.getColumnIndex(AllColumns[19]))) : null,
                cursor.getString(cursor.getColumnIndex(AllColumns[20])),
                (cursor.getBlob(cursor.getColumnIndex(AllColumns[21])) != null) ? getBitmap(cursor.getBlob(cursor.getColumnIndex(AllColumns[21]))) : null,
                cursor.getString(cursor.getColumnIndex(AllColumns[22])),
                cursor.getString(cursor.getColumnIndex(AllColumns[23])),
                cursor.getString(cursor.getColumnIndex(AllColumns[24])),
                cursor.getString(cursor.getColumnIndex(AllColumns[25])),
                cursor.getString(cursor.getColumnIndex(AllColumns[26])),
                cursor.getString(cursor.getColumnIndex(AllColumns[27])),
                cursor.getString(cursor.getColumnIndex(AllColumns[28])),
                cursor.getString(cursor.getColumnIndex(AllColumns[29])),
                cursor.getString(cursor.getColumnIndex(AllColumns[30])),
                cursor.getString(cursor.getColumnIndex(AllColumns[31])),
                cursor.getString(cursor.getColumnIndex(AllColumns[32])),
                cursor.getString(cursor.getColumnIndex(AllColumns[33])),
                cursor.getString(cursor.getColumnIndex(AllColumns[34])),
                cursor.getString(cursor.getColumnIndex(AllColumns[35])),
                cursor.getString(cursor.getColumnIndex(AllColumns[36])),
                cursor.getString(cursor.getColumnIndex(AllColumns[37])),
                cursor.getString(cursor.getColumnIndex(AllColumns[38])),
                cursor.getString(cursor.getColumnIndex(AllColumns[39]))
        );

        return patient;
    }

    private ContentValues setPatientContentValues(Patient patient) {
        ContentValues userValues = new ContentValues();
        userValues.put(AllColumns[0], patient.getPatientID());
        userValues.put(AllColumns[1], patient.getPatientName());
        userValues.put(AllColumns[2], patient.getAddress());
        userValues.put(AllColumns[3], patient.getGender());
        userValues.put(AllColumns[4], patient.getAge());
        userValues.put(AllColumns[5], patient.getBloodType());
        userValues.put(AllColumns[6], patient.getEMRNo());
        userValues.put(AllColumns[7], patient.getPatientType());
        userValues.put(AllColumns[8], patient.getRoomBed());
        userValues.put(AllColumns[9], patient.getAllergyFood());
        userValues.put(AllColumns[10], patient.getAllergyMedicine());
        userValues.put(AllColumns[11], patient.getAllergyLatex());
        userValues.put(AllColumns[12], patient.getAllergyOthers());
        userValues.put(AllColumns[13], patient.getChiefCompliant());
        userValues.put(AllColumns[14], patient.getPreviousAssessment());
        userValues.put(AllColumns[15], patient.getIndications());
        userValues.put(AllColumns[16], patient.getDateOfVisit());
        userValues.put(AllColumns[17], patient.getDateOfAdmission());
        userValues.put(AllColumns[18], patient.getTime());
        userValues.put(AllColumns[19], (patient.getImage() != null) ? getBytes(patient.getImage()) : null);
        userValues.put(AllColumns[20], patient.getDoctorsName());
        userValues.put(AllColumns[21], (patient.getSignature() != null) ? getBytes(patient.getSignature()) : null);
        userValues.put(AllColumns[22], patient.getVS_time());
        userValues.put(AllColumns[23], patient.getVS_pulseRate());
        userValues.put(AllColumns[24], patient.getVS_respiratoryRate());
        userValues.put(AllColumns[25], patient.getVS_respiratoryQuality());
        userValues.put(AllColumns[26], patient.getVS_sa02());
        userValues.put(AllColumns[27], patient.getVS_capRefill());
        userValues.put(AllColumns[28], patient.getVS_bloodPressure());
        userValues.put(AllColumns[29], patient.getVS_temperature());
        userValues.put(AllColumns[30], patient.getVS_leftPupilSize());
        userValues.put(AllColumns[31], patient.getVS_rightPupilSize());
        userValues.put(AllColumns[32], patient.getVS_leftPupilReaction());
        userValues.put(AllColumns[33], patient.getVS_rightPupilReaction());
        userValues.put(AllColumns[34], patient.getVS_painScore());
        userValues.put(AllColumns[35], patient.getVS_bloodGlucoseLevel());
        userValues.put(AllColumns[36], patient.getVS_eye());
        userValues.put(AllColumns[37], patient.getVS_verbal());
        userValues.put(AllColumns[38], patient.getVS_motor());
        userValues.put(AllColumns[39], patient.getVS_total());
        return userValues;
    }

    public static byte[] getBytes(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, stream);
        return stream.toByteArray();
    }

    public static Bitmap getBitmap(byte[] blob) {
        Bitmap bitmap = null;
        ByteArrayInputStream inputStream = new ByteArrayInputStream(blob);
        bitmap = BitmapFactory.decodeStream(inputStream);
        return bitmap;
    }
}
