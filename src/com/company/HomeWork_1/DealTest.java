package com.company.HomeWork_1;


import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertTrue;

class DealTest {

    @Test
    void createDeal() {
        assertTrue(DealName.valueOf("FX_SPOT").createDeal(100) instanceof FxSpot);
        assertTrue(DealName.valueOf("BOND").createDeal(100) instanceof Bond);
        assertTrue(DealName.valueOf("COMMODITY_SPOT").createDeal(100) instanceof CommoditySpot);
        assertTrue(DealName.valueOf("IR_SWAP").createDeal(100) instanceof IrSwap);
    }


}