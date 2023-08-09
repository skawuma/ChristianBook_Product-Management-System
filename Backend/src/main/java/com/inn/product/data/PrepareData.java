package com.inn.product.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class PrepareData implements CommandLineRunner {

    @Autowired
    ProcessEachUrlData processEachUrlData;


    @Override
    public void run(String... args) throws Exception {
        String urlStr = "https://www.christianbook.com/sitemap4.xml";
        try {
            System.out.println("==========1");
            String xmlContent = fetchXmlFromUrl(urlStr);
            System.out.println("==========2");
            List<String> urlsList = getData(xmlContent);
            System.out.println("==========3");
            Map<String, String> urlMap = createURLMap(urlsList);
            System.out.println("==========4");
            Data.setUrlMap(urlMap);
            processEachUrlData.processEachUrl();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public static String fetchXmlFromUrl(String urlStr) throws IOException {
        URL url = new URL(urlStr);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        int responseCode = conn.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            return response.toString();
        } else {
            System.out.println("Failed to fetch XML. Response code: " + responseCode);
            return null;
        }
    }

    public static List<String> getData(String xmlContent) throws Exception{
        List<String> urlList = new ArrayList<>();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        InputStream inputStream = new ByteArrayInputStream(xmlContent.getBytes());
        Document document = builder.parse(inputStream);

        NodeList locElements = document.getElementsByTagName("loc");
        for (int i = 0; i < locElements.getLength(); i++) {
            Element locElement = (Element) locElements.item(i);
            String locValue = locElement.getTextContent();
            urlList.add(locValue);
        }
        return urlList;
    }

    public static Map<String, String> createURLMap(List<String> urls) {
        Map<String, String> urlMap = new HashMap<>();

        for (String url : urls) {
            String lastValue = extractLastInteger(url);
            urlMap.put(lastValue, url);
        }

        return urlMap;
    }

    public static String extractLastInteger(String url) {
        String[] segments = url.split("/");
        String lastSegment = segments[segments.length - 1];

        try {
            return lastSegment;
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
            return null; // Return a default value or handle the error accordingly
        }
    }
}
