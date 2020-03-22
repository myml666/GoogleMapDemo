package com.itfitness.googlemapdemo;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * @ProjectName: GoogleMapDemo
 * @Package: com.itfitness.googlemapdemo
 * @ClassName: MapListActivity
 * @Description: java类作用描述 地图列表
 * @Author: 作者名 itfitness
 * @CreateDate: 2020/3/17 11:29
 * @UpdateUser: 更新者：
 * @UpdateDate: 2020/3/17 11:29
 * @UpdateRemark: 更新说明：
 * @Version: 1.0
 */
public class MapListActivity extends AppCompatActivity {
    private RecyclerView maplistRv;
    private Geocoder geocoder;
    private BaseQuickAdapter<String, BaseViewHolder> baseQuickAdapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maplist);
        maplistRv = (RecyclerView) findViewById(R.id.maplist_rv);
        maplistRv.setLayoutManager(new LinearLayoutManager(this));
        ArrayList<String> datas = new ArrayList<>();
        for(int i = 0;i<10;i++){
            datas.add(""+i);
        }
        baseQuickAdapter = new BaseQuickAdapter<String, BaseViewHolder>(R.layout.item_map,datas) {
            @Override
            protected void convert(BaseViewHolder helper, String item) {
                MapView itemMap = helper.getView(R.id.item_map);
                itemMap.onCreate(new Bundle());
                itemMap.onResume();
                try {
                    MapsInitializer.initialize(mContext);
                } catch (Exception e) {
                    Log.e("出错",e.getMessage());
                }
                int errorCode = GooglePlayServicesUtil.isGooglePlayServicesAvailable(mContext);
                if (ConnectionResult.SUCCESS == errorCode) {
                    itemMap.getMapAsync(new OnMapReadyCallback() {
                        @Override
                        public void onMapReady(GoogleMap googleMap) {
                            double lat = 36.69314;
                            double lng = 117.10170;
                            LatLng appointLoc = new LatLng(lat, lng);

                            // 移动地图到指定经度的位置
                            googleMap.moveCamera(CameraUpdateFactory.newLatLng(appointLoc));

                            //添加标记到指定经纬度
                            googleMap.addMarker(new MarkerOptions().position(new LatLng(lat, lng)).title("Marker")
                                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_dog)));
                        }
                    });
                }else {
                    Log.e("出差",errorCode+"");
                }
            }
        };
        maplistRv.setAdapter(baseQuickAdapter);
    }
    /**
     * Google地图根据经纬度获取地址
     */
    private void googleMapgetAddress(double shopLng, double shopLat, final TextView textView){
        if(geocoder == null){
            geocoder = new Geocoder(this, Locale.getDefault());
        }
        List<Address> addresses = null;
        try {
            addresses = geocoder.getFromLocation(shopLat, shopLng, 1);
            if(addresses!=null||addresses.size()>0) {
                String locality = addresses.get(0).getAddressLine(0);
                if(!TextUtils.isEmpty(locality)){
                    textView.setText(locality);
                }else {
                    textView.setText("");
                }
            }
        } catch (IOException e) {
            textView.setText("");
            e.printStackTrace();
        }
    }
}
