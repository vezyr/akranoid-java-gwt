package pl.vezyr.arkanoidgwt.client.gameobject.component.collision;

public interface Collidable {

	public Collider getCollider();
	public void handleCollision(CollisionResult collision);
	
}
