package com.example.coccoctest.model;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

@Root(name = "channel", strict = false)
public class RssChannel {
    @Element
    private String title;

    @Element
    private String description;

    @Element
    private String link;

    @ElementList(inline = true, required = false)
    public List<RssItem> item;

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getLink() {
        return link;
    }

    public List<RssItem> getItem() {
        return item;
    }
}
