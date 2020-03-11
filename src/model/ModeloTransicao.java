package model;

public class ModeloTransicao {
	/**
	 * @apiNote Limpa o local onde o aspirador se encontra
	 * @param e Estado no qual o método foi chamado
	 * @return Retorna o novo estado com base nas alterações feitas
	 */
	public Estado limpar(Estado e) {
		int localAspirador[] = e.getLocalAspirador();
		int x, y;
		int ambiente[][] = e.getAmbiente();
		
		x = localAspirador[0];
		y = localAspirador[1];
		ambiente[x][y] = 0;
		
		return new Estado(e, ambiente, x, y);
		
	
	}
	
	
	/**
	 * @apiNote Move o aspirador para um novo local com base no local atual e na movimentação
	 * @param e Estado no qual o método foi chamado
	 * @param horizontal +1 para direita, 1 para esquerda, 0 parado
	 * @param horizontal +1 para baixo, 1 para cima, 0 parado
	 * @return Retorna o novo estado com base nas alterações feitas
	 */
	public Estado mover(Estado e, int horizontal, int vertical) {
		int localAspirador[] = e.getLocalAspirador();
		int x, y;

		x = localAspirador[0] + horizontal;
		y = localAspirador[1] + vertical;

		return new Estado(e, e.getAmbiente().clone(), x, y);
	}
}
