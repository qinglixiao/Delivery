package me.std.common.third.glide;

import android.content.Context;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.Registry;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.cache.DiskCache;
import com.bumptech.glide.load.engine.cache.LruResourceCache;
import com.bumptech.glide.load.engine.cache.MemorySizeCalculator;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.module.AppGlideModule;
import com.bumptech.glide.request.RequestOptions;

import java.io.InputStream;

import me.std.common.third.glide.okhttp.OkHttpUrlLoader;
import me.std.common.utils.FileUtil;

@GlideModule
public final class STGlideModule extends AppGlideModule {

    /**
     * 通过GlideBuilder设置默认的结构(DecodeFormat, BitmapPool ,ArrayPool, MemoryCache等等).
     *
     * @param context context
     * @param builder builder for setting default structural classes for Glide to use.
     */
    @Override
    public void applyOptions(Context context, GlideBuilder builder) {
        int diskCacheSize = DiskCache.Factory.DEFAULT_DISK_CACHE_SIZE;
        MemorySizeCalculator calculator = new MemorySizeCalculator.Builder(context)
                .setMemoryCacheScreens(2)
                .build();

        builder.setDefaultRequestOptions(
                new RequestOptions()
                        .format(DecodeFormat.PREFER_RGB_565))
                .setMemoryCache(new LruResourceCache(calculator.getMemoryCacheSize()))
                .setDiskCache(new DiskCacheFactory(FileUtil.createCacheDir("Glide"), diskCacheSize));
    }

    /**
     * 为App注册一个自定义的String类型的BaseGlideUrlLoader
     *
     * @param context  context
     * @param registry Manages component registration.
     */
    @Override
    public void registerComponents(Context context, Glide glide, Registry registry) {
        registry.replace(GlideUrl.class, InputStream.class, new OkHttpUrlLoader.Factory());
        registry.append(String.class, InputStream.class, new StringBaseGlideUrlLoader.Factory());
    }

    /**
     * 清单解析的开启
     * <p>
     * 这里不开启，避免添加相同的modules两次
     *
     * @return 是否开启解析清单Module
     */
    @Override
    public boolean isManifestParsingEnabled() {
        return false;
    }
}
