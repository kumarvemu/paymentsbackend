package com.allstate.payments.control;

import com.allstate.payments.domain.CreditCardTransaction;
import com.allstate.payments.dtos.CreditCardTransactionDTO;
import com.allstate.payments.service.PaymentsService;
import com.allstate.payments.service.PaymentsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/api/payment")
public class PaymentsController {

    PaymentsService paymentsService;

    @Autowired
    public void setPaymentsService(PaymentsService paymentsService) {
        this.paymentsService = paymentsService;
    }


    //dependency injection
//    public PaymentsController(PaymentsService paymentsService) {
//        this.paymentsService = paymentsService;
//    }

    @GetMapping()
    public List<CreditCardTransaction> getAll(@RequestParam(value="country", required=false) String country,
                                              @RequestParam(value="orderId", required=false) String orderId) {
        if (country != null) {
            return paymentsService.getAllTransactionsForCountry(country);
        }
        else if (orderId != null) {
            return paymentsService.getAllTransactionsForOrderId(orderId);
        }

        return paymentsService.getAllTransactions();

    }

    @GetMapping(value ="/{id}", produces={MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public CreditCardTransaction getById(@PathVariable("id") Integer id) {
        return paymentsService.getTransactionById(id);
    }


    @GetMapping("/volume")
    public Map<String, String> getNumberOfPayments() {
        Integer volume = paymentsService.countTransactions();
        Map<String, String> results = new HashMap<>();
        results.put("volume", volume.toString());
        return results;
    }

    @PostMapping
    public CreditCardTransaction addTransaction(@RequestBody CreditCardTransactionDTO newTransaction) {
        return paymentsService.add(newTransaction);
    }

    @PutMapping("/{id}")
    public CreditCardTransaction updateTransaction(@PathVariable("id") Integer id,
                                                   @RequestBody Map<String, String> data) {

        return paymentsService.updateTransaction(id, data);
    }

}