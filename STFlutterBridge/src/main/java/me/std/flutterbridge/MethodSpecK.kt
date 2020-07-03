package me.std.flutterbridge

/**
 * Description:
 * Author: lixiao
 * Create on: 2020/6/5.
 */
enum class MethodSpecK(method: String) {
    FLT_OPEN_FLUTTER("open_flutter"),//打开flutter页面
    FLT_REFRESH("update_flutter_page"),//刷新页面

    NAT_GO_BACK("pop"),//返回上页
    NAT_CALL("call_native"),//访问原生方法
    NAT_OPEN("open_native"),//打开原生界面
    NAT_OPEN_H5("open_h5"),//打开H5
    NAT_CLOSE("close"),//关闭当前页
    NAT_GET_INIT_ARGS("get_init_args");//获取初始化参数

    var realName: String = method
}