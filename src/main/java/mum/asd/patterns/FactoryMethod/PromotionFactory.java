package mum.asd.patterns.FactoryMethod;

import mum.asd.domain.Promotion;

public interface PromotionFactory {
    public Promotion createPromotion(String promotionName);
}
