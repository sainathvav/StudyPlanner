package com.example.studyplanner;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.MyHolder> {

    Context context;
    List<Event> list;

    public EventAdapter(Context context, List<Event> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.all_events, parent, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, @SuppressLint("RecyclerView") final int position) {
        String eventTitle = list.get(position).getTitle();
        String eventDate = list.get(position).getDate();
        String eventTime = list.get(position).getTime();
        String eventDescription = list.get(position).getDescription();

        holder.title.setText(eventTitle);
        holder.date.setText(eventDate);
        holder.time.setText(eventTime);
        holder.description.setText(eventDescription);

        holder.remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataBaseHelper dataBaseHelper = new DataBaseHelper(v.getContext());
                boolean result = dataBaseHelper.removeEvent(list.get(position));
                if (result) {
                    Toast.makeText(v.getContext(), "Successfully removed the event",Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(v.getContext(), LeftSlideBarActivity.class);
                    context.startActivity(intent);
                }
                else {
                    //Toast.makeText(v.getContext(), "Failed",Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyHolder extends RecyclerView.ViewHolder {
        TextView title, date, time, description;
        Button remove;
        public MyHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.title);
            date = itemView.findViewById(R.id.date);
            time = itemView.findViewById(R.id.time);
            description = itemView.findViewById(R.id.description);
            remove = itemView.findViewById(R.id.remove);

        }
    }
}
