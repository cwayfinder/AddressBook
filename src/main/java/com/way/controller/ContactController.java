package com.way.controller;

import com.way.model.Contact;
import com.way.service.ContactService;
import com.way.service.GroupService;
import com.way.viewmodel.ContactDto;
import com.way.viewmodel.ListResponse;
import com.way.viewmodel.SingleResponse;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class ContactController {

    @Autowired
    private ContactService service;

    @Autowired
    private GroupService groupService;

    @RequestMapping(value = "/contacts/{contactId}", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public SingleResponse<ContactDto> getFact(@PathVariable Long contactId) throws IOException {
        System.out.println("Contact retrieved.");
        Contact contact = service.findOne(contactId);
        return new SingleResponse<ContactDto>(true, new ContactDto(contact));
    }

    @RequestMapping(value="/groups/{groupId}/contacts", method= RequestMethod.GET, produces="application/json")
    @ResponseBody
    public ListResponse<ContactDto> getFacts(@PathVariable Long groupId, HttpServletRequest request) throws IOException {
        System.out.println("Contact search.");

        int page = Integer.parseInt(request.getParameter("page")) - 1;
        int pageSize = Integer.parseInt(request.getParameter("limit"));

        Pageable pageable = new PageRequest(page, pageSize);

        Page<Contact> contactPage = service.findAllByGroupId(groupId, pageable);

        List<ContactDto> dtos = new ArrayList<ContactDto>();
        for (Contact contact : contactPage) {
            dtos.add(new ContactDto(contact));
        }

        return new ListResponse<ContactDto>(true, dtos, contactPage.getTotalElements());
    }

    @RequestMapping(value = "/contacts", method = RequestMethod.POST, produces = "application/json", consumes = "application/json", headers = "Accept=application/json")
    @ResponseBody
    public SingleResponse<ContactDto> createQuestion(@RequestBody String json) throws IOException {
        System.out.println("Contact created.");

        ObjectMapper mapper = new ObjectMapper();
        ContactDto dto = mapper.readValue(json, ContactDto.class);

        Contact contact = new Contact();
        contact.setName(dto.getName());
        contact.setEmail(dto.getEmail());
        contact.setPhone(dto.getPhone());
        contact.setGroup(groupService.findOne(dto.getGroupId()));
        contact = service.save(contact);

        return new SingleResponse<ContactDto>(true, new ContactDto(contact));
    }

    @RequestMapping(value = "/contacts/{contactId}", method = RequestMethod.PUT, produces = "application/json", consumes = "application/json")
    @ResponseBody
    public SingleResponse<ContactDto> updateQuestion(@PathVariable Long contactId, @RequestBody String json) throws IOException {
        System.out.println("Contact updated.");

		ObjectMapper mapper = new ObjectMapper();
		ContactDto dto = mapper.readValue(json, ContactDto.class);

        Contact contact = new Contact();
        contact.setId(dto.getId());
        contact.setGroup(groupService.findOne(dto.getGroupId()));
        contact.setName(dto.getName());
        contact.setEmail(dto.getEmail());
        contact.setPhone(dto.getPhone());

        contact = service.save(contact);
        return new SingleResponse<ContactDto>(true, new ContactDto(contact));
    }

    @RequestMapping(value = "/contacts/{id}", method = RequestMethod.DELETE, headers = "Accept=application/json")
    public ResponseEntity<String> deleteQuestion(@PathVariable("id") Long id) {
        Contact base = service.findOne(id);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        if (base == null) {
            return new ResponseEntity<String>(headers, HttpStatus.NOT_FOUND);
        }

        service.delete(id);
        return new ResponseEntity<String>(headers, HttpStatus.OK);
    }
}
