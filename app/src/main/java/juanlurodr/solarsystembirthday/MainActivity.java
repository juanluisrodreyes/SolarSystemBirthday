package juanlurodr.solarsystembirthday;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import static java.util.Calendar.DAY_OF_MONTH;
import static java.util.Calendar.MONTH;
import static java.util.Calendar.YEAR;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnDatePicker, btnTimePicker;
    EditText txtDate, txtTime;
    Calendar selectedDateTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnDatePicker = (Button) findViewById(R.id.dateButton);
        btnTimePicker = (Button) findViewById(R.id.timeButton);
        txtDate = (EditText) findViewById(R.id.dateText);
        txtTime = (EditText) findViewById(R.id.timeText);

        btnDatePicker.setOnClickListener(this);
        btnTimePicker.setOnClickListener(this);

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

    private void updateDateText() {
        txtDate.setText(new SimpleDateFormat("yyyy-MM-dd").format(selectedDateTime.getTime()));
    }

    private void updateTimeText() {
        txtTime.setText(new SimpleDateFormat("HH:mm").format(selectedDateTime.getTime()));
    }

}
