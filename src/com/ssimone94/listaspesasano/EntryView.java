package com.ssimone94.listaspesasano;

import android.content.Context;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class EntryView extends RelativeLayout {

	private TextView name;
	private ImageView sign;
	private ImageView remove;
	
	public EntryView(String name, Context context) {
		super(context);
	}
}
