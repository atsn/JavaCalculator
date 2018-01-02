
/**
 * This is my own implementation of a stack
 * Anders Stubberup
 * @ChangedBy Anders Stubberup
 */

import org.w3c.dom.Node;

public class MyStack
{
	Object[] myStack;
	int top;
	int maxsize;

	public MyStack(int Maxsize)
	{
		maxsize = Maxsize;
		myStack = new Object[Maxsize];
		top = -1;
	}

	public Boolean push(Object item)
	{
		if (top != maxsize)
		{
			myStack[++top] = item;

			return true;
		}
		else
		{
			return false;
		}
	}

	public Object pop()
	{
		if (top == -1)
		{
			return null;
		}
		else
		{
			Object ReturnObject = myStack[top];
			myStack[top--] = null;
			return ReturnObject;

		}
	}

	public Object peek()
	{
		if (top == -1)
		{
			return null;
		}
		else
		{
			return myStack[top];
		}
	}

	public void Clear()
	{
		while (top != -1)
			pop();
	}

	public int size()
	{
		return top + 1;
	}

	public boolean isEmpty()
	{
		if (top == -1) return true;
		else return false;
	}
}
