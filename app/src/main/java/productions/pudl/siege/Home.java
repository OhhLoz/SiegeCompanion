package productions.pudl.siege;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        BottomBar navigation = findViewById(R.id.bottomBar);

        final Intent statsIntent = new Intent(this, Stats.class);
        final Intent historyIntent = new Intent(this, History.class);
        final Intent profileIntent = new Intent(this, Profile.class);
        final Intent savedIntent = new Intent(this, Saved.class);

        navigation.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId)
            {
                if (tabId == R.id.navigation_home) {
                    // The tab with id R.id.tab_favorites was selected,
                    // change your content accordingly.
                }
                else if (tabId == R.id.navigation_history){
                    startActivity(historyIntent);
                }
                else if (tabId == R.id.navigation_profile) {
                    startActivity(profileIntent);
                }
                else if (tabId == R.id.navigation_saved) {
                    startActivity(savedIntent);
                }
                else if (tabId == R.id.navigation_stats) {
                    startActivity(statsIntent);
                }
            }
        });
    }

}
