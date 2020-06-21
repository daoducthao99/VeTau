package com.example.tau;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.tau.MainActivity;
import com.example.tau.R;

public class Sua extends AppCompatActivity implements View.OnClickListener {
    private EditText edGadi;
    private EditText edgaDen;
    private EditText DonGia;
    private RadioButton mot;
    private RadioButton hai;
    private Button btnok;
    private Button btnHuy;
    String id;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sua);
        initViews();
        Intent intent=getIntent();
        id=intent.getStringExtra("id");
        String gaDi1=intent.getStringExtra("GaDi");
        String gaDen1=intent.getStringExtra("GaDen");
        double donGia1=intent.getDoubleExtra("DonGia",0);
        String dongia=String.valueOf(donGia1);
        boolean Diem1=intent.getBooleanExtra("khuhoi",true);
        edGadi.setText(gaDi1);
        edgaDen.setText(gaDen1);
        DonGia.setText(dongia);
        if(Diem1==true){
            hai.setChecked(true);
            mot.setChecked(false);
        }
        if(Diem1==false){
            hai.setChecked(false);
            mot.setChecked(true);
        }

        if(mot.isChecked()){
            hai.setChecked(false);
        }
        if(hai.isChecked()){
            mot.setChecked(false);
        }
    }

    private void initViews() {
        edGadi=findViewById(R.id.ed_di);
        edgaDen=findViewById(R.id.ed_den);
        DonGia=findViewById(R.id.ed_donGia);
        btnok=findViewById(R.id.btn_sua);
        btnHuy=findViewById(R.id.btn_huy);
        mot=findViewById(R.id.rd_mot);
        hai=findViewById(R.id.rd_hai);
        btnok.setOnClickListener(this);
        btnHuy.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_sua:
                if(edGadi.getText().toString().equals("") || edgaDen.getText().toString().equals("") || DonGia.getText().toString().equals("")){
                    Toast.makeText(this,"Bạn chưa nhập đủ thông tin",Toast.LENGTH_SHORT).show();
                }
                if(!edGadi.getText().toString().equals("") && !edgaDen.getText().toString().equals("") && !DonGia.getText().toString().equals("")) {
                    String id1=id;
                    String Gadi=edGadi.getText().toString();
                    String Gaden=edgaDen.getText().toString();
                    String dongia=DonGia.getText().toString();
                    Boolean khuhoi;
                    if(mot.isChecked() || hai.isChecked()){
                        khuhoi=true;
                    }else {
                        khuhoi=false;
                    }
                    Intent intent=new Intent(this, MainActivity.class);
                    intent.putExtra("id", id1);
                    intent.putExtra("GaDi", Gadi);
                    intent.putExtra("GaDen", Gaden);
                    intent.putExtra("DonGia", dongia);
                    intent.putExtra("khuhoi",khuhoi);
                    setResult(MainActivity.Key1,intent);
                    finish();
                }
                break;
            case R.id.btn_huy:
                onBackPressed();
                break;
        }
    }
}
