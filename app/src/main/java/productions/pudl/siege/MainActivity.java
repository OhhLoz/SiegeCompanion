package productions.pudl.siege;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

import productions.pudl.siege.Fragment.Compare;
import productions.pudl.siege.Fragment.More;
import productions.pudl.siege.Fragment.Operators;
import productions.pudl.siege.Fragment.Profile;
import productions.pudl.siege.Fragment.Search.Search;

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
                if (tabId == R.id.navigation_compare) {
                    Compare compare = new Compare();
                    getSupportFragmentManager().beginTransaction().replace(R.id.contentContainer, compare).commit();
                    setTitle(R.string.title_compare);
                }
                else if (tabId == R.id.navigation_operators){
                    Operators operators = new Operators();
                    getSupportFragmentManager().beginTransaction().replace(R.id.contentContainer, operators).commit();
                    setTitle(R.string.title_operators);
                }
                else if (tabId == R.id.navigation_profile) {
                    Profile profile = new Profile();
                    getSupportFragmentManager().beginTransaction().replace(R.id.contentContainer, profile).commit();
                    setTitle(R.string.title_profile);
                }
                else if (tabId == R.id.navigation_search) {
                    Search search = new Search();
                    getSupportFragmentManager().beginTransaction().replace(R.id.contentContainer, search).commit();
                    setTitle(R.string.title_search);
                }
                else if (tabId == R.id.navigation_more) {
                    More more = new More();
                    getSupportFragmentManager().beginTransaction().replace(R.id.contentContainer, more).commit();
                    setTitle(R.string.title_more);
                }
            }
        });
    }
}