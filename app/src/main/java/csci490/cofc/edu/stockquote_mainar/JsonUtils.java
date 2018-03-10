package csci490.cofc.edu.stockquote_mainar;

/**
 * Created by smainar on 3/10/2018.
 */

import android.util.Log;
import org.json.JSONObject;

/**
 * Created by kbriggs on 3/5/18.
 */

public class JsonUtils {

    public static JSONObject parseStockQuoteJson(String json) {
        JSONObject quote = null;
        try {
            JSONObject jsonObject = new JSONObject(json);
            quote = jsonObject.getJSONObject("quote");
            Log.i("JSON", quote.toString());

        } catch (Exception ex) {
            Log.e("JSON", "Error parsing JSON");
        }
        return quote;
    }
}
