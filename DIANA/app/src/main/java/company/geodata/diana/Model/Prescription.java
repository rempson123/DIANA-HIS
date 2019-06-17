package company.geodata.diana.Model;

import android.content.Context;

import java.util.List;
import java.util.UUID;

import company.geodata.diana.Repository.PatientRepository;
import company.geodata.diana.Repository.PrescriptionRepository;

/**
 * Created by jcmate on 6/5/2017.
 */

public class Prescription {

    public static final String COL_PRESCRIPTION_ID = "prescription_id";
    public static final String COL_MEDICINE = "medicine";
    public static final String COL_GENERIC_NAME = "generic_name";
    public static final String COL_DOSE = "dose";
    public static final String COL_UNIT = "unit";
    public static final String COL_QUANTITY = "quantity";
    public static final String COL_DOSAGE = "dosage";
    public static final String COL_NUMBER_OF_TAKE = "number_of_take";
    public static final String COL_TYPE = "type";
    public static final String COL_REFILL_INSTRUCTION = "refill_instructions";
    public static final String COL_WHERE = "which";

    String PrescriptionID;
    String PatientID;
    String Medicine;
    String GenericName;
    int Dose;
    String Unit; //mg, g
    int Quantity;
    String Dosage; //pill, tablet, syrup
    String NumberOfTake;
    String Type;
    String RefillInstructions;
    String Where;

    public Prescription() {
    }

    public Prescription(String prescriptionID,String patientID, String medicine, String genericName, int dose, String unit, int quantity, String dosage, String numberOfTake, String type, String refillInstructions, String where) {
        PrescriptionID = prescriptionID;
        PatientID = patientID;
        Medicine = medicine;
        GenericName = genericName;
        Dose = dose;
        Unit = unit;
        Quantity = quantity;
        Dosage = dosage;
        NumberOfTake = numberOfTake;
        Type = type;
        RefillInstructions = refillInstructions;
        Where = where;
    }

    public String getPatientID() {
        return PatientID;
    }

    public void setPatientID(String patientID) {
        PatientID = patientID;
    }

    public String getPrescriptionID() {
        return PrescriptionID;
    }

    public void setPrescriptionID(String prescriptionID) {
        PrescriptionID = prescriptionID;
    }

    public String getMedicine() {
        return Medicine;
    }

    public void setMedicine(String medicine) {
        Medicine = medicine;
    }

    public String getGenericName() {
        return GenericName;
    }

    public void setGenericName(String genericName) {
        GenericName = genericName;
    }

    public int getDose() {
        return Dose;
    }

    public void setDose(int dose) {
        Dose = dose;
    }

    public String getUnit() {
        return Unit;
    }

    public void setUnit(String unit) {
        Unit = unit;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int quantity) {
        Quantity = quantity;
    }

    public String getDosage() {
        return Dosage;
    }

    public void setDosage(String dosage) {
        Dosage = dosage;
    }

    public String getNumberOfTake() {
        return NumberOfTake;
    }

    public void setNumberOfTake(String numberOfTake) {
        NumberOfTake = numberOfTake;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getRefillInstructions() {
        return RefillInstructions;
    }

    public String getWhere() {
        return Where;
    }

    public void setWhere(String where) {
        Where = where;
    }

    public void setRefillInstructions(String refillInstructions) {
        RefillInstructions = refillInstructions;
    }

    public static void create(Context context, Prescription prescription){
        PrescriptionRepository pr = new PrescriptionRepository(context);
        pr.open();
        pr.createPrescription(prescription);
        pr.close();
    }
    public static void update(Context context, Prescription prescription){
        PrescriptionRepository pr = new PrescriptionRepository(context);
        pr.open();
        pr.updatePrescription(prescription);
        pr.close();
    }

    public static Prescription findPrescription(Context context, Prescription prescription){
        PrescriptionRepository pr = new PrescriptionRepository(context);
        pr.open();
        Prescription p = pr.getPrescription(prescription.getPrescriptionID());
        pr.close();
        return p;
    }

    public static List<Prescription> findAllPatientPrescription(Context context, Patient patient, String which){
        PrescriptionRepository pr = new PrescriptionRepository(context);
        pr.open();
        List<Prescription> prescriptions = pr.getAllPatientPrescription(patient, which);
        pr.close();
        return prescriptions;
    }
}
