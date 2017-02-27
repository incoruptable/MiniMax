import java.util.ArrayList;
import java.util.List;

public class Node {
    public List<Node> children;
    private Node parent;
    private int resultOfGame;
    private int[] nodeState; //1 means that an O has been placed(Computer), 0 means that nothing is there, -1 means that an X has been placed(Human)

    public Node(Node parent) {
        this.parent = parent;
        nodeState = new int[9];
        children = new ArrayList<>();
    }

    public void setNodeState(int[] nodeState, int player, int position) {
        this.nodeState = nodeState;
        if (player == 1) {
            nodeState[position] = 1;
        } else if (player == -1) {
            nodeState[position] = -1;
        } else {
            System.out.print("SOMEHOW NO ONE PLACED IT. DONT ASK ME!!!\n");
            nodeState[position] = 0;
        }
    }

    public int[] getNodeState() {
        return nodeState;
    }

    public void setNodeState(int[] nodeState) {
        this.nodeState = nodeState;
    }
}
