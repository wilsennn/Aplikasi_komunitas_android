import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import android.os.Bundle;
import android.view.MenuItem;
import com.google.android.material.navigation.NavigationView;
public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle toggle;
    NavigationView navigationView;
    Fragment fragment;
    FragmentTransaction transaction;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        navigationView = findViewById(R.id.navigation_view);
        drawerLayout = findViewById(R.id.drawer_layout);
        toggle = new ActionBarDrawerToggle(this,
                drawerLayout, toolbar, R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                openFragment(item.getItemId());
                return true;
                                                                     }
                                                                 });
        openFragment(R.id.nav_beranda);
    }
    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawers();
        }
        else{
            super.onBackPressed();
        }
    }
    private void openFragment(int itemID) {
        fragment = null;
        switch (itemID){
            case R.id.nav_beranda:
                fragment = new FragmentBeranda();
                break;
            case R.id.nav_account:
                fragment = new FragmentAccount();
                break;
            case R.id.nav_kegiatan:
                fragment = new FragmentKegiatan();
                break;
            case R.id.nav_anggota:
                fragment = new FragmentAnggota();
                break;
        }
        if(fragment != null){
            transaction =
                    getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.frame_layout, fragment);
            transaction.commit();
        }
        drawerLayout.closeDrawers();
    }
}
