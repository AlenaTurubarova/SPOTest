package com.spo.services.impl;

import com.spo.model.Distribution;
import com.spo.model.Order;
import com.spo.services.DistributionService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DistributionServiceImpl implements DistributionService {

    private int minSeniorNum;

    public DistributionServiceImpl(@Value("${minSeniorNum}") int minSeniorNum) {
        this.minSeniorNum = minSeniorNum;
    }

    public List<Distribution> distribute(Order order) {
        return order.getRooms().stream()
                .map(roomLoad -> searchOptimalDistribution(order, roomLoad))
                .collect(Collectors.toList());
    }

    Distribution searchOptimalDistribution(Order order, int roomLoad) {
        int maxSeniorNum = countSeniorNumber(roomLoad, order);
        int juniorNum = 0;
        int overload = getSeniorCapacity(order);
        int currentOverload;
        int restLoad;
        Distribution distribution = new Distribution(maxSeniorNum, juniorNum);
        for (int seniorNum = maxSeniorNum; seniorNum >= minSeniorNum; seniorNum--) {
            restLoad = countRestload(roomLoad, seniorNum, order);
            juniorNum = countJuniorNumber(restLoad, order);
            currentOverload = countOverload(roomLoad, seniorNum, juniorNum, order);
            if (currentOverload < overload) {
                distribution.setSenior(seniorNum);
                distribution.setJunior(juniorNum);
                overload = currentOverload;
            }
            if (currentOverload == 0) {
                break;
            }
        }
        return distribution;
    }

    private int getSeniorCapacity(Order order) {
        return order.getSenior();
    }

    private int getJuniorCapacity(Order order) {
        return order.getJunior();
    }

    private int countSeniorNumber(int load, Order order) {
        return countCleanerNumber(load, getSeniorCapacity(order));
    }

    private int countJuniorNumber(int load, Order order) {
        return countCleanerNumber(load, getJuniorCapacity(order));
    }

    private int countCleanerNumber(int load, int capacity) {
        return (int) Math.ceil((float) load / capacity);
    }

    private int countOverload(int load, int seniorNum, int juniorNum, Order order) {
        return seniorNum * getSeniorCapacity(order) + juniorNum * getJuniorCapacity(order) - load;
    }

    private int countRestload(int load, int seniorNum, Order order) {
        int restLoad = load - seniorNum * getSeniorCapacity(order);
        return restLoad > 0 ? restLoad : 0;
    }
}
