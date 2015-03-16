package pt.c02classes.s01knowledge.s02app.app;

import pt.c02classes.s01knowledge.s01base.impl.BaseConhecimento;
import pt.c02classes.s01knowledge.s01base.impl.Statistics;
import pt.c02classes.s01knowledge.s01base.inter.IBaseConhecimento;
import pt.c02classes.s01knowledge.s01base.inter.IEnquirer;
import pt.c02classes.s01knowledge.s01base.inter.IResponder;
import pt.c02classes.s01knowledge.s01base.inter.IStatistics;
import pt.c02classes.s01knowledge.s02app.actors.EnquirerAnimals;
import pt.c02classes.s01knowledge.s02app.actors.ResponderAnimals;
import java.util.Scanner;

public class OrchestratorInit {

	public static void main(String[] args)
	{
		IEnquirer enq;
		IResponder resp;
		IStatistics stat;
		
		IBaseConhecimento base = new BaseConhecimento();

		boolean flag = false;
		base.setScenario("animals");
		Scanner entrada = new Scanner(System.in);
		System.out.println("Escolha o animal: ");
		String animalEscolhido = entrada.nextLine();
		String listaAnimais[] = base.listaNomes();
        for (int animal = 0; animal < listaAnimais.length; animal++) {
        	if(animalEscolhido.equalsIgnoreCase(listaAnimais[animal])){
				System.out.println("Enquirer com " + listaAnimais[animal] + "...");
				stat = new Statistics();
				resp = new ResponderAnimals(stat, listaAnimais[animal]);
				enq = new EnquirerAnimals();
				enq.connect(resp);
				enq.discover();
				System.out.println("----------------------------------------------------------------------------------------\n");
				flag = true;
				break;
        	}
        }
        if(!flag)
        	System.out.println("Animal não encontrado!");
        entrada.close();
	}
}
