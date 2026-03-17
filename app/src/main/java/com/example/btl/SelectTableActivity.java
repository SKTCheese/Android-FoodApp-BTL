package com.example.btl;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SelectTableActivity extends AppCompatActivity {

    private EditText edtTableCode;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_table);

        edtTableCode = findViewById(R.id.edtTableCode);
        // Khởi tạo Firebase tham chiếu đến node "Tables"
        mDatabase = FirebaseDatabase.getInstance("https://btl-staff-order-default-rtdb.asia-southeast1.firebasedatabase.app/")
                .getReference("Tables");
    }

    public void confirmTable(View view) {
        String tableCode = edtTableCode.getText().toString().trim().toUpperCase();

        if (tableCode.isEmpty()) {
            edtTableCode.setError("Please enter table code");
            edtTableCode.requestFocus();
            return;
        }

        // Kiểm tra bàn trên Firebase
        mDatabase.child(tableCode).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    // Nếu bàn tồn tại
                    Intent intent = new Intent(SelectTableActivity.this, MainActivity.class);
                    intent.putExtra("tableCode", tableCode);
                    startActivity(intent);
                    finish();
                } else {
                    // Nếu bàn không tồn tại
                    Toast.makeText(SelectTableActivity.this, "Table " + tableCode + " does not exist!", Toast.LENGTH_LONG).show();
                    edtTableCode.setError("Invalid Table Code");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(SelectTableActivity.this, "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
