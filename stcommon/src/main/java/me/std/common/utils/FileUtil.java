package me.std.common.utils;

import android.content.ContentUris;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.annotation.RequiresApi;
import android.text.TextUtils;

import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;

public class FileUtil {

    /**
     * copy file
     *
     * @param src  source file
     * @param dest target file
     * @throws IOException
     */
    public static void copyFile(File src, File dest) throws IOException {
        FileChannel inChannel = null;
        FileChannel outChannel = null;
        try {
            if (!dest.exists()) {
                dest.createNewFile();
            }
            inChannel = new FileInputStream(src).getChannel();
            outChannel = new FileOutputStream(dest).getChannel();
            inChannel.transferTo(0, inChannel.size(), outChannel);
        } finally {
            if (inChannel != null) {
                inChannel.close();
            }
            if (outChannel != null) {
                outChannel.close();
            }
        }
    }

    /**
     * 描 述 ：级联删除目录下的所有文件，如果为文件则直接删除
     *
     * @param file 目录/文件
     * @param self 是否删除到传入的目录级别（true: 如传入 /sdcard/delivety/sp,则会将sp下的所有内容，及sp目录一并删除）
     * @version : 1.0
     */
    public static void delete(String file, boolean self) {
        if (TextUtils.isEmpty(file)) {
            return;
        }
        File directory = new File(file);
        if (!directory.exists()) {
            return;
        }
        if (directory.isFile()) {
            directory.delete();
            return;
        }
        File[] children = directory.listFiles();
        for (int i = 0; i < children.length; i++) {
            if (children[i].isDirectory()) {
                delete(children[i].getPath(), self);
            }
            children[i].delete();
        }
        if (self) {
            directory.delete();
        }
    }

    /**
     * 描 述 ：级联删除目录下的所有文件，如果为文件则直接删除
     *
     * @version : 1.0
     */
    public static void delete(File file, boolean self) {
        if (file != null) {
            delete(file.getAbsolutePath(), self);
        }
    }

    public static void delete(String file) {
        delete(file, true);
    }

    public static File newFile(String name) {
        return createCacheFile(name);
    }

    public static File newImageCacheFile(String name) {
        File file = getImageCacheFile();
        if (file != null) {
            File i = new File(file.getAbsolutePath() + File.separator + UUIDUtil.generate(name) + ".png");
            if (!i.exists()) {
                try {
                    i.createNewFile();
                } catch (IOException e) {
                    return null;
                }
            }
            return i;
        }
        return null;
    }

    /**
     * 图片缓存目录
     * /storage/emulated/0/Android/data/com.std.framework/files/Pictures
     *
     * @return
     */
    public static File getImageCacheFile() {
        File file = AppContextUtil.getAppContext().getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        return file;
    }

