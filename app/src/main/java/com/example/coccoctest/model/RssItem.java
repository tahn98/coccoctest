package com.example.coccoctest.model;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.example.coccoctest.util.DescriptionConverter;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;
import org.simpleframework.xml.convert.Convert;

@Root(name = "item", strict = false)
public class RssItem {
    @Element
    private String title;

    @Element(name = "description", required = false)
    @Convert(DescriptionConverter.class)
    private RssDescription description;

    @Element
    private String pubDate;

    @Element
    private String link;

    public String getTitle() {
        return title;
    }

    public RssDescription getDescription() {
        return description;
    }

    public String getPubDate() {
        return pubDate;
    }

    public String getLink() {
        return link;
    }

    @BindingAdapter(value = {"imageUrl", "error"}, requireAll = false)
    public static void loadImage(ImageView view, String url, Drawable error) {
        if (url != null && !url.isEmpty()) {
            Glide.with(view.getContext())
                    .load(url)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(view);
        }
    }
}
