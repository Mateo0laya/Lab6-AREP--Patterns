package com.app.lab6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class RRInvoker {

    private static final String USER_AGENT = "Mozilla/5.0";
    private static final String GET_URL = "http://logservice";
    private static final String PORT = ":35000";
    private static String SERVER = null;

    public static String invoke(String msg) throws IOException {

        setServer();

        String urlMsg = msg.replaceAll(" ", "%20");
        String url = GET_URL + SERVER + PORT + "/logservice?log=" + urlMsg;

        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", USER_AGENT);

        // The following invocation perform the connection implicitly before getting the
        // code
        int responseCode = con.getResponseCode();
        System.out.println("GET Response Code :: " + responseCode);

        StringBuffer response = new StringBuffer();

        if (responseCode == HttpURLConnection.HTTP_OK) { // success
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    con.getInputStream()));
            String inputLine;

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // print result
            System.out.println(response.toString());
        } else {
            System.out.println("GET request not worked");
        }
        System.out.println("GET DONE");
        return response.toString();
    }

    private static void setServer() {
        if (SERVER == null) {
            SERVER = "1";
        } else if (SERVER.equals("1")) {
            SERVER = "2";
        } else if (SERVER.equals("2")) {
            SERVER = "3";
        } else if (SERVER.equals("3")) {
            SERVER = "1";
        }
    }

}
