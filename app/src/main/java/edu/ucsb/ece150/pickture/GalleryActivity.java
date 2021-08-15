package edu.ucsb.ece150.pickture;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

public class GalleryActivity extends AppCompatActivity {

    GridView gridview;
    int[] images_id = { R.drawable.i1, R.drawable.i2, R.drawable.i3, R.drawable.i4, R.drawable.i5, R.drawable.i6, R.drawable.i7,R.drawable.i8, R.drawable.i9};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);
        gridview = (GridView)findViewById(R.id.myGridView);

        ImageAdapter imageAdapter = new ImageAdapter(this);
        gridview.setAdapter(imageAdapter);

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(),images_id[position],Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getApplicationContext(),ProfileActivity.class);
                intent.putExtra("image",images_id[position]);
                startActivity(intent);
            }
        });
    }

    private class ImageAdapter extends BaseAdapter {
        Context ctx;


        ImageAdapter(Context ctx){

            this.ctx = ctx;
        }

        @Override
        public int getCount() {
            return images_id.length;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public Object getItem(int position) {
            return images_id[position];
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            View gridView = convertView;

            if(gridView==null){

                LayoutInflater inflater = (LayoutInflater)ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                assert inflater != null;
                gridView= inflater.inflate(R.layout.custom_image_layout,null);

            }

            ImageView i1 = (ImageView)gridView.findViewById(R.id.myImage);
            i1.setImageResource(images_id[position]);

            return gridView;
        }
    }


}

