package mum.asd.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import mum.asd.domain.Booking;
import mum.asd.repository.BookingRepository;
import mum.asd.service.BookingService;

public class BookingServiceImpl implements BookingService {
	
	@Autowired
	private BookingRepository bookingRepository;

	@Override
	public Booking save(Booking entity) {
		// TODO Auto-generated method stub
		return bookingRepository.save(entity);
	}

	@Override
	public Booking update(Booking entity) {
		// TODO Auto-generated method stub
		return bookingRepository.save(entity);
	}

	@Override
	public void delete(Booking entity) {
		// TODO Auto-generated method stub
		bookingRepository.delete(entity);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		bookingRepository.delete(id);
	}

	@Override
	public void deleteInBatch(List<Booking> entities) {
		// TODO Auto-generated method stub
		bookingRepository.deleteInBatch(entities);
	}

	@Override
	public Booking find(Long id) {
		// TODO Auto-generated method stub
		return bookingRepository.findOne(id);
	}

	@Override
	public List<Booking> findAll() {
		// TODO Auto-generated method stub
		return bookingRepository.findAll();
	}

}
