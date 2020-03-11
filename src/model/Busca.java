package model;

import java.util.ArrayList;
import java.util.HashMap;


public class Busca {
	protected final ModeloTransicao m;
	private ArrayList<Estado> borda; 
	private HashMap<String, Estado> rc;
	
	public Busca () {
		m = new ModeloTransicao();
		borda = new ArrayList<>();
		rc = new HashMap<>();
		int ambiente[][] = {{0,1,1}, {1,0,0}, {1,0,1}, {0,1,0,},{1,1,0},{1,0,0}};  //Determina o estado inicial {0,1,0,},{1,1,0},{1,0,0}
		borda.add(new Estado(null, ambiente, 0, 0)); //Determina a posição inicial do aspirador
	}
	
	/**
	 * Este método realiza a busca em largura utilizando da borda e da região conhecida
	 * Para modificar e fazer a busca em profundidade mudar a implementação de FIFO para LIFO <---
	 */
	public void fazBusca() {
		while(!borda.isEmpty()) {
			Estado e = borda.remove(borda.size()-1);  // <--- Profundidade -> borda.size()-1 | Largura -> 0
			rc.put(e.geraIdentificador(), e); 
			
			expandeNo(e);

			if(checaObjetivo(e)) {
				exibeCaminho(e);
				break;	
			}
		}		
	}
	
	/**
	 * O objetivo é todo o ambiente estar limpo independente da posição do aspirador
	 * Retorna se o ambiente todo está limpo 
	 * @param e
	 */
	private boolean checaObjetivo(Estado e) {
		int temp[][] = e.getAmbiente();
		boolean estalimpo = true;
		System.out.println();
		for(int i = 0; i < temp.length; i++) {
			for(int j = 0; j < temp[0].length; j++ ) {
				if(temp[i][j] == 1) {
					estalimpo = false;
				} 
			}
		}
		System.out.println("\n" + e.toString());
		if(estalimpo) {
			System.out.println("Está limpa!" +"\n");
		} else {
			System.out.println("Continua suja!\n");
			
		}
		return estalimpo;
	}

	private void expandeNo(Estado e) {
		// Fazer verificação se não está na borda ou na região conhecida
		
		int ambiente[][] = e.getAmbiente();
		int x = e.getLocalAspirador()[0];
		int y = e.getLocalAspirador()[1];
		
		if(ambiente[x][y] == 1) {
			Estado novo = m.limpar(e);
			if(!checaRCeBorda(novo)) {

				borda.add(novo);
			}
		} 
		if (x < ambiente.length-1) { // se ele não atingiu a borda da direita ele anda no eixo x positivo
			Estado novo = m.mover(e, 1, 0);
			if(!checaRCeBorda(novo)) {
				borda.add(novo);
			}
		} 
		if (y < ambiente[0].length-1) { 
			Estado novo = m.mover(e, 0, 1);
			if(!checaRCeBorda(novo)) {
				borda.add(novo);
			}
		} 
		if (x > 0) {
			Estado novo = m.mover(e, -1, 0);
			if(!checaRCeBorda(novo)) {
				borda.add(novo);
			}
		} 
		if (y > 0) { 
			Estado novo = m.mover(e, 0, -1);
			if(!checaRCeBorda(novo)) {
				borda.add(novo);
			}
		}	
	}
	
	private boolean checaRCeBorda(Estado e1) {		
        for (Estado e2 : borda) {
            if (e2.geraIdentificador() == (e1.geraIdentificador())) {
                return true;
            }
        }
        return rc.containsKey(e1.geraIdentificador());
	}	
	
    public void exibeCaminho(Estado e) {
        if (e.getPai() != null) {
        	//System.out.println(e.toString());
            exibeCaminho(e.getPai());
        }
       System.out.println("\n" + e.toString());

    }
}
