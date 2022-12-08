package algonquin.cst2335.finalproject;

import static androidx.constraintlayout.widget.Constraints.TAG;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import algonquin.cst2335.finalproject.WallpaperModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DatabaseAdapter extends RecyclerView.Adapter<DatabaseAdapter.Holder> {
    Context context;
    private ArrayList<WallpaperModel> wallpaper;

    public DatabaseAdapter(Context context, ArrayList<WallpaperModel> wallpaper, FavoriteActivity favoriteActivity) {
        this.context = context;
        this.wallpaper = wallpaper;
    }


    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.single_db , parent, false);
        Holder holder = new Holder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        WallpaperModel wallpaperModel = wallpaper.get(position);

        Glide.with(holder.url.getContext()).load(wallpaperModel.getOriginalUrl()).into(holder.url);

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
                Query applesQuery = ref.child("Favorites");

                applesQuery.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot appleSnapshot: dataSnapshot.getChildren()) {
                            appleSnapshot.getRef().removeValue();
                        }
                        Toast.makeText(context, "Item Removed", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Log.e(TAG, "onCancelled", databaseError.toException());
                    }
                });
            }
        });

    }



    @Override
    public int getItemCount() {
        return wallpaper.size();
    }

    public static class Holder extends RecyclerView.ViewHolder {
        private ImageView url;
        private ImageView delete;

        public Holder(@NonNull View itemView) {
            super(itemView);
            url = itemView.findViewById(R.id.imageViewItem);
            delete = itemView.findViewById(R.id.deleteBTn);
        }

    }

}
