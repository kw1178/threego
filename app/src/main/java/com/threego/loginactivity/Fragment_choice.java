package com.threego.loginactivity;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class Fragment_choice extends Fragment {
    ArrayList<DeliveryVO> delivery = new ArrayList<>();
    ListView lv;
    RequestQueue requestQueue;
    StringRequest stringRequest;
    DeliveryAdapter adapter;
    TextView tv_choice;
    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_choice, container, false);

        tv_choice = view.findViewById(R.id.tv_choice2);
        lv = view.findViewById(R.id.list_choice);

        delivery.clear();
        requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
        jsonParse();

        adapter = new DeliveryAdapter(view.getContext(),R.layout.delivery_list,delivery);
        lv.setAdapter(adapter);
        return view;
    }

    private void jsonParse() {

        String url = "http://222.102.104.230:8081/threego/deliveryAll.do";
        stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jarr = new JSONArray(response);
                    for (int i = 0, j = jarr.length(); i<j; i++){
                        JSONObject jobj = jarr.getJSONObject(i);

                        DeliveryVO deliveryVO = new DeliveryVO();

                        deliveryVO.setDl_shop(jobj.getString("dl_shop"));
                        Log.v("cha", deliveryVO.getDl_shop() +"");
                        deliveryVO.setDl_call(jobj.getInt("dl_call"));
                        deliveryVO.setDl_food(jobj.getString("dl_food"));
                        deliveryVO.setDl_price(jobj.getInt("dl_price"));
                        deliveryVO.setDl_distoadd(jobj.getString("dl_distoadd"));
                        deliveryVO.setDl_dltime(jobj.getString("dl_dltime"));
                        deliveryVO.setDl_cooktime(jobj.getString("dl_cooktime"));
                        deliveryVO.setDl_address(jobj.getString("dl_address"));
                        deliveryVO.setDl_number(jobj.getInt("dl_number"));
                        deliveryVO.setDl_s_lati(jobj.getString("dl_s_lati"));
                        deliveryVO.setDl_s_longi(jobj.getString("dl_s_longi"));

                        delivery.add(deliveryVO);

                    }
                    Log.v("cha", response +"");
                }catch (Exception e){
                    e.printStackTrace();
                }
                adapter = new DeliveryAdapter(view.getContext(),R.layout.delivery_list,delivery);
                lv.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> temp = new HashMap<>();
                temp.put("dl_status",tv_choice.getText().toString());

                return temp;
            }
        };

        requestQueue.add(stringRequest);
    }
}