import java.util.Stack;

/**
 * The main part of the calculator doing the calculations.
 * 
 * @author M. Kolling ¨
 * @ChangedBy Anders Stubberup
 * @version 0.1 (incomplete)
 */
public class CalcEngine
{
	char operator;
	int operand1;
	String displayValue;
	String displayValue2;

	/**
	 * Create a CalcEngine instance. Initialise its state so that it is ready
	 * for use.
	 */
	public CalcEngine()
	{
		operator = ' ';
		displayValue = "";
		displayValue2 = "";
		operand1 = 0;
	}

	/**
	 * Return the value that should currently be displayed on the calculator
	 * display.
	 */
	public String[] getDisplayValue()
	{
		String[] Strings = new String[2];
		Strings[0] = displayValue;
		Strings[1] = displayValue2;
		return (Strings);
	}

	/**
	 * A number button was pressed. Do whatever you have to do to handle it. The
	 * number value of the button is given as a parameter.
	 */
	public void ButtenPressed(String Button)
	{
		displayValue = displayValue + Button;
	}

	/**
	 * The '=' button was pressed.
	 */
	public void equals()
	{
		displayValue2 = Convert(displayValue);
		String result = Calculate(displayValue2);
		displayValue = result;
	}

	/**
	 * The 'C' (clear) button was pressed.
	 */
	public void clear()
	{
		displayValue = "";
		displayValue2 = "";
		operand1 = 0;

	}

	/**
	 * Return the title of this calculation engine.
	 */
	public String getTitle()
	{
		return ("My Calculator");
	}

	/**
	 * Return the author of this engine. This string is displayed as it is, so
	 * it should say something like "Written by H. Simpson".
	 */
	public String getAuthor()
	{
		return ("Anders Stubberup");
	}

	/**
	 * Return the version number of this engine. This string is displayed as it
	 * is, so it should say something like "Version 1.1".
	 */
	public String getVersion()
	{
		return ("Ver. 1.0");
	}

	public static String Convert(String Input)
	{
		String Return = "";
		MyStack Calcstack = new MyStack(999);
		char[] InputAray = Input.toCharArray();

		for (int i = 0; i < InputAray.length; i++)
		{
			switch (InputAray[i])
			{
			case '+':
			case '-':
				if (!Calcstack.isEmpty())
				{
					if ((char) Calcstack.peek() == '*' || (char) Calcstack.peek() == '/')
					{
						Return = Return + " " + Calcstack.pop() + " ";
						Calcstack.push(InputAray[i]);
						break;
					}
				}
			case '/':
			case '^':
			case '*':
				Return = Return + " ";
				Calcstack.push(InputAray[i]);
				break;
			case '(':
				Calcstack.push(InputAray[i]);
				break;
			case ')':

				while (!Calcstack.isEmpty() && (char) Calcstack.peek() != '(')
				{

					Return = Return + " " + Calcstack.pop();

				}
				if (!Calcstack.isEmpty()) Calcstack.pop();
				break;

			default:
				Return = Return + InputAray[i];
				InputAray[i] = 0;
				break;

			}
		}

		while (!Calcstack.isEmpty())
		{

			Return = Return + " " + Calcstack.pop();

		}
		return Return;
	}

	public static String Calculate(String Input)
	{

		String Return = "";
		String[] Ints = Input.split(" ");
		MyStack Calcstack = new MyStack(999);
		Double Caractor1;
		Double Caractor2;

		for (int i = 0; i < Ints.length; i++)
		{
			switch (Ints[i])
			{
			case "+":
				Caractor1 = (Double) Calcstack.pop();
				Caractor2 = (Double) Calcstack.pop();
				Calcstack.push(Caractor2 + Caractor1);
				break;
			case "-":
				Caractor1 = (Double) Calcstack.pop();
				Caractor2 = (Double) Calcstack.pop();
				Calcstack.push(Caractor2 - Caractor1);
				break;
			case "/":
				Caractor1 = (Double) Calcstack.pop();
				Caractor2 = (Double) Calcstack.pop();
				Calcstack.push(Caractor2 / Caractor1);
				break;
			case "*":
				Caractor1 = (Double) Calcstack.pop();
				Caractor2 = (Double) Calcstack.pop();
				Calcstack.push(Caractor2 * Caractor1);
				break;
			case "^":
				Caractor2 = (Double) Calcstack.pop();
				Caractor1 = (Double) Calcstack.pop();
				Calcstack.push(Math.pow(Caractor1, Caractor2));
				break;

			default:
				Calcstack.push(Double.parseDouble(Ints[i]));
				break;

			}

		}
		while (!Calcstack.isEmpty())
		{

			Return = Return + Calcstack.pop();

		}
		return Return;

	}
}