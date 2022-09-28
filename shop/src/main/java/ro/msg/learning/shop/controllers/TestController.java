package ro.msg.learning.shop.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.msg.learning.shop.services.*;
import ro.msg.learning.shop.utils.LocalDB;

@RestController
@RequiredArgsConstructor
@RequestMapping("/test")
@Profile("test")
public class TestController {
    @Autowired
    private final StockService stockService;
    @Autowired
    private final SupplierService supplierService;
    @Autowired
    private final ProductCategoryService productCategoryService;
    @Autowired
    private final CustomerService customerService;
    @Autowired
    private final ProductService productService;
    @Autowired
    private final LocationService locationService;

    @PostMapping(value = "/populate", produces = {"application/json"})
    public void populate() {
        LocalDB localDB = new LocalDB(stockService, supplierService, productCategoryService, customerService, productService, locationService);
        localDB.populate();
    }

    @DeleteMapping(value = "/clear", produces = {"application/json"})
    public void clear() {
        LocalDB localDB = new LocalDB(stockService, supplierService, productCategoryService, customerService, productService, locationService);
        localDB.clear();
    }
}
