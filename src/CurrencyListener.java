import java.util.ArrayList;

public interface CurrencyListener {
	
	public ArrayList<String> getName();
	public void setName(String thisname);
	public ArrayList<Double> getPrice();
	public void setPrice(Double thisprice);
	public ArrayList<String> getSymbol();
	public void setSymbol(String thissymbol);
	public ArrayList<String> getErrata();
	public void setErrata(String thiserrata);
	public void ClearArray();
	

}
