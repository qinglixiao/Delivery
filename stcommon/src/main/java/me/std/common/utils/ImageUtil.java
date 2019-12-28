package me.std.common.utils;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.annotation.RequiresApi;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

/**
 * Description:
 * 图片操作工具类
 * 目前图片压缩算法共有3种：small(缩略图)、common(清晰度较高，正常大小)、long(长图)，其中small和long的算法一样，只是long不限制长度
 * Author: huangyuan
 * Create on: 2018/10/16
 * Job number: 1800829
 * Phone: 13120112517
 * Email: huangyuan@chunyu.me
 */
public class ImageUtil {

    /**
     * 图片处理方式为imageview2时使用
     * 限定缩略图的宽最少为<Width>，高最少为<Height>，进行等比缩放，居中裁剪。
     * 转后的缩略图通常恰好是 <Width>x<Height> 的大小
     * 参见：https://developer.qiniu.com/dora/manual/1279/basic-processing-images-imageview2
     */
    private static final String IMAGE_VIEW_MODE = "1";//imageView2获取缩略图的后缀
    private static final String IMAGE_VIEW_MODE2 = "2";
    private static final String IMAGE_VIEW_MODE_HEIGHT = "/h/";
    private static final String IMAGE_VIEW_MODE_WIDTH = "/w/";
    private static final String IMAGE_VIEW_TYPE1 = "?imageView2/";


    private static final int IMAGE_HEIGHT_DEFAULT = 1024;
    private static final int IMAGE_WIDTH_DEFAULT = 768;
    private static HashMap<String, Integer> adjustHeightHistory;

    public static class ImageScaleResult {
        public String imageUri;
        public int width;
        public int height;
    }

    public interface AdjustListener {
        void onAdjust(float scale);
    }

