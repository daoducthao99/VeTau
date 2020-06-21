package com.example.tau;

import android.content.DialogInterface;
import android.content.Intent;
import androidx.annotation.Nullable;
import android.os.Bundle;

import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.PopupMenu;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tau.R;
import com.example.tau.Sua;
import com.example.tau.VeTau;
import com.example.tau.VeTauAdapter;
import com.example.tau.VeTauDB;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class MainActivity extends AppCompatActivity implements VeTauAdapter.FaceItemListener, PopupMenu.OnMenuItemClickListener {

    public static final int Key =5 ;
    public static final int Key1 =6 ;
    private ArrayList<VeTau> data;
    private VeTauAdapter adapter;
    private RecyclerView lvRes;
    private EditText txtSearch;
    private VeTauDB sql;
    private FloatingActionButton btn_Them;
    int PK=-1;
    private int pos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        getData();

        final double[] tong = new double[1];
        final double[] tong1 = new double[1];

        Collections.sort(data, new Comparator<VeTau>() {

            @Override
            public int compare(VeTau o1, VeTau o2) {
                if(o1.isKhuhoi()==true){
                    tong[0] =o1.getDonGia()*2*0.95;
                }
                if(o1.isKhuhoi()==false){
                    tong[0] =o1.getDonGia();
                }
                if(o2.isKhuhoi()==true){
                    tong1[0] =o1.getDonGia()*2*0.95;
                }
                if(o2.isKhuhoi()==false){
                    tong1[0] =o1.getDonGia();
                }
                if(tong[0] > tong1[0]){
                    return -1;
                }else {
                    if(tong[0] == tong1[0]){
                        return 0;
                    }else {
                        return 1;
                    }
                }
            }
        });
        adapter.notifyDataSetChanged();
    }

    private void getData() {
        sql = new VeTauDB(this,
                "sql", null, 1);
        sql.addVeTau(new VeTau(1,"Hà Nội","Thái Bình",400.000,true));
        sql.addVeTau(new VeTau(2,"Hà Nội","Hải Dương",300.000,true));
        sql.addVeTau(new VeTau(3,"Hà Nội","Sơn La",200.000,false));
        sql.addVeTau(new VeTau(4,"Hà Nội","Bắc Giang",170.000,false));
        sql.addVeTau(new VeTau(5,"Lạng Sơn","Thái Bình",150.000,false));
        sql.addVeTau(new VeTau(6,"Hà Nội","Thái Bình",100.000,false));

        data = sql.getAllVeTau();
        adapter.setData(data);

    }

    private void initViews() {
        lvRes = findViewById(R.id.lv_Bh);
        adapter = new VeTauAdapter(this);
        lvRes.setAdapter(adapter);
        adapter.setListener(this);
    }

    @Override
    public void onClick(int position) {

    }

    @Override
    public void onLongClick(int position, View v) {
        PopupMenu popupMenu = new PopupMenu(this,v);
        popupMenu.setOnMenuItemClickListener(this);
        popupMenu.inflate(R.menu.menuitem);
        popupMenu.show();
        pos = position;
    }

    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.mnuXoa:
                break;
            case R.id.mnuSua:
                int id=data.get(pos).getMa();
                String id2=String.valueOf(id);
                String gaDi = data.get(pos).getGaDi();
                String gaDen = data.get(pos).getGaDen();
                double DonGia =0;

                if(data.get(pos).isKhuhoi()==true){
                    DonGia=data.get(pos).getDonGia()*2*0.95;
                }
                if(data.get(pos).isKhuhoi()==false){
                    DonGia=data.get(pos).getDonGia();
                }
                String diem2=String.valueOf(DonGia);

                Boolean khuhoi=data.get(pos).isKhuhoi();
                String kh=String.valueOf(khuhoi);

                Intent login = new Intent(this, Sua.class);
                login.putExtra("id", id2);
                login.putExtra("GaDi", gaDi);
                login.putExtra("GaDen", gaDen);
                login.putExtra("DonGia", DonGia);
                login.putExtra("khuhoi",khuhoi);
                startActivityForResult(login,Key1);
        }
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data1) {
        super.onActivityResult(requestCode, resultCode, data1);
        if(resultCode==Key1){
            String id1=data1.getStringExtra("id");
            String gadi1=data1.getStringExtra("GaDi");
            String gaden1=data1.getStringExtra("GaDen");
            String dongia1=data1.getStringExtra("DonGia");
            boolean bo=data1.getBooleanExtra("khuhoi",true);
            double diem1=Double.parseDouble(dongia1);
            int id3=Integer.parseInt(id1);
            data.get(pos).setGaDi(gadi1);
            data.get(pos).setGaDen(gaden1);
            data.get(pos).setDonGia(diem1);
            data.get(pos).setKhuhoi(bo);
            sql.updateVeTau(id3,new VeTau(id3,gadi1,gaden1,diem1,bo));
            data=sql.getAllVeTau();
            adapter.setData(data);
            final double[] tong = new double[1];
            final double[] tong1 = new double[1];

            Collections.sort(data, new Comparator<VeTau>() {

                @Override
                public int compare(VeTau o1, VeTau o2) {
                    if(o1.isKhuhoi()==true){
                        tong[0] =o1.getDonGia()*2*0.95;
                    }
                    if(o1.isKhuhoi()==false){
                        tong[0] =o1.getDonGia();
                    }
                    if(o2.isKhuhoi()==true){
                        tong1[0] =o1.getDonGia()*2*0.95;
                    }
                    if(o2.isKhuhoi()==false){
                        tong1[0] =o1.getDonGia();
                    }
                    if(tong[0] > tong1[0]){
                        return -1;
                    }else {
                        if(tong[0] == tong1[0]){
                            return 0;
                        }else {
                            return 1;
                        }
                    }
                }
            });
            adapter.notifyDataSetChanged();
        }
    }
}
