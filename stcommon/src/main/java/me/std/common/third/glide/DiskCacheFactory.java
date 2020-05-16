package me.std.common.third.glide;

import androidx.annotation.Nullable;

import com.bumptech.glide.load.engine.cache.DiskCache;
import com.bumptech.glide.load.engine.cache.DiskLruCacheWrapper;

import java.io.File;

class DiskCacheFactory implements DiskCache.Factory {
    private File mCacheDir;
    private int mSize;

    public DiskCacheFactory(File cacheDir, int size) {
        mCacheDir = cacheDir;
        mSize = size;
    }

    @Nullable
    @Override
    public DiskCache build() {
        return DiskLruCacheWrapper.get(mCacheDir, mSize);
    }
}
