package com.company.HomeWork_1;

enum DealName {
    FX_SPOT,
    BOND,
    COMMODITY_SPOT,
    IR_SWAP;

    public static DealName selectDealName(String type){
        switch(type){
            case "FX_SPOT":
                return DealName.FX_SPOT;
            case "BOND":
                return DealName.BOND;
            case "COMMODITY_SPOT":
                return DealName.COMMODITY_SPOT;
            case "IR_SWAP"  :
                return DealName.IR_SWAP;
        }
        return null;
    }
}
