package mum.asd.patterns.FactoryMethod;

import mum.asd.domain.Promotion;

public class SpringHolidayPromotion extends HolidayPromotion {
    PromotionName promotionName;
    public SpringHolidayPromotion(){
//        super("SpringHoliday",15,20);
        this.setDiscount(15);
        this.setPercent(20);
        this.setName("SpringHoliday");
        this.promotionName = PromotionName.SpringHoliday;
    }
}
