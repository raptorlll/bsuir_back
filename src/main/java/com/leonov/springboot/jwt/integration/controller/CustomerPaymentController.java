package com.leonov.springboot.jwt.integration.controller;

import com.leonov.springboot.jwt.integration.domain.Conversation;
import com.leonov.springboot.jwt.integration.domain.CustomerPayment;
import com.leonov.springboot.jwt.integration.domain.User;
import com.leonov.springboot.jwt.integration.service.ConversationService;
import com.leonov.springboot.jwt.integration.service.CrudServiceInterface;
import com.leonov.springboot.jwt.integration.service.CustomerPaymentService;
import com.stripe.Stripe;
import com.stripe.exception.*;
import com.stripe.model.Charge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/customer_payment")
public class CustomerPaymentController extends CrudAbstractAuthUser<CustomerPayment, Long> {
    @Autowired
    private CustomerPaymentService service;

    @Autowired
    private ConversationService conversationService;

    @Value("${stripe.key}")
    private String token;

    @Override
    public CrudServiceInterface<CustomerPayment, Long> getService() {
        return service;
    }

    @Override
    public Collection<CustomerPayment> getItems() {
        User userCurrent = getCurrentUser();
        boolean isConsultant = isConsultant();
        boolean isAdmin = isAdmin();
        boolean isCustomer = isCustomer();

        return super.getItems()
                .stream()
                .filter(customerPayment -> {
                    if(isAdmin){
                        return true;
                    }

                    if (isCustomer && customerPayment.getConversation().getConsultantGroupUser().getUser().getId().equals(userCurrent.getId())) {
                        return true;
                    }

                    if (isCustomer && customerPayment.getConversation().getCustomerInformation().getUser().getId().equals(userCurrent.getId())) {
                        return true;
                    }

                    return false;
                })
                .collect(Collectors.toSet());
    }

    @GetMapping(value = "/conversation/{id}")
    public Collection<CustomerPayment> getItemsForConversation(@PathVariable(value = "id") Long id) {
        User userCurrent = getCurrentUser();
        boolean isConsultant = isConsultant();
        boolean isAdmin = isAdmin();
        boolean isCustomer = isCustomer();

        Conversation conversation = conversationService.findOne(id);
        List<CustomerPayment> customerPaymentList = service.findAllByConversationIs(conversation);

        return customerPaymentList;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @PreAuthorize("hasAuthority('CUSTOMER')")
    public String setToken(@RequestBody String tokenParameter) {
        String[] parts = tokenParameter.split(":");
        String tokenString = parts[0];
        Integer val;
        Long conversationId;

        try {
            val = (Integer) Integer.parseInt(parts[1]);
        } catch (NumberFormatException e) {
            return "error";
        }
        try {
            conversationId = (Long) Long.parseLong(parts[2]);
        } catch (NumberFormatException e) {
            return "error";
        }

        Stripe.apiKey = token;
        Map<String, Object> chargeParams = new HashMap<String, Object>();
        chargeParams.put("amount", val.toString());
        chargeParams.put("currency", "usd");
        chargeParams.put("description", "Charge for olivia.taylor@example.com");
        chargeParams.put("source", tokenString);

        try {
            Charge.create(chargeParams);
        } catch (AuthenticationException e) {
            e.printStackTrace();
        } catch (InvalidRequestException e) {
            e.printStackTrace();
        } catch (APIConnectionException e) {
            e.printStackTrace();
        } catch (CardException e) {
            e.printStackTrace();
        } catch (APIException e) {
            e.printStackTrace();
        }

        CustomerPayment customerPayment = new CustomerPayment();
        customerPayment.setConversation(conversationService.findOne(conversationId));
        customerPayment.setAmount(Long.parseLong(val.toString()));
        service.save(customerPayment);

        return "ok";
    }


}
