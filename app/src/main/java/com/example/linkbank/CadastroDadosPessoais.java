package com.example.linkbank;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
import java.util.Locale;

import com.blackcat.currencyedittext.CurrencyEditText;
import com.example.linkbank.model.Usuario;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.santalu.maskara.widget.MaskEditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class CadastroDadosPessoais extends AppCompatActivity {

    private Button btnEndereco;
    private ImageButton btnVoltar;
    private EditText etNome;
    private EditText etData;
    private EditText etCpf;
    private EditText etRg;
    private EditText etEmail;
    private EditText etPhone;
    private EditText etProfissao;
    private EditText etRenda;








    DatePickerDialog.OnDateSetListener onDateSetListener;

    Calendar myCalendar = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_dados_pessoais);

        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);





        etData = findViewById(R.id.etData);

        etData.setOnClickListener(v -> {
            DatePickerDialog datePickerDialog = new DatePickerDialog(
                    CadastroDadosPessoais.this, android.R.style.Theme_Holo_Dialog_MinWidth,
                    onDateSetListener, year, month, day);
            datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            datePickerDialog.show();


        });

        onDateSetListener = (view, year1, month1, dayOfMonth) -> {
            month1 = month1 + 1;
            String date = dayOfMonth + "/" + month1 + "/" + year1;
            etData.setText(date);
        };



        btnVoltar = findViewById(R.id.btnVoltar_dados);
        btnVoltar.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), TermosEPrivacidade.class);
            finish();
            startActivity(intent);
        });
        btnEndereco = findViewById(R.id.btnEndereco);
        btnEndereco.setOnClickListener(view -> {

            boolean sup = true;
            String nome = etNome.getText().toString();
            String cpf = etCpf.getText().toString();
            String rg = etRg.getText().toString();
            String email = etEmail.getText().toString();
            String phone = etPhone.getText().toString();
            String data = etData.getText().toString();
            String profissao = etProfissao.getText().toString();
            String renda = etRenda.getText().toString();

            if(nome.isEmpty()){
               sup = false;
            }
            if(cpf.isEmpty()){
               sup = false;
            }
            if(rg.isEmpty()){
               sup = false;
            }
            if(email.isEmpty()){
               sup = false;
            }
            if(phone.isEmpty()){
               sup = false;
            }
            if(data.isEmpty()){
               sup = false;
            }
            if(profissao.isEmpty()){
               sup = false;
            }
            if(renda.isEmpty()){
               sup = false;
            }


            if(sup == false){
                Toast.makeText(this, "Preencha todos campos!", Toast.LENGTH_SHORT).show();
            }else{
                Intent intent = new Intent(getApplicationContext(), CadastroEndereco.class);
                intent.putExtra("fireNome", etNome.getText().toString());
                intent.putExtra("fireCpf", etCpf.getText().toString());
                intent.putExtra("fireRg", etRg.getText().toString());
                intent.putExtra("fireEmail", etEmail.getText().toString());
                intent.putExtra("firePhone", etPhone.getText().toString());
                intent.putExtra("fireRenda", etRenda.getText().toString());
                intent.putExtra("fireNascimento", etData.getText().toString());
                intent.putExtra("fireProfissao", etProfissao.getText().toString());

                finish();
                startActivity(intent);
            }



        });



        etNome = findViewById(R.id.etNome);
        etCpf = findViewById(R.id.etCpf);
        etRg = findViewById(R.id.etRg);
        etEmail = findViewById(R.id.etEmail);
        etPhone = findViewById(R.id.etPhone);
        etData = findViewById(R.id.etData);
        etProfissao = findViewById(R.id.etProfissao);
        etRenda = findViewById(R.id.etRenda);



    }

}