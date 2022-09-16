###  说明文档
#### 使用
##### 配置资源来源地址
```
maven { url = uri("https://gitee.com/liu-huiliang/jarlibs/raw/master") }
```
##### 配置引用 
```
implementation 'com.lhl.apackage:apackage:1.0.0'
```
#### api说明
##### PackageUtil说明
| 方法名 | 方法说明     | 参数说明                   |
| --- |----------|------------------------|
 | versionName | 获取版本名    | context上下文             |
 | versionName | 获取版本名    | context上下文，apkFile文件地址 |
 | versionCode | 获取版本号    | context上下文             |
 | versionCode | 获取版本号    | context上下文，apkFile文件地址 |
 | sHA1 | 获取SHA1签名 | context上下文             |
 | sHA1 | 获取SHA1签名 | context上下文，apkFile文件地址 |
 | packageName | 获取包名     | context上下文，apkFile文件地址 |
 | apkName | 获取应用名    | context上下文  |
| apkName | 获取应用名    | context上下文，apkFile文件地址  |

#####  ApkUtil说明
| 方法名 | 方法说明           | 参数说明                        |
| --- |----------------|-----------------------------|
 | isAvailableYingYongBao | 是否安装了应用宝       | context上下文                  |
 | isAvailable360Help | 是否安装了360助手     | context上下文                  |
 | isAvailableBaiDuHelp | 是否安装了百度助手      | context上下文                  |
 | isAvailableXiaoMiStore | 是否安装了小米商店      | context上下文                  |
 | isAvailableHuaWeiStore | 是否安装了华为商店      | context上下文                  |
 | isAvailableGoogleStore | 是否安装了google商店  | context上下文                  |
 | isAvailableMeiZuStore | 是否安装了魅族商店      | context上下文                  |
 | isAvailableOppoStore | 是否安装了oppo商店    | context上下文                  |
 | isAvailableVivoStore | 是否安装了vivo商店    | context上下文                  |
 | isAvailableQQ | 是否安装了qq        | context上下文                  |
 | isAvailableQQZone | 是否安装了qq空间      | context上下文                  |
 | isAvailableWeiXin | 是否安装了微信        | context上下文                  |
 | isAvailableTanTan | 是否安装了探探        | context上下文                  |
 | isAvailableMoMo | 是否安装了陌陌        | context上下文                  |
 | isAvailableTaoBao | 是否安装了淘宝        | context上下文                  |
 | isAvailableJingDong | 是否安装了京东        | context上下文                  |
 | isAvailablePinDuoDuo | 是否安装了拼多多       | context上下文                  |
 | isAvailableSuNingEasyBuy | 是否安装了苏宁易购      | context上下文                  |
 | isAvailableBaiDuMap | 是否安装了百度地图      | context上下文                  |
 | isAvailableMiniMap | 是否安装了高德地图      | context上下文                  |
 | isAvailableGoogleMap | 是否安装了Google地图  | context上下文                  |
 | isAvailable | 是否安装了指定的软件     | context上下文 ,apkFile文件地址     |
 | isAvailable | 是否安装了指定的软件     | context上下文 ,packageName应用包名 |
 | packageName2intent | 根据包名获取启动intent | context上下文 ,packageName应用包名 |
 | uninstall | 卸载一个app        | context上下文 ,packageName应用包名 |
 | home | 获取主页intent     | context上下文 ,packageName应用包名 |


