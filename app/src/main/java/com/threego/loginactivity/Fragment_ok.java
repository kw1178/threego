package com.threego.loginactivity;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

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


public class Fragment_ok extends Fragment {
    ArrayList<DeliveryVO> delivery = new ArrayList<>();
    ListView lv;
    RequestQueue requestQueue;
    StringRequest stringRequest;
    DeliveryAdapter adapter;
    TextView tv_ok;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ok, container, false);

        tv_ok = view.findViewById(R.id.tv_ok2);
        lv = view.findViewById(R.id.list_ok);

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

                        delivery.add(deliveryVO);
                        adapter.notifyDataSetChanged();

                    }
                    Log.v("cha", response +"");
                }catch (Exception e){
                    e.printStackTrace();
                }
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
                temp.put("dl_status",tv_ok.getText().toString());

                return temp;
            }
        };

        requestQueue.add(stringRequest);
    }
}