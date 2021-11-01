package modelo;
//import java.io.File;
import java.io.InputStream;
//import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

/**
 * Projeto1 de POO
 * Grupo de alunos:  Glauco Gomes, Renato Ribeiro
 *
 */

public class JogoDaForca 
{
	
	private int N; // quantidade de palavras do arquivo (lido do arquivo)
	private String[] palavras; // um array com as N palavras (lidas do arquivo)
	private String[] dicas; // um array com as N dicas (lidas do arquivo)
	private String palavra;
	private int indice = 0; // indice da palavra sorteadado jogo
	private int acertos; // total de acertos do jogo
	private int erros; // total de erros do jogo
	private String[] penalidades = {"perna","perna","braço","braço","tronco","cabeça"};
	private StringBuilder tenAnt = new StringBuilder();
	private String[] letras;
	private String[] auxletras;
	private String resultado;

	public JogoDaForca(String nomearquivo) throws Exception
	{
		int i = 0;
		String[] partes = null;
		String linha = null;
		Scanner arq = null;
		try 
		{
			InputStream fonte = this.getClass().getResourceAsStream("/arquivo/palavras.txt");// Para arquivo interno
			arq = new Scanner(fonte);// Para arquivo interno
			//arq = new Scanner(new File(nomearquivo)); //Para arquivo externo
		}
		catch(Exception e)
		{
			throw new Exception("Arquivo não encontrado");
		}
		N = Integer.parseInt(arq.nextLine());// Ler a primeira linha do arquivo
		palavras = new String[N];
		dicas = new String[N];

		//Ler arquivo, separar e montar array de palavras e dicas
		while (arq.hasNextLine())
		{
			linha = arq.nextLine(); //ler as demais linhas do arquivo
			partes = linha.split(";");
			palavras[i] = partes[0]; //Array de palavras
			dicas[i] = partes[1]; //Array de dicas
			i++;;
		}
		arq.close();
	}

	public void iniciar() 
	{
		Random sorteio = new Random();
		indice = sorteio.nextInt(N);
		String palAux = palavras[indice].toUpperCase();
		this.auxletras = new String[palAux.length()];
//		int tam = a.length();
//		auxletras = "*********************".substring(0, tam);
		for(int k=0 ; k<palAux.length(); k++) 
		{
			this.auxletras[k] = "*"; 
		}
		
	}

	public boolean adivinhou(String tentativa)throws Exception
	{ 
		String letra = tentativa.trim().toUpperCase();
		int tam = letra.length();
		boolean resultAcerto = false;
		
//		for(int k = 0 ; k < tenAnt.toString().length(); k++)
//		{
//			if (tenAnt.isEmpty())
//				throw new Exception("A caixa está vazia");
//			if (!letra.matches("[a-zA-Z\s]+"))
//				throw new Exception("Você precisa informar uma letra!!");
//			if ((tam != 1))
//				throw new Exception("Você só pode digitar uma letra");
//		}
		
		for(int k = 0 ; k < tenAnt.toString().length(); k++)
		{
			if (!(tenAnt.isEmpty() || letra.matches("[A-Z\s]+")) || tam > 1)
			{
				throw new Exception("Você precisa informar uma letra!! ");
			}
			else
				if (tenAnt.substring(k).contains(letra))
				{
					throw new Exception("Você já digitou a letra " + letra + "!!");
				}
		}
		
		tenAnt.append(letra); // add a letra informada no array
		for(int k = 0 ; k<letras.length; k++)
		{
			if (letras[k].equals(letra))
			{
				this.acertos = this.getAcertos()+1;
				this.auxletras[k] = letra;
				resultAcerto = true;
			}	
		}
		if(resultAcerto)
		{
			if (this.getAcertos() == auxletras.length)
			{ 
				this.setResultado("Parabéns você ganhou!!");
			}
		}
		else 
		{
		    this.erros = this.getErros()+1;
			if (this.getErros() == 6) 
			{ 
				this.setResultado("Você perdeu!!");
			}
		}
		return resultAcerto;
	}

	public void setResultado(String resultado)
	{
		this.resultado = resultado;
	}

	public boolean terminou()
	{
		boolean status = false;
		if((this.acertos == auxletras.length) || (this.erros == 6))
		{
			status = true;
		}
		return status;
	}

	public String getPalavra()
	{// Esconder as letras da palavra sorteada 
		this.palavra = palavras[indice].toUpperCase();
		this.letras = palavra.split("");// Separar as letras da palavra sorteada e gerar um array
		StringBuilder escPalv= new StringBuilder();
		for(int k=0 ; k<auxletras.length; k++) 
		{
//			auxletras[k] = "-"; 
//			System.out.print(auxletras[k] + " ");
//			
			escPalv.append(auxletras[k]);
		}
		palavra = new String(escPalv);
		return palavra;
	}

	public String getDica()
	{
		return this.dicas[indice];
	}

	public String getPenalidade()
	{
		return penalidades[erros -1];
	}

	public int getAcertos()
	{
		return this.acertos;
	}

	public int getErros()
	{
		return this.erros;
	}

	public String getResultado()
	{
		return this.resultado;
	}
	
}