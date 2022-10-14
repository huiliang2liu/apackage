package com.lhl.apackage;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;

import androidx.core.content.FileProvider;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ApkUtil {

    volatile List<String> packageNames = new ArrayList<>();
    static ApkUtil apkUtil;
    private Context context;
    private List<PackageListener> listeners = new ArrayList<>();
    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String packageName = intent.getDataString();
            if (packageName == null || packageName.length() < 9)
                return;
            packageName = packageName.substring(8);
            if (intent.getAction().equals(Intent.ACTION_PACKAGE_ADDED)) {
                packageNames.add(packageName);
                for (PackageListener listener : listeners)
                    listener.onAdd(packageName);
                return;
            }
            packageNames.remove(packageName);
            for (PackageListener listener : listeners)
                listener.onRemove(packageName);
        }
    };

    public void registerPackageListener(PackageListener listener) {
        if (listener == null)
            return;
        listeners.add(listener);
    }

    public void unRegisterPackageListener(PackageListener listener) {
        listeners.remove(listener);
    }

    private ApkUtil(Context context) {
        this.context = context;
        final PackageManager packageManager = context.getPackageManager();
        // 获取所有已安装程序的包信息
        List<PackageInfo> packageInfos = packageManager.getInstalledPackages(0);
        // 从pinfo中将包名字逐一取出，压入pName list中
        if (packageInfos != null && !packageInfos.isEmpty()) {
            for (PackageInfo info : packageInfos)
                packageNames.add(info.packageName);
            // 判断packageNames中是否有目标程序的包名，有TRUE，没有FALSE
        }
        IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_PACKAGE_ADDED);
        filter.addAction(Intent.ACTION_PACKAGE_REMOVED);
        context.registerReceiver(receiver, filter);
    }

    public static ApkUtil getApkUtil(Context context) {
        if (apkUtil != null)
            return apkUtil;
        synchronized (ApkUtil.class) {
            if (apkUtil == null)
                apkUtil = new ApkUtil(context);
        }
        return apkUtil;
    }

    /**
     * 是否安装了应用宝
     *
     * @return
     */
    public boolean isAvailableYingYongBao() {
        return isAvailable("com.tencent.android.qqdownloader");
    }

    /**
     * 是否安装了360助手
     *
     * @return
     */
    public boolean isAvailable360Help() {
        return isAvailable("com.qihoo.appstore");
    }

    /**
     * 是否安装了百度助手
     *
     * @return
     */
    public boolean isAvailableBaiDuHelp() {
        return isAvailable("com.baidu.appsearch");
    }

    /**
     * 是否安装了小米商店
     *
     * @return
     */
    public boolean isAvailableXiaoMiStore() {
        return isAvailable("com.xiaomi.market");
    }

    /**
     * 是否安装了华为商店
     *
     * @return
     */
    public boolean isAvailableHuaWeiStore() {
        return isAvailable("com.huawei.appmarket");
    }

    /**
     * 是否安装了google商店
     *
     * @return
     */
    public boolean isAvailableGoogleStore() {
        return isAvailable("com.android.vending");
    }

    /**
     * 是否安装了魅族商店
     *
     * @return
     */
    public boolean isAvailableMeiZuStore() {
        return isAvailable("com.meizu.mstore");
    }

    /**
     * 是否安装了oppo商店
     *
     * @return
     */
    public boolean isAvailableOppoStore() {
        return isAvailable("com.oppo.market");
    }

    /**
     * 是否安装了vivo商店
     *
     * @return
     */
    public boolean isAvailableVivoStore() {
        return isAvailable("com.bbk.appstore");
    }

    /**
     * 是否安装了三星商店
     *
     * @return
     */
    public boolean isAvailableSamsung() {
        return isAvailable("com.sec.android.app.samsungapps");
    }


    /**
     * 是否安装了qq
     *
     * @return
     */
    public boolean isAvailableQQ() {
        return isAvailable("com.tencent.mobileqq");
    }

    /**
     * 是否安装了qq空间
     *
     * @return
     */
    public boolean isAvailableQQZone() {
        return isAvailable("com.qzone");
    }

    /**
     * 是否安装了微信
     *
     * @return
     */
    public boolean isAvailableWeiXin() {
        return isAvailable("com.tencent.mm");
    }

    /**
     * 是否安装了探探
     *
     * @return
     */
    public boolean isAvailableTanTan() {
        return isAvailable("com.p1.mobile.putong");
    }

    /**
     * 是否安装了陌陌
     *
     * @return
     */
    public boolean isAvailableMoMo() {
        return isAvailable("com.immomo.momo");
    }

    /**
     * 是否安装了淘宝
     *
     * @return
     */
    public boolean isAvailableTaoBao() {
        return isAvailable("com.taobao.taobao");
    }

    /**
     * 是否安装了京东
     *
     * @return
     */
    public boolean isAvailableJingDong() {
        return isAvailable("com.jingdong.app.mall");
    }

    /**
     * 是否安装了拼多多
     *
     * @return
     */
    public boolean isAvailablePinDuoDuo() {
        return isAvailable("com.xunmeng.pinduoduo");
    }

    /**
     * 是否安装苏宁易购
     *
     * @return
     */
    public boolean isAvailableSuNingEasyBuy() {
        return isAvailable("com.suning.mobile.ebuy");
    }

    /**
     * 是否安装百度地图
     *
     * @return
     */
    public boolean isAvailableBaiDuMap() {
        return isAvailable("com.baidu.BaiduMap");
    }

    /**
     * 是否安装了高德地图
     *
     * @return
     */
    public boolean isAvailableMiniMap() {
        return isAvailable("com.autonavi.minimap");
    }

    public boolean isAvailableGoogleMap() {
        return isAvailable("com.google.android.apps.maps");
    }

    /**
     * 检查手机上是否安装了指定的软件
     */
    public boolean isAvailable(File apkFile) {
        return isAvailable(PackageUtil.packageName(context, apkFile.getAbsolutePath()));
    }


    /**
     * 检查手机上是否安装了指定的软件
     */
    public boolean isAvailable(String packageName) {
        return packageNames.contains(packageName);
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
