package spoiling;

public class FreshnessStages {
	
	public enum Freshness
	{
		VERY_FRESH ("lime", "Very Fresh"),
		FRESH ("green", "Fresh"),
		STALE ("yellow", "Stale"),
		OLD ("orange", "Old"),
		SPOILED ("red", "Spoiled");
		private final String color;
		private final String text;
		Freshness(String clr, String txt)
		{
			color = clr;
			text = txt;
		}
	    public String toString() {
	        return "<"+color+">" + text;
	        //return "<"+color+">" + name().charAt(0) + name().substring(1).toLowerCase();
	    }
	    public String getColor() {
	        return "<"+color+">";
	    }
	}
	
	public static int n_freshness_stages = Freshness.values().length;
	
	public static Freshness getFreshness(float percentage_gone)
	{
		int idx = (int) (percentage_gone * (n_freshness_stages));
		if (idx >= n_freshness_stages)
		{
			idx = n_freshness_stages - 1;
		}
		else if (idx < 0)
		{
			idx = 0;
		}
		return Freshness.values()[idx];
	}
}
