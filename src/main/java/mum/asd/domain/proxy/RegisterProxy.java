package mum.asd.domain.proxy;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import mum.asd.config.StageManager;
import mum.asd.controller.ApplicationController;
import mum.asd.domain.Address;
import mum.asd.domain.HotelUser;
import mum.asd.domain.Payment;
import mum.asd.service.AddressService;
import mum.asd.service.ApplicationContextHolder;
import mum.asd.service.CardService;
import mum.asd.service.HotelUserService;
import mum.asd.view.FxmlView;
import mum.asd.service.PaymentService;


public class RegisterProxy extends ApplicationController  {
    @Autowired
    public StageManager stageManager;

	@Autowired
	private HotelUserService hotelUserService; 
	
	@Autowired
	private AddressService addressService;
	
	@Autowired
	private PaymentService paymentService;
	
	public void save(HotelUser entity) {
		// Proxy - Validate before save - Tan Tho Nguyen
		if(validate("Email", entity.getEmail(), "[a-zA-Z0-9][a-zA-Z0-9._]*@[a-zA-Z0-9]+([.][a-zA-Z]+)+") &&
    	   validate("First Name", entity.getFirstName(), "[a-zA-Z]+") &&
    	   validate("Last Name", entity.getLastName(), "[a-zA-Z]+") && 
    	   emptyValidation("Password", entity.getPassword().isEmpty())
    	   ){ 
			
			addressService = ApplicationContextHolder.getContext().getBean(AddressService.class);
			entity.setAddress(addressService.save(entity.getAddress()));


			paymentService = ApplicationContextHolder.getContext().getBean(PaymentService.class);
			Payment payment = new Payment();
			paymentService.save(payment);
			
			entity.setPayment(payment);
			
			hotelUserService = ApplicationContextHolder.getContext().getBean(HotelUserService.class);
			hotelUserService.save(entity); 
			
			showAlert(getStringFromResourceBundle("register.successful"));
			
			stageManager = ApplicationContextHolder.getContext().getBean(StageManager.class);
			stageManager.switchScene(FxmlView.LOGIN);
		}
	}
	
}
