import java.util.ArrayList;
public class Currency implements CurrencyListener {
	private ArrayList<String> name = new ArrayList<String>();
	private ArrayList<Double> price = new ArrayList<Double>();
	private ArrayList<String> symbol = new ArrayList<String>();
	private ArrayList<String> errata = new ArrayList<String>();
	
	public ArrayList<String> getName() {
		return name;
	}
	public void setName(String thisname) {
		name.add(thisname);
	}
	public ArrayList<Double> getPrice() {
		return price;
	}
	public void setPrice(Double thisprice) {
		price.add(thisprice);
	}
	public ArrayList<String> getSymbol() {
		return symbol;
	}
	public void setSymbol(String thissymbol) {
		symbol.add(thissymbol);
	}
	public ArrayList<String> getErrata() {
		return errata;
	}
	public void setErrata(String thiserrata) {
		errata.add(thiserrata);
	}
	
	public void ClearArray() {
		name.clear();
		price.clear();
		symbol.clear();
		errata.clear();
	}
}
