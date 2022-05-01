package ua.lviv.iot.tripadvisor.presentation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ua.lviv.iot.tripadvisor.domain.Contact;
import ua.lviv.iot.tripadvisor.service.ContactService;

@RequestMapping("/contact")
@RestController
public class ContactController extends AbstractController<Contact> {

	private final ContactService contactService;

	@Autowired
	public ContactController(ContactService contactService) {
		super(contactService);
		this.contactService = contactService;
	}

	@PutMapping(path = "/{id}")
	public ResponseEntity<Contact> updateContact(@PathVariable("id") Integer contactId, @RequestBody Contact contact) {
		contact.setId(contactId);
		Contact updatedContact = contactService.update(contactId, contact, new Contact());
		if (updatedContact != null) {
			return new ResponseEntity<Contact>(updatedContact, HttpStatus.OK);
		} else {
			return new ResponseEntity<Contact>(HttpStatus.NOT_FOUND);
		}
	}
}
