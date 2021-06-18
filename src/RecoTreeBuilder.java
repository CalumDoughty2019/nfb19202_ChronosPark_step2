/*
 * CS251 Programming
 * Year 2, term 3
 *
 * Coursework Project 2020/21
 * by nfb19202 - Calum Doughty
 *
 */

import java.io.IOException;

/*
Use this class to create the binary decision tree which will be used to decide if a user is eligible for a ride or not
 */
public class RecoTreeBuilder {

    //static BufferedReader capture = new BufferedReader(new InputStreamReader(System.in));

    //variables
    static RecoTree tree;

    /*
    runner is used to call the different functionality of the class into action
     */
    public void runner(Visitors visitor) throws IOException {
        //create instance of class RecoTree
        tree = new RecoTree();
        //generate tree
        generateTree();

        //System.out.println("Which of the following types of rides do you like?");

        //query tree
        String answer = queryTree(visitor);
        //set visitor
        setVisitor(visitor, answer);
    }

    /*
    generate
    //ANSWERS start at id 30
     */
    static void generateTree(){
        tree.addRoot(1, "Kids");
        tree.addYesNode(1, 2, "Water");
        tree.addNoNode(1, 3, "Water");

        tree.addYesNode(2, 4, "Horror");
        tree.addNoNode(2, 5, "Horror");

        tree.addYesNode(3, 6, "Horror");
        tree.addNoNode(3, 7, "Horror");

        tree.addYesNode(4, 8, "Adrenaline");
        tree.addNoNode(4, 9, "Adrenaline");

        tree.addYesNode(5, 10, "Adrenaline");
        tree.addNoNode(5, 11, "Adrenaline");

        tree.addYesNode(6, 12, "Adrenaline");
        tree.addNoNode(6, 13, "Adrenaline");

        tree.addYesNode(7, 14, "Adrenaline");
        tree.addNoNode(7, 15, "Adrenaline");

        //ANSWERS
        //starting with yes
        tree.addYesNode(8, 30, "yyyy"); //route of all yes'
        tree.addNoNode(8, 31, "yyyn");

        tree.addYesNode(9, 32, "yyny");
        tree.addNoNode(9, 33, "yynn");

        tree.addYesNode(10, 34, "ynyy");
        tree.addNoNode(10, 35, "ynyn");

        tree.addYesNode(11, 36, "ynny");
        tree.addNoNode(11, 37, "ynnn");

        //starting with no
        tree.addYesNode(12, 38, "nyyy");
        tree.addNoNode(12, 39, "nyyn");

        tree.addYesNode(13, 40, "nyny");
        tree.addNoNode(13, 41, "nynn");

        tree.addYesNode(14, 42, "nnyy");
        tree.addNoNode(14, 43, "nnyn");

        tree.addYesNode(15, 44, "nnny");
        tree.addNoNode(15, 45, "nnnn");
    }


    /*
    used to query the RecoTree
     */
    static String queryTree(Visitors visitor) throws IOException {
        return tree.queryBinaryTree(visitor);
    }


    /*
    used to set a Visitor visitor's variables relative to their inputs
     */
    private void setVisitor(Visitors visitor, String answer) {
        //KidsQ
        if(answer.charAt(0) == 'y'){
            visitor.setKidsQ(true);
        }else{
            visitor.setKidsQ(false);
        }

        //WaterQ
        if(answer.charAt(1) == 'y'){
            visitor.setWaterQ(true);
        }else{
            visitor.setWaterQ(false);
        }

        //HorrorQ
        if(answer.charAt(2) == 'y'){
            visitor.setHorrorQ(true);
        }else{
            visitor.setHorrorQ(false);
        }

        //AdrenalineQ
        if(answer.charAt(3) == 'y'){
            visitor.setAdrenalineQ(true);
        }else{
            visitor.setAdrenalineQ(false);
        }
    }

}
