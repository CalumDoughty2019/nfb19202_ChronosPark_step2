/*
 * CS251 Programming
 * Year 2, term 3
 *
 * Coursework Project 2020/21
 * by nfb19202 - Calum Doughty
 *
 */

/*
//For help with understanding BinaryTrees
https://cgi.csc.liv.ac.uk/~frans/OldLectures/COMP101/AdditionalStuff/javaDecTree.html
 */

import java.io.IOException;


/*
RecoTree is used to perform the actual functionality which allows a binary tree to be built and traversed
 */
public class RecoTree {
    //static BufferedReader capture = new BufferedReader(new InputStreamReader(System.in));

    //variables
    String answer = "";
    int count = 0;

    //Node class
    private class Node {

        //variables
        private final int nodeId;
        private String questionsAndAnswers = null;
        private Node yesPath = null;
        private Node noPath = null;

        //constructor
        public Node(int newNodeId, String newQOrA){
            this.nodeId = newNodeId;
            this.questionsAndAnswers = newQOrA;
        }
    }

    //variables
    Node root = null;

    //constructors
    //default constructor
    public RecoTree(){

    }


    //methods & functions
    /*
    Add the root node
     */
    public void addRoot(int newNodeId, String newQOrA){
        root = new Node(newNodeId, newQOrA);
    }


    /*
    Add yes Node
     */
    public void addYesNode(int oldNodeId, int newNodeId, String newQOrA){
        //if no root node then don't do anything
        if(root == null){
            System.out.println("Error: No root node");
            return;
        }

        //search tree
        addYesNodeWithSearch(root, oldNodeId, newNodeId, newQOrA);
    }


    /*
    search the tree and add the yes node
     */
    private boolean addYesNodeWithSearch(Node current, int oldNodeId, int newNodeId, String newQOrA) {
        if(current.nodeId == oldNodeId){
            //found the node
            if(current.yesPath == null){
                current.yesPath = new Node(newNodeId, newQOrA);
            }else{
                current.yesPath = new Node(newNodeId, newQOrA);
            }
            return true;
        }else{
            //try yes branch if it exixts
            if(current.yesPath != null){
                if(addYesNodeWithSearch(current.yesPath, oldNodeId, newNodeId, newQOrA)){
                    return true;
                }else{
                    //try no branch if it exists
                    if(current.noPath != null){
                        return(addYesNodeWithSearch(current.noPath, oldNodeId, newNodeId, newQOrA));
                    }
                    else{
                        return false;
                    }
                }
            }
            return false;
        }
    }


    /*
    Add no Node
     */
    public void addNoNode(int oldNodeId, int newNodeId, String newQOrA){
        //if no root node then don't do anything
        if(root == null){
            System.out.println("Error: No root node!");
            return;
        }

        //search tree
        addNoNodeWithSearch(root, oldNodeId, newNodeId, newQOrA);
    }


    /*
    search the tree and add the no node
     */
    private boolean addNoNodeWithSearch(Node current, int oldNodeId, int newNodeId, String newQOrA){
        if(current.nodeId == oldNodeId){
            //found node
            if(current.noPath == null){
                current.noPath = new Node(newNodeId, newQOrA);
            }else{
                current.noPath = new Node(newNodeId, newQOrA);
            }
            return true;
        }else{
            //try yes branch if it exists
            if(current.yesPath != null){
                if(addNoNodeWithSearch(current.yesPath, oldNodeId, newNodeId, newQOrA)){
                    return true;
                }else{
                    //try no branch if it exists
                    if(current.noPath != null){
                        return(addNoNodeWithSearch(current.noPath, oldNodeId, newNodeId, newQOrA));
                    }else{
                        return false;
                    }
                }
            }
            return false;
        }
    }


    /*
    query this tree giving the root and the visitor information
     */
    public String queryBinaryTree(Visitors visitor) throws IOException {
        queryBinaryTree(root, visitor);
        return answer;
    }


    /*
    query this tree giving the root(or current) and the visitor information
     */
    private String queryBinaryTree(Node current, Visitors visitor) throws IOException {
        //Test for leaf node (answer) and missing branches
        if(current.yesPath == null){
            if (current.noPath == null) {
                answer = current.questionsAndAnswers;
                return current.questionsAndAnswers;
            }else{
                System.out.println("Error: Missing Yes path at " +
                        current.questionsAndAnswers + " question");
                return null;
            }
        }
        if(current.noPath == null){
            System.out.println("Error: Missing No path at " +
                    current.questionsAndAnswers + " question");
            return null;
        }
        askQuestion(current, visitor);
        return current.questionsAndAnswers;
    }


    /*
    askQuestion is used in this to check the user answers and set them to a useable variable
     */
    private void askQuestion(Node current, Visitors visitor) throws IOException {
        //System.out.println(current.questionsAndAnswers + " (Y or N): ");
        //String answer = capture.readLine().toUpperCase();
        if(count == 0){
            if(visitor.isKidsQ()){ answer = "Y"; } else{answer = "N";}
            count++;
        }
        else if(count == 1){
            if(visitor.isWaterQ()){ answer = "Y"; } else{answer = "N";}
            count++;
        }
        else if(count == 2){
            if(visitor.isHorrorQ()){ answer = "Y"; } else{answer = "N";}
            count++;
        }
        else if(count == 3){
            if(visitor.isAdrenalineQ()){ answer = "Y"; } else{answer = "N";}
            count++;
        }
        if(answer.equals("Y")){
            queryBinaryTree(current.yesPath, visitor);
        }else{
            if(answer.equals("N")){
                queryBinaryTree(current.noPath, visitor);
            }else{
                //System.out.println("Error: Must answer Y or N");
                askQuestion(current, visitor);
            }
        }
    }

}
