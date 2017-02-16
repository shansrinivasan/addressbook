package com.shan.demo

import groovyx.net.http.RESTClient
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner

/**
 * Created by ssrinivasan on 2/15/2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ApplicationTest
class AddressBookTest {
    RESTClient client = new RESTClient("http://localhost:8080")

    @Before
    def void before() {

    }


    @Test
    def void getAll() {
        def response = client.get(path: '/contacts')
        def json = response.responseData
        assert json.content.size() == 5

    }
}
