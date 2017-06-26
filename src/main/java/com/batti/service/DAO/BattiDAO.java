package com.batti.service.DAO;

import com.batti.service.model.Order;

import java.util.List;

/**
 * Created by yonzhang on 6/17/17.
 */
public interface BattiDAO {
    void createOrder(Order order) throws Exception;

}
