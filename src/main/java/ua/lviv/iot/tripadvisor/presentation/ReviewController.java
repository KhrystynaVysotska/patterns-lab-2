package ua.lviv.iot.tripadvisor.presentation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ua.lviv.iot.tripadvisor.domain.Review;
import ua.lviv.iot.tripadvisor.service.ReviewService;

@RequestMapping("/review")
@RestController
public class ReviewController extends AbstractController<Review> {

	private final ReviewService reviewService;

	@Autowired
	public ReviewController(ReviewService reviewService) {
		super(reviewService);
		this.reviewService = reviewService;
	}

	@PutMapping(path = "/{id}")
	public ResponseEntity<Review> updateReview(@PathVariable("id") Integer reviewId, @RequestBody Review review) {
		review.setId(reviewId);
		Review updatedReview = reviewService.update(reviewId, review, new Review());
		if (updatedReview != null) {
			return new ResponseEntity<Review>(updatedReview, HttpStatus.OK);
		} else {
			return new ResponseEntity<Review>(HttpStatus.NOT_FOUND);
		}
	}
}
