package com.example.weightmanager.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.weightmanager.R;

import java.util.List;

public class SearchAdapter extends BaseAdapter {

    private Context context;
    private List<FoodData> list;
    private LayoutInflater inflate;
    private ViewHolder viewHolder;

    public SearchAdapter(List<FoodData> list, Context context){
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
            convertView = inflate.inflate(R.layout.listview_searchfood,null);

            viewHolder = new ViewHolder();
            viewHolder.foodName = (TextView) convertView.findViewById(R.id.food_name);
            viewHolder.foodCarb = (TextView) convertView.findViewById(R.id.food_carb);
            viewHolder.foodKcal = (TextView) convertView.findViewById(R.id.food_kcal);
            viewHolder.foodFat = (TextView) convertView.findViewById(R.id.food_fat);
            viewHolder.foodProtein = (TextView) convertView.findViewById(R.id.food_protein);

            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder)convertView.getTag();
        }

        // 리스트에 있는 데이터를 리스트뷰 셀에 뿌림.
        viewHolder.foodName.setText(list.get(position).name);
        viewHolder.foodKcal.setText(Integer.toString((int) list.get(position).kcal)+"kcal");
        viewHolder.foodCarb.setText(Integer.toString((int) list.get(position).carb)+"g");
        viewHolder.foodFat.setText(Integer.toString((int) list.get(position).fat)+"g");
        viewHolder.foodProtein.setText(Integer.toString((int) list.get(position).protein)+"g");

   //     convertView.setOnClickListener(new View.OnClickListener() {
     //       @Override
      //      public void onClick(View v) {
        //        Toast.makeText(context, list.get(position).name, Toast.LENGTH_LONG).show();
          //  }
        //});

        return convertView;
    }
    class ViewHolder{
        public TextView foodName, foodKcal, foodCarb, foodProtein, foodFat;
    }
}
