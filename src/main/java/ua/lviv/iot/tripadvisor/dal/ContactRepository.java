package ua.lviv.iot.tripadvisor.dal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ua.lviv.iot.tripadvisor.domain.Contact;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Integer> {

}