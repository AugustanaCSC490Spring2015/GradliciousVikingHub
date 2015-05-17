package edu.augustana.csc490.vikinghub;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Ethan on 5/4/2015.
 */
public class HelpListAdapter extends BaseAdapter {

    private ArrayList<String> arrayList;
    private LayoutInflater layoutInflater;
    private Context context;
    public HelpListAdapter(Context aContext, ArrayList<String> arrayList) {
        this.arrayList = arrayList;
        layoutInflater = LayoutInflater.from(aContext);
        context = aContext;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        final Typeface font = Typeface.createFromAsset(context.getAssets(), "fonts/moon_light.otf");
        ViewHolder holder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.help_row, null);
            holder = new ViewHolder();
            holder.helpItem = (TextView) convertView.findViewById(R.id.helpItem);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.helpItem.setText(arrayList.get(position));
        holder.helpItem.setTypeface(font);
        holder.helpItem.setTextSize(18);
        holder.helpItem.setTextColor(Color.parseColor("#151515"));
        return convertView;
    }

    static class ViewHolder {
        TextView helpItem;
    }

}
