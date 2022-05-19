package br.com.processo.services;

import java.util.ArrayList;
import java.util.List;

import br.com.processo.entities.Processo;
import br.com.processo.utils.constants.MensagensConstant;
import br.com.processo.utils.views.ProcessoView;

public class ProcessoService {

	private List<Processo> processos;
	private Integer quantidadeProcessos = 0;

	public ProcessoService(List<Processo> processos) {
		this.processos = List.of(new Processo(1, 20D), new Processo(2, 9D), new Processo(3, 10D));
		this.quantidadeProcessos = 3;
	}

	public static void pegarQuantum() {
		ProcessoView.pegarQuantum();
	}

	public void chamadaMetodos(Integer opcao) {
		switch (opcao) {
		case 1:
			metodoFifo();
			break;

		case 2:
			metodoMenorProcesso();
			break;

		case 3:
			metodoPrioridade();
			break;

		case 4:
			metodoRoundRobin();
			break;
		default:

			break;
		}
	}

	private void metodoFifo() {
		ProcessoView.calculo(//
				tmp(), //
				tem()//
		);
	}

	private void metodoMenorProcesso() {
		ordenarPorTempo();
		ProcessoView.calculo(//
				tmp(), //
				tem()//
		);
	}

	private void metodoPrioridade() {
		ordenarPorPrioridade();
		ProcessoView.calculo(//
				tmp(), //
				tem()//
		);
	}

	private void metodoRoundRobin() {
		ordernarRoundRobin();
		ProcessoView.calculo(//
				tmpRobin(), //
				temRobin()//
		);
	}

	private Double temRobin() {
		List<Double> listaIntervalo = new ArrayList<>();
		for (int i = 0; i < quantidadeProcessos; i++) {
			listaIntervalo.add(processos.get(i).getTempoInicial());
		}
		return somaIntervalo(listaIntervalo) / this.quantidadeProcessos;
	}

	private Double tmpRobin() {
		ArrayList<Double> listaIntervalo = new ArrayList<>();
		for (int i = 0; i < quantidadeProcessos; i++) {
			listaIntervalo.add(processos.get(i).getTempoFinal());
		}
		return somaIntervalo(listaIntervalo) / this.quantidadeProcessos;
	}

	private Double tem() {
		Double media = 0.0;
		List<Double> listaIntervalo = new ArrayList<>();
		for (int i = 0; i < this.processos.size() - 1; i++) {
			media += this.processos.get(i).getTempo();
			listaIntervalo.add(media);
		}
		return somaIntervalo(listaIntervalo) / this.processos.size();
	}

	private Double tmp() {
		Double media = 0.0;
		ArrayList<Double> listaIntervalo = new ArrayList<>();
		for (Processo processo : this.processos) {
			media += processo.getTempo();
			listaIntervalo.add(media);
		}
		return somaIntervalo(listaIntervalo) / this.processos.size();
	}

	private void ordernarRoundRobin() {
		Double total = pegarTotalTempo();
		Integer voltas = 1;
		Double somatoria = 0.0;
		while (total > somatoria) {
			for (Processo processo : processos) {
				if (voltas == 1) {
					processo.setTempoInicial(somatoria);
				}
				if (processo.getTempo() - voltas * MensagensConstant.QUANTUM > 0) {
					somatoria += MensagensConstant.QUANTUM;
				} else if (processo.getTempo() - voltas * MensagensConstant.QUANTUM == 0) {
					somatoria += MensagensConstant.QUANTUM;
					processo.setTempoFinal(somatoria);
				} else if (processo.getTempo() - voltas * MensagensConstant.QUANTUM < 0
						&& processo.getTempoFinal() == null) {
					somatoria += processo.getTempo() - ((voltas - 1) * MensagensConstant.QUANTUM);
					processo.setTempoFinal(somatoria);
				}

			}
			voltas++;
		}
	}

	private void ordenarPorTempo() {
		for (int i = 0; i < this.processos.size() - 1; i++) {
			for (int j = 0; j < this.processos.size() - 1 - i; j++) {
				if (this.processos.get(j).getTempo() > this.processos.get(j + 1).getTempo()) {
					Processo processo = this.processos.get(j);
					this.processos.set(j, processos.get(j + 1));
					this.processos.set(j + 1, processo);
				}
			}
		}
	}

	private void ordenarPorPrioridade() {
		for (int i = 0; i < this.processos.size() - 1; i++) {
			for (int j = 0; j < this.processos.size() - 1 - i; j++) {
				if (this.processos.get(j).getPrioridade() < this.processos.get(j + 1).getPrioridade()) {
					Processo processo = this.processos.get(j);
					this.processos.set(j, processos.get(j + 1));
					this.processos.set(j + 1, processo);
				}
			}
		}
	}

	private Double somaIntervalo(List<Double> intervalos) {
		Double soma = 0D;
		for (Double intervalo : intervalos) {
			soma += intervalo;
		}
		return soma;
	}

	private Integer quantidadeDeRounds() {
		Double maiorProcesso = pegarMaiorTempo();
		Integer count = 0;
		while (maiorProcesso > MensagensConstant.QUANTUM) {
			maiorProcesso -= MensagensConstant.QUANTUM;
			count++;
		}
		return count;
	}

	private Double pegarMaiorTempo() {
		Double maiorTempo = 0D;
		for (Processo processo : this.processos) {
			if (processo.getTempo() > maiorTempo) {
				maiorTempo = processo.getTempo();
			}
		}
		return maiorTempo;
	}

	private Double pegarTotalTempo() {
		Double total = 0.0;
		for (Processo processo : processos) {
			total += processo.getTempo();
		}
		return total;
	}
}
