package com.spo.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spo.model.Distribution;
import com.spo.model.Order;
import com.spo.services.DistributionService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.core.StringContains.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(DistributionController.class)
public class DistributionControllerIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private DistributionService service;

    @Test
    public void shouldReturnSuccessStatus() throws Exception {
        Distribution expectedDistribution1 = new Distribution(3, 1);
        Distribution expectedDistribution2 = new Distribution(1, 2);
        List<Distribution> expectedList = new ArrayList<>();
        expectedList.add(expectedDistribution1);
        expectedList.add(expectedDistribution2);

        Order order = new Order();
        order.setRooms(Arrays.asList(35, 21));
        order.setSenior(10);
        order.setJunior(6);

        when(service.distribute(any())).thenReturn(expectedList);
        mvc.perform(post("/distribute").contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(order)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    public void shouldReturnBadRequestStatus() throws Exception {
        Order order = new Order();
        order.setRooms(Arrays.asList(135, 21));
        order.setSenior(10);
        order.setJunior(6);

        mvc.perform(post("/distribute").contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(order)))
                .andExpect(status().isBadRequest())
        .andExpect(content().string(containsString("Room load should be less than 100")));
    }

}
