package com.std.framework.interfaces;

/**
 * 
 * 描      述 ：云端提供的接口地址
 * 创建日期 ： 2014-4-21
 * 作      者 ： lx
 * 修改日期 ： 
 * 修  改 者 ：
 * @version : 1.0
 */
public interface CloudService {
	/**服务器地址*/

	//	public static final String PRE = "http://192.168.9.151:18081/"; //内网地址
	//	public static final String PRE = "http://218.241.167.186:18081/"; //外网地址
	//	public static final String PRE = "http://10.11.101.219:8080/";//内网地址
	public static final String PRE = "http://119.57.188.133:18081/"; //外网地址
	//	public static final String PRE = "http://10.11.105.89:18081/";//内网地址

	/**扫描条码收款URL*/
	public static final String BARCODEPAY = PRE + "api/alipay/barCodePay.do";

	/**绑定URL*/
	public static final String BINGDING = PRE + "api/login/loginAdminAction.do";

	/**登陆URL*/
	public static final String LOGIN = PRE + "api/login/loginAction.do";

	/**订单查询URL*/
	public static final String ORDER_QUERY = PRE + "api/order/getOrderDetailsList.do";

	/**天猫意向订单获取地址**/
	public static final String TMALL_ORDER_LIST = PRE + "api/order/getTempTmallOrderInfo.do";

	/**新增商品获取地址**/
	public static final String ADD_DRUG_LIST = PRE + "api/commodity/getCommoditInfo.do";

	/**订单支付地址*/
	public static final String PAYPATH = PRE + "api/pay/orderPay.do";

	/**天猫订单支付地址*/
	public static final String TMALL_PAY_PATH = PRE + "api/pay/tmallOrderPay.do";

	/**每日订单数量查询呢地址*/
	public static final String ORDER_NUMBER_QUERY = PRE + "api/order/getNumberOfOrdersDay.do";

	/**天猫订单拆单地址*/
	public static final String UPDATETMALLORDER = PRE + "api/order/updateTmallOrder.do";

	/**取消天猫订单地址*/
	public static final String CANCLETMALLORDER = PRE + "api/order/orderCancel.do";

	/**退款地址*/
	public static final String REFUND = PRE + "api/pay/refundPay.do";

	/**交接班地址*/
	public static final String SHIFT = PRE + "api/handover/handoverAction.do";

	/**盘货同步地址*/
	public static final String STOCKTAKING = PRE + "api/commodity/stocktaking.do";

	/**商品类型地址*/
	public static final String PRODUCTTYPE = PRE + "api/commodity/getProductType.do";

	/**盘货范围地址*/
	public static final String STOCKTAKINGRANGE = PRE + "api/commodity/stocktakingrange.do";

	/**获取钱箱金额地址*/
	public static final String HANDOVERDETAILSBYCON = PRE + "api/handover/getHandoverDetailsByConAction.do";

	/**取消订单*/
	public static final String ORDERCANCEL = PRE + "api/order/orderCancel.do";
}
