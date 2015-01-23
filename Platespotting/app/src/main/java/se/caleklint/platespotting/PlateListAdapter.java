package se.caleklint.platespotting;

import android.content.Context;
import android.database.Cursor;
import android.database.DataSetObserver;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.TextView;

import se.caleklint.platespotting.database.DatabaseHandler;
import se.caleklint.platespotting.database.Plate;

public class PlateListAdapter implements ListAdapter {

    Cursor plates;

    public PlateListAdapter(Context context) {
        DatabaseHandler handler = new DatabaseHandler(context);
        plates = handler.getPlates();
        plates.moveToFirst();
    }
    @Override
    public boolean areAllItemsEnabled() {
        return true;
    }

    @Override
    public boolean isEnabled(int position) {
        return true;
    }

    @Override
    public void registerDataSetObserver(DataSetObserver observer) {
        plates.registerDataSetObserver(observer);
    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver observer) {
        plates.unregisterDataSetObserver(observer);
    }

    @Override
    public int getCount() {
        return plates.getCount();
    }

    @Override
    public Plate getItem(int position) {
        plates.moveToPosition(position);
        return new Plate(plates);
    }

    @Override
    public long getItemId(int position) {
        return getItem(position).getPlateNumber();
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = View.inflate(parent.getContext(), R.layout.plate_list_item, null);
        }
        Plate plate = getItem(position);
        TextView numberView = (TextView) listItemView.findViewById(R.id.number);
        TextView headerView = (TextView) listItemView.findViewById(R.id.head);
        TextView moreView = (TextView) listItemView.findViewById(R.id.more);
        numberView.setText(plate.getNumberAsString());
        headerView.setText(plate.getPlate());
        moreView.setText(plate.getMoreString(parent.getContext()));
        return listItemView;
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    @Override
    public int getViewTypeCount() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }
}
