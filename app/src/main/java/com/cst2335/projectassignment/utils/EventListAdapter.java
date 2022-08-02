package com.cst2335.projectassignment.utils;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.cst2335.projectassignment.R;
import com.cst2335.projectassignment.activities.JActivity;
import com.cst2335.projectassignment.objects.Event;

import java.util.ArrayList;

// TODO: Add JavaDoc Comment
public class EventListAdapter extends BaseAdapter {

    private JActivity jActivity;
    private ArrayList<Event> list;

    // TODO: Add JavaDoc Comment
    public EventListAdapter(ArrayList<Event> list, JActivity jActivity) {
        super();
        this.jActivity = jActivity;
        this.list = list;
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
        View view = jActivity.getLayoutInflater().inflate(R.layout.layout_event_list_item, parent, false);

        ImageView view_image = view.findViewById(R.id.eventListItem_imageView);
        TextView view_text_name = view.findViewById(R.id.eventListItem_textView_name);
        TextView view_text_description = view.findViewById(R.id.eventListItem_textView_description);
        TextView view_text_priceRange = view.findViewById(R.id.eventListItem_textView_priceRange);

        new DownloadImageTask(view_image).execute(event.getImage());
        view_text_name.setText(event.getName());
        view_text_description.setText(event.getDescription());
        view_text_priceRange.setText(event.getPriceRange().toString());

        return view;
    }
}
