package algonquin.cst2335.finalproject.Main;

import static androidx.constraintlayout.widget.Constraints.TAG;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.pexels.R;

import java.util.ArrayList;

public class DatabaseAdapter extends RecyclerView.Adapter<DatabaseAdapter.Holder> {
    //Initialize ArrayLists and context

    Context context;
    private ArrayList<WallpaperModel> wallpaper;
    private static ArrayList<String> sKey = new ArrayList<>();

    public DatabaseAdapter(Context context, ArrayList<WallpaperModel> wallpaper, FavoriteActivity favoriteActivity) {
        this.context = context;
        this.wallpaper = wallpaper;
    }


    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.single_db, parent, false);
        Holder holder = new Holder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {

        //Load ArrayList from Room Database
        loadArray(holder.itemView.getContext());
        WallpaperModel wallpaperModel = wallpaper.get(position);

        Glide.with(holder.url.getContext()).load(wallpaperModel.getOriginalUrl()).into(holder.url);

        //Delete image from Room Database
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                for(int i = 0;i<sKey.size();i++){
                    if(sKey.get(i).equals(wallpaperModel.getOriginalUrl())){
                        sKey.remove(i);
                        saveArray(view.getContext());
                    }
                }

            }
        });

    }


    @Override
    public int getItemCount() {
        return wallpaper.size();
    }

    public static class Holder extends RecyclerView.ViewHolder {
        private ImageView url;
        private Button delete;

        public Holder(@NonNull View itemView) {
            super(itemView);
            url = itemView.findViewById(R.id.imageViewItem);
            delete = itemView.findViewById(R.id.deleteBTn);
        }

    }

    public static boolean saveArray(Context context) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor mEdit1 = sp.edit();
        /* sKey is an array */
        mEdit1.putInt("Status_size", sKey.size());

        for (int i = 0; i < sKey.size(); i++) {
            mEdit1.remove("Status_" + i);
            mEdit1.putString("Status_" + i, sKey.get(i));
        }
        return mEdit1.commit();
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
}
