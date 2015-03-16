package pt.c02classes.s01knowledge.s02app.actors;

import java.util.ArrayList;

import pt.c02classes.s01knowledge.s01base.impl.BaseConhecimento;
import pt.c02classes.s01knowledge.s01base.inter.IBaseConhecimento;
import pt.c02classes.s01knowledge.s01base.inter.IDeclaracao;
import pt.c02classes.s01knowledge.s01base.inter.IEnquirer;
import pt.c02classes.s01knowledge.s01base.inter.IObjetoConhecimento;
import pt.c02classes.s01knowledge.s01base.inter.IResponder;

public class EnquirerAnimals implements IEnquirer {

	IResponder responder;
	
	public void connect(IResponder responder) {
		this.responder = responder;
	}
	
	public boolean discover() {
		IBaseConhecimento bc = new BaseConhecimento();
		IObjetoConhecimento obj;
		
		bc.setScenario("animals");

		String listaAnimais[] = bc.listaNomes();
		ArrayList<String> Perguntas = new ArrayList<String> ();	// Um arrayList para guardar as perguntas
		ArrayList<String> Respostas = new ArrayList<String> ();	// Um arrayList para guardar as respostas
		int animal, contFor;
		boolean repetido = false;
		
		
		/* Este FOR garante que todos os animais sejam testados pelo Enquirer */
		for (animal = 0; animal < listaAnimais.length; animal++) {
			System.out.println(listaAnimais.length);
			obj = bc.recuperaObjeto(listaAnimais[animal]);
			IDeclaracao decl = obj.primeira();
			
	        boolean animalEsperado = true;
			while (decl != null && animalEsperado) {
				
				String pergunta = decl.getPropriedade();
				
				/* Este FOR compara a questao atual com as guardadas no estoque. Se for igual, a flag "repetido" vira true e o loop acaba. Se nao for, adiciona-a na lista */
				for(contFor = 0; Perguntas.size() > contFor; contFor = contFor+1) { // O for roda enquanto houverem questoes na lista
						if (Perguntas.get(contFor).equalsIgnoreCase(pergunta)) {
							repetido = true;
							break;
						}
				}
				/* Se a pergunta nao for repetida, ela pode ser feita normalmente */
				if (!repetido) {
					String respostaEsperada = decl.getValor();
					
					String resposta = responder.ask(pergunta);
					
					if (!(resposta.equalsIgnoreCase(respostaEsperada)))
						animalEsperado = false;	

					/* Adiciona a nova pergunta e resposta as respectivas arrays */
					Perguntas.add(pergunta);
					Respostas.add(resposta);
				}
				
				/* Se a pergunta for repetida, compara o valor esperado para esse animal com o que estava guardado na lista */
				else {
					String respostaEsperada = decl.getValor();					
					if (!(Respostas.get(contFor).equalsIgnoreCase(respostaEsperada)))
						animalEsperado = false;	// Se as respostas nao baterem, nao eh o animal correto
				}
			
				decl = obj.proxima(); // Pega a proxima pergunta
				
				repetido = false; // Reinicia "repetido"
			}
			/* Se sair do WHILE sem respostas negativas, o animal correto foi encontrado! O loop eh entao quebrado */
			if (animalEsperado != false)
				break;
		}
		
		boolean acertei = responder.finalAnswer(listaAnimais[animal]);
		
		if (acertei)
			System.out.println("Oba! Acertei!");
		else
			System.out.println("fuem! fuem! fuem!");
		
		/* Libera as listas */
		Perguntas.clear();
		Respostas.clear();
		
		return acertei;
	}

}
