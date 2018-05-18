package mum.asd.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mum.asd.domain.Payment;
import mum.asd.repository.PaymentRepository;
import mum.asd.service.PaymentService;

@Service
public class PaymentServiceImpl implements PaymentService {
	
	@Autowired
	private PaymentRepository paymentRepository;

	@Override
	public Payment save(Payment entity) {
		// TODO Auto-generated method stub
		return paymentRepository.save(entity);
	}

	@Override
	public Payment update(Payment entity) {
		// TODO Auto-generated method stub
		return paymentRepository.save(entity);
	}

	@Override
	public void delete(Payment entity) {
		// TODO Auto-generated method stub
		paymentRepository.delete(entity);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		paymentRepository.delete(id);
	}

	@Override
	public void deleteInBatch(List<Payment> entities) {
		// TODO Auto-generated method stub
		paymentRepository.deleteInBatch(entities);
	}

	@Override
	public Payment find(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Payment> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
}
