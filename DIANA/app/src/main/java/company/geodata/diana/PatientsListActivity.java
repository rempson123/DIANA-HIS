package company.geodata.diana;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import company.geodata.diana.Adapter.PatientListAdapter;
import company.geodata.diana.Model.Assessment;
import company.geodata.diana.Model.DischargePlan;
import company.geodata.diana.Model.Orders;
import company.geodata.diana.Model.Patient;

public class PatientsListActivity extends AppCompatActivity {
    public static List<Patient> patients;
    PatientListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patients_list);

        ListView listView = (ListView) findViewById(R.id.listViewPatients);
        Bitmap dafault = BitmapFactory.decodeResource(getResources(), R.drawable.default_avatar);


        if(Patient.findAllPatients(this).size() > 0) {
            patients = Patient.findAllPatients(this);
        } else {
            patients = new ArrayList<>();

            //static datas
            Patient patient1 = new Patient("0",
                    "Dela Cruz, Juan M.",
                    "",
                    "",
                    0,
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    dafault,
                    "",
                    null,
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    ""
            );

            Patient patient2 = new Patient("1",
                    "Marasigan, Carlo G.",
                    "",
                    "",
                    0,
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    dafault,
                    "",
                    null,
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    ""
            );

            patients.add(patient1);
            patients.add(patient2);

            for (Patient p : patients) {
                Patient.create(this, p);
            }
        }



        adapter = new PatientListAdapter(this, patients);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Intent i = new Intent(PatientsListActivity.this, PatientCareActivity.class);
                i.putExtra("POSITION", position);
                startActivity(i);
            }
        });
    }
}
