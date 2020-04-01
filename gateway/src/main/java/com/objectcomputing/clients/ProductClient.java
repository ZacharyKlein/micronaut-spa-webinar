package com.objectcomputing.clients;

import com.objectcomputing.ProductApi;
import io.micronaut.http.client.annotation.Client;


@Client(id = "inventory", path = "/product")
public interface ProductClient extends ProductApi {}
