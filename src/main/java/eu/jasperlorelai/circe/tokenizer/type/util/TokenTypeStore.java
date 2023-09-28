package eu.jasperlorelai.circe.tokenizer.type.util;

import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;

import eu.jasperlorelai.circe.tokenizer.type.LiteralTokenType;
import eu.jasperlorelai.circe.tokenizer.type.SpecialTokenType;
import eu.jasperlorelai.circe.tokenizer.type.FunctionTokenType;

public class TokenTypeStore {

	private static final List<TokenType> IGNORED_TOKEN_TYPES = List.of(
			SpecialTokenType.WHITESPACE,
			SpecialTokenType.SINGLE_COMMENT,
			SpecialTokenType.MULTI_COMMENT
	);

	private static final List<TokenType> TOKEN_TYPES = new ArrayList<>();
	static {
		add(FunctionTokenType.values());
		add(LiteralTokenType.values());
		add(SpecialTokenType.values());

		TOKEN_TYPES.removeAll(IGNORED_TOKEN_TYPES);
	}

	private static void add(TokenType[] types) {
		getTokenTypes().addAll(Arrays.asList(types));
	}

	public static List<TokenType> getIgnoredTokenTypes() {
		return IGNORED_TOKEN_TYPES;
	}

	public static List<TokenType> getTokenTypes() {
		return TOKEN_TYPES;
	}

}
