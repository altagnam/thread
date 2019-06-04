package br.mg.gnam.pool.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.core.task.TaskRejectedException;
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
	
	
	private static int init = 0;


	@JmsListener(destination = "message")
	public void listener(String message) {
		init = init + 1;
		listener(message, init);
	}
	
	
	public void listener(String message, int contador) {
		
		
		ThreadPoolTaskExecutor pool = (ThreadPoolTaskExecutor) executor;
		
		try {
			
			String json =  "{\"name\":" + " \" " + message + "\", \"date\": \"" + new SimpleDateFormat("dd/MM/yyyy HH:mm:ss:sss").format(new Date()) + "\"}";
			
			System.out.println("################################### BEGIN " + Thread.currentThread().getId());
			System.out.println(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss:sss").format(new Date()));
			System.out.println(message);
			System.out.println("pool.getActiveCount() " + pool.getActiveCount());
			System.out.println("pool.getPoolSize()    " + pool.getPoolSize());
			System.out.println("pool.getMaxPoolSize() " + pool.getMaxPoolSize());
			System.out.println("pool.getQueue()       " + pool.getThreadPoolExecutor().getQueue().size());
			asynchronousService.processMessage(json);
			System.out.println(">>>>>>>>>>>>> CONSUMIDOR: " + contador++);
			System.out.println("################################### END "  + Thread.currentThread().getId());
			System.out.println("");
		}catch (TaskRejectedException e) {
			
			
			try {
				Thread.sleep(2000);				
				listener(message, contador);
			
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			
		}

	}

}
