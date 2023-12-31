package eu.jasperlorelai.circe.parser.function;

import java.util.List;
import java.util.ArrayList;

import org.jetbrains.annotations.NotNull;

import eu.jasperlorelai.circe.parser.expression.ArrayNode;
import eu.jasperlorelai.circe.parser.function.util.Function;
import eu.jasperlorelai.circe.parser.expression.util.NodeType;
import eu.jasperlorelai.circe.parser.function.util.ParameterType;
import eu.jasperlorelai.circe.parser.expression.NumberLiteralNode;
import eu.jasperlorelai.circe.parser.expression.StringLiteralNode;
import eu.jasperlorelai.circe.parser.expression.util.ExpressionNode;

public class UnshiftFunction extends Function {

	private ArrayNode array;
	private StringLiteralNode elementString;
	private NumberLiteralNode elementNumber;

	@NotNull
	@Override
	public List<ParameterType> getParameterTypes() {
		return List.of(
				NodeType.ARRAY.asParameterType(),
				ParameterType.create(NodeType.STRING, NodeType.NUMBER)
		);
	}

	@Override
	public void initializeArguments(List<ExpressionNode> arguments) {
		array = castArray(arguments.get(0));
		elementString = castString(arguments.get(1));
		elementNumber = castNumber(arguments.get(1));
	}

	@NotNull
	@Override
	public ExpressionNode execute() {
		List<String> list = new ArrayList<>();
		list.add(elementString == null ? elementNumber.value() : elementString.quoteless());
		array.array().forEach(item -> list.add(valueQuoteless(item)));
		return arrayNode(list);
	}

}
