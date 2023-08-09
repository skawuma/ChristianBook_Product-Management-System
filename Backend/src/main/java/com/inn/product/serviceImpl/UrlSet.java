package com.inn.product.serviceImpl;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "urlset", namespace = "http://www.sitemaps.org/schemas/sitemap/0.9")
public class UrlSet {

    private List<UrlEntry> urls;

    @XmlElement(name = "url")
    public List<UrlEntry> getUrls() {
        return urls;
    }

    public void setUrls(List<UrlEntry> urls) {
        this.urls = urls;
    }
}

