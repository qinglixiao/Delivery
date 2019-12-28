package me.std.common.third.glide;

import android.text.TextUtils;

import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.ModelCache;
import com.bumptech.glide.load.model.ModelLoader;
import com.bumptech.glide.load.model.ModelLoaderFactory;
import com.bumptech.glide.load.model.MultiModelLoaderFactory;
import com.bumptech.glide.load.model.stream.BaseGlideUrlLoader;

import java.io.InputStream;

public class StringBaseGlideUrlLoader extends BaseGlideUrlLoader<String> {

    public StringBaseGlideUrlLoader(ModelLoader<GlideUrl, InputStream> concreteLoader, ModelCache<String, GlideUrl> modelCache) {
        super(concreteLoader, modelCache);
    }

    @Override
    protected String getUrl(String model, int width, int height, Options options) {
        if (TextUtils.isEmpty(model)) {
            return "";
        }

        try {
            return processUri(model);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    @Override
    public boolean handles(String s) {
        return true;
    }

    public static class Factory implements ModelLoaderFactory<String, InputStream> {
        @Override
        public ModelLoader<String, InputStream> build(MultiModelLoaderFactory multiFactory) {
            return new StringBaseGlideUrlLoader(multiFactory.build(GlideUrl.class, InputStream.class), new ModelCache<String, GlideUrl>());
        }

        @Override
        public void teardown() {
        }
    }

    private String processUri(String origin) {
        return origin;
    }
}
