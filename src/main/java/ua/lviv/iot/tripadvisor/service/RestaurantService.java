package ua.lviv.iot.tripadvisor.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.lviv.iot.tripadvisor.dal.RestaurantRepository;
import ua.lviv.iot.tripadvisor.domain.Address;
import ua.lviv.iot.tripadvisor.domain.Contact;
import ua.lviv.iot.tripadvisor.domain.Restaurant;
import ua.lviv.iot.tripadvisor.domain.User;

@Service
public class RestaurantService extends AbstractService<Restaurant> {

	private final UserService userService;
	private final AddressService addressService;
	private final ContactService contactService;

	@Autowired
	public RestaurantService(RestaurantRepository restaurantRepository, UserService userService,
			AddressService addressService, ContactService contactService) {
		super(restaurantRepository);
		this.userService = userService;
		this.addressService = addressService;
		this.contactService = contactService;
	}

	@Override
	public Restaurant mapCsvToObject(String[] objectCsv) {
		String name = objectCsv[1];
		Float rating = Float.valueOf(objectCsv[2]);
		String description = objectCsv[3];
		Double lowerPrice = Double.valueOf(objectCsv[4]);
		Double topPrice = Double.valueOf(objectCsv[5]);
		String menuUrl = objectCsv[6];
		Address address = addressService.getById(Integer.parseInt(objectCsv[7]));
		Contact contact = contactService.getById(Integer.parseInt(objectCsv[8]));
		User owner = userService.getById(Integer.parseInt(objectCsv[9]));

		return new Restaurant(name, rating, description, lowerPrice, topPrice, menuUrl, owner, address, contact);
	}
}
