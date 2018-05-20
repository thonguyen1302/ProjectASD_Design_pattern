package mum.asd.patterns.FactoryMethod;

import mum.asd.domain.Promotion;

public class FallHolidayPromotion extends HolidayPromotion {
    public FallHolidayPromotion(){
//        super("FallHoliday",10,25);
        this.setDiscount(10);
        this.setPercent(25);
        this.setName("FallHoliday");
    }
}
