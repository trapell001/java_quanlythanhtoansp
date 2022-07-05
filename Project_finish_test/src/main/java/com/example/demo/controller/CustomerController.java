package com.example.demo.controller;

import com.example.demo.dto.CustomerDTO;
import com.example.demo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;

    }
    @GetMapping("/")
    public ResponseEntity<Page<CustomerDTO>> findAll(@RequestParam int index , @RequestParam int size){
        PageRequest pageRequest = PageRequest.of(index,size);
        Page<CustomerDTO> customerDTOs = customerService.findAll(pageRequest);
        return new ResponseEntity<>(customerDTOs,HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<CustomerDTO> findById(@PathVariable Long id){
        CustomerDTO customerDTO = customerService.findCustomerById(id);
        return new ResponseEntity<>(customerDTO,HttpStatus.OK);
    }
    @PostMapping("/")
    public ResponseEntity<CustomerDTO> createCustomer(@RequestBody CustomerDTO customerDTO){
        customerService.createCustomer(customerDTO);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<CustomerDTO> updateCustomer(@PathVariable Long id,@RequestBody CustomerDTO customerDTO){
        customerService.updateCustomerById(id,customerDTO);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
    @DeleteMapping("{id}")
    public ResponseEntity deleteCustomer(@PathVariable Long id){
        customerService.deleteCustomerById(id);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }
}
