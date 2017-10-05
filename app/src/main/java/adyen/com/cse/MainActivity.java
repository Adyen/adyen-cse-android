package adyen.com.cse;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

import adyen.com.adyencse.encrypter.exception.EncrypterException;
import adyen.com.adyencse.pojo.Card;

public class MainActivity extends AppCompatActivity {

    private EditText mCardNumber;
    private EditText mCardExpiryDate;
    private EditText mCardCvc;
    private TextView mCseResult;

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
        mCseResult = (TextView)findViewById(R.id.cse_result);
    }

    private void initData() {
        cardNumber = mCardNumber.getText().toString();
        cardExpiryDate = mCardExpiryDate.getText().toString();
        cardCvc = mCardCvc.getText().toString();
    }

    public void initPayment(View payButton) {
        initData();
        ConfigLoader configLoader = new ConfigLoader();
        JSONObject configuration = configLoader.loadJsonConfiguration();
        Card card = buildCardData();

        if (card == null) {
            return;
        }

        try {
            String encryptedCardData = card.serialize(configuration.getString("publicKey"));
            mCseResult.setText(encryptedCardData);
        } catch (EncrypterException | JSONException e) {
            mCseResult.setText(e.getLocalizedMessage());
        }
    }

    private Card buildCardData() {
        Card card = null;

        try {
            card = new Card.Builder()
                    .setHolderName("test")
                    .setCvc(cardCvc)
                    .setExpiryMonth(cardExpiryDate.split("/")[0])
                    .setExpiryYear(cardExpiryDate.split("/")[1])
                    .setGenerationTime(new Date())
                    .setNumber(cardNumber)
                    .build();
        } catch (NullPointerException | IllegalStateException | ArrayIndexOutOfBoundsException e) {
            mCseResult.setText(e.getClass().getSimpleName() + ": " + e.getLocalizedMessage());
        }

        return card;
    }


}
