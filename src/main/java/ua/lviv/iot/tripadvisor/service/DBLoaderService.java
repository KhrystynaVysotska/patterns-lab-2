package ua.lviv.iot.tripadvisor.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.lviv.iot.tripadvisor.dal.FileRepository;
import ua.lviv.iot.tripadvisor.domain.Address;
import ua.lviv.iot.tripadvisor.domain.Contact;
import ua.lviv.iot.tripadvisor.domain.Restaurant;
import ua.lviv.iot.tripadvisor.domain.Review;
import ua.lviv.iot.tripadvisor.domain.User;

@Service
public class DBLoaderService {

	@Autowired
	FileRepository repository;

	AddressService addressService;
	ContactService contactService;
	UserService userService;
	RestaurantService restaurantService;
	ReviewService reviewService;

	@Autowired
	public DBLoaderService(AddressService addressService, ContactService contactService, UserService userService,
			RestaurantService restaurantService, ReviewService reviewService) {
		this.addressService = addressService;
		this.contactService = contactService;
		this.userService = userService;
		this.restaurantService = restaurantService;
		this.reviewService = reviewService;
	}

	public void dumpCsvToDB(String filepath) {
		List<String[]> data = repository.readAll(filepath);
		data.forEach(entry -> {
			if (entry[0].equals("ADDRESS")) {
				Address address = addressService.mapCsvToObject(entry);
				addressService.saveToDatabase(address);
			} else if (entry[0].equals("CONTACT")) {
				Contact contact = contactService.mapCsvToObject(entry);
				contactService.saveToDatabase(contact);
			} else if (entry[0].equals("USER")) {
				User user = userService.mapCsvToObject(entry);
				userService.saveToDatabase(user);
			} else if (entry[0].equals("RESTAURANT")) {
				Restaurant restaurant = restaurantService.mapCsvToObject(entry);
				restaurantService.saveToDatabase(restaurant);
			} else if (entry[0].equals("REVIEW")) {
				Review review = reviewService.mapCsvToObject(entry);
				reviewService.saveToDatabase(review);
			}
		});
	}
}
