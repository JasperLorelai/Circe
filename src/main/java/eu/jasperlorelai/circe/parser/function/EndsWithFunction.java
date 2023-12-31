package eu.jasperlorelai.circe.parser.function;

import java.util.List;

import org.jetbrains.annotations.NotNull;

import eu.jasperlorelai.circe.parser.function.util.Function;
import eu.jasperlorelai.circe.parser.expression.util.NodeType;
import eu.jasperlorelai.circe.parser.function.util.ParameterType;
import eu.jasperlorelai.circe.parser.expression.StringLiteralNode;
import eu.jasperlorelai.circe.parser.expression.util.ExpressionNode;

public class EndsWithFunction extends Function {

	private StringLiteralNode string;
	private StringLiteralNode substring;

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
		substring = castString(arguments.get(1));
	}

	@NotNull
	@Override
	public ExpressionNode execute() {
		return stringFromBool(string.quoteless().endsWith(substring.quoteless()));
	}

}
