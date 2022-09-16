package com.lhl.apackage;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;

import androidx.core.content.FileProvider;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ApkUtil {

    static volatile List<String> packageNames = null;
    static volatile List<String> intentPackages = new ArrayList<>();

    /**
     * 是否安装了应用宝
     *
     * @param context
     * @return
     */
    public static boolean isAvailableYingYongBao(Context context) {
        return isAvailable(context, "com.tencent.android.qqdownloader");
    }

    /**
     * 是否安装了360助手
     *
     * @param context
     * @return
     */
    public static boolean isAvailable360Help(Context context) {
        return isAvailable(context, "com.qihoo.appstore");
    }

    /**
     * 是否安装了百度助手
     *
     * @param context
     * @return
     */
    public static boolean isAvailableBaiDuHelp(Context context) {
        return isAvailable(context, "com.baidu.appsearch");
    }

    /**
     * 是否安装了小米商店
     *
     * @param context
     * @return
     */
    public static boolean isAvailableXiaoMiStore(Context context) {
        return isAvailable(context, "com.xiaomi.market");
    }

    /**
     * 是否安装了华为商店
     *
     * @param context
     * @return
     */
    public static boolean isAvailableHuaWeiStore(Context context) {
        return isAvailable(context, "com.huawei.appmarket");
    }

    /**
     * 是否安装了google商店
     *
     * @param context
     * @return
     */
    public static boolean isAvailableGoogleStore(Context context) {
        return isAvailable(context, "com.android.vending");
    }

    /**
     * 是否安装了魅族商店
     *
     * @param context
     * @return
     */
    public static boolean isAvailableMeiZuStore(Context context) {
        return isAvailable(context, "com.meizu.mstore");
    }

    /**
     * 是否安装了oppo商店
     *
     * @param context
     * @return
     */
    public static boolean isAvailableOppoStore(Context context) {
        return isAvailable(context, "com.oppo.market");
    }

    /**
     * 是否安装了vivo商店
     *
     * @param context
     * @return
     */
    public static boolean isAvailableVivoStore(Context context) {
        return isAvailable(context, "com.bbk.appstore");
    }

    /**
     * 是否安装了三星商店
     *
     * @param context
     * @return
     */
    public static boolean isAvailableSamsung(Context context) {
        return isAvailable(context, "com.sec.android.app.samsungapps");
    }


    /**
     * 是否安装了qq
     *
     * @param context
     * @return
     */
    public static boolean isAvailableQQ(Context context) {
        return isAvailable(context, "com.tencent.mobileqq");
    }

    /**
     * 是否安装了qq空间
     *
     * @param context
     * @return
     */
    public static boolean isAvailableQQZone(Context context) {
        return isAvailable(context, "com.qzone");
    }

    /**
     * 是否安装了微信
     *
     * @param context
     * @return
     */
    public static boolean isAvailableWeiXin(Context context) {
        return isAvailable(context, "com.tencent.mm");
    }

    /**
     * 是否安装了探探
     *
     * @param context
     * @return
     */
    public static boolean isAvailableTanTan(Context context) {
        return isAvailable(context, "com.p1.mobile.putong");
    }

    /**
     * 是否安装了陌陌
     *
     * @param context
     * @return
     */
    public static boolean isAvailableMoMo(Context context) {
        return isAvailable(context, "com.immomo.momo");
    }

    /**
     * 是否安装了淘宝
     *
     * @param context
     * @return
     */
    public static boolean isAvailableTaoBao(Context context) {
        return isAvailable(context, "com.taobao.taobao");
    }

    /**
     * 是否安装了京东
     *
     * @param context
     * @return
     */
    public static boolean isAvailableJingDong(Context context) {
        return isAvailable(context, "com.jingdong.app.mall");
    }

    /**
     * 是否安装了拼多多
     *
     * @param context
     * @return
     */
    public static boolean isAvailablePinDuoDuo(Context context) {
        return isAvailable(context, "com.xunmeng.pinduoduo");
    }

    /**
     * 是否安装苏宁易购
     *
     * @param context
     * @return
     */
    public static boolean isAvailableSuNingEasyBuy(Context context) {
        return isAvailable(context, "com.suning.mobile.ebuy");
    }

    /**
     * 是否安装百度地图
     *
     * @param context
     * @return
     */
    public static boolean isAvailableBaiDuMap(Context context) {
        return isAvailable(context, "com.baidu.BaiduMap");
    }

    /**
     * 是否安装了高德地图
     *
     * @param context
     * @return
     */
    public static boolean isAvailableMiniMap(Context context) {
        return isAvailable(context, "com.autonavi.minimap");
    }

    public static boolean isAvailableGoogleMap(Context context) {
        return isAvailable(context, "com.google.android.apps.maps");
    }

    /**
     * 检查手机上是否安装了指定的软件
     */
    public static boolean isAvailable(Context context, File apkFile) {
        return isAvailable(context, PackageUtil.packageName(context, apkFile.getAbsolutePath()));
    }


    /**
     * 检查手机上是否安装了指定的软件
     */
    public static boolean isAvailable(Context context, String packageName) {
        if (searchSystem(context, packageName))
            return true;
        if (intentPackages.contains(packageName))
            return true;
        synchronized (intentPackages) {
            if (intentPackages.contains(packageName))
                return true;
            Intent intent = packageName2intent(context, packageName);
            if (intent != null) {
                intentPackages.add(packageName);
                return true;
            }
            return false;
        }
    }

    private static boolean searchSystem(Context context, String packageName) {
        if (packageNames != null) {
            return packageNames.contains(packageName);
        }
        synchronized (ApkUtil.class) {
            if (packageNames != null)
                return packageNames.contains(packageName);
            packageNames = new ArrayList<>();
            final PackageManager packageManager = context.getPackageManager();
            // 获取所有已安装程序的包信息
            List<PackageInfo> packageInfos = packageManager.getInstalledPackages(0);
            // 从pinfo中将包名字逐一取出，压入pName list中
            if (packageInfos != null && !packageInfos.isEmpty()) {
                for (PackageInfo info : packageInfos)
                    packageNames.add(info.packageName);
                // 判断packageNames中是否有目标程序的包名，有TRUE，没有FALSE
            }
            return packageNames.contains(packageName);
        }
    }

    /**
     * 更具包名获取启动应用的intent
     *
     * @param context
     * @param packageName
     * @return
     */
    public static Intent packageName2intent(Context context, String packageName) {
        PackageManager packageManager = context.getPackageManager();
        Intent intent = packageManager.getLaunchIntentForPackage(packageName);
        return intent;
    }

    /**
     * 卸载一个app
     */
    public static void uninstall(Context context, String packageName) {
        //通过程序的包名创建URI
        Uri packageURI = Uri.parse("package:" + packageName);
        //创建Intent意图
        Intent intent = new Intent(Intent.ACTION_DELETE, packageURI);
        //执行卸载程序
        context.startActivity(intent);
    }


    //    <uses-permission android:name="android.permission.REQUEST_INSTALL_PACKAGES" />
    public static Intent install(File file, Context context, String authority) {
//        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
//
//        }
        // 安装应用
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if (Build.VERSION.SDK_INT >= 24) { //判读版本是否在7.0以上
            //参数1 上下文, 参数2 Provider主机地址 和配置文件中保持一致   参数3  共享的文件
            Uri apkUri =
                    FileProvider.getUriForFile(context, authority, file);
            //添加这一句表示对目标应用临时授权该Uri所代表的文件
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            intent.setDataAndType(apkUri, "application/vnd.android.package-archive");
        } else {
            intent.setDataAndType(Uri.fromFile(file),
                    "application/vnd.android.package-archive");
        }
        return intent;
    }


    public static Intent home() {
        Intent home = new Intent(Intent.ACTION_MAIN);
        home.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        home.addCategory(Intent.CATEGORY_HOME);
        return home;
    }


}
