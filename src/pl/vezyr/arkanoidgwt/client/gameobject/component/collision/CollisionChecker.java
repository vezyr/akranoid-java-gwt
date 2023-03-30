package pl.vezyr.arkanoidgwt.client.gameobject.component.collision;

public interface CollisionChecker {

	public CollisionResult checkCollision(Collidable obj1, Collidable obj2);
	
}
