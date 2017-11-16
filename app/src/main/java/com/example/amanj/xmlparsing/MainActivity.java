package com.example.amanj.xmlparsing;

import android.app.ListActivity;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends ListActivity {
    List<MyTask> tasks;
    List<Flower> flowerList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        flowerList=null;
        tasks = new ArrayList<>();

       requestData("http://services.hanselandpetal.com/feeds/flowers.xml");

    }
        private void requestData(String uri) {
            MyTask task=new MyTask();
            task.execute(uri);
        }

    private class MyTask extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {


            tasks.add(this);
        }

        @Override
        protected String doInBackground(String... params) {
            String content = ConnectionManager.getData(params[0]);
            return content;
        }

        @Override
        protected void onPostExecute(String result) {

            flowerList = FlowerXMLParser.parseFeed(result);

            FlowerAdaptor adaptor=new FlowerAdaptor(MainActivity.this,R.layout.one_row,flowerList);
            setListAdapter(adaptor);

            tasks.remove(this);


        }





    }

}
