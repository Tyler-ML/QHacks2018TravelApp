package com.example.james.travelhack;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class IteneraryListActivity extends AppCompatActivity {

    ListView mListView;

    int[] images = {
            //R.drawable.image1,
            //R.drawable.image2
    };
    String [] name = getIntent().getExtras().getStringArray("GeeGee");
    /*String[] names = {
            "CN Tower",
            "Ripley's Aquarium"

    };*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_itenerary_list);
        mListView = (ListView) findViewById(R.id.listView);

        CustomAdaptor customAdaptor = new CustomAdaptor();
        mListView.setAdapter(customAdaptor);
    }

    class CustomAdaptor extends BaseAdapter {

        @Override
        public int getCount() {
            return images.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }
        //test
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = getLayoutInflater().inflate(R.layout.itenerarylayout, parent, false);

            ImageView mImageView = (ImageView) view.findViewById(R.id.imageView);
            TextView mTextView = (TextView) view.findViewById(R.id.textView);

            mImageView.setImageResource(images[position]);
            mTextView.setText(name[position]);

            return view;
        }
    }
}
