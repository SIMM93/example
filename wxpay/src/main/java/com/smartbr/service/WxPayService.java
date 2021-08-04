package com.smartbr.service;


import com.smartbr.entity.bo.OnePayItem;
import com.smartbr.entity.bo.OnePayRefundsItem;

import java.util.HashMap;

public interface WxPayService {
    HashMap<String, String> wxPayJSAPI(OnePayItem onePayItem);

    String wxCombinePayJSAPI(String jsonInfo);

    String wxPayRefund(OnePayRefundsItem onePayRefundsItem);

    String wxProfitSharing(String jsonInfo);

    String wxOrderSearchJSAPI(String out_trade_no);


}
