package fr.wildcodeschool.getdiamond;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static com.android.volley.VolleyLog.TAG;

class ApiSingleton {

    private final static String API_URL = "http://10.0.2.2:8080/api/";
    //private final static String API_URL = "http://localhost:8080/api/";
    private static Context mCtx;
    private RequestQueue mRequestQueue;
    Gson gson = new Gson();
    private static ApiSingleton mInstance;

    ArrayList<UserModel> userList = new ArrayList<>();

/*
    private static final ApiSingleton ourInstance = new ApiSingleton();

    static ApiSingleton getInstance() {
        return ourInstance;
    }
*/
    public static synchronized ApiSingleton getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new ApiSingleton(context);
        }
        return mInstance;
    }

    private ApiSingleton(Context context) {
        mCtx = context;
        mRequestQueue = getRequestQueue();
    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(mCtx.getApplicationContext());
        }
        return mRequestQueue;
    }

    public void jsonCallUser(final ApiListener listener) {
        String url = API_URL + "users";

        JsonArrayRequest jsonObjectRequest = new JsonArrayRequest(Request.Method.GET, url,
                null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray results) {
                try {

                    for (int i = 0; i < results.length(); i++) {

                        JSONObject userObject = results.getJSONObject(i);
                        long id = userObject.getLong("id");
                        String name = userObject.getString("name");
                        String password = userObject.getString("password");
                        int money = userObject.getInt("money");
                        int diamond = userObject.getInt("diamond");
                        int opal = userObject.getInt("opal");
                        int emerald = userObject.getInt("emerald");
                        int ruby = userObject.getInt("ruby");
                        String date = userObject.getString("lastMining");
                        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                        Date lastMining = null;
                        try {
                            lastMining = sdf.parse(date);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }

                        JewelryModel indent1 = null;
                        if (!userObject.isNull("indent1")) {
                            JSONObject jewelryJson = userObject.getJSONObject("indent1");
                            indent1 = gson.fromJson(jewelryJson.toString(), JewelryModel.class);
                        }

                        JewelryModel indent2 = null;
                        if (!userObject.isNull("indent2")) {
                            JSONObject jewelryJson2 = userObject.getJSONObject("indent2");
                            indent2 = gson.fromJson(jewelryJson2.toString(), JewelryModel.class);
                        }

                        JewelryModel indent3 = null;
                        if (!userObject.isNull("indent3")) {
                            JSONObject jewelryJson3 = userObject.getJSONObject("indent3");
                            indent2 = gson.fromJson(jewelryJson3.toString(), JewelryModel.class);
                        }

                        int totalExchange = userObject.getInt("totalExchange");
                        int totalBuilt = userObject.getInt("totalBuilt");

                        UserModel userJson = new UserModel(id, name, password, money, diamond, opal,
                                emerald, ruby, lastMining, indent1, indent2, indent3,
                                totalExchange, totalBuilt);

                        userList.add(userJson);
                    }
                    listener.onResponse(true);

                } catch (JSONException e) {
                    e.printStackTrace();
                    listener.onResponse(false);
                }
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "onErrorResponse: " + error.getMessage());
                listener.onResponse(false);
            }
        });
        getRequestQueue().add(jsonObjectRequest);
    }

    public ArrayList<UserModel> getUserList() {
        return userList;
    }

    public void setUserList(ArrayList<UserModel> userList) {
        this.userList = userList;
    }


}
