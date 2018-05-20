package mum.asd.patterns.FactoryMethod;

import mum.asd.domain.Promotion;
import mum.asd.patterns.Command.PromotionCommandInterface;

public class SpringHolidayPromotion extends HolidayPromotion implements PromotionCommandInterface {
    PromotionName promotionName;
    public SpringHolidayPromotion(){
//        super("SpringHoliday",15,20);
        this.setDiscount(15);
        this.setPercent(20);
        this.setName("SpringHoliday");
        this.promotionName = PromotionName.SpringHoliday;
    }
    @Override
    public int executeGetDiscout() {
        return this.getDiscount()+(int)this.getPercent();
    }

}
