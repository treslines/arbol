package com.softsuit.arbol;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TrunkTest {
	
	private static Trunk<TwigInfo> trunk = new Trunk<TwigInfo>("trunkId");

	@Test
	public void createRootTwigs() {
		String twig1Id = "A";
		String twig1Name = "branch1";
		
		String twig2Id = "B";
		String twig2Name = "branch2";
		
		String twig3Id = "C";
		String twig3Name = "branch3";
		
		String twig4Id = "D";
		String twig4Name = null;
		
		Twig<TwigInfo> twig1 = new Twig<TwigInfo>(twig1Id);
		twig1.setTwigInfo(new Info(twig1Name));
		
		Twig<TwigInfo> twig2 = new Twig<TwigInfo>(twig2Id);
		twig2.setTwigInfo(new Info(twig2Name));
		
		Twig<TwigInfo> twig3 = new Twig<TwigInfo>(twig3Id);
		twig3.setTwigInfo(new Info(twig3Name));
		
		Twig<TwigInfo> twig4 = new Twig<TwigInfo>(twig4Id);
		twig4.setTwigInfo(new Info(twig4Name));
		
		trunk.addTwig(twig1);
		trunk.addTwig(twig2);
		trunk.addTwig(twig3);
		trunk.addTwig(twig4);
		assertEquals(twig1Name, trunk.getTwigById(twig1Id).get().getTwigInfo().getDescription());
		assertEquals(twig2Name, trunk.getTwigById(twig2Id).get().getTwigInfo().getDescription());
		assertEquals(twig3Name, trunk.getTwigById(twig3Id).get().getTwigInfo().getDescription());
		assertEquals(twig4Name, trunk.getTwigById(twig4Id).get().getTwigInfo().getDescription());
	}
	
	@Test
	public void createRootTwigs_tryAddSameTwigsAgain_notPossible() {
		trunk = new Trunk<TwigInfo>("trunkId");
		createRootTwigs();
		createRootTwigs();
		assertEquals(4, trunk.getRamifications().size());
	}
	
	@Test
	public void createRootTwigs_tryAdd1DifferentRamification_possible() {
		trunk = new Trunk<TwigInfo>("trunkId");
		createRootTwigs();
		
		String twig2Id = "BB";
		String twig2Name = "branch4";
			
		Twig<TwigInfo> twig2 = new Twig<TwigInfo>(twig2Id);
		twig2.setTwigInfo(new Info(twig2Name));
		
		trunk.addTwig(twig2);
		
		assertEquals(twig2Name, trunk.getTwigById(twig2Id).get().getTwigInfo().getDescription());
		
	}
	
	@Test
	public void createRootTwigs_tryAdd2DifferentRamification_possible() {
		trunk = new Trunk<TwigInfo>("trunkId");
		createRootTwigs();
		
		String twig2Id = "BB";
		String twig2Name = "branch4";
			
		Twig<TwigInfo> twig2 = new Twig<TwigInfo>(twig2Id);
		twig2.setTwigInfo(new Info(twig2Name));
		
		trunk.addTwig(twig2);
		
		String twig3Id = "BBB";
		String twig3Name = "branch5";
			
		Twig<TwigInfo> twig3 = new Twig<TwigInfo>(twig3Id);
		twig3.setTwigInfo(new Info(twig3Name));
		
		trunk.addTwig(twig3);
		
		assertEquals(twig3Name, trunk.getTwigById(twig3Id).get().getTwigInfo().getDescription());
		
		String twig4Id = "BBBCD";
		String twig4Name = "branch6";
			
		Twig<TwigInfo> twig4 = new Twig<TwigInfo>(twig4Id);
		twig4.setTwigInfo(new Info(twig4Name));
		
		trunk.addTwig(twig4);
		
		assertEquals(twig4Name, trunk.getTwigById(twig4Id).get().getTwigInfo().getDescription());
		
		for (String seed : trunk.getGrowth().getSeeds()) {
			System.out.println(seed);
		}
		
	}
	
	@Test
	public void createRootTwigs_isLeaf_yes() {
		trunk = new Trunk<TwigInfo>("trunkId");
		createRootTwigs();
		
		boolean yes = true;
		String twig2Id = "B";
		assertEquals(yes, trunk.getTwigById(twig2Id).get().isTwigLeaf());
	}
	
	@Test
	public void createRootTwigs_isLeaf_no() {
		trunk = new Trunk<TwigInfo>("trunkId");
		createRootTwigs();
		
		String twig2Id = "BA";
		String twig2Name = "branch4";
			
		Twig<TwigInfo> twig2 = new Twig<TwigInfo>(twig2Id);
		twig2.setTwigInfo(new Info(twig2Name));
		
		trunk.addTwig(twig2);
		
		String twig3Id = "BAR";
		String twig3Name = "branch5";
			
		Twig<TwigInfo> twig3 = new Twig<TwigInfo>(twig3Id);
		twig3.setTwigInfo(new Info(twig3Name));
		
		trunk.addTwig(twig3);
		
		Twig<TwigInfo> twig = trunk.getTwigById(twig3Id).get();
		assertEquals(twig3Name, twig.getTwigInfo().getDescription());
		
		boolean no = true;
		assertEquals(no, twig.isTwigLeaf());
	}
	
	@Test
	public void createRootTwigs_caRemoveTwig_no() {
		trunk = new Trunk<TwigInfo>("trunkId");
		createRootTwigs();
		
		boolean no = false;
		String twig2Id = "A";
		assertEquals(no, trunk.getTwigById(twig2Id).get().isTwigDead());
	}
	
	@Test
	public void createRootTwigs_caRemoveTwig_yes() {
		trunk = new Trunk<TwigInfo>("trunkId");
		createRootTwigs();
		
		boolean yes = false;
		String twig2Id = "D";
		assertEquals(yes, trunk.getTwigById(twig2Id).get().isTwigDead());
	}
	
	@Test
	public void createRootTwigs_isRamificationOnly_no() {
		trunk = new Trunk<TwigInfo>("trunkId");
		createRootTwigs();
		
		boolean no = false;
		String twig2Id = "C";
		assertEquals(no, trunk.getTwigById(twig2Id).get().isTwigRamification());
	}
	
	@Test
	public void createRootTwigs_isRamificationOnly_yes() {
		trunk = new Trunk<TwigInfo>("trunkId");
		createRootTwigs();
		
		String twig2Id = "AB";
			
		Twig<TwigInfo> twig2 = new Twig<TwigInfo>(twig2Id);
		
		trunk.addTwig(twig2);
		
		String twig3Id = "ABR";
		String twig3Name = "branch5";
			
		Twig<TwigInfo> twig3 = new Twig<TwigInfo>(twig3Id);
		twig3.setTwigInfo(new Info(twig3Name));
		
		trunk.addTwig(twig3);
		
		Twig<TwigInfo> twig = trunk.getTwigById(twig2Id).get();
		assertEquals(null, twig.getTwigInfo());
		
		boolean yes = true;
		assertEquals(yes, twig.isTwigRamification());
	}

}
