package edu.eci.ieti.envirify;

import com.google.gson.Gson;
import com.mongodb.client.MongoClients;
import de.flapdoodle.embed.mongo.MongodExecutable;
import de.flapdoodle.embed.mongo.MongodStarter;
import de.flapdoodle.embed.mongo.config.IMongodConfig;
import de.flapdoodle.embed.mongo.config.MongodConfigBuilder;
import de.flapdoodle.embed.mongo.config.Net;
import de.flapdoodle.embed.mongo.distribution.Version;
import de.flapdoodle.embed.process.runtime.Network;
import edu.eci.ieti.envirify.controllers.dtos.CreateUserDTO;
import edu.eci.ieti.envirify.controllers.dtos.LoginDTO;
import edu.eci.ieti.envirify.controllers.dtos.UserDTO;
import edu.eci.ieti.envirify.security.userdetails.UserDetailsImpl;
import edu.eci.ieti.envirify.security.userdetails.UserDetailsServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class UserTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    private static final Gson gson = new Gson();

    private static final String CONNECTION_STRING = "mongodb://%s:%d";

    private MongodExecutable mongodExecutable;
    private MongoTemplate mongoTemplate;

    @AfterEach
    void clean() {
        mongodExecutable.stop();
    }

    @BeforeEach
    void setup() throws Exception {
        String ip = "localhost";
        int port = 27017;
        IMongodConfig mongodConfig = new MongodConfigBuilder().version(Version.Main.PRODUCTION)
                .net(new Net(ip, port, Network.localhostIsIPv6()))
                .build();
        MongodStarter starter = MongodStarter.getDefaultInstance();
        mongodExecutable = starter.prepare(mongodConfig);
        mongodExecutable.start();
        mongoTemplate = new MongoTemplate(MongoClients.create(String.format(CONNECTION_STRING, ip, port)), "test");
    }

    @Test
    void shouldLogin(){
        CreateUserDTO user = new CreateUserDTO("nicolas@gmail.com", "Nicolas", "12345", "male", "password");
        LoginDTO loginDTO = new LoginDTO("nicolas@gmail.com",  "password");
        MvcResult result = null;
        try {
            mockMvc.perform(post("/api/v1/users")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(gson.toJson(user)))
                    .andExpect(status().isCreated())
                    .andReturn();
            result = mockMvc.perform(post("/api/v1/users/login")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(gson.toJson(loginDTO)))
                    .andExpect(status().isAccepted())
                    .andReturn();
            Assertions.assertEquals(202, result.getResponse().getStatus());
        } catch (Exception e) {
            Assertions.assertTrue(false);
        }
    }

    @Test
    void shouldNotDoLogin(){
        LoginDTO loginDTO = new LoginDTO("nonexistentuser@gmail.com",  "password");
        MvcResult result = null;
        try {
            result = mockMvc.perform(post("/api/v1/users/login")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(gson.toJson(loginDTO)))
                    .andExpect(status().isUnauthorized())
                    .andReturn();
            Assertions.assertEquals(401, result.getResponse().getStatus());
        } catch (Exception e) {
            Assertions.assertTrue(false);
        }
    }


    @Test
    void shouldGetUserDetails() throws Exception {
        String id = "1";
        String username = "nicolas9906";
        String email = "nicolas@gmail.com";
        String password = "nicolas123";
        UserDetailsImpl userDetails = new UserDetailsImpl(id,username,email,password);
        Assertions.assertEquals(id,userDetails.getId());
        Assertions.assertEquals(username,userDetails.getUsername());
        Assertions.assertEquals(email,userDetails.getEmail());
        Assertions.assertEquals(password,userDetails.getPassword());
        Assertions.assertEquals(0,userDetails.getAuthorities().size());
        Assertions.assertEquals(true,userDetails.isAccountNonExpired());
        Assertions.assertEquals(true,userDetails.isAccountNonLocked());
        Assertions.assertEquals(true,userDetails.isCredentialsNonExpired());
        Assertions.assertEquals(true,userDetails.isEnabled());
    }

    @Test
    void shouldBuildUserDetails() throws Exception {
        CreateUserDTO user = new CreateUserDTO("andres@gmail.com", "Andres", "12345", "Masculino", "andres123");
        MvcResult result = mockMvc.perform(post("/api/v1/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(gson.toJson(user)))
                .andExpect(status().isCreated())
                .andReturn();
        UserDetails userDetails = userDetailsService.loadUserByUsername("andres@gmail.com");
        Assertions.assertEquals(user.getName(),userDetails.getUsername());
        Assertions.assertEquals(0,userDetails.getAuthorities().size());
        Assertions.assertEquals(true,userDetails.isAccountNonExpired());
        Assertions.assertEquals(true,userDetails.isAccountNonLocked());
        Assertions.assertEquals(true,userDetails.isCredentialsNonExpired());
        Assertions.assertEquals(true,userDetails.isEnabled());
    }

    @Test
    void shouldNotBuildUserDetails() throws Exception {
        try {
            UserDetails userDetails = userDetailsService.loadUserByUsername("carlos@gmail.com");
        }catch (UsernameNotFoundException e){
            Assertions.assertTrue(true);
        }
    }



    @Test
    void shouldCreateAUser() throws Exception {
        CreateUserDTO user = new CreateUserDTO("daniel@gmail.com", "Daniel", "12345", "Masculino", "password");
        MvcResult result = mockMvc.perform(post("/api/v1/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(gson.toJson(user)))
                .andExpect(status().isCreated())
                .andReturn();
        Assertions.assertEquals(201, result.getResponse().getStatus());
    }

    @Test
    void shouldNotAddAnExistingAUser() throws Exception {
        String email = "daniel2@gmail.com";
        CreateUserDTO user = new CreateUserDTO(email, "Daniel", "12345", "Masculino", "password");
        mockMvc.perform(post("/api/v1/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(gson.toJson(user)))
                .andExpect(status().isCreated());
        MvcResult result = mockMvc.perform(post("/api/v1/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(gson.toJson(user)))
                .andExpect(status().isConflict())
                .andReturn();
        Assertions.assertEquals("There is already a user with the " + email + " email address", result.getResponse().getContentAsString());
    }

    @Test
    void shouldGetAUserByEmail() throws Exception {
        String email = "daniel3@gmail.com";
        CreateUserDTO user = new CreateUserDTO(email, "Daniel", "12345", "Masculino", "password");
        mockMvc.perform(post("/api/v1/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(gson.toJson(user)))
                .andExpect(status().isCreated());
        MvcResult result = mockMvc.perform(get("/api/v1/users/" + email))
                .andExpect(status().isAccepted())
                .andReturn();
        String responseBody = result.getResponse().getContentAsString();
        UserDTO returnedUser = gson.fromJson(responseBody, UserDTO.class);
        Assertions.assertEquals(user.getEmail(), returnedUser.getEmail());
        Assertions.assertEquals(user.getName(), returnedUser.getName());
        Assertions.assertEquals(user.getPhoneNumber(), returnedUser.getPhoneNumber());
        Assertions.assertEquals(user.getGender(), returnedUser.getGender());
    }

    @Test
    void shouldNotGetANonExistentUser() throws Exception {
        String email = "noexiste@gmail.com";
        MvcResult result = mockMvc.perform(get("/api/v1/users/" + email))
                .andExpect(status().isNotFound())
                .andReturn();
        String responseBody = result.getResponse().getContentAsString();
        Assertions.assertEquals("There is no user with the email address " + email, responseBody);
    }

}
