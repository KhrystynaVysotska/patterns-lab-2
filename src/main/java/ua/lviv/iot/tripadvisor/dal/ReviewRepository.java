package ua.lviv.iot.tripadvisor.dal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ua.lviv.iot.tripadvisor.domain.Review;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer> {

}