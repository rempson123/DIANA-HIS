package company.geodata.diana.Model;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Paint;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import company.geodata.diana.Repository.PatientRepository;

/**
 * Created by jcmate on 5/29/2017.
 */

public class Patient {
    public static final String COL_PATIENT_ID = "patient_id";
    public static final String COL_NAME = "name";
    public static final String COL_ADDRESS = "address";
    public static final String COL_GENDER = "gender";
    public static final String COL_AGE = "age";
    public static final String COL_BLOOD_TYPE = "blood_type";
    public static final String COL_EMRNO = "EMR_no";
    public static final String COL_PATIENT_TYPE = "patient_type";
    public static final String COL_ROOM_BED = "room_bed";
    public static final String COL_ALLERGY_FOOD = "allergy_food";
    public static final String COL_ALLERGY_MEDICINE = "allergy_medicine";
    public static final String COL_ALLERGY_LATEX = "allergy_latex";
    public static final String COL_ALLERGY_OTHERS = "allergy_others";
    public static final String COL_CHIEF_COMPLIANT = "chief_compliant";
    public static final String COL_PREVIOUS_ASSESSMENT = "previous_assessment";
    public static final String COL_INDICATIONS = "Indications";
    public static final String COL_DATE_OF_VISIT = "date_of_visit";
    public static final String COL_DATE_OF_ADMISSION = "date_of_admission";
    public static final String COL_TIME = "time";
    public static final String COL_IMAGE = "image";
    public static final String COL_DOCTORS_NAME = "doctors_name";
    public static final String COL_SIGNATURE = "signature";

    //Vital Signs
    public static final String COL_VS_TIME = "VS_time";
    public static final String COL_VS_PULSE_RATE = "VS_pulse_rate";
    public static final String COL_VS_RESPIRATORY_RATE = "VS_respiratory_rate";
    public static final String COL_VS_RESPIRATORY_QUALITY = "VS_respiratory_quality";
    public static final String COL_VS_SA02 = "VS_sa02";
    public static final String COL_VS_CAP_REFILL =  "VS_cap_refill";
    public static final String COL_VS_BLOOD_PRESSURE = "VS_blood_pressure";
    public static final String COL_VS_TEMPERATURE = "VS_temperature";
    public static final String COL_VS_LEFT_PUPIL_SIZE = "VS_left_pupil_size";
    public static final String COL_VS_RIGHT_PUPIL_SIZE = "VS_right_pupil_size";
    public static final String COL_VS_LEFT_PUPIL_REACTION = "VS_left_pupil_reaction";
    public static final String COL_VS_RIGHT_PUPIL_REACTION = "VS_right_pupil_reaction";
    public static final String COL_VS_PAIN_SCORE = "VS_pain_score";
    public static final String COL_VS_BLOOD_GLUCOSE_LEVEL = "VS_blood_glucose_level";

    //Glascow coma scale
    public static final String COL_VS_EYE =  "VS_eye";
    public static final String COL_VS_VERBAL = "VS_verbal";
    public static final String COL_VS_MOTOR = "VS_motor";
    public static final String COL_VS_TOTAL = "VS_total";

    String PatientID;
    String PatientName;
    String Address;
    String Gender;
    int Age;
    String BloodType;
    String EMRNo;
    String PatientType;
    String RoomBed;
    String AllergyFood;
    String AllergyMedicine;
    String AllergyLatex;
    String AllergyOthers;
    String ChiefCompliant;
    String PreviousAssessment;
    String Indications;
    String DateOfVisit;
    String DateOfAdmission;
    String Time;
    Bitmap Image;
    String doctorsName;
    Bitmap signature;

    //Vital Signs
    String VS_time;
    String VS_pulseRate;
    String VS_respiratoryRate;
    String VS_respiratoryQuality;
    String VS_sa02;
    String VS_capRefill;
    String VS_bloodPressure;
    String VS_temperature;
    String VS_leftPupilSize;
    String VS_rightPupilSize;
    String VS_leftPupilReaction;
    String VS_rightPupilReaction;
    String VS_painScore;
    String VS_bloodGlucoseLevel;

    //Glascow coma scale
    String VS_eye;
    String VS_verbal;
    String VS_motor;
    String VS_total;

    Orders orders;
    Assessment assessment;
    DischargePlan dischargePlan;

