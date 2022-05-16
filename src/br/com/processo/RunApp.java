package br.com.processo;

import java.util.ArrayList;

import br.com.processo.entities.Processo;
import br.com.processo.services.ProcessoService;
import br.com.processo.utils.views.ProcessoView;

public class RunApp {

	private static ArrayList<Processo> processos = new ArrayList<>();
	
	public static void main(String[] args) {
		ProcessoService.pegarQuantum();
		Integer id = 1;
		do {
			processos.add(ProcessoView.montarProcesso(id));
			id++;
		} while (ProcessoView.desejaContinuar());
		
		ProcessoService processoService = new ProcessoService(processos);
		processoService.chamadaMetodos(ProcessoView.opcoesProcesso());
	}
}