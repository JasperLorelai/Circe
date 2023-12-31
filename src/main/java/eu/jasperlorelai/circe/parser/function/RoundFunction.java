package eu.jasperlorelai.circe.parser.function;

import java.util.List;

import org.jetbrains.annotations.NotNull;

import eu.jasperlorelai.circe.parser.function.util.Function;
import eu.jasperlorelai.circe.parser.expression.util.NodeType;
import eu.jasperlorelai.circe.parser.function.util.ParameterType;
import eu.jasperlorelai.circe.parser.expression.NumberLiteralNode;
import eu.jasperlorelai.circe.parser.expression.util.ExpressionNode;

public class RoundFunction extends Function {

	private NumberLiteralNode number;

	@NotNull
	@Override
	public List<ParameterType> getParameterTypes() {
		return NodeType.NUMBER.asParameterTypes();
	}

	@Override
	public void initializeArguments(List<ExpressionNode> arguments) {
		number = castNumber(arguments.get(0));
	}

	@NotNull
	@Override
	public ExpressionNode execute() {
		return numberNode(number.valueInteger());
	}

}
