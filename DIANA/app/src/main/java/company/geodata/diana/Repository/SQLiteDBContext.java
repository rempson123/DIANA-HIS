package company.geodata.diana.Repository;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import company.geodata.diana.Model.Assessment;
import company.geodata.diana.Model.DischargePlan;
import company.geodata.diana.Model.Orders;
import company.geodata.diana.Model.Patient;
import company.geodata.diana.Model.Prescription;

/**
 * Created by jcmate on 9/4/2017.
 */

public class SQLiteDBContext extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "DIANAHIS.db";
    public static final int DATABASE_VERSION = 4;

    public static  final String TABLE_PATIENT_INFO = "patient_information";
    public static  final String TABLE_PRESCRIPTION = "prescription";
    public static  final String TABLE_ORDERS = "orders";
    public static  final String TABLE_ASSESSMENT = "assessment";
    public static  final String TABLE_DISCHARGE_PLAN = "discharge_plan";

    private static final String CREATE_TABLE_PATIENT_INFO = "CREATE TABLE "+ TABLE_PATIENT_INFO +
            " (" +
            Patient.COL_PATIENT_ID + " VARCHAR(32) NOT NULL PRIMARY KEY, " +
            Patient.COL_NAME + " VARCHAR(50)," +
            Patient.COL_ADDRESS + " VARCHAR(50)," +
            Patient.COL_GENDER + " VARCHAR(50)," +
            Patient.COL_AGE + " VARCHAR(50), " +
            Patient.COL_BLOOD_TYPE + " VARCHAR(50)," +
            Patient.COL_EMRNO + " VARCHAR(50)," +
            Patient.COL_PATIENT_TYPE + " VARCHAR(50)," +
            Patient.COL_ROOM_BED + " VARCHAR(50)," +
            Patient.COL_ALLERGY_FOOD + " VARCHAR(50)," +
            Patient.COL_ALLERGY_MEDICINE + " VARCHAR(50)," +
            Patient.COL_ALLERGY_LATEX +" VARCHAR(50)," +
            Patient.COL_ALLERGY_OTHERS +" VARCHAR(50)," +
            Patient.COL_CHIEF_COMPLIANT +" VARCHAR(50)," +
            Patient.COL_PREVIOUS_ASSESSMENT +" VARCHAR(50)," +
            Patient.COL_INDICATIONS +" VARCHAR(50)," +
            Patient.COL_DATE_OF_VISIT +" VARCHAR(50)," +
            Patient.COL_DATE_OF_ADMISSION +" VARCHAR(50)," +
            Patient.COL_TIME +" VARCHAR(50)," +
            Patient.COL_IMAGE +" BLOB," +
            Patient.COL_DOCTORS_NAME +" VARCHAR(50)," +
            Patient.COL_SIGNATURE +" BLOB," +
            Patient.COL_VS_TIME +" VARCHAR(50)," +
            Patient.COL_VS_PULSE_RATE +" VARCHAR(50)," +
            Patient.COL_VS_RESPIRATORY_RATE +" VARCHAR(50)," +
            Patient.COL_VS_RESPIRATORY_QUALITY +" VARCHAR(50)," +
            Patient.COL_VS_SA02 +" VARCHAR(50)," +
            Patient.COL_VS_CAP_REFILL +" VARCHAR(50)," +
            Patient.COL_VS_BLOOD_PRESSURE +" VARCHAR(50)," +
            Patient.COL_VS_TEMPERATURE +" VARCHAR(50)," +
            Patient.COL_VS_LEFT_PUPIL_SIZE +" VARCHAR(50)," +
            Patient.COL_VS_RIGHT_PUPIL_SIZE +" VARCHAR(50)," +
            Patient.COL_VS_LEFT_PUPIL_REACTION +" VARCHAR(50)," +
            Patient.COL_VS_RIGHT_PUPIL_REACTION +" VARCHAR(50)," +
            Patient.COL_VS_PAIN_SCORE +" VARCHAR(50)," +
            Patient.COL_VS_BLOOD_GLUCOSE_LEVEL +" VARCHAR(50)," +
            Patient.COL_VS_EYE +" VARCHAR(50)," +
            Patient.COL_VS_VERBAL +" VARCHAR(50)," +
            Patient.COL_VS_MOTOR +" VARCHAR(50)," +
            Patient.COL_VS_TOTAL +" VARCHAR(50)" +
            " )";

    private static final String CREATE_TABLE_PRESCRIPTION = "CREATE TABLE "+ TABLE_PRESCRIPTION +
            " (" +
            Prescription.COL_PRESCRIPTION_ID + " VARCHAR(32) NOT NULL PRIMARY KEY, " +
            Patient.COL_PATIENT_ID + " VARCHAR(32) NOT NULL, " +
            Prescription.COL_MEDICINE + " VARCHAR(50), " +
            Prescription.COL_GENERIC_NAME + " VARCHAR(50), " +
            Prescription.COL_DOSE + " VARCHAR(50), " +
            Prescription.COL_UNIT + " VARCHAR(50), " +
            Prescription.COL_QUANTITY + " VARCHAR(50), " +
            Prescription.COL_DOSAGE + " VARCHAR(50), " +
            Prescription.COL_NUMBER_OF_TAKE + " VARCHAR(50), " +
            Prescription.COL_TYPE + " VARCHAR(50), " +
            Prescription.COL_REFILL_INSTRUCTION + " VARCHAR(50), " +
            Prescription.COL_WHERE + " VARCHAR(50)" +
            " )";

    private static final String CREATE_TABLE_ORDERS = "CREATE TABLE "+ TABLE_ORDERS +
            " (" +
            Orders.COL_ORDERS_ID + " VARCHAR(32) NOT NULL PRIMARY KEY, " +
            Patient.COL_PATIENT_ID + " VARCHAR(32) NOT NULL, " +
            Orders.COL_FOOD + " VARCHAR(50), " +
            Orders.COL_TESTS + " VARCHAR(50), " +
            Orders.COL_PROCEDURES + " VARCHAR(50), " +
            Orders.COL_INTRAVENOUS_FLUID + " VARCHAR(50), " +
            Orders.COL_BLOOD_TRANSFUSION + " VARCHAR(50), " +
            Orders.COL_SIGNATURE + " BLOB" +
            " )";

    private static final String CREATE_TABLE_ASSESSMENT= "CREATE TABLE "+ TABLE_ASSESSMENT +
            " (" +
            Assessment.COL_ASSESSMENT_ID + " VARCHAR(32) NOT NULL PRIMARY KEY, " +
            Patient.COL_PATIENT_ID + " VARCHAR(32) NOT NULL, " +
            Assessment.COL_SUBGJECTIVE + " VARCHAR(50), " +
            Assessment.COL_OBJECTIVE + " VARCHAR(50), " +
            Assessment.COL_ASSESSMENT + " VARCHAR(50), " +
            Assessment.COL_PLAN + " VARCHAR(50), " +
            Assessment.COL_DIAGNOSIS_INITIAL + " VARCHAR(50), " +
            Assessment.COL_DIAGNOSIS_ADMITTING + " VARCHAR(50), " +
            Assessment.COL_SIGNATURE + " BLOB" +
            " )";

    private static final String CREATE_TABLE_DISCHARGE_PLAN = "CREATE TABLE "+ TABLE_DISCHARGE_PLAN +
            " (" +
            DischargePlan.COL_DISCHARGE_PLAN_ID + " VARCHAR(32) NOT NULL PRIMARY KEY, " +
            Patient.COL_PATIENT_ID + " VARCHAR(32) NOT NULL, " +
            DischargePlan.COL_CLINICAL_ABSTRACT_SUMMARY + " VARCHAR(50), " +
            DischargePlan.COL_FINAL_DIAGNOSIS + " VARCHAR(50), " +
            DischargePlan.COL_AFTER_CARE_INSTRUCTIONS + " VARCHAR(50), " +
            DischargePlan.COL_SIGNATURE + " BLOB" +
            " )";

    public SQLiteDBContext(Context context, String name, SQLiteDatabase.CursorFactory factory,
                           int version) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public SQLiteDBContext(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_PATIENT_INFO);
        db.execSQL(CREATE_TABLE_PRESCRIPTION);
        db.execSQL(CREATE_TABLE_ORDERS);
        db.execSQL(CREATE_TABLE_ASSESSMENT);
        db.execSQL(CREATE_TABLE_DISCHARGE_PLAN);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(newVersion > oldVersion){
            db.execSQL("DROP TABLE IF EXISTS " + CREATE_TABLE_PATIENT_INFO);
            db.execSQL("DROP TABLE IF EXISTS " + CREATE_TABLE_PRESCRIPTION);
            db.execSQL("DROP TABLE IF EXISTS " + CREATE_TABLE_ORDERS);
            db.execSQL("DROP TABLE IF EXISTS " + CREATE_TABLE_ASSESSMENT);
            db.execSQL("DROP TABLE IF EXISTS " + CREATE_TABLE_DISCHARGE_PLAN);
            onCreate(db);
        }
    }
}
