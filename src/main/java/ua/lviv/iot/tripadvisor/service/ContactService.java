package ua.lviv.iot.tripadvisor.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.lviv.iot.tripadvisor.dal.ContactRepository;
import ua.lviv.iot.tripadvisor.domain.Contact;

@Service
public class ContactService extends AbstractService<Contact> {

	@Autowired
	public ContactService(ContactRepository repository) {
		super(repository);
	}

	@Override
	public Contact mapCsvToObject(String[] objectCsv) {
		String websiteUrl = objectCsv[1];
		Long phoneNumber = Long.parseLong(objectCsv[2]);
		String email = objectCsv[3];

		return new Contact(websiteUrl, phoneNumber, email);
	}
}
