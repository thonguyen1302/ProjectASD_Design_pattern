package mum.asd.patterns.FactoryMethod;

import mum.asd.domain.Promotion;
import mum.asd.patterns.Command.PromotionCommandInterface;

public class WinterHolidayPromotion extends HolidayPromotion implements PromotionCommandInterface {
    public WinterHolidayPromotion(){
//        super("WinterHoliday",5,5);
        this.setDiscount(5);
        this.setPercent(5);
        this.setName("WinterHoliday");
    }
    @Override
    public int executeGetDiscout() {
        return Math.abs(this.getDiscount()-(int)this.getPercent());
    }
}
