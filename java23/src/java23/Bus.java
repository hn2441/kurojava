package java23;

public class Bus extends Car{
	int person;
	
	public void station() {
		System.out.println("�͹��п� ����.");
	}

	@Override
	public String toString() {
		return "Bus [person=" + person + "]";
	}
	
	
}