    public Patient(String patientID, String patientName, String address, String gender, int age, String bloodType, String EMRNo, String patientType, String roomBed, String allergyFood, String allergyMedicine, String allergyLatex, String allergyOthers, String chiefCompliant, String previousAssessment, String indications, String dateOfVisit, String dateOfAdmission, String time, Bitmap image, String doctorsName, Bitmap signature, String VS_time, String VS_pulseRate, String VS_respiratoryRate, String VS_respiratoryQuality, String VS_sa02, String VS_capRefill, String VS_bloodPressure, String VS_temperature, String VS_leftPupilSize, String VS_rightPupilSize, String VS_leftPupilReaction, String VS_rightPupilReaction, String VS_painScore, String VS_bloodGlucoseLevel, String VS_eye, String VS_verbal, String VS_motor, String VS_total) {
        PatientID = patientID;
        PatientName = patientName;
        Address = address;
        Gender = gender;
        Age = age;
        BloodType = bloodType;
        this.EMRNo = EMRNo;
        PatientType = patientType;
        RoomBed = roomBed;
        AllergyFood = allergyFood;
        AllergyMedicine = allergyMedicine;
        AllergyLatex = allergyLatex;
        AllergyOthers = allergyOthers;
        ChiefCompliant = chiefCompliant;
        PreviousAssessment = previousAssessment;
        Indications = indications;
        DateOfVisit = dateOfVisit;
        DateOfAdmission = dateOfAdmission;
        Time = time;
        Image = image;
        this.doctorsName = doctorsName;
        this.signature = signature;
        this.VS_time = VS_time;
        this.VS_pulseRate = VS_pulseRate;
        this.VS_respiratoryRate = VS_respiratoryRate;
        this.VS_respiratoryQuality = VS_respiratoryQuality;
        this.VS_sa02 = VS_sa02;
        this.VS_capRefill = VS_capRefill;
        this.VS_bloodPressure = VS_bloodPressure;
        this.VS_temperature = VS_temperature;
        this.VS_leftPupilSize = VS_leftPupilSize;
        this.VS_rightPupilSize = VS_rightPupilSize;
        this.VS_leftPupilReaction = VS_leftPupilReaction;
        this.VS_rightPupilReaction = VS_rightPupilReaction;
        this.VS_painScore = VS_painScore;
        this.VS_bloodGlucoseLevel = VS_bloodGlucoseLevel;
        this.VS_eye = VS_eye;
        this.VS_verbal = VS_verbal;
        this.VS_motor = VS_motor;
        this.VS_total = VS_total;

        //Every Patient has order, assessment, and discharge plan
        orders = new Orders(
                UUID.randomUUID().toString(),
                patientID,
                new ArrayList<Prescription>(),
                "",
                "",
                "",
                "",
                "",
                null
        );
        assessment = new Assessment(
                UUID.randomUUID().toString(),
                patientID,
                "",
                "",
                "",
                "",
                "",
                "",
                null
        );
        dischargePlan = new DischargePlan(
                UUID.randomUUID().toString(),
                patientID,
                "",
                "",
                new ArrayList<Prescription>(),
                "",
                null
        );
    }


    public String getPatientID() {
        return PatientID;
    }

    public void setPatientID(String patientID) {
        PatientID = patientID;
    }

    public String getPatientName() {
        return PatientName;
    }

