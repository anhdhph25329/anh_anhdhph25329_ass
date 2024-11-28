package fpt.anhdhph.anh_anhdhph25329_ass.screen;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.navigation.NavigationView;

import fpt.anhdhph.anh_anhdhph25329_ass.R;
import fpt.anhdhph.anh_anhdhph25329_ass.fragment.FragInfo;
import fpt.anhdhph.anh_anhdhph25329_ass.fragment.FragJob;
import fpt.anhdhph.anh_anhdhph25329_ass.fragment.FragLog;

public class JobManage extends AppCompatActivity {

    DrawerLayout drawerLayout;
    Toolbar toolbar;
    NavigationView nav;

    FragmentManager fm;
    FragJob fragJob;
    FragInfo fragInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_job_manage);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        anhXa();


        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(
                this,
                drawerLayout,
                toolbar,
                R.string.chuoi_open,
                R.string.chuoi_close
        );

        drawerToggle.setDrawerIndicatorEnabled(true);
        drawerToggle.syncState();
        drawerLayout.addDrawerListener( drawerToggle );

        fm.beginTransaction().add(R.id.frag_container, fragJob).commit();
        nav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.btnJob) {
                    fm.beginTransaction().replace(R.id.frag_container, fragJob).commit();
                    toolbar.setTitle("Danh sách công việc");
                } else if (item.getItemId() == R.id.btnInfo) {
                    fm.beginTransaction().replace(R.id.frag_container, fragInfo).commit();
                    toolbar.setTitle("Thông tin cá nhân");
                }else {
                    fm.beginTransaction().replace(R.id.frag_container, fragJob).commit();
                    toolbar.setTitle("Danh sách công việc");
                }
                drawerLayout.close();
                return false;
            }
        });

    }

    void anhXa() {
        drawerLayout = findViewById(R.id.main);
        toolbar = findViewById(R.id.tb);
        nav = findViewById(R.id.menuDrawer);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Danh sách công việc");

        fm = getSupportFragmentManager();
        fragJob = new FragJob();
        fragInfo = new FragInfo();
    }

}