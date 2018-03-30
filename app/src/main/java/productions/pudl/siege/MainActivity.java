package productions.pudl.siege;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

public class MainActivity extends AppCompatActivity
{
    BottomBar bottomBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomBar = (BottomBar) findViewById(R.id.bottomBar);

        bottomBar.setOnTabSelectListener(new OnTabSelectListener()
        {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                if (tabId == R.id.navigation_home) {
                    Home home = new Home();
                    getSupportFragmentManager().beginTransaction().replace(R.id.contentContainer, home).commit();
                    setTitle(R.string.title_home);
                }
                else if (tabId == R.id.navigation_history){
                    History history = new History();
                    getSupportFragmentManager().beginTransaction().replace(R.id.contentContainer, history).commit();
                    setTitle(R.string.title_history);
                }
                else if (tabId == R.id.navigation_profile) {
                    Profile profile = new Profile();
                    getSupportFragmentManager().beginTransaction().replace(R.id.contentContainer, profile).commit();
                    setTitle(R.string.title_profile);
                }
                else if (tabId == R.id.navigation_saved) {
                    Saved saved = new Saved();
                    getSupportFragmentManager().beginTransaction().replace(R.id.contentContainer, saved).commit();
                    setTitle(R.string.title_saved);
                }
                else if (tabId == R.id.navigation_stats) {
                    Stats stats = new Stats();
                    getSupportFragmentManager().beginTransaction().replace(R.id.contentContainer, stats).commit();
                    setTitle(R.string.title_stats);
                }
            }
        });
    }
}