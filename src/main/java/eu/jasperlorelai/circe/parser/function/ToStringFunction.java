package eu.jasperlorelai.circe.parser.function;

import java.util.List;

import org.jetbrains.annotations.NotNull;

import eu.jasperlorelai.circe.parser.function.util.Function;
import eu.jasperlorelai.circe.parser.expression.util.NodeType;
import eu.jasperlorelai.circe.parser.function.util.ParameterType;
import eu.jasperlorelai.circe.parser.expression.NumberLiteralNode;
import eu.jasperlorelai.circe.parser.expression.util.ExpressionNode;

public class ToStringFunction extends Function {

	private NumberLiteralNode number;
	private NumberLiteralNode radix;

	@NotNull
	@Override
	public List<ParameterType> getParameterTypes() {
		return List.of(
				NodeType.NUMBER.asParameterType(),
				NodeType.NUMBER.asParameterType()
		);
	}

	@Override
	public void initializeArguments(List<ExpressionNode> arguments) {
		number = castNumber(arguments.get(0));
		radix = castNumber(arguments.get(1));
	}

	@NotNull
	@Override
	public ExpressionNode execute() {
		return stringNode(Integer.toString(number.valueInteger(), radix.valueInteger()));
	}

}
