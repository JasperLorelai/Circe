package eu.jasperlorelai.circe.parser.function;

import java.util.List;
import java.util.Collections;

import org.jetbrains.annotations.NotNull;

import eu.jasperlorelai.circe.parser.expression.ArrayNode;
import eu.jasperlorelai.circe.parser.function.util.Function;
import eu.jasperlorelai.circe.parser.expression.util.NodeType;
import eu.jasperlorelai.circe.parser.function.util.ParameterType;
import eu.jasperlorelai.circe.parser.expression.util.ExpressionNode;

public class ReverseArrayFunction extends Function {

	private ArrayNode array;

	@NotNull
	@Override
	public List<ParameterType> getParameterTypes() {
		return NodeType.ARRAY.asParameterTypes();
	}

	@Override
	public void initializeArguments(List<ExpressionNode> arguments) {
		array = castArray(arguments.get(0));
	}

	@NotNull
	@Override
	public ExpressionNode execute() {
		Collections.reverse(array.array());
		return array;
	}

}
