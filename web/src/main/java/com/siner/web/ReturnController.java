package com.siner.web;

import com.alipay.api.AlipayApiException;
import com.alipay.api.internal.util.AlipaySignature;
import com.itextpdf.text.log.Logger;
import com.itextpdf.text.log.LoggerFactory;
import com.siner.util.AliReturnPayBean;
import com.siner.util.AlipayConfig;
import com.siner.util.PayUtil;
import com.siner.service.TbPaymentRecordsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value = "/returnPay")
public class ReturnController {
    private static Logger logger = LoggerFactory.getLogger(ReturnController.class);
    @Autowired
    private TbPaymentRecordsService tbPaymentRecordsService;

    /**
     * 支付宝回调的接口
     *
     * @param uuid
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/aliReturnPay", method = RequestMethod.POST)
    public void returnPay(HttpServletResponse response, AliReturnPayBean returnPay, HttpServletRequest req)
            throws IOException {
        response.setContentType("type=text/html;charset=UTF-8");
        logger.info("****************************************支付宝的的回调函数被调用******************************");
        if (!PayUtil.checkSign(req)) {
            logger.info("****************************************验签失败*******************************************");
            response.getWriter().write("failture");
            return;
        }
        if (returnPay == null) {
            logger.info("支付宝的returnPay返回为空");
            response.getWriter().write("success");
            return;
        }
        logger.info("支付宝的returnPay" + returnPay.toString());
        if (returnPay.getTrade_status().equals("TRADE_SUCCESS")) {
            logger.info("支付宝的支付状态为TRADE_SUCCESS");
            tbPaymentRecordsService.aliPaySuccess(returnPay);
        }
        response.getWriter().write("success");
    }

    /**
     * 支付宝的验签方法
     * @param req
     * @return
     */
    public static boolean checkSign(HttpServletRequest req) {
        Map<String, String[]> requestMap = req.getParameterMap();
        Map<String, String> paramsMap = new HashMap<>();
        requestMap.forEach((key, values) -> {
            String strs = "";
            for(String value : values) {
                strs = strs + value;
            }
            System.out.println(("key值为"+key+"value为："+strs));
            paramsMap.put(key, strs);
        });

        //调用SDK验证签名
        try {
            return  AlipaySignature.rsaCheckV1(paramsMap, AlipayConfig.alipay_public_key, AlipayConfig.charset, AlipayConfig.sign_type);
        } catch (AlipayApiException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.out.println("*********************验签失败********************");
            return false;
        }
    }
}
