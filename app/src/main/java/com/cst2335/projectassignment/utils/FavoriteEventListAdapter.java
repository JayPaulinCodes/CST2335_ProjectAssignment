package com.cst2335.projectassignment.utils;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.cst2335.projectassignment.R;
import com.cst2335.projectassignment.activities.ActivityFavorites;
import com.cst2335.projectassignment.activities.JActivity;
import com.cst2335.projectassignment.objects.Event;

import java.util.ArrayList;

// TODO: Add JavaDoc Comment
public class FavoriteEventListAdapter extends BaseAdapter {

    private JActivity jActivity;
    private ArrayList<Event> list;

    // TODO: Add JavaDoc Comment
    public FavoriteEventListAdapter(ArrayList<Event> list, JActivity jActivity) {
        super();
        this.jActivity = jActivity;
        this.list = list;
    }

    // TODO: Add JavaDoc Comment
    public void setList(ArrayList<Event> events) {
        this.list = events;
    }

    // TODO: Add JavaDoc Comment
    @Override
    public int getCount() {
        return list.size();
    }

    // TODO: Add JavaDoc Comment
    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    // TODO: Add JavaDoc Comment
    @Override
    public long getItemId(int position) {
        return 0;
    }

    // TODO: Add JavaDoc Comment
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Event event = list.get(position);
        View view = jActivity.getLayoutInflater().inflate(R.layout.layout_event_favorite_list_item, parent, false);

        ImageView view_image = view.findViewById(R.id.favoriteEventListItem_imageView);
        TextView view_text_name = view.findViewById(R.id.favoriteEventListItem_textView_name);
//        TextView view_text_description = view.findViewById(R.id.favoriteEventListItem_textView_description);
        TextView view_text_priceRange = view.findViewById(R.id.favoriteEventListItem_textView_priceRange);
        TextView view_text_date = view.findViewById(R.id.favoriteEventListItem_textView_date);
        TextView view_text_distance = view.findViewById(R.id.favoriteEventListItem_textView_distance);

        new DownloadImageTask(view_image).execute(event.getImage());
        view_text_name.setText(event.getName());
//        if (event.getDescription() != null) view_text_description.setText(event.getDescription());
        if (event.getPriceRange() != null) view_text_priceRange.setText(event.getPriceRange().toString());
        if (event.getStartDate() != null) view_text_date.setText(event.getStartDate().getLocalDateTimeFormatted());
        if (event.getDistance() != null) view_text_distance.setText(event.getDistance().toString());

        return view;
    }
}
