package com.example.studyplanner.ui.calendar;

import android.graphics.Color;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.studyplanner.DataBaseHelper;
import com.example.studyplanner.DateEvent;
import com.example.studyplanner.R;
import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.github.sundeepk.compactcalendarview.domain.Event;

import org.w3c.dom.Text;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class CalendarFragment extends Fragment {

//    private GalleryViewModel galleryViewModel;
    CalendarView calendarView;
    TextView dateView;
    CompactCalendarView compCalendar;
    TextView month;
    TextView studyCount, assignCount, examCount, lectureCount;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
//        galleryViewModel = new ViewModelProvider(this).get(GalleryViewModel.class);
        View root = inflater.inflate(R.layout.activity_calendar, container, false);
//        final TextView textView = root.findViewById(R.id.text_gallery);
//        galleryViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });
//        calendarView = (CalendarView) root.findViewById(R.id.calendarView);
        dateView = (TextView)root.findViewById(R.id.dateView);
        compCalendar = root.findViewById(R.id.compactcalendar_view);
        month = root.findViewById(R.id.monthView);
        studyCount = (TextView) root.findViewById(R.id.studyCount);
        assignCount = (TextView) root.findViewById(R.id.assignCount);
        examCount = (TextView) root.findViewById(R.id.examCount);
        lectureCount = (TextView) root.findViewById(R.id.lectureCount);

        Calendar cal = Calendar.getInstance();

        SimpleDateFormat month_date = new SimpleDateFormat("MMMM yyyy");
        String month_name = month_date.format(cal.getTime());
        month.setText(month_name);

        String date = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
        dateView.setText(date);
        String date2 = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(new Date());
        DataBaseHelper dataBaseHelper1 = new DataBaseHelper(getContext());
        DateEvent dateEvent = dataBaseHelper1.getEventCount(date2);
        if (dateEvent != null) {
            studyCount.setText(Integer.toString(dateEvent.getStudyCount()));
            assignCount.setText(Integer.toString(dateEvent.getAssignCount()));
            examCount.setText(Integer.toString(dateEvent.getExamsCount()));
            lectureCount.setText(Integer.toString(dateEvent.getLectureCount()));
        }

        DataBaseHelper dataBaseHelper = new DataBaseHelper(getContext());
        List<String> listDate = new ArrayList<>();
        listDate = dataBaseHelper.getAllDates();
        for (int i=0; i<listDate.size(); i++) {
            Log.i("Date",listDate.get(i));
            String tempDate = listDate.get(i);
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            try {
                Date date1 = format.parse(tempDate);
                Event tempEvent = new Event(Color.RED,date1.getTime());
                compCalendar.addEvent(tempEvent);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        compCalendar.setListener(new CompactCalendarView.CompactCalendarViewListener() {
            @Override
            public void onDayClick(Date dateClicked) {
                // get info from database
                String dateForView = (String) DateFormat.format("dd-MM-yyyy",dateClicked);
                dateView.setText(dateForView);
                String date = (String) DateFormat.format("dd/MM/yyyy",dateClicked);

                // function to display data
                //Toast.makeText(getContext(), date, Toast.LENGTH_LONG).show();
                DataBaseHelper dataBaseHelper = new DataBaseHelper(getContext());
                DateEvent dateEvent = dataBaseHelper.getEventCount(date);
                if (dateEvent != null) {
                    studyCount.setText(Integer.toString(dateEvent.getStudyCount()));
                    assignCount.setText(Integer.toString(dateEvent.getAssignCount()));
                    examCount.setText(Integer.toString(dateEvent.getExamsCount()));
                    lectureCount.setText(Integer.toString(dateEvent.getLectureCount()));
                }
            }

            @Override
            public void onMonthScroll(Date firstDayOfNewMonth) {
                Log.i("Month",""+ firstDayOfNewMonth + (String) DateFormat.format("MMM",  firstDayOfNewMonth)); // Jun);
                String monthString  = (String) DateFormat.format("MMMM yyyy",  firstDayOfNewMonth);
                month.setText(monthString);
            }
        });



//        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
//            @Override
//            public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {
//                String date = i2 +"-" +(i1 +1) +"-"+i;
//                dateView.setText(date);
//                Log.i("date",date);
//            }
//        });

        return root;
    }
}