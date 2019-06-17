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
import company.geodata.diana.Model.Orders;
import company.geodata.diana.Model.Patient;
import company.geodata.diana.Model.Prescription;
import company.geodata.diana.PatientCareActivity;

/**
 * Created by jcmate on 9/6/2017.
 */

public class OrdersRepository extends Repository {
    Context context;
    private String[] AllColumns = {
            Orders.COL_ORDERS_ID,
            Patient.COL_PATIENT_ID,
            Orders.COL_FOOD,
            Orders.COL_TESTS,
            Orders.COL_PROCEDURES,
            Orders.COL_INTRAVENOUS_FLUID,
            Orders.COL_BLOOD_TRANSFUSION,
            Orders.COL_SIGNATURE
    };

    public OrdersRepository(Context context) {
        super(context);
        this.context = context;
    }

    public Orders getOrders(String patientID) {
        Orders orders = null;
        Cursor cursor = database.query(SQLiteDBContext.TABLE_ORDERS, AllColumns, AllColumns[1] + " = ?" , new String[]{patientID}, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            orders = parseResultSetToOrders(cursor);
            cursor.moveToNext();
        }
        cursor.close();
        return orders;
    }

    public Orders createOrders(Orders orders) {
        ContentValues ordersContentValues = setOrdersContentValues(orders);
        long insertId = database.insert(SQLiteDBContext.TABLE_ORDERS, null, ordersContentValues);
        Cursor cursor = database.query(SQLiteDBContext.TABLE_ORDERS, AllColumns, AllColumns[0] + " = ?", new String[] {orders.getOrdersID()}, null, null, null);
        cursor.moveToFirst();
        Orders orders1 = null;
        if (insertId > 0) {
            orders1 = parseResultSetToOrders(cursor);
        }
        cursor.close();
        return orders1;
    }

    public Orders updateOrders(Orders orders) {
        ContentValues ordersContentValues = setOrdersContentValues(orders);
        int editedId = Integer.parseInt(String.valueOf(database.update(SQLiteDBContext.TABLE_ORDERS, ordersContentValues,
                AllColumns[1] + " = ?", new String[] {orders.getPatientID()})));

        Orders orders1 = null;
        if (editedId > 0) {
            Cursor cursor = database.query(SQLiteDBContext.TABLE_ORDERS, AllColumns,
                    AllColumns[1] + " = ?", new String[] {orders.getPatientID()}, null, null, null);
            cursor.moveToFirst();
            orders1 = parseResultSetToOrders(cursor);
            cursor.close();
        }

        return  orders1;
    }

    private Orders parseResultSetToOrders(Cursor cursor) {
        Orders orders = new Orders(
                cursor.getString(cursor.getColumnIndex(AllColumns[0])),
                cursor.getString(cursor.getColumnIndex(AllColumns[1])),
                (Prescription.findAllPatientPrescription(context, PatientCareActivity.patient, PrescriptionFragment.ORDERS).size() > 0) ? Prescription.findAllPatientPrescription(context, PatientCareActivity.patient, PrescriptionFragment.ORDERS) : new ArrayList<Prescription>(),
                cursor.getString(cursor.getColumnIndex(AllColumns[2])),
                cursor.getString(cursor.getColumnIndex(AllColumns[3])),
                cursor.getString(cursor.getColumnIndex(AllColumns[4])),
                cursor.getString(cursor.getColumnIndex(AllColumns[5])),
                cursor.getString(cursor.getColumnIndex(AllColumns[6])),
                (cursor.getBlob(cursor.getColumnIndex(AllColumns[7])) != null) ? getBitmap(cursor.getBlob(cursor.getColumnIndex(AllColumns[7]))) : null

        );
        return orders;
    }
    private ContentValues setOrdersContentValues(Orders orders) {
        ContentValues userValues = new ContentValues();
        userValues.put(AllColumns[0], orders.getOrdersID());
        userValues.put(AllColumns[1], orders.getPatientID());
        userValues.put(AllColumns[2], orders.getFood());
        userValues.put(AllColumns[3], orders.getTests());
        userValues.put(AllColumns[4], orders.getProcedures());
        userValues.put(AllColumns[5], orders.getIntravenousFluid());
        userValues.put(AllColumns[6], orders.getBloodTransfusion());
        userValues.put(AllColumns[7], (orders.getSignature() != null) ? getBytes(orders.getSignature()) : null);
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
