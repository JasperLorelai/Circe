package eu.jasperlorelai.circe.parser.function;

import java.util.List;

import org.jetbrains.annotations.NotNull;

import eu.jasperlorelai.circe.parser.expression.ArrayNode;
import eu.jasperlorelai.circe.parser.function.util.Function;
import eu.jasperlorelai.circe.parser.expression.util.NodeType;
import eu.jasperlorelai.circe.parser.function.util.ParameterType;
import eu.jasperlorelai.circe.parser.expression.StringLiteralNode;
import eu.jasperlorelai.circe.parser.expression.util.ExpressionNode;

public class SizeFunction extends Function {

	private ArrayNode array;
	private StringLiteralNode string;

	@NotNull
	@Override
	public List<ParameterType> getParameterTypes() {
		return List.of(ParameterType.create(NodeType.ARRAY, NodeType.STRING));
	}

	@Override
	public void initializeArguments(List<ExpressionNode> arguments) {
		array = castArray(arguments.get(0));
		string = castString(arguments.get(0));
	}

	@NotNull
	@Override
	public ExpressionNode execute() {
		return numberNode(array == null ? string.quoteless().length() : array.array().size());
	}

}
