package edu.miu.seniorproject.eBicycleRental.serviceimplementation;
import edu.miu.seniorproject.eBicycleRental.model.Booking;
import edu.miu.seniorproject.eBicycleRental.model.Category;
import edu.miu.seniorproject.eBicycleRental.model.Vehicle;
import edu.miu.seniorproject.eBicycleRental.repository.IBookingRepository;
import edu.miu.seniorproject.eBicycleRental.repository.IVehicleRepository;
import edu.miu.seniorproject.eBicycleRental.service.SearchService;

import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service("searchService")
public class SearchServiceImp implements SearchService {
	
	private IVehicleRepository vehicleRepository;

	private IBookingRepository bookingRepository;
	
	public SearchServiceImp(IVehicleRepository vehicleRepository, IBookingRepository bookingRepository) {
		this.vehicleRepository = vehicleRepository;
		this.bookingRepository = bookingRepository;
	}
	
	@Override
	public List<Vehicle> getAvailableVehicles(LocalDate start, LocalDate end){
		List<Vehicle> bookedVehicles = bookingRepository.findAll().stream()
				.filter(b -> (b.getStartDate().isBefore(start) && b.getEndDate().isAfter(start))
								|| b.getStartDate().isAfter(start) && b.getStartDate().isBefore(end) )
				.map(Booking::getVehicle)
				.collect(Collectors.toList());		
		return vehicleRepository.findAll().stream()
				  				.filter(v -> !bookedVehicles.contains(v))
				  				.collect(Collectors.toList()); 
	}




	@Override
	public List<Category> findAvailableCategories(LocalDate start, LocalDate end) {
		return getAvailableVehicles(start, end).stream()
											   .map(v -> v.getCategory())
											   .distinct()
											   .collect(Collectors.toList());
	}

}
