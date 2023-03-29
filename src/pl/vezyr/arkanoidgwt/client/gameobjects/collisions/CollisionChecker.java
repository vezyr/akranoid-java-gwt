package pl.vezyr.arkanoidgwt.client.gameobjects.collisions;

public interface CollisionChecker {

	public CollisionResult checkCollision(Collidable obj1, Collidable obj2);
	
}
