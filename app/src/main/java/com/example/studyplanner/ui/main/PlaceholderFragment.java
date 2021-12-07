package com.example.studyplanner.ui.main;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.studyplanner.AddEventActivity;
import com.example.studyplanner.DataBaseHelper;
import com.example.studyplanner.Event;
import com.example.studyplanner.EventAdapter;
import com.example.studyplanner.R;
import com.example.studyplanner.databinding.FragmentMainBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class PlaceholderFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";

    private PageViewModel pageViewModel;
    private FragmentMainBinding binding;
    private FloatingActionButton fab;
    private int index;
    private String type;
    private RecyclerView recyclerView;
    private EventAdapter adapter;

    public static PlaceholderFragment newInstance(int index) {
        PlaceholderFragment fragment = new PlaceholderFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_SECTION_NUMBER, index);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pageViewModel = new ViewModelProvider(this).get(PageViewModel.class);
        index = 1;
        if (getArguments() != null) {
            index = getArguments().getInt(ARG_SECTION_NUMBER);
        }
        switch(index) {
            case 1:
                type = "STUDY PLAN";
                break;
            case 2:
                type = "ASSIGNMENTS";
                break;
            case 3:
                type = "EXAMS";
                break;
            case 4:
                type = "LECTURES";
                break;
        }
        //Toast.makeText(getContext(), "Going to " + type, Toast.LENGTH_SHORT).show();
        pageViewModel.setIndex(index);
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {


        binding = FragmentMainBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        fab = binding.fab;

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), AddEventActivity.class);
                intent.putExtra("type", type);
                startActivity(intent);
                //Toast.makeText(getContext(), type, Toast.LENGTH_LONG).show();
            }
        });

        pageViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@NonNull String s) {
                //Toast.makeText(getContext(), "Case " + s, Toast.LENGTH_SHORT).show();
                switch(s) {
                    case "1":
                        type = "STUDY PLAN";
                        break;
                    case "2":
                        type = "ASSIGNMENTS";
                        break;
                    case "3" :
                        type = "EXAMS";
                        break;
                    case "4" :
                        type = "LECTURES";
                        break;
                }
                ProgressDialog pd = new ProgressDialog(getContext());
                pd.setMessage("Updating");
                pd.show();
                recyclerView = binding.recyclerView;
                recyclerView.setHasFixedSize(true);
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                DataBaseHelper dataBaseHelper = new DataBaseHelper(getContext());
                List<Event> eventList = new ArrayList<>();
                if (type != null) {
                    eventList = dataBaseHelper.getAllEvents(type);
                }
                adapter = new EventAdapter(getContext(), eventList);
                recyclerView.setAdapter(adapter);
                pd.dismiss();
            }
        });

        recyclerView = binding.recyclerView;
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        DataBaseHelper dataBaseHelper = new DataBaseHelper(getContext());
        List<Event> eventList = new ArrayList<>();
        if (type != null) {
            eventList = dataBaseHelper.getAllEvents(type);
        }
        adapter = new EventAdapter(getContext(), eventList);
        recyclerView.setAdapter(adapter);

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}