    public void setPatientName(String patientName) {
        PatientName = patientName;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public int getAge() {
        return Age;
    }

    public void setAge(int age) {
        Age = age;
    }

    public String getBloodType() {
        return BloodType;
    }

    public void setBloodType(String bloodType) {
        BloodType = bloodType;
    }

    public String getEMRNo() {
        return EMRNo;
    }

    public void setEMRNo(String EMRNo) {
        this.EMRNo = EMRNo;
    }

    public String getPatientType() {
        return PatientType;
    }

    public void setPatientType(String patientType) {
        PatientType = patientType;
    }

    public String getRoomBed() {
        return RoomBed;
    }

    public void setRoomBed(String roomBed) {
        RoomBed = roomBed;
    }

    public String getAllergyFood() {
        return AllergyFood;
    }

    public void setAllergyFood(String allergyFood) {
        AllergyFood = allergyFood;
    }

    public String getAllergyMedicine() {
        return AllergyMedicine;
    }

    public void setAllergyMedicine(String allergyMedicine) {
        AllergyMedicine = allergyMedicine;
    }

    public String getAllergyLatex() {
        return AllergyLatex;
    }

    public void setAllergyLatex(String allergyLatex) {
        AllergyLatex = allergyLatex;
    }

    public String getAllergyOthers() {
        return AllergyOthers;
    }

    public void setAllergyOthers(String allergyOthers) {
        AllergyOthers = allergyOthers;
    }

    public String getChiefCompliant() {
        return ChiefCompliant;
    }

    public void setChiefCompliant(String chiefCompliant) {
        ChiefCompliant = chiefCompliant;
    }

    public String getPreviousAssessment() {
        return PreviousAssessment;
    }

    public void setPreviousAssessment(String previousAssessment) {
        PreviousAssessment = previousAssessment;
    }

    public String getIndications() {
        return Indications;
    }

    public void setIndications(String indications) {
        Indications = indications;
    }

    public String getDateOfVisit() {
        return DateOfVisit;
    }

    public void setDateOfVisit(String dateOfVisit) {
        DateOfVisit = dateOfVisit;
    }

    public String getDateOfAdmission() {
        return DateOfAdmission;
    }

    public void setDateOfAdmission(String dateOfAdmission) {
        DateOfAdmission = dateOfAdmission;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public Bitmap getImage() {
        return Image;
    }

    public void setImage(Bitmap image) {
        Image = image;
    }

    public Orders getOrders() {
        return orders;
    }

    public void setOrders(Orders orders) {
        this.orders = orders;
    }

    public DischargePlan getDischargePlan() {
        return dischargePlan;
    }

    public void setDischargePlan(DischargePlan dischargePlan) {
        this.dischargePlan = dischargePlan;
    }

    public Assessment getAssessment() {
        return assessment;
    }

    public void setAssessment(Assessment assessment) {
        this.assessment = assessment;
    }

    public String getVS_time() {
        return VS_time;
    }

    public void setVS_time(String VS_time) {
        this.VS_time = VS_time;
    }

    public String getVS_pulseRate() {
        return VS_pulseRate;
    }

    public void setVS_pulseRate(String VS_pulseRate) {
        this.VS_pulseRate = VS_pulseRate;
    }

    public String getVS_respiratoryRate() {
        return VS_respiratoryRate;
    }

    public void setVS_respiratoryRate(String VS_respiratoryRate) {
        this.VS_respiratoryRate = VS_respiratoryRate;
    }

    public String getVS_respiratoryQuality() {
        return VS_respiratoryQuality;
    }

    public void setVS_respiratoryQuality(String VS_respiratoryQuality) {
        this.VS_respiratoryQuality = VS_respiratoryQuality;
    }

    public String getVS_sa02() {
        return VS_sa02;
    }

    public void setVS_sa02(String VS_sa02) {
        this.VS_sa02 = VS_sa02;
    }

    public String getVS_capRefill() {
        return VS_capRefill;
    }

    public void setVS_capRefill(String VS_capRefill) {
        this.VS_capRefill = VS_capRefill;
    }

    public String getVS_bloodPressure() {
        return VS_bloodPressure;
    }

    public void setVS_bloodPressure(String VS_bloodPressure) {
        this.VS_bloodPressure = VS_bloodPressure;
    }

    public String getVS_temperature() {
        return VS_temperature;
    }

    public void setVS_temperature(String VS_temperature) {
        this.VS_temperature = VS_temperature;
    }

    public String getVS_leftPupilSize() {
        return VS_leftPupilSize;
    }

    public void setVS_leftPupilSize(String VS_leftPupilSize) {
        this.VS_leftPupilSize = VS_leftPupilSize;
    }

    public String getVS_rightPupilSize() {
        return VS_rightPupilSize;
    }

    public void setVS_rightPupilSize(String VS_rightPupilSize) {
        this.VS_rightPupilSize = VS_rightPupilSize;
    }

    public String getVS_leftPupilReaction() {
        return VS_leftPupilReaction;
    }

    public void setVS_leftPupilReaction(String VS_leftPupilReaction) {
        this.VS_leftPupilReaction = VS_leftPupilReaction;
    }

    public String getVS_rightPupilReaction() {
        return VS_rightPupilReaction;
    }

    public void setVS_rightPupilReaction(String VS_rightPupilReaction) {
        this.VS_rightPupilReaction = VS_rightPupilReaction;
    }

    public String getVS_painScore() {
        return VS_painScore;
    }

    public void setVS_painScore(String VS_painScore) {
        this.VS_painScore = VS_painScore;
    }

    public String getVS_bloodGlucoseLevel() {
        return VS_bloodGlucoseLevel;
    }

    public void setVS_bloodGlucoseLevel(String VS_bloodGlucoseLevel) {
        this.VS_bloodGlucoseLevel = VS_bloodGlucoseLevel;
    }

    public String getVS_eye() {
        return VS_eye;
    }

    public void setVS_eye(String VS_eye) {
        this.VS_eye = VS_eye;
    }

    public String getVS_verbal() {
        return VS_verbal;
    }

    public void setVS_verbal(String VS_verbal) {
        this.VS_verbal = VS_verbal;
    }

    public String getVS_motor() {
        return VS_motor;
    }

    public void setVS_motor(String VS_motor) {
        this.VS_motor = VS_motor;
    }

    public String getVS_total() {
        return VS_total;
    }

    public void setVS_total(String VS_total) {
        this.VS_total = VS_total;
    }

    public String getDoctorsName() {
        return doctorsName;
    }

    public void setDoctorsName(String doctorsName) {
        this.doctorsName = doctorsName;
    }

    public Bitmap getSignature() {
        return signature;
    }

    public void setSignature(Bitmap signature) {
        this.signature = signature;
    }

    public static void create(Context context, Patient patient){
        PatientRepository pr = new PatientRepository(context);
        pr.open();
        pr.createPatient(patient);
        pr.close();
    }
    public static void update(Context context, Patient patient){
        PatientRepository pr = new PatientRepository(context);
        pr.open();
        pr.updatePatient(patient);
        pr.close();
    }

    public static Patient findPatient(Context context, Patient patient){
        PatientRepository pr = new PatientRepository(context);
        pr.open();
        Patient b = pr.getPatient(patient.getPatientID());
        pr.close();
        return b;
    }

    public static List<Patient> findAllPatients(Context context){
        PatientRepository pr = new PatientRepository(context);
        pr.open();
        List<Patient> businessList = pr.getAllPatients();
        pr.close();
        return businessList;
    }
}
