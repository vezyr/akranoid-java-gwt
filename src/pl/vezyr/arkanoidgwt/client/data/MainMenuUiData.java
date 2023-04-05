package pl.vezyr.arkanoidgwt.client.data;

public class MainMenuUiData implements UiData {

	private boolean loaded;
	
	public MainMenuUiData(boolean loaded) {
		this.loaded = loaded;
	}
	
	public boolean isLoaded() {
		return loaded;
	}
	
	public void setLoaded(boolean loaded) {
		this.loaded = loaded;
	}
}
