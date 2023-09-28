package eu.jasperlorelai.circe.parser.function;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import eu.jasperlorelai.circe.parser.function.util.Function;
import eu.jasperlorelai.circe.parser.expression.util.NodeType;
import eu.jasperlorelai.circe.parser.function.util.ParameterType;
import eu.jasperlorelai.circe.parser.expression.StringLiteralNode;
import eu.jasperlorelai.circe.parser.expression.util.ExpressionNode;

public class MatchFunction extends Function {

	private StringLiteralNode string;
	private StringLiteralNode pattern;

	@NotNull
	@Override
	public List<ParameterType> getParameterTypes() {
		return List.of(
				NodeType.STRING.asParameterType(),
				NodeType.STRING.asParameterType()
		);
	}

	@Override
	public void initializeArguments(List<ExpressionNode> arguments) {
		string = castString(arguments.get(0));
		pattern = castString(arguments.get(1));
	}

	@NotNull
	@Override
	public ExpressionNode execute() {
		try {
			List<String> list = new ArrayList<>();
			Matcher matcher = Pattern.compile(pattern.quoteless()).matcher(string.quoteless());
			while (matcher.find()) {
				for (int j = 0; j <= matcher.groupCount(); j++) {
					list.add(matcher.group(j));
				}
			}
			return arrayNode(list);
		} catch (PatternSyntaxException e) {
			throw formatException(e);
		}
	}

}
