package fpt.anhdhph.anh_anhdhph25329_ass.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import fpt.anhdhph.anh_anhdhph25329_ass.fragment.FragLog;
import fpt.anhdhph.anh_anhdhph25329_ass.fragment.FragReg;

public class LogRegAdapter extends FragmentStateAdapter {

    FragLog fragLog;
    FragReg fragReg;
    int soLuongTab = 2;

    public LogRegAdapter(@NonNull Fragment fragment) {
        super(fragment);
        fragLog = new FragLog();
        fragReg = new FragReg();
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 1:
                return fragReg;
            default:
                return fragLog;
        }
    }

    @Override
    public int getItemCount() {
        return soLuongTab;
    }
}
