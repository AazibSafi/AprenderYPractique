package test;

import org.junit.Test;

import java.util.List;
import java.util.Objects;

public class TestClass {

    @Test
    public void test() {
    }

    List<String> toUpper(List<String> list) {
        return list.stream().filter(Objects::nonNull).map(String::toUpperCase).toList();
    }

}
/*
    Events
    State
    Source target

    State Machine - Status

    builder
    Map of All State Machine

    Factory Pattern
    Status: PICKUP
*/
/*
    Amazon checkout system

    list of product
    client choose product

    APIs
    1- Fetch list of products   (Already available)

    Product:
        - insurance details
        - delivery methods
        - Fulfilment Providers
        - Status (IN_PROGRESS, PICKED_UP, DELIVERED, CANCELLED)

        product codes: UPC, SKU

    - No DB
    - Backend System (Third party service)

    Build Flow.

    Solution;
        - Our Service calls to Backend Service
        - return Product details
        - product will have the status

        Each Action
            - Processing

     Observability
      - SLf4J logs -> GCP - Logs explorer, Splunk
      - metrics - statistics - DataDog, Grafana
      - Database portal: Tables, Collections will be queried
      - Health Check

      retry mechanism

      - insurance

 */