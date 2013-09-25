package com.way.controller;

import com.way.model.Group;
import com.way.model.User;
import com.way.service.GroupService;
import com.way.viewmodel.GroupDto;
import com.way.viewmodel.ListResponse;
import com.way.viewmodel.SingleResponse;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class GroupController {

    @Autowired
    private GroupService service;

    @RequestMapping(value = "/groups/{groupId}", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public SingleResponse<GroupDto> getFact(@PathVariable Long groupId) throws IOException {
        System.out.println("Group retrieved.");
        Group group = service.findOne(groupId);
        return new SingleResponse<GroupDto>(true, new GroupDto(group));
    }

    @RequestMapping(value = "/groups", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public ListResponse<GroupDto> getGroups(HttpServletResponse response) throws IOException {
        System.out.println("Group search.");

        if (getUser() == null) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return null;
        } else {
            List<Group> groups = service.findAllByUser(getUser());

            List<GroupDto> dtos = new ArrayList<GroupDto>();
            for (Group group : groups) {
                dtos.add(new GroupDto(group));
            }

            return new ListResponse<GroupDto>(true, dtos, groups.size());
        }
    }

    private User getUser() {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = attr.getRequest().getSession();

        return (User) session.getAttribute("user");
    }

    @RequestMapping(value = "/groups", method = RequestMethod.POST, produces = "application/json", consumes = "application/json", headers = "Accept=application/json")
    @ResponseBody
    public SingleResponse<GroupDto> createGroup(@RequestBody Group group) throws IOException {
        System.out.println("Group created.");
        group.setUser(getUser());
        group = service.save(group);
        return new SingleResponse<GroupDto>(true, new GroupDto(group));
    }

    @RequestMapping(value = "/groups/{groupId}", method = RequestMethod.PUT, produces = "application/json", consumes = "application/json")
    @ResponseBody
    public SingleResponse<GroupDto> updateGroup(@RequestBody Group group) throws IOException {
        System.out.println("Group updated.");
        group.setUser(getUser());
        group = service.save(group);
        return new SingleResponse<GroupDto>(true, new GroupDto(group));
    }

    @RequestMapping(value = "/groups/{id}", method = RequestMethod.DELETE, headers = "Accept=application/json")
    public ResponseEntity<String> deleteGroup(@PathVariable("id") Long id) {
        Group group = service.findOne(id);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        if (group == null) {
            return new ResponseEntity<String>(headers, HttpStatus.NOT_FOUND);
        }
        service.delete(id);
        return new ResponseEntity<String>(headers, HttpStatus.OK);
    }
}
