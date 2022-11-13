package com.example.mytestproject.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mytestproject.DataManager;
import com.example.mytestproject.Images;
import com.example.mytestproject.MainActivity;
import com.example.mytestproject.R;
import com.example.mytestproject.ui.main.DifficultyFragment;
import com.example.mytestproject.ui.main.QuestionFragment;
import com.example.mytestproject.ui.main.SecondFragment;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.MyViewHolder> {
    ArrayList<String> categories;
    Context context;
    DataManager data;


    public CategoriesAdapter(ArrayList<String> categories) {
        this.categories = categories;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_row,parent,false);
        context=parent.getContext();
        data = DataManager.getDataManager();
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        String testString = categories.get(position);
    holder.tvCategory.setText(testString);
    holder.score.setText("high score: "+String.valueOf(data.getHighScorePerCategory()[position]));
    holder.rowImg.setImageResource(Images.categoryImages[position]);
    holder.tvCategory.setOnClickListener(view -> {

        DataManager data = DataManager.getDataManager();
        data.setCategory(testString);
        data.setCategoryIndex(position);
        FragmentManager manager = ((AppCompatActivity)context).getSupportFragmentManager();
      manager.beginTransaction()
                .replace(R.id.container, DifficultyFragment.newInstance())
                .commitNow();
    });
    }

    @Override
    public int getItemCount() {
        if(categories!=null){
            return categories.size();
        }
        return 0;
    }

    static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView score;
        TextView tvCategory;
        ImageView rowImg;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvCategory = itemView.findViewById(R.id.tv_categoty);
            score = itemView.findViewById(R.id.row_top_score);
            rowImg = itemView.findViewById(R.id.row_img);

        }

    }
}
