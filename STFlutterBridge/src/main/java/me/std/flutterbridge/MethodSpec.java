package me.std.flutterbridge;

/**
 * Description:方法集： flutter<->native
 * Author: lixiao
 * Create on: 2019/3/27.
 * Job number: 1800838
 * Phone: 18611867932
 * Email: lixiao@chunyu.me
 */
public enum MethodSpec {
    FLT_OPEN_FLUTTER("open_flutter"),//打开flutter页面
    FLT_REFRESH("update_flutter_page"),//刷新页面
    FLT_GO_BACK("pop"),//返回上页

    NAT_CALL("call_native"),
    NAT_HTTP("network"),//调用原生网络请求
    NAT_SAVE_IMAGE("save_image"),//保存图片
    NAT_OPEN("open_native"),//打开原生界面
    NAT_NEW_ARCHIVE("create_archive"),
    NAT_OPEN_H5("open_h5"),//打开H5
    NAT_CLOSE("close"),//关闭当前页
    NAT_GET_CLINIC_DATA("get_clinic_data"),//获取科室
    NAT_OPEN_NATIVE("open_native");//打开原生页面

    public String name;

    MethodSpec(String name) {
        this.name = name;
    }
}
