package com.inn.product.data;

import com.inn.product.entity.Products;
import com.inn.product.serviceImpl.ProductServiceImpl;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import java.util.HashSet;
import java.util.Set;

public class ProcessData {

	private final RestTemplate restTemplate;

	@Autowired
	public ProcessData(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	public Products getData(String url) {
		String html = fetchDataFromUrl(url);
		Set<String> authorNames = extractAuthorNames(html);
		Products products = new Products();
		products.setAuthos(authorNames);
		products.setPrice(extractPrice(html));
		products.setTitla(extractTitle(html));
		return products;
	}

    public String fetchDataFromUrl(String url) {
        // Perform the HTTP GET request using RestTemplate
        String response = restTemplate.getForObject(url, String.class);
        return response;
    }

//	public static Products extractProductDetails(String html) {
//		Document doc = Jsoup.parse(html);
//
//		// Extract author names
//		Elements authorElements = doc.select("div.CBD-ProductDetailAuthor a");
//		Set<String> authorNames = new HashSet<>();
//		for (Element authorElement : authorElements) {
//			authorNames.add(authorElement.text());
//		}
//
//		// Extract title
//		Element titleElement = doc.selectFirst("h1.CBD-ProductDetailTitle");
//		String title = (titleElement != null) ? titleElement.text() : null;
//
//		// Extract price
//		Element priceElement = doc.selectFirst("span.CBD-ProductDetailActionPrice span");
//		String price = (priceElement != null) ? priceElement.text() : null;
//		Products products = new Products();
//		products.setAuthos(authorNames);
//		products.setPrice(price);
//		products.setTitla(title);
//		return products;
//	}

    public static Set<String> extractAuthorNames(String html) {
		Document doc = Jsoup.parse(html);
		Elements authorElements = doc.select("div.CBD-ProductDetailAuthor a");

//		String[] authorNames = new String[authorElements.size()];
		Set<String> authorNames = new HashSet<>();
		for (int i = 0; i < authorElements.size(); i++) {
			Element authorElement = authorElements.get(i);
			authorNames.add(authorElement.text());
		}

		return authorNames;
	}

	public static String extractTitle(String html) {
		Document doc = Jsoup.parse(html);
		Element titleElement = doc.selectFirst("h1.CBD-ProductDetailTitle");

		if (titleElement != null) {
			return titleElement.text();
		} else {
			return null; // Title element not found or HTML structure has changed
		}
	}

	public static String extractPrice(String html) {
		Document doc = Jsoup.parse(html);
		Element priceElement = doc.selectFirst("span.CBD-ProductDetailActionPrice span");

		if (priceElement != null) {
			return priceElement.text();
		} else {
			return null; // Price element not found or HTML structure has changed
		}
	}

    }
