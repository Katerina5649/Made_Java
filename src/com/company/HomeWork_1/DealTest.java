package com.company.HomeWork_1;

import org.junit.Test;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

class DealTest {

    @Test
    void createDeal() {
        assertTrue(Main.createDeal(1000, DealName.selectDealName("FX_SPOT")) instanceof FxSpot);
        assertTrue(Main.createDeal(1000, DealName.selectDealName("BOND")) instanceof Bond);
        assertTrue(Main.createDeal(1000, DealName.selectDealName("COMMODITY_SPOT")) instanceof CommoditySpot);
        assertTrue(Main.createDeal(1000, DealName.selectDealName("IR_SWAP")) instanceof IrSwap);
        assertNull(Main.createDeal(1000, DealName.selectDealName("WRONG_TYPE")));
    }


}