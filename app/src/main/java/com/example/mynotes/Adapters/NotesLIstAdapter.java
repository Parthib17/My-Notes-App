package com.example.mynotes.Adapters;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mynotes.Models.Notes;
import com.example.mynotes.NotesClickListener;
import com.example.mynotes.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class NotesLIstAdapter extends RecyclerView.Adapter<NotesViewHoler>{

    Context context;
    List<Notes> list;
    NotesClickListener listener;

    public NotesLIstAdapter(Context context, List<Notes> list, NotesClickListener listener) {
        this.context = context;
        this.list = list;
        this.listener = listener;
    }

    @NonNull
    @Override
    public NotesViewHoler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new NotesViewHoler(LayoutInflater.from(context).inflate(R.layout.notes_list,parent,false));
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull NotesViewHoler holder, int position) {
       holder.textView_title.setText(list.get(position).getTitle());
       holder.textView_title.setSelected(true);

       holder.textView_notes.setText(list.get(position).getNotes());

        holder.textView_date.setText(list.get(position).getDate());
        holder.textView_date.setSelected(true);

        if(list.get(position).isPinned()){
            holder.imageView_pin.setImageResource(R.drawable.pinimg);
        }
        else{
            holder.imageView_pin.setImageResource(0);
        }

        int color_code = getRandomColor();
        holder.notes_container.setCardBackgroundColor(holder.itemView.getResources().getColor(color_code , null));

        holder.notes_container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onClick(list.get(holder.getAdapterPosition()));
            }
        });

        holder.notes_container.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                listener.onLongClick(list.get(holder.getAdapterPosition()), holder.notes_container);
                return true;
            }
        });
    }

    private int getRandomColor(){
        List<Integer> colorCode = new ArrayList<>();

        colorCode.add(R.color.color1);
        colorCode.add(R.color.color2);
        colorCode.add(R.color.color3);
        colorCode.add(R.color.color4);
        colorCode.add(R.color.color5);
        colorCode.add(R.color.color6);
        colorCode.add(R.color.color7);
        colorCode.add(R.color.color8);

        Random random = new Random();
        int random_color = random.nextInt(colorCode.size());
        return colorCode.get(random_color);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}

class NotesViewHoler extends RecyclerView.ViewHolder {

    CardView notes_container;
    TextView textView_title,textView_notes,textView_date;
    ImageView imageView_pin;


    public NotesViewHoler(@NonNull View itemView) {
        super(itemView);
        notes_container=itemView.findViewById(R.id.notes_container);
        textView_title=itemView.findViewById(R.id.textView_title);
        textView_notes=itemView.findViewById(R.id.textView_notes);
        textView_date=itemView.findViewById(R.id.textView_date);
        imageView_pin=itemView.findViewById(R.id.imageView_pin);
    }
}