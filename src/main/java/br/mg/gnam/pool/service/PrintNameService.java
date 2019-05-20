package br.mg.gnam.pool.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

@Service
public class PrintNameService {
	
	
	public void printName (String message) {
		try {
			
		
			Thread.sleep(new Random(10).nextInt(1000));
			
		
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}

}
