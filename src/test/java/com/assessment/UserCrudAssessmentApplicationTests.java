package com.assessment;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.client.HttpClientErrorException;

import com.assessment.model.Role;
import com.assessment.model.User;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
				classes = UserCrudAssessmentApplicationTests.class)
@EnableAutoConfiguration
public class UserCrudAssessmentApplicationTests {

	@Autowired
	private TestRestTemplate restTemplate;
	
    @LocalServerPort
    private int port;
	
    private String getRootUrl() {
        return "http://localhost:" + port;
    }
    
	@Test
	public void contextLoads() {
		
	}

    @Test
    public void testGetUserById() {
        User user = restTemplate.getForObject(getRootUrl() + "/users/2", User.class);
        System.out.println(user.getFirstName());
        assertNotNull(user);
    }
    
    @Test
    public void testCreatUser() {
    	
        User user = new User();
        user.setEmail("admin@gmail.com");
        user.setFirstName("admin");
        user.setLastName("admin");
        Role role = new Role();
        role.setDescription("Admin Role");
        role.setName("ADMIN");
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        user.setRoles(roles);
        ResponseEntity<User> postResponse = restTemplate.postForEntity(getRootUrl() + "/signup", user, User.class);
        assertNotNull(postResponse);
        assertNotNull(postResponse.getBody());
    }
    
	@Test
	public void testUpdateUser() {
		
		int id = 1;
		User user = restTemplate.getForObject(getRootUrl() + "/user/" + id, User.class);
		user.setFirstName("admin1");
		user.setLastName("admin2");

		restTemplate.put(getRootUrl() + "/user/" + id, user);

		User updatedUser = restTemplate.getForObject(getRootUrl() + "/user/" + id, User.class);
		assertNotNull(updatedUser);
	}

	@Test
	public void testDeleteUser() {
		
		int id = 2;
		User user = restTemplate.getForObject(getRootUrl() + "/user/" + id, User.class);
		assertNotNull(user);

		restTemplate.delete(getRootUrl() + "/user/" + id);

		try {
			user = restTemplate.getForObject(getRootUrl() + "/user/" + id, User.class);
		} catch (final HttpClientErrorException e) {
			assertEquals(e.getStatusCode(), HttpStatus.NOT_FOUND);
		}
	}
}
