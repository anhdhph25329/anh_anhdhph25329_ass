package fpt.anhdhph.anh_anhdhph25329_ass.fragment;

import static android.app.Activity.RESULT_OK;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import fpt.anhdhph.anh_anhdhph25329_ass.R;
import fpt.anhdhph.anh_anhdhph25329_ass.adapter.JobAdapter;
import fpt.anhdhph.anh_anhdhph25329_ass.dao.JobDAO;
import fpt.anhdhph.anh_anhdhph25329_ass.model.Job;
import fpt.anhdhph.anh_anhdhph25329_ass.screen.AddJobScreen;

public class FragJob extends Fragment {

    JobAdapter jobAdapter;
    JobDAO jobDAO;
    RecyclerView rvJob;
    FloatingActionButton btnAdd;
    ArrayList<Job> list;

    ActivityResultLauncher<Intent> getData = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult o) {
                    if(o.getResultCode() == RESULT_OK){
                        list.clear();
                        list.addAll(jobDAO.getList());
                        jobAdapter.notifyDataSetChanged();
                    }
                }
            });

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
        list = jobDAO.getList();


        rvJob.setLayoutManager(new LinearLayoutManager(getContext()));
        jobAdapter = new JobAdapter(getContext(), list);
        rvJob.setAdapter(jobAdapter);

        btnAdd.setOnClickListener(view1 -> {
            Intent intent = new Intent(getContext(), AddJobScreen.class);
            getData.launch(intent);
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        list.clear();
        list.addAll(jobDAO.getList());
        jobAdapter.notifyDataSetChanged();
    }
}
