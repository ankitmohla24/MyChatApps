package com.example.wifidirectchat.CustomAdapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.text.util.Linkify;
import android.util.Patterns;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.wifidirectchat.ChatActivity;
import com.example.wifidirectchat.Entities.Message;
import com.example.wifidirectchat.R;
import com.example.wifidirectchat.ViewImageActivity;

import java.util.HashMap;
import java.util.List;

public class ChatAdapter extends BaseAdapter {
	private List<Message> listMessage;
	private LayoutInflater inflater;
	public static Bitmap bitmap;
	private Context mContext;
	private HashMap<String,Bitmap> mapThumb;

	public ChatAdapter(Context context, List<Message> listMessage){
		this.listMessage = listMessage;
		inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		mContext = context;
		mapThumb = new HashMap<String, Bitmap>();
	}
	
	@Override
	public int getCount() {
		return listMessage.size();
	}

	@Override
	public Object getItem(int position) {
		return position;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}
	

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = convertView;
		
		Message mes = listMessage.get(position);
		int type = mes.getmType();
		
		if(view == null){
			CacheView cache = new CacheView();            
            
			view = inflater.inflate(R.layout.chat_row, null);
			cache.chatName = (TextView) view.findViewById(R.id.chatName);
            cache.text = (TextView) view.findViewById(R.id.text);
            cache.image = (ImageView) view.findViewById(R.id.image);
            cache.relativeLayout = (LinearLayout) view.findViewById(R.id.relativeLayout);
            cache.fileSaved = (TextView) view.findViewById(R.id.fileSaved);
            cache.fileSavedIcon = (ImageView) view.findViewById(R.id.file_attached_icon);
            	            
			view.setTag(cache);
		}


        //Retrieve the items from cache
        CacheView cache = (CacheView) view.getTag();
        cache.chatName.setText(listMessage.get(position).getChatName());
        cache.chatName.setTag(cache);
        cache.chatName.setOnLongClickListener(new OnLongClickListener() {
			
			@Override
			public boolean onLongClick(View v) {
				CacheView cache = (CacheView) v.getTag();
				((ChatActivity)mContext).talkTo((String) cache.chatName.getText());
				return true;
			}
		});
        final LinearLayout.LayoutParams linearLayout$LayoutParams = (LinearLayout.LayoutParams)cache.relativeLayout.getLayoutParams();

        //Drawing the differently own message
        if(listMessage.get(position).isMine()){
            linearLayout$LayoutParams.gravity = Gravity.RIGHT;
            cache.relativeLayout.setBackground(view.getResources().getDrawable(R.drawable.chat_bubble_mine));
        }
        else{
            linearLayout$LayoutParams.gravity = Gravity.LEFT;
            cache.relativeLayout.setBackground(view.getResources().getDrawable(R.drawable.chat_bubble));
        }
        
        //disable all the views and enable certain views depending on the message's type
        disableAllMediaViews(cache);
        
        /*Text Message*/
        if(type == Message.TEXT_MESSAGE){           
        	enableTextView(cache, mes.getmText());
			}
        /*Image Message*/
        else if(type == Message.IMAGE_MESSAGE){
        	enableTextView(cache, mes.getmText());
			cache.image.setVisibility(View.VISIBLE);
			
			if(!mapThumb.containsKey(mes.getFileName())){
				Bitmap thumb = mes.byteArrayToBitmap(mes.getByteArray());
				mapThumb.put(mes.getFileName(), thumb);				
			}
			cache.image.setImageBitmap(mapThumb.get(mes.getFileName()));
			cache.image.setTag(position);
			
			cache.image.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					Message mes = listMessage.get((Integer) v.getTag());
					bitmap = mes.byteArrayToBitmap(mes.getByteArray());
					
					Intent intent = new Intent(mContext, ViewImageActivity.class);
					String fileName = mes.getFileName();
					intent.putExtra("fileName", fileName);
					
					mContext.startActivity(intent);
				}
			});
        }
        /*File Message*/
        else if(type == Message.FILE_MESSAGE){
        	enableTextView(cache, mes.getmText());
        	cache.fileSavedIcon.setVisibility(View.VISIBLE);
        	cache.fileSaved.setVisibility(View.VISIBLE);
        	cache.fileSaved.setText(mes.getFileName());
        }
        	return view;
		}
	
		private void disableAllMediaViews(CacheView cache){
			cache.text.setVisibility(View.GONE);
			cache.image.setVisibility(View.GONE);
			cache.fileSaved.setVisibility(View.GONE);
			cache.fileSavedIcon.setVisibility(View.GONE);
		}
		private void enableTextView(CacheView cache, String text){
			if(!text.equals("")){
				cache.text.setVisibility(View.VISIBLE);
				cache.text.setText(text);
				Linkify.addLinks(cache.text, Linkify.PHONE_NUMBERS);
				Linkify.addLinks(cache.text, Patterns.WEB_URL, "myweburl:");
			}		
		}
		//Cache
		private static class CacheView{
			public TextView chatName;
			public TextView text;
			public ImageView image;
			public LinearLayout relativeLayout;
			public ImageView fileSavedIcon;
			public TextView fileSaved;
		}
	}
