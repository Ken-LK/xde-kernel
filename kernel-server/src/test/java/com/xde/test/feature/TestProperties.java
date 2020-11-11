package com.xde.test.feature;

import com.xde.kernel.XdeKernelApplication;
import com.xde.kernel.config.Animal;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author <a href="mailto:liukailk.ken@gmail.com"> Ken </a>
 * @date 2020/10/29 9:10 上午
 **/
@SpringBootTest(classes = XdeKernelApplication.class)
public class TestProperties {


   @Autowired
    private Animal animal;



    @Test
    public void testList(){

        System.out.println(animal.getList().size());

    }







}






