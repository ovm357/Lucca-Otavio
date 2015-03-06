package pt.c01interfaces.s01knowledge.s02app.actors;

import pt.c01interfaces.s01knowledge.s01base.impl.BaseConhecimento;
import pt.c01interfaces.s01knowledge.s01base.inter.IBaseConhecimento;
import pt.c01interfaces.s01knowledge.s01base.inter.IDeclaracao;
import pt.c01interfaces.s01knowledge.s01base.inter.IEnquirer;
import pt.c01interfaces.s01knowledge.s01base.inter.IObjetoConhecimento;
import pt.c01interfaces.s01knowledge.s01base.inter.IResponder;

public class Enquirer implements IEnquirer
{
    IObjetoConhecimento obj;
	
	public Enquirer()
	{
	}
	
	
	@Override
	public void connect(IResponder responder)
	{
        IBaseConhecimento bc = new BaseConhecimento();
		String listaAnimais[] = bc.listaNomes();
		String storage[]= new String[30];
		int animal, contPer = 0, contFor;
		boolean repetido = false;
		
		/* Este FOR garante que todos os animais sejam testados pelo Enquirer */
		for (animal = 0; animal < listaAnimais.length; animal++) {
			obj = bc.recuperaObjeto(listaAnimais[animal]);
			IDeclaracao decl = obj.primeira();
			
	        boolean animalEsperado = true;
			while (decl != null && animalEsperado) {
				
				String pergunta = decl.getPropriedade();
				
				/* Este FOR compara a questao atual com as guardadas no estoque. Se for igual, a flag "repetido" vira true e o loop acaba. Se nao for, adiciona-a na lista */
				for(contFor = 0; contFor < 30; contFor = contFor+1) {
					if(storage[contFor]!= null) {
						if (storage[contFor].equalsIgnoreCase(pergunta)) {
							repetido = true;
							break;
						}
					}			
				}
				/* Se a pergunta nao for repetida, ela pode ser feita */
				if (!repetido) {
					storage[contPer] = pergunta;	
					// Conta as perguntas que ja passaram
					contPer++;
					String respostaEsperada = decl.getValor();
					
					String resposta = responder.ask(pergunta);
					
					if (!(resposta.equalsIgnoreCase(respostaEsperada)))
						animalEsperado = false;		
				}
				decl = obj.proxima();
				
				//marcar pergunta repetida
				
				/* Reinicia "repetido" */
				repetido = false;
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

	}

}