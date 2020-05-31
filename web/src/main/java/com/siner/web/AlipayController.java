
package com.siner.web;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.siner.entity.Book;
import com.siner.entity.Orders;
import com.siner.service.BookService;
import com.siner.service.OrdersService;
import com.siner.util.AlipayConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller("alipay")
public class AlipayController {

    @Autowired
    private BookService bookService;

    @Autowired
    private OrdersService ordersService;

    /**
     * 阿里支付代码块
     * @param oid   订单号
     * @param response
     * @param request
     * @throws Exception
     */
    @RequestMapping(value = "/aliplay", method = RequestMethod.POST)
    public void payOrder(String bid, HttpServletResponse response, HttpServletRequest request) throws Exception {
        Book one = bookService.searchBookByID(Integer.valueOf(bid));
        System.out.println("支付方法执行");
        //获得初始化的AlipayClient
        AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id, AlipayConfig.merchant_private_key, "json", AlipayConfig.charset, AlipayConfig.alipay_public_key, AlipayConfig.sign_type);
        //设置请求参数
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        alipayRequest.setReturnUrl(AlipayConfig.return_url);
        alipayRequest.setNotifyUrl(AlipayConfig.notify_url);
        //商户订单号，商户网站订单系统中唯一订单号，必填
        String out_trade_no = String.valueOf(one.getBid()+"s"+new Date().getTime());
        //付款金额，必填
        String total_amount = String.valueOf(one.getBprice());
        //订单名称，必填
//        List<SUPN> list = JSONObject.parseArray(one.getNameandnum(), SUPN.class);//将存入的json字符串转为对象数组
        String subject = "17书城————购买书籍";
        //商品描述，可空
        String body = "";
        alipayRequest.setBizContent("{\"out_trade_no\":\"" + out_trade_no + "\","
                + "\"total_amount\":\"" + total_amount + "\","
                + "\"subject\":\"" + subject + "\","
                + "\"body\":\"" + body + "\","
                + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
        String head = "<html><head><meta http-equiv='Content-Type' content='text/html; charset=utf-8'></head><body>";
        String result = alipayClient.pageExecute(alipayRequest).getBody();
        String buttom = "</body></html>";
        String content = head + result + buttom;
        response.setHeader("content-type", "text/html;charset=UTF-8");
        OutputStream out = response.getOutputStream();
        out.write(content.getBytes("UTF-8"));
        out.close();
//        response.getWriter().println(head + result + buttom);
    }



    /**
     * 阿里支付的return_url跳转方法
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping(value = "/payloading", method = RequestMethod.GET,produces="application/json; utf-8")
    public void payLoading(HttpServletRequest request, HttpServletResponse response) throws Exception {
        System.out.println("支付成功, 进入异步通知接口...");
        response.setContentType("text/html; charset=utf-8");//千万不要忘了设编码,否则密钥报错!!!!!!
        PrintWriter out = response.getWriter();
        Map<String, String> params = new HashMap<String, String>();
        Map<String, String[]> requestParams = request.getParameterMap();
        for (String str : requestParams.keySet()) {
            String name = str;
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            params.put(name, valueStr);
        }
        boolean signVerified = AlipaySignature.rsaCheckV1(params, AlipayConfig.alipay_public_key, AlipayConfig.charset, AlipayConfig.sign_type); //调用SDK验证签名

        if (!signVerified) {
            System.out.println("验签失败");
            out.print("fail");
            return;
        }
        //商户订单号
        String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"), "UTF-8");

        //交易状态
        System.out.println("交易成功");
        Orders one = ordersService.findOrderByOid(out_trade_no.split("s")[0]);
        one.setState(1);//设置为已付款状态
        /*OrderGoods orderGoods = new OrderGoods();
        orderGoods.setOid(one.getOid());//设置商品表oid
        orderGoods.setOoprice(one.getOprice());//设置价格
        orderGoods.setUname(one.getUser().getUname());//设置买家名
        orderGoods.setOostatus(0);//0为后台未审核通过，1为审核通过
        orderGoodsService.saveOne(orderGoods);*/
        out.print("<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>Title</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "\n" +
                "</body>\n" +
                "<script>\n" +
                "    location.href=\"http://47.100.60.48:8011/pet-user/checkout.html\";\n" +
                "</script>\n" +
                "</html>");
    }




}

