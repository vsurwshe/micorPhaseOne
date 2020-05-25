package org.invoice.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.domain.entity.invoice.Invoice;
import org.domain.entity.invoice.InvoiceItem;
import org.domain.entity.payments.Payments;
import org.exception.exec.UserServiceException;
import org.repository.invoice.InvoiceRepository;
import org.repository.payment.PaymentsRepository;
import org.service.apiService.ErrorServiceMessage;
import org.service.apiService.LogService;
import org.service.apiService.ResponseEntityResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
public class PaymentInvoice {

	//---------------- Global Variable Declarations	
	@Autowired
	private PaymentsRepository paymentRepo;
	
	@Autowired
	private InvoiceRepository invoiceRepo;
	
	//---------------- Rest Controller Methods	
	@GetMapping(value="/getDates")
    public ResponseEntity<?> findDates() {
        return this.getTransDates();
    }
	
	@GetMapping(value="/getAll")
    public ResponseEntity<?> findAllInvoice() {
        return this.getAllInvoices();
    }

	@PostMapping(value = "/genrate/{date}")
	public ResponseEntity<?> findGenrateAllInvoice(@PathVariable("date")String invoiceDate ){
		return this.genrateAllInvoice(invoiceDate);
	}
	
	//------------------ This is customs method Area	

	private ResponseEntity<?> genrateAllInvoice(String invoiceDate) {
		List<Long> tempInvoiceIds = new ArrayList<>();
		try {
			List<Payments> filterPayment= paymentRepo.findBytarnsDate(invoiceDate);
			if(!filterPayment.isEmpty()) {
				for (Payments payments : filterPayment) {
					if( payments.getInvoice() == null) 
					{
						Invoice tempInvoice = this.invoiceRepo.saveAndFlush(this.setInvoice(payments));
						payments.setInvoice(tempInvoice);
						paymentRepo.saveAndFlush(payments);
						tempInvoiceIds.add(tempInvoice.getInvoiceId());
					}else {
						continue;
					}
				}
			}else {
				throw new UserServiceException(ErrorServiceMessage.NO_FILTER_PAYMENT_RECORDS_FOUND);
			}
			
		} catch (UserServiceException e) {
			LogService.setLogger(e.getMessage());
			return ResponseEntityResult.notFound(e.getMessage());
		}
		return ResponseEntityResult.successResponseEntity(tempInvoiceIds);
	}

	private Invoice setInvoice(Payments payments) {
		Invoice tempInvoice=new Invoice();
		tempInvoice.setInvoiceDate(payments.getTarnsDate());
		tempInvoice.setInvoiceSubTotalAmount(payments.getAmount());
		tempInvoice.setInvoiceTotalAmount(payments.getAmount());
		tempInvoice.setInvoiceItem(this.setInvoiceItemList(payments, tempInvoice));
		tempInvoice.setPayments(payments);
		return tempInvoice;
	}

	private List<InvoiceItem> setInvoiceItemList(Payments payments,Invoice invoice) {
		InvoiceItem tempInvoiceItem=new InvoiceItem();
		tempInvoiceItem.setItemName(payments.getMode().toString());
		tempInvoiceItem.setItemPrice(payments.getAmount());
		tempInvoiceItem.setItemQty(1);
		tempInvoiceItem.setItemTotalPrice(payments.getAmount());
		tempInvoiceItem.setInvoice(invoice);
		return Arrays.asList(tempInvoiceItem) ;
	}
	
	private ResponseEntity<?> getTransDates() {
		List<String> transDates=null;
		try {
			transDates=paymentRepo.findBytarnsDate();
			if(transDates.isEmpty()) {
				throw new UserServiceException("There is not any dates");
			}
		} catch (UserServiceException e) {
			LogService.setLogger(e.getMessage());
			return ResponseEntityResult.internalServerError(e.getMessage());
		}
		return ResponseEntityResult.successResponseEntity(transDates);
	}
	
	private ResponseEntity<?> getAllInvoices() {
		List<Invoice> invoiceResult=null;
		try {
			 invoiceResult=invoiceRepo.findAll();
			 if(invoiceResult.isEmpty()) {
				 throw new UserServiceException("Sorry there is no Invoice Item");
			 }
		} catch (UserServiceException e) {
			LogService.setLogger(e.getMessage());
			return ResponseEntityResult.notFound(e.getMessage());
		}
		return ResponseEntityResult.successResponseEntity(invoiceResult);
	}

}
