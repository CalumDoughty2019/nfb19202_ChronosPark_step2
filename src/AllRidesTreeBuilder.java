/*
 * CS251 Programming
 * Year 2, term 3
 *
 * Coursework Project 2020/21
 * by nfb19202 - Calum Doughty
 *
 */

import java.io.IOException;
import java.util.ArrayList;

/*
Use this class to create the binary decision tree which will be used to decide if a user is eligible for a ride or not
 */
public class AllRidesTreeBuilder {

    //static BufferedReader capture = new BufferedReader(new InputStreamReader(System.in));

    //variables
    static RecoTree tree;
    ArrayList<Ride> suitableRides = new ArrayList<>();

    /*
    runner is used to call the different functionality of the class into action
     */
    public String runner(Visitors visitor) throws IOException {
        //create instance of class RecoTree
        tree = new RecoTree();
        //generate tree
        generateTree();

        String answer = queryTree(visitor);
        return answer;
    }

    /*
    generate
    //ANSWERS start at id 30
    //use a binary encoder for all 20 rides
    Binary 1 represents still live, 0 represents dead choice
     */
    static void generateTree(){
        tree.addRoot(1, "Root"); //kidsQ
        tree.addYesNode(1, 2, "11111111111111111111"); //waterQ         //y
        tree.addNoNode(1, 3, "11101001001010001010"); //waterQ          //n

        //results from water question below
        tree.addYesNode(2, 4, "11111111111111111111"); //horror         //yy
        tree.addNoNode(2, 5, "01011011110101111100"); //horror          //yn

        tree.addYesNode(3, 6, "11101001001010001010"); //horror         //ny
        tree.addNoNode(3, 7, "01001001000000000000"); //horror          //nn

        //results from horror questions below
        tree.addYesNode(4, 8, "11111111111111111111"); //adrenaline     //yyy
        tree.addNoNode(4, 9, "01010110100111111000"); //adrenaline      //yyn

        tree.addYesNode(5, 10, "01011011110101100011"); //adrenaline    //yny
        tree.addNoNode(5, 11, "01010010100101100000"); //adrenaline     //ynn

        tree.addYesNode(6, 12, "11101001001010001010"); //adrenaline    //nyy
        tree.addNoNode(6, 13, "01000000000010001000"); //adrenaline     //nyn

        tree.addYesNode(7, 14, "01001001000000000000"); //adrenaline    //nny
        tree.addNoNode(7, 15, "01000000000000000000"); //adrenaline     //nnn

        //ANSWERS
        //results from adrenaline question below
        tree.addYesNode(8, 30, "11111111111111111111"); //yyyy
        tree.addNoNode(8, 31, "10010010000100100000"); //yyyn

        tree.addYesNode(9, 32, "01010110100111111000"); //yyny
        tree.addNoNode(9, 33, "00010010000100100000"); //yynn

        tree.addYesNode(10, 34, "01011011110101100011"); //ynyy
        tree.addNoNode(10, 35, "00010010000100100000"); //ynyn

        tree.addYesNode(11, 36, "01010010100101100000"); //ynny
        tree.addNoNode(11, 37, "00010010000100100000"); //ynnn

        //starting with no
        tree.addYesNode(12, 38, "11101001001010001010"); //nyyy
        tree.addNoNode(12, 39, "10000000000000000000"); //nyyn

        tree.addYesNode(13, 40, "01000000000010001000"); //nyny
        tree.addNoNode(13, 41, "00000000000000000000"); //nynn

        tree.addYesNode(14, 42, "01001001000000000000"); //nnyy
        tree.addNoNode(14, 43, "00000000000000000000"); //nnyn

        tree.addYesNode(15, 44, "01000000000000000000"); //nnny
        tree.addNoNode(15, 45, "00000000000000000000"); //nnnn
    }


    /*
    used to query the RecoTree
     */
    static String queryTree(Visitors visitor) throws IOException {
        return tree.queryBinaryTree(visitor);
    }

}
