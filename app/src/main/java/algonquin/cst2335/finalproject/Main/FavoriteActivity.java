package algonquin.cst2335.finalproject.Main;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pexels.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class FavoriteActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    private final Context context=this;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    private ArrayList<WallpaperModel> wallpaperModels;
    private DatabaseAdapter wallpaperAdapter;
    private RecyclerView wallpaperList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);

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
    DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
    Query query = reference.child("Favorites");
    query.addValueEventListener(new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {
            wallpaperList.clearOnChildAttachStateChangeListeners();
            for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                WallpaperModel wallpaper = new WallpaperModel();
                wallpaper.setOriginalUrl(snapshot1.child("url").getValue().toString());
                wallpaperModels.add(wallpaper);
            }
            wallpaperAdapter = new DatabaseAdapter(getApplicationContext(),wallpaperModels,FavoriteActivity.this);
            wallpaperList.setLayoutManager(new LinearLayoutManager(FavoriteActivity.this));
            wallpaperList.setAdapter(wallpaperAdapter);
            wallpaperAdapter.notifyDataSetChanged();
        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {

        }
    });
}



}