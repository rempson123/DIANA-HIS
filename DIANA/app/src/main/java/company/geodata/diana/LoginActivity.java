package company.geodata.diana;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import java.lang.reflect.Method;

import company.geodata.diana.Model.Orders;

public class LoginActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = (Button) findViewById(R.id.btnLogin);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LoginActivity.this, PatientsListActivity.class);
                startActivity(i);
                /*Orders orders = Orders.findOrders(LoginActivity.this, new Orders("0"));
                Log.d("CHECK", "onClick: " + orders.getPatientID());*/
                Toast.makeText(LoginActivity.this, "Welcome to Diana-HIS", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
