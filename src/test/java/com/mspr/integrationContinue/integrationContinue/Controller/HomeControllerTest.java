package com.mspr.integrationContinue.integrationContinue.Controller;

import static org.assertj.core.api.Assertions.assertThat;

import com.mspr.integrationContinue.integrationContinue.Repository.ProduitRepository;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

class HomeControllerTest {

    @Autowired
    private HomeController controller;



    @Test
    public void contexLoads() throws Exception {
        assertThat(controller).isNotNull();
    }

}