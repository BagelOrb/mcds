package seasons;


public enum Season
{
	SPRING ("lime", "Spring", 1.0f, 0.5f),
	SUMMER ("yellow", "Summer", 0.1f, 2.0f),
	AUTUMN ("orange", "Autumn", 4.0f, 1.0f),
	WINTER ("aqua", "Winter", 5.0f, 0.75f);
	private final String color;
	private final String text;
	private final float rain_chance;
	private final float thunder_chance;
	Season(String clr, String txt, float rain_chance, float thunder_chance)
	{
		color = clr;
		text = txt;
		this.rain_chance = rain_chance;
		this.thunder_chance = thunder_chance;
	}
    public String toString() {
        return "<"+color+">" + text;
    }
    public String getColor() {
        return "<"+color+">";
    }
    public float getThunderChance()
    {
    	return thunder_chance;
    }
    
    public float getRainChance()
    {
    	return rain_chance;
    }
}
