package seasons;

public enum Season
{
	SPRING ("lime", "Spring"),
	SUMMER ("yellow", "Summer"),
	AUTUMN ("orange", "Autumn"),
	WINTER ("aqua", "Winter");
	private final String color;
	private final String text;
	Season(String clr, String txt)
	{
		color = clr;
		text = txt;
	}
    public String toString() {
        return "<"+color+">" + text;
    }
    public String getColor() {
        return "<"+color+">";
    }
}
