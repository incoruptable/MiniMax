import java.util.ArrayList;
import java.util.List;

public class Node {
    public List<Node> children;
    public boolean gameOver;
    private Node parent;
    private int resultOfGame; //1 means that computer has won, 0 means that the game was a tie, -1 means that human has won,
    private int[] nodeState; //1 means that an O has been placed(Computer), 0 means that nothing is there, -1 means that an X has been placed(Human)

    public Node(Node parent) {
        this.parent = parent;
        nodeState = new int[9];
        children = new ArrayList<>();
    }

    public void setNodeState(int player, int position) {
        this.nodeState = parent.getNodeState().clone();
        if (player == 1) {
            nodeState[position] = 1;
        } else if (player == -1) {
            nodeState[position] = -1;
        } else {
            System.out.print("SOMEHOW NO ONE PLACED IT. DONT ASK ME!!!\n");
            nodeState[position] = 0;
        }
    }

    public Node getChildForPosition(int position) {
        if (nodeState[position] != 0) {
            return null;
        }

        for (Node child : children) {
            if (child.getNodeState()[position] != 0) {
                return child;
            }
        }
        return null;
    }

    public boolean checkIfGameOver() {
        //Check each possible situation where the game would end
        //Check for Wins across
        for (int i = 0; i < 3; i++) {
            if (nodeState[i * 3] == 1) {
                FOUND:
                {
                    for (int j = 1; j < 3; j++) {
                        if (nodeState[i * 3 + j] != 1) {
                            break FOUND;
                        }
                    }
                    resultOfGame = 10;
                    gameOver = true;
                    return gameOver;
                }
            } else if (nodeState[i * 3] == -1) {
                FOUND:
                {
                    for (int j = 1; j < 3; j++) {
                        if (nodeState[i * 3 + j] != -1) {
                            break FOUND;
                        }
                    }
                    resultOfGame = -10;
                    gameOver = true;
                    return gameOver;
                }
            }
        }


        //Check for wins down
        for (int i = 0; i < 3; i++) {
            if (nodeState[i] == 1) {
                FOUND:
                {
                    for (int j = 1; j < 3; j++) {
                        if (nodeState[i + j * 3] != 1)
                            break FOUND;
                    }
                    resultOfGame = 10;
                    gameOver = true;
                    return gameOver;
                }
            } else if (nodeState[i] == -1) {
                FOUND:
                {
                    for (int j = 1; j < 3; j++) {
                        if (nodeState[i + j * 3] != -1)
                            break FOUND;
                    }
                    resultOfGame = -10;
                    gameOver = true;
                    return gameOver;
                }
            }
        }

        //check for wins diagonally
        //check from left to right
        if (nodeState[0] == 1) {
            FOUND:
            {
                for (int i = 1; i < 3; i++) {
                    if (nodeState[i * 4] != 1) {
                        break FOUND;
                    }
                }
                resultOfGame = 10;
                gameOver = true;
                return gameOver;
            }
        } else if (nodeState[0] == -1) {
            FOUND:
            {
                for (int i = 1; i < 3; i++) {
                    if (nodeState[i * 4] != -1) {
                        break FOUND;
                    }
                }
                resultOfGame = -10;
                gameOver = true;
                return gameOver;
            }
        }

        //check from right to left
        if (nodeState[2] == 1) {
            FOUND:
            {
                for (int i = 1; i < 3; i++) {
                    if (nodeState[i * 2 + 2] != 1) {
                        break FOUND;
                    }
                }
                resultOfGame = 10;
                gameOver = true;
                return gameOver;
            }
        } else if (nodeState[2] == -1) {
            FOUND:
            {
                for (int i = 1; i < 3; i++) {
                    if (nodeState[i * 2 + 2] != -1) {
                        break FOUND;
                    }
                }
                resultOfGame = -10;
                gameOver = true;
                return gameOver;
            }
        }

        for (int i = 0; i < 9; i++) {
            if (nodeState[i] == 0) {
                gameOver = false;
                return gameOver;
            }
        }
        resultOfGame = 0;
        gameOver = true;
        return gameOver;
    }

    public int[] getNodeState() {
        return nodeState;
    }

    public void setNodeState(int[] nodeState) {
        this.nodeState = nodeState;
    }

    public int getResultOfGame() {
        return this.resultOfGame;
    }
}
