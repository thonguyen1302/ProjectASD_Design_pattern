package mum.asd.patterns.prototype;

import org.springframework.beans.factory.annotation.Required;

public interface PromotionPrototype {
    @Required
    public PromotionPrototype doClone();
}
