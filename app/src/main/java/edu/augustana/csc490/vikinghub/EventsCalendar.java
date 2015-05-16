package edu.augustana.csc490.vikinghub;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import android.view.Menu;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;


class ProductTest
{

    public String name;
    public String quantity;
    public String color;

}

public class EventsCalendar extends Activity {

        private final String URL_STRING = "http://www.augustana.edu/prebuilt/acal/calpage.php?mode=js&viewid=13";

        @Override
        protected void onCreate(Bundle savedInstanceState) {

            super.onCreate(savedInstanceState);
            setContentView(R.layout.events_layout);

            TextView display = (TextView)findViewById(R.id.info);

            Calendar cal = Calendar.getInstance();
            cal.setTime(new Date());
            String month = "&month=" + cal.get(Calendar.MONTH);
            String year = "&year=" + cal.get(Calendar.YEAR);

            String url = URL_STRING+year+month;
            Log.w("URL",url);
            HTMLParser parser = new HTMLParser();
            parser.sendDisplay(display);
            parser.execute(url);

        }

        /*private void parseXML(XmlPullParser parser) throws XmlPullParserException,IOException {
            ArrayList<ProductTest> products = null;
            int eventType = parser.getEventType();
            ProductTest currentProduct = null;

            while (eventType != XmlPullParser.END_DOCUMENT){
                String name = null;
                switch (eventType){
                    case XmlPullParser.START_DOCUMENT:
                        products = new ArrayList();
                        break;
                    case XmlPullParser.START_TAG:
                        name = parser.getName();
                        if (name.equals("product")){
                            currentProduct = new ProductTest();
                        } else if (currentProduct != null){
                            if (name.equals("productname")){
                                currentProduct.name = parser.nextText();
                            } else if (name.equals("productcolor")){
                                currentProduct.color = parser.nextText();
                            } else if (name.equals("productquantity")){
                                currentProduct.quantity= parser.nextText();
                            }
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        name = parser.getName();
                        if (name.equalsIgnoreCase("product") && currentProduct != null){
                            products.add(currentProduct);
                        }
                }
                eventType = parser.next();
            }

            printProducts(products);
        }

    private void printProducts(ArrayList<ProductTest> products)
    {
        String content = "";
        Iterator<ProductTest> it = products.iterator();
        while(it.hasNext())
        {
            ProductTest currProduct  = it.next();
            content = content + "Product :" +  currProduct.name + "\n";
            content = content + "Quantity :" +  currProduct.quantity + "\n";
            content = content + "Color :" +  currProduct.color + "\n \n";

        }

        TextView display = (TextView)findViewById(R.id.info);
        display.setText(content);
    }*/

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
}

class HTMLParser extends AsyncTask<String, Void, String> {
    TextView display;
    @Override
    protected String doInBackground(String... strings) {
        StringBuilder stringBuilder = new StringBuilder();
        String uglyDoc = "";
        try {
            Log.d("JSwa", "Connecting to [" + strings[0] + "]");
            Document doc  = Jsoup.connect(strings[0]).get();
            Log.d("JSwa", "Connected to ["+strings[0]+"]");

            uglyDoc = doc.toString();
            String[] uglyDocArray = uglyDoc.split("\n");
            for(String line : uglyDocArray){
                Log.w("line",line);
            }

        }
        catch(Throwable t) {
            t.printStackTrace();
        }

        return uglyDoc;
    }

    protected void sendDisplay(TextView display){
        this.display = display;
    }

    @Override
    protected void onPostExecute(String s) {
        //Question the ordering of these two lines. May need to call display.setText before super executes.
        super.onPostExecute(s);
        display.setText(s);
    }

    private void formatText(){

    }
}





