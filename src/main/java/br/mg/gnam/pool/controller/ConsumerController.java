package br.mg.gnam.pool.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.task.TaskExecutor;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import br.mg.gnam.pool.service.AsynchronousService;

@Component
public class ConsumerController {
	
	@Autowired
	private AsynchronousService asynchronousService;
	
	@Autowired
	private TaskExecutor executor;


	@JmsListener(destination = "message")
	public void listener(String message) {
		String json =  "{\"name\":" + " \" " + message + "\", \"date\": \"" + new SimpleDateFormat("dd/MM/yyyy HH:mm:ss:sss").format(new Date()) + "\"}";
		ThreadPoolTaskExecutor pool = (ThreadPoolTaskExecutor) executor;
		System.out.println("################################### BEGIN");
		System.out.println(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss:sss").format(new Date()));
		System.out.println(message);
		System.out.println("pool.getActiveCount() " + pool.getActiveCount());
		System.out.println("pool.getPoolSize() " + pool.getPoolSize());
		System.out.println("pool.getMaxPoolSize() " + pool.getMaxPoolSize());
		asynchronousService.processMessage(json);
		System.out.println("################################### END");
		System.out.println("");

	}

}