#### 常用应用和对应包名
| 应用名 | 包名                               |
| --- |----------------------------------|
| 腾讯应用宝 | com.tencent.android.qqdownloader |
| 360手机助手 com.qihoo.appstore |
| 百度手机助手 | com.baidu.appsearch              |
| 小米应用商店 | com.xiaomi.market |
| 华为应用商店 | com.huawei.appmarket |
| Google Play Store | com.android.vending |
| 魅族应用市场 | com.meizu.mstore |
| 豌豆荚 | com.wandoujia.phoenix2 |
| 91手机助手 | com.dragon.android.pandaspace |
| PP手机助手 | com.pp.assistant |
| OPPO应用商店 | com.oppo.market |
| VIVO应用商店 | com.bbk.appstore |
| 搜狗应用市场 | com.sogou.androidtool |
| 三星应用商店 | com.sec.android.app.samsungapps |
| 联想应用商店  | com.lenovo.leos.appstore |
| 中兴应用商店 | zte.com.market |
| 安智应用商店 | com.hiapk.marketpho |
| 应用汇 | com.yingyonghui.market |
|机锋应用市场 | com.mappn.gfan |
| 安卓市场 | com.hiapk.marketpho |
| GO商店 | cn.goapk.market |
| 酷派应用商店 | com.yulong.android.coolmart |
| 酷市场 | com.coolapk.market |
|金立软件商店 | com.gionee.aora.market |
| QQ | com.tencent.mobileqq |
| QQ空间 | com.qzone |
| 微信 | com.tencent.mm |
| 探探 | com.p1.mobile.putong |
|陌陌 | com.immomo.momo |
| 淘宝 | com.taobao.taobao |
| 京东 | com.jingdong.app.mall |
| 拼多多 | com.xunmeng.pinduoduo |
| 美团 | com.sankuai.meituan |
| 苏宁易购 | com.suning.mobile.ebuy |
| 咸鱼 | com.taobao.idlefish |
| 每日优鲜 |  cn.missfresh.application |
| QQ阅读 | com.qq.reader |
| 微信读书 | com.tencent.weread |
| 掌阅 | com.chaozh.iReaderFree |
| 今日头条 | com.ss.android.article.news |
| 新浪微博 | com.sina.weibo |
| 网易新闻 | com.netease.newsreader.activity |
| 搜狐新闻 | com.sohu.newsclient |
| 抖音短视频 | com.ss.android.ugc.aweme |
| 快手 | com.smile.gifmaker |
| 火山小视频 | com.ss.android.ugc.live |
| 秒拍 | com.yixia.videoeditor |
| 优酷 | com.youku.phone |
| 爱奇艺 | com.qiyi.video |
| 腾讯视频 | com.tencent.qqlive |
| 斗鱼直播 | air.tv.douyu.android |
| 熊猫直播 |  com.panda.videoliveplatform |
| 携程  | ctrip.android.view |
| 去哪儿旅行 | com.Qunar |
| 飞猪 |  com.taobao.trip |
| 艺龙旅行 | com.dp.android.elong |
| 途牛旅游 | com.tuniu.app.ui |
| 美图秀秀 | com.mt.mtxx.mtxx |
| 美颜相机 | com.meitu.meiyancamera |
| 美拍 | com.meitu.meipaimv |
| 支付宝 | com.eg.android.AlipayGphone |
| 百度钱包 | com.baidu.wallet |
| 京东钱包  | com.wangyin.payment |
| 美团外卖 | com.sankuai.meituan.takeoutnew  |
| 饿了么 | me.ele |
| 大众点评 | com.dianping.v1 |
| 讯飞输入法 | com.iflytek.inputmethod |
| 百度输入法 | com.baidu.input |
| 搜狗输入法 | com.sohu.inputmethod.sogou |
| QQ浏览器 | com.tencent.mtt |
| UC浏览器 | com.UCMobile |
| 搜狗浏览器 | sogou.mobile.explorer |
| 百度浏览器 | com.baidu.browser.apps |
| 60浏览器 | com.qihoo.browser |
| 猎豹浏览器 | com.ijinshan.browser_fast |
| 谷歌浏览器 | com.android.chrome |
| 火狐浏览器 | org.mozilla.firefox |
| 百度地图 | com.baidu.BaiduMap |
| 高德地图 | com.autonavi.minimap |
| 谷歌地图 | com.google.android.apps.maps |
| 摩拜单车 | com.mobike.mobikeapp |
| 滴滴出行 | com.sdu.didi.psnger |
| 12306 | battymole.trainticket | 
| 航班管家 | com.flightmanager.view |
| 简书 | com.jianshu.haruki |
| 知乎 | com.zhihu.android |
| 得到 | com.luojilab.player |
| QQ音乐 | com.tencent.qqmusic |
| 酷狗 | com.kugou.android |
| 全民K歌 | com.tencent.karaoke |
| 酷我 | cn.kuwo.player  |
| 虾米 | fm.xiami.main |
| 唱吧 | com.changba |
| 网易云音乐 | com.netease.cloudmusic |
| 喜马拉雅 | com.ximalaya.ting.android |