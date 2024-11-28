package fpt.anhdhph.anh_anhdhph25329_ass.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import fpt.anhdhph.anh_anhdhph25329_ass.R;
import fpt.anhdhph.anh_anhdhph25329_ass.adapter.LogRegAdapter;

public class FragCollReg extends Fragment {

    ViewPager2 pageLogReg;
    TabLayout tabLogReg;
    LogRegAdapter logRegAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.layout_frag_collection_logreg, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        pageLogReg = view.findViewById(R.id.pageLogReg);
        tabLogReg = view.findViewById(R.id.tabLogReg);

        logRegAdapter = new LogRegAdapter(this);
        pageLogReg.setAdapter(logRegAdapter);

        TabLayoutMediator mediator = new TabLayoutMediator(tabLogReg, pageLogReg,
                new TabLayoutMediator.TabConfigurationStrategy() {
                    @Override
                    public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                        switch (position){
                            case 0:
                                tab.setText("Login");
                                break;
                            case 1:
                                tab.setText("Register");
                                break;
                        }
                    }
                });
                mediator.attach();

    }
}
