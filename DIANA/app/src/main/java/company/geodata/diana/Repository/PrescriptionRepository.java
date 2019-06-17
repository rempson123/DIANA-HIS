package company.geodata.diana.Repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.graphics.PathEffect;

import java.util.ArrayList;
import java.util.List;

import company.geodata.diana.Fragments.PrescriptionFragment;
import company.geodata.diana.Model.Patient;
import company.geodata.diana.Model.Prescription;


/**
 * Created by jcmate on 9/5/2017.
 */

public class PrescriptionRepository extends Repository {
    Context context;
    private String[] AllColumns = {
            Prescription.COL_PRESCRIPTION_ID,
            Patient.COL_PATIENT_ID,
            Prescription.COL_MEDICINE,
            Prescription.COL_GENERIC_NAME,
            Prescription.COL_DOSE,
            Prescription.COL_UNIT,
            Prescription.COL_QUANTITY,
            Prescription.COL_DOSAGE,
            Prescription.COL_NUMBER_OF_TAKE,
            Prescription.COL_TYPE,
            Prescription.COL_REFILL_INSTRUCTION,
            Prescription.COL_WHERE
    };

    public PrescriptionRepository(Context context) {
        super(context);
        this.context = context;
    }

    public Prescription getPrescription(String prescriptionID) {
        Prescription prescription = null;
        Cursor cursor = database.query(SQLiteDBContext.TABLE_PRESCRIPTION, AllColumns, AllColumns[0] + " = ?" , new String[]{prescriptionID}, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            prescription = parseResultSetToPrescription(cursor);
            cursor.moveToNext();
        }
        cursor.close();
        return prescription;
    }
    public List<Prescription> getAllPatientPrescription(Patient patient, String which){
        List<Prescription> prescriptions = new ArrayList<>();
        Cursor cursor = database.query(SQLiteDBContext.TABLE_PRESCRIPTION, AllColumns, AllColumns[11] + " = ? AND " + AllColumns[1] + " = ?", new String[]{which, patient.getPatientID()}, null, null, null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            prescriptions.add(parseResultSetToPrescription(cursor));
            cursor.moveToNext();
        }
        cursor.close();
        return prescriptions;
    }

    public Prescription createPrescription(Prescription prescription) {
        ContentValues prescriptionContentValues = setPrescriptionContentValues(prescription);
        long insertId = database.insert(SQLiteDBContext.TABLE_PRESCRIPTION, null, prescriptionContentValues);
        Cursor cursor = database.query(SQLiteDBContext.TABLE_PRESCRIPTION, AllColumns, AllColumns[0] + " = ?", new String[] {prescription.getPrescriptionID()}, null, null, null);
        cursor.moveToFirst();
        Prescription prescription1 = null;
        if (insertId > 0) {
            prescription1 = parseResultSetToPrescription(cursor);
        }
        cursor.close();
        return prescription1;
    }

    public Prescription updatePrescription(Prescription prescription) {
        ContentValues prescriptionContentValues = setPrescriptionContentValues(prescription);
        int editedId = Integer.parseInt(String.valueOf(database.update(SQLiteDBContext.TABLE_PRESCRIPTION, prescriptionContentValues,
                AllColumns[0] + " = ?", new String[] {prescription.getPrescriptionID()})));

        Prescription prescription1 = null;
        if (editedId > 0) {
            Cursor cursor = database.query(SQLiteDBContext.TABLE_PRESCRIPTION, AllColumns,
                    AllColumns[0] + " = ?", new String[] {prescription.getPrescriptionID()}, null, null, null);
            cursor.moveToFirst();
            prescription1 = parseResultSetToPrescription(cursor);
            cursor.close();
        }

        return  prescription1;
    }

    private Prescription parseResultSetToPrescription(Cursor cursor) {
        Prescription prescription = new Prescription(
                cursor.getString(cursor.getColumnIndex(AllColumns[0])),
                cursor.getString(cursor.getColumnIndex(AllColumns[1])),
                cursor.getString(cursor.getColumnIndex(AllColumns[2])),
                cursor.getString(cursor.getColumnIndex(AllColumns[3])),
                cursor.getInt(cursor.getColumnIndex(AllColumns[4])),
                cursor.getString(cursor.getColumnIndex(AllColumns[5])),
                cursor.getInt(cursor.getColumnIndex(AllColumns[6])),
                cursor.getString(cursor.getColumnIndex(AllColumns[7])),
                cursor.getString(cursor.getColumnIndex(AllColumns[8])),
                cursor.getString(cursor.getColumnIndex(AllColumns[9])),
                cursor.getString(cursor.getColumnIndex(AllColumns[10])),
                cursor.getString(cursor.getColumnIndex(AllColumns[11]))
        );
        return prescription;
    }

    private ContentValues setPrescriptionContentValues(Prescription prescription) {
        ContentValues userValues = new ContentValues();
        userValues.put(AllColumns[0], prescription.getPrescriptionID());
        userValues.put(AllColumns[1], prescription.getPatientID());
        userValues.put(AllColumns[2], prescription.getMedicine());
        userValues.put(AllColumns[3], prescription.getGenericName());
        userValues.put(AllColumns[4], prescription.getDose());
        userValues.put(AllColumns[5], prescription.getUnit());
        userValues.put(AllColumns[6], prescription.getQuantity());
        userValues.put(AllColumns[7], prescription.getDosage());
        userValues.put(AllColumns[8], prescription.getNumberOfTake());
        userValues.put(AllColumns[9], prescription.getType());
        userValues.put(AllColumns[10], prescription.getRefillInstructions());
        userValues.put(AllColumns[11], prescription.getWhere());
        return userValues;
    }
}
