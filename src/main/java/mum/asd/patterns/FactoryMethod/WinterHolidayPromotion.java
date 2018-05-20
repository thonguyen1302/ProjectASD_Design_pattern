package mum.asd.patterns.FactoryMethod;

import mum.asd.domain.Promotion;

public class WinterHolidayPromotion extends HolidayPromotion {
    public WinterHolidayPromotion(){
//        super("WinterHoliday",5,5);
        this.setDiscount(5);
        this.setPercent(5);
        this.setName("WinterHoliday");
    }
}
