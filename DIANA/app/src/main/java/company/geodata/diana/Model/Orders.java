package company.geodata.diana.Model;

import android.content.Context;
import android.graphics.Bitmap;

import java.util.ArrayList;
import java.util.List;

import company.geodata.diana.Repository.OrdersRepository;
import company.geodata.diana.Repository.PatientRepository;

/**
 * Created by jcmate on 6/13/2017.
 */

public class Orders {

    public static final String COL_ORDERS_ID = "orders_id";
    public static final String COL_FOOD = "food";
    public static final String COL_TESTS = "tests";
    public static final String COL_PROCEDURES = "procedures";
    public static final String COL_INTRAVENOUS_FLUID = "intravenous_fluid";
    public static final String COL_BLOOD_TRANSFUSION = "blood_transfusion";
    public static final String COL_SIGNATURE = "signature";

    String OrdersID;
    String PatientID;
    List<Prescription> PrescriptionList;
    String Food;
    String Tests;
    String Procedures;
    String IntravenousFluid;
    String BloodTransfusion;
    Bitmap Signature;

    public Orders(String ordersID, String patientID, List<Prescription> prescriptionList, String food, String tests, String procedures, String intravenousFluid, String bloodTransfusion, Bitmap signature) {
        OrdersID = ordersID;
        PatientID = patientID;
        PrescriptionList = prescriptionList;
        Food = food;
        Tests = tests;
        Procedures = procedures;
        IntravenousFluid = intravenousFluid;
        BloodTransfusion = bloodTransfusion;
        Signature = signature;
    }

    public Orders(String patientID) {
        PatientID = patientID;
        PrescriptionList = new ArrayList<Prescription>();
    }

    public String getOrdersID() {
        return OrdersID;
    }

    public String getPatientID() {
        return PatientID;
    }

    public void setPatientID(String patientID) {
        PatientID = patientID;
    }

    public void setOrdersID(String ordersID) {
        OrdersID = ordersID;
    }

    public List<Prescription> getPrescriptionList() {
        return PrescriptionList;
    }

    public void setPrescriptionList(List<Prescription> prescriptionList) {
        PrescriptionList = prescriptionList;
    }

    public List getPrescription() {
        return PrescriptionList;
    }

    public void setPrescription(List prescription) {
        PrescriptionList = prescription;
    }

    public String getFood() {
        return Food;
    }

    public void setFood(String food) {
        Food = food;
    }

    public String getTests() {
        return Tests;
    }

    public void setTests(String tests) {
        Tests = tests;
    }

    public String getProcedures() {
        return Procedures;
    }

    public void setProcedures(String procedures) {
        Procedures = procedures;
    }

    public String getIntravenousFluid() {
        return IntravenousFluid;
    }

    public void setIntravenousFluid(String intravenousFluid) {
        IntravenousFluid = intravenousFluid;
    }

    public String getBloodTransfusion() {
        return BloodTransfusion;
    }

    public void setBloodTransfusion(String bloodTransfusion) {
        BloodTransfusion = bloodTransfusion;
    }

    public Bitmap getSignature() {
        return Signature;
    }

    public void setSignature(Bitmap signature) {
        Signature = signature;
    }


    public static void create(Context context, Orders orders){
        OrdersRepository pr = new OrdersRepository(context);
        pr.open();
        pr.createOrders(orders);
        pr.close();
    }
    public static void update(Context context, Orders orders){
        OrdersRepository pr = new OrdersRepository(context);
        pr.open();
        pr.updateOrders(orders);
        pr.close();
    }

    public static Orders findOrders(Context context, Patient patient){
        OrdersRepository pr = new OrdersRepository(context);
        pr.open();
        Orders b = pr.getOrders(patient.getPatientID());
        pr.close();
        return b;
    }
}
