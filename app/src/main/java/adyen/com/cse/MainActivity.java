package adyen.com.cse;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

import adyen.com.cse.encrypter.exception.EncrypterException;

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
        try {
            String encryptedCardData = card.serialize(configuration.getString("publicKey"));

            Log.i("=======> ", "ecryptedCard=" + encryptedCardData);

            mCseResult.setText(encryptedCardData);
        } catch (EncrypterException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private Card buildCardData() {
        Card card = new Card();

        card.setCardHolderName("test");
        card.setCvc(cardCvc);
        card.setExpiryMonth(cardExpiryDate.split("/")[0]);
        card.setExpiryYear("20" + cardExpiryDate.split("/")[1]);
        card.setGenerationTime(new Date());
        card.setNumber(cardNumber);

        return card;
    }


}
