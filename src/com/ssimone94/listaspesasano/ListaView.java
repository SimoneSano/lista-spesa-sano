package com.ssimone94.listaspesasano;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class ListaView extends TextView {
	
	private int id;
	
	public ListaView(int id, String name, Context context) {
		super(context);
		this.id = id;
		this.setText(name);
		this.setOnLongClickListener(new OnLongClickListener(){
			@Override
			public boolean onLongClick(View v) {
				
				return false;
			}
		});
	}
	
	public int getId(){
		return this.id;
	}
	
	public void setId(int id){
		this.id = id;
	}

}

/*************************
 *** LongClickListener ***
 *************************/
class MessageRoomViewLongClickListener implements OnLongClickListener {
	
	private AlertDialog.Builder choose_action;
	private ListaView listaView;
	private boolean contrassegnato = false;

	protected MessageRoomViewLongClickListener(ListaView listaView){
		this.listaView = listaView;
	}
	
	@Override
	public boolean onLongClick(View v) {
		this.choose_action = new AlertDialog.Builder(listaView
				.getContext());
		this.choose_action.setItems(R.array.lista,
				new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface d, int which) {
				switch (which) {
				case 0: // Contrassegna
					if(!contrassegnato)
						listaView.setCompoundDrawables( 
							    null,// left 
							    null,//top 
							    listaView.getContext().getResources().getDrawable(android.R.drawable.checkbox_on_background),
							    null);//bottom
					else
						listaView.setCompoundDrawables( 
							    null,// left 
							    null,//top 
							    null,
							    null);//bottom
					contrassegnato = !contrassegnato;
					break;
				case 1: // Rinomina
					Toast.makeText(listaView.getContext(), "Non adesso!", Toast.LENGTH_SHORT).show();
					break;
				case 2: // Elimina
					LinearLayout parent = (LinearLayout)listaView.getParent();
					parent.removeView(listaView);
					parent.invalidate();
					break;
				}
			}
		});
		this.choose_action.create().show();
		return true;
	}
}