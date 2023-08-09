package com.inn.product.serviceImpl;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "url")
public class UrlEntry {
    private String loc;

    @XmlElement
    public String getLoc() {
        return loc;
    }

    public void setLoc(String loc) {
        this.loc = loc;
    }

    // Add other fields like 'changefreq' and 'priority' if needed
}