    /**
     * use {@link #getThumb(Context, Uri, int, int)} instead
     * maxWidth 必须小于等于 maxHeight
     * http://developer.android.com/training/displaying-bitmaps/load-bitmap.html
     *
     * @param filePath  文件路径
     * @param maxWidth  宽度
     * @param maxHeight 高度
     * @return bitmap对象
     */
    @Deprecated
    public static Bitmap getThumb(String filePath, int maxWidth, int maxHeight) {
        try {
            return getThumb(new FileInputStream(new File(filePath)),
                    new FileInputStream(new File(filePath)), maxWidth, maxHeight);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获取缩略图
     */
    public static Bitmap getThumb(Context context, Uri uri, int maxWidth, int maxHeight) {
        try {
            InputStream inputStream0 = context.getContentResolver().openInputStream(uri);
            InputStream inputStream1 = context.getContentResolver().openInputStream(uri);
            return getThumb(inputStream0, inputStream1, maxWidth, maxHeight);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获取缩略图
     */
    private static Bitmap getThumb(InputStream inputStream0, InputStream inputStream1,
                                   int maxWidth, int maxHeight) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeStream(inputStream0, null, options);
        int scale = getSmallImageScale(options.outWidth, options.outHeight, maxWidth, maxHeight);

        options = new BitmapFactory.Options();
        options.inSampleSize = scale;
        return BitmapFactory.decodeStream(inputStream1, null, options);
    }

    /**
     * 将图片转为byte[]
     */
    public static byte[] bitmapToBytes(Bitmap bm) {
        ByteArrayOutputStream ops = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.JPEG, 80, ops);
        bm.recycle();
        byte[] bytes = ops.toByteArray();
        try {
            ops.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bytes;
    }

    /**
     * 获取图片，无压缩
     */
    private static Bitmap getBitmap(Context context, Uri uri, int scale) {
        uri = Uri.parse(addFileScheme(uri.toString()));
        InputStream in;
        try {
            ContentResolver contentResolver = context.getContentResolver();
            in = contentResolver.openInputStream(uri);
            if (in == null) {
                return null;
            }
            //Decode image size
            BitmapFactory.Options o = new BitmapFactory.Options();
            o.inJustDecodeBounds = true;

            BitmapFactory.decodeStream(in, null, o);
            in.close();

            BitmapFactory.Options o2 = new BitmapFactory.Options();
            o2.inSampleSize = scale;
            o2.inPreferredConfig = Bitmap.Config.RGB_565;//降低图片从ARGB888到RGB565
            in = contentResolver.openInputStream(uri);
            if (in == null) {
                return null;
            }
            Bitmap b = BitmapFactory.decodeStream(in, null, o2);

            in.close();

            return b;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取压缩图片，应用中最常用的压缩方法，getImageScale()获取压缩比
     */
    public static Bitmap getCommonBitmap(Context context, Uri uri) {
        return getBitmap(context, uri, getImageScale(context, uri, IMAGE_WIDTH_DEFAULT, IMAGE_HEIGHT_DEFAULT, false));
    }


    /**
     * 获取图片的压缩比
     *
     * @param needWidth  图片所需宽度
     * @param needHeight 图片所需高度
     * @return 压缩比
     */
    public static int getImageScale(
            Context context, Uri localUri,
            int needWidth, int needHeight, boolean isLong) {
        int scale = 1;
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        try {
            BitmapFactory.decodeStream(
                    context.getContentResolver().openInputStream(localUri), null, options);
            if (isLong) {
                scale = getLongImageScale(options.outWidth, needWidth);
            } else {
                scale = getCommonImageScale(options.outWidth, options.outHeight, needWidth, needHeight);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return scale;
        }
        return scale;

    }

    /**
     * 压缩后清晰度较高的压缩算法，最常用
     * 一般几Mb的图片用这个尺寸压缩，再加后续的80%质量压缩，最终大小一般在几十kb~几百kb
     *
     * @param outWidth  图片原始宽度
     * @param outHeight 图片原始高度
     * @param maxWidth  图片允许最大宽度
     * @param maxHeight 图片允许最大高度
     * @return 压缩比
     */
    private static int getCommonImageScale(int outWidth, int outHeight, int maxWidth, int maxHeight) {
        int scale = 1;
        if (outHeight > maxHeight || outWidth > maxWidth) {
            int maxSize = maxHeight > maxWidth ? maxHeight : maxWidth;
            scale = (int) Math.pow(2, (int) Math.round(Math.log(maxSize /
                    (double) Math.max(outHeight, outWidth)) / Math.log(0.5)));
        }
        return scale;
    }

    /**
     * 压缩后图片不太清晰的压缩算法
     * 一般获取缩略图时用
     *
     * @param outWidth  图片原始宽度
     * @param outHeight 图片原始高度
     * @param maxWidth  图片允许最大宽度
     * @param maxHeight 图片允许最大高度
     * @return 压缩比
     */
    private static int getSmallImageScale(int outWidth, int outHeight, int maxWidth, int maxHeight) {
        int scale = 1;
        int width, height;
        //保证高>宽
        if (outWidth <= outHeight) {
            width = outWidth;
            height = outHeight;
        } else {
            width = outHeight;
            height = outWidth;
        }
        while (width / scale > maxWidth || height / scale > maxHeight) {
            scale *= 2;
        }
        return scale;
    }

    /**
     * 长图压缩算法(只限制宽度，不关注高度)，社区发图时用
     *
     * @param outWidth 图片原始宽度
     * @param maxWidth 图片允许最大宽度
     * @return 压缩比
     */
    private static int getLongImageScale(int outWidth, int maxWidth) {
        int scale = 1;
        while (outWidth / scale > maxWidth) {
            scale *= 2;
        }
        return scale;
    }

    /**
     * 图片处理方法，对图片进行特定处理
     *
     * @param context   context
     * @param localPath 本地文件路径
     * @param scale     压缩比
     * @return 处理后的图片信息
     */
    public static ImageScaleResult scaleImageToWithSize(
            Context context, Object localPath, int scale, boolean isLong) {

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        try {
            if (localPath instanceof Uri) {
                BitmapFactory.decodeStream(
                        context.getContentResolver().openInputStream((Uri) localPath), null, options);
            } else {
                BitmapFactory.decodeStream(
                        new FileInputStream(new File((String) localPath)), null, options);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }

        int outWidth = options.outWidth;
        int outHeight = options.outHeight;
        options = new BitmapFactory.Options();
        options.inSampleSize = scale;
        //降低图片质量从ARGB888到RGB565
        options.inPreferredConfig = Bitmap.Config.RGB_565;
        try {
            Bitmap bmp;
            if (localPath instanceof Uri) {
                bmp = BitmapFactory.decodeStream(
                        context.getContentResolver().openInputStream((Uri) localPath), null, options);
            } else {
                bmp = BitmapFactory.decodeStream(
                        new FileInputStream(new File((String) localPath)), null, options);
            }

            File myCaptureFile = FileUtil.getImageCacheFile();
            BufferedOutputStream bos =
                    new BufferedOutputStream(new FileOutputStream(myCaptureFile));
            bmp.compress(Bitmap.CompressFormat.JPEG, 80, bos);
            bos.flush();
            bos.close();

            ImageScaleResult result = new ImageScaleResult();
            result.imageUri = myCaptureFile.getAbsolutePath();
            //如果是长图，不对尺寸做特殊处理
            if (isLong) {
                result.width = bmp.getWidth();
                result.height = bmp.getHeight();
            } else {
                result.width = Math.min(outHeight, outWidth) / scale;
                result.height = Math.max(outHeight, outWidth) / scale;
            }
            return result;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static String picasaImageUri2Path(Context context, Uri imageUri) {
        try {
            InputStream ist = context.getContentResolver().openInputStream(imageUri);
            Bitmap bitmap = BitmapFactory.decodeStream(ist);
            return FileUtil.savePic(bitmap);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 添加读取图片用scheme
     * 问答页上传图片时，选择的图片path本身不带file://，上传时读取图片需要这个，否则读不了图片，上传失败
     *
     * @param path 问答页要上传的图片本地地址
     * @return 读取图片用的图片本地地址
     */
    private static String addFileScheme(String path) {
        String fileScheme = "file://";
        if (!path.startsWith(fileScheme)) {
            path = fileScheme + path;
        }
        return path;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public static String imageUri2Path(Context context, Uri imageUri) {
        if (imageUri == null) {
            return null;
        }

        if (!imageUri.toString().startsWith("content://")) {
            return imageUri.getPath();
        }

        if (imageUri.toString().startsWith(
                "content://com.google.android.gallery3d")) {
            return picasaImageUri2Path(context, imageUri);
        }

        //增加4.4及以上系统，多种情况下获取图片地址的方法，解决获取高系统版本内置存储卡图片闪退的问题
        if (!TextUtils.isEmpty(FileUtil.getPath(context, imageUri))) {
            return FileUtil.getPath(context, imageUri);
        }
        //以下的方法只针对Media和非Media的情况做了处理，不够完善，仍然保留
        Cursor cursor;
        if (isMediaDocument(imageUri)) {
            final String docId = DocumentsContract.getDocumentId(imageUri);
            final String[] split = docId.split(":");
            final String type = split[0];
            if (!TextUtils.isEmpty(type) && type.equals("image")) {
                imageUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
            }

            String selection = "_id=?";
            String[] selectionArgs = new String[]{split[1]};
            String[] proj = {MediaStore.Images.Media.DATA};

            cursor = context.getContentResolver()
                    .query(imageUri, proj, selection, selectionArgs, null);

        } else {
            String[] proj = {MediaStore.Images.Media.DATA};
            cursor = context.getContentResolver().query(imageUri, proj, null, null, null);
        }
        if (cursor == null) {
            return null;
        }

        try {
            int idx = cursor
                    .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            if (cursor.moveToFirst()) {
                return cursor.getString(idx);
            }
        } catch (IllegalArgumentException e) {
            cursor.close();
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Get the value of the data column for this Uri. This is useful for
     * MediaStore Uris, and other file-based ContentProviders.
     *
     * @param context       The context.
     * @param uri           The Uri to query.
     * @param selection     (Optional) Filter used in the query.
     * @param selectionArgs (Optional) Selection arguments used in the query.
     * @return The value of the _data column, which is typically a file path.
     */
    public static String getDataColumn(Context context, Uri uri, String selection,
                                       String[] selectionArgs) {

        Cursor cursor = null;
        final String column = "_data";
        final String[] projection = {
                column
        };

        try {
            cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs,
                    null);
            if (cursor != null && cursor.moveToFirst()) {
                final int index = cursor.getColumnIndexOrThrow(column);
                return cursor.getString(index);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close();
            }

        }
        return null;
    }


    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is ExternalStorageProvider.
     */
    public static boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is DownloadsProvider.
     */
    public static boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is MediaProvider.
     */
    public static boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is Google Photos.
     */
    public static boolean isGooglePhotosUri(Uri uri) {
        return "com.google.android.apps.photos.content".equals(uri.getAuthority());
    }


    public static void adjustHeight(final ImageView iv) {
        Drawable drawable = iv.getDrawable();
        if (drawable == null) {
            return;
        }
        adjustHeight(iv, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
    }

    public static void adjustHeight(final View view, final AdjustListener listener) {
        ViewTreeObserver observer = view.getViewTreeObserver();
        final Drawable drawable = view.getBackground();
        observer.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                view.getViewTreeObserver().removeGlobalOnLayoutListener(this);

                float scale = (float) view.getWidth() / drawable.getIntrinsicWidth();
                int height = (int) (scale * drawable.getIntrinsicHeight());
                ViewGroup.LayoutParams params = view.getLayoutParams();
                params.height = height;
                view.setLayoutParams(params);

                listener.onAdjust(scale);
            }
        });
    }

    public static void adjustHeight(final View view, final int intrinsicWidth,
                                    final int intrinsicHeight) {
        if (intrinsicWidth == 0 || intrinsicHeight == 0) {
            return;
        }

        ViewTreeObserver observer = view.getViewTreeObserver();
        observer.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                view.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                int height = view.getWidth() * intrinsicHeight / intrinsicWidth;
                if (height == 0) {
                    return;
                }
                ViewGroup.LayoutParams params = view.getLayoutParams();
                params.height = height + 2; // 计算出来的height不知为什么差一点点，补偿2像素
                view.setLayoutParams(params);
            }
        });
    }


    /**
     * 在ListView 滚动后，ViewTreeObserver，不会被触发，导致没有办法调整高度尺寸。
     * 所以，用了一个缓存高度的策略。
     *
     * @param view            控件
     * @param intrinsicWidth  固有宽度
     * @param intrinsicHeight 固有高度
     * @param tag：唯一
     */
    public static void adjustHeight(final View view, final int intrinsicWidth,
                                    final int intrinsicHeight, final String tag) {
        if (intrinsicWidth == 0 || intrinsicHeight == 0) {
            return;
        }
        if (adjustHeightHistory == null) {
            adjustHeightHistory = new HashMap<>();
        }
        final String compoundTag = tag + intrinsicWidth + "" + intrinsicHeight;
        if (adjustHeightHistory.get(compoundTag) != null) {
            ViewGroup.LayoutParams params = view.getLayoutParams();
            params.height = adjustHeightHistory.get(compoundTag);
            view.setLayoutParams(params);
            return;
        }

        ViewTreeObserver observer = view.getViewTreeObserver();
        observer.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                view.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                int height = view.getWidth() * intrinsicHeight / intrinsicWidth;
                ViewGroup.LayoutParams params = view.getLayoutParams();
                params.height = height + 2; // 计算出来的height不知为什么差一点点，补偿2像素
                view.setLayoutParams(params);
                adjustHeightHistory.put(compoundTag, params.height);

            }
        });
    }


    /**
     * 将Drawable转换成Bitmap
     */
    public static Bitmap drawableToBitmap(Drawable drawable) {
        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable) drawable).getBitmap();
        }

        Bitmap bitmap;
        int width = drawable.getIntrinsicWidth();
        int height = drawable.getIntrinsicHeight();
        if (width > 0 && height > 0) {
            bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(bitmap);
            drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
            drawable.draw(canvas);
        } else {
            bitmap = null;
        }

        return bitmap;
    }

    /**
     * 打印url的log
     *
     * @param tag tag
     * @param tip 提示内容
     * @param url 图片url
     */
    public static void showImageUrlLog(String tag, String tip, String url) {
        if (TextUtils.isEmpty(tip)) {
            tip = "图片URL : ";
        }
        LogUtil.e(tag, tip + url);
    }

    /**
     * 使用七牛API,对图片url做处理，获取展示大图时的压缩后的图片url（等比压缩）
     *
     * @param url       图片原始url
     * @param maxWidth  想要的最大宽，单位px
     * @param maxHeight 想要的最大高，单位px
     * @return 处理后的url
     */
    public static String getFullScreenThumbnailUrl(String url, int maxWidth, int maxHeight) {
        if (TextUtils.isEmpty(url) || maxWidth <= 0 || maxHeight <= 0) {
            return url;
        }

        if (url.contains(IMAGE_VIEW_TYPE1)) {
            //如果用的是imageView2做处理的，但是没有限制宽和高，则加一下高宽设置参数
            if (!url.contains(IMAGE_VIEW_MODE_WIDTH) && !url.contains(IMAGE_VIEW_MODE_HEIGHT)) {
                url = url + IMAGE_VIEW_MODE2 + IMAGE_VIEW_MODE_WIDTH + maxWidth
                        + IMAGE_VIEW_MODE_HEIGHT + maxHeight;
            }
        } else {
            //如果没有url做过处理，则直接加后缀处理。使用后缀1(imageView2)处理
            if (!url.contains("?")) {
                url = url + IMAGE_VIEW_TYPE1 + IMAGE_VIEW_MODE2 + IMAGE_VIEW_MODE_WIDTH +
                        maxWidth + IMAGE_VIEW_MODE_HEIGHT + maxHeight;
            }
        }

        return url;
    }

}

