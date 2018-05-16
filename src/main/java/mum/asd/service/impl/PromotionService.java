package mum.asd.service.impl;

import mum.asd.domain.Promotion;
import mum.asd.repository.PromotionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PromotionService {
    @Autowired
    PromotionRepository promotionRepository;
    public boolean addPromotion(String name, int discount, float percent){
        Promotion promotion = new Promotion();
        promotion.setName(name);
        promotion.setDiscount(discount);
        promotion.setPercent(percent);
        if (promotionRepository.save(promotion)!=null){
            return true;
        }
        return false;
    }
}
