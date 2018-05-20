package mum.asd.patterns.FactoryMethod;

import mum.asd.domain.Promotion;

public class HolidayPromotion extends Promotion {
    public Promotion getPromotion(){
        return new Promotion(this.getName(),this.getDiscount(),this.getPercent());
    }
}
