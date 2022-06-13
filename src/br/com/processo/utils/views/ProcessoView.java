package br.com.processo.utils.views;

import static br.com.processo.utils.constants.MensagensConstant.DESEJA_CONTINUAR;
import static br.com.processo.utils.constants.MensagensConstant.ESCOLHA_OPCAO;
import static br.com.processo.utils.constants.MensagensConstant.NOME;
import static br.com.processo.utils.constants.MensagensConstant.OPCAO_INVALIDA;
import static br.com.processo.utils.constants.MensagensConstant.OPCOES_ESCALONAMENTOS;
import static br.com.processo.utils.constants.MensagensConstant.PRIORIDADE;
import static br.com.processo.utils.constants.MensagensConstant.RESULTADO;
import static br.com.processo.utils.constants.MensagensConstant.TEMPO;
import static br.com.processo.utils.constants.MensagensConstant.TMP_TEM;
import static br.com.processo.utils.constants.MensagensConstant.TIPOPROCESSO;

import java.math.RoundingMode;
import java.text.DecimalFormat;

import javax.swing.JOptionPane;

import br.com.processo.entities.Processo;
import br.com.processo.utils.constants.MensagensConstant;

public class ProcessoView {

	private ProcessoView() {

	}

	public static void pegarQuantum() {
		Boolean invalido = true;
		while (invalido) {
			try {
				MensagensConstant.QUANTUM = Double.parseDouble(
							JOptionPane.showInputDialog("Quantum:")
						  );
				invalido = false;
			} catch (NumberFormatException numberFormatException) {
				opcaoInvalida();
				invalido = true;
			}
		}
	}

	public static Processo montarProcesso(Integer id) {
		Boolean valido = false;
		while (!valido) {
			try {
				String nome = JOptionPane.showInputDialog(//
						NOME//
				);
				Double tempo = Double.parseDouble(//
						JOptionPane.showInputDialog(//
								TEMPO//
						)//
				);
				Integer prioridade = Integer.parseInt(//
						JOptionPane.showInputDialog(//
								PRIORIDADE//
						)//
				);
				Integer tipoProcesso = Integer.parseInt(//
						JOptionPane.showInputDialog(//
								TIPOPROCESSO//
								)//
						);
				valido = true;
				return new Processo(id, tempo, nome, prioridade, tipoProcesso);
			} catch (NumberFormatException numberFormatException) {
				opcaoInvalida();
				valido = false;
			}
		}
		return new Processo();
	}

	public static Boolean desejaContinuar() {
		return JOptionPane.showConfirmDialog(//
				null, //
				DESEJA_CONTINUAR, //
				ESCOLHA_OPCAO, //
				1//
		) == 0;
	}

	public static Integer opcoesProcesso() {
		Boolean invalido = true;
		while (invalido) {
			try {
				invalido = false;
				return Integer.parseInt(JOptionPane.showInputDialog(//
						null, //
						OPCOES_ESCALONAMENTOS, //
						ESCOLHA_OPCAO, //
						3//
				)//
				);
			} catch (NumberFormatException numberFormatException) {
				opcaoInvalida();
				invalido = true;
			}
		}
		return -1;
	}

	public static void calculo(Double tmp, Double tem) {
		DecimalFormat decimalFormat = new DecimalFormat("00.00");
		decimalFormat.setRoundingMode(RoundingMode.HALF_UP);
		decimalFormat.format(tmp);
		JOptionPane.showMessageDialog(//
				null, //
				String.format(//
						TMP_TEM, //
						decimalFormat.format(tmp), //
						decimalFormat.format(tem)//
				), //
				RESULTADO, //
				1//
		);
	}

	private static void opcaoInvalida() {
		JOptionPane.showMessageDialog(//
				null, //
				OPCAO_INVALIDA, //
				OPCAO_INVALIDA, //
				2//
		);
	}

}
