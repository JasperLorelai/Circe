package eu.jasperlorelai.circe;

import eu.jasperlorelai.circe.parser.Parser;
import eu.jasperlorelai.circe.exeption.ParserException;
import eu.jasperlorelai.circe.exeption.UnknownTokenException;
import eu.jasperlorelai.circe.exeption.FunctionCallException;

public class Circe {

	private Circe() {}

	public static String parse(String code) {
		// Start flags.
		boolean hasDebug = code.startsWith("debug;");
		if (hasDebug) code = code.replaceFirst("debug;", "");

		try {
			return new Parser().parse(code);
		} catch (UnknownTokenException | ParserException | FunctionCallException exception) {
			if (hasDebug) return exception.getClass().getSimpleName() + ": " + exception.getMessage();
		}
		return "";
	}

}
