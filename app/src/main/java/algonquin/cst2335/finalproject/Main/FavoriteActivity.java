package algonquin.cst2335.finalproject.Main;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pexels.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class FavoriteActivity extends AppCompatActivity {
    private final Context context=this;

    private ArrayList<WallpaperModel> wallpaperModels;
    private DatabaseAdapter wallpaperAdapter;
    private RecyclerView wallpaperList;
    private static ArrayList<String> sKey =new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        loadArray(FavoriteActivity.this);

        wallpaperList = new RecyclerView(context);

       /*
        Category Recycler View Config.
        */
        wallpaperList = findViewById(R.id.recyclerView);
        wallpaperModels = new ArrayList<>();

        fetchFavorites();
    }

    /*
        Fetch Favorites
         */
    private void fetchFavorites() {
//        clearAll();
        //Retrieve categories.
        for(int i = 0;i<sKey.size();i++){
            WallpaperModel wallpaper = new WallpaperModel();
            wallpaper.setOriginalUrl(sKey.get(i));
            wallpaperModels.add(wallpaper);
        }
        wallpaperAdapter = new DatabaseAdapter(getApplicationContext(),wallpaperModels,FavoriteActivity.this);
        wallpaperList.setLayoutManager(new LinearLayoutManager(FavoriteActivity.this));
        wallpaperList.setAdapter(wallpaperAdapter);
        wallpaperAdapter.notifyDataSetChanged();
    }




    public static void loadArray(Context mContext)
    {
        SharedPreferences mSharedPreference1 =   PreferenceManager.getDefaultSharedPreferences(mContext);
        sKey.clear();
        int size = mSharedPreference1.getInt("Status_size", 0);

        for(int i=0;i<size;i++)
        {
            sKey.add(mSharedPreference1.getString("Status_" + i, null));
        }

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==android.R.id.home){
            finish();
        }
        return true;
    }
}