package com.nletechnologies.smartmirrorapp1;

import java.text.Format;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.Locale;

import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    // Weather View Temperature Fields
    TextView currentTemp;
    TextView temperatureHour1;
    TextView temperatureHour2;
    TextView temperatureHour3;
    TextView temperatureHour4;
    // Weather View Times
    TextView weatherCurrentTime;
    TextView weatherCurrentTime1;
    TextView weatherCurrentTime2;
    TextView weatherCurrentTime3;
    TextView weatherCurrentTime4;
    TextView headline1;
    TextView headline2;
    TextView headline3;
    TextView headline4;
    TextView localTime;
    TextView coordinates;
    ImageView weatherView;
    ImageView weatherView1;
    ImageView weatherView2;
    ImageView weatherView3;
    ImageView weatherView4;
    // Refresh App Timer
    private Timer timer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // Remove status bar
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);

        // Hide Action Bar
        getSupportActionBar().hide();

        // Apply XML Layout
        setContentView(R.layout.activity_main);
        // Track Date field
        localTime = findViewById(R.id.localTime);
        // Get and Format Date
        DateFormat df = new SimpleDateFormat(" M/dd/yyyy ", Locale.getDefault());
        String currentDate = df.format(new Date());

        // Get Current Hour For Weather Forcast
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        int hour = calendar.get(Calendar.HOUR);

        // Temperatures
        currentTemp = findViewById(R.id.currentWeather);
        temperatureHour1 = findViewById(R.id.Hour1);
        temperatureHour2 = findViewById(R.id.Hour2);
        temperatureHour3 = findViewById(R.id.Hour3);
        temperatureHour4 = findViewById(R.id.Hour4);

        // Weather Times
        weatherCurrentTime = findViewById(R.id.weatherViewTime);
        weatherCurrentTime1 = findViewById(R.id.weatherViewTime1);
        weatherCurrentTime2 = findViewById(R.id.weatherViewTime2);
        weatherCurrentTime3 = findViewById(R.id.weatherViewTime3);
        weatherCurrentTime4 = findViewById(R.id.weatherViewTime4);

        // Set Date Field
        localTime.setText(currentDate);

        weatherView = findViewById(R.id.weatherIcon);
        weatherView1 = findViewById(R.id.weatherIcon1);
        weatherView2 = findViewById(R.id.weatherIcon2);
        weatherView3 = findViewById(R.id.weatherIcon3);
        weatherView4 = findViewById(R.id.weatherIcon4);

        // Top Headlines
        headline1 = findViewById(R.id.headline1);
        headline2 = findViewById(R.id.headline2);
        headline3 = findViewById(R.id.headline3);
        headline4 = findViewById(R.id.headline4);

        coordinates = findViewById(R.id.coordinates);
        double latitude = 40.442829;
        double longitude = -79.950432;
        String weatherURL = "https://api.open-meteo.com/v1/forecast?latitude="
                + Double.toString(latitude) + "&longitude=" + Double.toString(longitude)
                + "&current_weather=true&hourly=apparent_temperature,relativehumidity_2m,windspeed_10m,weathercode,is_day&timezone=America%2FNew_York";

        // JSON Data API for Test
        String testAPI = "https://jsonplaceholder.typicode.com/todos/1";
        startTimer();
    }

    private void startTimer() {
        timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                // Code to update the date, time, and weather information
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        // Update the UI elements with the refreshed information
                        updateDateTime();
                        updateWeather();
                        updateNews();
                    }
                });
            }
        };

        // Schedule the timer task to run every 10 minutes (adjust the interval as needed)
        long delay = 0;
        long interval = 30 * 60 * 1000; // 10 minutes
        timer.scheduleAtFixedRate(timerTask, delay, interval);
    }

    private void updateDateTime() {
        // Get and Format Date
        DateFormat df = new SimpleDateFormat(" M/dd/yyyy ", Locale.getDefault());
        String currentDate = df.format(new Date());

        // Update the date field
        localTime.setText(currentDate);
    }

    private void updateNews() {
        String apiKey = "xxxxxxxxxxxx";
        String newsURL = "https://newsapi.org/v2/top-headlines?country=us&apiKey=" + apiKey + "&pageSize=10";
        JsonObjectRequest jsonObjectRequestNews = new JsonObjectRequest(Request.Method.GET, newsURL, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // Handle the response
                        String[] displayArticles = new String[4];

                        try {
                            response = new JSONObject(response.toString());
                            JSONArray articlesArr = new JSONArray();

                            JSONObject totalResults = new JSONObject();
                            articlesArr = response.getJSONArray("articles");

                            JSONArray articlesArrTitle = new JSONArray();
                            JSONArray articles = new JSONObject(String.valueOf(response)).getJSONArray("articles");

                            // Add news headlines to String Arr
                            for (int i = 0; i < displayArticles.length; i++) {
                                JSONObject article = articles.getJSONObject(i);
                                String title = article.getString("title");
                                displayArticles[i] = title;
                            }

                            // Display News Headlines
                            headline1.setText(displayArticles[0].toString());
                            headline2.setText(displayArticles[1].toString());
                            headline3.setText(displayArticles[2].toString());
                            headline4.setText(displayArticles[3].toString());

                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                headline1.setText("News Loading Error");
            }
        }) {
            /* including the "User-Agent" header, you simulate a request similar to that of a browser,
                which helps bypass any potential restrictions that the NewsAPI.org server might have.
                NewsAPI.org may require specific headers or user agent information to authorize the request.
                Set the appropriate headers in your app's request object.
             */
            @Override
            public Map<String, String> getHeaders() {
                Map<String, String> headers = new HashMap<>();
                headers.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36");
                return headers;
            }
        };
        RequestQueue requestQueue1 = Volley.newRequestQueue(this);
        requestQueue1.add(jsonObjectRequestNews);
    }

    private void updateWeather() {

        double latitude = 40.442829;
        double longitude = -79.950432;
        String weatherURL = "https://api.open-meteo.com/v1/forecast?latitude="
                + Double.toString(latitude) + "&longitude=" + Double.toString(longitude)
                + "&current_weather=true&hourly=apparent_temperature,relativehumidity_2m,windspeed_10m,weathercode,is_day&timezone=America%2FNew_York";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(weatherURL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                // fiveDayForcast.setText(response.toString());
                try {

                    double lat = response.getDouble("latitude");
                    // Get Weather code
                    int weatherCode = response.getJSONObject("current_weather").getInt("weathercode");
                    // Get Current and Parse temperature
                    double temperature = response.getJSONObject("current_weather").getDouble("temperature");
                    // Convert to F
                    temperature = (temperature * 9 / 5) + 32;
                    temperature = (int) temperature;

                    // Array of Hourly Temps
                    JSONArray tempArr = new JSONArray();
                    tempArr = response.getJSONObject("hourly").getJSONArray("apparent_temperature");

                    // Array of Corresponding Times
                    JSONArray tempTimeArr = new JSONArray();
                    tempTimeArr = response.getJSONObject("hourly").getJSONArray("time");
                    String currApiTime = response.getJSONObject("current_weather").getString("time");

                    // Array of Hourly Weather Codes
                    JSONArray hourlyCodeArr = new JSONArray();
                    hourlyCodeArr = response.getJSONObject("hourly").getJSONArray("weathercode");

                    // Array of Hourly Night or Day variable
                    JSONArray hourlyCodeNightOrDay = new JSONArray();
                    hourlyCodeNightOrDay = response.getJSONObject("hourly").getJSONArray("is_day");

                    int currentTempIndex = 0;

                     /* Finds Current Time in Array: tempTimeArr
                        - Uses The index to assist in
                            displaying correct Hourly Temps
                            by finding the temp at index i of:
                            tempArr ("apparent_temperature")
                     */
                    try {
                        found:
                        for (int i = 0; i < tempTimeArr.length(); i++) {
                            if (tempTimeArr.getString(i).equals(currApiTime)) {
                                currentTempIndex = i;
                                break found;
                            }
                        }
                    } catch (Exception e) {
                        // If Not found ERROR in JSON Data
                        System.out.println("Parsing Date Error");
                        currentTempIndex = 0;
                    }

                    // Hourly Temperatures
                    double tempHour1 = (int) (tempArr.getDouble(currentTempIndex + 1) * 9 / 5) + 32;
                    double tempHour2 = (int) (tempArr.getDouble(currentTempIndex + 2) * 9 / 5) + 32;
                    double tempHour3 = (int) (tempArr.getDouble(currentTempIndex + 3) * 9 / 5) + 32;
                    double tempHour4 = (int) (tempArr.getDouble(currentTempIndex + 4) * 9 / 5) + 32;

                    // Set Weather Images
                    WeatherImage.setImageForValue(weatherCode, weatherView, hourlyCodeNightOrDay.getInt(currentTempIndex));
                    WeatherImage.setImageForValue(hourlyCodeArr.getInt(currentTempIndex + 1), weatherView1, hourlyCodeNightOrDay.getInt(currentTempIndex + 1));
                    WeatherImage.setImageForValue(hourlyCodeArr.getInt(currentTempIndex + 2), weatherView2, hourlyCodeNightOrDay.getInt(currentTempIndex + 2));
                    WeatherImage.setImageForValue(hourlyCodeArr.getInt(currentTempIndex + 3), weatherView3, hourlyCodeNightOrDay.getInt(currentTempIndex + 3));
                    WeatherImage.setImageForValue(hourlyCodeArr.getInt(currentTempIndex + 4), weatherView4, hourlyCodeNightOrDay.getInt(currentTempIndex + 4));

                    // Set Temperature TextFields
                    currentTemp.setText(String.format("%.0f", temperature) + "°F");
                    temperatureHour1.setText(String.format("%.0f", tempHour1) + "°F");
                    temperatureHour2.setText(String.format("%.0f", tempHour2) + "°F");
                    temperatureHour3.setText(String.format("%.0f", tempHour3) + "°F");
                    temperatureHour4.setText(String.format("%.0f", tempHour4) + "°F");

                    // Format JSON Date Data From timeTempArr : ("hourly":{time}
                    Format f = new SimpleDateFormat("a");
                    String strMarker = f.format(new Date());

                    // Get and Format Time From Api
                    String timeHr1 = tempTimeArr.getString(currentTempIndex + 1).substring(11,13);
                    String timeHr2 = tempTimeArr.getString(currentTempIndex + 2).substring(11,13);
                    String timeHr3 = tempTimeArr.getString(currentTempIndex + 3).substring(11,13);
                    String timeHr4 = tempTimeArr.getString(currentTempIndex + 4).substring(11,13);

                    // Display Weather Times
                    weatherCurrentTime.setText("Now");
                    // Hour 1
                    if(timeHr1.charAt(0) == '0')
                        weatherCurrentTime1.setText(getWeatherViewTime(Character.getNumericValue(timeHr1.charAt(1)), strMarker));
                    else
                        weatherCurrentTime1.setText(getWeatherViewTime(Integer.parseInt(timeHr1),strMarker));
                    // Hour 2
                    if(timeHr2.charAt(0) == '0')
                        weatherCurrentTime2.setText(getWeatherViewTime(Character.getNumericValue(timeHr2.charAt(1)), strMarker));
                    else
                        weatherCurrentTime2.setText(getWeatherViewTime(Integer.parseInt(timeHr2),strMarker));
                    // Hour 3
                    if(timeHr3.charAt(0) == '0')
                        weatherCurrentTime3.setText(getWeatherViewTime(Character.getNumericValue(timeHr3.charAt(1)), strMarker));
                    else
                        weatherCurrentTime3.setText(getWeatherViewTime(Integer.parseInt(timeHr3),strMarker));
                    //Hour 4
                    if(timeHr4.charAt(0) == '0')
                        weatherCurrentTime4.setText(getWeatherViewTime(Character.getNumericValue(timeHr4.charAt(1)), strMarker));
                    else
                        weatherCurrentTime4.setText(getWeatherViewTime(Integer.parseInt(timeHr4),strMarker));

                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                currentTemp.setText("Weather Loading Error");
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonObjectRequest);
    }

    public static String getWeatherViewTime(int val, String amOrPm) {
        int tempTime = 0;

        String temp = "";
        if (val == 0) {
            tempTime = 12;
            temp = tempTime + amOrPm;
            return temp;
        }
        if(val < 12 ) {
            amOrPm = " AM";
            temp = val + amOrPm;
        } else {
            amOrPm = "PM";
                if (val == 12) {
                    temp = val + amOrPm;
                } else {
                    tempTime = val - 12;
                    temp = tempTime + amOrPm;
                }
            }
        return temp;
    }
}