package com.example.electric;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    // Input section views
    EditText inputKWh, inputRebate;
    Button btnCalculate, btnClear;

    // Result section views
    LinearLayout inputSection, resultSection;
    TextView tvCharges, tvTotalCharges, tvFinalCost;
    Button btnBack, btnAbout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize input section views
        inputKWh = findViewById(R.id.inputKWh);
        inputRebate = findViewById(R.id.inputRebate);
        btnCalculate = findViewById(R.id.btnCalculate);
        btnClear = findViewById(R.id.btnClear);
        //btnAbout = findViewById(R.id.btnAbout);

        // Initialize result section views
        inputSection = findViewById(R.id.inputSection);
        resultSection = findViewById(R.id.resultSection);
        //tvCharges = findViewById(R.id.tvCharges);
        tvTotalCharges = findViewById(R.id.tvTotalCharges);
        tvFinalCost = findViewById(R.id.tvFinalCost);
        btnBack = findViewById(R.id.btnBack);

        // Ensure the ActionBar is set
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Electricity Bill Estimator");
        }

        // Ensure ActionBar is displayed (if not already set)
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Electricity Bill Estimator");
        }

        // Calculate button click listener
        btnCalculate.setOnClickListener(view -> {
            try {
                double kWh = Double.parseDouble(inputKWh.getText().toString());
                double rebate = Double.parseDouble(inputRebate.getText().toString());

                if (rebate < 0 || rebate > 5) {
                    Toast.makeText(this, "Rebate must be between 0% and 5%", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Calculate charges
                double[] blocks = calculateCharges(kWh);
                double totalCharges = blocks[0] + blocks[1] + blocks[2] + blocks[3];
                double finalCost = totalCharges - (totalCharges * rebate / 100);

                // Update result section
                //tvCharges.setText(String.format("Block 1: RM %.2f\nBlock 2: RM %.2f\nBlock 3: RM %.2f\nBlock 4: RM %.2f",
                        //blocks[0], blocks[1], blocks[2], blocks[3]));
                tvTotalCharges.setText(String.format("Total Charges: RM %.2f", totalCharges));
                tvFinalCost.setText(String.format("Final Cost After Rebate: RM %.2f", finalCost));

                // Show result section
                inputSection.setVisibility(View.GONE);
                resultSection.setVisibility(View.VISIBLE);
            } catch (NumberFormatException e) {
                Toast.makeText(this, "Please enter valid numbers", Toast.LENGTH_SHORT).show();
            }
        });

        // About button
        //btnAbout.setOnClickListener(view -> {
            // Navigate to the About Page
            //Intent intent = new Intent(MainActivity.this, AboutActivity.class);
            //startActivity(intent);
        //});

        // Clear button click listener
        btnClear.setOnClickListener(view -> {
            inputKWh.setText("");
            inputRebate.setText("");
        });

        // Back button click listener
        btnBack.setOnClickListener(view -> {
            // Show input section
            resultSection.setVisibility(View.GONE);
            inputSection.setVisibility(View.VISIBLE);
        });
    }

    private double[] calculateCharges(double kWh) {
        double[] blocks = new double[4];
        if (kWh <= 200) {
            blocks[0] = kWh * 0.218;
        } else if (kWh <= 300) {
            blocks[0] = 200 * 0.218;
            blocks[1] = (kWh - 200) * 0.334;
        } else if (kWh <= 600) {
            blocks[0] = 200 * 0.218;
            blocks[1] = 100 * 0.334;
            blocks[2] = (kWh - 300) * 0.516;
        } else {
            blocks[0] = 200 * 0.218;
            blocks[1] = 100 * 0.334;
            blocks[2] = 300 * 0.516;
            blocks[3] = (kWh - 600) * 0.546;
        }
        return blocks;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        // Handle menu item clicks
        if (item.getItemId() == R.id.menu_about) {
            // Redirect to AboutActivity
            Intent intent = new Intent(MainActivity.this, AboutActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}