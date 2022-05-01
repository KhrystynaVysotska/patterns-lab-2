package ua.lviv.iot.tripadvisor.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.lviv.iot.tripadvisor.dal.ReviewRepository;
import ua.lviv.iot.tripadvisor.domain.Restaurant;
import ua.lviv.iot.tripadvisor.domain.Review;
import ua.lviv.iot.tripadvisor.domain.User;

@Service
public class ReviewService extends AbstractService<Review> {

	private final UserService userService;
	private final RestaurantService restaurantService;

	@Autowired
	public ReviewService(ReviewRepository reviewRepository, UserService userService,
			RestaurantService restaurantService) {
		super(reviewRepository);
		this.userService = userService;
		this.restaurantService = restaurantService;
	}

	public Review mapCsvToObject(String[] objectCsv) {
		User author = userService.getById(Integer.parseInt(objectCsv[1]));
		Restaurant restaurant = restaurantService.getById(Integer.parseInt(objectCsv[2]));
		Float rating = Float.valueOf(objectCsv[3]);
		String review = objectCsv[4];
		String status = objectCsv[5];
		LocalDate dateOfPublishing = LocalDate.parse(objectCsv[6]);

		return new Review(author, restaurant, rating, review, status, dateOfPublishing);
	}
}
