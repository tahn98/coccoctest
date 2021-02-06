package com.example.coccoctest.util;

import com.example.coccoctest.model.RssDescription;

import org.simpleframework.xml.convert.Converter;
import org.simpleframework.xml.stream.InputNode;
import org.simpleframework.xml.stream.OutputNode;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DescriptionConverter implements Converter<RssDescription> {
    @Override
    public RssDescription read(InputNode node) throws Exception {
        final String IMG_SRC_REG_EX = "<img src=([^>]+)>";
        final String HTML_TAG_REG_EX = "</?[^>]+>";

        String nodeText = node.getValue();

        Pattern imageLinkPattern = Pattern.compile(IMG_SRC_REG_EX);
        Matcher matcher = imageLinkPattern.matcher(nodeText);

        String link = null;
        while (matcher.find()) {
            link = matcher.group(1);
        }

        if(link == null || link.isEmpty()){
            link = "";
        }else{
            link = link.substring(1, link.length() - 2);
        }

        String text = nodeText.replaceFirst(IMG_SRC_REG_EX, "")
                .replaceAll(HTML_TAG_REG_EX, "");

        return new RssDescription(link, text);
    }

    @Override
    public void write(OutputNode node, RssDescription value) throws Exception { }
}
