package com.spo.services;

import com.spo.model.Distribution;
import com.spo.model.Order;

import java.util.List;

public interface DistributionService{

    List<Distribution> distribute(Order order);
}
