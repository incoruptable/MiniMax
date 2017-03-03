import java.util.Scanner;

/**
 * Created by jisazb1 on 2/27/2017.
 */
public class MiniMax {

    static Node bestOption;
    Node root;

    public MiniMax() {
        initializeTree();
        boolean stop = false;
        Scanner scanner = new Scanner(System.in);
        Node prevState = root;
        Node currentState;
        System.out.println("Hello HUMAN! Welcome to tic-tac toe! Good luck! ;)\n");
        printState(root.getNodeState());

//        //TESTING
//        currentState = prevState.getChildForPosition(0);
//        prevState = currentState;
//        currentState = prevState.getChildForPosition(3);
//        prevState = currentState;
//        currentState = prevState.getChildForPosition(6);
//        prevState = currentState;
//        currentState = prevState.getChildForPosition(4);
//        printState(currentState.getNodeState());
//        prevState = currentState;
        while (!stop) {

            System.out.println("Enter which space to place an X:");
            int position = scanner.nextInt();
            if (position <= 0 || position >= 10) {
                System.out.print("This position doesn't exist. What are you doing? ");
                continue;
            }
            currentState = prevState.getChildForPosition(position - 1);
            if (currentState == null) {
                System.out.print("This position is already filled. Please try again. ");
                continue;
            }
            if (currentState.gameOver) {
                if (currentState.getResultOfGame() == -10) {
                    System.out.print("YOU WIN THIS TIME HUMAN! :(");
                } else {
                    System.out.print("WE ARE EVENLY MATCHED I SEE!");
                }
                return;
            }
            printState(currentState.getNodeState());
            System.out.print("\n");
            prevState = currentState;
            currentState = findBestMove(prevState);
            printState(currentState.getNodeState());

            if (currentState.gameOver) {
                if (currentState.getResultOfGame() == 10) {
                    System.out.print("I KNEW YOU WERE NO MATCH FOR ME!");
                } else {
                    System.out.print("WE ARE EVENLY MATCHED I SEE!");
                }
                return;
            }
            System.out.print("\n");
            prevState = currentState;

        }

    }

    public static void main(String args[]) {
        MiniMax game = new MiniMax();
    }

    public Node findBestMove(Node currentState) {
        int bestValue = -2000000;
        Node bestMove = currentState;
        for (Node child : currentState.children) {

            int currentValue = miniMax(child, 0, 1);
            if (currentValue > bestValue) {
                bestMove = child;
                bestValue = currentValue;
            }

        }
        return bestMove;
    }

    public int miniMax(Node node, int depth, int player) {
        if (node.gameOver) {
            return node.getResultOfGame() - depth;
        }
        int bestValue;
        if (player == 1) {
            bestValue = -2000000;
            for (Node child : node.children) {
                int childValue = miniMax(child, depth + 1, -1);
                if (childValue > bestValue) {
                    bestValue = childValue;
                }
            }
        } else {
            bestValue = 2000000;
            for (Node child : node.children) {
                int childValue = miniMax(child, depth + 1, 1);
                if (childValue < bestValue) {
                    bestValue = childValue;
                }
            }
        }

        return bestValue;
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
        root = generateChildren(null, 0, -1);
    }

    public Node generateChildren(Node parent, int player, int position) {
        Node currentNode = new Node(parent);
        if (player != 0) {
            currentNode.setNodeState(player, position);
        }
        boolean gameOver = currentNode.checkIfGameOver();
        if (!gameOver) {
            for (int i = 0; i < 9; i++) {
                if (currentNode.getNodeState()[i] == 0) {
                    currentNode.children.add(generateChildren(currentNode, changePlayer(player), i));
                }
            }
        }
        return currentNode;
    }

    public int changePlayer(int player) {
        if (player == 0) {
            return -1;
        } else if (player == -1) {
            return 1;
        } else {
            return -1;
        }
    }

}
