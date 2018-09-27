package com.thinkgem.jeesite.modules.postManeger.sf.service;

import java.util.Map;

public class SFUtil {
	/**
	 * 获取下单接口的请求数据
	 * @param params
	 * @return
	 */
	private String getOrderServiceRequestXml(Map<String,String> params){
		StringBuilder strBuilder = new StringBuilder();
		/* //订单号
	    String orderNo = params.get("orderNo");
	    //收件人
	    String receiverName = params.get("receiverName");
	    //收件人电话
	    String receiverMobile = params.get("receiverMobile");
	    //收件人详细地址
	    String receiverAddress = params.get("address");
	    //商品名称
	    String commodityName = params.get("commodityName");
	    //商品数量
	    String orderNum = params.get("orderNum");
	
	
	    
	    strBuilder.append("<Request service='OrderService' lang='zh-CN'>");
	    strBuilder.append("<Head>" + clientCode + "</Head>");
	    strBuilder.append("<Body>");
	    strBuilder.append("<Order").append(" ");
	    strBuilder.append("orderid='" + orderNo.toString().trim() + "" + "'").append(" ");
	    //返回顺丰运单号
	    strBuilder.append("is_gen_bill_no='1'").append(" ");
	    //寄件方信息
	    strBuilder.append("j_company='" + j_company + "'").append(" ");
	    strBuilder.append("j_contact='" + j_contact + "'").append(" ");
	    strBuilder.append("j_tel='" + j_tel + "'").append(" ");
	    strBuilder.append("j_address='" + j_province+j_city+j_county+j_address + "'").append(" ");
	    //收件方信息
	    strBuilder.append("d_company='" + d_company + "'").append(" ");
	    strBuilder.append("d_contact='" + receiverName.toString().trim() + "'").append(" ");
	    strBuilder.append("d_tel='" + receiverMobile.toString().trim() + "'").append(" ");
	    strBuilder.append("d_address='" + receiverAddress.toString().trim() + "'").append(" ");
	    strBuilder.append(" > ");
	    //货物信息
	    strBuilder.append("<Cargo").append(" ");
	    strBuilder.append("name='" + commodityName + "'").append(" ");
	    strBuilder.append("count='" + orderNum.toString() + "'").append(" ");
	    strBuilder.append("unit='双'").append(">");
	    strBuilder.append("</Cargo>");
	
	    strBuilder.append("</Order>");
	    strBuilder.append("</Body>");
	    strBuilder.append("</Request>");*/
	
	    return strBuilder.toString();
	}
}

