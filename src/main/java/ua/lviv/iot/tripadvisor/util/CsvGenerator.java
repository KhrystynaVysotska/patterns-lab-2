package ua.lviv.iot.tripadvisor.util;

import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import org.jeasy.random.EasyRandom;

import com.opencsv.CSVWriter;

import ua.lviv.iot.tripadvisor.domain.Address;
import ua.lviv.iot.tripadvisor.domain.Contact;
import ua.lviv.iot.tripadvisor.domain.Restaurant;
import ua.lviv.iot.tripadvisor.domain.Review;
import ua.lviv.iot.tripadvisor.domain.User;

public class CsvGenerator {

	public static void main(String[] args) throws IOException {
		List<String[]> data = new LinkedList<>();

		generateAddressData(data);
		generateContactData(data);
		generateUserData(data);
		generateRestaurantData(data);
		generateReviewData(data);

		try (CSVWriter writer = new CSVWriter(new FileWriter("data.csv"))) {
			writer.writeAll(data);
		}
	}

	private static void generateAddressData(List<String[]> data) {
		EasyRandom generator = new EasyRandom();
		List<Address> addresses = generator.objects(Address.class, 200).collect(Collectors.toList());

		for (Address address : addresses) {
			data.add(address.toCsvFormat());
		}
	}

	private static void generateContactData(List<String[]> data) {
		EasyRandom generator = new EasyRandom();
		List<Contact> contacts = generator.objects(Contact.class, 200).collect(Collectors.toList());

		for (Contact contact : contacts) {
			data.add(contact.toCsvFormat());
		}
	}

	private static void generateUserData(List<String[]> data) {
		EasyRandom generator = new EasyRandom();
		List<User> users = generator.objects(User.class, 200).collect(Collectors.toList());

		for (User user : users) {
			Integer addressId = Math.abs(user.getAddress().getId() % 200);
			if (addressId == 0) addressId++;
			
			user.getAddress().setId(addressId);
			data.add(user.toCsvFormat());
		}
	}

	private static void generateRestaurantData(List<String[]> data) {
		EasyRandom generator = new EasyRandom();
		List<Restaurant> restaurants = generator.objects(Restaurant.class, 200).collect(Collectors.toList());

		for (Restaurant restaurant : restaurants) {
			Integer addressId = Math.abs(restaurant.getAddress().getId() % 200);
			Integer contactId = Math.abs(restaurant.getContact().getId() % 200);
			Integer ownerId = Math.abs(restaurant.getOwner().getId() % 200);
			if (addressId == 0) addressId++;
			if (contactId == 0) contactId++;
			if (ownerId == 0) ownerId++;
			
			restaurant.getAddress().setId(addressId);
			restaurant.getContact().setId(contactId);
			restaurant.getOwner().setId(ownerId);
			
			data.add(restaurant.toCsvFormat());
		}
	}

	private static void generateReviewData(List<String[]> data) {
		EasyRandom generator = new EasyRandom();
		List<Review> reviews = generator.objects(Review.class, 200).collect(Collectors.toList());

		for (Review review : reviews) {
			Integer authorId = Math.abs(review.getAuthor().getId() % 200);
			Integer restaurantId = Math.abs(review.getRestaurant().getId() % 200);
			if (authorId == 0) authorId++;
			if (restaurantId == 0) restaurantId++;
			
			review.getAuthor().setId(authorId);
			review.getRestaurant().setId(restaurantId);
			
			data.add(review.toCsvFormat());
		}
	}
}
