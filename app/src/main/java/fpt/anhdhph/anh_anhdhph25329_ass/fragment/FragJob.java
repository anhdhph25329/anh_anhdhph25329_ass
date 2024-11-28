package fpt.anhdhph.anh_anhdhph25329_ass.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import fpt.anhdhph.anh_anhdhph25329_ass.R;
import fpt.anhdhph.anh_anhdhph25329_ass.adapter.JobAdapter;
import fpt.anhdhph.anh_anhdhph25329_ass.dao.JobDAO;
import fpt.anhdhph.anh_anhdhph25329_ass.model.Job;

public class FragJob extends Fragment {

    JobAdapter jobAdapter;
    JobDAO jobDAO;
    Job job;
    RecyclerView rvJob;
    FloatingActionButton btnAdd;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.layout_frag_job, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvJob = view.findViewById(R.id.rvJob);
        btnAdd = view.findViewById(R.id.btnAdd);
        jobDAO = new JobDAO(getContext());


        btnAdd.setOnClickListener(view1 -> {
            //xu ly them

        });
    }
}
