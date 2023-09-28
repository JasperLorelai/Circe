package eu.jasperlorelai.circe.tokenizer;

import java.util.List;
import java.util.ArrayList;
import java.util.regex.Matcher;

import eu.jasperlorelai.circe.tokenizer.type.util.TokenType;
import eu.jasperlorelai.circe.exeption.UnknownTokenException;
import eu.jasperlorelai.circe.tokenizer.type.util.TokenTypeStore;

public class Tokenizer {

	private List<Token> tokens;
	private int pointer;

	public record Token(TokenType tokenType, String value) {}

	public Tokenizer() {
		tokens = new ArrayList<>();
		pointer = 0;
	}

	/**
	 * @throws UnknownTokenException if the string contains an unknown token
	 */
	public void tokenize(String string) {
		tokens = new ArrayList<>();
		Matcher matcher;
		boolean hasMatch;

		while (!string.isEmpty()) {
			hasMatch = false;

			for (TokenType tokenType : TokenTypeStore.getIgnoredTokenTypes()) {
				matcher = tokenType.regex().matcher(string);
				if (!matcher.find()) continue;
				hasMatch = true;
				string = matcher.replaceFirst("");
				break;
			}
			if (hasMatch) continue;

			for (TokenType tokenType : TokenTypeStore.getTokenTypes()) {
				matcher = tokenType.regex().matcher(string);
				if (!matcher.find()) continue;
				hasMatch = true;
				tokens.add(new Token(tokenType, matcher.group()));
				string = matcher.replaceFirst("");
				break;
			}
			if (hasMatch) continue;

			throw new UnknownTokenException("Unknown token: " + string);
		}
	}

	public boolean hasMoreTokens() {
		return pointer < tokens.size();
	}

	public Token getNextToken() {
		return hasMoreTokens() ? tokens.get(pointer++) : null;
	}

}
