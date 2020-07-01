package com.app.hippotherapy.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.app.hippotherapy.HistoryActivity;
import com.app.hippotherapy.R;
import com.app.hippotherapy.TasksActivity;
import com.app.hippotherapy.model.Session;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder> {
    private List<Session> sessionsList;
    private String userName;
    private Context context;

    public HistoryAdapter(List<Session> sessionsList, String userName, Context context) {
        this.sessionsList = sessionsList;
        this.userName = userName;
        this.context = context;
    }

    public class HistoryViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.img_background)
        ImageView imgBackground;
        @BindView(R.id.img_day)
        ImageView imgDay;
        @BindView(R.id.img_rating)
        ImageView imgRating;
        @BindView(R.id.txt_time)
        TextView txtTime;
        @BindView(R.id.txt_comment)
        TextView txtComment;
        @BindView(R.id.img_mail)
        ImageView imgMail;

        public HistoryViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    @Override
    public HistoryAdapter.HistoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int layoutIdForListItem = R.layout.history_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(layoutIdForListItem, parent, false);
        return new HistoryAdapter.HistoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(HistoryAdapter.HistoryViewHolder holder, int position) {
        final Session session = sessionsList.get(position);
        int dayNo = position + 1;

        int bg_resId = context.getResources().getIdentifier("comment_bg_" + (dayNo > 9 ? dayNo : "0" + dayNo), "drawable", "com.app.hippotherapy");
        holder.imgBackground.setImageResource(bg_resId);

        int day_resId = context.getResources().getIdentifier("comment_day_" + dayNo, "drawable", "com.app.hippotherapy");
        holder.imgDay.setImageResource(day_resId);

        int rate_resId = context.getResources().getIdentifier("history_rate_" + session.getRating(), "drawable", "com.app.hippotherapy");
        holder.imgRating.setImageResource(rate_resId);

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        final String format = sdf.format(session.getDateCompleted().longValue());
        holder.txtTime.setText("Έγινε στις " + format);

        holder.txtComment.setText(session.getComment());

        holder.imgMail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.setType("text/plain");
                sendIntent.putExtra(Intent.EXTRA_TEXT, format + "\n" + userName + "\n" + session.getRating());

                Intent shareIntent = Intent.createChooser(sendIntent, "Share via");
                context.startActivity(shareIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return (sessionsList != null) ? sessionsList.size() : 0;
    }
}
