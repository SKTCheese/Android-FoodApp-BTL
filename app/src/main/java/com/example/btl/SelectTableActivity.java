package com.example.btl;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class SelectTableActivity extends AppCompatActivity {

    private EditText edtTableCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_table);

        edtTableCode = findViewById(R.id.edtTableCode);
    }

    public void confirmTable(View view) {
        String tableCode = edtTableCode.getText().toString().trim();

        if (tableCode.isEmpty()) {
            edtTableCode.setError("Please enter table code");
            edtTableCode.requestFocus();
            return;
        }

        Intent intent = new Intent(SelectTableActivity.this, MainActivity.class);
        intent.putExtra("tableCode", tableCode);
        startActivity(intent);
        finish();
    }
}