package company.geodata.diana.Model;

import android.content.Context;
import android.graphics.Bitmap;

import java.util.ArrayList;
import java.util.List;

import company.geodata.diana.Repository.DischargePlanRepository;
import company.geodata.diana.Repository.OrdersRepository;

/**
 * Created by jcmate on 6/16/2017.
 */

public class DischargePlan {
    public static final String COL_DISCHARGE_PLAN_ID = "discharge_plan_id";
    public static final String COL_CLINICAL_ABSTRACT_SUMMARY = "clinical_abstract_summary";
    public static final String COL_FINAL_DIAGNOSIS = "final_diagnosis";
    public static final String COL_AFTER_CARE_INSTRUCTIONS = "after_care_instructions";
    public static final String COL_SIGNATURE = "signature";

    String DischargePlanID;
    String PatientID;
    String ClinicalAbstractSummary;
    String FinalDiagnosis;
    List<Prescription> PrescriptionList;
    String AfterCareInstructions;
    Bitmap Signature;

    public DischargePlan(String dischargePlanID, String patientID, String clinicalAbstractSummary, String finalDiagnosis, List<Prescription> prescriptionList, String afterCareInstructions, Bitmap signature) {
        DischargePlanID = dischargePlanID;
        PatientID = patientID;
        ClinicalAbstractSummary = clinicalAbstractSummary;
        FinalDiagnosis = finalDiagnosis;
        PrescriptionList = prescriptionList;
        AfterCareInstructions = afterCareInstructions;
        Signature = signature;
    }

    public DischargePlan(String patientID) {
        PatientID = patientID;
        PrescriptionList = new ArrayList<Prescription>();
    }

    public DischargePlan() {
        PrescriptionList = new ArrayList<Prescription>();
    }
    public String getDischargePlanID() {
        return DischargePlanID;
    }

    public void setDischargePlanID(String dischargePlanID) {
        DischargePlanID = dischargePlanID;
    }

    public String getPatientID() {
        return PatientID;
    }

    public void setPatientID(String patientID) {
        PatientID = patientID;
    }

    public Bitmap getSignature() {
        return Signature;
    }

    public void setSignature(Bitmap signature) {
        Signature = signature;
    }

    public String getClinicalAbstractSummary() {
        return ClinicalAbstractSummary;
    }

    public void setClinicalAbstractSummary(String clinicalAbstractSummary) {
        ClinicalAbstractSummary = clinicalAbstractSummary;
    }

    public String getFinalDiagnosis() {
        return FinalDiagnosis;
    }

    public void setFinalDiagnosis(String finalDiagnosis) {
        FinalDiagnosis = finalDiagnosis;
    }

    public List<Prescription> getPrescriptionList() {
        return PrescriptionList;
    }

    public void setPrescriptionList(List<Prescription> prescriptionList) {
        PrescriptionList = prescriptionList;
    }

    public String getAfterCareInstructions() {
        return AfterCareInstructions;
    }

    public void setAfterCareInstructions(String afterCareInstructions) {
        AfterCareInstructions = afterCareInstructions;
    }

    public static void create(Context context, DischargePlan dischargePlan){
        DischargePlanRepository pr = new DischargePlanRepository(context);
        pr.open();
        pr.createdischargePlan(dischargePlan);
        pr.close();
    }

    public static void update(Context context, DischargePlan dischargePlan){
        DischargePlanRepository pr = new DischargePlanRepository(context);
        pr.open();
        pr.updateDischargePlan(dischargePlan);
        pr.close();
    }

    public static DischargePlan findDischargePlan(Context context, Patient patient){
        DischargePlanRepository pr = new DischargePlanRepository(context);
        pr.open();
        DischargePlan b = pr.getDischargePlan(patient.getPatientID());
        pr.close();
        return b;
    }
}
