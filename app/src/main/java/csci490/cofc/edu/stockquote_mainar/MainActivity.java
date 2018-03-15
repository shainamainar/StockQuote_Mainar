package csci490.cofc.edu.stockquote_mainar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.os.AsyncTask;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText editText;
    private TextView symbol, name, lastPr, lastTi, change, range;
    private String symbolStr, nameStr, lastPrStr, lastTiStr, changeStr, rangeStr;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = this.findViewById(R.id.edit_text);
        editText.setOnKeyListener(mKeyListener);
        symbol = this.findViewById(R.id.symbol);
        name = this.findViewById(R.id.name);
        lastPr = this.findViewById(R.id.lastPrice);
        lastTi = this.findViewById(R.id.lastTime);
        change = this.findViewById(R.id.change);
        range = this.findViewById(R.id.range);

    }
    private View.OnKeyListener mKeyListener = new View.OnKeyListener() {
        @Override
        public boolean onKey(View v, int keyCode, KeyEvent event) {
            Stock stock;
            getStock gs = new getStock();
            gs.execute(editText.getText().toString());
            try{
                stock = gs.get();
                if(stock == null || stock.getSymbol() == "null"){
                    Toast.makeText(getApplicationContext(), "dis no bueno", Toast.LENGTH_SHORT).show();
                    return false;
                }else{
                    symbol.setText(symbolStr);
                    name.setText(nameStr);
                    lastPr.setText(lastPrStr);
                    lastTi.setText(lastTiStr);
                    change.setText(changeStr);
                    range.setText(rangeStr);
                }
            }catch (Exception e){
                Log.i("Error", e.getMessage());
            }
            return false;
        }
    };
    private class getStock extends AsyncTask<String, Integer, Stock>{
        @Override
        protected Stock doInBackground(String... strings) {
            Stock stock = new Stock(strings[0]);
            try {
                stock.load();
                symbolStr = stock.getSymbol();
                nameStr = stock.getName();
                lastPrStr = stock.getLastTradePrice();
                lastTiStr = stock.getLastTradeTime();
                changeStr = stock.getChange();
                rangeStr = stock.getRange();
            }
            catch (Exception e){
                Log.i("Error", e.getMessage());
            }
            return stock;
        }
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putString("symbolString", symbolStr);
        bundle.putString("nameString", nameStr);
        bundle.putString("lastPriceString", lastPrStr);
        bundle.putString("lastTimeString", lastTiStr);
        bundle.putString("changeString", changeStr);
        bundle.putString("rangeString", rangeStr);
        bundle.putString("inputString", editText.getText().toString());
    }
    public void onRestoreInstanceState(Bundle bundle){
        if(bundle != null){
            super.onRestoreInstanceState(bundle);
            symbol.setText(bundle.getString("symbolString"));
            name.setText(bundle.getString("nameString"));
            lastPr.setText(bundle.getString("lastPriceString"));
            lastTi.setText(bundle.getString("lastTimeString"));
            change.setText(bundle.getString("changeString"));
            range.setText(bundle.getString("rangeString"));
            editText.setText(bundle.getString("inputString"));
            symbolStr = bundle.getString("symbolString");
            nameStr = bundle.getString("nameString");
            lastPrStr = bundle.getString("lastPriceString");
            lastTiStr = bundle.getString("lastTimeString");
            changeStr = bundle.getString("changeString");
            rangeStr = bundle.getString("rangeString");
        }
    }
}
