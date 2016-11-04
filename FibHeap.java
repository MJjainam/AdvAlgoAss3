class FibHeap
{
	Node min = null;
	int numOfNodes = 0;
	public void insert(int value)
	{
		Node ins = new Node();
		ins.degree = 0;
		ins.value = value;
		ins.left = ins;
		ins.right = ins;
		concatenate(min,ins);
		if(min==null || ins.value<min.value)
			min = ins;
		numOfNodes++;

	}
	public void concatenate(Node X,Node newNode)
	{
		if(X==null)X = newNode;
		else
		{
			Node rightNode = X.right;
			newNode.left = X;
			newNode.right = rightNode;
			X.right = newNode;
			rightNode.left = newNode;

			
		}

	}
	public Node extractMin()
	{
		Node z = min;
		if(z!=null)
		{
			Node zchild = z.child;
			z.child = null;   //doubt
			if(zchild!=null)concatenate(z,zchild);
			if(z == z.right)
			{	
				min = null;
				
			}

			else
			{
				
				min.left.right = min.right;
				min.right.left = min.left;    //removing min node 
				
				z=min;
				min = min.right;

				consolidate();
			}
			numOfNodes--;
			return z;
		}
		return null;
	}
	public void consolidate()
	{
		Node newHeap = null;
		Node oldHeap = min;
		Node[] A = new Node[numOfNodes];
		while(oldHeap!=null)
		{
			Node oldHeapNode;
			if(oldHeap.left!=oldHeap)
			{
			oldHeapNode = oldHeap;
			oldHeap.left.right = oldHeap.right;
			oldHeap.right.left = oldHeap.left;
			oldHeap = oldHeap.left;
			
			}
			else
			{
				oldHeapNode = oldHeap;
				oldHeap = null;
			}

			if(A[oldHeapNode.degree]==null)
			{
				A[oldHeapNode.degree] = oldHeapNode;
				concatenate(newHeap,oldHeapNode);

			}
			else
			{
				if(oldHeapNode.value < A[oldHeapNode.degree].value)   //if the oldHeapNode inserting value is less 
				{


					exchange(oldHeapNode,A[oldHeapNode.degree]);
					Node temp = oldHeapNode;
					oldHeapNode = A[oldHeapNode.degree];
					A[oldHeapNode.degree] = temp;

				}

				heapLink(A[oldHeapNode.degree],oldHeapNode);
				A[oldHeapNode.degree+1]=A[oldHeapNode.degree];
				A[oldHeapNode.degree]=null;
				
			}
		} 
		min =null;
		for(int i=0;i<A.length;i++)
		{
			if(A[i]!=null)
			{
				if(min == null || min.value>A[i].value)
				{
					min = A[i];
				}
			}
		}
	}
	public void heapLink(Node x,Node y)
	{
		if(x.child==null)
		{
			x.child = y; 				
		}
		else
		{
		concatenate(x.child,y);
		}
	}
	public void exchange(Node x,Node y)
	{

		if(x!=x.left && y!=y.left)
		{
		x.left.right = y;
		x.right.left = y;

		y.left.right = x;
		y.right.left = x;

		Node tempXleft = x.left;
		Node tempXright = x.right;

		x.left = y.left;
		x.right = y.right;
		y.left = tempXleft;
		y.right = tempXright;
		}
		else if(x==x.left && y!=y.left)
		{
			y.left.right = x;
			y.right.left = x;

			x.left = y.left;
			x.right = y.right;

			y.left = null;
			y.right = null;
		}
		else if(x!=x.left && y==y.left)
		{
			x.left.right = y;
			x.right.left = y;

			y.left = x.left;
			y.right= x.right;

			x.left = null;
			x.right = null;

		}
		else if(x==x.left && y==y.left)
		{
			Node temp = x;
			x = y;
			y = temp;
		}


	}
	public static void main(String[] args)
	{
		FibHeap X = new FibHeap();
		X.insert(2);
		X.insert(3);
		X.insert(1);
		X.insert(61);

		System.out.println(X.extractMin().value);
		System.out.println(X.extractMin().value);
		System.out.println(X.extractMin().value);
		System.out.println(X.extractMin().value);
		

	}

}
class Node
{
	int value;
	int degree;
	Node parent;
	Node left;
	Node right;
	Node child;
	//public Node(value,degree,parent,ne)
}