package algonquin.cst2335.finalproject.Main;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.pexels.R;

import java.util.List;

public class WallpaperAdapter extends RecyclerView.Adapter<WallpaperAdapter.WallpaperViewHolder> {

    public Context context;
    public static List<WallpaperModel> wallpaperModelList;

    public WallpaperAdapter(Context context, List<WallpaperModel> wallpaperModelList) {
        this.context = context;
        this.wallpaperModelList = wallpaperModelList;
    }

    @NonNull
    @Override
    public WallpaperViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(context).inflate(R.layout.image_item,parent,false);
        return new WallpaperViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WallpaperViewHolder holder, @SuppressLint("RecyclerView") final int position) {

        Glide.with(context).load(wallpaperModelList.get(position).getMediumUrl()).into(holder.imageView);
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context, FullScreenWallpaper.class)
                .putExtra("originalUrl",wallpaperModelList.get(position).getOriginalUrl()));
            }
        });

    }

//    public boolean removeItem(int position){
////        wallpaperModelList.remove(position);
////        notifyItemRemoved(position);
////        notifyItemRangeChanged(position, wallpaperModelList.size());
////        return true;
////    }

    @Override
    public int getItemCount() {
        return wallpaperModelList.size();
    }

    public static class WallpaperViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;

        public WallpaperViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.imageViewItem);

        }
    }
}

