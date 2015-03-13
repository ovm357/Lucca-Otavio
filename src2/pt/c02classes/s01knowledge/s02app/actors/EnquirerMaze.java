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
		      switch(direcao) {
			      
			      /* NORTE */
			      case("norte"):
			    	  if (responder.ask("oeste")==("parede")) {
			    		  if (!(responder.ask("norte")==("parede"))) {
			    			  responder.move("norte");
			    			  direcao = "norte";
			    			  System.out.println(direcao);
			    			  break;
			    		  }
			    		  else if (!(responder.ask("leste")==("parede"))) {
			    			  responder.move("leste");
				    		  direcao = "leste";
				    		  System.out.println(direcao);
				    		  break;
			    		  }
				    	  else {
			    			  responder.move("sul");
				    		  direcao = "sul";
				    		  System.out.println(direcao);
				    		  break;
			    		  }
			      }
			      else {
			    	  responder.move("oeste");
			    	  direcao = "oeste";
			    	  System.out.println(direcao);
			    	  break;
			      }
			      
			      /* LESTE */
			      case("leste"):
			    	  if (responder.ask("norte")==("parede")) {
			    		  if (!(responder.ask("leste")==("parede"))) {
			    			  responder.move("leste");
			    			  direcao = "leste";
			    			  System.out.println(direcao);
			    			  break;
			    		  }
			    		  else if (!(responder.ask("sul")==("parede"))) {
			    			  responder.move("sul");
				    		  direcao = "sul";
				    		  System.out.println(direcao);
				    		  break;
			    		  }
				    	  else {
			    			  responder.move("oeste");
				    		  direcao = "oeste";
				    		  System.out.println(direcao);
				    		  break;
			    		  }
			      }
			      else {
			    	  responder.move("norte");
			    	  direcao = "norte";
			    	  System.out.println(direcao);
			    	  break;
			      }
			      
			      /* OESTE */
			      case("oeste"):
			    	  if (responder.ask("sul")==("parede")) {
			    		  if (!(responder.ask("oeste")==("parede"))) {
			    			  responder.move("oeste");
			    			  direcao = "oeste";
			    			  System.out.println(direcao);
			    			  break;
			    		  }
			    		  else if (!(responder.ask("norte")==("parede"))) {
			    			  responder.move("norte");
				    		  direcao = "norte";
				    		  System.out.println(direcao);
				    		  break;
			    		  }
				    	  else {
			    			  responder.move("leste");
				    		  direcao = "leste";
				    		  System.out.println(direcao);
				    		  break;
			    		  }
			      }
			      else {
			    	  responder.move("sul");
			    	  direcao = "sul";
			    	  System.out.println(direcao);
			    	  break;
			      }
			      
			      /* SUL */
			      case("sul"):
			    	  if (responder.ask("leste")==("parede")) {
			    		  if (!(responder.ask("sul")==("parede"))) {
			    			  responder.move("sul");
			    			  direcao = "sul";
			    			  System.out.println(direcao);
			    			  break;
			    		  }
			    		  else if (!(responder.ask("oeste")==("parede"))) {
			    			  responder.move("oeste");
				    		  direcao = "oeste";
				    		  System.out.println(direcao);
				    		  break;
			    		  }
				    	  else {
			    			  responder.move("norte");
				    		  direcao = "norte";
				    		  System.out.println(direcao);
				    		  break;
			    		  }
			      }
			      else {
			    	  responder.move("leste");
			    	  direcao = "leste";
			    	  System.out.println(direcao);
			    	  break;
			      }
		      }
		      
		      
		      if (responder.ask("aqui")==("saida"))
		    	  saida = true;
		      
		}
		System.out.println(responder.ask("aqui"));
		return true;
	}
	
}