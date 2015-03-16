package pt.c02classes.s01knowledge.s02app.actors;

import pt.c02classes.s01knowledge.s01base.inter.IEnquirer;
import pt.c02classes.s01knowledge.s01base.inter.IResponder;

public class EnquirerMaze implements IEnquirer {

	IResponder responder;
	
	public void connect(IResponder responder) {
		this.responder = responder;
	}
	
	public boolean discover() {
		
		boolean saida = false;
		
	    String direcao = "temp";
		
	    /* Este bloco descobre qual a direcao inicial apos a entrada no labirinto */
	    if (responder.ask("norte")==("passagem"))
	    	direcao = "norte";
	    else if (responder.ask("leste")==("passagem"))
	    	direcao = "leste";
	    else if (responder.ask("oeste")==("passagem"))
	    	direcao = "oeste";
	    else if (responder.ask("sul")==("passagem"))
	    	direcao = "sul";
	    
		while(!saida) {
			/* Switch indica qual a direcao para qual estamos nos locomovendo
			 * Uma vez que o programa se baseia em seguir a parede da esquerda da entrada,
			 *   precisamos definir a direcao de deslocamento, pois se estamos indo para o norte,
			 *   nossa esquerda esta a oeste, se vamos para o leste nossa esquerda esta a norte , ...
			 */
		      switch(direcao) {
			      
			      /* NORTE */
			      case("norte"):
			    	  //se temos parede a esquerda
			    	  if (responder.ask("oeste")==("parede")) {
			    		  //se podemos seguir em frente
			    		  if (!(responder.ask("norte")==("parede"))) {
			    			  //siga em frente
			    			  responder.move("norte");
			    			  direcao = "norte";
			    			  break;
			    		  }
			    		  //se nao, se podemos ir para a direita
			    		  else if (!(responder.ask("leste")==("parede"))) {
			    			  //va para direita
			    			  responder.move("leste");
				    		  direcao = "leste";
				    		  break;
			    		  }
			    		  //se estamos encurralados
				    	  else {
				    		  //volte
			    			  responder.move("sul");
				    		  direcao = "sul";
				    		  break;
			    		  }
			      }
			      //se nao ha parede a nossa esquerda
			      else {
			    	  //vamos para a esquerda
			    	  responder.move("oeste");
			    	  direcao = "oeste";
			    	  break;
			      }
			      
			      /* LESTE */
			      case("leste"):
			    	  if (responder.ask("norte")==("parede")) {
			    		  if (!(responder.ask("leste")==("parede"))) {
			    			  responder.move("leste");
			    			  direcao = "leste";
			    			  break;
			    		  }
			    		  else if (!(responder.ask("sul")==("parede"))) {
			    			  responder.move("sul");
				    		  direcao = "sul";
				    		  break;
			    		  }
				    	  else {
			    			  responder.move("oeste");
				    		  direcao = "oeste";
				    		  break;
			    		  }
			      }
			      else {
			    	  responder.move("norte");
			    	  direcao = "norte";
			    	  break;
			      }
			      
			      /* OESTE */
			      case("oeste"):
			    	  if (responder.ask("sul")==("parede")) {
			    		  if (!(responder.ask("oeste")==("parede"))) {
			    			  responder.move("oeste");
			    			  direcao = "oeste";
			    			  break;
			    		  }
			    		  else if (!(responder.ask("norte")==("parede"))) {
			    			  responder.move("norte");
				    		  direcao = "norte";
				    		  break;
			    		  }
				    	  else {
			    			  responder.move("leste");
				    		  direcao = "leste";
				    		  break;
			    		  }
			      }
			      else {
			    	  responder.move("sul");
			    	  direcao = "sul";
			    	  break;
			      }
			      
			      /* SUL */
			      case("sul"):
			    	  if (responder.ask("leste")==("parede")) {
			    		  if (!(responder.ask("sul")==("parede"))) {
			    			  responder.move("sul");
			    			  direcao = "sul";
			    			  break;
			    		  }
			    		  else if (!(responder.ask("oeste")==("parede"))) {
			    			  responder.move("oeste");
				    		  direcao = "oeste";
				    		  break;
			    		  }
				    	  else {
			    			  responder.move("norte");
				    		  direcao = "norte";
				    		  break;
			    		  }
			      }
			      else {
			    	  responder.move("leste");
			    	  direcao = "leste";
			    	  break;
			      }
		      }
		      
		      
		      if (responder.ask("aqui")==("saida"))
		    	  saida = true;
		      
		}
		System.out.println("Oba! Encontrei a saida !!");
		
		return true;
	}
	
}