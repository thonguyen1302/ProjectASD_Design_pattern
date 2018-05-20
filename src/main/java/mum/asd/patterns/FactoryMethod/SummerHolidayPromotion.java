package mum.asd.patterns.FactoryMethod;

import mum.asd.domain.Promotion;

public class SummerHolidayPromotion extends HolidayPromotion {
    public SummerHolidayPromotion(){
//        super("SummerHoliday",15,10);
        this.setDiscount(15);
        this.setPercent(10);
        this.setName("SummerHoliday");
    }
}
