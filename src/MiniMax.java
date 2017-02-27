/**
 * Created by jisazb1 on 2/27/2017.
 */
public class MiniMax {

    Node root;

    public MiniMax() {
        initializeTree();
    }

    public static void main(String args[]) {
        MiniMax game = new MiniMax();
    }

    public void printState(int[] state) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (state[i * 3 + j] == 1)
                    System.out.print("O");
                else if (state[i * 3 + j] == -1)
                    System.out.print("X");
                else
                    System.out.print(i * 3 + j + 1);
                if (j < 2) {
                    System.out.print("|");
                }
            }
            if (i < 2) {
                System.out.print("\n------");
            }
            System.out.print("\n");
        }
    }

    public void initializeTree() {
        root = new Node(null);
        root.setNodeState(new int[9]);
        generateChildren(root, 0, -1);
    }

    public Node generateChildren(Node parent, int player, int position) {
        Node currentNode = new Node(parent);
        if (player != 0) {
            currentNode.setNodeState(parent.getNodeState(), player, position);
        }

        for (int i = 0; i < 9; i++) {
            if (currentNode.isValidMove(i)) {
                currentNode.children.add(generateChildren(currentNode, changePlayer(player), position));
            }
        }
        return currentNode;
    }

    public int changePlayer(int player) {
        if (player == 0) {
            return -1;
        } else if (player == -1) {
            return 1;
        } else if (player == 1) {
            return -1;
        }
    }

}
