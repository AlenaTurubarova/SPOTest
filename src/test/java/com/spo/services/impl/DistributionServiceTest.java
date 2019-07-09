package com.spo.services.impl;

import com.spo.model.Distribution;
import com.spo.model.Order;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
public class DistributionServiceTest {

    private DistributionServiceImpl service;

    @Before
    public void initOrder(){
        service = new DistributionServiceImpl(1);
    }

    @Test
    public void shouldReturnOptimalDistribution(){
        Order order = new Order();
        order.setRooms(Arrays.asList(35));
        order.setSenior(10);
        order.setJunior(6);
        Distribution expectedDistribution = new Distribution(3, 1);

        Distribution distribution = service.searchOptimalDistribution(order, order.getRooms().get(0));
        Assert.assertEquals(expectedDistribution, distribution);
    }

    @Test
    public void shouldReturnOneSeniorIfRoomLoadLessThanCapacity(){
        Order order = new Order();
        order.setRooms(Arrays.asList(9));
        order.setSenior(13);
        order.setJunior(2);
        Distribution expectedDistribution = new Distribution(1, 0);

        Distribution distribution = service.searchOptimalDistribution(order, order.getRooms().get(0));
        Assert.assertEquals(expectedDistribution, distribution);
    }

    @Test
    public void shouldReturnThreeDistributions(){
        Order order = new Order();
        order.setRooms(Arrays.asList(27,56,18));
        order.setSenior(12);
        order.setJunior(5);

        Distribution expectedDistribution1 = new Distribution(1, 3);
        Distribution expectedDistribution2 = new Distribution(3, 4);
        Distribution expectedDistribution3 = new Distribution(1, 2);
        List<Distribution> expectedList = new ArrayList<>();
        expectedList.add(expectedDistribution1);
        expectedList.add(expectedDistribution2);
        expectedList.add(expectedDistribution3);

        List<Distribution> list = service.distribute(order);
        Assert.assertEquals(expectedList, list);
    }

}
