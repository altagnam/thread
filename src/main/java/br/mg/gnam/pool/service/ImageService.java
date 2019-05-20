package br.mg.gnam.pool.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import org.springframework.stereotype.Service;

@Service
public class ImageService {

	public void saveImage (String message) {
		
		try {
		
			Thread.sleep(new Random(100).nextInt(3000));
		
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		
	}
}
