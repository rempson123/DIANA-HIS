package company.geodata.diana.Fragments;

import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import company.geodata.diana.Adapter.PrescriptionsAdapter;
import company.geodata.diana.Dialogs.SignatureDialog;
import company.geodata.diana.Model.Orders;
import company.geodata.diana.Model.Patient;
import company.geodata.diana.Model.Prescription;
import company.geodata.diana.PatientCareActivity;
import company.geodata.diana.PatientsListActivity;
import company.geodata.diana.R;

import static company.geodata.diana.Fragments.PrescriptionFragment.ORDERS;

/**
 * Created by jcmate on 6/1/2017.
 */

public class OrdersFragment extends Fragment {
    ImageView ivSignature;
    EditText editFood, editTests, editProcedures, editIntravenous, editBloodTransfusion;
    Button btnSave;

    Orders orders;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_orders, container, false);
        ivSignature = (ImageView) v.findViewById(R.id.imageViewSignature);
        editFood = (EditText) v.findViewById(R.id.editFood);
        editTests = (EditText) v.findViewById(R.id.editTests);
        editProcedures = (EditText) v.findViewById(R.id.editProcedures);
        editIntravenous = (EditText) v.findViewById(R.id.editIntravenous);
        editBloodTransfusion = (EditText) v.findViewById(R.id.editBloodTransfusion);
        btnSave = (Button) v.findViewById(R.id.btnSave);

        orders = Orders.findOrders(getActivity(), PatientCareActivity.patient);
        if (orders != null) displayDatas();

        ivSignature.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SignatureDialog dialog = new SignatureDialog();
                dialog.newInstance(view);
                dialog.show(getFragmentManager(), "PatientInformation");
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (Orders.findOrders(getActivity(), PatientCareActivity.patient) != null) {
                    Orders orders = new Orders(
                            PatientCareActivity.patient.getOrders().getOrdersID(),
                            PatientCareActivity.patient.getPatientID(),
                            PatientCareActivity.patient.getOrders().getPrescriptionList(),
                            editFood.getText().toString(),
                            editTests.getText().toString(),
                            editProcedures.getText().toString(),
                            editIntravenous.getText().toString(),
                            editBloodTransfusion.getText().toString(),
                            (ivSignature.getDrawable() != null) ? ((BitmapDrawable) ivSignature.getDrawable()).getBitmap() : null
                    );

                    PatientsListActivity.patients.get(PatientCareActivity.position).setOrders(orders);
                    Orders.update(getActivity(), orders);

                } else { //add new
                    Orders orders = new Orders(
                            UUID.randomUUID().toString(),
                            PatientCareActivity.patient.getPatientID(),
                            PatientCareActivity.patient.getOrders().getPrescriptionList(),
                            editFood.getText().toString(),
                            editTests.getText().toString(),
                            editProcedures.getText().toString(),
                            editIntravenous.getText().toString(),
                            editBloodTransfusion.getText().toString(),
                            (ivSignature.getDrawable() != null) ? ((BitmapDrawable) ivSignature.getDrawable()).getBitmap() : null
                    );
                    PatientsListActivity.patients.get(PatientCareActivity.position).setOrders(orders);
                    Orders.create(getActivity(), orders);
                }

                Toast.makeText(getActivity(), "Saved successfully!", Toast.LENGTH_SHORT).show();

            }
        });
        return v;
    }

    private void displayDatas() {
        editFood.setText(orders.getFood());
        editTests.setText(orders.getTests());
        editProcedures.setText(orders.getProcedures());
        editIntravenous.setText(orders.getIntravenousFluid());
        editBloodTransfusion.setText(orders.getBloodTransfusion());
        ivSignature.setImageBitmap(orders.getSignature());
    }
}

