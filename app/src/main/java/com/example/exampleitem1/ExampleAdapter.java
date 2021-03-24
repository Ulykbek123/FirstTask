package com.example.exampleitem1;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;


public class ExampleAdapter extends RecyclerView.Adapter<ExampleAdapter.ExampleViewHolder> {
    private RecyclerView.Adapter mAdapter;
    private ArrayList<ExampleItem> mExampleList;
    private OnItemClickListener mListener;
    private boolean isChekBoxVisible = false;



    public void insert(ExampleItem note) {
        mExampleList.add(note);
        notifyDataSetChanged();
    }

    public void update(int index,ExampleItem note) {
        mExampleList.set(index,note);
        notifyItemChanged(index);
    }
    public void setChekBoxVisible(boolean CheckBoxVisible){
        isChekBoxVisible = CheckBoxVisible;
        notifyDataSetChanged();
    }
    public void deleteAll(){
        List<ExampleItem> indexList = new ArrayList();
        for (int i = 0;i<mExampleList.size();i++){
            if (mExampleList.get(i).getIsChecked()){
                indexList.add(mExampleList.get(i));
            }
        }
        mExampleList.removeAll(indexList);
        notifyDataSetChanged();
    }




    public interface OnItemClickListener {
        void onItemClick(int id,ExampleItem note);
        void onDeleteClick(int position);



    }



    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;

    }

    public static class ExampleViewHolder extends RecyclerView.ViewHolder {
        public ImageView mImageView;
        public TextView mTextView1;
        public TextView mTextView2;
        public ImageView mDeleteImage;
        public CheckBox mCheckBox;
        

        public ExampleViewHolder(View itemView, final OnItemClickListener listener) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.imageView);
            mTextView1 = itemView.findViewById(R.id.textView);
            mTextView2 = itemView.findViewById(R.id.textView2);
            mDeleteImage = itemView.findViewById(R.id.image_delete);
            mCheckBox = itemView.findViewById(R.id.checkbox);


        }
    }

    public ExampleAdapter(ArrayList<ExampleItem> exampleList) {

        mExampleList = exampleList;
    }

    @Override
    public ExampleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.example_item, parent, false);
        ExampleViewHolder evh = new ExampleViewHolder(v, mListener);
        return evh;
    }

    @Override
    public void onBindViewHolder(ExampleViewHolder holder, int position) {
        ExampleItem note = mExampleList.get(position);

        holder.mImageView.setImageResource(note.getImageResource());
        holder.mTextView1.setText(note.getText1());
        holder.mTextView2.setText(note.getText2());
        if(isChekBoxVisible) {
            holder.mCheckBox.setVisibility(View.VISIBLE);
            holder.mCheckBox.setChecked(false);
        }
        else {
            holder.mCheckBox.setVisibility(View.GONE);
        }
        holder.mCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mExampleList.get(position).setIsChecked(isChecked);
            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {




            @Override
            public void onClick(View v) {

                if (mListener != null) {
                    int position = holder.getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        mListener.onItemClick(position,mExampleList.get(position));

                    }
                }

            }
        });
        holder.mDeleteImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    int position = holder.getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        mListener.onDeleteClick(position);
                    }
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mExampleList.size();
    }
}
