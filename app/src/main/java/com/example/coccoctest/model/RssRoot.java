package com.example.coccoctest.model;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "rss", strict = false)
public class RssRoot {
    @Element
    private RssChannel channel;

    public RssChannel getChannel() {
        return channel;
    }
}
