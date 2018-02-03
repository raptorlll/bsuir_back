package com.nouhoun.springboot.jwt.integration.controller;

import com.nouhoun.springboot.jwt.integration.domain.CustomerInformation;
import com.nouhoun.springboot.jwt.integration.domain.User;
import com.nouhoun.springboot.jwt.integration.service.CrudServiceInterface;
import com.nouhoun.springboot.jwt.integration.service.CustomerInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer_information")
public class    CustomerInformationController extends CrudAbstractAuthUser<CustomerInformation, Long> {
    @Autowired
    private CustomerInformationService customerInformationService;

    @Override
    public CrudServiceInterface<CustomerInformation, Long> getService() {
        return customerInformationService;
    }

    @Override
//    @PreAuthorize("hasAuthority('ADMIN_USER') or hasAuthority('CUSTOMER')")
    public CustomerInformation updateItem(@PathVariable(value = "id") Long id, @RequestBody CustomerInformation information) {
        Long userId = getCurrentUserId();
        information.setUser(getCurrentUser());
        return super.updateItem(id, information);
    }
}
