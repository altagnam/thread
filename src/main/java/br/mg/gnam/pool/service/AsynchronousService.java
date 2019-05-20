package br.mg.gnam.pool.service;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;


@Service
public class AsynchronousService {
	
	
	
	@Autowired
	private PrintNameService printNameService;
	

	@Autowired
	private ImageService imageService;
	
	
	@Async("threadTest")
	public void processMessage (String json) {
		System.out.println("-------> start thread: " + Thread.currentThread().getId() + ": " + json + " " + new SimpleDateFormat("dd/MM/yyyy HH:mm:ss:sss").format(new Date()));
		printNameService.printName(json);
		imageService.saveImage(json);
		System.out.println("------->   end thread: "  + Thread.currentThread().getId() + ": " + json + " " + new SimpleDateFormat("dd/MM/yyyy HH:mm:ss:sss").format(new Date()));
		

	}

}
