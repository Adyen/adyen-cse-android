package adyen.com.cse;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText mCardNumber;
    private EditText mCardExpiryDate;
    private EditText mCardCvc;

    private String cardNumber;
    private String cardExpiryDate;
    private String cardCvc;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mCardNumber = (EditText)findViewById(R.id.credit_card_no);
        mCardExpiryDate = (EditText)findViewById(R.id.credit_card_exp_date);
        mCardCvc = (EditText)findViewById(R.id.credit_card_cvc);

        initData();
    }

    private void initData() {
        cardNumber = mCardNumber.getText().toString();
        cardExpiryDate = mCardExpiryDate.getText().toString();
        cardCvc = mCardCvc.getText().toString();

        Log.i("MainActivity|Card data", cardNumber + " | " + cardExpiryDate + " | " + cardCvc);
    }


}
