package com.app.lab6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class RRInvoker {

    private static final String USER_AGENT = "Mozilla/5.0";
    private static final String GET_URL = "http://ec2-18-209-160-174.compute-1.amazonaws.com:";
    private static String port = null;

    public static String invoke(String msg) throws IOException {

        setPort();

        String urlMsg = msg.replaceAll(" ", "%20");
        String url = GET_URL + port + "/logservice?log=" + urlMsg;

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

    private static void setPort() {
        if (port == null) {
            port = "35001";
        } else if (port == "35001") {
            port = "35002";
        } else if (port == "35002") {
            port = "35003";
        } else {
            port = "35001";
        }
    }

}
