package edu.eci.ieti.envirify;


import com.google.gson.Gson;
import com.google.gson.JsonObject;
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
import org.json.JSONArray;
import org.json.JSONObject;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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

        CreatePlaceDTO place = new CreatePlaceDTO("Finca pepe", "Cund", "Bog", "km 1 via tabio(finca pepe)", "finca linda", "hola.png", 3, 2, 1);


        MvcResult result1 = mockMvc.perform(post("/api/v1/places").header("X-Email", email)
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

        CreatePlaceDTO place = new CreatePlaceDTO("casa colonial", "Cund", city, direction, "finca linda", "hola.png", 3, 2, 1);

        mockMvc.perform(post("/api/v1/places").header("X-Email", email)
                .contentType(MediaType.APPLICATION_JSON)
                .content(gson.toJson(place)))
                .andExpect(status().isCreated());

        MvcResult result = mockMvc.perform(post("/api/v1/places").header("X-Email", email)
                .contentType(MediaType.APPLICATION_JSON)
                .content(gson.toJson(place)))
                .andExpect(status().isConflict())
                .andDo(print())
                .andReturn();
        Assertions.assertEquals("There is already a place with the " + direction + "-" + city + "-address", result.getResponse().getContentAsString());
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
        CreatePlaceDTO place = new CreatePlaceDTO("casa colonial", "Cund", city, direction, "finca linda", "hola.png", 3, 2, 1);
        mockMvc.perform(post("/api/v1/places").header("X-Email", email)
                .contentType(MediaType.APPLICATION_JSON)
                .content(gson.toJson(place)))
                .andExpect(status().isCreated());
        place.setCity("Med");
        MvcResult result = mockMvc.perform(post("/api/v1/places").header("X-Email", email)
                .contentType(MediaType.APPLICATION_JSON)
                .content(gson.toJson(place)))
                .andExpect(status().isCreated())
                .andReturn();

        Assertions.assertEquals(201, result.getResponse().getStatus());
    }

    @Test
    void shouldNotCreateAPlaceWithoutUser() throws Exception {
        CreatePlaceDTO place = new CreatePlaceDTO("Finca pepe", "Cund", "Bog", "km 2 via tabio(finca pepe)", "finca linda", "hola.png", 3, 2, 1);
        MvcResult result1 = mockMvc.perform(post("/api/v1/places").header("X-Email", "noexiste@gmail.com")
                .contentType(MediaType.APPLICATION_JSON)
                .content(gson.toJson(place)))
                .andExpect(status().isConflict())
                .andReturn();
        String responseBody = result1.getResponse().getContentAsString();
        Assertions.assertEquals("There is no user with the email address " + "noexiste@gmail.com", responseBody);
    }

    @Test
    void shouldGetPlacesByDepartment() throws Exception {
        String email = "armando@gmail.com";
        String department = "Cundinamarca";
        CreateUserDTO user = new CreateUserDTO(email, "Armando", "12345", "Masculino", "password");
        createUser(user);
        CreatePlaceDTO place = new CreatePlaceDTO("Finca pepe", department, "Suesca", "direccion", "finca linda", "hola.png", 3, 2, 1);
        createPlace(place, email);
        place.setCity("Cogua");
        createPlace(place, email);
        MvcResult result = mockMvc.perform(get("/api/v1/places?search="+department))
                .andExpect(status().isOk())
                .andReturn();
        String bodyResult = result.getResponse().getContentAsString();
        JSONArray array = new JSONArray(bodyResult);
        CreatePlaceDTO placeDTO;
        for (int i = 0; i < array.length(); i++) {
            placeDTO = gson.fromJson(array.getJSONObject(i).toString(), CreatePlaceDTO.class);
            Assertions.assertEquals(department, placeDTO.getDepartment());
        }
    }

    @Test
    void shouldGetPlacesByCity() throws Exception {
        String email = "armando2@gmail.com";
        String search = "Cajica";
        CreateUserDTO user = new CreateUserDTO(email, "Armando", "12345", "Masculino", "password");
        createUser(user);
        CreatePlaceDTO place = new CreatePlaceDTO("Finca pepe", "Boyaca", search, "direccion1", "finca linda", "hola.png", 3, 2, 1);
        place.setOwner(email);
        createPlace(place, email);
        place.setDirection("direccion2");
        place.setDepartment(search);
        place.setCity("ciudad");
        createPlace(place, email);
        place.setDirection("direccion3");
        place.setDepartment(search);
        place.setCity(search);
        createPlace(place, email);
        MvcResult result = mockMvc.perform(get("/api/v1/places?search="+search))
                .andExpect(status().isOk())
                .andReturn();
        String bodyResult = result.getResponse().getContentAsString();
        JSONArray array = new JSONArray(bodyResult);
        CreatePlaceDTO placeDTO;
        for (int i = 0; i < array.length(); i++) {
            placeDTO = gson.fromJson(array.getJSONObject(i).toString(), CreatePlaceDTO.class);
            Assertions.assertTrue(placeDTO.getCity().equals(search) || placeDTO.getDepartment().equals(search));
        }
    }

    @Test
    void shouldNotGetNonExistentPlacesByDepartmentOrByCity() throws Exception {
        String search = "NoExiste";
        MvcResult result = mockMvc.perform(get("/api/v1/places?search="+search))
                .andExpect(status().isNotFound())
                .andReturn();
        String bodyResult = result.getResponse().getContentAsString();
        Assertions.assertEquals("There are no results for " + search, bodyResult);
    }

    @Test
    void shouldGetPlacesById() throws Exception {
        String email = "armando3@gmail.com";
        String department = "ABC";
        CreateUserDTO user = new CreateUserDTO(email, "Armando", "12345", "Masculino", "password");
        createUser(user);
        CreatePlaceDTO place = new CreatePlaceDTO("Finca pepe", department, "Suescas", "direccion4", "finca linda", "hola.png", 3, 2, 1);
        createPlace(place, email);
        MvcResult result = mockMvc.perform(get("/api/v1/places?search="+department))
                .andExpect(status().isOk())
                .andReturn();
        String bodyResult = result.getResponse().getContentAsString();
        JSONObject object = new JSONArray(bodyResult).getJSONObject(0);
        CreatePlaceDTO placeDTO = gson.fromJson(object.toString(), CreatePlaceDTO.class);
        String id = placeDTO.getId();
        result = mockMvc.perform(get("/api/v1/places/"+id))
                .andExpect(status().isOk())
                .andReturn();
        bodyResult = result.getResponse().getContentAsString();
        placeDTO = gson.fromJson(bodyResult,CreatePlaceDTO.class);
        Assertions.assertEquals(place,placeDTO);
    }

    @Test
    void shouldNotGetANonExistentPlaceById() throws Exception {
        String id = "NoExiste";
        MvcResult result = mockMvc.perform(get("/api/v1/places/"+id))
                .andExpect(status().isNotFound())
                .andReturn();
        String bodyResult = result.getResponse().getContentAsString();
        Assertions.assertEquals("There is no place with the id " + id, bodyResult);
    }

    private void createUser(CreateUserDTO userDTO) throws Exception {
        mockMvc.perform(post("/api/v1/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(gson.toJson(userDTO)))
                .andExpect(status().isCreated());
    }

    private void createPlace(CreatePlaceDTO placeDTO, String userEmail) throws Exception {
        mockMvc.perform(post("/api/v1/places").header("X-Email", userEmail)
                .contentType(MediaType.APPLICATION_JSON)
                .content(gson.toJson(placeDTO)))
                .andExpect(status().isCreated());
    }


}
