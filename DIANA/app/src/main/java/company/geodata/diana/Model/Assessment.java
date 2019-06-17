package company.geodata.diana.Model;

import android.content.Context;
import android.graphics.Bitmap;

import company.geodata.diana.Repository.AssessmentRepository;
import company.geodata.diana.Repository.DischargePlanRepository;

/**
 * Created by jcmate on 8/24/2017.
 */

public class Assessment {

    public static final String COL_ASSESSMENT_ID = "assessment_id";
    public static final String COL_SUBGJECTIVE = "subjective";
    public static final String COL_OBJECTIVE = "objective";
    public static final String COL_ASSESSMENT = "assessment";
    public static final String COL_PLAN = "plan";
    public static final String COL_DIAGNOSIS_INITIAL = "diagnosis_initial";
    public static final String COL_DIAGNOSIS_ADMITTING = "diagnosis_admitting";
    public static final String COL_SIGNATURE = "signature";

    String AssessmentID;
    String PatientID;
    String Subjective;
    String Objective;
    String Assessment;
    String Plan;
    String DiagnosisInitial;
    String DiagnosisAdmitting;
    Bitmap Signature;

    public Assessment() {
    }

    public Assessment(String assessmentID, String patientID, String subjective, String objective, String assessment, String plan, String diagnosisInitial, String diagnosisAdmitting, Bitmap signature) {
        AssessmentID = assessmentID;
        PatientID = patientID;
        Subjective = subjective;
        Objective = objective;
        Assessment = assessment;
        Plan = plan;
        DiagnosisInitial = diagnosisInitial;
        DiagnosisAdmitting = diagnosisAdmitting;
        Signature = signature;
    }

    public String getPatientID() {
        return PatientID;
    }

    public void setPatientID(String patientID) {
        PatientID = patientID;
    }

    public String getAssessmentID() {
        return AssessmentID;
    }

    public void setAssessmentID(String assessmentID) {
        AssessmentID = assessmentID;
    }

    public String getSubjective() {
        return Subjective;
    }

    public void setSubjective(String subjective) {
        Subjective = subjective;
    }

    public String getObjective() {
        return Objective;
    }

    public void setObjective(String objective) {
        Objective = objective;
    }

    public String getAssessment() {
        return Assessment;
    }

    public void setAssessment(String assessment) {
        Assessment = assessment;
    }

    public String getPlan() {
        return Plan;
    }

    public void setPlan(String plan) {
        Plan = plan;
    }

    public String getDiagnosisInitial() {
        return DiagnosisInitial;
    }

    public void setDiagnosisInitial(String diagnosisInitial) {
        DiagnosisInitial = diagnosisInitial;
    }

    public String getDiagnosisAdmitting() {
        return DiagnosisAdmitting;
    }

    public void setDiagnosisAdmitting(String diagnosisAdmitting) {
        DiagnosisAdmitting = diagnosisAdmitting;
    }

    public Bitmap getSignature() {
        return Signature;
    }

    public void setSignature(Bitmap signature) {
        Signature = signature;
    }

    public static void create(Context context, Assessment assessment){
        AssessmentRepository pr = new AssessmentRepository(context);
        pr.open();
        pr.createAssessment(assessment);
        pr.close();
    }

    public static void update(Context context, Assessment assessment){
        AssessmentRepository pr = new AssessmentRepository(context);
        pr.open();
        pr.updateAssessment(assessment);
        pr.close();
    }

    public static Assessment findAssessment(Context context, Patient patient){
        AssessmentRepository pr = new AssessmentRepository(context);
        pr.open();
        Assessment b = pr.getAssessment(patient.getPatientID());
        pr.close();
        return b;
    }
}
