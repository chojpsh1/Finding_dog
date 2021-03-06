package com.example.minyoung.finding_dog;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ChatArrayAdapter extends ArrayAdapter {
    private TextView chatText;
    private ImageView chatImage;

    private List chatMessageList = new ArrayList();
    private LinearLayout singleMessageContainer;

    public void add(ChatMessage object) {
        chatMessageList.add(object);
        super.add(object);
    }

    public ChatArrayAdapter(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
    }


    public int getCount() {
        return this.chatMessageList.size();
    }

    public ChatMessage getItem(int index) {
        return (ChatMessage)this.chatMessageList.get(index);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        if (row == null) {
            LayoutInflater inflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.chatmessage, parent, false);
        }
        singleMessageContainer = row.findViewById(R.id.singleMessageContainer);

        ChatMessage chatMessageObj = getItem(position);

        chatText = (TextView) row.findViewById(R.id.chatmessage);
        chatImage = (ImageView) row.findViewById(R.id.chatimage);

        if(chatMessageObj.message != null) {
            chatText.setVisibility(View.VISIBLE);
            chatImage.setVisibility(View.GONE);
            chatText.setText(chatMessageObj.message);
            chatText.setBackgroundResource(chatMessageObj.left ? R.drawable.left_bubble9 : R.drawable.right_bubble_black9);
            chatText.setTextColor(chatMessageObj.left ? Color.rgb(0x00, 0x00, 0x00) : Color.rgb(0xFF,0xFF,0xFF));
        }
        else{
            chatText.setVisibility(View.GONE);
            chatImage.setVisibility(View.VISIBLE);
            chatImage.setImageBitmap(chatMessageObj.image);
            chatImage.setBackgroundResource(chatMessageObj.left ? R.drawable.left_bubble9 : R.drawable.right_bubble_black9);
        }

        singleMessageContainer.setGravity(chatMessageObj.left ? Gravity.LEFT : Gravity.RIGHT);

        return row;
    }

    public Bitmap decodeToBitmap(byte[] decodedByte) {
        return BitmapFactory.decodeByteArray(decodedByte, 0, decodedByte.length);
    }
}

