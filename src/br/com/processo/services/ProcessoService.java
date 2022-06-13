package br.com.processo.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import br.com.processo.entities.Processo;
import br.com.processo.utils.constants.MensagensConstant;
import br.com.processo.utils.views.ProcessoView;

public class ProcessoService {

	private List<Processo> processos;
	private List<Processo> menorTempo;
	private List<Processo> prioridade;
	private List<Processo> fifo;
	private Integer quantidadeProcessos = 0;

	public ProcessoService(List<Processo> processos) {
		this.processos = List.of(
				new Processo(1, 17D, "1", 1, 1),
				new Processo(2, 20D, "2", 1, 4),
				new Processo(3, 12D, "3", 1, 4), 
				new Processo(4, 10D, "4", 1, 2), 
				new Processo(1, 8D, "3", 1, 2)
				);
		this.quantidadeProcessos = processos.size();
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

		case 5:
			metodoMultipasFilas();
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

	private void metodoMultipasFilas() {
		ordernarMultipasFilas();
		ProcessoView.calculo(//
				tmpRobin(), //
				temRobin()//
		);
	}

	private Double temRobin() {
		List<Double> listaIntervalo = new ArrayList<>();
		for (Processo processo : processos) {
			listaIntervalo.add(processo.getTempoInicial());
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

	private Double multipasFilasRoundRobin(List<Processo> lista, Double soma) {
		Double total = pegarTotalTempoMultipasFilas(lista);
		Integer voltas = 1;
		Double somatoria = 0.0;
		while (total > somatoria) {
			for (Processo processo : processos.stream().filter(f -> f.getTipoProcesso().equals(4))
					.collect(Collectors.toList())) {
				if (voltas == 1) {
					processos.get(processos.indexOf(processo)).setTempoInicial(somatoria + soma);
				}
				if (processo.getTempo() - voltas * MensagensConstant.QUANTUM > 0) {
					somatoria += MensagensConstant.QUANTUM;
				} else if (processo.getTempo() - voltas * MensagensConstant.QUANTUM == 0) {
					somatoria += MensagensConstant.QUANTUM;
					//System.err.println(lista.indexOf(processo));
					processos.get(processos.indexOf(processo)).setTempoFinal(somatoria + soma);
					processos.get(processos.indexOf(processo)).setJaCalculado(true);
				} else if (processo.getTempo() - voltas * MensagensConstant.QUANTUM < 0
						&& processo.getTempoFinal() == null) {
					somatoria += processo.getTempo() - ((voltas - 1) * MensagensConstant.QUANTUM);
					processos.get(processos.indexOf(processo)).setTempoFinal(somatoria + soma);
					processos.get(processos.indexOf(processo)).setJaCalculado(true);
				}

			}
			voltas++;
		}
		return soma + somatoria;
	}

	private void ordernarMultipasFilas() {
		this.fifo = processos.stream().filter(f -> f.getTipoProcesso().equals(1)).collect(Collectors.toList());
		this.menorTempo = processos.stream().filter(f -> f.getTipoProcesso().equals(2)).collect(Collectors.toList());
		this.menorTempo = this.ordenarListasMultipalas(this.menorTempo, true);
		this.prioridade = processos.stream().filter(f -> f.getTipoProcesso().equals(3)).collect(Collectors.toList());
		this.prioridade = this.ordenarListasMultipalas(this.prioridade, false);

		List<Processo> roundRobin = processos.stream().filter(f -> f.getTipoProcesso().equals(4))
				.collect(Collectors.toList());
		Double soma = 0.0;
		for (Processo processo : processos) {
			if (!processo.isJaCalculado()) {
				if (processo.getTipoProcesso() == 4)
					soma = this.multipasFilasRoundRobin(roundRobin, soma);
				else
					soma = this.somaTempos(processo.getTipoProcesso(), soma);

			}
		}
	}

	private Double somaTempos(Integer tipoProcesso, Double soma) {

		List<Processo> lista = new ArrayList<>();
		lista = this.inverteLista(tipoProcesso, lista, true);
		for (Processo processo : lista) {
			this.processos.get(lista.indexOf(processo)).setTempoInicial(soma);
			soma += processo.getTempo();
			this.processos.get(lista.indexOf(processo)).setTempoFinal(soma);
			this.processos.get(lista.indexOf(processo)).setJaCalculado(true);
		}
		this.inverteLista(tipoProcesso, lista, false);
		return soma;
	}

	private List<Processo> inverteLista(Integer tipoProcesso, List<Processo> lista, boolean inverter) {
		if (inverter) {
			if (tipoProcesso == 1)
				lista = this.fifo;
			else if (tipoProcesso == 2)
				lista = this.menorTempo;
			else
				lista = this.prioridade;
		} else {
			if (tipoProcesso == 1)
				this.fifo = lista;
			else if (tipoProcesso == 2)
				this.menorTempo = lista;
			else
				this.prioridade = lista;
		}
		return lista;
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

	private List<Processo> ordenarListasMultipalas(List<Processo> lista, boolean isMenorTempo) {
		for (int i = 0; i < lista.size() - 1; i++) {
			for (int j = 0; j < lista.size() - 1 - i; j++) {
				if (isMenorTempo ? lista.get(j).getTempo() > lista.get(j + 1).getTempo()
						: lista.get(j).getPrioridade() < lista.get(j + 1).getPrioridade()) {
					Processo processo = lista.get(j);
					lista.set(j, processos.get(j + 1));
					lista.set(j + 1, processo);
				}
			}
		}
		return lista;
	}

	private Double somaIntervalo(List<Double> intervalos) {
		Double soma = 0D;
		for (Double intervalo : intervalos) {
			soma += intervalo;
		}
		return soma;
	}

	private Double pegarTotalTempo() {
		Double total = 0.0;
		for (Processo processo : processos) {
			total += processo.getTempo();
		}
		return total;
	}

	private Double pegarTotalTempoMultipasFilas(List<Processo> lista) {
		Double total = 0.0;
		for (Processo processo : lista) {
			total += processo.getTempo();
		}
		return total;
	}

}
