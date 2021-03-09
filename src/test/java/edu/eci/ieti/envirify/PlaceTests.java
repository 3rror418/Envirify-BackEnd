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
import edu.eci.ieti.envirify.controllers.dtos.CreatePlaceDTO;
import edu.eci.ieti.envirify.controllers.dtos.CreateUserDTO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class PlaceTests {

    @Autowired
    private MockMvc mockMvc;

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
    void shouldCreateAPlace() throws Exception {

        String email = "daniela@gmail.com";
        CreateUserDTO user = new CreateUserDTO(email, "Daniela", "12345", "Masculino", "password");

        mockMvc.perform(post("/api/v1/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(gson.toJson(user)))
                .andExpect(status().isCreated());

        CreatePlaceDTO place = new CreatePlaceDTO("Finca pepe", "Cund","Bog","km 1 via tabio(finca pepe)","finca linda",3,2,1);



        MvcResult result1 = mockMvc.perform(post("/api/v1/places").header("X-Email",email)
                .contentType(MediaType.APPLICATION_JSON)
                .content(gson.toJson(place)))
                .andExpect(status().isCreated())
                .andReturn();

        Assertions.assertEquals(201, result1.getResponse().getStatus());
    }

    @Test
    void shouldNotAddAnExistingAPlace() throws Exception {
        String direction = "calle 1 # 12-34";
        String city = "Bog";
        
        String email = "daniela1@gmail.com";
        CreateUserDTO user = new CreateUserDTO(email, "Daniela", "12345", "Masculino", "password");

        mockMvc.perform(post("/api/v1/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(gson.toJson(user)))
                .andExpect(status().isCreated());

        CreatePlaceDTO place = new CreatePlaceDTO("casa colonial", "Cund",city,direction,"finca linda",3,2,1);

        mockMvc.perform(post("/api/v1/places").header("X-Email",email)
                .contentType(MediaType.APPLICATION_JSON)
                .content(gson.toJson(place)))
                .andExpect(status().isCreated());

        MvcResult result = mockMvc.perform(post("/api/v1/places").header("X-Email",email)
                .contentType(MediaType.APPLICATION_JSON)
                .content(gson.toJson(place)))
                .andExpect(status().isConflict())
                .andDo(print())
                .andReturn();
        Assertions.assertEquals("There is already a place with the " + direction+"-"+city + "-address", result.getResponse().getContentAsString());
    }

    @Test
    void shouldAddAnExistingADirectionDifferentCity() throws Exception {

        String email = "daniela2@gmail.com";
        CreateUserDTO user = new CreateUserDTO(email, "Daniela", "12345", "Masculino", "password");

        mockMvc.perform(post("/api/v1/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(gson.toJson(user)))
                .andExpect(status().isCreated());

        String direction = "calle 2 # 12-34";
        String city = "Bog";
        CreatePlaceDTO place = new CreatePlaceDTO("casa colonial", "Cund",city,direction,"finca linda",3,2,1);

        mockMvc.perform(post("/api/v1/places").header("X-Email",email)
                .contentType(MediaType.APPLICATION_JSON)
                .content(gson.toJson(place)))
                .andExpect(status().isCreated());

        place.setCity("Med");
        MvcResult result = mockMvc.perform(post("/api/v1/places").header("X-Email",email)
                .contentType(MediaType.APPLICATION_JSON)
                .content(gson.toJson(place)))
                .andExpect(status().isCreated())
                .andReturn();

        Assertions.assertEquals(201, result.getResponse().getStatus());
    }

    @Test
    void shouldNotCreateAPlaceWithoutUser() throws Exception {


        CreatePlaceDTO place = new CreatePlaceDTO("Finca pepe", "Cund","Bog","km 2 via tabio(finca pepe)","finca linda",3,2,1);

        MvcResult result1 = mockMvc.perform(post("/api/v1/places").header("X-Email","noexiste@gmail.com")
                .contentType(MediaType.APPLICATION_JSON)
                .content(gson.toJson(place)))
                .andExpect(status().isConflict())
                .andReturn();

        String responseBody = result1.getResponse().getContentAsString();
        Assertions.assertEquals("There is no user with the email address " + "noexiste@gmail.com", responseBody);
    }



}
