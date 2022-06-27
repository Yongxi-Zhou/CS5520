package edu.neu.numad22sp_yongxi;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;


import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;


public class WebService extends AppCompatActivity {
    //Creating the essential parts needed for a Recycler view to work: RecyclerView, Adapter, LayoutManager
    private ArrayList<ServiceCard> serviceList = new ArrayList<>();
    ;
    private static final String TAG = "WebServiceActivity";
    private RecyclerView recyclerView;
    private SviewAdapter sviewAdapter;
    private RecyclerView.LayoutManager rLayoutManger;
    private static final String KEY_OF_INSTANCE = "KEY_OF_INSTANCE";
    private static final String NUMBER_OF_ITEMS = "NUMBER_OF_ITEMS";

    private Handler handler=new Handler();
    private int mProgress;
    private ProgressBar progressBar;
    private String input=null;

    private class MultiThread extends Thread{

        @Override
        public void run(){
            mProgress=0;
            while(mProgress<90){
                mProgress++;
                android.os.SystemClock.sleep(10);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        progressBar.setProgress(mProgress);//refresh UI
                    }
                });
            }
            try {
                serviceList = new ArrayList<>();
                String mealDB = "https://www.themealdb.com/api/json/v1/1/search.php?s=" + input;
                URL url = new URL(mealDB);
                // Get String response from the url address
                String resp = NetworkUtil.httpResponse(url);
                Log.i("resp",resp);
                // Transform String into JSONObject
                JSONObject jObject = new JSONObject(resp);
                JSONArray jsonArray = jObject.getJSONArray("meals");
                //Log.i("resp",jsonArray.getString(0));
                System.out.println(jsonArray);
                for(int i=0;i<jsonArray.length();i++){
                    JSONObject body = jsonArray.getJSONObject(i);
                    String serviceName=body.getString("strMeal");
                    String serviceImg = body.getString("strMealThumb");
                    String serviceID = body.getString("idMeal");
                    Bitmap bm = getInternetPicture(serviceImg);
                    ServiceCard serviceCard = new ServiceCard(bm,serviceID,serviceName);
                    serviceList.add(serviceCard);
                }
                progressBar.setProgress(100);
                //Log.i("jTitle",jObject.getString("title"));
                //Log.i("jBody",jObject.getString("body"));
            } catch (MalformedURLException e) {
                Log.e(TAG,"MalformedURLException");
                e.printStackTrace();
            } catch (ProtocolException e) {
                Log.e(TAG,"ProtocolException");
                e.printStackTrace();
            } catch (IOException e) {
                Log.e(TAG,"IOException");
                e.printStackTrace();
            } catch (JSONException e) {
                Log.e(TAG,"JSONException");
                e.printStackTrace();
                progressBar.setVisibility(View.INVISIBLE);
                Looper.prepare();
                Toast toast= Toast.makeText(getApplicationContext(), "No recipe in this area", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER,0,0);
                toast.show();
                Looper.loop();
            }
            handler.post(new Runnable() {
                @Override
                public void run() {
                    progressBar.setVisibility(View.INVISIBLE);
                    recyclerView.setBackgroundColor(Color.DKGRAY);
                    createRecyclerView();
                    input=null;
                }
            });
        }
    }
    public static boolean hasPermissions(Context context, String... permissions)
    {
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && context != null && permissions != null)
        {
            for (String permission : permissions)
            {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED)
                {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webservice);
        init(savedInstanceState);
        int PERMISSION_ALL = 1;
        String[] PERMISSIONS = {Manifest.permission.READ_CONTACTS, Manifest.permission.WRITE_CONTACTS, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_SMS, Manifest.permission.CAMERA};

        if (!hasPermissions(this, PERMISSIONS)) {
            ActivityCompat.requestPermissions(this, PERMISSIONS, PERMISSION_ALL);
        }


        Button webservice = (Button) findViewById(R.id.ping);
        webservice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText et = (EditText) findViewById(R.id.input);
                input = et.getText().toString();
                if(input.length()==0){
                    Toast toast= Toast.makeText(getApplicationContext(), "Please input an area", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER,0,0);
                    toast.show();
                }
                else{
                    progressBar=(ProgressBar) findViewById(R.id.progress);
                    progressBar.setVisibility(View.VISIBLE);
                    MultiThread mt = new MultiThread();
                    Thread mt1 = new Thread(mt, "Service");
                    mt.start();
                    createRecyclerView();

                }
            }
        });

    }


    private void init(Bundle savedInstanceState) {
        initialItemData(savedInstanceState);
        createRecyclerView();
    }


    private void initialItemData(Bundle savedInstanceState) {


        if (savedInstanceState != null && savedInstanceState.containsKey(NUMBER_OF_ITEMS)) {
            if (serviceList == null || serviceList.size() == 0) {

                int size = savedInstanceState.getInt(NUMBER_OF_ITEMS);


                for (int i = 0; i < size; i++) {
                    String serviceImg = savedInstanceState.getString(KEY_OF_INSTANCE + i + "0");
                    String serviceID = savedInstanceState.getString(KEY_OF_INSTANCE + i + "1");
                    String serviceName = savedInstanceState.getString(KEY_OF_INSTANCE + i + "2");
                    Bitmap bm = getInternetPicture(serviceImg);


                    ServiceCard serviceCard = new ServiceCard(bm,serviceID,serviceName);

                    serviceList.add(serviceCard);
                }
            }
        }


    }


    private void createRecyclerView() {
        rLayoutManger = new LinearLayoutManager(this);
        recyclerView = findViewById(R.id.service_view);
        recyclerView.setHasFixedSize(true);

        sviewAdapter = new SviewAdapter(serviceList);
        recyclerView.setAdapter(sviewAdapter);
        recyclerView.setLayoutManager(rLayoutManger);
    }


    static class SviewAdapter extends RecyclerView.Adapter<SviewHolder> {

        private final ArrayList<ServiceCard> itemList;
        private ItemClickListener listener;

        //Constructor
        public SviewAdapter(ArrayList<ServiceCard> itemList) {
            this.itemList = itemList;
        }

        @Override
        public SviewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_service_card, parent, false);
            return new SviewHolder(view, listener);
        }

        @Override
        public void onBindViewHolder(SviewHolder holder, int position) {
            ServiceCard currentItem = itemList.get(position);

            holder.itemImg.setImageBitmap(currentItem.getServiceImg());
            holder.itemID.setText(currentItem.getServiceID());
            holder.itemName.setText(currentItem.getServiceName());

        }

        @Override
        public int getItemCount() {
            return itemList.size();
        }
    }


    static class SviewHolder extends RecyclerView.ViewHolder {
        public ImageView itemImg;
        public TextView itemID;
        public TextView itemName;

        public SviewHolder(View itemView, final ItemClickListener listener) {
            super(itemView);
            itemImg = itemView.findViewById(R.id.item_icon);
            itemID = itemView.findViewById(R.id.item_id);
            itemName = itemView.findViewById(R.id.item_name);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getLayoutPosition();
                        if (position != RecyclerView.NO_POSITION) {

                            listener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }


    public Bitmap getInternetPicture(String UrlPath) {
        Bitmap bm = null;
        String urlpath = UrlPath;

        try {
            URL uri = new URL(urlpath);

            HttpURLConnection connection = (HttpURLConnection) uri.openConnection();
            connection.setRequestMethod("GET");
            connection.setReadTimeout(5000);
            connection.setConnectTimeout(5000);
            connection.connect();

            if (connection.getResponseCode() == 200) {
                InputStream is = connection.getInputStream();
                bm = BitmapFactory.decodeStream(is);
            } else {
                bm = null;
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bm;
    }


}