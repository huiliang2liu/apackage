package com.lhl.apackage;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Resources;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Locale;

public class PackageUtil {

    public static String packageName(Context context, String apkFile) {
        String name = null;
        try {
            name = context.getPackageManager().getPackageArchiveInfo(apkFile, PackageManager.GET_ACTIVITIES).packageName;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return name;
    }

    public static String apkName(Context context) {
        try {
            return apkName(context.getResources(), context.getPackageManager().getPackageInfo(context.getPackageName(), 0).applicationInfo.labelRes);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String apkName(Context context, String apkFile) {
        int labelRes = context.getPackageManager().getPackageArchiveInfo(apkFile, 0).applicationInfo.labelRes;
        try {
            AssetManager assetManager = AssetManager.class.newInstance();
            Method addAssetPath = AssetManager.class.getDeclaredMethod("addAssetPath", String.class);
            if (!addAssetPath.isAccessible())
                addAssetPath.setAccessible(true);
            addAssetPath.invoke(assetManager,
                    apkFile);
            Resources superRes = context.getResources();
            Resources resources = new Resources(assetManager,
                    superRes.getDisplayMetrics(),
                    superRes.getConfiguration());
            return apkName(resources, labelRes);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String apkName(Resources resources, int labelRes) {
        return resources.getString(labelRes);
    }

    public static String versionName(Context context) {
        String name = null;
        try {
            name = versionName(context.getPackageManager().getPackageInfo(context.getPackageName(), 0));

        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return name;
    }

    public static String versionName(Context context, String apkFile) {
        String name = null;
        try {
            name = versionName(context.getPackageManager().getPackageArchiveInfo(apkFile, 0));

        } catch (Exception e) {
            e.printStackTrace();
        }

        return name;
    }

    private static String versionName(PackageInfo info) {
        return info.versionName;
    }

    public static int versionCode(Context context) {
        int code = 0;
        try {
            code = versionCode(context.getPackageManager().getPackageInfo(context.getPackageName(), 0));

        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        return code;
    }

    public static int versionCode(Context context, String apkFile) {
        int code = 0;
        try {
            PackageInfo info = context.getPackageManager().getPackageArchiveInfo(apkFile, 0);
            code = info.versionCode;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return code;
    }

    public static int versionCode(PackageInfo info) {
        return info.versionCode;
    }


//    public static String getInstalledAPKSignature(Context context) {
//        try {
//            PackageManager pm = context.getPackageManager();
//            PackageInfo appInfo = pm.getPackageInfo(context.getPackageName(), PackageManager.GET_SIGNATURES);
//            if (null == appInfo || null == appInfo.signatures) {
//                return "";
//            }
//            return appInfo.signatures[0].toCharsString();
//        } catch (PackageManager.NameNotFoundException e) {
//            e.printStackTrace();
//        }
//        return "";
//    }


    public static String sHA1(Context context) {
        try {
            PackageInfo info = context.getPackageManager().getPackageInfo(
                    context.getPackageName(), PackageManager.GET_SIGNATURES);
            return sHA1(info);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String sHA1(Context context, String apkPath) {
        try {
            PackageInfo info = context.getPackageManager().getPackageArchiveInfo(apkPath, PackageManager.GET_SIGNATURES);
            return sHA1(info);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String sHA1(PackageInfo info) {
        try {
            byte[] cert = info.signatures[0].toByteArray();
            MessageDigest md = MessageDigest.getInstance("SHA1");
            byte[] publicKey = md.digest(cert);
            StringBuffer hexString = new StringBuffer();
            for (int i = 0; i < publicKey.length; i++) {
                String appendString = Integer.toHexString(0xFF & publicKey[i])
                        .toUpperCase(Locale.US);
                if (appendString.length() == 1)
                    hexString.append("0");
                hexString.append(appendString);
                hexString.append(":");
            }
            String result = hexString.toString();
            return result.substring(0, result.length() - 1);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

}
