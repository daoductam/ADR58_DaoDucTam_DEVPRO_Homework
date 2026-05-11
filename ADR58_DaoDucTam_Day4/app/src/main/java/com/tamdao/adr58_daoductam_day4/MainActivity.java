package com.tamdao.adr58_daoductam_day4;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private TextView tvDescription, btnReadMore;
    private boolean isExpanded = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        
        // Handle Edge-to-Edge padding
        View mainView = findViewById(R.id.main);
        ViewCompat.setOnApplyWindowInsetsListener(mainView, (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialize Views
        tvDescription = findViewById(R.id.tvDescription);
        btnReadMore = findViewById(R.id.btnReadMore);

        // Click listener for Expand/Collapse
        btnReadMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isExpanded) {
                    // Expand
                    tvDescription.setMaxLines(Integer.MAX_VALUE);
                    btnReadMore.setText("Collapse");
                    isExpanded = true;
                } else {
                    // Collapse
                    tvDescription.setMaxLines(3);
                    btnReadMore.setText("Read More...");
                    isExpanded = false;
                }
            }
        });
    }
}