package controller;

import java.util.concurrent.Semaphore;

public class ThreadAeroporto extends Thread{
	
	private Semaphore areaDecolagem;
	private Semaphore pistaNorte;
	private Semaphore pistaSul;
	private int numAviao;
	
	public ThreadAeroporto(Semaphore areaDecolagem, Semaphore pistaNorte, Semaphore pistaSul, int numAviao) {
		this.areaDecolagem = areaDecolagem;
		this.pistaNorte = pistaNorte;
		this.pistaSul = pistaSul;
		this.numAviao = numAviao;
	}
	
	@Override
	public void run() {
		try {
			areaDecolagem.acquire();
			System.out.println("O avião #" + numAviao + " entrou na área de decolagem.");
			manobrar();
			taxiar();
			decolar();
			afastar();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			areaDecolagem.release();
		}
	}

	private void manobrar() {
		int tempoManobra = (int)((Math.random() * 4001) + 3000);
		try {
			sleep(tempoManobra);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("O avião #" + numAviao + " terminou de manobrar.");
	}
	
	private void taxiar() {
		int tempoTaxiar = (int)((Math.random() * 5001) + 5000);
		try {
			sleep(tempoTaxiar);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("O avião #" + numAviao + " terminou de taxiar.");
	}
	
	private void decolar() {
		int tempoDecolagem = (int)((Math.random() * 3001) + 1000);
		int pista = (int)((Math.random() * 2) + 1);
		if (pista == 1) {
			try {
				pistaNorte.acquire();
				System.out.println("O avião #" + numAviao + " entrou na pista norte.");
				sleep(tempoDecolagem);
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				pistaNorte.release();
			}
		} else {
			try {
				pistaSul.acquire();
				System.out.println("O avião #" + numAviao + " entrou na pista sul.");
				sleep(tempoDecolagem);
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				pistaSul.release();
			}
		}
		System.out.println("O avião #" + numAviao + " decolou");
	}
	
	private void afastar() {
		int tempoAfastar = (int)((Math.random() * 5001) + 3000);
		try {
			sleep(tempoAfastar);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("O avião #" + numAviao + " se afastou da área de decolagem.");
	}

}
