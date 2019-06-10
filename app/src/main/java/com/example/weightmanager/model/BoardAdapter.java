package com.example.weightmanager.model;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.weightmanager.R;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

public class BoardAdapter extends BaseAdapter {

    private Context context;
    private List<BoardData> list;
    private LayoutInflater inflate;
    private ViewHolder viewHolder;

    public BoardAdapter(List<BoardData> list, Context context){
        this.list = list;
        this.context = context;
        this.inflate = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup viewGroup) {
        if(convertView == null){
            convertView = inflate.inflate(R.layout.listview_board,null);

            viewHolder = new ViewHolder();
            viewHolder.boardTitle = (TextView) convertView.findViewById(R.id.board_title);
            viewHolder.boardText = (TextView) convertView.findViewById(R.id.board_text);
            viewHolder.imageUri = (ImageView) convertView.findViewById(R.id.board_image);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder)convertView.getTag();
        }

        // 리스트에 있는 데이터를 리스트뷰 셀에 뿌림.
        viewHolder.boardTitle.setText(list.get(position).title);
        viewHolder.boardText.setText(list.get(position).text);
        if (Integer.parseInt(list.get(position).imageUrl) == 1)
        {
            viewHolder.imageUri.setImageResource(R.drawable.infoimage_2);
        } else if (Integer.parseInt(list.get(position).imageUrl) == 0)
        {
            viewHolder.imageUri.setImageResource(R.drawable.infoimage_1);
        }else if (Integer.parseInt(list.get(position).imageUrl) == -1)
        {
            viewHolder.imageUri.setImageResource(R.drawable.infoimage_0);
        }

        String path = list.get(position).imageUrl;

        //"/sdcard/Images/test_image.jpg"
       /* try{
            //File imgFile = new  File("/sdcard/image1.jpg");
            String test= Environment.getExternalStorageDirectory().getAbsolutePath()+"/";
            Bitmap bitmap = BitmapFactory.decodeFile(test + path);
            Log.d("imagetest", test+path);
            viewHolder.imageUri.setImageBitmap(bitmap);

        }catch (NullPointerException e)
        {
            Log.d("image", "이미지 실패");
            Toast.makeText(convertView.getContext(), "이미지가 없습니다.", Toast.LENGTH_SHORT).show();
        }*/

        return convertView;
    }
    class ViewHolder{
        public ImageView imageUri;
        public TextView boardTitle, boardText;
    }
}
// INSERT INTO Board ('title', 'text', 'image') VALUES ('ImageTest', 'ImageTesttext', '/sdcard/image1.jpg');