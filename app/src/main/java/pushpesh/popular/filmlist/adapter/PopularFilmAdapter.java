package pushpesh.popular.filmlist.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import pushpesh.popular.filmlist.R;
import pushpesh.popular.filmlist.common.Constant;
import pushpesh.popular.filmlist.model.Result;

public class PopularFilmAdapter extends RecyclerView.Adapter<PopularFilmAdapter.ViewHolder>{
    private Context mContext;
    private List<Result> results ;

    public PopularFilmAdapter(Context mContext, List<Result> results) {
        this.mContext = mContext;
        this.results = results;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.item_details, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Result resultModel = results.get(position);
        Picasso.get().load(Constant.IMAGE_URL + resultModel.getPosterPath()).into(holder.PosterImg);
        holder.item_name.setText(resultModel.getTitle());
    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView PosterImg;
        private TextView item_name;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.PosterImg = itemView.findViewById(R.id.item_details_image);
            this.item_name = itemView.findViewById(R.id.item_name);
        }
    }
}

