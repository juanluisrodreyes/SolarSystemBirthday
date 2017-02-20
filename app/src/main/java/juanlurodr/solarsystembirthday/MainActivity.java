package juanlurodr.solarsystembirthday;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.LinkedList;

import static java.util.Calendar.DAY_OF_MONTH;
import static java.util.Calendar.MONTH;
import static java.util.Calendar.YEAR;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    private static String APP_TAG = "SolarSystemBirthday";

    //Tropical orbit periods are taken from http://nssdc.gsfc.nasa.gov/planetary/factsheet/

    private static double TROPICAL_PERIOD_MERCURY = 87.968;
    private static double TROPICAL_PERIOD_VENUS = 224.695;
    private static double TROPICAL_PERIOD_EARTH = 365.242;
    private static double TROPICAL_PERIOD_MARS = 686.973;
    private static double TROPICAL_PERIOD_JUPITER = 4330.595;
    private static double TROPICAL_PERIOD_SATURN = 10746.94;
    private static double TROPICAL_PERIOD_URANUS = 30588.740;
    private static double TROPICAL_PERIOD_NEPTUNE = 59799.9;

    Button btnDatePicker, btnTimePicker;
    EditText txtDate, txtTime;
    Spinner planetSpinner;

    Calendar selectedDateTime;
    Planet selectedPlanet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnDatePicker = (Button) findViewById(R.id.dateButton);
        btnTimePicker = (Button) findViewById(R.id.timeButton);
        txtDate = (EditText) findViewById(R.id.dateText);
        txtTime = (EditText) findViewById(R.id.timeText);
        planetSpinner = (Spinner) findViewById(R.id.planetSpinner);

        LinkedList<Planet> planets = new LinkedList<>();
        planets.add(new Planet(getString(R.string.mercury), TROPICAL_PERIOD_MERCURY));
        planets.add(new Planet(getString(R.string.venus), TROPICAL_PERIOD_VENUS));
        planets.add(new Planet(getString(R.string.earth), TROPICAL_PERIOD_EARTH));
        planets.add(new Planet(getString(R.string.mars), TROPICAL_PERIOD_MARS));
        planets.add(new Planet(getString(R.string.jupiter), TROPICAL_PERIOD_JUPITER));
        planets.add(new Planet(getString(R.string.saturn), TROPICAL_PERIOD_SATURN));
        planets.add(new Planet(getString(R.string.uranus), TROPICAL_PERIOD_URANUS));
        planets.add(new Planet(getString(R.string.neptune), TROPICAL_PERIOD_NEPTUNE));

        ArrayAdapter<Planet> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, planets);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        planetSpinner.setAdapter(adapter);

        btnDatePicker.setOnClickListener(this);
        btnTimePicker.setOnClickListener(this);
        planetSpinner.setOnItemSelectedListener(this);

        selectedDateTime = GregorianCalendar.getInstance();
        updateDateText();
        updateTimeText();
    }

    @Override
    public void onClick(View v) {
        if (v == btnDatePicker) {
            DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                    selectedDateTime.set(Calendar.YEAR, year);
                    selectedDateTime.set(Calendar.MONTH, month);
                    selectedDateTime.set(Calendar.DAY_OF_MONTH, day);
                    updateDateText();
                }
            }, selectedDateTime.get(Calendar.YEAR), selectedDateTime.get(Calendar.MONTH), selectedDateTime.get(Calendar.DAY_OF_MONTH));
            datePickerDialog.show();
        }
        if (v == btnTimePicker) {
            TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                    new TimePickerDialog.OnTimeSetListener() {
                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay,
                                              int minute) {
                            selectedDateTime.set(Calendar.HOUR_OF_DAY, hourOfDay);
                            selectedDateTime.set(Calendar.MINUTE, minute);
                            updateTimeText();
                        }
                    }, selectedDateTime.get(Calendar.HOUR_OF_DAY), selectedDateTime.get(Calendar.MINUTE), true);
            timePickerDialog.show();
        }
    }

    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        selectedPlanet = (Planet) parent.getItemAtPosition(pos);
        Log.d(APP_TAG, String.format("Selected planet %s", selectedPlanet.toString()));
    }

    public void onNothingSelected(AdapterView<?> parent) {
        // Do nothing
    }

    private void updateDateText() {
        txtDate.setText(new SimpleDateFormat("yyyy-MM-dd").format(selectedDateTime.getTime()));
    }

    private void updateTimeText() {
        txtTime.setText(new SimpleDateFormat("HH:mm").format(selectedDateTime.getTime()));
    }

}
