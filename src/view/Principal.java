package view;

import java.util.concurrent.Semaphore;

import controller.ThreadAeroporto;

public class Principal {

	public static void main(String[] args) {
		
		Semaphore areaDecolagem = new Semaphore(2);
		Semaphore pistaNorte = new Semaphore(1);
		Semaphore pistaSul = new Semaphore(1);
		
		for (int i = 1; i <= 12; i++) {
			Thread aviao  = new ThreadAeroporto(areaDecolagem, pistaNorte, pistaSul, i);
			aviao.start();
		}

	}

}
