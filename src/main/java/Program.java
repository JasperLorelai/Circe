import eu.jasperlorelai.circe.Circe;

public class Program {

	public static void main(String[] args) {
		System.out.println(Circe.parse(String.join(" ", args)));
	}

}
