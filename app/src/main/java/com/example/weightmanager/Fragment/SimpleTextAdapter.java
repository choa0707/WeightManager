package com.example.weightmanager.Fragment;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.weightmanager.R;

import java.util.ArrayList;

public class SimpleTextAdapter extends RecyclerView.Adapter<SimpleTextAdapter.ViewHolder> {
    private ItemClick itemClick;//데이터 리스트에 해당 데이터 클릭시 위치값 저장
    private ArrayList<String> mData = null ;//추가한 전체 아침(혹은 점심, 저녁, 간식)메뉴 리스트 저장
    String text;

    public interface ItemClick {
        public void onClick(View view,int position);
    }
    public void setItemClick(ItemClick itemClick) {
        this.itemClick = itemClick;
    }

    //아이템 뷰를 저장하는 뷰홀더 클래스
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView1 ;

        ViewHolder(View itemView) {
            super(itemView) ;

            //뷰 객체에 대한 참조
            textView1 = itemView.findViewById(R.id.text1) ;
        }
    }

    //생성자에서 데이터 리스트 객체를 전달받음
    SimpleTextAdapter(ArrayList<String> list) {
        mData = list ;
    }

    // onCreateViewHolder() - 아이템 뷰를 위한 뷰홀더 객체 생성하여 리턴.
    @Override
    public SimpleTextAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext() ;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) ;

       //리사이클러뷰 반복적으로 인플레이터
        View view = inflater.inflate(R.layout.recyclerview_item, parent, false) ;
        SimpleTextAdapter.ViewHolder vh = new SimpleTextAdapter.ViewHolder(view) ;

        return vh ;
    }

    // position에 해당하는 데이터를 뷰홀더의 아이템뷰에 표시(텍스트를 반복적으로 어댑터를 이용해 뷰홀더로 붙여줌)
    @Override
    public void onBindViewHolder(SimpleTextAdapter.ViewHolder holder, final int position) {
         text = mData.get(position) ;//text에 전체 데이터 리스트의 데이터 위치값을 저장
        holder.textView1.setText(text) ;
        holder.textView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(itemClick != null){//
                    itemClick.onClick(v, position);
                }
            }
        });
    }

    // getItemCount() - 전체 데이터 갯수 리턴.
    @Override
    public int getItemCount() {
        return mData.size() ;
    }

}