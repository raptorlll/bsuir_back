package com.leonov.springboot.jwt.integration.JsonModels;

import com.leonov.springboot.jwt.integration.domain.Conversation;
import com.leonov.springboot.jwt.integration.domain.ConversationMessage;
import com.leonov.springboot.jwt.integration.domain.CustomerPayment;

import java.util.Collection;

public class ConversationCalculator {
    private Conversation conversation;

    public ConversationCalculator(Conversation conversation) {
        this.conversation = conversation;
    }

    public double getVideoMoney() {
        double tarifVideo = getTarifVideo();
        double minutes = 0;

        for (ConversationMessage cm : conversation.getConversationMessages()){
            if(cm.getVideoDuration()!=null){
                minutes += cm.getVideoDuration().getMinutes() + cm.getVideoDuration().getSeconds() / 60;
            }
        }

        return Math.ceil(minutes * tarifVideo);
    }

    public double getSpentMoney() {
        double tarif = getTarif();

        return conversation.getConversationMessages().size() * tarif + getVideoMoney();
    }

    public double getTarif() {
        return conversation.getConsultantGroupUser().getConversationTarif() == null
                ? conversation.getConsultantGroupUser().getConsultantGroup().getConversationTarif()
                : conversation.getConsultantGroupUser().getConversationTarif();
    }
    public double getTarifVideo() {
        return conversation.getConsultantGroupUser().getVideoTarif() == null
                ? conversation.getConsultantGroupUser().getConsultantGroup().getVideoTarif()
                : conversation.getConsultantGroupUser().getVideoTarif();
    }

    public boolean canPost(Collection<CustomerPayment> data){
        return  needAtLeast(data) < 0;
    }

    public double needAtLeast(Collection<CustomerPayment> data){
        int paymentsTotal = 0;
        double tarif = getTarif();
        double tarifVideo = getTarifVideo();

        for (CustomerPayment customerPayment : data) {
            paymentsTotal += customerPayment.getAmount();
        }

        return  (getSpentMoney() + Math.max(tarif, tarifVideo)) - paymentsTotal;
    }

}
