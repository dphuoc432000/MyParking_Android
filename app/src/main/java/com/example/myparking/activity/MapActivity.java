package com.example.myparking.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myparking.R;
import com.example.myparking.data.BaiXeSQLHandler;
import com.example.myparking.data.KhachHangSQLHandler;
import com.example.myparking.data.RoleSQLHandler;
import com.example.myparking.data.SQLHandler;
import com.example.myparking.data.TaiKhoanSQLHandler;
import com.example.myparking.data.model.BaiXe;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap map;
    SupportMapFragment supportMapFragment;
    FusedLocationProviderClient client;
    FloatingActionButton fab;
    SQLHandler db;
    BaiXeSQLHandler baiXeSQLHandler;
    TaiKhoanSQLHandler taiKhoanSQLHandler;
    KhachHangSQLHandler khachHangSQLHandler;
    RoleSQLHandler roleSQLHandler;
    LinearLayout layoutSheet;
    BottomSheetBehavior bottomSheetBehavior;
    TextView tenbaixe;
    TextView soluong;
    TextView diachi;
    Button btnChiDuong;
    Button btnChiTiet;
    ImageButton btn_profile;
    Bundle bundleAccount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Retrieve the content view that renders the map.
        setContentView(R.layout.activity_map);
        //set thanh ẩn thanh title
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        baiXeSQLHandler = new BaiXeSQLHandler();
        taiKhoanSQLHandler = new TaiKhoanSQLHandler();;
        khachHangSQLHandler = new KhachHangSQLHandler();
        roleSQLHandler = new RoleSQLHandler();
        layoutSheet = (LinearLayout)findViewById(R.id.bottom_sheet);
        bottomSheetBehavior = BottomSheetBehavior.from(layoutSheet);
        tenbaixe = findViewById(R.id.tv_tenBaiXe);
        soluong = findViewById(R.id.tv_soluong);
        diachi = findViewById(R.id.tv_diachi);
        btnChiDuong = findViewById(R.id.btn_chiduong);
        btnChiTiet = findViewById(R.id.btn_chitiet);
        btn_profile = findViewById(R.id.btn_profile);

        //DATABASE
        db = new SQLHandler(MapActivity.this,baiXeSQLHandler, taiKhoanSQLHandler, khachHangSQLHandler, roleSQLHandler);

        // Get the SupportMapFragment and request notification when the map is ready to be used.
        supportMapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.google_map);

        // Initialize fused location
        client = LocationServices.getFusedLocationProviderClient(this);

        //check permission
        if(ActivityCompat.checkSelfPermission(MapActivity.this,Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED)
        {
            // when permission grated
            // Call method
            getCurrentLoction();
        }
        else{
            //when permission denied
            //request permission
            ActivityCompat.requestPermissions(MapActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 44);
        }
        //btn-zoom
        fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getCurrentLoction();
            }
        });



        //xu ly btn_profile
        btn_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_profileClick();
            }
        });


        bundleAccount = this.getIntent().getExtras();


    }

    private void btn_profileClick(){
        PopupMenu popupMenu = new PopupMenu(this, this.btn_profile);
        popupMenu.inflate(R.menu.layout_popup_menu);
        Menu menu =popupMenu.getMenu();
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.menuItem_profile:
                        Toast.makeText(getApplicationContext(),"This Profile function is in the process of updating", Toast.LENGTH_LONG).show();
                        break;
                    case R.id.menuItem_account:
                        Intent intentAccount = new Intent(MapActivity.this, AccountActivity.class);
                        intentAccount.putExtras(bundleAccount);
                        startActivity(intentAccount);
                        break;
                    case R.id.menuItem_logout:
                        Intent intentLogout = new Intent(MapActivity.this, Login.class);
                        startActivity(intentLogout);
                        Toast.makeText(getApplicationContext(),"Logged out", Toast.LENGTH_LONG).show();
                        break;
                    default:
                        Toast.makeText(getApplicationContext(), item.getTitle(), Toast.LENGTH_SHORT).show();
                        break;
                }
                return true;
            }
        });
        popupMenu.show();
    }

    private void getCurrentLoction() {
        //Initialize task location
        Task<Location> task = client.getLastLocation();
        task.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                //when success
                if(location != null)
                    supportMapFragment.getMapAsync(new OnMapReadyCallback() {
                        @Override
                        public void onMapReady(GoogleMap googleMap) {
                            //Initialize lat lng
                            LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
                            //Create marker options
                            MarkerOptions options = new MarkerOptions().position(latLng).title("I am here");
                            //zoom map
                            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,15));
                            //add marker on map
                            googleMap.addMarker(options);
                            //list ra nhiều bãi đỗ xe
                            List<BaiXe> listBaiXe = db.getAllBaiXe();
                            List<LatLng> locations = new ArrayList<>();
                            for (BaiXe baixe: listBaiXe) {
                                System.out.println(baixe.getTenBx());
                                locations.add(new LatLng(baixe.getKinhdo(), baixe.getVido()));
                            }
                            for (int i = 0; i < locations.size(); i++) {
                                Marker marker = googleMap.addMarker(new MarkerOptions().position(locations.get(i)).title(listBaiXe.get(i).getTenBx()));
                                marker.setTag(listBaiXe.get(i));
                            }
                            googleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                                @Override
                                public boolean onMarkerClick(Marker marker) {
                                    try {
                                        BaiXe baiXe = (BaiXe) marker.getTag();
                                        tenbaixe.setText(baiXe.getTenBx());
                                        soluong.setText(baiXe.getSoluongtrong() + "/" + baiXe.getSoluonggiuxe());
                                        diachi.setText(baiXe.getDiachi());
                                        bottomSheetBehavior.setPeekHeight(190);
                                    }
                                    catch (Exception e){
                                        tenbaixe.setText("");
                                        soluong.setText("");
                                        diachi.setText("");
                                        bottomSheetBehavior.setPeekHeight(0);
                                    }
                                    //set nut chi duong
                                    btnChiDuong.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            // Get value sourceLatLng and destination
                                            String sSourceLatLng = options.getPosition().latitude + "," + options.getPosition().longitude;
                                            BaiXe baiXe = (BaiXe) marker.getTag();
                                            String sDestination = baiXe.getTenBx().trim() + "," + baiXe.getDiachi().trim();
                                            System.out.println(sSourceLatLng);
                                            System.out.println(sDestination);
                                            DisplayTrack(sSourceLatLng, sDestination);
                                        }
                                    });
                                    // set nut chi tiet
                                    btnChiTiet.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            Intent i = new Intent(MapActivity.this, BaiXeDetailActivity.class);
                                            BaiXe baiXe = (BaiXe)marker.getTag();
                                            Bundle bundle = new Bundle();
                                            bundle.putString("tenbaixe", baiXe.getTenBx());
                                            bundle.putString("diachi", baiXe.getDiachi());
                                            bundle.putString("startTime", baiXe.getStartTime());
                                            bundle.putString("finishTime", baiXe.getFinishTime());
                                            bundle.putDouble("price", baiXe.getPrice());
                                            bundle.putString("description", baiXe.getDescription());
                                            bundle.putInt("soluongtrong", baiXe.getSoluongtrong());
                                            bundle.putInt("soluonggiuxe", baiXe.getSoluonggiuxe());
                                            i.putExtras(bundle);
                                            startActivity(i);
                                        }
                                    });

                                    return false;

                                }
                            });
                        }
                    });
            }
        });

    }

    private void DisplayTrack(String sSourceLatLng, String sDestination) {
        try {
            Uri uri = Uri.parse("https://www.google.com/maps/dir/" + sSourceLatLng +"/" + sDestination);
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            intent.setPackage("com.google.android.apps.maps");
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
        catch (ActivityNotFoundException e){
            Uri uri = Uri.parse("https://play.google.com/store/apps/details?id=com.google.android.apps.maps");
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull @NotNull String[] permissions, @NonNull @NotNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == 44){
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                //When permission granted
                //Call method
                getCurrentLoction();
        }
    }

    @Override
    public void onMapReady(@NonNull @org.jetbrains.annotations.NotNull GoogleMap googleMap) {
//        this.map = googleMap;
//        LatLng localuser = new LatLng(15.926172698910968, 108.23066665866178);
//        googleMap.addMarker(new MarkerOptions().position(localuser));
//        LatLng localuser2 = new LatLng(15.926268185209006, 108.23094368196915);
//        googleMap.addMarker(new MarkerOptions().position(localuser2));
//        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(localuser,15));
//        this.map = googleMap;
//        map.setOnMapLoadedCallback(new GoogleMap.OnMapLoadedCallback() {
//            List<BaiXe> list = db.getAllBaiXe();
//            @Override
//            public void onMapLoaded() {
//                List<LatLng> locations = new ArrayList<>();
//                for (BaiXe baixe: list) {
//                    locations.add(new LatLng(baixe.getKinhdo(), baixe.getVido()));
//                }
//                for (LatLng latLng: locations){
//                    for (BaiXe baixe: list) {
//                        map.addMarker(new MarkerOptions().position(latLng).title(baixe.getTenBx()));
//                    }
//                }
//            }
//        });
    }



    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}