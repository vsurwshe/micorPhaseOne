package org.vany.repository;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

/**
 * Hello world!
 *
 */
//@EnableAutoConfiguration
//@SpringBootApplication
@EntityScan("org.domain.model")
public class Respository 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
    }
}
