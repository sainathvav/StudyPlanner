package com.example.studyplanner.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.studyplanner.AddEventActivity;
import com.example.studyplanner.DataBaseHelper;
import com.example.studyplanner.Event;
import com.example.studyplanner.R;
import com.example.studyplanner.databinding.FragmentMainBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

/**
 * A placeholder fragment containing a simple view.
 */
public class PlaceholderFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";

    private PageViewModel pageViewModel;
    private FragmentMainBinding binding;
    private FloatingActionButton fab;
    private int index;
    private String type;

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
        pageViewModel.setIndex(index);
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {

        switch(index) {
            case 1 :
                type = "STUDY PLAN";
                break;
            case 2 :
                type = "ASSIGNMENTS";
                break;
            case 3 :
                type = "EXAMS";
                break;
            case 4 :
                type = "LECTURES";
                break;
        }

        binding = FragmentMainBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        fab = binding.fab;

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*
                DataBaseHelper dataBaseHelper = new DataBaseHelper(getContext());
                if (dataBaseHelper.addEvent(new Event(type, "HI", "DATE", "TIME", "DESCRIPTION"))) {
                    Toast.makeText(getContext(),"Success", Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(getContext(),"Failed", Toast.LENGTH_LONG).show();
                }
                 */
                Intent intent = new Intent(getContext(), AddEventActivity.class);
                intent.putExtra("type", type);
                startActivity(intent);

            }
        });

        final TextView textView = binding.sectionLabel;
        pageViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}