package com.example.android.bluetoothchat;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class MyAdapter extends BaseAdapter
{
    Context context;
    ArrayList<Messages> messages;
    
    MyAdapter(final Context context, final ArrayList<Messages> messages) {
        super();
        this.context = context;
        this.messages = messages;
    }
    
    public int getCount() {
        return this.messages.size();
    }
    
    public Object getItem(final int n) {
        return this.messages.get(n);
    }
    
    public long getItemId(final int n) {
        return 0L;
    }


    public class ViewHolder
    {
        TextView t;
    }

    public View getView(final int n, final View view, final ViewGroup viewGroup) {
        View inflate = view;
        ViewHolder tag;
        final Messages message = (Messages)this.getItem(n);
        if (inflate == null) {
            final LayoutInflater layoutInflater = (LayoutInflater)this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            inflate = LayoutInflater.from(this.context).inflate(R.layout.message, viewGroup, false);
            tag = new ViewHolder();
            tag.t = (TextView)inflate.findViewById(R.id.text1);
            inflate.setTag((Object)tag);
        }
        else {
            tag = (ViewHolder)inflate.getTag();
        }
        final LinearLayout.LayoutParams linearLayout$LayoutParams = (LinearLayout.LayoutParams)tag.t.getLayoutParams();
        if (!message.isMine()) {

            linearLayout$LayoutParams.gravity = Gravity.LEFT;
            tag.t.setBackgroundResource(R.drawable.bubbleu);
            tag.t.setText(message.getMessage());

            if (message.isSticker) {
                tag.t.setBackgroundResource(0);
            }
            return inflate;
        }

        linearLayout$LayoutParams.gravity = Gravity.RIGHT;
        tag.t.setBackgroundResource(R.drawable.bubbleme);

        if (message.isSticker) {
            tag.t.setBackgroundResource(0);
        }

        tag.t.setText(message.getMessage());
        tag.t.setTextColor(Color.parseColor("#000000"));

        return inflate;
    }

    public void clear()
    {
        messages.clear();
        this.notifyDataSetChanged();
    }
}
