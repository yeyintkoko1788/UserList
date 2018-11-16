package com.example.davidnaing.userlist.activities;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.davidnaing.userlist.R;
import com.example.davidnaing.userlist.data.models.UserModel;
import com.example.davidnaing.userlist.data.vo.AddressVO;
import com.example.davidnaing.userlist.data.vo.CompanyVO;
import com.example.davidnaing.userlist.data.vo.LatlngVO;
import com.example.davidnaing.userlist.data.vo.UserVO;
import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.MapView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.mikhaellopez.circularimageview.CircularImageView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UserDetailActivity extends AppCompatActivity implements OnMapReadyCallback {
    @BindView(R.id.tv_id2)
    TextView tvId;
    @BindView(R.id.tv_name2)
    TextView tvName;
    @BindView(R.id.tv_username2)
    TextView tvUsername;
    @BindView(R.id.tv_email2)
    TextView tvEmail;
    @BindView(R.id.tv_phone2)
    TextView tvPhone;
    @BindView(R.id.tv_website2)
    TextView tvWebsite;
    @BindView(R.id.tv_address2)
    TextView tvAddress;
    @BindView(R.id.tv_zipcode2)
    TextView tvZipcode;
    @BindView(R.id.mapView2)
    MapView map;
    @BindView(R.id.tv_company_name2)
    TextView tvCompanyName;
    @BindView(R.id.tv_catch_phrase2)
    TextView tvCatchPhrase;
    @BindView(R.id.tv_bs2)
    TextView tvBs;
    @BindView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbarLayout;
    @BindView(R.id.toolbar)
    Toolbar toolBar;
    @BindView(R.id.app_bar_layout)
    AppBarLayout appBarLayout;
    @BindView(R.id.btm_next)
    ImageButton btm_next;
    @BindView(R.id.btm_previous)
    ImageButton btm_brevious;

    private LatlngVO latlngVO;
    private GoogleMap mMap;
    private Context context = this;
    private String id;

    public static Intent getNewIntent(Context context, String id) {
        Intent intent = new Intent(context, UserDetailActivity.class);
        intent.putExtra("user_id", id);
        return intent;
    }

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ButterKnife.bind(this);

        String id = getIntent().getStringExtra("user_id");
        final UserVO userVo = UserModel.getInstance().getUserById(id);
        bindData(userVo);
        id = userVo.getId();

        setSupportActionBar(toolBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(0,R.anim.zoom_out);
            }
        });
        collapsingToolbarLayout.setContentScrimColor(ContextCompat.getColor(context,R.color.colorPrimary));
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.BaseOnOffsetChangedListener() {
            boolean isShow = false;
            int scrollRange = -1;
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    collapsingToolbarLayout.setTitle(userVo.getName());
                    isShow = true;
                } else if (isShow) {
                    collapsingToolbarLayout.setTitle("");
                    isShow = false;
                }
            }
        });

        AddressVO addressVO = userVo.getAddress();
        latlngVO = addressVO.getGeo();
        map.onCreate(savedInstanceState);
        map.getMapAsync(this);
        tvWebsite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = WebViewActivity.getNewIntent(UserDetailActivity.this, userVo.getWebsite());
                startActivity(intent);
            }
        });

        final String finalId = id;
        btm_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goNext(finalId);
            }
        });

        btm_brevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goPrevious(finalId);
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        map.onPause();
    }

    @Override
    protected void onStart() {
        super.onStart();
        map.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        map.onResume();
    }

    @Override
    protected void onStop() {
        super.onStop();
        map.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        map.onDestroy();
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        map.onSaveInstanceState(outState);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(0,R.anim.zoom_out);
    }

    private void bindData(UserVO user){
        AddressVO addressVO = user.getAddress();
        CompanyVO companyVO = user.getCompany();
        tvName.setText(user.getName());
        tvId.setText(user.getId());
        tvUsername.setText(user.getUserName());
        tvEmail.setText(user.getEmail());
        tvPhone.setText(user.getPhone());
        tvWebsite.setText(user.getWebsite());
        String add = addressVO.getSuite()+" , "+addressVO.getStreet() +" St, \n"+addressVO.getCity()+".";
        tvAddress.setText(add);
        tvZipcode.setText(addressVO.getZipcode());
        tvCompanyName.setText(companyVO.getName());
        tvCatchPhrase.setText(companyVO.getCatchPhrase());
        tvBs.setText(companyVO.getBs());
        tvWebsite.setPaintFlags(tvEmail.getPaintFlags() |   Paint.UNDERLINE_TEXT_FLAG);
        tvWebsite.setClickable(true);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        float lat = Float.parseFloat(latlngVO.getLat());
        float lng = Float.parseFloat(latlngVO.getLng());
        LatLng location = new LatLng(lat,lng);
        mMap.addMarker(new MarkerOptions().position(location));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(location));
        GoogleMapOptions googleMapOptions = new GoogleMapOptions()
                .mapToolbarEnabled(true)
                .maxZoomPreference(5);
    }

    private void goNext(String userId){
        String key = UserModel.getInstance().getNextUser(userId);
        //userId = String.valueOf(Integer.parseInt(userId) + 1);
        if(key != null){
            finish();
            Intent intent = getNewIntent(this, key);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
        }else{
            Toast.makeText(this,"Error No More User!",Toast.LENGTH_LONG).show();
        }
    }

    private void goPrevious(String userId){
        String key = UserModel.getInstance().getPreviousUser(userId);
        //userId = String.valueOf(Integer.parseInt(userId) - 1);
        if(key != null){
            finish();
            Intent intent = getNewIntent(this, key);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
        }else{
            Toast.makeText(this,"Error No More User!",Toast.LENGTH_LONG).show();
        }
    }
}
