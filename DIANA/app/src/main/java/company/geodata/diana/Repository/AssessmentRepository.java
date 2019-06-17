package company.geodata.diana.Repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

import company.geodata.diana.Fragments.PrescriptionFragment;
import company.geodata.diana.Model.Assessment;
import company.geodata.diana.Model.DischargePlan;
import company.geodata.diana.Model.Patient;
import company.geodata.diana.Model.Prescription;
import company.geodata.diana.PatientCareActivity;

/**
 * Created by jcmate on 9/6/2017.
 */

public class AssessmentRepository extends Repository {
    Context context;
    private String[] AllColumns = {
            Assessment.COL_ASSESSMENT_ID,
            Patient.COL_PATIENT_ID,
            Assessment.COL_SUBGJECTIVE,
            Assessment.COL_OBJECTIVE,
            Assessment.COL_ASSESSMENT,
            Assessment.COL_PLAN,
            Assessment.COL_DIAGNOSIS_INITIAL,
            Assessment.COL_DIAGNOSIS_ADMITTING,
            Assessment.COL_SIGNATURE
    };

    public AssessmentRepository(Context context) {
        super(context);
        this.context = context;
    }

    public Assessment getAssessment(String patientID) {
        Assessment assessment = null;
        Cursor cursor = database.query(SQLiteDBContext.TABLE_ASSESSMENT, AllColumns, AllColumns[1] + " = ?" , new String[]{patientID}, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            assessment = parseResultSetToAssessment(cursor);
            cursor.moveToNext();
        }
        cursor.close();
        return assessment;
    }

    public Assessment createAssessment(Assessment assessment) {
        ContentValues assessmentPlanContentValues = setAssessmentContentValues(assessment);
        long insertId = database.insert(SQLiteDBContext.TABLE_ASSESSMENT, null, assessmentPlanContentValues);
        Cursor cursor = database.query(SQLiteDBContext.TABLE_ASSESSMENT, AllColumns, AllColumns[0] + " = ?", new String[] {assessment.getAssessmentID()}, null, null, null);
        cursor.moveToFirst();
        Assessment assessment1 = null;
        if (insertId > 0) {
            assessment1 = parseResultSetToAssessment(cursor);
        }
        cursor.close();
        return assessment1;
    }

    public Assessment updateAssessment(Assessment assessment) {
        ContentValues assessmentContentValues = setAssessmentContentValues(assessment);
        int editedId = Integer.parseInt(String.valueOf(database.update(SQLiteDBContext.TABLE_ASSESSMENT, assessmentContentValues,
                AllColumns[1] + " = ?", new String[] {assessment.getPatientID()})));

        Assessment assessment1 = null;
        if (editedId > 0) {
            Cursor cursor = database.query(SQLiteDBContext.TABLE_ASSESSMENT, AllColumns,
                    AllColumns[1] + " = ?", new String[] {assessment.getPatientID()}, null, null, null);
            cursor.moveToFirst();
            assessment1 = parseResultSetToAssessment(cursor);
            cursor.close();
        }

        return  assessment1;
    }

    private Assessment parseResultSetToAssessment(Cursor cursor) {
        Assessment assessment = new Assessment(
                cursor.getString(cursor.getColumnIndex(AllColumns[0])),
                cursor.getString(cursor.getColumnIndex(AllColumns[1])),
                cursor.getString(cursor.getColumnIndex(AllColumns[2])),
                cursor.getString(cursor.getColumnIndex(AllColumns[3])),
                cursor.getString(cursor.getColumnIndex(AllColumns[4])),
                cursor.getString(cursor.getColumnIndex(AllColumns[5])),
                cursor.getString(cursor.getColumnIndex(AllColumns[6])),
                cursor.getString(cursor.getColumnIndex(AllColumns[7])),
                (cursor.getBlob(cursor.getColumnIndex(AllColumns[8])) != null) ? getBitmap(cursor.getBlob(cursor.getColumnIndex(AllColumns[8]))) : null

        );
        return assessment;
    }
    private ContentValues setAssessmentContentValues(Assessment assessment) {
        ContentValues userValues = new ContentValues();
        userValues.put(AllColumns[0], assessment.getAssessmentID());
        userValues.put(AllColumns[1], assessment.getPatientID());
        userValues.put(AllColumns[2], assessment.getSubjective());
        userValues.put(AllColumns[3], assessment.getObjective());
        userValues.put(AllColumns[4], assessment.getAssessment());
        userValues.put(AllColumns[5], assessment.getPlan());
        userValues.put(AllColumns[6], assessment.getDiagnosisInitial());
        userValues.put(AllColumns[7], assessment.getDiagnosisAdmitting());
        userValues.put(AllColumns[8], (assessment.getSignature() != null) ? getBytes(assessment.getSignature()) : null);
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
