package mum.asd.patterns.Command;

import mum.asd.patterns.FactoryMethod.HolidayPromotion;

public interface PromotionCommandInterface {
    public HolidayPromotion executeCreateObject(String promotionName);
}
