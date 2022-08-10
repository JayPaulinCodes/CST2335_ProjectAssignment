package com.cst2335.projectassignment.utils;

import android.annotation.SuppressLint;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.cst2335.projectassignment.R;
import com.cst2335.projectassignment.activities.JActivity;
import com.cst2335.projectassignment.objects.Event;

import java.util.ArrayList;

/**
 * List Adapter class for the list of events on the favorites page.
 *
 * @author Jacob Paulin
 */
public class FavoriteEventListAdapter extends BaseAdapter {

    private final JActivity jActivity;
    private ArrayList<Event> list;

    /**
     * Constructor for the FavoriteEventListAdapter class
     * @param list ArrayList of Events
     * @param jActivity Context
     */
    public FavoriteEventListAdapter(ArrayList<Event> list, JActivity jActivity) {
        super();
        this.jActivity = jActivity;
        this.list = list;
    }

    /**
     * Sets the event variable
     * @param events ArrayList of Events
     */
    public void setList(ArrayList<Event> events) {
        this.list = events;
    }

    /**
     * Returns the amount of events in the list
     * @return Integer of list size
     */
    @Override
    public int getCount() {
        return list.size();
    }

    /**
     * Retrieves an item at a given position
     * @param position the position of the item to retrieve
     * @return the item at the given position
     */
    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    /**
     * Unused method for item ID
     * @param position
     * @return
     * @deprecated
     */
    @Override
    public long getItemId(int position) {
        return 0;
    }

    /**
     * Preparing the event list items with their respective data
     * @param position the position of the event in the events list
     * @param convertView
     * @param parent
     * @return the prepared view
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Event event = list.get(position);
        @SuppressLint("ViewHolder") View view = jActivity.getLayoutInflater().inflate(R.layout.layout_event_favorite_list_item, parent, false);

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
