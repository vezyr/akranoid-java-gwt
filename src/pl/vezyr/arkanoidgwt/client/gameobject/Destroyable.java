package pl.vezyr.arkanoidgwt.client.gameobject;

public interface Destroyable {

	public void takeDamage(int attackStrength);
	public void heal(int healPoints);
	public int currentHealth();
	public boolean isAlive();
	
}