    /**
     * 文档缓存目录
     * /storage/emulated/0/Android/data/com.std.framework/files/Documents
     *
     * @return
     */
    public static File getDocumentsCacheFile() {
        return AppContextUtil.getAppContext().getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS);
    }

    /**
     * 音频缓存目录
     * /storage/emulated/0/Android/data/com.std.framework/files/Music
     *
     * @return
     */
    public static File getMusicCacheFile() {
        return AppContextUtil.getAppContext().getExternalFilesDir(Environment.DIRECTORY_MUSIC);
    }

    /**
     * 日志缓存目录
     * /storage/emulated/0/Android/data/com.std.framework/files/log
     *
     * @return
     */
    public static File getLogCacheFile() {
        return createCacheDir("log");
    }

    /**
     * 地址：
     * /storage/emulated/0/Android/data/com.std.framework/files/${name}
     *
     * @param name xx/xx 目录
     * @return
     */
    public static File createCacheDir(String name) {
        if (Environment.isExternalStorageEmulated()) {
            File file = new File(AppContextUtil.getAppContext().getExternalFilesDir(null).getAbsolutePath() + File.separator + name);
            if (!file.exists()) {
                file.mkdirs();
            }
            return file;
        }
        return null;
    }

    /**
     * 地址：
     * /storage/emulated/0/Android/data/com.std.framework/files/${name}
     *
     * @param name xx/xx 文件
     * @return
     */
    public static File createCacheFile(String name) {
        if (Environment.isExternalStorageEmulated()) {
            File file = new File(AppContextUtil.getAppContext().getExternalFilesDir(null).getAbsolutePath() + File.separator + name);
            if (!file.exists()) {
                try {
                    file.createNewFile();
                } catch (IOException e) {
                    return null;
                }
            }
            return file;
        }
        return null;
    }

    /**
     * 保存图片到sdcard中
     */
    public static String savePic(Bitmap bitmap) {
        File file = newImageCacheFile(null);
        return savePic(bitmap, file);
    }

    public static String savePic(Bitmap bitmap, String filename) {
        File file = newImageCacheFile(filename);
        return savePic(bitmap, file);
    }

    public static String savePic(Bitmap bitmap, File file) {
        if (bitmap == null) {
            return null;
        }
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.PNG, 90, fos);
            fos.flush();
            return file.getAbsolutePath();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            closeSafely(fos);
        }
        return null;
    }


    public static String getStringFromFile(File file) {
        if (file == null || !file.exists()) {
            return "";
        }

        String str = "";
        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream(file);
            int size = inputStream.available();
            byte[] bytes = new byte[size];
            if (inputStream.read(bytes) == size) {
                str = new String(bytes);
            }
        } catch (IOException ignored) {
        } finally {
            closeSafely(inputStream);
        }
        return str;
    }

    public static void saveStringToFile(File file, String content) {
        if (file == null) {
            return;
        }
        if (TextUtils.isEmpty(content)) {
            if (file.exists()) {
                file.delete();
            }
            return;
        }
        FileOutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(file);
            outputStream.write(content.getBytes());
        } catch (IOException ignored) {
            LogUtil.v("CY_FILE", "saveStringToFile", ignored);

        } finally {
            closeSafely(outputStream);
        }
    }

    public static void appendStringToFile(File file, String content) {
        if (file == null) {
            return;
        }
        if (TextUtils.isEmpty(content)) {
            return;
        }
        FileOutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(file, true);
            outputStream.write(content.getBytes());
        } catch (IOException ignored) {
            LogUtil.v("CY_FILE", "saveStringToFile", ignored);

        } finally {
            closeSafely(outputStream);
        }
    }


    public static boolean removeFile(File file) {
        if (file == null || !file.exists()) {
            return true;
        }

        return !file.isDirectory() && file.delete();
    }

    public static void renameFile(File srcFile, File desFile) {
        if (srcFile == null || !srcFile.exists()) {
            return;
        }
        srcFile.renameTo(desFile);
    }

    public static void deleteDirectory(File dir) {
        try {
            if (dir != null && dir.exists() && dir.isDirectory()) {
                File[] files = dir.listFiles();
                if (files != null) {
                    for (File file : files) {
                        if (file.isFile()) {
                            file.delete();
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getFileMD5(File file) {
        if (file == null || !file.exists()) {
            return null;
        }

        String value = null;
        FileInputStream in = null;
        try {
            in = new FileInputStream(file);
            MappedByteBuffer byteBuffer = in.getChannel().map(FileChannel.MapMode.READ_ONLY, 0, file.length());
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(byteBuffer);
            BigInteger bi = new BigInteger(1, md5.digest());
            value = bi.toString(16);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeSafely(in);
        }
        return value;
    }

    /**
     * 4.4及以上版本获取图片路径的方法，详见http://blog.csdn.net/cq121237785/article/details/45720239
     */
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public static String getPath(final Context context, final Uri uri) {
        final boolean isKitKat = Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;
        // DocumentProvider
        if (uri == null) {
            return null;
        }

        if (isKitKat && DocumentsContract.isDocumentUri(context, uri)) {
            // ExternalStorageProvider
            if (ImageUtil.isExternalStorageDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                if ("primary".equalsIgnoreCase(type)) {
                    return Environment.getExternalStorageDirectory() + "/" + split[1];
                }
            }
            // DownloadsProvider
            else if (ImageUtil.isDownloadsDocument(uri)) {

                final String id = DocumentsContract.getDocumentId(uri);
                final Uri contentUri = ContentUris.withAppendedId(
                        Uri.parse("content://downloads/public_downloads"), Long.valueOf(id));

                return ImageUtil.getDataColumn(context, contentUri, null, null);
            }
            // MediaProvider
            else if (ImageUtil.isMediaDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                Uri contentUri = null;
                if ("image".equals(type)) {
                    contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                } else if ("video".equals(type)) {
                    contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                } else if ("audio".equals(type)) {
                    contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                }

                final String selection = "_id=?";
                final String[] selectionArgs = new String[]{
                        split[1]
                };

                return ImageUtil.getDataColumn(context, contentUri, selection, selectionArgs);
            }
        }
        // MediaStore (and general)
        else if ("content".equalsIgnoreCase(uri.getScheme())) {

            // Return the remote address
            if (ImageUtil.isGooglePhotosUri(uri)) {
                return uri.getLastPathSegment();
            }

            return ImageUtil.getDataColumn(context, uri, null, null);
        }
        // File
        else if ("file".equalsIgnoreCase(uri.getScheme())) {
            return uri.getPath();
        }

        return null;
    }

    /**
     * 安全关闭
     */
    public static void closeSafely(Closeable closeable) {
        try {
            if (closeable != null) {
                closeable.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
