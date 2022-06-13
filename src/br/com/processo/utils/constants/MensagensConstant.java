package br.com.processo.utils.constants;

public class MensagensConstant {
	
	private MensagensConstant() {
		
	}
	
	public static Double QUANTUM = 0D;
	public static final String METODO_FIFO = "1 - FIFO\n";
	public static final String METODO_MENOR_PROCESSO = "2 - Menor Processo\n";
	public static final String METODO_PRIORIDADE = "3 - Pioridade\n";
	public static final String METODO_ROUND_ROBIN = "4 - Round Robin\n";
	public static final String METODO_MULTIPLAS_FILAS = "5 - Multiplas Filas\n";
	public static final String OPCAO = "Opção: ";
	
	public static final String OPCOES_ESCALONAMENTOS = 
			                        METODO_FIFO
									.concat(METODO_MENOR_PROCESSO)
									.concat(METODO_PRIORIDADE)
									.concat(METODO_ROUND_ROBIN)
									.concat(METODO_MULTIPLAS_FILAS)
									.concat(OPCAO);
	public static final String OPCAO_INVALIDA = "Opção inválida.";
	
	public static final String DESEJA_CONTINUAR = "Deseja continuar?";
	public static final String ESCOLHA_OPCAO = "Escolha uma opcão";
	
	public static final String NOME = "Nome:";
	public static final String TEMPO = "Tempo:";
	public static final String PRIORIDADE = "Prioridade:";
	public static final String TIPOPROCESSO = "Tipo do Processo:";

	
	public static final String TMP_TEM = "TMP: %s\n"
										.concat("TEM: %s");
	public static final String RESULTADO = "Resultado";

}
