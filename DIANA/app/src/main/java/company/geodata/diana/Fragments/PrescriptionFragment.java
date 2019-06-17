package company.geodata.diana.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentManagerNonConfig;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.InflateException;
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
import company.geodata.diana.Dialogs.DropdownDialog;
import company.geodata.diana.Model.Patient;
import company.geodata.diana.Model.Prescription;
import company.geodata.diana.PatientCareActivity;
import company.geodata.diana.R;

/**
 * Created by jcmate on 6/8/2017.
 */

public class PrescriptionFragment extends Fragment {

    View v;
    static EditText medicine;
    static EditText genericName;
    static ImageView search;
    static EditText dose;
    static EditText unit;
    static EditText quantity;
    static EditText dosage;
    static EditText numberOfTake;
    static EditText type;
    static EditText refillInstructions;
    static Button buttonAddToList;
    static Button buttonCancel;

    private RecyclerView recyclerView;
    private PrescriptionsAdapter adapter;
    public static int position = -1;

    public static String ORDERS = "orders";
    public static String DISCHARGE_PLAN = "discharge_plan";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState != null) {

        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        try {
            v = inflater.inflate(R.layout.fragment_prescription, container, false);
            medicine = (EditText) v.findViewById(R.id.editTextMedicine);
            genericName = (EditText) v.findViewById(R.id.editTextGenericName);
            search = (ImageView) v.findViewById(R.id.imageViewSearch);
            dose = (EditText) v.findViewById(R.id.editTextDose);
            unit = (EditText) v.findViewById(R.id.editTextUnit);
            quantity = (EditText) v.findViewById(R.id.editTextQuantity);
            dosage = (EditText) v.findViewById(R.id.editTextDosage);
            numberOfTake = (EditText) v.findViewById(R.id.editTextNumberOfTake);
            type = (EditText) v.findViewById(R.id.editTextType);
            refillInstructions = (EditText) v.findViewById(R.id.editTextRefillInstructions);
            buttonAddToList = (Button) v.findViewById(R.id.buttonAddToList);
            buttonCancel = (Button) v.findViewById(R.id.buttonCancel);
            recyclerView = (RecyclerView) v.findViewById(R.id.recyclerView);

            setNonFocusableEditText();

            if (getFragmentManager().findFragmentById(R.id.prescriptionFragment).getTag().equals("orders")) {


                List<Prescription> pres = Prescription.findAllPatientPrescription(getActivity(), PatientCareActivity.patient, ORDERS);
                if (pres.size() > 0)
                    PatientCareActivity.patient.getOrders().setPrescriptionList(pres);

                //if the Orders Tab is selected, use this arrayList from Orders Model
                adapter = new PrescriptionsAdapter(PatientCareActivity.patient.getOrders().getPrescriptionList());
            }
            else{
                List<Prescription> pres = Prescription.findAllPatientPrescription(getActivity(), PatientCareActivity.patient, DISCHARGE_PLAN);
                if (pres.size() > 0)
                    PatientCareActivity.patient.getDischargePlan().setPrescriptionList(pres);

                //if the Discharge Plan Tab  is selected, use this arrayList from Discharge Paln Model
                adapter = new PrescriptionsAdapter(PatientCareActivity.patient.getDischargePlan().getPrescriptionList());

            }

            unit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    DropdownDialog dialog = new DropdownDialog((EditText) view, getResources().getStringArray(R.array.array_units));
                    dialog.show(getFragmentManager(), "Units");
                }
            });
            dosage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    DropdownDialog dialog = new DropdownDialog((EditText) view, getResources().getStringArray(R.array.array_dosage));
                    dialog.show(getFragmentManager(), "Dosage");
                }
            });
            numberOfTake.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    DropdownDialog dialog = new DropdownDialog((EditText) view, getResources().getStringArray(R.array.array_num_of_take));
                    dialog.show(getFragmentManager(), "Number of take");
                }
            });
            type.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    DropdownDialog dialog = new DropdownDialog((EditText) view, getResources().getStringArray(R.array.array_type));
                    dialog.show(getFragmentManager(), "Type");

                }
            });

            buttonAddToList.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if (medicine.getText().length() > 0 &&
                            genericName.getText().length() > 0 &&
                            dose.getText().length() > 0 &&
                            unit.getText().length() > 0 &&
                            dosage.getText().length() > 0 &&
                            numberOfTake.getText().length() > 0 &&
                            type.getText().length() > 0) {

                        /**update*/
                        if (position > -1) {
                            //if the Orders Tab is selected
                            if (getFragmentManager().findFragmentById(R.id.prescriptionFragment).getTag().equals("orders")) {
                                Prescription prescription = new Prescription(
                                        PatientCareActivity.patient.getOrders().getPrescriptionList().get(position).getPrescriptionID(),
                                        PatientCareActivity.patient.getPatientID(),
                                        medicine.getText().toString(),
                                        genericName.getText().toString(),
                                        Integer.parseInt(dose.getText().toString()),
                                        unit.getText().toString(),
                                        Integer.parseInt(quantity.getText().toString()),
                                        dosage.getText().toString(),
                                        numberOfTake.getText().toString(),
                                        type.getText().toString(),
                                        refillInstructions.getText().toString(),
                                        ORDERS);

                                PatientCareActivity.patient.getOrders().getPrescriptionList().set(position, prescription);
                                Prescription.update(getActivity(), prescription);


                                //if the Discharge Plan Tab is selected
                            } else {
                                Prescription prescription = new Prescription(
                                        PatientCareActivity.patient.getDischargePlan().getPrescriptionList().get(position).getPrescriptionID(),
                                        PatientCareActivity.patient.getPatientID(),
                                        medicine.getText().toString(),
                                        genericName.getText().toString(),
                                        Integer.parseInt(dose.getText().toString()),
                                        unit.getText().toString(),
                                        Integer.parseInt(quantity.getText().toString()),
                                        dosage.getText().toString(),
                                        numberOfTake.getText().toString(),
                                        type.getText().toString(),
                                        refillInstructions.getText().toString(),
                                        DISCHARGE_PLAN);

                                PatientCareActivity.patient.getDischargePlan().getPrescriptionList().set(position, prescription);
                                Prescription.update(getActivity(), prescription);
                            }

                            buttonAddToList.setText("Add");
                            buttonCancel.setVisibility(View.GONE);
                        } else {
                            //if the Orders Tab is selected
                            if (getFragmentManager().findFragmentById(R.id.prescriptionFragment).getTag().equals("orders")) {
                                Prescription prescription = new Prescription(
                                        UUID.randomUUID().toString(),
                                        PatientCareActivity.patient.getPatientID(),
                                        medicine.getText().toString(),
                                        genericName.getText().toString(),
                                        Integer.parseInt(dose.getText().toString()),
                                        unit.getText().toString(),
                                        Integer.parseInt(quantity.getText().toString()),
                                        dosage.getText().toString(),
                                        numberOfTake.getText().toString(),
                                        type.getText().toString(),
                                        refillInstructions.getText().toString(),
                                        ORDERS);
                                PatientCareActivity.patient.getOrders().getPrescriptionList().add(prescription);
                                Prescription.create(getActivity(), prescription);

                                //if the Discharge Plan Tab is selected
                            } else {
                                Prescription prescription =new Prescription(
                                        UUID.randomUUID().toString(),
                                        PatientCareActivity.patient.getPatientID(),
                                        medicine.getText().toString(),
                                        genericName.getText().toString(),
                                        Integer.parseInt(dose.getText().toString()),
                                        unit.getText().toString(),
                                        Integer.parseInt(quantity.getText().toString()),
                                        dosage.getText().toString(),
                                        numberOfTake.getText().toString(),
                                        type.getText().toString(),
                                        refillInstructions.getText().toString(),
                                        DISCHARGE_PLAN);
                                PatientCareActivity.patient.getDischargePlan().getPrescriptionList().add(prescription);
                                Prescription.create(getActivity(), prescription);
                            }
                        }
                        adapter.notifyDataSetChanged();
                        clearFields();

                    } else {
                        Toast.makeText(getActivity(), "Please fill up the required fields", Toast.LENGTH_SHORT).show();
                    }

                }
            });

            buttonCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    clearFields();
                    buttonAddToList.setText("Add");
                    buttonCancel.setVisibility(View.GONE);
                }
            });

            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
            recyclerView.setLayoutManager(mLayoutManager);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.setAdapter(adapter);


        } catch (InflateException e) {

        }
        return v;
    }

    void setNonFocusableEditText() {
        unit.setFocusable(false);
        dosage.setFocusable(false);
        numberOfTake.setFocusable(false);
        type.setFocusable(false);
    }


    public static void update(Prescription prescription, int pos) {
        medicine.setText(prescription.getMedicine());
        genericName.setText(prescription.getGenericName());
        dose.setText(String.valueOf(prescription.getDose()));
        unit.setText(prescription.getUnit());
        quantity.setText(String.valueOf(prescription.getQuantity()));
        dosage.setText(prescription.getDosage());
        numberOfTake.setText(prescription.getNumberOfTake());
        type.setText(prescription.getType());
        refillInstructions.setText(prescription.getRefillInstructions());
        position = pos;
        if (pos > -1) {
            buttonAddToList.setText("Update");
            buttonCancel.setVisibility(View.VISIBLE);
        }
        else {
            buttonAddToList.setText("Add");
            buttonCancel.setVisibility(View.GONE);
        }
    }

    void clearFields(){
        medicine.setText("");
        genericName.setText("");
        dose.setText("0");
        unit.setText("");
        quantity.setText("0");
        dosage.setText("");
        numberOfTake.setText("");
        type.setText("");
        refillInstructions.setText("");
        position = -1;
    }

    public void onDestroyView() {
        super.onDestroyView();
        try {
            Fragment fragment = (getFragmentManager().findFragmentById(R.id.prescriptionFragment));
            FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
            ft.remove(fragment);
            ft.commit();
        } catch (Exception e) {

        }

    }
}
