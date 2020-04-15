package com.softsuit.arbol;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class GrowthTest {

	private static Trunk<TwigInfo> trunk = new Trunk<TwigInfo>("trunkId", new Growth());
	
	@Test
	public void insertSeeds_checkGrowth() {
		Growth gt = new Growth();
		gt.addSeed("ABCD");
		
		int i = 0;
		String[] expected = {"ABCD","ABC","AB","A"};
		for (String seed : gt.getSeeds()) {
			assertEquals(expected[i++], seed);
		}
		
		gt.addSeed("ABCD");
		
		i = 0;
		expected = new String[]{"ABCD","ABC","AB","A"};
		for (String seed : gt.getSeeds()) {
			assertEquals(expected[i++], seed);
			assertEquals(4, gt.getSeeds().size());
		}
		
		gt.addSeed("BCD");
		
		i = 0;
		expected = new String[]{"BCD","BC","B"};
		for (String seed : expected) {
			assertEquals(true, gt.getSeeds().contains(seed));
			assertEquals(7, gt.getSeeds().size());
		}
	}
	
	@Test
	public void createRootTwigs_addSeveralRamifications_checkGrowth() {
		trunk = new Trunk<TwigInfo>("trunkId", new Growth());
		
		String viniciusDeMoraes = 
		"Quando a luz dos olhos meus " + 
		"E a luz dos olhos teus " + 
		"Resolvem se encontrar " + 
		"Ai, que bom que isso é, meu Deus " + 
		"Que frio que me dá " + 
		"O encontro desse olhar " + 
		
		"Mas se a luz dos olhos teus " + 
		"Resiste aos olhos meus " + 
		"Só pra me provocar " + 
		"Meu amor, juro por Deus " + 
		"Me sinto incendiar " + 
		
		"Meu amor, juro por Deus " + 
		"Que a luz dos olhos meus " + 
		"Já não pode esperar " + 
		"Quero a luz dos olhos meus " + 
		"Na luz dos olhos teus " + 
		"Sem mais lararará " + 
		
		"Pela luz dos olhos teus " + 
		"Eu acho, meu amor " + 
		"E só se pode achar " + 
		"Que a luz dos olhos meus " + 
		"Precisa se casar";
		
		String sonetoDeFidelidade = 
		"De tudo, ao meu amor serei atento" + 
		"Antes, e com tal zelo, e sempre, e tanto" + 
		"Que mesmo em face do maior encanto" + 
		"Dele se encante mais meu pensamento." + 
		
		"Quero vivê-lo em cada vão momento" + 
		"E em louvor hei de espalhar meu canto" + 
		"E rir meu riso e derramar meu pranto" + 
		"Ao seu pesar ou seu contentamento." + 
		
		"E assim, quando mais tarde me procure" + 
		"Quem sabe a morte, angústia de quem vive" + 
		"Quem sabe a solidão, fim de quem ama" + 
		
		"Eu possa me dizer do amor (que tive):" + 
		"Que não seja imortal, posto que é chama" + 
		"Mas que seja infinito enquanto dure.";
		
		String olhosDaAmada =
		"Ó minha amada" + 
		"Que olhos os teus" + 
		
		"São cais noturnos" + 
		"Cheios de adeus" + 
		"São docas mansas" + 
		"Trilhando luzes" + 
		"Que brilham longe" + 
		"Longe nos breus…" + 
		
		"Ó minha amada" + 
		"Que olhos os teus" + 
		
		"Quanto mistério" + 
		"Nos olhos teus" + 
		"Quantos saveiros" + 
		"Quantos navios" + 
		"Quantos naufrágios" + 
		"Nos olhos teus…" + 
		
		"Ó minha amada" + 
		"Que olhos os teus" + 
		
		"Se Deus houvera" + 
		"Fizera-os Deus" + 
		"Pois não os fizera" + 
		"Quem não soubera" + 
		"Que há muitas eras" + 
		"Nos olhos teus." + 
		
		"Ah, minha amada" + 
		"De olhos ateus" + 
		
		"Cria a esperança" + 
		"Nos olhos meus" + 
		"De verem um dia" + 
		"O olhar mendigo" + 
		"Da poesia" + 
		"Nos olhos teus.";
		
		String amorFogoQueArde = 
		"Amor é fogo que arde sem se ver" + 
		
		"Amor é fogo que arde sem se ver;" + 
		"É ferida que dói e não se sente;" + 
		"É um contentamento descontente;" + 
		"É dor que desatina sem doer;" + 
		
		"É um não querer mais que bem querer;" + 
		"É solitário andar por entre a gente;" + 
		"É nunca contentar-se de contente;" + 
		"É cuidar que se ganha em se perder;" + 
		
		"É querer estar preso por vontade;" + 
		"É servir a quem vence, o vencedor;" + 
		"É ter com quem nos mata lealdade." + 
		
		"Mas como causar pode seu favor" + 
		"Nos corações humanos amizade," + 
		"Se tão contrário a si é o mesmo Amor?";
		
		String braulioBessa =
		"Só eu sei cada passo por mim dado" + 
		"nessa estrada esburacada que é a vida," + 
		"passei coisas que até mesmo Deus duvida," + 
		"fiquei triste, capiongo, aperreado," + 
		"porém nunca me senti desmotivado," + 
		"me agarrava sempre numa mão amiga," + 
		"e de forças minha alma era munida" + 
		"pois do céu a voz de Deus dizia assim:" + 
		"- Suba o queixo, meta os pés, confie em mim," + 
		"vá pra luta que eu cuido das feridas";
		
		String maquinaDoMundo =
		"E como eu palmilhasse vagamente" + 
		"uma estrada de Minas, pedregosa," + 
		"e no fecho da tarde um sino rouco" + 
		
		"se misturasse ao som de meus sapatos" + 
		"que era pausado e seco; e aves pairassem" + 
		"no céu de chumbo, e suas formas pretas" + 
		
		"lentamente se fossem diluindo" + 
		"na escuridão maior, vinda dos montes" + 
		"e de meu próprio ser desenganado," + 
		
		"a máquina do mundo se entreabriu" + 
		"para quem de a romper já se esquivava" + 
		"e só de o ter pensado se carpia." + 
		
		"Abriu-se majestosa e circunspecta," + 
		"sem emitir um som que fosse impuro" + 
		"nem um clarão maior que o tolerável" + 
		
		"pelas pupilas gastas na inspeção" + 
		"contínua e dolorosa do deserto," + 
		"e pela mente exausta de mentar" + 
		
		"toda uma realidade que transcende" + 
		"a própria imagem sua debuxada" + 
		"no rosto do mistério, nos abismos." + 
		
		"Abriu-se em calma pura, e convidando" + 
		"quantos sentidos e intuições restavam" + 
		"a quem de os ter usado os já perdera" + 
		
		"e nem desejaria recobrá-los," + 
		"se em vão e para sempre repetimos" + 
		"os mesmos sem roteiro tristes périplos," + 
		
		"convidando-os a todos, em coorte," + 
		"a se aplicarem sobre o pasto inédito" + 
		"da natureza mítica das coisas";
		
		String[] seeds = {
				viniciusDeMoraes,
				sonetoDeFidelidade,
				olhosDaAmada,
				amorFogoQueArde,
				braulioBessa,
				maquinaDoMundo
		};
		
		for (String seed : seeds) {			
			for (String s : seed.split(" ")) {
				trunk.addTwig(new Twig<TwigInfo>(s));
			}
		}
		
		Growth growth = trunk.getGrowth();
		System.out.println("longst seed: " + growth.getLongstSeed());
		System.out.println(growth.getSeeds().size());
		for (String seed : growth.getSeeds()) {
			System.out.println(seed);
		}
		
	}

}
