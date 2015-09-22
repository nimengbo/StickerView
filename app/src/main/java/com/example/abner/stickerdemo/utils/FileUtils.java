package com.example.abner.stickerdemo.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Environment;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Abner on 15/9/22.
 * QQ 230877476
 * Email nimengbo@gmail.com
 * github https://github.com/nimengbo
 */
public class FileUtils {

    private static FileUtils instance = null;

    private static Context mContext;

    private static final String APP_DIR = "Abner";

    private static final String TEMP_DIR = "Abner/.TEMP";

    public static FileUtils getInstance(Context context) {
        if (instance == null) {
            synchronized (FileUtils.class) {
                if (instance == null) {
                    mContext = context.getApplicationContext();
                    instance = new FileUtils();
                }
            }
        }
        return instance;
    }

    /**
     * 保存图像到本地
     *
     * @param bm
     * @return
     */
    public static String saveBitmapToLocal(Bitmap bm, Context context) {
        String path = null;
        try {
            File file = FileUtils.getInstance(context).createTempFile("IMG_", ".jpg");
            FileOutputStream fos = new FileOutputStream(file);
            bm.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();
            path = file.getAbsolutePath();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        return path;
    }

    /**
     * @param prefix
     * @param extension
     * @return
     * @throws java.io.IOException
     */
    public File createTempFile(String prefix, String extension)
            throws IOException {
        File file = new File(getAppDirPath() + ".TEMP/" + prefix
                + System.currentTimeMillis() + extension);
        file.createNewFile();
        return file;
    }

    /**
     * 得到当前应用程序内容目录,外部存储空间不可用时返回null
     *
     * @return
     */
    public String getAppDirPath() {
        String path = null;
        if (getLocalPath() != null) {
            path = getLocalPath() + APP_DIR + "/";
        }
        return path;
    }

    /**
     * 得到当前app的目录
     *
     * @return
     */
    private static String getLocalPath() {
        String sdPath = null;
        sdPath = mContext.getFilesDir().getAbsolutePath() + "/";
        return sdPath;
    }

    /**
     * 检查sd卡是否就绪并且可读写
     *
     * @return
     */
    public boolean isSDCanWrite() {
        String status = Environment.getExternalStorageState();
        if (status.equals(Environment.MEDIA_MOUNTED)
                && Environment.getExternalStorageDirectory().canWrite()
                && Environment.getExternalStorageDirectory().canRead()) {
            return true;
        } else {
            return false;
        }
    }

    private FileUtils() {
        // 创建应用内容目录
        if (isSDCanWrite()) {
            creatSDDir(APP_DIR);
            creatSDDir(TEMP_DIR);
        }
    }

    /**
     * 在SD卡根目录上创建目录
     *
     * @param dirName
     */
    public File creatSDDir(String dirName) {
        File dir = new File(getLocalPath() + dirName);
        dir.mkdirs();
        return dir;
    }
}
