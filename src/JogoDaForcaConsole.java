/*
 * IFPB-POO-PROJETO1
 * Aplicação console para testar a classe JogoDaForca
 * 
 */
import java.util.Scanner;

public class JogoDaForcaConsole {
	public static void main(String[] args) {
		Scanner teclado = new Scanner (System.in);
		
		JogoDaForca jogo = null;
		try{
			jogo = new JogoDaForca("palavras.txt");
		}
		catch(Exception e){
			System.out.println(e.getMessage());
			System.exit(0);
		}
		
		jogo.iniciar();
		String letra;
		do{
			System.out.println("palavra = " + jogo.getPalavra());
			System.out.println("dica = " + jogo.getDica());
 
			System.out.println("digite uma letra da palavra");
			letra = teclado.nextLine();
			
			try {
				if (jogo.adivinhou(letra)){
					System.out.println("voce acertou a letra "+ letra);
					System.out.println("total de acertos = "+jogo.getAcertos());
				} 
				else{
					System.out.println("Voce errou a letra "+ letra);
					System.out.println("total de erros = "+jogo.getErros());
					System.out.println("Penalidade: "+jogo.getPenalidade());
				}
			}
			catch(Exception e){
				System.out.println(e.getMessage());
			}
		}

		while(!jogo.terminou());
		
		teclado.close();
		
		System.out.println("game over");
		System.out.println("resultado final = "+jogo.getResultado() );

	}
}
