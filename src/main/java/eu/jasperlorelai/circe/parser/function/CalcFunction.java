package eu.jasperlorelai.circe.parser.function;

import java.util.Map;
import java.util.List;
import java.util.HashMap;

import org.jetbrains.annotations.NotNull;

import net.objecthunter.exp4j.ExpressionBuilder;

import eu.jasperlorelai.circe.parser.function.util.Function;
import eu.jasperlorelai.circe.parser.function.util.Variables;
import eu.jasperlorelai.circe.exeption.FunctionCallException;
import eu.jasperlorelai.circe.parser.expression.util.NodeType;
import eu.jasperlorelai.circe.parser.function.util.ParameterType;
import eu.jasperlorelai.circe.parser.expression.NumberLiteralNode;
import eu.jasperlorelai.circe.parser.expression.StringLiteralNode;
import eu.jasperlorelai.circe.parser.expression.util.ExpressionNode;

public class CalcFunction extends Function {

	private StringLiteralNode string;

	@NotNull
	@Override
	public List<ParameterType> getParameterTypes() {
		return NodeType.STRING.asParameterTypes();
	}

	@Override
	public void initializeArguments(List<ExpressionNode> arguments) {
		string = castString(arguments.get(0));
	}

	@NotNull
	@Override
	public ExpressionNode execute() {
		try {
			Map<String, Double> variableMap = new HashMap<>();
			Variables.getVariables().forEach((name, value) -> {
				if (!(value instanceof NumberLiteralNode number)) return;
				variableMap.put(name, number.valueDouble());
			});

			return new NumberLiteralNode(new ExpressionBuilder(string.quoteless())
					.variables(variableMap.keySet())
					.build()
					.setVariables(variableMap)
					.evaluate() + ""
			);
		} catch (IllegalArgumentException | FunctionCallException e) {
			throw formatException(e);
		}
	}

}
