package com.leonov.springboot.jwt.integration.controller;

import com.leonov.springboot.jwt.integration.JsonModels.SummaryPayments;
import com.leonov.springboot.jwt.integration.JsonModels.ConversationCalculator;
import com.leonov.springboot.jwt.integration.domain.ConsultantGroup;
import com.leonov.springboot.jwt.integration.domain.CustomerPayment;
import com.leonov.springboot.jwt.integration.domain.User;
import com.leonov.springboot.jwt.integration.service.ConsultantGroupService;
import com.leonov.springboot.jwt.integration.service.CrudServiceInterface;
import com.leonov.springboot.jwt.integration.service.CustomerPaymentService;
import com.leonov.springboot.jwt.integration.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping("/summary_payments")
public class SummaryPaymentsController extends CrudAbstractAuthUser<ConsultantGroup, Long> {
    @Autowired
    private ConsultantGroupService service;

    @Autowired
    private CustomerPaymentService customerPaymentService;

    @Autowired
    private EmailService emailService;

    @Override
    public CrudServiceInterface<ConsultantGroup, Long> getService() {
        return service;
    }

    @RequestMapping(value = "/send", method = RequestMethod.POST)
    public Collection<SummaryPayments> sendReports() {
        List<SummaryPayments> consultantPayments = getSummaryPayments();

        StringBuffer stringBuffer = new StringBuffer();

        for(SummaryPayments summaryPayments: consultantPayments){
            stringBuffer
                    .append("Message count : " + summaryPayments.getMessageCount() + " | ")
                    .append("Payments count : " + summaryPayments.getPaymentsCount() + " | ")
                    .append("Summary payments : " + summaryPayments.getSum() + " USD cents | ")
                    .append("Date : " + summaryPayments.getDate().toString() + " \n");
        }

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("data", stringBuffer.toString());

        emailService.setContent("report", parameters)
                .sendSimpleMessage(getCurrentUser().getEmail(), "Report!");

        return  consultantPayments;
    }

    @RequestMapping(value = "/reports", method = RequestMethod.GET)
    public Collection<SummaryPayments> getReports() {
        List<SummaryPayments> consultantPayments = getSummaryPayments();

        return  consultantPayments;
    }

    private List<SummaryPayments> getSummaryPayments() {
        User userCurrent = getCurrentUser();
        boolean isConsultant = isConsultant();
        boolean isAdmin = isAdmin();
        boolean isCustomer = isCustomer();

        List<CustomerPayment> customerPaymentList  = customerPaymentService.findAll();
        List<SummaryPayments> consultantPayments  = new ArrayList<>();

        for(CustomerPayment cpl : customerPaymentList){
            if(isConsultant && !cpl.getConversation().getConsultantGroupUser().getUser().getId().equals(userCurrent.getId())){
                break;
            }

            if(isCustomer && !cpl.getConversation().getCustomerInformation().getUser().getId().equals(userCurrent.getId())){
                break;
            }

            ConversationCalculator conversationCalculator = new ConversationCalculator(cpl.getConversation());

            boolean founded = false;
            for (SummaryPayments consultantPayments1 : consultantPayments){
                if(consultantPayments1.getDate().getDay() == cpl.getDataTime().getDay()
                        && consultantPayments1.getDate().getMonth() == cpl.getDataTime().getMonth()
                        ){
                    consultantPayments1.setMessageCount(consultantPayments1.getMessageCount() + cpl.getConversation().getConversationMessages().size());
                    consultantPayments1.setPaymentsCount(consultantPayments1.getPaymentsCount() + 1);
                    consultantPayments1.setSum(consultantPayments1.getSum() + conversationCalculator.getSpentMoney());
                    founded = true;
                }
            }

            if(founded){
                continue;
            }

            SummaryPayments consultantPayment = new SummaryPayments();
            consultantPayment.setDate(cpl.getDataTime());
            consultantPayment.setMessageCount(cpl.getConversation().getConversationMessages().size());
            consultantPayment.setPaymentsCount(1);
            consultantPayment.setSum(conversationCalculator.getSpentMoney());

            consultantPayments.add(consultantPayment);
        }
        return consultantPayments;
    }
}
