/// 接口地址

const NET_GET_ORDER_LIST = "/marketer/orders/get";  ///我买的列表
const NET_POST_CANCEL_ORDER = "/marketer/order/abolish";  ///取消订单
const NET_GET_SELL_ORDER_LIST = "/marketer/orders/getclearing";///我卖的列表
const NET_GET_AFFIRMPAY_ORDER = "/marketer/order/affirmpay";///确认收款
const NET_GET_PLACE_LIST = "/mailing/mailings/get";///地址列表
const NET_GET_PLACE_DETAIL = "/mailing/mailing/get";  ///收货地址详情
const NET_POST_PLACE_EDIT = "/mailing/mailing/edit";  ///修改地址详情
const NET_POST_PLACE_ADD = "/mailing/mailing/add";  ///添加地址详情
const NET_POST_PLACE_DELETE = "/mailing/mailing/delete"; ///删除地址
const NET_POST_MODIFY_PWD = "/member/user/editPassword";///修改密码
const NET_POST_SEND_MESSAGE = "/msg/sms/codeSend";///发送短信
const NET_POST_FORGET_PASSWORD = "/member/user/passwordForgot";///忘记密码
const NET_GET_PAY_PASSWORD_MODIFY = "/payaccount/tfs/password";///修改、重置支付密码
const NET_GET_MSG_CRYP_TO_GET = "/msg/cryptoGet";///获取阿里云上传需要的加密信息
const NET_POST_FILE_TO_ALIYUN = "https://tope-test-baie-bj.oss-cn-beijing.aliyuncs.com";///上传文件到阿里云
const NET_POST_EDIT_USER_IMAGE = "/member/user/cover_img_edit";///上传阿里云头像图片地址到我们服务器