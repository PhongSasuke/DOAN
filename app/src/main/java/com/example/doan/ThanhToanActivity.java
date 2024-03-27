package com.example.doan;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.paypal.android.sdk.payments.PayPalConfiguration;
import com.paypal.android.sdk.payments.PayPalPayment;
import com.paypal.android.sdk.payments.PayPalService;
import com.paypal.android.sdk.payments.PaymentActivity;
import com.paypal.android.sdk.payments.PaymentConfirmation;

import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigDecimal;

public class ThanhToanActivity extends AppCompatActivity {
    EditText edtAmount;
    Button btnPayment;

    String clientId = "AeDzUjBl6Kd-hm3xMxKXv0SQJk3tRarscK6O-D0cIThNUk5ak3_HShasv77OdKv5C0ikYbjRmWxE9bJ7";

    int PAYPAL_REQUEST_CODE = 123;

    public  static PayPalConfiguration configuration;
    @Override
    protected void onCreate (Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_thanhtoan);

        edtAmount = findViewById(R.id.edtAmount);
        btnPayment = findViewById(R.id.btnPayment);

        Bundle bundle = getIntent().getExtras();
        float defaultValue = 0.0f; // giá trị mặc định được truyền vào
        float gia = bundle.getFloat("tongTien",defaultValue);
        gia = gia / 23000;
        String giaFloatValue = String.format("%.2f",gia);

        edtAmount.setText(String.valueOf(giaFloatValue));
        configuration = new PayPalConfiguration().environment(PayPalConfiguration.ENVIRONMENT_SANDBOX)
                .clientId(clientId);


        btnPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getPayment();

            }
        });
    }

    private void getPayment(){
        String amounts = edtAmount.getText().toString();

        PayPalPayment payment = new PayPalPayment(new BigDecimal(String.valueOf(amounts)), "USD", "code with Arvind",PayPalPayment.PAYMENT_INTENT_SALE);

        Intent intent = new Intent(this, PaymentActivity.class);
        intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION,configuration);
        intent.putExtra(PaymentActivity.EXTRA_PAYMENT,payment);

        startActivityForResult(intent,PAYPAL_REQUEST_CODE);


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if(requestCode==PAYPAL_REQUEST_CODE){

            PaymentConfirmation paymentConfirmation = data.getParcelableExtra(PaymentActivity.EXTRA_RESULT_CONFIRMATION);

            if (paymentConfirmation != null) {



                try {

                    String paymentDetails = paymentConfirmation.toJSONObject().toString();
                    JSONObject object = new JSONObject(paymentDetails);


                } catch (JSONException e) {

                    Toast.makeText(this, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

                }
            }else if (requestCode== Activity.RESULT_CANCELED){

                Toast.makeText(this, "error", Toast.LENGTH_SHORT).show();

            }

        }else if (requestCode == PaymentActivity.RESULT_EXTRAS_INVALID){

            Toast.makeText(this, "Invalid payment", Toast.LENGTH_SHORT).show();

        }
    }
}
