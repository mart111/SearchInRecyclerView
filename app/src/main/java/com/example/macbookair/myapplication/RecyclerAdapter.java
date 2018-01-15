package com.example.macbookair.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by macbookair on 1/15/18.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    private Context mContext;
    private List<Contact> contactList;

    public RecyclerAdapter(Context mContext, List<Contact> contactList) {
        this.mContext = mContext;
        this.contactList = contactList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(mContext).inflate(R.layout.recycler_view, null);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Contact contact = contactList.get(position);
        holder.contactName.setText(contact.getName());
        holder.setContactImage(BitmapFactory.decodeResource(mContext.getResources(), R.mipmap.ic_launcher_round));
        if (contact.getSocialNetwork().equals(SocialNetworks.FACEBOOK))
            holder.contactImageSocial.setImageBitmap(BitmapFactory.decodeResource(mContext.getResources(), R.drawable.facebook));
        else
            holder.contactImageSocial.setImageBitmap(BitmapFactory.decodeResource(mContext.getResources(), R.drawable.twitter));
    }

    @Override
    public int getItemCount() {
        return contactList.size() > 0 ? contactList.size() : 0;
    }


    class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView contactImage;
        private ImageView contactImageSocial;
        private TextView contactName;

        public ViewHolder(View itemView) {
            super(itemView);
            contactImage = itemView.findViewById(R.id.contactImage);
            contactName = itemView.findViewById(R.id.contactName);
            contactImageSocial = itemView.findViewById(R.id.socialImage);
        }

        public void setContactImage(Bitmap bm) {
            contactImage.setImageBitmap(bm);
        }


    }
}
