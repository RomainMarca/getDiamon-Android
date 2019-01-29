package fr.wildcodeschool.getdiamond;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import fr.wildcodeschool.getdiamond.models.ExchangeModel;
import fr.wildcodeschool.getdiamond.models.JewelryModel;
import fr.wildcodeschool.getdiamond.models.UserModel;

import static com.android.volley.VolleyLog.TAG;

class ApiSingleton {

    private final static String API_URL = "http://10.0.2.2:8080/api/";
    private static Context mCtx;
    private RequestQueue mRequestQueue;
    Gson gson = new Gson();
    private static ApiSingleton mInstance;

    ArrayList<UserModel> userList = new ArrayList<>();
    ArrayList<JewelryModel> jewelryList = new ArrayList<>();
    ArrayList<ExchangeModel> exchangeList = new ArrayList<>();

    UserModel currentUser;
    JewelryModel currentJewel;
    UserModel currentReceiver;

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

    public void jsonCallJewelry(final ApiListener listener) {
        String url = API_URL + "jewelryfalse";

        JsonArrayRequest jsonObjectRequest = new JsonArrayRequest(Request.Method.GET, url,
                null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray results) {
                try {

                    for (int i = 0; i < results.length(); i++) {

                        JSONObject jewelryObject = results.getJSONObject(i);
                        long id = jewelryObject.getLong("id");
                        String name = jewelryObject.getString("name");
                        int diamond = jewelryObject.getInt("diamond");
                        int opal = jewelryObject.getInt("opal");
                        int emerald = jewelryObject.getInt("emerald");
                        int ruby = jewelryObject.getInt("ruby");
                        int gain = jewelryObject.getInt("gain");
                        int resale = jewelryObject.getInt("resale");
                        boolean built = jewelryObject.getBoolean("built");
                        String date = jewelryObject.getString("lastBuilt");
                        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                        Date lastBuilt = null;
                        try {
                            lastBuilt = sdf.parse(date);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }

                        JewelryModel jewelryJson = new JewelryModel(id, name, diamond, opal,
                                emerald, ruby, gain, resale, built, lastBuilt);

                        jewelryList.add(jewelryJson);
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

    public void jsonUpdateUser(UserModel currentUser, final ApiListener listener) {
        JSONObject jsonBody = new JSONObject();
        try {

            //Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create(); // your format
            String jsonUser = gson.toJson(currentUser);
            jsonBody = new JSONObject(jsonUser);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        final String mRequestBody = jsonBody.toString();

        String url = API_URL + "users/"+currentUser.getId();

        StringRequest putRequest = new StringRequest(Request.Method.PUT, url,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        // response
                        Log.d("Response", response);
                        listener.onResponse(response.equals("200"));
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
                        Log.d("Error.Response", error.toString());
                        listener.onResponse(false);
                    }
                }
        ) {
            @Override
            public String getBodyContentType() {
                return "application/json; charset=utf-8";
            }

            @Override
            public byte[] getBody() throws AuthFailureError {
                try {
                    return mRequestBody == null ? null : mRequestBody.getBytes("utf-8");
                } catch (UnsupportedEncodingException uee) {
                    VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s", mRequestBody, "utf-8");
                    return null;
                }
            }
            @Override
            protected Response<String> parseNetworkResponse(NetworkResponse response) {
                String responseString = "";
                if (response != null) {
                    responseString = String.valueOf(response.statusCode);
                }
                return Response.success(responseString, HttpHeaderParser.parseCacheHeaders(response));
            }
        };
        getRequestQueue().add(putRequest);
    }

    public void jsonCallExchange(final ApiListener listener) {
        String url = API_URL + "exchange";

        JsonArrayRequest jsonObjectRequest = new JsonArrayRequest(Request.Method.GET, url,
                null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray results) {
                try {

                    for (int i = 0; i < results.length(); i++) {

                        JSONObject exchangeObject = results.getJSONObject(i);
                        long id = exchangeObject.getLong("id");
                        boolean accepted = exchangeObject.getBoolean("accepted");
                        int diamondAsker = exchangeObject.getInt("diamondAsker");
                        int opalAsker = exchangeObject.getInt("opalAsker");
                        int emeraldAsker = exchangeObject.getInt("emeraldAsker");
                        int rubyAsker = exchangeObject.getInt("rubyAsker");
                        int diamondReceiver = exchangeObject.getInt("diamondReceiver");
                        int opalReceiver = exchangeObject.getInt("opalReceiver");
                        int emeraldReceiver = exchangeObject.getInt("emeraldReceiver");
                        int rubyReceiver = exchangeObject.getInt("rubyReceiver");

                        String date = exchangeObject.getString("createDate");
                        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                        Date createDate = null;
                        try {
                            createDate = sdf.parse(date);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }

                        UserModel nameAsker = null;
                        if (!exchangeObject.isNull("nameAsker")) {
                            JSONObject exchangeJson = exchangeObject.getJSONObject("nameAsker");
                            nameAsker = gson.fromJson(exchangeJson.toString(), UserModel.class);
                        }

                        UserModel nameReceive = null;
                        if (!exchangeObject.isNull("nameReceive")) {
                            JSONObject exchangeJson1 = exchangeObject.getJSONObject("nameReceive");
                            nameReceive = gson.fromJson(exchangeJson1.toString(), UserModel.class);
                        }

                        ExchangeModel exchangeModelJson = new ExchangeModel(id, createDate, accepted, nameReceive, opalAsker, emeraldAsker,
                                diamondAsker, rubyAsker,nameAsker, opalReceiver, emeraldReceiver, diamondReceiver, rubyReceiver);

                        exchangeList.add(exchangeModelJson);
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


    public void jsonUpdateJewelry(JewelryModel currentJewel, final ApiListener listener) {
        JSONObject jsonBody = new JSONObject();
        try {

            //Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create(); // your format
            String jsonJewelry = gson.toJson(currentJewel);
            jsonBody = new JSONObject(jsonJewelry);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        final String mRequestBody = jsonBody.toString();

        String url = API_URL + "jewelry/"+currentJewel.getId();

        StringRequest putRequest = new StringRequest(Request.Method.PUT, url,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        // response
                        Log.d("Response", response);
                        listener.onResponse(response.equals("200"));
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
                        Log.d("Error.Response", error.toString());
                        listener.onResponse(false);
                    }
                }
        ) {
            @Override
            public String getBodyContentType() {
                return "application/json; charset=utf-8";
            }

            @Override
            public byte[] getBody() throws AuthFailureError {
                try {
                    return mRequestBody == null ? null : mRequestBody.getBytes("utf-8");
                } catch (UnsupportedEncodingException uee) {
                    VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s", mRequestBody, "utf-8");
                    return null;
                }
            }
            @Override
            protected Response<String> parseNetworkResponse(NetworkResponse response) {
                String responseString = "";
                if (response != null) {
                    responseString = String.valueOf(response.statusCode);
                }
                return Response.success(responseString, HttpHeaderParser.parseCacheHeaders(response));
            }
        };
        getRequestQueue().add(putRequest);
    }

    public void jsonAddExchange(ExchangeModel exchange, final ApiListener listener) {
        JSONObject jsonBody = new JSONObject();
        try {

            Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create(); // your format
            String jsonExchange = gson.toJson(exchange);
            jsonBody = new JSONObject(jsonExchange);
            // jsonBody.put( "ReleveTache", jsonTache);


        } catch (JSONException e) {
            e.printStackTrace();
        }
        final String mRequestBody = jsonBody.toString();

        String url = API_URL + "exchange/asker/"+exchange.getAsker().getId()+"/receiver/"+
                exchange.getReceiver().getId();


        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        // response
                        Log.d("Response", response);

                        listener.onResponse(response.equals("200"));

                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
                        Log.d("Error.Response", error.toString());
                        listener.onResponse(false);
                    }
                }
        ) {
            @Override
            public String getBodyContentType() {
                return "application/json; charset=utf-8";
            }

            @Override
            public byte[] getBody() throws AuthFailureError {
                try {
                    return mRequestBody == null ? null : mRequestBody.getBytes("utf-8");
                } catch (UnsupportedEncodingException uee) {
                    VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s", mRequestBody, "utf-8");
                    return null;
                }
            }
            @Override
            protected Response<String> parseNetworkResponse(NetworkResponse response) {
                String responseString = "";
                if (response != null) {
                    responseString = String.valueOf(response.statusCode);
                }
                return Response.success(responseString, HttpHeaderParser.parseCacheHeaders(response));
            }
        };
        getRequestQueue().add(postRequest);
    }

    public void jsonUpdateExchange(ExchangeModel exchange, final ApiListener listener) {
        JSONObject jsonBody = new JSONObject();
        try {

            Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create(); // your format
            String jsonExchange = gson.toJson(exchange);
            jsonBody = new JSONObject(jsonExchange);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        final String mRequestBody = jsonBody.toString();

        String url = API_URL + "exchange/"+ exchange.getId();

        StringRequest putRequest = new StringRequest(Request.Method.PUT, url,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        // response
                        Log.d("Response", response);
                        listener.onResponse(response.equals("200"));
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
                        Log.d("Error.Response", error.toString());
                        listener.onResponse(false);
                    }
                }
        ) {
            @Override
            public String getBodyContentType() {
                return "application/json; charset=utf-8";
            }

            @Override
            public byte[] getBody() throws AuthFailureError {
                try {
                    return mRequestBody == null ? null : mRequestBody.getBytes("utf-8");
                } catch (UnsupportedEncodingException uee) {
                    VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s", mRequestBody, "utf-8");
                    return null;
                }
            }
            @Override
            protected Response<String> parseNetworkResponse(NetworkResponse response) {
                String responseString = "";
                if (response != null) {
                    responseString = String.valueOf(response.statusCode);
                }
                return Response.success(responseString, HttpHeaderParser.parseCacheHeaders(response));
            }
        };
        getRequestQueue().add(putRequest);
    }


    public ArrayList<UserModel> getUserList() {
        return userList;
    }

    public void setUserList(ArrayList<UserModel> userList) {
        this.userList = userList;
    }

    public ArrayList<JewelryModel> getJewelryList() {
        return jewelryList;
    }

    public void setJewelryList(ArrayList<JewelryModel> jewelryList) {
        this.jewelryList = jewelryList;
    }

    public ArrayList<ExchangeModel> getExchangeList() {
        return exchangeList;
    }

    public void setExchangeList(ArrayList<ExchangeModel> exchangeList) {
        this.exchangeList = exchangeList;
    }

    public UserModel getCurrentReceiver() {
        return currentReceiver;
    }

    public void setCurrentReceiver(UserModel currentReceiver) {
        this.currentReceiver = currentReceiver;
    }

    public UserModel getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(UserModel currentUser) {
        this.currentUser = currentUser;
    }

    public JewelryModel getCurrentJewel() {
        return currentJewel;
    }

    public void setCurrentJewel(JewelryModel currentJewel) {
        this.currentJewel = currentJewel;
    }
}
