public class Physics {

	public Physics() {
		
	}
	public static boolean determineElasticity(Entity a, Entity b){
		//returns true if the impact is elastic
		return true;
	}
	public static void  calculateImpact(Entity a, Entity b){
		if (determineElasticity(a, b))
			elasticCollision(a, b);
		else
			inelasticCollision(a, b);
	}
	public static void elasticCollision(Entity a, Entity b){
		//Conservation of kinetic energy, and momentum <==>
		double mom = a.getMomentum() + b.getMomentum();
		double sumE = a.getKineticEnergy() + b.getKineticEnergy();
	}
	public static void inelasticCollision(Entity a, Entity b){
		//Conservation of momentum, not kinetic energy =>=>
		double mom = a.getMomentum() + b.getMomentum();
		double sumE = a.getKineticEnergy() + b.getKineticEnergy();
		double newMomA = 0;
	}
	public static void partiallyElasticCollision(Entity a, Entity b){
		//Variable conservation of energy, momentum conserved
		//clone, scale, feed, sum
		double tol1 = a.getTolerance();
		double tol2 = b.getTolerance();
	}
}