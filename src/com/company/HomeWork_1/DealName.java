package com.company.HomeWork_1;


enum DealName {
    FX_SPOT {
        @Override
        Object createDeal(int price) {
            return new FxSpot(price);
        }
    },
    BOND {
        @Override
        Object createDeal(int price) {
            return new Bond(price);
        }
    },
    COMMODITY_SPOT {
        @Override
        Object createDeal(int price) {
            return new CommoditySpot(price);
        }
    },
    IR_SWAP {
        @Override
        Object createDeal(int price) {
            return new IrSwap(price);
        }
    };


    abstract Object createDeal(int price);

}



