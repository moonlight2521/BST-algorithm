/**
 * Created by zunlin on 11/13/17.
 * Assigment 3
 * CMSC 401
 */
import java.util.Scanner;

public class cmsc401 {

    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        int s = in.nextInt()+1;
        int w = in.nextInt();
        int i;
        int count = 0;

        int [] sArray = new int [s];
        int [] wArray = new int [w];

        for(i = 1; i<s; i++){
            sArray[i]= in.nextInt();
        }


        BinarySearchTree tree = new BinarySearchTree();
        Node root = tree.constructTree(sArray, s);

        for(i = 0; i < w; i++){
            wArray[i] = in.nextInt();
        }

        for(i = 0; i < w; i++){
            if(wArray[i] < sArray[s-1] && wArray[i] > sArray[0]){
                count++;
            }
        }
        System.out.println(count);
        for(i = 0; i < w; i++){
            tree.search(root,wArray[i]);
        }
//        System.out.println("Inorder traversal of the constructed tree is: ");
//        tree.printInorder(root);
    }
}

// Construct BST:
class Node{
    int data;
    Node left, right;

    Node(int d){
        data = d;
        left = right = null;
    }
}

class Index{
    int index = 0;
}

class BinarySearchTree{
    Index index = new Index();

    //recursive to construct BST from preIndex and keep in track of index in pre[].
    Node constructTreeUtil(int pre[], Index preIndex, int key, int min, int max, int size){
        //Base case
        if(preIndex.index >= size){
            return null;
        }
        Node root = null;
        // If current element of pre[] is in range, then only it is part of current subtree
        if(key > min && key < max){
            //Allocate memory for root of this subtree and increment *p
            root = new Node(key);
            preIndex.index = preIndex.index + 1;
            if(preIndex.index < size){
                //Construct the subtree under root
                //All nodes which are in range form min -> key will go here, left subtree
                root.left = constructTreeUtil(pre, preIndex, pre[preIndex.index], min, key, size);
                //All nodes which are in range form key -> max will go here, right subtree.
                root.right = constructTreeUtil(pre, preIndex, pre[preIndex.index], key, max, size);
            }
        }
        return root;
    }

    Node constructTree(int pre[], int size){
        int preIndex = 0;
        return constructTreeUtil(pre, index, pre[0], Integer.MIN_VALUE, Integer.MAX_VALUE, size);
    }
    Node search(Node curr, int val){
        if(curr.data >= val){
            if(curr.left.data < val){
                System.out.println(curr.left.data+1 + "  "+ curr.data);
                return null;
            }
            return search(curr.left, val);
        }
        else{
            if(curr.right.data >= val){
                System.out.println(curr.data+1 + " " + curr.right.data);
                return null;
            }
            return search(curr.right, val);
        }
    }
    void printInorder(Node node){
        if(node == null){
            return;
        }
        printInorder(node.left);
        System.out.print(node.data + " ");
        printInorder(node.right);
    }
}
