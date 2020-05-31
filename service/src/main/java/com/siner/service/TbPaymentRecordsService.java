package com.siner.service;

import com.siner.util.AliReturnPayBean;
import org.springframework.stereotype.Service;

@Service("TbPaymentRecordsService")
public class TbPaymentRecordsService {
    public void aliPaySuccess(AliReturnPayBean returnPay){

        System.out.println(returnPay);
    }
}
