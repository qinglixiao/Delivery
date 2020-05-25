
/*
 * 回调几种case
 * 1. channel调用本身的callback
 * 2. flutter通过参数传递到原生的回调方法
 *    eg. flutter传递到一个原生页面一个回调，该原生页面返回时，回调flutter方法，告知处理结果
 * 3. 原生通过参数传递到flutter的回调方法
 *    同上，暂不支持
 * 
 * 这些回调均使用以下格式
 */

typedef CYCallBackFn = Function(Object data, String error);