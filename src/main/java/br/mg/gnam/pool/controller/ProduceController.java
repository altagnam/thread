package br.mg.gnam.pool.controller;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProduceController {

	@Autowired
	private JmsTemplate jmsTemplate;

	@GetMapping("/message/{quantidade}")
	public void sendMessage (@PathVariable  Integer quantidade) throws InterruptedException {
		
//		for (int i = 0; i < quantidade / 5; i++) {
//			jmsTemplate.convertAndSend("message", getMessage());
//		}
		

		
		for (int i = 0; i < quantidade; i++) {
			//Thread.sleep(new Random(100).nextInt(1000));
			jmsTemplate.convertAndSend("message", getMessage());
		}
		
	}
	
	
	public String getMessage () {
		return "{\"name\":" + " \" " + getName() + "\"}";
	}
	
	
	public String getName () {

        // chose a Character random from this String 
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                                    + "0123456789"
                                    + "abcdefghijklmnopqrstuvxyz"; 
  
        // create StringBuffer size of AlphaNumericString 
        StringBuilder sb = new StringBuilder(15); 
  
        for (int i = 0; i < 15; i++) { 
  
            // generate a random number between 
            // 0 to AlphaNumericString variable length 
            int index   = (int)(AlphaNumericString.length() * Math.random()); 
  
            // add Character one by one in end of sb 
            sb.append(AlphaNumericString.charAt(index)); 
        } 
  
        return sb.toString(); 
		     
	}
}
