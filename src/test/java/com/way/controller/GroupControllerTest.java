package com.way.controller;

import com.way.model.Group;
import com.way.model.User;
import com.way.service.GroupService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:tests-context.xml")
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class})
public class GroupControllerTest {

	private MockMvc mockMvc;

	@Autowired
	private GroupController groupController;

	@Autowired
	private GroupService groupService;

	private User user;

	private MockHttpSession session;

	@Before
	public void setup() {
		user = new User();
		session = new MockHttpSession();
		session.setAttribute("user", user);

		mockMvc = standaloneSetup(groupController).build();
	}

	@Test
	public void getGroupsWithoutAuthentication() throws Exception {
		mockMvc.perform(get("/groups.json"))
				.andExpect(status().isUnauthorized());
	}

	@Test
	public void getEmptyGroupsList() throws Exception {
		when(groupService.findAllByUser(user)).thenReturn(new ArrayList<Group>());

		mockMvc.perform(get("/groups.json").session(session))
				.andExpect(status().isOk())
				.andExpect(content().string("{\"data\":[],\"success\":true,\"totalCount\":0}"));
	}

	@Test
	public void getGroupsListWithOneItem() throws Exception {
		List<Group> groups = new ArrayList<Group>();
		groups.add(new Group());

		when(groupService.findAllByUser(user)).thenReturn(groups);

		mockMvc.perform(get("/groups.json").session(session))
				.andExpect(status().isOk())
				.andExpect(content().string("{\"data\":[{\"name\":null,\"id\":null}],\"success\":true,\"totalCount\":1}"));
	}

	@Test
	public void getGroupsListWithFewItems() throws Exception {
		List<Group> groups = new ArrayList<Group>();
		groups.add(new Group());
		groups.add(new Group());
		groups.add(new Group());

		when(groupService.findAllByUser(user)).thenReturn(groups);

		mockMvc.perform(get("/groups.json").session(session))
				.andExpect(status().isOk())
				.andExpect(content().string("{\"data\":[{\"name\":null,\"id\":null},{\"name\":null,\"id\":null},{\"name\":null,\"id\":null}],\"success\":true,\"totalCount\":3}"));
	}

}