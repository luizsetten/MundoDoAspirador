package model;

public class Estado {
	private Estado pai;
	private int ambiente[][];
	private int localAspirador[];
	
	
	public int[][] getAmbiente() {
		return ambiente;
	}
	public void setAmbiente(int ambiente[][]) {
		this.ambiente = ambiente;
	}
	public int[] getLocalAspirador() {
		return localAspirador;
	}
	public void setLocalAspirador(int localApirador[]) {
		this.localAspirador = localApirador;
	}
	
	public Estado getPai() {
		return this.pai;
	}
	
	/**
	 * @param pai
	 * @param sujo matriz contendo onde o local está limpo(0) e onde está sujo(1)
	 * @param x posição do aspirado no eixo horizontal
	 * @param y posição do aspirado no eixo vertical
	 */
	public Estado(Estado pai, int sujo[][], int x, int y){
		this.pai = pai;
		ambiente = new int[sujo.length][sujo[0].length];
		for(int i = 0; i < ambiente.length; i++) {
			for(int j = 0; j< ambiente[0].length; j++) {
				ambiente[i][j] = sujo[i][j];
			}
		}
		localAspirador = new int[2];
		localAspirador[0] = x;
		localAspirador[1] = y;
	}
	
    /**
     * Gera identificador baseado no ambietne e na posição do aspirador
     *
     * @return identificador
     */
    public String geraIdentificador() {
    	String id = null;
    	for(int i = 0; i < ambiente.length; i++ ) {
    		for(int j = 0; j < ambiente[0].length; j++) {
    			id += ambiente[i][j];
    		}
    	}
    	id+=localAspirador[0];
    	id+=localAspirador[1];
        return id;
    }
	
    /**
     * @return Retorna uma string "visualmente agradável" do estado
     */
    public String toString() {
    	String caminho = new String("");
    	for(int i = 0; i < ambiente.length; i++) {
    		for(int j = 0; j < ambiente[0].length; j++ ) {
    			caminho += ambiente[i][j] + " ";
    		}

    		caminho += "\n";
    	}
    	caminho += "[" + localAspirador[0] + "," + localAspirador[1] + "]";

    	return caminho;

    }
    
}
