package com.leonov.springboot.jwt.integration.controller;

import com.leonov.springboot.jwt.integration.domain.CustomerInformation;
import com.leonov.springboot.jwt.integration.domain.User;
import com.leonov.springboot.jwt.integration.service.CrudServiceInterface;
import com.leonov.springboot.jwt.integration.service.CustomerInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.stream.Collectors;

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
    @PreAuthorize("isAuthenticated()")
    public Collection<CustomerInformation> getItems() {
        Collection<CustomerInformation> infos = super.getItems();

        Collection<CustomerInformation> info;

        if(isCustomer()){
            User user = getCurrentUser();
            info = infos.stream()
                    .filter(a -> a.getUser().getId() == user.getId())
                    .collect(Collectors.toSet());
        }else{
            info = infos;
        }

        return info;
    }

    @Override
    @PreAuthorize("hasAuthority('ADMIN_USER') or hasAuthority('CUSTOMER')")
    public CustomerInformation updateItem(@PathVariable(value = "id") Long id, @RequestBody CustomerInformation information) {
        if (isCustomer()){
            Long userId = getCurrentUserId();
            information.setUser(getCurrentUser());
        }

        return super.updateItem(id, information);
    }

    @Override
    @PreAuthorize("hasAuthority('ADMIN_USER') or hasAuthority('CUSTOMER')")
    public CustomerInformation saveClientInformation(@RequestBody CustomerInformation information) {
        if (isCustomer()){
            information.setUser(getCurrentUser());
        }

        return super.saveClientInformation(information);
    }
}
