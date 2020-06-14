package com.example.samue.sensores;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private SensorManager sensorManager;
    private List<Sensor> sensorList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensorList = sensorManager.getSensorList(Sensor.TYPE_ALL);
        // As linhas acima pegam a lista dos sensores
        List<String> nomes = new ArrayList<String>();
        for (Sensor s : sensorList)
        {
            nomes.add(s.getName() + " - " + s.getVendor() + " - " +
                    s.getType()); // parametros qu iremos listar
        }
        ListView listView = (ListView)findViewById(R.id.lstSensores);
        listView.setOnItemClickListener(this);
        int layout = android.R.layout.simple_list_item_1;
        ArrayAdapter<String>adaptador = new ArrayAdapter<String>(this, layout, nomes);
        listView.setAdapter(adaptador);

    }


    @Override
    public void onItemClick(AdapterView<?> AdapterView, View view, int i, long l) {
        Sensor sensor = sensorList.get(i);
        String msg = sensor.getName() + " - " + sensor.getVendor();
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, TestSensorActivity.class);
        intent.putExtra("position", i);
        startActivity(intent);
    }
}
