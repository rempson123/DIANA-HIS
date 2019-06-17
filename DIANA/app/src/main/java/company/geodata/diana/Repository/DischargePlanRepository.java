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
import company.geodata.diana.Model.DischargePlan;
import company.geodata.diana.Model.Orders;
import company.geodata.diana.Model.Patient;
import company.geodata.diana.Model.Prescription;
import company.geodata.diana.PatientCareActivity;

/**
 * Created by jcmate on 9/6/2017.
 */

public class DischargePlanRepository extends Repository {
    Context context;
    private String[] AllColumns = {
            DischargePlan.COL_DISCHARGE_PLAN_ID,
            Patient.COL_PATIENT_ID,
            DischargePlan.COL_CLINICAL_ABSTRACT_SUMMARY,
            DischargePlan.COL_FINAL_DIAGNOSIS,
            DischargePlan.COL_AFTER_CARE_INSTRUCTIONS,
            DischargePlan.COL_SIGNATURE
    };

    public DischargePlanRepository(Context context) {
        super(context);
        this.context = context;
    }

    public DischargePlan getDischargePlan(String patientID) {
        DischargePlan dischargePlan = null;
        Cursor cursor = database.query(SQLiteDBContext.TABLE_DISCHARGE_PLAN, AllColumns, AllColumns[1] + " = ?" , new String[]{patientID}, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            dischargePlan = parseResultSetToDischargePlan(cursor);
            cursor.moveToNext();
        }
        cursor.close();
        return dischargePlan;
    }

    public DischargePlan createdischargePlan(DischargePlan dischargePlan) {
        ContentValues dischargePlanContentValues = setDischargePlanContentValues(dischargePlan);
        long insertId = database.insert(SQLiteDBContext.TABLE_DISCHARGE_PLAN, null, dischargePlanContentValues);
        Cursor cursor = database.query(SQLiteDBContext.TABLE_DISCHARGE_PLAN, AllColumns, AllColumns[0] + " = ?", new String[] {dischargePlan.getDischargePlanID()}, null, null, null);
        cursor.moveToFirst();
        DischargePlan dischargePlan1 = null;
        if (insertId > 0) {
            dischargePlan1 = parseResultSetToDischargePlan(cursor);
        }
        cursor.close();
        return dischargePlan1;
    }

    public DischargePlan updateDischargePlan(DischargePlan dischargePlan) {
        ContentValues dischargePlanContentValues = setDischargePlanContentValues(dischargePlan);
        int editedId = Integer.parseInt(String.valueOf(database.update(SQLiteDBContext.TABLE_DISCHARGE_PLAN, dischargePlanContentValues,
                AllColumns[1] + " = ?", new String[] {dischargePlan.getPatientID()})));

        DischargePlan dischargePlan1 = null;
        if (editedId > 0) {
            Cursor cursor = database.query(SQLiteDBContext.TABLE_DISCHARGE_PLAN, AllColumns,
                    AllColumns[1] + " = ?", new String[] {dischargePlan.getPatientID()}, null, null, null);
            cursor.moveToFirst();
            dischargePlan1 = parseResultSetToDischargePlan(cursor);
            cursor.close();
        }

        return  dischargePlan1;
    }


    private DischargePlan parseResultSetToDischargePlan(Cursor cursor) {
        DischargePlan dischargePlan = new DischargePlan(
                cursor.getString(cursor.getColumnIndex(AllColumns[0])),
                cursor.getString(cursor.getColumnIndex(AllColumns[1])),
                cursor.getString(cursor.getColumnIndex(AllColumns[2])),
                cursor.getString(cursor.getColumnIndex(AllColumns[3])),
                (Prescription.findAllPatientPrescription(context, PatientCareActivity.patient, PrescriptionFragment.DISCHARGE_PLAN).size() > 0) ? Prescription.findAllPatientPrescription(context, PatientCareActivity.patient, PrescriptionFragment.DISCHARGE_PLAN) : new ArrayList<Prescription>(),
                cursor.getString(cursor.getColumnIndex(AllColumns[4])),
                (cursor.getBlob(cursor.getColumnIndex(AllColumns[5])) != null) ? getBitmap(cursor.getBlob(cursor.getColumnIndex(AllColumns[5]))) : null
        );
        return dischargePlan;
    }
    private ContentValues setDischargePlanContentValues(DischargePlan dischargePlan) {
        ContentValues userValues = new ContentValues();
        userValues.put(AllColumns[0], dischargePlan.getDischargePlanID());
        userValues.put(AllColumns[1], dischargePlan.getPatientID());
        userValues.put(AllColumns[2], dischargePlan.getClinicalAbstractSummary());
        userValues.put(AllColumns[3], dischargePlan.getFinalDiagnosis());
        userValues.put(AllColumns[4], dischargePlan.getAfterCareInstructions());
        userValues.put(AllColumns[5], (dischargePlan.getSignature() != null) ? getBytes(dischargePlan.getSignature()) : null);
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
